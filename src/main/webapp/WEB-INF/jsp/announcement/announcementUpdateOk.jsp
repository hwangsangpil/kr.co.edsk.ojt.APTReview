<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>모집공고 수정 성공 페이지</title>
</head>
<body>
<%@ include file="../include/inc_header.jsp"%>
<%@ include file="../include/inc_top.jsp"%>
<%@ include file="../include/inc_left_menu.jsp"%>
<script type="text/javascript">
	
 	$(document).ready(function(){
		alert("글이 수정 되었습니다");
 		location.href="selectDetailAnnouncementView.do?selectedAnnouncementNo=${selectedAnnouncementNo}";
 	});
</script>
</body>
</html>