<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>후기게시판 등록</title>
<%@ include file="../include/inc_header.jsp"%>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<style>
/* #test{background-image: "/image/test.jpg";} */
</style>

<script>
/* 지역 선택창에 기본 포커스 */
$(document).ready(function(){
	$('#aptZoneCode').focus();
});
/* 입력결과 확인 */
if('${insertResult}'==-1){
	alert("입력 오류가 발생했습니다.");
	history.back();
}

function ajaxSelectBox(){
	var aptZoneCode = $('#aptZoneCode').val();
	var test = {aptZoneCode : aptZoneCode};
	
	if(aptZoneCode == -1){
		$('#checkAptBlockCode').text("지역을 먼저 선택해주세요");
		$('#aptZoneCode').focus();
		return false;
	}else {
		if($('#checkAptBlockCode').text().length > 0){
		$('#checkAptBlockCode').text("");
		}
		
		$.ajax({
            type: 'POST', // POST형식으로 폼 전송
            url: '/ajaxTest.do', // 목적지
            data: test,
            dataType: 'json',
            async: false,
            success: function(resultData) {
            	alert("성공");
            	alert("success: "+resultData);
            },
            complete : function(resultData) {
            	alert("실패 후");
            	alert("complete: "+resultData);
            },
            error: function(xhr, status, error, resultData){
               	console.log(xhr);
               	console.log("status="+status);
               	console.log("error="+error);
            	alert("에러발생");
            	alert("error: "+error+" errorData: "+resultData);
            }
        }); 
	}
}
/* 
function test2(resultData){
	alert("성공");
} */



/* 등록 */
function insertAptReview(){
	
	/* 지역, 단지명, 제목, 내용 의 값을 변수에 저장 */
	var aptZoneCode = $('#aptZoneCode').val();
	var aptBlockCode = $('#aptBlockCode').val();
	var aptReviewTitle = $('#aptReviewTitle').val().trim();
	var aptReviewContent = $('#aptReviewContent').val().trim();
	/* 특수문자 제한 */
	re = /[~!@\#$%^&*\()\-=+_'<>]/gi;
	/* 유효성 결과 */
	var result = 0;
	
	
	/* 유효성 검사결과 텍스트 초기화 */
	/* 지역 */
	if($('#checkAptZoneCode').text().length > 0){
		$('#checkAptZoneCode').text("");
	}
	/* 단지명 */
	if($('#checkAptBlockCode').text().length > 0){
		$('#checkAptBlockCode').text("");
	}
	/* 제목 */
	if($('#checkAptReviewTitle').text().length > 0){
		$('#checkAptReviewTitle').text("");
	}
	/* 내용 */
	if($('#checkAptReviewContent').text().length > 0){
		$('#checkAptReviewContent').text("");
	}
	
	
	/* 입력데이터 벨리데이션 체크 */
	/* 상단에있는 항목기준으로 포커스를 지정해 줘야되기 때문에 절차의 역순으로 조건문 작성 */
	
	/* 내용에 입력된 값이 없을 시 */
	if(aptReviewContent.length > 10){
		$('#checkAptReviewContent').text("10자 이하로 입력 부탁드립니다.");
		$('#aptReviewContent').focus();
		result = 1;
	}else if(re.test(aptReviewContent)){
		$('#checkAptReviewContent').text("특수문자는 입력불가 항목 입니다.");	
		$('#aptReviewContent').focus();
		result = 1;
	}
	/* 제목에 입력된 값이 없을 시 */
	if(aptReviewTitle == ''){
		$('#checkAptReviewTitle').text("제목은 필수입력 항목 입니다.");
		$('#aptReviewTitle').focus();
		result = 1;
	}else if(re.test(aptReviewTitle)){
		$('#checkAptReviewTitle').text("특수문자는 입력불가 항목 입니다.");
		$('#aptReviewTitle').focus();
		result = 1;
	}
	/* 단지명이 선택되지 않았을 시 */
	if(aptBlockCode == -1){
		$('#checkAptBlockCode').text("단지를 선택해주세요");
		$('#aptBlockCode').focus();
		result = 1;
	}
	/* 지역이 선택되지 않았을 시 */
	if(aptZoneCode == -1){
		$('#checkAptZoneCode').text("지역을 선택해주세요");
		$('#aptZoneCode').focus();
		result = 1;
	}
	
	/* 유효성 검사결과 판단 후 동작실행 */
	if(result == 1){
		return false;
	}else{
		document.aptReviewRegister.method = "post";
		document.aptReviewRegister.action = "/insertAptReview.do";
		document.aptReviewRegister.submit();
	}

/* insertAptReview() End */
}


/* 취소 */
function selectAptReviewList() {
	document.aptReviewRegister.method = "post";
	document.aptReviewRegister.action = "/selectAptReviewList.do";
	document.aptReviewRegister.submit();
}


/* 엔터키 입력시 insertAptReview() 로 이동 */
function hitEnterKey(e){
	if(e.keyCode == 13){
		insertAptReview();
	}else{
		e.keyCode == 0;
	 	return;
	}
}
</script>
</head>
<body>

	<div>
		<!--BEGIN TOPBAR-->
		<%@ include file="../include/inc_top.jsp"%>
		<!--END TOPBAR-->
		<div id="wrapper">
			<!--BEGIN SIDEBAR MENU-->
					<%@ include file="../include/inc_left_menu.jsp"%>
			<!--END SIDEBAR MENU-->
			<div id="page-wrapper">
				<!--BEGIN TITLE & BREADCRUMB PAGE-->
				<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
					<div class="page-header pull-left">
						<div class="page-title">아파트 후기 등록</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<!--END TITLE & BREADCRUMB PAGE-->
				<!--BEGIN CONTENT-->
				<div class="page-content">
					<div id="tab-general">
						<div class="row mbl">
							<!-- 등록폼 -->
							<div class="col-lg-8">
								<div class="panel panel-green">
	                                <div class="panel-heading">아파트 후기 등록</div>
	                                <div class="panel-body pan">
	                                    <form id="aptReviewRegister" name="aptReviewRegister">
	                                    <input type="hidden" id="pageIndex" name="pageIndex" value="${defaultVO.pageIndex}">
	                                    	<div class="form-body pal" id="test">
	                                    			<div class="form-group">
														<div class="input-icon right">
															지역
															<i class="fa fa-pencil"></i>
															<select name="aptZoneCode" id="aptZoneCode" class="form-control" tabindex="1" onKeypress="hitEnterKey(event)">
																<option value="-1">지역선택</option>
																<c:forEach items="${zoneCodeVO}" var="list">
																<option value="${list.aptZoneCode}">${list.aptZoneCodeValue}</option>
																</c:forEach>
															</select>
															<div class="checkDiv" id="checkAptZoneCode"></div>
														</div>
													</div>
													<div class="form-group">
														<div class="input-icon right">
															단지명
															<i class="fa fa-pencil"></i>
															<select name="aptBlockCode" onclick="ajaxSelectBox()" id="aptBlockCode" class="form-control" tabindex="2" onKeypress="hitEnterKey(event)">
																<option value="-1">단지선택</option>
															</select>
															<div class="checkDiv" id="checkAptBlockCode"></div>
														</div>
													</div>
													<div class="form-group">
														<div class="input-icon right">
															제목
															<i class="fa fa-pencil"></i><input id="aptReviewTitle"
																name="aptReviewTitle" type="text" placeholder="제목"
																class="form-control" tabindex="3" onKeypress="hitEnterKey(event)" />
																<div class="checkDiv" id="checkAptReviewTitle"></div>
														</div>
													</div>
													<div class="form-group">
														<div class="input-icon right">
															내용
															<i class="fa fa-balance-scale"></i> <input id="aptReviewContent"
																name="aptReviewContent" type="text" placeholder="내용"
																class="form-control" tabindex="4" onKeypress="hitEnterKey(event)"/>
																<div class="checkDiv" id="checkAptReviewContent"></div>
														</div>
													</div>
													
											</div>
												<div class="form-actions text-right pal">
												<button type="button" onclick="insertAptReview()" class="btn btn-primary" tabindex="5">등록</button>
												<button type="button" onclick="selectAptReviewList()" class="btn btn-primary" tabindex="6">취소</button>
												</div>
												
												</form>
									</div>
									
	                            </div>	
	                        </div>
							<!-- 여기까지 등록 폼 -->	
						</div>
					</div>
				</div>
				
				<!--END CONTENT-->
				<!--BEGIN FOOTER-->
				<!--END FOOTER-->
			</div>
			<!--END PAGE WRAPPER-->
		</div>
	</div>
</body>

</html>

