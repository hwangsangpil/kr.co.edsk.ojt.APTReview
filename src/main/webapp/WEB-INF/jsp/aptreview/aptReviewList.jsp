<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>후기게시판</title>
<%@ include file="../include/inc_header.jsp"%>
<script type="text/javascript">

/* 입력결과 확인 */
if('${insertResult}'>=1){
	alert("입력이 완료 되었습니다.");
}
	
/* 검색 */
function searchAptReview(){
var searchKeyword = document.getElementById("searchKeyword").value;
document.aptReviewList.submit();
}
	
/* 게시글 등록 */
function insertAptReview(){
document.aptReviewList.method = "post";
document.aptReviewList.action = "/insertAptReviewForm.do";
document.aptReviewList.submit();
}
	
/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
document.aptReviewList.pageIndex.value = pageNo;
document.aptReviewList.action = "<c:url value='/selectAptReviewList.do'/>";
document.aptReviewList.submit();
}
	
/* 상세페이지 이동 */
function selectAptReviewView(aptReviewNo){
	document.getElementById('aptReviewNo').value = aptReviewNo
	document.aptReviewList.method = "post";
   	document.aptReviewList.action = "/selectAptReviewView.do";
   	document.aptReviewList.submit();
}
	
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
        <div class="page-title">후기게시판</div>
       </div>
       <div class="clearfix"></div>
      </div>
       <!--END TITLE & BREADCRUMB PAGE-->
       <!--BEGIN CONTENT-->
       <div class="page-content">
        <!-- commandName == @ModelAttribute(값) -->
        <form:form commandName="defaultVO"  name="aptReviewList" method="post">
        <input type="hidden" id="aptReviewNo" name="aptReviewNo" value="0"/>
         <div id="tab-general">
          <div class="row mbl">
           <div class="col-lg-12">
            <div class="row">
             <div class="col-lg-12">
              <div class="panel panel-yellow">
               <div class="panel-heading">후기게시글 목록</div>
               <div class="mbl"></div>
               <div class="col-lg-8">&nbsp;</div>
               <!-- 
               <div class="col-lg-4">
                <div class="input-group">
                 <span class="input-group-addon">
                  <i class="fa fa-search"></i>
                 </span>
                 
                 <input type="text" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력하세요" value="" class="form-control"  tabindex="1"/>
                 <span class="input-group-btn">
                  <button type="button" class="btn btn-default" onclick="searchAptReview()" tabindex="3">검색</button>
				 </span>
				</div>
               </div> 
               -->
               <div class="col-lg-12">&nbsp;</div>
               <div class="col-lg-12">&nbsp;</div>
                <div class="panel-body" style="overflow: auto;">
                 <table class="table table-hover">
                  <thead>
                   <tr>
                    <th style="text-align:center; width: 50px;">NO</th>
                    <th style="text-align:center; width: 200px;">지역</th>
                    <th style="text-align:center; width: 150px;">단지</th>
                    <th style="text-align:center; width: 150px;">제목</th>
                    <th style="text-align:center; width: 150px;">작성자</th>
                    <th style="text-align:center; width: 150px;">작성일</th>
                   </tr>
                  </thead>
                  <tbody>
                   <c:forEach items="${selectAptReviewList}" var="dto">
                	<%-- <input type="hidden" id="aptReviewNo" name="aptReviewNo" value="${dto.aptReviewNo}"/> --%>
                    <tr  style="cursor: pointer;" onclick="selectAptReviewView(${dto.aptReviewNo});">
                     <td style="text-align:center;">${dto.aptReviewNo}</td>
                     <td style="text-align:center;">${dto.aptZoneCodeValue}</td>
                     <td style="text-align:center;">${dto.aptBlockCodeValue}</td>
                     <td style="text-align:center;">${dto.aptReviewTitle}</td>
                     <td style="text-align:center;">${dto.memberName}</td>
                     <td style="text-align:center;">${dto.aptReviewCreateDate}</td>
                    </tr>
                   </c:forEach>
                  </tbody>
                 </table>
                </div>
                <!-- /List -->
                <div id="paging">
                 <ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
                  <form:hidden path="pageIndex"/>
                </div>
                <div class="text-right pal">
                 <button type="button" class="btn btn-primary" onclick="insertAptReview();" tabindex="4">등록</button>
                </div>
              </div>
             </div>
            </div>
           </div>
          </div>
         </div>
        </form:form>
       </div>
       <!--END CONTENT-->
      </div>
      <!--END PAGE WRAPPER-->
     </div>
    </div>
   </body>
  </html>
