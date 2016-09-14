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

	/* 수정페이지 이동 */
	function updateAptReview() {
		document.aptReviewListView.method = "post";
		document.aptReviewListView.action = "/updateAptReviewForm.do";
		document.aptReviewListView.submit();
	}
	
	/* 삭제 */
	function deleteAptReview() {
		document.aptReviewListView.method = "post";
		document.aptReviewListView.action = "/deleteAptReview.do";
		document.aptReviewListView.submit();
	}
	
	/* 게시글 답글등록 */
/* 	function insertAptReviewReply() {
		document.aptReviewListView.method = "post";
		document.aptReviewListView.action = "/updateAptReviewForm.do";
		document.aptReviewListView.submit();
	} 
*/
	
	/* 취소 시 목록페이지 이동 */
	function selectAptReview(pageIndex) {
		document.getElementById('pageIndex').value = pageIndex
		document.aptReviewListView.method = "post";
		document.aptReviewListView.action = "/selectAptReviewList.do";
		document.aptReviewListView.submit();
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
        <div class="page-title">후기게시판 상세보기</div>
       </div>
       <div class="clearfix"></div>
      </div>
       <!--END TITLE & BREADCRUMB PAGE-->
       <!--BEGIN CONTENT-->
       <div class="page-content">
        <!-- commandName == @ModelAttribute(값) -->
        <form id="aptReviewListView" name= "aptReviewListView">
        
        <c:forEach items="${selectAptReviewView}" var="selectAptReviewView">
        <input type="hidden" id="aptReviewNo" name="aptReviewNo" value="${selectAptReviewView.aptReviewNo}"/>
        </c:forEach>
        
        <input type="hidden" id="pageIndex" name="pageIndex" value="${defaultVO.pageIndex}"/>
         <div id="tab-general">
          <div class="row mbl">
           <div class="col-lg-12">
            <div class="row">
             <div class="col-lg-12">
              <div class="panel panel-yellow">
              
              <c:forEach items="${selectAptReviewView}" var="selectAptReviewView">
               <div class="panel-heading">${selectAptReviewView.aptReviewTitle}</div>
               </c:forEach>
               
               <div class="mbl"></div>
               <div class="col-lg-8">&nbsp;</div>
               <div class="col-lg-4">
                <div class="input-group">
				</div>
               </div>
               <div class="col-lg-12">&nbsp;</div>
               <div class="col-lg-12">&nbsp;</div>
                <div class="panel-body" style="overflow: auto;">
                 <table class="table table-hover">
                  <thead>
                   <tr>
                    <th style="text-align:center; width: 200px;">지역</th>
                    <th style="text-align:center; width: 150px;">단지</th>
                    <th style="text-align:center; width: 150px;">제목</th>
                    <th style="text-align:center; width: 150px;">도면도</th>
                    <th style="text-align:center; width: 150px;">내용</th>
                   </tr>
                  </thead>
                  <tbody>
                    <tr style="cursor: pointer;">
                    <c:forEach items="${selectAptReviewView}" var="selectAptReviewView">
                     <td style="text-align:center;">${selectAptReviewView.aptZoneCodeValue}</td>
                     <td style="text-align:center;">${selectAptReviewView.aptBlockCodeValue}</td>
                     <td style="text-align:center;">${selectAptReviewView.aptReviewTitle}</td>
                     </c:forEach>
                     <td style="text-align:center;">${defaultVO.pageIndex}</td>
                     <c:forEach items="${selectAptReviewView}" var="selectAptReviewView">
                     <td style="text-align:center;">${selectAptReviewView.aptReviewContent}</td>
                     </c:forEach>
                    </tr>
                  </tbody>
                 </table>
                </div>
                <!-- /List -->
                <div class="text-right pal">
                 <button type="button" class="btn btn-primary" onclick="updateAptReview();" tabindex="1">수정</button>
                 <button type="button" class="btn btn-primary" onclick="deleteAptReview();" tabindex="2">삭제</button>
                 <!-- <button type="button" class="btn btn-primary" onclick="insertAptReviewReply();" tabindex="3">답글작성</button> -->
                 <button type="button" class="btn btn-primary" onclick="selectAptReview(${defaultVO.pageIndex});" tabindex="4">목록</button>
                </div>
              </div>
             </div>
            </div>
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
