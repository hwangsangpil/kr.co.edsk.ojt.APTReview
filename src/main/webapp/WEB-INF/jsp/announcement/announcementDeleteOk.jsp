<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>모집공고 삭제 성공 페이지</title>
</head>
<body>
<%@ include file="../include/inc_header.jsp"%>
<%@ include file="../include/inc_top.jsp"%>
<%@ include file="../include/inc_left_menu.jsp"%>
</body>
<script type="text/javascript">
	
 	$(document).ready(function(){
		alert("${deleteCount}"+"개의 글이 삭제 되었습니다");
 		location.href="announcementList.do";
 	});
</script>
</html>
	