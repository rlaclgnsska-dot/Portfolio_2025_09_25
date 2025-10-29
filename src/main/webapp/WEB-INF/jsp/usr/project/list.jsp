<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="프로젝트" />
	
	<%@ include file="../common/head.jsp" %>
	
	<section class="mt-8">
		<div class="container mx-auto px-3">
			
			<div class="mb-4 text-2xl font-bold">
				<h1>프로젝트 포트폴리오</h1>
			</div>
			
			<div class="mb-4 text-base">
				<div><span>총 : ${getProjectsCnt }개</span></div>
			</div>
			
			<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
				<c:forEach var="project" items="${projects }">
					<div class="card bg-base-100 shadow-xl">
						<div class="card-body">
							<h2 class="card-title">${project.title }</h2>
							<p class="text-sm text-gray-500">작성자: ${project.writerName }</p>
							<p class="text-sm text-gray-500">작성일: ${project.regDate.substring(0, 10) }</p>
							
							<c:if test="${not empty project.techStack }">
								<div class="mt-2">
									<span class="text-xs font-semibold">기술 스택:</span>
									<p class="text-sm">${project.techStack }</p>
								</div>
							</c:if>
							
							<c:if test="${not empty project.status }">
								<div class="mt-2">
									<span class="badge ${project.status == 'completed' ? 'badge-success' : (project.status == 'ongoing' ? 'badge-warning' : 'badge-info')}">
										${project.status == 'completed' ? '완료' : (project.status == 'ongoing' ? '진행중' : '계획중')}
									</span>
								</div>
							</c:if>
							
							<div class="card-actions justify-end mt-4">
								<a href="detail?id=${project.id }" class="btn btn-primary btn-sm">자세히 보기</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<c:if test="${rq.getLoginedMemberId() != 0 }">
				<div class="mt-4 flex justify-end">
					<a href="write" class="btn btn-primary">프로젝트 등록</a>
				</div>
			</c:if>
			
			<div>
				<div class="paging_group">
					<div class="join mt-8 flex justify-center">
						<c:if test="${startPage > 1}">
							<a class="join-item btn btn-sm" href="?page=${page - 1}">«</a>
						</c:if>
						<c:if test="${startPage <= 1}">
							<a class="join-item btn btn-sm btn-disabled">«</a>
						</c:if>
					
						<c:forEach begin="${startPage }" end="${endPage }" var="i">
							<a class="join-item btn btn-sm ${page == i ? 'btn-active' : ''}" href="?page=${i }">${i }</a>
						</c:forEach>
					
						<c:if test="${endPage < pagesCnt}">
							<a class="join-item btn btn-sm" href="?page=${page + 1}">»</a>
						</c:if>
						<c:if test="${endPage >= pagesCnt}">
							<a class="join-item btn btn-sm btn-disabled">»</a>
						</c:if>
					</div>
				</div>
			</div>
			
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>
