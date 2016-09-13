<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>모집공고 전체목록</title>
    <%@ include file="../include/inc_header.jsp"%>
    
    <script type="text/javascript">
        /* 글 상세보기 화면 function */
        function fn_egov_select(announcementNo) {
        	console.log(announcementNo);
        	document.listForm.selectedAnnouncementNo.value = announcementNo;
           	document.listForm.action = "<c:url value='/selectDetailAnnouncementView.do'/>";
           	document.listForm.submit();
        }
        
        /* 글 등록 화면 function */
        function fn_egov_addView() {
           	document.listForm.action = "<c:url value='/insertAnnouncementForm.do'/>";
           	document.listForm.submit();
        }
        
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
        	document.listForm.action = "<c:url value='/announcementList.do'/>";
           	document.listForm.submit();
        }
        
        /* pagination 페이지 링크 function */
        function fn_egov_link_page(pageNo){
        	document.listForm.pageIndex.value = pageNo;
        	document.listForm.action = "<c:url value='/announcementList.do'/>";
           	document.listForm.submit();
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
						<div class="page-title">모집공고 게시판</div>
					</div>
					<div class="clearfix"></div>
				</div>
			<!--END TITLE & BREADCRUMB PAGE-->
				
			<!--BEGIN CONTENT-->
				<div class="page-content">
    				<form:form commandName="defaultVO" id="listForm" name="listForm" method="post">
					<input type="hidden" name="selectedAnnouncementNo" />
					<div id="tab-general">
						<div class="row mbl">
							<div class="col-lg-12">
								<div class="row">
									<div class="col-lg-12">
										<div class="panel panel-yellow">
											<div class="panel-heading">모집공고 게시판 목록</div>
											<div class="mbl"></div>
											<div class="col-lg-8">&nbsp;</div>
												<div class="col-lg-4">
													<div>
														<input type="hidden" name="searchUseYn" value="Y">
													 	<label for="searchCondition">검색 선택 : </label>
								        				<form:select path="searchCondition" cssClass="use">
								        					<form:option value="1" label="공고내용" />
								        					<form:option value="0" label="공고제목" />
								        				</form:select>
													</div>																						        		
													<div class="input-group">
														<span class="input-group-addon">
															<i class="fa fa-search"></i>
														</span>
														<input type="text" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력하세요" class="form-control"  tabindex="1"/>
														<span class="input-group-btn">
															<button type="button" class="btn btn-default" onclick="javascript:fn_egov_selectList()" tabindex="2">검색</button>
														</span>
													</div>
												</div>
												
												<div class="col-lg-12">&nbsp;</div>
												<div class="col-lg-12">&nbsp;</div>
												<div class="panel-body" style="overflow: auto;">
													<table class="table table-hover">
														<thead>
															<tr>
										        				<th style="text-align:center; width: 10px;">No</th>
										        				<th style="text-align:center; width: 60px;">지역코드</th>
										        				<!-- <th align="center" width="100">작성자</th>-->
										        				<th style="text-align:center; width: 80px;">공고명</th>
										        				<th style="text-align:center; width: 50px;">공고일</th>
										        				<th style="text-align:center; width: 50px;">마감일</th>
										        				<th style="text-align:center; width: 80px;">소재지</th>
										        				<th style="text-align:center; width: 60px;">전용면적</th>
										        				<th style="text-align:center; width: 60px;">총세대수</th>
										        				<th style="text-align:center; width: 60px;">조회수</th>
										        				<th style="text-align:center; width: 50px;">작성일</th>
															</tr>
														</thead>
														
														<tbody>
															<c:forEach var="result" items="${AnnouncementList}" varStatus="status">
										            			<tr style="cursor: pointer;">
										            				<td style="text-align:center;"><c:out value="${result.announcementNo}"/></td>
										            				<td style="text-align:center;"><c:out value="${result.aptZoneCode}"/></td>
										            				<%-- <td style="text-align:center;" class="listtd"><c:out value="${result.memberNo}"/></td> --%>
										            				<td style="text-align:center;"><a href="javascript:fn_egov_select('<c:out value="${result.announcementNo}"/>');"><c:out value="${result.announcementName}"/></a></td>
										            				<td style="text-align:center;"><fmt:formatDate value="${result.announcementStart}" pattern="yyyy년 MM월 dd일"/></td>
										            				<td style="text-align:center;"><fmt:formatDate value="${result.announcementEnd}" pattern="yyyy년 MM월 dd일"/></td>
										            				<td style="text-align:center;"><c:out value="${result.announcementAddress}"/></td>
										            				<td style="text-align:center;"><c:out value="${result.announcementArea}"/></td>
										            				<td style="text-align:center;"><c:out value="${result.announcementAllresident}"/></td>
										            				<td style="text-align:center;"><c:out value="${result.announcementClicks}"/></td>
										            				<td style="text-align:center;"><c:out value="${result.announcementCreateDate}"/></td>
										            			</tr>
        													</c:forEach>
														</tbody>
													</table>
													
													<!-- /List -->
										        	<div id="paging">
													<%-- 페이징 처리의 편의를 위해 <ui:pagination/> 태그를 제공한다.
														<ui:pagination/>의 주요 속성은 아래와 같다.
														
														이름			설명	
														paginationInfo:	페이징리스트를 만들기 위해 필요한 데이터. 데이터 타입은 egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo이다.	
														type		  :	페이징리스트 렌더링을 담당할 클래스의 아이디. 이 아이디는 빈설정 파일에 선언된 프로퍼티 rendererType의 key값이다.
														jsFunction	  :	페이지 번호에 걸리게 될 자바스크립트 함수 이름. 페이지 번호가 기본적인 argument로 전달된다. --%>
										        	
										        		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page"/>
										        		<form:hidden path="pageIndex" />
										        	</div>			
													<div class="text-right pal">
														<button type="button" class="btn btn-primary" onclick="javascript:fn_egov_addView();" tabindex="13">등록</button>
													</div>
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
