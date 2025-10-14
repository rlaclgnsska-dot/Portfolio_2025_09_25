<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE MODIFY" />
	
	<%@ include file="../common/head.jsp"%>
	
	<link rel="stylesheet" href="/resource/trip/list.css" />
	
	<script src="${pageContext.request.contextPath}/js/usr/trip/list.js"></script>

	<section class="mt-8 text-xl">
	    <div class="container mx-auto px-3">
	        
	        <!-- 태그 필터 섹션 -->
	        <div class="category-section">
	            <div class="category-label">
	                <span>종류별</span>
	            </div>
	            <div>
					<a href="?boardId=0" class="tag" data-category="종류별" data-value="0">전체</a>
					<a href="?boardId=1" class="tag" data-category="종류별" data-value="1">숙소</a>
					<a href="?boardId=2" class="tag" data-category="종류별" data-value="2">카페</a>
					<a href="?boardId=3" class="tag" data-category="종류별" data-value="3">음식점</a>
					<a href="?boardId=4" class="tag" data-category="종류별" data-value="4">가볼만한곳</a>
	            </div>
	        </div>
	        
	        <div class="flex container md-2 text-base">
	            <div class="flex items-center">
	                <span>총 : ${getTripsCnt}개</span>
	            </div>
	            <div class="flex-grow"></div>
	            <a class="btn btn-outline btn-sm" href="write">새 글 작성</a>
	        </div>
	        
	        <div class="table-box-type mt-3">
	            <table>
	                <thead>
	                    <tr>
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
	                    <c:forEach var="trip" items="${trips}">
	                        <tr>
	                            <td>${trip.id}</td>
	                            <td>${trip.boardName}</td>
	                            <td>${trip.provinceCity}</td>
	                            <td class="hover:underline">
	                                <a href="detail?id=${trip.id}">${trip.placeName}</a>
	                            </td>
	                            <td>${trip.price}</td>
	                            <td>${trip.nickname}</td>
	                            <td>${trip.regDate}</td>
	                        </tr>
	                    </c:forEach>
	                </tbody>
	            </table>
	        </div>
	
	        <!-- 기존 페이징 네비게이션 (그대로 유지) -->
	        <div class="pagination-wrapper flex justify-center mt-8">
	            <div class="pagination flex items-center space-x-2">
	                <!-- 이전 페이지 그룹으로 이동 -->
	                <c:if test="${startPage > 1}">
	                    <a href="?page=${startPage - 1}" class="btn btn-sm btn-outline">«</a>
	                </c:if>
	
	                <!-- 이전 페이지 -->
	                <c:if test="${page > 1}">
	                    <a href="?page=${page - 1}" class="btn btn-sm btn-outline">‹</a>
	                </c:if>
	
	                <!-- 페이지 번호들 -->
	                <c:forEach var="i" begin="${startPage}" end="${endPage}">
	                    <c:choose>
	                        <c:when test="${i == page}">
	                            <span class="btn btn-sm btn-primary">${i}</span>
	                        </c:when>
	                        <c:otherwise>
	                            <a href="?page=${i}" class="btn btn-sm btn-outline">${i}</a>
	                        </c:otherwise>
	                    </c:choose>
	                </c:forEach>
	
	                <!-- 다음 페이지 -->
	                <c:if test="${page < pagesCnt}">
	                    <a href="?page=${page + 1}" class="btn btn-sm btn-outline">›</a>
	                </c:if>
	
	                <!-- 다음 페이지 그룹으로 이동 -->
	                <c:if test="${endPage < pagesCnt}">
	                    <a href="?page=${endPage + 1}" class="btn btn-sm btn-outline">»</a>
	                </c:if>
	            </div>
	        </div>
	    </div>
	</section>

	<%@ include file="../common/foot.jsp"%>