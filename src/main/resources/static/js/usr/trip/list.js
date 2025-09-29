// 태그 클릭 시 활성화 상태만 변경 (서버 필터링은 안함)
document.querySelectorAll('.tag').forEach(tag => {
    tag.addEventListener('click', function() {
        // 같은 카테고리의 다른 태그들에서 active 제거
        const category = this.dataset.category;
        document.querySelectorAll(`[data-category="${category}"]`).forEach(t => {
            t.classList.remove('selected-all', 'active');
        });
        
        // 클릭된 태그에 active 추가
        if (this.dataset.value === '전체') {
            this.classList.add('selected-all');
        } else {
            this.classList.add('active');
        }
    });
});