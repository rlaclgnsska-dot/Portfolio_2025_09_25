<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="${board.name }" />
	
	<%@ include file="../common/head.jsp" %>
	
	<script>
	
		const listForm_onSubmit = function(form) {
			from.searchKeyword.value = from.searchKeyword.value.trim();
			
			if (from.searchKeyword.value.length == 0) {
				alert('검색어를을 입력해주세요');
				form.searchKeyword.focus();
				return;
			}
			
		}
		
	</script>
	
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			
			<div class="md-2 text-base">
				<div><span>총 : ${getArticlesCnt }개</span></div>
			</div>
			
			<div class="table-box-type">
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>작성일</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>추천수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles }">
							<tr>
								<td>${article.id }</td>
								<td>${article.regDate }</td>
								<td class="hover:underline"><a href="detail?id=${article.id }">${article.title }</a></td>
								<td>${article.writerName }</td>
								<td>${article.hitCount }</td>
								<td>${article.point }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<c:if test="${rq.getLoginedMemberId() != 0 }">
				<div class="mt-2 flex justify-end">
					<a href="write">글쓰기</a>
				</div>
			</c:if>
			
			<div>
				<div class="paging_group">
					<div class="join mt-4 flex justify-center">
						<c:if test="${startPage > 1}">
							<a class="join-item btn btn-sm" href="?boardId=${board.id }&page=${page - 1}">«</a>
						</c:if>
						<c:if test="${startPage <= 1}">
							<a class="join-item btn btn-sm btn-disabled">«</a>
						</c:if>
					
						<c:forEach begin="${startPage }" end="${endPage }" var="i">
							<a class="join-item btn btn-sm ${page == i ? 'btn-active' : ''}" href="?boardId=${board.id }&page=${i }&searchType=${searchType }&searchKeyword=${searchKeyword }">${i }</a>
						</c:forEach>
					
						<c:if test="${endPage < pagesCnt}">
							<a class="join-item btn btn-sm" href="?boardId=${board.id }&page=${page + 1}">»</a>
						</c:if>
						<c:if test="${endPage >= pagesCnt}">
							<a class="join-item btn btn-sm btn-disabled">»</a>
						</c:if>
					</div>
				</div>
			</div>
			
			<form action="list" method="get" onsubmit="listForm_onSubmit(this); return false;">
				<div class="search_bar">
					<div class="join mt-4 flex justify-center gap-2">
					
						<select class="select select-bordered" name="searchType" >
						    <option value="title" selected>제목</option>
						    <option value="body">내용</option>
						    <option value="title_body">제목 + 내용</option>
						</select>
						
						<input class="input input-bordered" name="searchKeyword" value="${searchKeyword }"type="text" placeholder="검색어를 입력해주세요" />
						
						<button class="btn btn-outline" type="submit" >검색</button>
						
					</div>
				</div>
			</form>
			
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>