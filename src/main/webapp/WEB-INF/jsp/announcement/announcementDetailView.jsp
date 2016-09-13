<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>모집공고 상세보기</title>
	<%@ include file="../include/inc_header.jsp"%>
		<script type="text/javaScript">
	 	$(document).ready(function(){
			 $("#listbutton").click(function(){
					var listbtn = confirm("목록으로 돌아가겠습니까?");
					if(listbtn == true){
						location.href = "announcementList.do";
					}else{
						return false;
					} 
			 });
			 
			 $("#deletebutton").click(function(){
				var deletebtn = confirm("해당 모집공고를 삭제하겠습니까?");
				var selectedAnnouncementNo = document.detailForm.selectedAnnouncementNo.value;
				if(deletebtn == true){
					location.href = "deleteAnnouncement.do?announcementNo=${announcementDetailVO.announcementNo}";
				}else{
					return false;
				}
			 });
			 
			 $("#updatebutton").click(function(){
				var updatebtn = confirm("해당 모집공고를 수정하겠습니까?");
				var selectedAnnouncementNo = document.detailForm.selectedAnnouncementNo.value;
				if(updatebtn == true){
					location.href = "updateAnnouncementForm.do?announcementNo=${announcementDetailVO.announcementNo}";
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
						<div class="page-title">모집공고 게시판</div>
					</div>
					<div class="clearfix"></div>
				</div>
			<!--END TITLE & BREADCRUMB PAGE-->
				
			<!--BEGIN CONTENT-->
				<div class="page-content">
    				<form:form commandName="defaultVO" id="detailForm" name="detailForm" method="post">
					<input type="hidden" name="selectedAnnouncementNo" />			
					<div id="tab-general">
						<div class="row mbl">
							<div class="col-lg-12">
								<div class="row">
									<div class="col-lg-12">
										<div class="panel panel-yellow">
											<div class="panel-heading">상세보기</div>
											<div class="mbl"></div>
											<div class="col-lg-8">&nbsp;</div>							
												<div class="col-lg-12">&nbsp;</div>
												<div class="col-lg-12">&nbsp;</div>
												<div class="panel-body" style="overflow: auto;">
													<table class="table table-hover">
															<tr style="cursor: pointer;">
										        				<td style="text-align:center; width: 50px;">No</td>
									            				<td style="text-align:center; width: 200px;" id="announcementNo"><c:out value="${announcementDetailVO.announcementNo}"/></td>
										        			</tr>
										        			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">지역코드</td>
									            				<td style="text-align:center;" id="aptZoneCode"><c:out value="${announcementDetailVO.aptZoneCode}"/></td>
									            			</tr>
									            			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">회원번호</td>
									            				<td style="text-align:center;" id="memberNo"><c:out value="${announcementDetailVO.memberNo}"/></td>
									            			</tr>
									            			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">공고명</td>
									            				<td style="text-align:center;" id="announcementName"><c:out value="${announcementDetailVO.announcementName}"/></td>
									            			</tr>
									            			
									            			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">공고일</td>
									            				<td style="text-align:center;" id="announcementStart">
									            					<fmt:parseDate var="dateFmt" value='${announcementDetailVO.announcementStart}' pattern='yyyy-MM-dd'/>
       																<fmt:formatDate value="${dateFmt }" pattern="yyyy년 MM월 dd일"/>
									            				</td>
									            				<%-- <fmt:formatDate value="${announcementDetailVO.announcementStart}" pattern="yyyy-MM-dd"/> --%>
									            			</tr>
									            			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">마감일</td>
										        				<td style="text-align:center;" id="announcementEnd">
									            					<fmt:parseDate var="dateFmt" value='${announcementDetailVO.announcementEnd}' pattern='yyyy-MM-dd'/>
       																<fmt:formatDate value="${dateFmt }" pattern="yyyy년 MM월 dd일"/>
									            				</td>
									            			</tr>
									            			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">소재지</td>
									            				<td style="text-align:center;" id="announcementAddress"><c:out value="${announcementDetailVO.announcementAddress}"/></td>
										        			</tr>
										        			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">전용면적</td>
									            				<td style="text-align:center;" id="announcementArea"><c:out value="${announcementDetailVO.announcementArea}"/></td>
										        			</tr>
										        			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">총세대수</td>
									            				<td style="text-align:center;" id="announcementAllResident"><c:out value="${announcementDetailVO.announcementAllResident}"/></td>
										        			</tr>
										        			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">조회수</td>
									            				<td style="text-align:center;" id="announcementClicks"><c:out value="${announcementDetailVO.announcementClicks}"/></td>
										        			</tr>
										        			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">작성일</td>
									            				<td style="text-align:center;" id="announcementCreateDate"><c:out value="${announcementDetailVO.announcementCreateDate}"/></td>
										        			</tr>
										        			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">수정일</td>
									            				<td style="text-align:center;"><c:out value="${announcementDetailVO.announcementUpdateDate}"/></td>
										        			</tr>
										        			<tr style="cursor: pointer;">	
										        				<td style="text-align:center;">내용</td>
									            				<td style="text-align:center;"><c:out value="${announcementDetailVO.announcementContents}"/></td>
															</tr>

															<tr>
																<td colspan="10">
																	<!-- <button type="reset" class="btn btn-primary">취소</button> -->
															    	<!-- <button type="button" class="btn btn-primary" id="writebutton">글쓰기</button> -->
																	<button type="button" class="btn btn-primary" id="updatebutton">수정하기</button>
																	<button type="button" class="btn btn-primary" id="deletebutton">삭제하기</button>
																	<button type="button" class="btn btn-primary" id="listbutton">목록으로</button>
																
																</td>
															</tr>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form:form>
					</div>
				</div>
			</div>
		</div>	
	</body>	
</html>