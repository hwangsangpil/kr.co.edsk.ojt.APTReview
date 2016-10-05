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
<title>APT Review HOME</title>
<%@ include file="include/inc_header.jsp"%>
<script type="text/javascript">

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
  <%@ include file="include/inc_top.jsp"%>
   <!--END TOPBAR-->
   <div id="wrapper">
    <!--BEGIN SIDEBAR MENU-->
    <%@ include file="include/inc_left_menu.jsp"%>
     <!--END SIDEBAR MENU-->
     <div id="page-wrapper">

       <!--BEGIN CONTENT-->
       <div class="page-content">
        <!-- commandName == @ModelAttribute(값) -->
        <form name="aptReviewList" method="post">
        <input type="hidden" id="aptReviewNo" name="aptReviewNo" value="0">
         
          
           
            <div class="row">
             <div class="col-lg-12">
             
             
              <div class="panel panel-yellow" style="width: 35%; float: left; height: 60%; margin-top: 50px; margin-left: 90px;">
               <div class="panel-heading" style="text-align:center;">모집공고 목록</div>
               <div class="mbl"></div>
               
                <div class="panel-body" style="overflow: auto; padding-right: 10px; padding-left: 10px;">
                 <table class="table table-hover">
                  <thead>
                   <tr>
                    <th style="text-align:center;">제목</th>
                    <th style="text-align:center;">작성일</th>
                   </tr>
                  </thead>
                  <tbody>
                   <c:forEach items="${selectAnnouncementHomeList}" var="selectAnnouncementHomeList">
                	<%-- <input type="hidden" id="aptReviewNo" name="aptReviewNo" value="${dto.aptReviewNo}"/> --%>
                    <tr  style="cursor: pointer;" onclick="selectAptReviewView(${selectAnnouncementHomeList.ANNOUNCEMENT_NO});">
                     <td style="text-align:left;">${selectAnnouncementHomeList.ANNOUNCEMENT_NAME}</td>
                     <td style="text-align:center;">${selectAnnouncementHomeList.ANNOUNCEMENT_CREATE_DATE}</td>
                    </tr>
                   </c:forEach>
                  </tbody>
                 </table>
                </div>
                <!-- /List -->
              </div>
              
              
              <div class="panel panel-yellow" style="width: 35%; float: right; height: 60%; margin-top: 50px; margin-right: 90px;">
               <div class="panel-heading" style="text-align:center;">후기게시판 목록</div>
               <div class="mbl"></div>
                <div class="panel-body" style="overflow: auto; padding-right: 10px; padding-left: 10px;">
                 <table class="table table-hover">
                  <thead>
                   <tr>
                    <th style="text-align:center;">제목</th>
                    <th style="text-align:center;">작성일</th>
                   </tr>
                  </thead>
                  <tbody>
                   <c:forEach items="${selectAptReviewHomeList}" var="selectAptReviewHomeList">
                	<%-- <input type="hidden" id="aptReviewNo" name="aptReviewNo" value="${dto.aptReviewNo}"/> --%>
                    <tr  style="cursor: pointer;" onclick="selectAptReviewView(${selectAptReviewHomeList.APT_REVIEW_NO});">
                     <td style="text-align:left;">${selectAptReviewHomeList.APT_REVIEW_TITLE}</td>
                     <td style="text-align:center;">${selectAptReviewHomeList.APT_REVIEW_CREATE_DATE}</td>
                    </tr>
                   </c:forEach>
                  </tbody>
                 </table>
                </div>
                <!-- /List -->
              </div>
              
             </div>
            </div>
           
          
         
        </form>
       </div>
       <!--END CONTENT-->
      </div>
      <!--END PAGE WRAPPER-->
     </div>
    </div>
   </body>
  </html>
