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

function clickAptZoneCode(){
	$('#checkAptZoneCode').text("지역은 수정이 불가능합니다.");
	alert('test');
}

function clickAptBlockCode(){
	$('#checkAptBlockCode').text("단지는 수정이 불가능합니다.");
}

/* 수정 */
function updateAptReview() {
	
	var aptReviewTitle = $('#aptReviewTitle').val();
	var aptReviewContent = $('#aptReviewContent').val();
	
	if(aptReviewContent.length > 10){
		$('#checkAptReviewContent').text("10자 이하로 입력 부탁드립니다.");
	}else{
		$('#checkAptReviewContent').text("");
	}
	if(aptReviewTitle ==' ' || aptReviewTitle == '' || aptReviewContent.length > 10){
 		
		if(aptReviewTitle ==' ' || aptReviewTitle == ''){
			$('#checkAptReviewTitle').text("제목은 필수입력 항목 입니다.");
		}else{
			$('#checkAptReviewTitle').text("");
		}
		if(aptReviewContent.length > 10){
			$('#checkAptReviewContent').text("10자 이하로 입력 부탁드립니다.");
		}else{
			$('#checkAptReviewContent').text("");
		}
		
	}else{
		document.updateForm.method = "post";
		document.updateForm.action = "/updateAptReview.do";
		document.updateForm.submit();
}

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
	                                    	<input type="hidden" id="aptReviewNo" name="aptReviewNo" value="${updateAptReviewForm.aptReviewNo}"/>
	                                    	<input type="hidden" id="pageIndex" name="pageIndex" value="${defaultVO.pageIndex}">
	                                    	<div class="form-body pal" id="test">
	                                    			<div class="form-group">
														<div class="input-icon right">
															지역
															<i class="fa fa-pencil"></i>
															 <input id="aptZoneCode"
																name="aptZoneCode" type="text" placeholder="${updateAptReviewForm.aptZoneCode}" onclick="clickAptZoneCode()"
																class="form-control" tabindex="1" onKeypress="hitEnterKey(event)" readonly  />
																<div id=checkAptZoneCode></div>
														</div>
													</div>
													<div class="form-group">
														<div class="input-icon right">
															단지명
															<i class="fa fa-pencil"></i>
															 <input id="aptBlockCode"
																name="aptBlockCode" type="text" placeholder="${updateAptReviewForm.aptBlockCode}" 
																class="form-control" tabindex="1" onKeypress="hitEnterKey(event)" readonly />
																<div id=checkAptBlockCode></div>
														</div>
													</div>
													<div class="form-group">
														<div class="input-icon right">
															제목
															<i class="fa fa-pencil"></i>
															 <input id="aptReviewTitle"
																name="aptReviewTitle" type="text" placeholder="${updateAptReviewForm.aptReviewTitle}"
																class="form-control" tabindex="1" onKeypress="hitEnterKey(event)" />
																<div id=checkAptReviewTitle></div>
														</div>
													</div>
													<div class="form-group">
														<div class="input-icon right">
															내용
															<i class="fa fa-balance-scale"></i> <input id="aptReviewContent"
															name="aptReviewContent" type="text" placeholder="${updateAptReviewForm.aptReviewContent}"
															class="form-control" tabindex="2" onKeypress="hitEnterKey(event)"/>
															<div id=checkAptReviewContent></div>
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

