<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>후기게시판 수정</title>
<%@ include file="../include/inc_header.jsp"%>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<style>
/* #test{background-image: "/image/test.jpg";} */
</style>

<script>
/* 제목 입력창에 기본 포커스 */
$(document).ready(function(){
	$('#aptReviewTitle').focus();
});

if('${updateResult}'==-1){
	alert("수정 오류가 발생했습니다.");
	history.back();
}

function clickAptZoneCode(){
	$('#checkAptZoneCode').text("지역은 수정이 불가능합니다.");
}

function clickAptBlockCode(){
	$('#checkAptBlockCode').text("단지는 수정이 불가능합니다.");
}

/* 수정 */
function updateAptReview() {
	
	var aptReviewTitle = $('#aptReviewTitle').val().trim();
	var aptReviewContent = $('#aptReviewContent').val().trim();
	/* 특수문자 제한 */
	re = /[~!@\#$%^&*\()\-=+_'<>]/gi;
	/* 유효성 결과 */
	var result = 0;
	
	/* 유효성 검사결과 텍스트 초기화 */
	/* 제목 */
	if($('#checkAptReviewTitle').text().length > 0){
		$('#checkAptReviewTitle').text("");
	}
	/* 내용 */
	if($('#checkAptReviewContent').text().length > 0){
		$('#checkAptReviewContent').text("");
	}
	
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
	
	/* 유효성 검사결과 판단 후 동작실행 */
	if(result == 1){
		return false;
	}else{
		document.updateForm.method = "post";
		document.updateForm.action = "/updateAptReview.do";
		document.updateForm.submit();
	}

}/* updateAptReview() End */

/* 이전 상세보기 리스트 이동 */
function selectAptReviewView(){
	document.updateForm.method = "post";
	document.updateForm.action = "/selectAptReviewView.do";
	document.updateForm.submit();
}

/* Enter 입력시 수정실행 */
function hitEnterKey(e){
	  if(e.keyCode == 13){
		  updateAptReview();
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
						<div class="page-title">아파트 후기 수정</div>
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
	                                <div class="panel-heading">아파트 후기 수정</div>
	                                <div class="panel-body pan">
	                                    <form id="updateForm" name="updateForm" method="post" enctype="post">
	                                    	<input type="hidden" id="aptReviewNo" name="aptReviewNo" value="${updateAptReviewForm.APT_REVIEW_NO}"/>
	                                    	<input type="hidden" id="pageIndex" name="pageIndex" value="${defaultVO.pageIndex}">
	                                    	<div class="form-body pal" id="test">
	                                    			<div class="form-group">
														<div class="input-icon right">
															지역
															<i class="fa fa-pencil"></i>
															 <input id="aptZoneCode"
																name="aptZoneCode" type="text" placeholder="${updateAptReviewForm.APT_ZONE_CODE_VALUE}" onclick="clickAptZoneCode()"
																class="form-control" tabindex="1" onKeypress="hitEnterKey(event)" readonly  />
																<div class="checkDiv" id="checkAptZoneCode"></div>
														</div>
													</div>
													<div class="form-group">
														<div class="input-icon right">
															단지명
															<i class="fa fa-pencil"></i>
															 <input id="aptBlockCode"
																name="aptBlockCode" type="text" placeholder="${updateAptReviewForm.APT_BLOCK_CODE_VALUE}" onclick="clickAptBlockCode()"
																class="form-control" tabindex="1" onKeypress="hitEnterKey(event)" readonly />
																<div class="checkDiv" id="checkAptBlockCode"></div>
														</div>
													</div>
													<div class="form-group">
														<div class="input-icon right">
															제목
															<i class="fa fa-pencil"></i>
															 <input id="aptReviewTitle"
																name="aptReviewTitle" type="text" placeholder="${updateAptReviewForm.APT_REVIEW_TITLE}"
																class="form-control" tabindex="1" onKeypress="hitEnterKey(event)" />
																<div class="checkDiv" id="checkAptReviewTitle"></div>
														</div>
													</div>
													<div class="form-group">
														<div class="input-icon right">
															내용
															<i class="fa fa-balance-scale"></i> <input id="aptReviewContent"
															name="aptReviewContent" type="text" placeholder="${updateAptReviewForm.APT_REVIEW_CONTENTS}"
															class="form-control" tabindex="2" onKeypress="hitEnterKey(event)"/>
															<div class="checkDiv" id="checkAptReviewContent"></div>
														</div>
													</div>
													
											</div>
												<div class="form-actions text-right pal">
												<button type="button" onclick="updateAptReview();" class="btn btn-primary">수정</button>
												<button type="button" onclick="selectAptReviewView();" class="btn btn-primary">취소</button>
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

