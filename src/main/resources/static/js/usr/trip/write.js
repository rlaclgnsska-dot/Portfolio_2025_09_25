
// imgUpload
function upload(event) {
    const files = event.target.files;
    const imagePreview = document.getElementById('imagePreview');
    
    for (let i = 0; i < files.length; i++) {
		const file = files[i];
		
		if (file && file.type.startsWith('image/')) {
			
	        const img = document.createElement('img');
	        img.src = URL.createObjectURL(file);
	        img.style.maxWidth = '200px';
	        img.style.maxHeight = '200px';
	        img.style.margin = '5px';
	        img.style.border = '1px solid #ddd';
	        img.style.borderRadius = '4px';
	        
	        imagePreview.appendChild(img);
	        
            const editorImg = document.createElement('img');
            editorImg.src = URL.createObjectURL(file);
            editorImg.style.maxWidth = '100%';
            editorImg.style.height = 'auto';
            editorImg.setAttribute('data-temp-image', 'true');
            document.getElementById('editor').appendChild(editorImg);
			
		}
		
	}
    
}




// 가격 입력 관련 함수들
function addCommas(num) {
    return num.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

function removeCommas(str) {
    return str.replace(/,/g, '');
}

function getActualPrice() {
    const priceInput = document.getElementById('priceInput');
    return removeCommas(priceInput.value);
}

// not null 컬럼 검증
const writeForm_onSubmit = function(form) {
	
    const editorContent = document.getElementById('editor').innerHTML;
    
    let bodyInput = form.querySelector('input[name="body"]');
    if (!bodyInput) {
        bodyInput = document.createElement('input');
        bodyInput.type = 'hidden';
        bodyInput.name = 'body';
        form.appendChild(bodyInput);
    }
    
    bodyInput.value = editorContent;
	
	
    // 기존 검증 로직
    form.placeName.value = form.placeName.value.trim(); // valaue -> value 오타 수정
    form.tripStartTime.value = form.tripStartTime.value.trim(); 
    
    // 지역 선택 검증 추가
    const selectedProvince = document.querySelector('input[name="province"]:checked');
    const selectedCity = document.querySelector('input[name="city"]:checked');
    
    if (!selectedProvince) {
        alert('도/특별시/광역시를 선택해주세요');
        return false;
    }
    
    if (!selectedCity) {
        alert('시/군/구를 선택해주세요');
        return false;
    }
    
    // 숨겨진 필드에 값 설정
    form.city.value = selectedCity.value;
    
    if (form.placeName.value.length == 0) {
        alert('상호명을 입력해주세요');
        form.placeName.focus();
        return false;
    }
    
    // 폼 제출 전에 가격 필드를 실제 숫자값으로 변경 (수정된 부분)
    const actualPrice = getActualPrice();
    
    // 숨겨진 price 필드가 있는 경우 그 필드에 값 설정
    const hiddenPriceField = form.querySelector('input[name="price"]');
    if (hiddenPriceField) {
        hiddenPriceField.value = actualPrice;
    } else {
        // 또는 기존 방식으로 설정
        form.price.value = actualPrice;
    }
    
    // 추가 검증: 가격이 숫자인지 확인
    if (isNaN(actualPrice) || actualPrice === '') {
        alert('올바른 가격을 입력해주세요');
        document.getElementById('priceInput').focus();
        return false;
    }
    
        // Java int 범위 검증 (최대값: 2,147,483,647)
    const maxIntValue = 2147483647;
    if (parseInt(actualPrice) > maxIntValue) {
        alert('가격이 너무 큽니다. 최대 21억원까지 입력 가능합니다.');
        document.getElementById('priceInput').focus();
        return false;
    }
    
    // 폼 제출 허용
    form.onsubmit = null; // 무한 루프 방지
    form.submit();
}

// 도별 시/군/구 데이터
const cityData = {
    '경기도': [
        '수원시', '성남시', '용인시', '안양시', '안산시', '과천시', '광명시', '광주시', 
        '군포시', '김포시', '남양주시', '동두천시', '부천시', '시흥시', '안성시', 
        '양주시', '오산시', '의왕시', '의정부시', '이천시', '파주시', '평택시', 
        '포천시', '하남시', '화성시', '가평군', '양평군', '여주시', '연천군', '고양시'
    ],
    '경상북도': [
        '경주시', '경산시', '구미시', '김천시', '문경시', '상주시', '안동시', 
        '영주시', '영천시', '포항시', '고령군', '군위군', '봉화군', '성주군', 
        '영덕군', '영양군', '예천군', '울릉군', '울진군', '의성군', '청도군', 
        '청송군', '칠곡군'
    ],
    '경상남도': [
        '창원시', '진주시', '통영시', '사천시', '김해시', '밀양시', '거제시', 
        '양산시', '의령군', '함안군', '창녕군', '고성군', '남해군', '하동군', 
        '산청군', '함양군', '거창군', '합천군'
    ],
    '충청북도': [
        '청주시', '충주시', '제천시', '보은군', '옥천군', '영동군', '증평군', 
        '진천군', '괴산군', '음성군', '단양군'
    ],
    '충청남도': [
        '천안시', '공주시', '보령시', '아산시', '서산시', '논산시', '계룡시', 
        '당진시', '금산군', '부여군', '서천군', '청양군', '홍성군', '예산군', 
        '태안군'
    ],
    '전라북도': [
        '전주시', '군산시', '익산시', '정읍시', '남원시', '김제시', '완주군', 
        '진안군', '무주군', '장수군', '임실군', '순창군', '고창군', '부안군'
    ],
    '전라남도': [
        '목포시', '여수시', '순천시', '나주시', '광양시', '담양군', '곡성군', 
        '구례군', '고흥군', '보성군', '화순군', '장흥군', '강진군', '해남군', 
        '영암군', '무안군', '함평군', '영광군', '장성군', '완도군', '진도군', 
        '신안군'
    ],
    '강원도': [
        '춘천시', '원주시', '강릉시', '동해시', '태백시', '속초시', '삼척시', 
        '홍천군', '횡성군', '영월군', '평창군', '정선군', '철원군', '화천군', 
        '양구군', '인제군', '고성군', '양양군'
    ],
    '제주도': [
        '제주시', '서귀포시'
    ],
    '서울특별시': [
        '강남구', '강동구', '강북구', '강서구', '관악구', '광진구', '구로구', 
        '금천구', '노원구', '도봉구', '동대문구', '동작구', '마포구', '서대문구', 
        '서초구', '성동구', '성북구', '송파구', '양천구', '영등포구', '용산구', 
        '은평구', '종로구', '중구', '중랑구'
    ],
    '부산광역시': [
        '중구', '서구', '동구', '영도구', '부산진구', '동래구', '남구', '북구', 
        '해운대구', '사하구', '금정구', '강서구', '연제구', '수영구', '사상구', 
        '기장군'
    ],
    '대구광역시': [
        '중구', '동구', '서구', '남구', '북구', '수성구', '달서구', '달성군'
    ],
    '인천광역시': [
        '중구', '동구', '미추홀구', '연수구', '남동구', '부평구', '계양구', 
        '서구', '강화군', '옹진군'
    ]
};

// 페이지 로드 후 초기화
document.addEventListener('DOMContentLoaded', function() {
    // 가격 입력 기능 초기화
    const priceInput = document.getElementById('priceInput');
    if (priceInput) {
        // 포커스 시: "0"이면 지우기
        priceInput.addEventListener('focus', function() {
            if (this.value === '0') {
                this.value = '';
            }
        });

        // 입력 시: 숫자만 허용하고 콤마 추가
        priceInput.addEventListener('input', function() {
            // 숫자만 추출
            let value = this.value.replace(/[^0-9]/g, '');

            // 빈 값이면 그대로 두기
            if (value === '') {
                this.value = '';
                return;
            }

            // 콤마 추가해서 표시
            this.value = addCommas(value);
        });

        // 포커스 해제 시: 빈 값이면 "0"으로 복원
        priceInput.addEventListener('blur', function() {
            if (this.value === '') {
                this.value = '0';
            }
        });
    }

    // 지역 선택 기능 초기화
    initLocationSelection();
});

function initLocationSelection() {
    // DOM 요소
    const provinceRadios = document.querySelectorAll('input[name="province"]');
    const citySection = document.getElementById('citySection');
    const selectedInfo = document.getElementById('selectedInfo');
    const selectedText = document.getElementById('selectedText');

    // 도 선택 이벤트
    provinceRadios.forEach(radio => {
        radio.addEventListener('change', function() {
            const selectedProvince = this.value;
            showCities(selectedProvince);
            updateSelectedInfo(selectedProvince, null);
        });
    });

    // 시/군/구 목록 표시
    function showCities(province) {
        const cities = cityData[province] || [];
        
        if (cities.length === 0) {
            citySection.innerHTML = '<div class="empty-state">해당 지역의 시/군/구 정보가 없습니다.</div>';
            citySection.classList.remove('active');
            return;
        }
        
        let html = '<div class="radio-list">';
        cities.forEach((city, index) => {
            html += `
                <div class="radio-item">
                    <input type="radio" name="city" value="${city}" id="city${index}" />
                    <label for="city${index}">${city}</label>
                </div>
            `;
        });
        html += '</div>';
        
        citySection.innerHTML = html;
        citySection.classList.add('active');
        
        // 새로 생성된 시/군/구 라디오 버튼에 이벤트 추가
        const cityRadios = citySection.querySelectorAll('input[name="city"]');
        cityRadios.forEach(radio => {
            radio.addEventListener('change', function() {
                const selectedProvince = document.querySelector('input[name="province"]:checked').value;
                const selectedCity = this.value;
                updateSelectedInfo(selectedProvince, selectedCity);
            });
        });
    }

    // 선택된 정보 업데이트
    function updateSelectedInfo(province, city) {
        if (city) {
            selectedText.textContent = `${province} ${city}`;
            selectedInfo.style.display = 'block';
        } else {
            selectedText.textContent = `${province} (시/군/구를 선택해주세요)`;
            selectedInfo.style.display = 'block';
        }
    }
}