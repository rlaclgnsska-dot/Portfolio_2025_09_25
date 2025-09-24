<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE DITAIL" />
	
	<%@ include file="../common/head.jsp" %>
	
	<script>
	

	</script>	
	
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<div class="table-box-type">
				<table>
					<tr>
						<th>번호</th>
						<td>${trip.id }</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${trip.regDate }</td>
					</tr>
					<tr>
						<th>수정일</th>
						<td>${trip.updateDate }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${trip.nickname }</td>
					</tr>
					<tr>
						<th>종류</th>
						<td>${trip.boardName }</td>
					</tr>
					<tr>
						<th>도시</th>
						<td>${trip.provinceCity }</td>
					</tr>
					<tr>
						<th>상호명</th>
						<td>${trip.placeName }</td>
					</tr>
					<tr>
						<th>가격</th>
						<td>${trip.price }</td>
					</tr>
					<tr>
						<th>비고</th>
						<td>${trip.description }</td>
					</tr>
				</table>
			</div>
			
			<div class="btus">
				<button class="btn btn-outline btn-sm" onclick="history.back();">뒤로가기</button>
				
				<c:if test="${loginedMemberId != 0 && loginedMemberId == trip.memberId }">
					<a class="btn btn-outline btn-sm" href="doDelete?id=${trip.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;" >삭제</a>
					<a class="btn btn-outline btn-sm" href="modify?id=${trip.id }" >수정</a>
				</c:if>	
			</div>
			
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>