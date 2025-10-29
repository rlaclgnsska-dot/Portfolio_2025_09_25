<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="프로젝트 상세" />
	
	<%@ include file="../common/head.jsp" %>
	
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<div class="bg-base-100 shadow-xl rounded-lg p-6">
				<h1 class="text-3xl font-bold mb-4">${project.title }</h1>
				
				<div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-6">
					<div>
						<p class="text-sm text-gray-600">작성자</p>
						<p class="text-lg">${project.writerName }</p>
					</div>
					<div>
						<p class="text-sm text-gray-600">작성일</p>
						<p class="text-lg">${project.regDate }</p>
					</div>
					<div>
						<p class="text-sm text-gray-600">수정일</p>
						<p class="text-lg">${project.updateDate }</p>
					</div>
					<div>
						<p class="text-sm text-gray-600">상태</p>
						<p class="text-lg">
							<span class="badge ${project.status == 'completed' ? 'badge-success' : (project.status == 'ongoing' ? 'badge-warning' : 'badge-info')}">
								${project.status == 'completed' ? '완료' : (project.status == 'ongoing' ? '진행중' : '계획중')}
							</span>
						</p>
					</div>
				</div>
				
				<c:if test="${not empty project.startDate or not empty project.endDate }">
					<div class="mb-6">
						<p class="text-sm text-gray-600">프로젝트 기간</p>
						<p class="text-lg">
							${project.startDate } 
							<c:if test="${not empty project.endDate }">
								~ ${project.endDate }
							</c:if>
						</p>
					</div>
				</c:if>
				
				<c:if test="${not empty project.techStack }">
					<div class="mb-6">
						<p class="text-sm text-gray-600">기술 스택</p>
						<p class="text-lg">${project.techStack }</p>
					</div>
				</c:if>
				
				<div class="mb-6">
					<p class="text-sm text-gray-600 mb-2">프로젝트 설명</p>
					<div class="prose max-w-none">
						<p>${project.body }</p>
					</div>
				</div>
				
				<c:if test="${not empty project.githubUrl or not empty project.demoUrl }">
					<div class="mb-6">
						<p class="text-sm text-gray-600 mb-2">관련 링크</p>
						<div class="flex gap-2">
							<c:if test="${not empty project.githubUrl }">
								<a href="${project.githubUrl }" target="_blank" class="btn btn-outline btn-sm">
									<i class="fa-brands fa-github"></i> GitHub
								</a>
							</c:if>
							<c:if test="${not empty project.demoUrl }">
								<a href="${project.demoUrl }" target="_blank" class="btn btn-outline btn-sm">
									<i class="fa-solid fa-link"></i> Demo
								</a>
							</c:if>
						</div>
					</div>
				</c:if>
				
				<div class="mt-8 flex gap-2">
					<button class="btn btn-outline btn-sm" onclick="history.back();">뒤로가기</button>
					
					<c:if test="${loginedMemberId != 0 && loginedMemberId == project.memberId }">
						<a class="btn btn-outline btn-sm" href="modify?id=${project.id }">수정</a>
						<a class="btn btn-outline btn-sm" href="doDelete?id=${project.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
					</c:if>
				</div>
			</div>
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>
