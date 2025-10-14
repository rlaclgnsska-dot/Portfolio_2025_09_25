// 페이지 로드 시 URL에 맞는 태그 활성화
function initializeActiveTag() {
    const urlParams = new URLSearchParams(window.location.search);
    const boardId = urlParams.get('boardId') || '0';
    
    // 모든 태그에서 active 제거
    document.querySelectorAll('.tag').forEach(t => {
        t.classList.remove('selected-all', 'active');
    });
    
    // 현재 boardId에 해당하는 태그 찾기
    const activeTag = document.querySelector(`.tag[data-value="${boardId}"]`);
    
    if (activeTag) {
        if (boardId === '0') {
            activeTag.classList.add('selected-all');
        } else {
            activeTag.classList.add('active');
        }
    }
}

// 페이지 로드 시 실행
document.addEventListener('DOMContentLoaded', function() {
    initializeActiveTag();
});

// 태그 클릭 시 활성화 상태만 변경 (서버 필터링은 안함)
document.querySelectorAll('.tag').forEach(tag => {
    tag.addEventListener('click', function() {
        // 같은 카테고리의 다른 태그들에서 active 제거
        const category = this.dataset.category;
        document.querySelectorAll(`[data-category="${category}"]`).forEach(t => {
            t.classList.remove('selected-all', 'active');
        });

        // 클릭된 태그에 active 추가
        if (this.dataset.value === '0') {
            this.classList.add('selected-all');
        } else {
            this.classList.add('active');
        }
    });
});