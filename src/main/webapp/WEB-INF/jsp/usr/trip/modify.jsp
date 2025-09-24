<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE MODIFY" />
	
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
			
		}
		
	</script>
	
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<form action="doModify" method="post" onsubmit="modifyForm_onSubmit(this); return false;">
				<input name="id" type="hidden" value="${trip.id }" />
				<div class="table-box-type">
					<table>
						<tr>
							<th>번호</th>
							<td>${article.id }</td>
						</tr>
						<tr>
							<th>작성일</th>
							<td>${article.regDate }</td>
						</tr>
						<tr>
							<th>수정일</th>
							<td>${article.updateDate }</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${article.writerName }</td>
						</tr>
						<tr>
							<th>종류</th>
							<td>${trip.boardName }</td>
						</tr>	
						<tr>
							<th>도시</th>
							<td><input name="city" type="text" value="${trip.city }"
								placeholder="도시를 입력해주세요" /></td>
						</tr>
						<tr>
							<th>상호명</th>
							<td><input name="place_name" type="text" value="${trip.place_name }"
								placeholder="상호명을 입력해주세요" /></td>
						</tr>
						<tr>
							<th>가격</th>
							<td><input name="price" id="priceInput" type="text" value="${trip.price }" autocomplete="off" /></td>
						</tr>
						<tr>
							<th>비고</th>
							<td><input name="description" type="text" value="${trip.description }"
								placeholder="비고를 입력해주세요" /></td>
						</tr>
						<tr>
							<td colspan="2"><button>수정</button></td>
						</tr>
					</table>
				</div>
			</form>
			
			<div class="btus">
				<button onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>