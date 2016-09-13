package kr.co.edsk.ojt.APTReview.controller;

import java.util.List;

import javax.annotation.Resource;

import kr.co.edsk.ojt.APTReview.service.TBAptReviewService;
import kr.co.edsk.ojt.APTReview.vo.DefaultVO;
import kr.co.edsk.ojt.APTReview.vo.TBAptReviewVO;
import kr.co.edsk.ojt.APTReview.vo.TBBlockCodeVO;
import kr.co.edsk.ojt.APTReview.vo.TBZoneCodeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : TBAptReviewController.java
 * @Description : TBAptReviewController Class
 * @Modification Information
 * @
 * @  수정일      	  	  수정자         				수정내용
 * @ ---------   	---------   -------------------------------
 * @ 20016.08.17  	  황상필         				최초생성
 *
 * @author 한국이디에스 황상필	
 * @since 2016.08.17
 * @version 1.0
 * @see
 */
@Controller
public class TBAptReviewController{

	/** 로그 메세지 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TBAptReviewController.class);

	/** TBAptReviewService */
	@Resource(name = "tbAptReviewService")
	private TBAptReviewService tbAptReviewService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	
	
	
	
	/** 모집공고글 목록 조회(paging)
	 *  
	 * @param defaultVO - 조회할 정보가 담긴 DefaultVO
	 * @param model
	 * 					Model → Interface , ModelMap → 구현체
	 * 					Spring @mvc 이전에는 ModelAndView를 통해서 뷰 페이지를 전달
						Spring @mvc 이후에는 Model or ModelMap를 사용한다.
	 * @return "aptreview/aptReviewList"
	 * @exception Exception
	 *  
	 */
	@RequestMapping(value = "/selectAptReviewList.do")
//			<form:form commandName="defaultVO">@ModelAttribute("defaultVO")는 같아야한다
	public String selectAptReviewList(@ModelAttribute("defaultVO") DefaultVO defaultVO, ModelMap model) throws Exception {
//		@RequestMapping selectAptReviewList() 진입로그
		LOGGER.info("@RequestMapping selectAptReviewList() In");
		
		/** 유효성 검사 */
		/** model 객체 */
		if(model == null || model.equals("")){
			LOGGER.info("model == null || model.equals('')");
		}
		/** defaultVO 객체 */
		if(defaultVO == null || defaultVO.equals("")){
			LOGGER.info("defaultVO == null || defaultVO.equals('')");
		}else{
			/** 검색조건 */
			String searchCondition = defaultVO.getSearchCondition();
			/** 검색Keyword */
			String searchKeyword = defaultVO.getSearchKeyword();
			/** 검색사용여부 */
			String searchUseYn = defaultVO.getSearchUseYn();
			/** 현재페이지 */
			int pageIndex = defaultVO.getPageIndex();
			/** 페이지갯수 */
			int pageUnit = defaultVO.getPageUnit();
			/** 페이지사이즈 */
			int pageSize = defaultVO.getPageSize();
			/** firstIndex */
			int firstIndex = defaultVO.getFirstIndex();
			/** lastIndex */
			int lastIndex = defaultVO.getLastIndex();
			/** recordCountPerPage */
			int recordCountPerPage = defaultVO.getRecordCountPerPage();
			
			/** 유효성 검사 */
			/** 검색조건 */
			if(searchCondition == null || searchCondition.equals("")){
				LOGGER.info("searchCondition == null || searchCondition.equals('')");
			}
			/** 검색Keyword */
			if(searchKeyword == null || searchKeyword.equals("")){
				LOGGER.info("searchKeyword == null || searchKeyword.equals('')");
			}
			/** 검색사용여부 */
			if(searchUseYn == null || searchUseYn.equals("")){
				LOGGER.info("searchUseYn == null || searchUseYn.equals('')");
			}
			/** 현재페이지 */
			if(pageIndex == 0 || pageIndex > 0){
				LOGGER.info("pageIndex == 0 || pageIndex > 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit > 0){
				LOGGER.info("pageUnit == 0 || pageUnit > 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize > 0){
				LOGGER.info("pageSize == 0 || pageSize > 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex > 0){
				LOGGER.info("firstIndex == 0 || firstIndex > 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex > 0){
				LOGGER.info("lastIndex == 0 || lastIndex > 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage > 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage > 0");
			}
		
			/** 페이지갯수 */
			defaultVO.setPageUnit(propertiesService.getInt("pageUnit"));
			/** 페이지사이즈 */
			defaultVO.setPageSize(propertiesService.getInt("pageSize"));
	
			/** pageing setting */
			PaginationInfo paginationInfo = new PaginationInfo();
	//		현재 페이지 번호를 저장
			paginationInfo.setCurrentPageNo(pageIndex);
	//		한 페이지에 해당되는 게시물 갯수 저장
			paginationInfo.setRecordCountPerPage(pageUnit);
	//		페이지 리스트에 게시되는 페이지 갯수 저장
			paginationInfo.setPageSize(pageSize);
	
	//		FirstIndex ~ LastIndex까지 보여줌		현재 페이지 번호				한 페이지에 게시되는 게시물 건수
	//		FirstIndex 공식 firstRecordIndex = (getCurrentPageNo() - 1) * getRecordCountPerPage();
			defaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	//											현재 페이지 번호		한 페이지에 게시되는 게시물 건수
	//		LastIndex 공식 lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
			defaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
	//		RecordCountPerPage 한 페이지에 게시되는 게시물 건수
			defaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
	//		후기게시판 조회
			List<DefaultVO> List = tbAptReviewService.selectAptReviewList(defaultVO);
			
	//		후기게시판 조회 데이터를 저장
			model.addAttribute("selectAptReviewList", List);
			
	//		후기게시판 게시물 건수 조회
			int totCnt = tbAptReviewService.selectAptReviewListTotalCount(defaultVO);
			
	//		검색된 게시물 건수 paginationInfo 객체에 저장
			paginationInfo.setTotalRecordCount(totCnt);
	
	//		paginationInfo 데이터를 저장
			model.addAttribute("paginationInfo", paginationInfo);
		
			LOGGER.info("@RequestMapping selectAptReviewList() Out  "+model.addAttribute("test", totCnt));
		}	/** defaultVO 객체 유효성검사 else End */
		
	//		@RequestMapping selectAptReviewList() 아웃로그
			
	//		조회 화면으로 이동
			return "aptreview/aptReviewList";
	}
	
	
	
	
	
	/**
	 * 모집공고 작성페이지 조회한다.
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "aptReviewRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAptReviewForm.do")
	public String insertAptReviewForm(DefaultVO defaultVO, ModelMap model) throws Exception {
//		@RequestMapping insertAptReviewForm() 진입로그
		LOGGER.info("@RequestMapping insertAptReviewForm() In");
		
		/** 유효성 검사 */
		/** model 객체 */
		if(model == null || model.equals("")){
			LOGGER.info("model == null || model.equals('')");
		}
		/** defaultVO 객체 */
		if(defaultVO == null || defaultVO.equals("")){
			LOGGER.info("defaultVO == null || defaultVO.equals('')");
		}else{
			/** 검색조건 */
			String searchCondition = defaultVO.getSearchCondition();
			/** 검색Keyword */
			String searchKeyword = defaultVO.getSearchKeyword();
			/** 검색사용여부 */
			String searchUseYn = defaultVO.getSearchUseYn();
			/** 현재페이지 */
			int pageIndex = defaultVO.getPageIndex();
			/** 페이지갯수 */
			int pageUnit = defaultVO.getPageUnit();
			/** 페이지사이즈 */
			int pageSize = defaultVO.getPageSize();
			/** firstIndex */
			int firstIndex = defaultVO.getFirstIndex();
			/** lastIndex */
			int lastIndex = defaultVO.getLastIndex();
			/** recordCountPerPage */
			int recordCountPerPage = defaultVO.getRecordCountPerPage();
			
			/** 유효성 검사 */
			/** 검색조건 */
			if(searchCondition == null || searchCondition.equals("")){
				LOGGER.info("searchCondition == null || searchCondition.equals('')");
			}
			/** 검색Keyword */
			if(searchKeyword == null || searchKeyword.equals("")){
				LOGGER.info("searchKeyword == null || searchKeyword.equals('')");
			}
			/** 검색사용여부 */
			if(searchUseYn == null || searchUseYn.equals("")){
				LOGGER.info("searchUseYn == null || searchUseYn.equals('')");
			}
			/** 현재페이지 */
			if(pageIndex == 0 || pageIndex > 0){
				LOGGER.info("pageIndex == 0 || pageIndex > 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit > 0){
				LOGGER.info("pageUnit == 0 || pageUnit > 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize > 0){
				LOGGER.info("pageSize == 0 || pageSize > 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex > 0){
				LOGGER.info("firstIndex == 0 || firstIndex > 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex > 0){
				LOGGER.info("lastIndex == 0 || lastIndex > 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage > 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage > 0");
			}
		}	
		/** defaultVO 객체 유효성검사 else End */
		TBZoneCodeVO zoneCodeVO = new TBZoneCodeVO();
		List<TBZoneCodeVO> zoneCodeVOList = tbAptReviewService.selectZoneCode(zoneCodeVO);
		LOGGER.info("insertAptReviewForm() Out@@"+zoneCodeVOList);
		model.addAttribute("zoneCodeVO", zoneCodeVOList);
		
		TBBlockCodeVO blockCodeVO = new TBBlockCodeVO();
		TBBlockCodeVO blockCodeVOList = tbAptReviewService.selectBlockCode(blockCodeVO);
		LOGGER.info("insertAptReviewForm() Out@@@@"+blockCodeVOList);
		model.addAttribute("blockCodeVO", blockCodeVOList);		
		
		
//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);
		
//		@RequestMapping insertAptReviewForm() 아웃로그
		LOGGER.info("insertAptReviewForm() Out");
		
//		등록페이지 이동
		return "aptreview/aptReviewRegister";
		
	}

	
	
	
	
	/**
	 * 모집공고글 작성
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param aptreviewVO - 등록할 정보가 담긴 VO
	 * @param model
	 * @return "forward:/selectAptReviewList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAptReview.do", method = RequestMethod.POST)
	public String insertAptReview(DefaultVO defaultVO, ModelMap model, TBAptReviewVO aptReviewVO) throws Exception {
//		@RequestMapping insertAptReview() 진입로그
		LOGGER.info("@RequestMapping insertAptReview() In");
		
		/** 유효성 검사 */
		/** model 객체 */
		if(model == null || model.equals("")){
			LOGGER.info("model == null || model.equals('')");
		}
		/** defaultVO 객체 */
		if(defaultVO == null || defaultVO.equals("")){
			LOGGER.info("defaultVO == null || defaultVO.equals('')");
		}else{
			/** 검색조건 */
			String searchCondition = defaultVO.getSearchCondition();
			/** 검색Keyword */
			String searchKeyword = defaultVO.getSearchKeyword();
			/** 검색사용여부 */
			String searchUseYn = defaultVO.getSearchUseYn();
			/** 현재페이지 */
			int pageIndex = defaultVO.getPageIndex();
			/** 페이지갯수 */
			int pageUnit = defaultVO.getPageUnit();
			/** 페이지사이즈 */
			int pageSize = defaultVO.getPageSize();
			/** firstIndex */
			int firstIndex = defaultVO.getFirstIndex();
			/** lastIndex */
			int lastIndex = defaultVO.getLastIndex();
			/** recordCountPerPage */
			int recordCountPerPage = defaultVO.getRecordCountPerPage();
			
			/** 유효성 검사 */
			/** 검색조건 */
			if(searchCondition == null || searchCondition.equals("")){
				LOGGER.info("searchCondition == null || searchCondition.equals('')");
			}
			/** 검색Keyword */
			if(searchKeyword == null || searchKeyword.equals("")){
				LOGGER.info("searchKeyword == null || searchKeyword.equals('')");
			}
			/** 검색사용여부 */
			if(searchUseYn == null || searchUseYn.equals("")){
				LOGGER.info("searchUseYn == null || searchUseYn.equals('')");
			}
			/** 현재페이지 */
			if(pageIndex == 0 || pageIndex > 0){
				LOGGER.info("pageIndex == 0 || pageIndex > 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit > 0){
				LOGGER.info("pageUnit == 0 || pageUnit > 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize > 0){
				LOGGER.info("pageSize == 0 || pageSize > 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex > 0){
				LOGGER.info("firstIndex == 0 || firstIndex > 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex > 0){
				LOGGER.info("lastIndex == 0 || lastIndex > 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage > 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage > 0");
			}
		}	
		/** defaultVO 객체 유효성검사 else End */
		
		/*
		 * 
		 * TBAptReviewVO 유효성검사해야됨
		 * 
		*/
		
//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);		
		
//		후기게시판 게시글 데이터 입력
		int result; 
		try{
			result = tbAptReviewService.insertAptReview(aptReviewVO);
			LOGGER.info("TBAptReviewController insertAptReview result: "+result);
		}catch(Exception e){
			result = -1;
			LOGGER.info("TBAptReviewController insertAptReview Exception result: "+result);
			e.printStackTrace();
		}
		
//		입력결과 확인
		if(result >= 1){
		model.addAttribute("insertResult",result);

//		@RequestMapping insertAptReview() 아웃로그
		LOGGER.info("@RequestMapping insertAptReview() Out");
		
//		후기게시판 목록으로 이동
		return "forward:/selectAptReviewList.do";
		
		}else{
			LOGGER.info("입력오류");
			result = -1;
			model.addAttribute("insertResult",result);
			return "forward:/insertAptReviewForm.do";
		}
}
	
	
	
	
	
	
	/**
	 * 모집공고 수정페이지 조회한다.
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @param aptReviewNo - 기준 번호
	 * @return "aptReviewRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateAptReviewForm.do")
	public String updateAptReviewForm(DefaultVO defaultVO, ModelMap model, int aptReviewNo) throws Exception {
//		@RequestMapping updateAptReviewForm() 진입로그
		LOGGER.info("@RequestMapping updateAptReviewForm() in");

		/** 유효성 검사 */
		/** 현제패이지 번호 */
		if(aptReviewNo == 0 || aptReviewNo > 0){
			LOGGER.info("aptReviewNo == 0 || aptReviewNo > 0");
		}
		/** model 객체 */
		if(model == null || model.equals("")){
			LOGGER.info("model == null || model.equals('')");
		}
		/** defaultVO 객체 */
		if(defaultVO == null || defaultVO.equals("")){
			LOGGER.info("defaultVO == null || defaultVO.equals('')");
		}else{
			/** 검색조건 */
			String searchCondition = defaultVO.getSearchCondition();
			/** 검색Keyword */
			String searchKeyword = defaultVO.getSearchKeyword();
			/** 검색사용여부 */
			String searchUseYn = defaultVO.getSearchUseYn();
			/** 현재페이지 */
			int pageIndex = defaultVO.getPageIndex();
			/** 페이지갯수 */
			int pageUnit = defaultVO.getPageUnit();
			/** 페이지사이즈 */
			int pageSize = defaultVO.getPageSize();
			/** firstIndex */
			int firstIndex = defaultVO.getFirstIndex();
			/** lastIndex */
			int lastIndex = defaultVO.getLastIndex();
			/** recordCountPerPage */
			int recordCountPerPage = defaultVO.getRecordCountPerPage();
			
			/** 유효성 검사 */
			/** 검색조건 */
			if(searchCondition == null || searchCondition.equals("")){
				LOGGER.info("searchCondition == null || searchCondition.equals('')");
			}
			/** 검색Keyword */
			if(searchKeyword == null || searchKeyword.equals("")){
				LOGGER.info("searchKeyword == null || searchKeyword.equals('')");
			}
			/** 검색사용여부 */
			if(searchUseYn == null || searchUseYn.equals("")){
				LOGGER.info("searchUseYn == null || searchUseYn.equals('')");
			}
			/** 현재페이지 */
			if(pageIndex == 0 || pageIndex > 0){
				LOGGER.info("pageIndex == 0 || pageIndex > 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit > 0){
				LOGGER.info("pageUnit == 0 || pageUnit > 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize > 0){
				LOGGER.info("pageSize == 0 || pageSize > 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex > 0){
				LOGGER.info("firstIndex == 0 || firstIndex > 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex > 0){
				LOGGER.info("lastIndex == 0 || lastIndex > 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage > 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage > 0");
			}
		}	
		/** defaultVO 객체 유효성검사 else End */

//		모집공고번호 기준 데이터 조회
		TBAptReviewVO aptReviewVO = tbAptReviewService.selectAptReviewView(aptReviewNo);
		
//		모집공고번호 기준 데이터 저장
		model.addAttribute("updateAptReviewForm", aptReviewVO);
		
//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);
		
//		@RequestMapping updateAptReviewForm() 아웃로그
		LOGGER.info("@RequestMapping updateAptReviewForm() Out");
		
//		수정페이지 이동
		return "aptreview/aptReviewUpdate";
	}

	
	
	
	
	/**
	 * 모집공고글 수정
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param aptreviewVO - 등록할 정보가 담긴 VO
	 * @param model
	 * @return "forward:/selectAptReviewList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateAptReview.do", method = RequestMethod.POST)
	public String updateAptReview(DefaultVO defaultVO, ModelMap model, TBAptReviewVO aptReviewVO)
			throws Exception {
//		@RequestMapping updateAptReview() 진입로그
		LOGGER.info("@RequestMapping updateAptReview() In");

		/*
		 * 
		 * TBAptReviewVO 유효성검사해야됨
		 * 
		*/
		
		
//		후기게시판 게시글 수정
		tbAptReviewService.updateAptReview(aptReviewVO);
		
//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);		
		
//		@RequestMapping updateAptReview() 아웃로그
		LOGGER.info("@RequestMapping updateAptReview() Out");
		
//		후기게시판 목록으로 이동
//		return "redirect:/selectAptReviewList.do";
		return "forward:/selectAptReviewList.do";
}
	
	
	
	
	
	/**
	 * 글을 삭제한다.
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @param aptReviewNo - 삭제 기준 번호
	 * @return "forward:/selectAptReviewList.do"
	 * @exception Exception
	 */
	@RequestMapping("/deleteAptReview.do")
	public String deleteAptReview(DefaultVO defaultVO, ModelMap model, int aptReviewNo) throws Exception {
//		@RequestMapping deleteAptReview() 진입로그
		LOGGER.info("@RequestMapping deleteAptReview() In" + aptReviewNo);
		
		/** 유효성 검사 */
		/** 현제패이지 번호 */
		if(aptReviewNo == 0 || aptReviewNo > 0){
			LOGGER.info("aptReviewNo == 0 || aptReviewNo > 0");
		}
		/** model 객체 */
		if(model == null || model.equals("")){
			LOGGER.info("model == null || model.equals('')");
		}
		/** defaultVO 객체 */
		if(defaultVO == null || defaultVO.equals("")){
			LOGGER.info("defaultVO == null || defaultVO.equals('')");
		}else{
			/** 검색조건 */
			String searchCondition = defaultVO.getSearchCondition();
			/** 검색Keyword */
			String searchKeyword = defaultVO.getSearchKeyword();
			/** 검색사용여부 */
			String searchUseYn = defaultVO.getSearchUseYn();
			/** 현재페이지 */
			int pageIndex = defaultVO.getPageIndex();
			/** 페이지갯수 */
			int pageUnit = defaultVO.getPageUnit();
			/** 페이지사이즈 */
			int pageSize = defaultVO.getPageSize();
			/** firstIndex */
			int firstIndex = defaultVO.getFirstIndex();
			/** lastIndex */
			int lastIndex = defaultVO.getLastIndex();
			/** recordCountPerPage */
			int recordCountPerPage = defaultVO.getRecordCountPerPage();
			
			/** 유효성 검사 */
			/** 검색조건 */
			if(searchCondition == null || searchCondition.equals("")){
				LOGGER.info("searchCondition == null || searchCondition.equals('')");
			}
			/** 검색Keyword */
			if(searchKeyword == null || searchKeyword.equals("")){
				LOGGER.info("searchKeyword == null || searchKeyword.equals('')");
			}
			/** 검색사용여부 */
			if(searchUseYn == null || searchUseYn.equals("")){
				LOGGER.info("searchUseYn == null || searchUseYn.equals('')");
			}
			/** 현재페이지 */
			if(pageIndex == 0 || pageIndex > 0){
				LOGGER.info("pageIndex == 0 || pageIndex > 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit > 0){
				LOGGER.info("pageUnit == 0 || pageUnit > 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize > 0){
				LOGGER.info("pageSize == 0 || pageSize > 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex > 0){
				LOGGER.info("firstIndex == 0 || firstIndex > 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex > 0){
				LOGGER.info("lastIndex == 0 || lastIndex > 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage > 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage > 0");
			}
		}	
		/** defaultVO 객체 유효성검사 else End */
		
//		후기게시판 게시글 삭제
		tbAptReviewService.deleteAptReview(aptReviewNo);

//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);		
		
//		@RequestMapping deleteAptReview() 아웃로그
		LOGGER.info("@RequestMapping deleteAptReview() Out");

		//게시글 조회페이지로 이동
//		return "redirect:/selectAptReviewList.do";
		return "forward:/selectAptReviewList.do";
	}

	
	
	
	
	/**
	 * 모집공고 상세 게시글을 조회한다.
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param aptReviewNo  - 상세조회 기준 번호
	 * @return "aptReviewListView""
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectAptReviewView.do")
	public String selectAptReviewView(DefaultVO defaultVO, ModelMap model, int aptReviewNo) throws Exception {
//		@RequestMapping selectAptReviewView() 진입로그
		LOGGER.info("@RequestMapping selectAptReviewView() In");
		
		/** 유효성 검사 */
		/** 현제패이지 번호 */
		if(aptReviewNo == 0 || aptReviewNo > 0){
			LOGGER.info("aptReviewNo == 0 || aptReviewNo > 0");
		}
		/** model 객체 */
		if(model == null || model.equals("")){
			LOGGER.info("model == null || model.equals('')");
		}
		/** defaultVO 객체 */
		if(defaultVO == null || defaultVO.equals("")){
			LOGGER.info("defaultVO == null || defaultVO.equals('')");
		}else{
			/** 검색조건 */
			String searchCondition = defaultVO.getSearchCondition();
			/** 검색Keyword */
			String searchKeyword = defaultVO.getSearchKeyword();
			/** 검색사용여부 */
			String searchUseYn = defaultVO.getSearchUseYn();
			/** 현재페이지 */
			int pageIndex = defaultVO.getPageIndex();
			/** 페이지갯수 */
			int pageUnit = defaultVO.getPageUnit();
			/** 페이지사이즈 */
			int pageSize = defaultVO.getPageSize();
			/** firstIndex */
			int firstIndex = defaultVO.getFirstIndex();
			/** lastIndex */
			int lastIndex = defaultVO.getLastIndex();
			/** recordCountPerPage */
			int recordCountPerPage = defaultVO.getRecordCountPerPage();
			
			/** 유효성 검사 */
			/** 검색조건 */
			if(searchCondition == null || searchCondition.equals("")){
				LOGGER.info("searchCondition == null || searchCondition.equals('')");
			}
			/** 검색Keyword */
			if(searchKeyword == null || searchKeyword.equals("")){
				LOGGER.info("searchKeyword == null || searchKeyword.equals('')");
			}
			/** 검색사용여부 */
			if(searchUseYn == null || searchUseYn.equals("")){
				LOGGER.info("searchUseYn == null || searchUseYn.equals('')");
			}
			/** 현재페이지 */
			if(pageIndex == 0 || pageIndex > 0){
				LOGGER.info("pageIndex == 0 || pageIndex > 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit > 0){
				LOGGER.info("pageUnit == 0 || pageUnit > 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize > 0){
				LOGGER.info("pageSize == 0 || pageSize > 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex > 0){
				LOGGER.info("firstIndex == 0 || firstIndex > 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex > 0){
				LOGGER.info("lastIndex == 0 || lastIndex > 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage > 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage > 0");
			}
		}	
		/** defaultVO 객체 유효성검사 else End */
		
		LOGGER.info("defaultVO.getPageIndex() In");
		
		LOGGER.info(""+aptReviewNo+" 번째 게시글 상세보기");
	
//		모집공고번호 기준 데이터 조회
		TBAptReviewVO aptReviewVO = tbAptReviewService.selectAptReviewView(aptReviewNo);
		
//		모집공고번호 기준 데이터 저장
		model.addAttribute("selectAptReviewView", aptReviewVO);

//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);
		
//		@RequestMapping selectAptReviewView() 아웃로그
		LOGGER.info("@RequestMapping selectAptReviewView() Out");
		
//		상세조회 페이지 이동
		return "aptreview/aptReviewListView";
	}
	
}
