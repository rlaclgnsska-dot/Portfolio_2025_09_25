<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="프로젝트 수정" />
	
	<%@ include file="../common/head.jsp" %>
	
	<script>
		const modifyForm_onSubmit = function(form) {
			form.title.value = form.title.value.trim();
			form.body.value = form.body.value.trim();
			
			if (form.title.value.length == 0) {
				alert('제목을 입력해주세요');
				form.title.focus();
				return;
			}
			
			if (form.body.value.length == 0) {
				alert('내용을 입력해주세요');
				form.body.focus();
				return;
			}
			
			form.submit();
		}
		
	</script>
	
	<section class="mt-8">
		<div class="container mx-auto px-3">
			<h1 class="text-2xl font-bold mb-4">프로젝트 수정</h1>
			
			<form action="doModify" method="post" onsubmit="modifyForm_onSubmit(this); return false;">
				<input name="id" type="hidden" value="${project.id }" />
				<div class="bg-base-100 shadow-xl rounded-lg p-6">
					<div class="form-control mb-4">
						<label class="label">
							<span class="label-text">번호</span>
						</label>
						<input type="text" value="${project.id }" class="input input-bordered w-full" disabled />
					</div>
					
					<div class="form-control mb-4">
						<label class="label">
							<span class="label-text">작성일</span>
						</label>
						<input type="text" value="${project.regDate }" class="input input-bordered w-full" disabled />
					</div>
					
					<div class="form-control mb-4">
						<label class="label">
							<span class="label-text">수정일</span>
						</label>
						<input type="text" value="${project.updateDate }" class="input input-bordered w-full" disabled />
					</div>
					
					<div class="form-control mb-4">
						<label class="label">
							<span class="label-text">작성자</span>
						</label>
						<input type="text" value="${project.writerName }" class="input input-bordered w-full" disabled />
					</div>
					
					<div class="form-control mb-4">
						<label class="label">
							<span class="label-text">제목 *</span>
						</label>
						<input name="title" type="text" value="${project.title }" placeholder="프로젝트 제목을 입력해주세요" class="input input-bordered w-full" />
					</div>
					
					<div class="form-control mb-4">
						<label class="label">
							<span class="label-text">프로젝트 설명 *</span>
						</label>
						<textarea name="body" class="textarea textarea-bordered h-32" placeholder="프로젝트에 대한 설명을 입력해주세요">${project.body }</textarea>
					</div>
					
					<div class="form-control mb-4">
						<label class="label">
							<span class="label-text">기술 스택</span>
						</label>
						<input name="techStack" type="text" value="${project.techStack }" placeholder="예: Spring Boot, React, MySQL" class="input input-bordered w-full" />
					</div>
					
					<div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
						<div class="form-control">
							<label class="label">
								<span class="label-text">GitHub URL</span>
							</label>
							<input name="githubUrl" type="text" value="${project.githubUrl }" placeholder="https://github.com/..." class="input input-bordered w-full" />
						</div>
						
						<div class="form-control">
							<label class="label">
								<span class="label-text">Demo URL</span>
							</label>
							<input name="demoUrl" type="text" value="${project.demoUrl }" placeholder="https://..." class="input input-bordered w-full" />
						</div>
					</div>
					
					<div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
						<div class="form-control">
							<label class="label">
								<span class="label-text">시작일</span>
							</label>
							<input name="startDate" type="date" value="${project.startDate }" class="input input-bordered w-full" />
						</div>
						
						<div class="form-control">
							<label class="label">
								<span class="label-text">종료일</span>
							</label>
							<input name="endDate" type="date" value="${project.endDate }" class="input input-bordered w-full" />
						</div>
					</div>
					
					<div class="form-control mb-4">
						<label class="label">
							<span class="label-text">프로젝트 상태</span>
						</label>
						<select name="status" class="select select-bordered w-full">
							<option value="completed" ${project.status == 'completed' ? 'selected' : ''}>완료</option>
							<option value="ongoing" ${project.status == 'ongoing' ? 'selected' : ''}>진행중</option>
							<option value="planned" ${project.status == 'planned' ? 'selected' : ''}>계획중</option>
						</select>
					</div>
					
					<div class="flex gap-2 mt-6">
						<button type="button" class="btn btn-outline" onclick="history.back();">취소</button>
						<button type="submit" class="btn btn-primary">수정</button>
					</div>
				</div>
			</form>
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>
