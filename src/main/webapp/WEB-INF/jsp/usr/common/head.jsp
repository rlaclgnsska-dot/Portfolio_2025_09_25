<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>

<link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.14/dist/full.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
<!-- 테일윈드 치트시트 -->
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/resource/common.css" />
</head>
<body>
	<div class="h-20 flex container mx-auto text-4xl">
		<div><a class="h-full px-3 flex items-center" href="/"><span>로고</span></a></div>
		<div class="flex-grow"></div>
		<ul class="flex">
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/article/list?boardId=1"><span>공지</span></a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/article/list?boardId=2"><span>게시물</span></a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/trip/list"><span>여행</span></a></li>
			<c:if test="${rq.getLoginedMemberId() == 0 }">
				<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/login"><span>로그인</span></a></li>
			</c:if>
			<c:if test="${rq.getLoginedMemberId() != 0 }">
							<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/keepTrip/list"><span>찜목록</span></a></li>
				<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/doLogout"><span>LOGOUT</span></a></li>
			</c:if>
		</ul>
	</div>
