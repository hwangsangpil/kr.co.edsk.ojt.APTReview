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

/* 파일 필드 숨기기 */

.filebox input[type="file"] {  
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip:rect(0,0,0,0);
  border: 0;
}


</style>

<script>

/* 입력결과 확인 */
if('${insertResult}'==-1){
	alert("입력 오류가 발생했습니다.");
	history.back();
}

function clickAptZoneCode(){
	$('#checkAptZoneCode').text("지역은 수정이 불가능합니다.");
}

function clickAptBlockCode(){
	$('#checkAptBlockCode').text("단지는 수정이 불가능합니다.");
}

function clickAptReviewTitle(){
	$('#checkAptReviewTitle').text("제목은 수정이 불가능합니다.");
}

$(document).ready(function(){
	/* 내용에 기본 포커스 */
	$('#aptReviewContent').focus();
	
	/* 내용 의 값을 변수에 저장 */
	var aptReviewContent = $('#aptReviewContent').val().trim();
	/* 특수문자 제한 */
	re = /[~!@\#$%^&*\()\-=+_'<>]/gi;
	
	/* 파일선택시 버튼 색상 변경 */
	$('.aptPlanFile1').change(function(){
		if(window.FileReader){
			$('.filebox.bs3-primary #aptPlanDiv1 label').css('background-color', 'red');
		}
	});
	/* 파일선택시 버튼 색상 변경 */
	$('.aptPlanFile2').change(function(){
		if(window.FileReader){
			$('.filebox.bs3-primary #aptPlanDiv2 label').css('background-color', 'red');
		}
	});
	/* 파일선택시 버튼 색상 변경 */
	$('.aptPlanFile3').change(function(){
		if(window.FileReader){
			$('.filebox.bs3-primary #aptPlanDiv3 label').css('background-color', 'red');
		}
	});
	
	$("#aptReviewContent").change(function() {	
		var aptReviewContent = $('#aptReviewContent').val().trim();
		/* 특수문자 제한 */
		re = /[~!@\#$%^&*\()\-=+_'<>]/gi;
		
		if(aptReviewContent.length > 10){
			$('#checkAptReviewContent').text("10자 이하로 입력 부탁드립니다.");
			$('#aptReviewContent').focus();
		}else if(re.test(aptReviewContent)){
			$('#checkAptReviewContent').text("특수문자는 입력불가 항목 입니다.");	
			$('#aptReviewContent').focus();
		}else {
			if($('#checkAptReviewContent').text().length > 0){
				$('#checkAptReviewContent').text("");
			}
		}
	});
	
});

/* 등록 */
function insertAptReviewReply(){
	
	/* 지역, 단지명, 제목, 내용 의 값을 변수에 저장 */
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
	
	/* 유효성 검사결과 판단 후 동작실행 */
	if(result == 1){
		return false;
	}else{
		document.aptReviewReplyRegister.encoding = "multipart/form-data"
		document.aptReviewReplyRegister.method = "post";
		document.aptReviewReplyRegister.action = "/insertAptReviewReply.do";
		document.aptReviewReplyRegister.submit();
	}

}/* insertAptReviewReply() End */


/* 취소 */
function selectAptReviewView(aptReviewNo){
	document.getElementById('aptReviewNo').value = aptReviewNo
	document.aptReviewReplyRegister.method = "post";
   	document.aptReviewReplyRegister.action = "/selectAptReviewView.do";
   	document.aptReviewReplyRegister.submit();
}


/* 엔터키 입력시 insertAptReviewReply() 로 이동 */
function hitEnterKey(e){
	if(e.keyCode == 13){
		insertAptReviewReply();
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
	                                    	<form id="aptReviewReplyRegister" name="aptReviewReplyRegister">
	                                    		<input type="hidden" id="pageIndex" name="pageIndex" value="${defaultVO.pageIndex}">
	                                    		<input type="hidden" id="aptReviewNo" name="aptReviewNo" value="0">
	                                    		<div class="form-body pal">
	                                    			<!-- 지역 -->
	                                    			<div>
	                                    				<div class="aptReviewRegister-item-name">지역</div>
														<div class="aptReviewRegister-item-input">
															<input type="text" class="form-control" onclick="clickAptZoneCode()"
															readonly placeholder="${aptZoneCodeList.APT_ZONE_CODE_VALUE}">
															<input type="hidden" id="aptZoneCode" name="aptZoneCode"
															value="${aptZoneCodeList.APT_ZONE_CODE}">
														</div>
														<div class="checkDiv" id="checkAptZoneCode"></div>
													</div>
													
													<!-- 단지 -->
													<div>
														<div class="aptReviewRegister-item-name">단지명</div>
														<div class="aptReviewRegister-item-input">
															<input type="text" class="form-control" onclick="clickAptBlockCode()"
															readonly placeholder="${aptBlockCodeList.APT_BLOCK_CODE_VALUE}">
															<input type="hidden" id="aptBlockCode" name="aptBlockCode"
															value="${aptBlockCodeList.APT_BLOCK_CODE}">
														</div>
														<div class="checkDiv" id="checkAptBlockCode"></div>
													</div>
													
													<!-- 제목 -->
													<div>
														<div class="aptReviewRegister-item-name">제목</div>
														<div class="aptReviewRegister-item-input">
															<input type="text" class="form-control" onclick="clickAptReviewTitle()"
															readonly placeholder="re:${AptReviewReplyList.APT_REVIEW_TITLE}">
															<input type="hidden" id="aptReviewTitle" name="aptReviewTitle"
															value="re:${AptReviewReplyList.APT_REVIEW_TITLE}">
														</div>
														<div class="checkDiv" id="checkAptReviewTitle"></div>
													</div>
													
													
													<div>
														<div class="aptReviewRegister-item-name-same">도면도</div>
														<div class="aptReviewRegister-item-name-same">내용</div>
														
														<!-- 도면도 -->
														<div class="filebox bs3-primary aptReviewRegister-item-name-same-left" id="aptPlanImage">
															<div class="aptPlanCodeValue div-sort">
																<div id="aptPlanDiv1" class="div-sort fileButton">
																	<label for="aptPlanFile1">업로드</label>
																	<input type="file" id="aptPlanFile1" name="file1" class="aptPlanFile1">
																</div>
																<div id="aptPlanDiv2" class="div-sort fileButton">
																	<label for="aptPlanFile2">업로드</label>
																	<input type="file" id="aptPlanFile2" name="file2" class="aptPlanFile2">
																</div>
																<div id="aptPlanDiv3" class="div-sort fileButton">
																	<label for="aptPlanFile3">업로드</label>
																	<input type="file" id="aptPlanFile3" name="file3" class="aptPlanFile3">
																</div>
															</div>
														</div>
								                        
								                        <!-- 내용 -->
														<div class="aptReviewRegister-item-name-same-right">
															<textarea id="aptReviewContent" name="aptReviewContent" tabindex="4">${AptReviewReplyList.APT_REVIEW_NO}</textarea>
															<!-- 
															textarea 의 높이와 크기를 정하는 기존방법
															rows="8" cols="40"
															-->
														</div>
														
														<div class="checkDiv checkDiv-left" id=""></div>
														<div class="checkDiv checkDiv-right" id="checkAptReviewContent"></div>
													</div>
													
													<!-- 여백주기 -->
													<div class="div-clear">&nbsp;</div>
													
													<!-- 버튼 -->
													<div class="form-actions text-right pal" style="background: white;">
													<button type="button" onclick="insertAptReviewReply()" class="btn btn-primary" tabindex="5">등록</button>
													<button type="button" onclick="selectAptReviewView(${AptReviewReplyList.APT_REVIEW_NO});" class="btn btn-primary" tabindex="6">취소</button>
													</div>
												</div>
											</form>
											<!-- 여기까지 등록 폼 -->	
										</div>
	                            	</div>	
	                        	</div>
							</div>
						</div>
					</div>
					<!--END CONTENT-->
				</div>
				<!--END PAGE WRAPPER-->
		</div>
</div>
</body>
</html>