<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="MAIN" />
	
	<%@ include file="../common/head.jsp" %>
	
	<section class="mt-8">
		<div class="container mx-auto">
			<div>
				Lorem ipsum dolor sit amet, consectetur adipisicing elit. Totam alias eligendi consequatur modi ut distinctio assumenda voluptates doloribus voluptas nulla quidem quia sequi nesciunt eos illo quas accusamus veritatis labore.
			</div>
		</div>
	</section>
	
	<div class="h-20 flex container mx-auto text-4xl">
		<div><a class="h-full px-3 flex items-center" href="/"><span>로고</span></a></div>
		<div class="flex-grow"></div>
		<ul class="flex">
			<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/ttest/type"><span>type</span></a></li>
		</ul>
	</div>
	
	<%@ include file="../common/foot.jsp" %>