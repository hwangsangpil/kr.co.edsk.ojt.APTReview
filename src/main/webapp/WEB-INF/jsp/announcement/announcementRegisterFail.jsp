<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>모집공고 등록 실패 페이지</title>
</head>
<body>
<%@ include file="../include/inc_header.jsp"%>
<%@ include file="../include/inc_top.jsp"%>
<%@ include file="../include/inc_left_menu.jsp"%>
<script type="text/javascript">
	
 	$(document).ready(function(){
 		var result = confirm("모집공고 게시글 등록이 실패하였습니다. 목록으로 돌아가겠습니까?");
		if(result == true){
	 		location.href = "announcementList.do";		
		}else{
			location.href = history.go(-1);
		}
		
 	});
</script>
</body>
</html>