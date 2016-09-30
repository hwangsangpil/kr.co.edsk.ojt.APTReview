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


$(document).ready(function(){
	/* 지역 선택창에 기본 포커스 */
	$('#aptZoneCode').focus();
	
	/* 지역, 단지명, 제목, 내용 의 값을 변수에 저장 */
	var aptReviewTitle = $('#aptReviewTitle').val().trim();
	var aptReviewContent = $('#aptReviewContent').val().trim();
	/* 특수문자 제한 */
	re = /[~!@\#$%^&*\()\-=+_'<>]/gi;
	
	var select = "<option>:: 지역선택 ::</option>"; 
	
	$("#aptZoneCode").change(function() {			
		
		if($("#aptZoneCode").val() == "-1") { // select의 value가 ""이면, "선택" 메뉴만 보여줌.
			$("#aptBlockCode").find("option").remove().end().append('<option value="' + -1 + '">' + '단지선택' + '</option>');
			$('#checkAptZoneCode').text("지역을 선택해주세요");
			$('#aptZoneCode').focus();
		} else {
			if($('#checkAptZoneCode').text().length > 0){
				$('#checkAptZoneCode').text("");
			}
			var aptZoneCodeValue = $(this).val();
			ajaxSelectBox(aptZoneCodeValue);
		}
	});
	
	$("#aptBlockCode").change(function() {			
		if($("#aptBlockCode").val() == "-1") { // select의 value가 ""이면, "선택" 메뉴만 보여줌.
			$('#checkAptBlockCode').text("단지를 선택해주세요");
			$('#aptBlockCode').focus();
		} else {
			if($('#checkAptBlockCode').text().length > 0){
				$('#checkAptBlockCode').text("");
			}
			
			var aptBlockCode = $(this).val();
			aptPlanImage(aptBlockCode);
			 
		}
	});
	
	
	$("#aptReviewTitle").change(function() {	
		var aptReviewTitle = $('#aptReviewTitle').val().trim();
		/* 특수문자 제한 */
		re = /[~!@\#$%^&*\()\-=+_'<>]/gi;
		
		if(aptReviewTitle == ''){
			$('#checkAptReviewTitle').text("제목은 필수입력 항목 입니다.");
			$('#aptReviewTitle').focus();
		}else if(re.test(aptReviewTitle)){
			$('#checkAptReviewTitle').text("특수문자는 입력불가 항목 입니다.");
			$('#aptReviewTitle').focus();
		}else {
			if($('#checkAptReviewTitle').text().length > 0){
				$('#checkAptReviewTitle').text("");
			}
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

function aptPlanImage(aptBlockCode){
	var aptBlockCode = {"aptBlockCode" : aptBlockCode};
		
		$.ajax({
            type: 'POST', // POST형식으로 폼 전송
            async: true,
            dataType: 'json',
            data: aptBlockCode,
            url: '/aptPlanImage.do', // 목적지
            success: function(data) {
            	
				if(!data) {
					alert('데이터 실패');
					return;
				} else {
					$("#aptPlanImage").find("div").remove().end().append('<div class="' + 'aptPlanCodeValue div-sort' + '"><div class="' + 
							'div-sort fileButton' + '"id="'+'aptPlanDiv1'+'"><label for="' + 'aptPlanFile1' + '">업로드</label><input type="' + 
							'file' + '"id="' + 'aptPlanFile1' + '"class="' + 'aptPlanFile1' + '"name="'+'file1'+'"></div><div class="' + 
							'div-sort fileButton' + '"id="'+'aptPlanDiv2'+'"><label for="' + 'aptPlanFile2' + '">업로드</label><input type="' + 
							'file' + '"id="' + 'aptPlanFile2' + '"class="' + 'aptPlanFile2' + '"name="'+'file2'+'"></div><div class="' + 
							'div-sort fileButton' + '"id="'+'aptPlanDiv3'+'"><label for="' + 'aptPlanFile3' + '">업로드</label><input type="' + 
							'file' + '"id="' + 'aptPlanFile3' + '"class="' + 'aptPlanFile3' + '"name="'+'file3'+'"></div></div>');
				}
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
            },
            complete : function(data) {
            	/* 
            	alert("실패 후");
            	alert("complete: "+resultData); 
            	 */
            },
            error: function(xhr, status, error, data){
               	console.log(xhr);
               	console.log("status="+status);
               	console.log("error="+error);
            	alert("에러발생");
            	alert("error: "+error+" errorData: "+data);
			}
     }); 
}

/* ajax */
function ajaxSelectBox(aptZoneCodeValue){
	var aptZoneCode = {"aptZoneCode" : aptZoneCodeValue};
		
		$.ajax({
            type: 'POST', // POST형식으로 폼 전송
            async: true,
            dataType: 'json',
            data: aptZoneCode,
            url: '/ajaxTest.do', // 목적지
            success: function(data) {
            	
				if(!data) {
					$("#aptBlockCode").find("option").remove().end().append('<option value="' + -1 + '">' + '단지선택' + '</option>');
					return;
				} else {
					$("#aptBlockCode").find("option").remove().end().append('<option value="' + -1 + '">' + '단지선택' + '</option>');
					$.each(data, function(index, value) {
						$('#aptBlockCode').append("<option value='" + index + "'>" + value + " </option>");
					});
				}
            },
            complete : function(data) {
            	/*
            	alert("실패 후");
            	alert("complete: "+resultData); 
            	*/
            },
            error: function(xhr, status, error, data){
               	console.log(xhr);
               	console.log("status="+status);
               	console.log("error="+error);
            	alert("에러발생");
            	alert("error: "+error+" errorData: "+data);
			}
     }); 
}

/* 단지선택창 클릭시 지역이 선택되어 있어야함 */
function checkAjaxSelectBox(){
	var aptZoneCode = $('#aptZoneCode').val();
	
	if(aptZoneCode == -1){
		$('#checkAptBlockCode').text("지역을 먼저 선택해주세요");
		$('#aptZoneCode').focus();
		return false;
	}
}

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
		document.aptReviewRegister.encoding = "multipart/form-data"
		document.aptReviewRegister.method = "post";
		document.aptReviewRegister.action = "/insertAptReview.do";
		document.aptReviewRegister.submit();
	}

}/* insertAptReview() End */


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
	                                    		<div class="form-body pal">
	                                    		
	                                    			<!-- 지역 -->
	                                    			<div>
	                                    				<div class="aptReviewRegister-item-name">지역</div>
														<div class="aptReviewRegister-item-input">
															<select name="aptZoneCode" id="aptZoneCode" class="form-control" tabindex="1" onKeypress="hitEnterKey(event)">
																<option value="-1">지역선택</option>
																<c:forEach items="${zoneCodeVO}" var="list">
																<option value="${list.aptZoneCode}">${list.aptZoneCodeValue}</option>
																</c:forEach>
															</select>
														</div>
														<div class="checkDiv" id="checkAptZoneCode"></div>
													</div>
													
													<!-- 단지 -->
													<div>
														<div class="aptReviewRegister-item-name">단지명</div>
														<div class="aptReviewRegister-item-input">
															<select name="aptBlockCode" id="aptBlockCode" onclick="checkAjaxSelectBox()" id="aptBlockCode" class="form-control" tabindex="2" onKeypress="hitEnterKey(event)">
																<option value="-1">단지선택</option>
															</select>
														</div>
														<div class="checkDiv" id="checkAptBlockCode"></div>
													</div>
													
													<!-- 제목 -->
													<div>
														<div class="aptReviewRegister-item-name">제목</div>
														<div class="input-icon right aptReviewRegister-item-input">
															<i class="fa fa-pencil"></i>
															<input id="aptReviewTitle" name="aptReviewTitle" type="text" placeholder="제목"
																class="form-control" tabindex="3" onKeypress="hitEnterKey(event)" />
														</div>
														<div class="checkDiv" id="checkAptReviewTitle"></div>
													</div>
													
													<!-- 도면도 -->
													<div>
														<div class="aptReviewRegister-item-name-same">도면도</div>
														<div class="aptReviewRegister-item-name-same">내용</div>

														<div class="filebox bs3-primary aptReviewRegister-item-name-same-left" id="aptPlanImage">
															<!-- 
															<div class="aptPlanCodeValue div-sort">
																<div class="div-sort fileButton">
																	<label for="aptPlanFile1">업로드</label>
																	<input type="file" id="aptPlanFile1" class="aptPlanFile">
																</div>
																<div class="div-sort fileButton">
																	<label for="aptPlanFile2">업로드</label>
																	<input type="file" id="aptPlanFile2" class="aptPlanFile">
																</div>
																<div class="div-sort fileButton">
																	<label for="aptPlanFile3">업로드</label>
																	<input type="file" id="aptPlanFile3" class="aptPlanFile">
																</div>
															</div>
															 -->
														</div>
								                        
														<div class="aptReviewRegister-item-name-same-right">
															<textarea id="aptReviewContent" name="aptReviewContent" tabindex="4"></textarea>
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
													<button type="button" onclick="insertAptReview()" class="btn btn-primary" tabindex="5">등록</button>
													<button type="button" onclick="selectAptReviewList()" class="btn btn-primary" tabindex="6">취소</button>
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