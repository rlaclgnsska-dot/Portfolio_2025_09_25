<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE MODIFY" />
	
	<%@ include file="../common/head.jsp"%>
	
	<script>
	
		function sendSelectedTrips() {
		    var checkedValues = [];
		    var checkboxes = document.getElementsByName('tripCheckList');
		    
		    for (var i = 0; i < checkboxes.length; i++) {
		        if (checkboxes[i].checked) {
		            checkedValues.push(checkboxes[i].value);
		        }
		    }
		    
		    if (checkedValues.length === 0) {
		        alert('선택된 여행이 없습니다.');
		    } else {
		        alert('선택된 여행: ' + checkedValues.join(', '));
		    }
		    
		    document.querySelector('form[action="doKeepTripInsert"]').submit();
		}
		
	</script>

	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<form action="doKeepTripInsert" method="post"
				onsubmit="listForm_onSubmit(this); return false;">
				<div class="flex container md-2 text-base">
					<div class="flex items-center">
						<span>총 : ${getTripsCnt }개</span>
					</div>
					<div class="flex-grow"></div>
					<a class="btn btn-outline btn-sm" href="write">새 글 작성</a>
					<button type="button" class="btn btn-outline btn-sm" onclick="sendSelectedTrips()">찜</button>
				</div>
				<div class="table-box-type mt-3">
					<table>
						<thead>
							<tr>
								<th>선택</th>
								<th>번호</th>
								<th>종류</th>
								<th>도시</th>
								<th>상호명</th>
								<th>가격</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="trip" items="${trips }">
								<tr>
									<td>
										<input type="checkbox" name="tripCheckList" value="${trip.id }" />
									</td>
									<td>${trip.id }</td>
									<td>${trip.boardName }</td>
									<td>${trip.provinceCity }</td>
									<td class="hover:underline"><a href="detail?id=${trip.id }">
											${trip.placeName } </a></td>
									<td>${trip.price }</td>
									<td>${trip.nickname }</td>
									<td>${trip.regDate }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
	
				<!-- 페이징 네비게이션 시작 -->
				<div class="pagination-wrapper flex justify-center mt-8">
					<div class="pagination flex items-center space-x-2">
	
						<!-- 이전 페이지 그룹으로 이동 -->
						<c:if test="${startPage > 1}">
							<a href="?page=${startPage - 1}" class="btn btn-sm btn-outline">
								« </a>
						</c:if>
	
						<!-- 이전 페이지 -->
						<c:if test="${page > 1}">
							<a href="?page=${page - 1}" class="btn btn-sm btn-outline"> ‹
							</a>
						</c:if>
	
						<!-- 페이지 번호들 -->
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:choose>
								<c:when test="${i == page}">
									<!-- 현재 페이지 -->
									<span class="btn btn-sm btn-primary">${i}</span>
								</c:when>
								<c:otherwise>
									<!-- 다른 페이지 -->
									<a href="?page=${i}" class="btn btn-sm btn-outline">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
	
						<!-- 다음 페이지 -->
						<c:if test="${page < pagesCnt}">
							<a href="?page=${page + 1}" class="btn btn-sm btn-outline"> ›
							</a>
						</c:if>
	
						<!-- 다음 페이지 그룹으로 이동 -->
						<c:if test="${endPage < pagesCnt}">
							<a href="?page=${endPage + 1}" class="btn btn-sm btn-outline">
								» </a>
						</c:if>
					</div>
				</div>
			</form>
		</div>
	</section>

	<%@ include file="../common/foot.jsp"%>