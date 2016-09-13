<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/inc_header.jsp"%>
    <title>모집공고 등록 화면 페이지</title>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script type="text/javaScript">
	
    $(document).ready(function(){
    	var flag = 1;
    	
    	$("#writebutton").click(function(){
    		var anName = $("#announcementName").val();
    		var anStart = $("#announcementStart").val();
    		var anEnd = $("#announcementEnd").val();
    		var anAddress = $("#announcementAddress").val();
    		var anArea = $("#announcementArea").val();
    		var anAllResident = $("#announcementAllResident").val();
    		var anContents = $("#announcementContents").val();
    		var insertAnForm = document.insertAnnouncementForm;
    				
    		if(anName == null || anName == ""){
    			alert("공고명이 올바르지 않아 등록이 불가합니다.");
    			insertAnForm.announcementName.focus();
    			return false;
    		}
    		if(anName.length > 100){
    			alert("공고명이 너무 길어 등록리 불가합니다.");
    			insertAnForm.announcementName.focus();
    			return false;
    		}
    		
    		if(anStart == null || anStart == ""){
    			alert("공고일을 올바르지 않아 등록이 불가합니다.");
    			insertAnForm.announcementStart.focus();
    			return false;
    		}
    		if(anEnd == null || anEnd == ""){
    			alert("마감일이 올바르지 않아 등록이 불가합니다.");
    			insertAnForm.announcementEnd.focus();
    			return false;
    		}
    		if(anAddress == null || anAddress == ""){
    			alert("소재지가 올바르지 않아 등록이 불가합니다.");
    			insertAnForm.announcementAddress.focus(); 
    			return false;
    		}
    		if(anAddress.length > 100){
    			alert("소재지가 너무 길어 등록이 불가합니다.");
    			insertAnForm.announcementAddress.focus(); 
    			return false;
    		}
    		if(anArea == null || anArea == ""){
    			alert("전용면적이 올바르지 않아 등록이 불가합니다.");
    			insertAnForm.announcementArea.focus();
    			return false;
    		}
    		if(isNaN(anArea)){
    			alert("전용면적을 숫자로 입력하세요");
    			insertAnForm.announcementArea.focus();
    			return false;
    		}
    		if(anArea.length > 6){
    			alert("전용면적을 6자리수 이하로 입력하세요");
    			insertAnForm.announcementArea.focus(); 
    			return false;
    		}
    		if(anAllResident == null || anAllResident == ""){
    			alert("총세대수가 올바르지 않아 등록이 불가합니다.");
    			insertAnForm.announcementAllResident.focus();
    			return false;
    		}
    		if(isNaN(anAllResident)){
    			alert("총세대수를 숫자로 입력하세요");
    			insertAnForm.announcementAllResident.focus(); 
    			return false;
    		}
    		if(anAllResident.length > 6){
    			alert("총세대수를 6자리수 이하로 입력하세요");
    			insertAnForm.announcementAllResident.focus();
    			return false;
    		}
    		if(anContents == null || anContents == ""){
    			alert("내용이 올바르지 않아 등록이 불가합니다.");
    			insertAnForm.announcementContents.focus();
    			return false;
    		}
    		
    		var registerRun = confirm("모집공고 등록을 완료하겠습니까?");
    		if(registerRun == false){
    			return false;
    		}else{
    			$("#insertAnnouncementForm").submit();
    		}		
    	 	
    	});   
    	
   		$("#aptZoneCodeOption").change(function(){
   			alert($("#aptZoneCodeOption option:selected").text());
   		});
   		
   	 	$(".datepicker").datepicker({
   	 		dateFormat: 'yy-mm-dd'
   		});
 			 			
		$("#listbutton").click(function(){
			var listbtn = confirm("목록으로 돌아가겠습니까?");
			if(listbtn == true){
				location.href = "announcementList.do";
			}else{
				return false;
			}
		});
   
			
    });
    </script>
</head>
<body>
<div style="min-width: 300px">
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
					<div class="page-title">모집공고 등록</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<!--END TITLE & BREADCRUMB PAGE-->
			
			<!--BEGIN CONTENT-->
			<div class="page-content">
               <form action="insertAnnouncement.do" method="post" name="insertAnnouncementForm" id="insertAnnouncementForm" enctype="post">
				<input type="hidden" name="selectedAnnouncementNo" />
				<div id="tab-general">
					<div class="row mbl">
						<!-- 등록폼 -->
						<div class="col-lg-8">
							<div class="panel panel-green">
                                <div class="panel-heading">모집공고 등록화면</div>
                                <div class="panel-body pan" style="overflow: auto;">
							
								<table class="table table-hover">
									<tr style="cursor: pointer;">
										<td style="text-align:center; width: 200px;">	
											<input type="hidden" name="memberNo" value="${announcementVO.memberNo }"></input>
							   				<label>지역코드 : </label>
										</td>
										<td style="text-align:center; width: 200px;">
							 				<!-- <select id="aptZoneCodeOption" name="aptZoneCode">
							 					<option selected="selected" value="Z001">전체</option>
							 					<option value="Z002">강원</option>
							 					<option value="Z003">경기</option>
							 					<option value="Z004">경북</option>
							 					<option value="Z005">경남</option>
							 					<option value="Z006">광주</option>
							 					<option value="Z007">대구</option>
							 					<option value="Z008">대전</option>
							 					<option value="Z009">부산</option>
							 					<option value="Z010">서울</option>
							 					<option value="Z011">세종</option>
							 					<option value="Z012">울산</option>
							 					<option value="Z013">인천</option>
							 					<option value="Z014">전남</option>
							 					<option value="Z015">전북</option>
							 					<option value="Z016">충남</option>
							 					<option value="Z017">충북</option>
							 				</select>      	 -->
							 				<select id="aptZoneCodeOption" name="aptZoneCode" style="width:850px;height:30px; font-size: 13px;">
							 					<c:forEach items="${zoneCodeVO}" var="list">
													<option value="${list.aptZoneCode}">${list.aptZoneCodeValue}</option>
												</c:forEach>
											</select>
 										</td>			
									</tr>

									<tr style="cursor: pointer;">
										<td style="text-align:center; width: 200px;">
	   										<label>공고명 : </label>
										</td>
										<td style="text-align:center; width: 200px;">
								    		<input size="50" type="text" class="form-control" name="announcementName" id="announcementName" placeholder="공고명을 입력하세요." required="required">
										</td>
									</tr>

									<tr style="cursor: pointer;">
										<td style="text-align:center; width: 200px;">
						   					<label>공고일 : </label>
										</td>
										<td>
								    		<input size="100" type="text" class="datepicker" name="announcementStart" id="announcementStart" placeholder="공고일을 입력하세요." required="required">
										</td>
									</tr>

									<tr style="cursor: pointer;">
										<td style="text-align:center; width: 200px;">
						      				<label>마감일 : </label>	
										</td>
										<td>
							    	  		<input size="100" type="text" class="datepicker" name="announcementEnd" id="announcementEnd" placeholder="마감일 입력하세요." required="required">  
										</td>
									</tr>

									<tr style="cursor: pointer;">
										<td style="text-align:center; width: 200px;">
								      		<label>소재지 : </label>
										</td>
										<td>			
						     				<input size="50" type="text" class="form-control" name="announcementAddress" id="announcementAddress" placeholder="소재지를 입력하세요." required="required">
										</td>
									</tr>

									<tr style="cursor: pointer;">
										<td style="text-align:center; width: 200px;">
							      			<label>전용면적 : </label>
										</td>
										<td>
								     		<input size="50" type="text" class="form-control" name="announcementArea" id="announcementArea" placeholder="전용면적을 숫자로 입력하세요." required="required">
										</td>
									</tr>

									<tr style="cursor: pointer;">
										<td style="text-align:center; width: 200px;">
								      		<label>총세대수 : </label>
										</td>
										<td>			
						     				<input size="50" type="text" class="form-control" name="announcementAllResident" id="announcementAllResident" placeholder="총세대수를 숫자로 입력하세요." required="required">
										</td>
									</tr>

									<tr style="cursor: pointer;">
										<td style="text-align:center; width: 200px;">
							      			<label>내용 : </label>
										</td>
										<td>
							     			<textarea style="resize:none" cols="42" rows="10" class="form-control" name="announcementContents" id="announcementContents" placeholder="내용을 입력하세요." required="required"></textarea>
										</td>
									</tr>

									<tr>
										<td colspan="2">
										    <button type="button" class="btn btn-primary" id="writebutton">글쓰기</button>
											<!-- <button type="reset" class="btn btn-primary">취소</button> -->
											<button type="button" class="btn btn-primary" id="listbutton">목록으로</button>
										</td>
									</tr>
								</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
			</div>
		</div>
		</div>
	</div>							
</body>
</html>