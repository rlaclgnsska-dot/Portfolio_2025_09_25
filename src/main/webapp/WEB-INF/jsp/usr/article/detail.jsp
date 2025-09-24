<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE DITAIL" />
	
	<%@ include file="../common/head.jsp" %>
	
	<script>
	
	
	
		$(document).ready(function() {
			getRecommendPoint();
			
			$('#recommendBtn').click(function(){
				
				let recommendBtn = $('#recommendBtn').hasClass('btn-active');
				
				$.ajax({
					url: "../recommendPoint/doRecommendPoint",
					method: "get",
					data: {
							"relTypeCode" : "article",
							"relId" : ${article.id },
							"recommendBtn" : recommendBtn
						},
					dataType: "text",
					success: function(data) {
						console.log(data);
					},
					error: function(xhr, status, error) {
						console.error("ERROR : " + status + " - " + error);
					}
				})
			
				location.reload();
			})
			
		})
		
		const getRecommendPoint = function(){
			$.ajax({
				url: "../recommendPoint/getRecommendPoint",
				method: "get",
				data: {
						"relTypeCode" : "article",
						"relId" : ${article.id}
						},
				dataType: "json",
				success: function(data) {
					if (data.success) {
						$('#recommendBtn').addClass('btn-active');
					}
				},
				error: function(xhr, status, error) {
					console.error("ERROR : " + status + " - " + error);
				}
			})
		}

	</script>	
	
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
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
						<th>조회수</th>
						<td>${article.hitCount }</td>
					</tr>
					<tr>
						<th>좋아요</th>
						<td>
							<c:if test="${rq.getLoginedMemberId() == 0 }">
								<span>${article.point }</span>
							</c:if>
							<c:if test="${rq.getLoginedMemberId() != 0 }">
								<button id="recommendBtn" class="mt-8 btn btn-outline btn-sm"">좋아요👍</button>
								<span>${article.point }</span>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${article.title }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${article.body }</td>
					</tr>
				</table>
			</div>
			
			<div class="btus">
			
				<button id="recommendBut" class="btn btn-outline btn-sm" onclick="history.back();">뒤로가기</button>
				
				<c:if test="${loginedMemberId != 0 && loginedMemberId == article.memberId }">
					<a class="btn btn-outline btn-sm" href="modify?id=${article.id }">수정</a>
					<a class="btn btn-outline btn-sm" href="doDelete?id=${article.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
				</c:if>
			</div>
		</div>
	</section>
	
	<section class="mt-5 text-base">
		<div class="container mx-auto px-3">
			<h2>댓글</h2>
			
			<from action="../reply/doWrite" method="post" onsubmit="">
				<input name="relTypeCode" type="hidden" value="article" />
				<input name="relId" type="hidden" value="${article.id }" />
				<div>
					<div>닉네임</div>
					<textarea class="textarea textarea-bordered textarea-primary w-9/12" name="body" placeholder="댓글을 입력해주세요"></textarea>
					<button class="btn btn-outline btn-sm">작성</button>
					<a class="btn btn-outline btn-sm" href="replyModify?id=${article.id }">수정</a>
					<a class="btn btn-outline btn-sm" href="replyDoDelete?id=${article.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
				</div>
			</from>
		</div>
	</section>
	
	<%@ include file="../common/foot.jsp" %>