<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE WRITE" />

<%@ include file="../common/head.jsp"%>

<link rel="stylesheet" href="/resource/trip/write.css" />
<script src="${pageContext.request.contextPath}/js/usr/trip/write.js"></script>

<section class="mt-8 text-xl">
	<form id="locationForm" action="doWrite" method="post"
		enctype="multipart/form-data"
		onsubmit="writeForm_onSubmit(this); return false;">
		<div class="container">
			<input name="id" type="hidden" value="" />
			<!-- 숨겨진 필드 추가 -->
			<input name="city" type="hidden" value="" />

			<table>
				<tr>
					<th>게시판</th>
					<td>
						<div style="display: flex; gap: 20px;">
							<label style="display: flex; align-items: center;"> <input
								name="boardId" type="radio" value="1" checked="checked" />
								&nbsp;숙소
							</label> <label style="display: flex; align-items: center;"> <input
								name="boardId" type="radio" value="2" /> &nbsp;음식점
							</label> <label style="display: flex; align-items: center;"> <input
								name="boardId" type="radio" value="3" /> &nbsp;가볼만한곳
							</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>지역 선택</th>
					<td>
						<div class="location-form">
							<!-- 도/특별시/광역시 선택 -->
							<div class="form-group">
								<label class="form-label">도/특별시/광역시</label>
								<div class="radio-list">
									<div class="radio-item">
										<input type="radio" name="province" value="경기도" id="gyeonggi" />
										<label for="gyeonggi">경기도</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="경상북도"
											id="gyeongsangbuk" /> <label for="gyeongsangbuk">경상북도</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="경상남도"
											id="gyeongsangnam" /> <label for="gyeongsangnam">경상남도</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="충청북도"
											id="chungcheongbuk" /> <label for="chungcheongbuk">충청북도</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="충청남도"
											id="chungcheongnam" /> <label for="chungcheongnam">충청남도</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="전라북도"
											id="jeollabuk" /> <label for="jeollabuk">전라북도</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="전라남도"
											id="jeollanam" /> <label for="jeollanam">전라남도</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="강원도" id="gangwon" />
										<label for="gangwon">강원도</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="제주도" id="jeju" />
										<label for="jeju">제주도</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="서울특별시" id="seoul" />
										<label for="seoul">서울특별시</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="부산광역시" id="busan" />
										<label for="busan">부산광역시</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="대구광역시" id="daegu" />
										<label for="daegu">대구광역시</label>
									</div>
									<div class="radio-item">
										<input type="radio" name="province" value="인천광역시" id="incheon" />
										<label for="incheon">인천광역시</label>
									</div>
								</div>
							</div>

							<!-- 시/군/구 선택 -->
							<div class="form-group">
								<label class="form-label">시/군/구</label>
								<div class="city-section" id="citySection">
									<div class="empty-state">👈 먼저 도/특별시/광역시를 선택해주세요</div>
								</div>
							</div>
						</div> <!-- 선택된 정보 표시 -->
						<div id="selectedInfo" class="selected-info"
							style="display: none;">
							<h3>선택된 지역</h3>
							<p id="selectedText"></p>
						</div>
					</td>
				</tr>

				<tr>
					<th>상호명</th>
					<td><input name="placeName" type="text"
						placeholder="상호명을 입력해주세요" /></td>
				</tr>
				<tr>
					<th>가격</th>
					<td><input name="price" id="priceInput" type="text" value="0"
						autocomplete="off" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<div id="editor" class="body" contenteditable="true"></div>
						<input type="hidden" name="body" id="bodyInput">
					</td>
				</tr>
			</table>

			<div class="flex" style="margin-top: 20px;">
				<button type="button" onclick="history.back();">뒤로가기</button>
				<div class="flex-grow"></div>
				<button type="submit">작성</button>
			</div>
		</div>
	</form>
</section>

<%@ include file="../common/foot.jsp"%>