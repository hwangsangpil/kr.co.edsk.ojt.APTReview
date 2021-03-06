package kr.co.edsk.ojt.APTReview.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.co.edsk.ojt.APTReview.service.TBAptReviewService;
import kr.co.edsk.ojt.APTReview.vo.DefaultVO;
import kr.co.edsk.ojt.APTReview.vo.TBAptReviewVO;
import kr.co.edsk.ojt.APTReview.vo.TBBlockCodeVO;
import kr.co.edsk.ojt.APTReview.vo.TBZoneCodeVO;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	
	

	
	/** 홈 목록 조회
	 * @param defaultVO - 페이지 정보가 담긴 DefaultVO
	 * @param model
	 *	 				Model → Interface , ModelMap → 구현체
	 * 					Spring @mvc 이전에는 ModelAndView를 통해서 뷰 페이지를 전달
	 *					Spring @mvc 이후에는 Model or ModelMap를 사용한다.
	 * @return home
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectHomeList.do")
	public String selectHomeList(DefaultVO defaultVO, ModelMap model, Map<String,String> map) throws Exception {
//		@Controller selectHomeList() 진입로그
		LOGGER.info("@Controller selectHomeList() In");
			
//		모델 또는 맵에서 데이터를 가지고와서 파라미터로 사용할 수 있는지 알아보기
//		위처럼 사용하는게 좋은지 받아온 객체를 바로 사용하는게 좋은지 알아보기
		
//		파라미터 유효성 검사
		
		/** model 객체 유효성 검사 Start */
		if(model.isEmpty()){
			LOGGER.debug("model객체에 데이터가 없습니다.");
			return "Redirect:/selectHomeList.do";
		}/** model 객체 유효성 검사 End */
		if(map.isEmpty()){
			LOGGER.debug("map객체에 데이터가 없습니다.");
			return "Redirect:/selectHomeList.do";
		}
		
//		map에 null이 있다면
		if(map.size()>0){
			LOGGER.debug("map.size()>0:   "+map.size());
			for(int i=0; map.size()>i; i++){
				if(map.get(i)==null){
					LOGGER.debug("map.get("+i+") == null:   "+map.get(i));
					LOGGER.debug("map.size()>0:   "+map.toString());
				}
			}
		}

		/** defaultVO 객체 유효성 검사 Start */
		if(defaultVO != null){
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
			
			/** 검색조건 */
			if(searchCondition == null || searchCondition.equals("")){
				LOGGER.info("입력된 검색어 조건이 없습니다.");
			}
			/** 검색Keyword */
			if(searchKeyword == null || searchKeyword.equals("")){
				LOGGER.info("입력된 검색어가 없습니다.");
			}
			/** 검색사용여부 */
			if(searchUseYn == null || searchUseYn.equals("")){
				LOGGER.info("검색기능을 사용하지 않습니다.");
			}
			/** 현재페이지 */
			if(pageIndex == 0 || pageIndex < 0){
				LOGGER.debug("현재페이지 번호가 없습니다.");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit < 0){
				LOGGER.debug("페이지갯수가 없습니다.");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize < 0){
				LOGGER.debug("페이지 사이즈가 없습니다.");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex < 0){
				LOGGER.debug("시작 값이 없습니다");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex < 0){
				LOGGER.debug("마지막 값이 없습니다.");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage < 0){
				LOGGER.info("현재페이지에 보여줄 값의 범위가 없습니다.");
			}
		}else{
			LOGGER.debug("defaultVO 객체가 없습니다.");
			return "redirect:/selectHomeList.do";
		}/** defaultVO 객체 유효성 검사 End */
		
//		후기게시판 조회
		List<Map<String,String>> selectAptReviewHomeList = tbAptReviewService.selectAptReviewHomeList(defaultVO);
//		후기게시판 조회 데이터를 저장
		model.addAttribute("selectAptReviewHomeList", selectAptReviewHomeList);
			
//		모집공고 조회
		List<Map<String,String>> selectAnnouncementHomeList = tbAptReviewService.selectAnnouncementHomeList(defaultVO);
//		모집공고 조회 데이터를 저장
		model.addAttribute("selectAnnouncementHomeList", selectAnnouncementHomeList);
			
//		@Controller selectHomeList() 아웃로그
		LOGGER.info("@Controller selectHomeList() Out\n");
			
//		조회 화면으로 이동
		return "home";
	}
	
	
	
	
	
	/** 모집공고글 목록 조회(paging)
	 *  
	 * @param defaultVO - 조회할 정보가 담긴 DefaultVO
	 * @param model
	 * @return "aptreview/aptReviewList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectAptReviewList.do")
//			<form:form commandName="defaultVO">@ModelAttribute("defaultVO")는 같아야한다
	public String selectAptReviewList(@ModelAttribute("defaultVO") DefaultVO defaultVO, ModelMap model) throws Exception {
//		@Controller selectAptReviewList() 진입로그
		LOGGER.info("@Controller selectAptReviewList() In");

//		유효성 검사
		
		/** model 객체 */
		if(model == null || model.equals("")){
			LOGGER.debug("model객체에 데이터가 없습니다.");
			return "Redirect:/homp";
		}
		/** defaultVO 객체 */
		if(defaultVO == null || defaultVO.equals("")){
			LOGGER.debug("defaultVO 객체가 없습니다");
			return "Redirect:/homp";
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
			
//			유효성 검사
			
			/** 검색조건 */
			if(searchCondition == null || searchCondition.equals("")){
				LOGGER.info("입력된 검색어 조건이 없습니다.");
			}
			/** 검색Keyword */
			if(searchKeyword == null || searchKeyword.equals("")){
				LOGGER.info("입력된 검색어가 없습니다.");
			}
			/** 검색사용여부 */
			if(searchUseYn == null || searchUseYn.equals("")){
				LOGGER.info("검색기능을 사용하지 않습니다.");
			}
			/** 현재페이지 */
			if(pageIndex == 0 || pageIndex < 0){
				LOGGER.debug("현재페이지 번호가 없습니다.");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit < 0){
				LOGGER.debug("페이지갯수가 없습니다.");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize < 0){
				LOGGER.debug("페이지 사이즈가 없습니다.");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex < 0){
				LOGGER.debug("시작 값이 없습니다");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex < 0){
				LOGGER.debug("마지막 값이 없습니다.");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage < 0){
				LOGGER.info("현재페이지에 보여줄 값의 범위가 없습니다.");
			}
		}	
		/** defaultVO 객체 유효성검사 else End */		
		
		
//		pageing setting
		
		/** 페이지갯수 */
		defaultVO.setPageUnit(propertiesService.getInt("pageUnit"));
		/** 페이지사이즈 */
		defaultVO.setPageSize(propertiesService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
//		현재 페이지 번호를 저장
		paginationInfo.setCurrentPageNo(defaultVO.getPageIndex());
			
//		한 페이지에 해당되는 게시물 갯수 저장
		paginationInfo.setRecordCountPerPage(defaultVO.getPageUnit());
			
//		페이지 리스트에 게시되는 페이지 갯수 저장
		paginationInfo.setPageSize(defaultVO.getPageSize());
			
//		FirstIndex ~ LastIndex까지 보여줌		현재 페이지 번호				한 페이지에 게시되는 게시물 건수
//		FirstIndex 공식 firstRecordIndex = (getCurrentPageNo() - 1) * getRecordCountPerPage();
		defaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			
//										현재 페이지 번호		한 페이지에 게시되는 게시물 건수
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
			
		
//		@Controller selectAptReviewList() 아웃로그
		LOGGER.info("@Controller selectAptReviewList() Out");
			
//		조회 화면으로 이동
		return "aptreview/aptReviewList";
	}
	
	
	
	
	
	/**
	 * 후기게시판 상세 게시글을 조회한다.
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param aptReviewNo  - 상세조회 기준 번호
	 * @return "aptreview/aptReviewListView"
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectAptReviewView.do", method = RequestMethod.POST)
	public String selectAptReviewView(DefaultVO defaultVO, ModelMap model, int aptReviewNo, Map map, TBAptReviewVO aptReviewVO) throws Exception {
//		@RequestMapping selectAptReviewView() 진입로그
		LOGGER.info("@RequestMapping selectAptReviewView() In");
		LOGGER.debug("model:  "+model);
		LOGGER.debug("defaultVO: "+defaultVO);
		LOGGER.debug("map: "+map);
		LOGGER.debug("aptReviewVO: "+aptReviewVO);
//		유효성 검사
		
		/** 현재페이지 번호 */
		if(aptReviewNo == 0 || aptReviewNo < 0){
			LOGGER.info("aptReviewNo == 0 || aptReviewNo < 0");
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
			
//			유효성 검사
			
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
			if(pageIndex == 0 || pageIndex < 0){
				LOGGER.info("pageIndex == 0 || pageIndex < 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit < 0){
				LOGGER.info("pageUnit == 0 || pageUnit < 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize < 0){
				LOGGER.info("pageSize == 0 || pageSize < 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex < 0){
				LOGGER.info("firstIndex == 0 || firstIndex < 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex < 0){
				LOGGER.info("lastIndex == 0 || lastIndex < 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage < 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage < 0");
			}
		}	
		/** defaultVO 객체 유효성검사 else End */
		
		LOGGER.info("defaultVO.getPageIndex() In");
		
		LOGGER.info(""+aptReviewNo+" 번째 게시글 상세보기");
	
//		모집공고번호 기준 데이터 조회
//		List<?> List = tbAptReviewService.selectAptReviewView(aptReviewNo);
		
		HashMap<?, ?> Map = tbAptReviewService.selectAptReviewView(aptReviewNo);
		LOGGER.info("@@List<?> List:  "+Map);
		LOGGER.info("@@List<?> List0:  "+Map);
//		LOGGER.info("@@List<?> List1:  "+List.get(1));
//		LOGGER.info("@@List<?> List2:  "+List.get(2));
		LOGGER.info("@@model.addAttribute:  "+model.addAttribute("List",Map));
//		모집공고번호 기준 데이터 저장
		model.addAttribute("selectAptReviewView", Map);

//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);
		
//		@RequestMapping selectAptReviewView() 아웃로그
		LOGGER.info("@RequestMapping selectAptReviewView() Out");
		
//		상세조회 페이지 이동
		return "aptreview/aptReviewListView";
	}	
	
	
	
	
	
	/**
	 * 모집공고 작성페이지 조회한다.
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "aptReviewRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAptReviewForm.do", method = RequestMethod.POST)
	public String insertAptReviewForm(DefaultVO defaultVO, ModelMap model) throws Exception {
//		@Controller insertAptReviewForm() 진입로그
		LOGGER.info("@Controller insertAptReviewForm() In");
		
//		유효성 검사
		
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
			
//			유효성 검사
			
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
			if(pageIndex == 0 || pageIndex < 0){
				LOGGER.info("pageIndex == 0 || pageIndex < 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit < 0){
				LOGGER.info("pageUnit == 0 || pageUnit < 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize < 0){
				LOGGER.info("pageSize == 0 || pageSize < 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex < 0){
				LOGGER.info("firstIndex == 0 || firstIndex < 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex < 0){
				LOGGER.info("lastIndex == 0 || lastIndex < 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage < 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage < 0");
			}
		}//	defaultVO 객체 유효성검사 else End
			TBZoneCodeVO zoneCodeVO = new TBZoneCodeVO();
			List<TBZoneCodeVO> zoneCodeVOList = tbAptReviewService.selectZoneCode(zoneCodeVO);
			
			TBBlockCodeVO blockCodeVO = new TBBlockCodeVO();
			TBBlockCodeVO blockCodeVOList = tbAptReviewService.selectBlockCode(blockCodeVO);
			
//			지역 정보 저장
			model.addAttribute("zoneCodeVO", zoneCodeVOList);
//			단지 정보 저장
			model.addAttribute("blockCodeVO", blockCodeVOList);		
//			페이지 정보 저장
			model.addAttribute("defaultVO", defaultVO);
		
//		@Controller insertAptReviewForm() 아웃로그
		LOGGER.info("@Controller insertAptReviewForm() Out");
		
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
	public String insertAptReview(DefaultVO defaultVO, ModelMap model, TBAptReviewVO aptReviewVO, HttpServletRequest request) throws Exception {
//		@Controller insertAptReview() 진입로그
		LOGGER.info("@Controller insertAptReview() In" + request);
		
//		유효성 검사
		
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
			
//			유효성 검사
			
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
			if(pageIndex == 0 || pageIndex < 0){
				LOGGER.info("pageIndex == 0 || pageIndex < 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit < 0){
				LOGGER.info("pageUnit == 0 || pageUnit < 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize < 0){
				LOGGER.info("pageSize == 0 || pageSize < 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex < 0){
				LOGGER.info("firstIndex == 0 || firstIndex < 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex < 0){
				LOGGER.info("lastIndex == 0 || lastIndex < 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage < 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage < 0");
			}
		}//	defaultVO 객체 유효성검사 else End
		
//		유효성 검사
		
		/** aptReviewVO 객체 */
		if(aptReviewVO==null || aptReviewVO.equals("")){
			LOGGER.info("aptReviewVO == null || aptReviewVO.equals('')");
		
		}else{
			
			/** 후기게시판번호 */
			int aptReviewNo = aptReviewVO.getAptReviewNo();
			/** 작성자 */
			int memberNo = aptReviewVO.getMemberNo();
			/** 지역코드 */
			String aptZoneCode = aptReviewVO.getAptZoneCode();
			/** 단지코드 */
			String aptBlockCode = aptReviewVO.getAptBlockCode();
			/** 제목 */
			String aptReviewTitle = aptReviewVO.getAptReviewTitle();
			/** 내용 */
			String aptReviewContent = aptReviewVO.getAptReviewContent();
			/** 조회수 */
			int aptReviewClick = aptReviewVO.getAptReviewClick();
			/** 답변 게시물 그룹 */
			int aptReviewGroup = aptReviewVO.getAptReviewGroup();
			/** 답변 게시물 순서 */
			int aptReviewNum = aptReviewVO.getAptReviewNum();
			/** 답변 게시물 깊이 */
			int aptReviewDepth = aptReviewVO.getAptReviewDepth();
			/** 작성일 */
			String aptReviewCreateDate = aptReviewVO.getAptReviewCreateDate();
			/** 수정일 */
			String aptReviewUpdateDate = aptReviewVO.getAptReviewUpdateDate();
			
//			유효성 검사
			
			/** 후기게시판번호 */
			if(aptReviewNo == 0 || aptReviewNo < 0){
				LOGGER.info("aptReviewNo == 0 || aptReviewNo < 0");
			}
			
			/** 작성자 */
			if(memberNo == 0 || memberNo < 0){
				LOGGER.info("memberNo == 0 || memberNo < 0");
			}
			
			/** 지역코드 */
			if(aptZoneCode == null || aptZoneCode.equals("")){
				LOGGER.info("aptZoneCode == null || aptZoneCode.equals('')");
			}
			
			/** 단지코드 */
			if(aptBlockCode == null || aptBlockCode.equals("")){
				LOGGER.info("aptBlockCode == null || aptBlockCode.equals('')");
			}
			
			/** 제목 */
			if(aptReviewTitle == null || aptReviewTitle.equals("")){
				LOGGER.info("aptReviewTitle == null || aptReviewTitle.equals('')");
			}
			
			/** 내용 */
			if(aptReviewContent == null || aptReviewContent.equals("")){
				LOGGER.info("aptReviewContent == null || aptReviewContent.equals('')");
			}
			
			/** 조회수 */
			if(aptReviewClick == 0 || aptReviewClick < 0){
				LOGGER.info("aptReviewClick == 0 || aptReviewClick < 0");
			}
			
			/** 답변 게시물 그룹 */
			if(aptReviewGroup == 0 || aptReviewGroup < 0){
				LOGGER.info("aptReviewGroup == 0 || aptReviewGroup < 0");
			}
			
			/** 답변 게시물 순서 */
			if(aptReviewNum == 0 || aptReviewNum < 0){
				LOGGER.info("aptReviewNum == 0 || aptReviewNum < 0");
			}
			
			/** 답변 게시물 깊이 */
			if(aptReviewDepth == 0 || aptReviewDepth < 0){
				LOGGER.info("aptReviewDepth == 0 || aptReviewDepth < 0");
			}
			
			/** 작성일 */
			if(aptReviewCreateDate == null || aptReviewCreateDate.equals("")){
				LOGGER.info("aptReviewCreateDate == null || aptReviewCreateDate.equals('')");
			}
			
			/** 수정일 */
			if(aptReviewUpdateDate == null || aptReviewUpdateDate.equals("")){
				LOGGER.info("aptReviewUpdateDate == null || aptReviewUpdateDate.equals('')");
			}
		}//	aptReviewVO 객체 유효성검사 else End
		
//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);
		
//		후기게시판 게시글 데이터 입력
		int result; 
		try{
			result = tbAptReviewService.insertAptReview(aptReviewVO, request);
			LOGGER.info("TBAptReviewController insertAptReview result: "+result);
		}catch(Exception e){
			result = -1;
			LOGGER.info("TBAptReviewController insertAptReview Exception result: "+result);
			e.printStackTrace();
		}
		
//		입력결과 확인
		if(result >= 1){
		model.addAttribute("insertResult",result);

//		@Controller insertAptReview() 아웃로그
		LOGGER.info("@Controller insertAptReview() Out");
		
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
	 * 후기게시판 답변 작성페이지 조회한다.
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "aptReviewRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAptReviewReplyForm.do", method = RequestMethod.POST)
	public String insertAptReviewReplyForm(DefaultVO defaultVO, ModelMap model, int aptReviewNo) throws Exception {
//		@Controller insertAptReviewReplyForm() 진입로그
		LOGGER.info("@Controller insertAptReviewReplyForm() In:  "+ aptReviewNo);
		
//		유효성 검사
		
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
			
//			유효성 검사
			
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
			if(pageIndex == 0 || pageIndex < 0){
				LOGGER.info("pageIndex == 0 || pageIndex < 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit < 0){
				LOGGER.info("pageUnit == 0 || pageUnit < 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize < 0){
				LOGGER.info("pageSize == 0 || pageSize < 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex < 0){
				LOGGER.info("firstIndex == 0 || firstIndex < 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex < 0){
				LOGGER.info("lastIndex == 0 || lastIndex < 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage < 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage < 0");
			}
		}//	defaultVO 객체 유효성검사 else End
			Map<String,String> AptReviewReplyList = tbAptReviewService.selectAptReviewReply(aptReviewNo);
			LOGGER.info("AptReviewReplyList:    "+AptReviewReplyList);
			String aptZoneCode = AptReviewReplyList.get("APT_ZONE_CODE");
			Map<String,String> aptZoneCodeList = tbAptReviewService.selectReplyZoneCode(aptZoneCode);
			
			String aptBlockCode = AptReviewReplyList.get("APT_BLOCK_CODE");
			Map<String,String> aptBlockCodeList = tbAptReviewService.selectReplyBlockCode(aptBlockCode);
			
			
			model.addAttribute("AptReviewReplyList", AptReviewReplyList);
//			지역 정보 저장
			model.addAttribute("aptZoneCodeList", aptZoneCodeList);
//			단지 정보 저장
			model.addAttribute("aptBlockCodeList", aptBlockCodeList);		
//			페이지 정보 저장
			model.addAttribute("defaultVO", defaultVO);
		
//		@Controller insertAptReviewReplyForm() 아웃로그
		LOGGER.info("@Controller insertAptReviewReplyForm() Out");
		
//		등록페이지 이동
		return "aptreview/aptReviewReplyRegister";
		
	}
	
	
	
	
	
	/**
	 * 후기게시판 답글 작성
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param aptreviewVO - 등록할 정보가 담긴 VO
	 * @param model
	 * @return "forward:/selectAptReviewList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAptReviewReply.do", method = RequestMethod.POST)
	public String insertAptReviewReply(DefaultVO defaultVO, ModelMap model, TBAptReviewVO aptReviewVO, HttpServletRequest request) throws Exception {
//		@Controller insertAptReviewReply() 진입로그
		LOGGER.info("@Controller insertAptReviewReply() In" + aptReviewVO);
		
//		유효성 검사
		
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
			
//			유효성 검사
			
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
			if(pageIndex == 0 || pageIndex < 0){
				LOGGER.info("pageIndex == 0 || pageIndex < 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit < 0){
				LOGGER.info("pageUnit == 0 || pageUnit < 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize < 0){
				LOGGER.info("pageSize == 0 || pageSize < 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex < 0){
				LOGGER.info("firstIndex == 0 || firstIndex < 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex < 0){
				LOGGER.info("lastIndex == 0 || lastIndex < 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage < 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage < 0");
			}
		}//	defaultVO 객체 유효성검사 else End
		
//		유효성 검사
		
		/** aptReviewVO 객체 */
		if(aptReviewVO==null || aptReviewVO.equals("")){
			LOGGER.info("aptReviewVO == null || aptReviewVO.equals('')");
		
		}else{
			
			/** 후기게시판번호 */
			int aptReviewNo = aptReviewVO.getAptReviewNo();
			/** 작성자 */
			int memberNo = aptReviewVO.getMemberNo();
			/** 지역코드 */
			String aptZoneCode = aptReviewVO.getAptZoneCode();
			/** 단지코드 */
			String aptBlockCode = aptReviewVO.getAptBlockCode();
			/** 제목 */
			String aptReviewTitle = aptReviewVO.getAptReviewTitle();
			/** 내용 */
			String aptReviewContent = aptReviewVO.getAptReviewContent();
			/** 조회수 */
			int aptReviewClick = aptReviewVO.getAptReviewClick();
			/** 답변 게시물 그룹 */
			int aptReviewGroup = aptReviewVO.getAptReviewGroup();
			/** 답변 게시물 순서 */
			int aptReviewNum = aptReviewVO.getAptReviewNum();
			/** 답변 게시물 깊이 */
			int aptReviewDepth = aptReviewVO.getAptReviewDepth();
			/** 작성일 */
			String aptReviewCreateDate = aptReviewVO.getAptReviewCreateDate();
			/** 수정일 */
			String aptReviewUpdateDate = aptReviewVO.getAptReviewUpdateDate();
			
//			유효성 검사
			
			/** 후기게시판번호 */
			if(aptReviewNo == 0 || aptReviewNo < 0){
				LOGGER.info("aptReviewNo == 0 || aptReviewNo < 0");
			}
			
			/** 작성자 */
			if(memberNo == 0 || memberNo < 0){
				LOGGER.info("memberNo == 0 || memberNo < 0");
			}
			
			/** 지역코드 */
			if(aptZoneCode == null || aptZoneCode.equals("")){
				LOGGER.info("aptZoneCode == null || aptZoneCode.equals('')");
			}
			
			/** 단지코드 */
			if(aptBlockCode == null || aptBlockCode.equals("")){
				LOGGER.info("aptBlockCode == null || aptBlockCode.equals('')");
			}
			
			/** 제목 */
			if(aptReviewTitle == null || aptReviewTitle.equals("")){
				LOGGER.info("aptReviewTitle == null || aptReviewTitle.equals('')");
			}
			
			/** 내용 */
			if(aptReviewContent == null || aptReviewContent.equals("")){
				LOGGER.info("aptReviewContent == null || aptReviewContent.equals('')");
			}
			
			/** 조회수 */
			if(aptReviewClick == 0 || aptReviewClick < 0){
				LOGGER.info("aptReviewClick == 0 || aptReviewClick < 0");
			}
			
			/** 답변 게시물 그룹 */
			if(aptReviewGroup == 0 || aptReviewGroup < 0){
				LOGGER.info("aptReviewGroup == 0 || aptReviewGroup < 0");
			}
			
			/** 답변 게시물 순서 */
			if(aptReviewNum == 0 || aptReviewNum < 0){
				LOGGER.info("aptReviewNum == 0 || aptReviewNum < 0");
			}
			
			/** 답변 게시물 깊이 */
			if(aptReviewDepth == 0 || aptReviewDepth < 0){
				LOGGER.info("aptReviewDepth == 0 || aptReviewDepth < 0");
			}
			
			/** 작성일 */
			if(aptReviewCreateDate == null || aptReviewCreateDate.equals("")){
				LOGGER.info("aptReviewCreateDate == null || aptReviewCreateDate.equals('')");
			}
			
			/** 수정일 */
			if(aptReviewUpdateDate == null || aptReviewUpdateDate.equals("")){
				LOGGER.info("aptReviewUpdateDate == null || aptReviewUpdateDate.equals('')");
			}
		}//	aptReviewVO 객체 유효성검사 else End
		
//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);
		
//		후기게시판 게시글 데이터 입력
		int result; 
		try{
			result = tbAptReviewService.insertAptReviewReply(aptReviewVO, request);
			LOGGER.info("TBAptReviewController insertAptReviewReply result: "+result);
		}catch(Exception e){
			result = -1;
			LOGGER.info("TBAptReviewController insertAptReviewReply Exception result: "+result);
			e.printStackTrace();
		}
		
//		입력결과 확인
		if(result >= 1){
		model.addAttribute("insertResult",result);

//		@Controller insertAptReviewReply() 아웃로그
		LOGGER.info("@Controller insertAptReviewReply() Out");
		
//		후기게시판 목록으로 이동
		return "forward:/selectAptReviewList.do";
		
		}else{
			LOGGER.info("입력오류");
			result = -1;
			model.addAttribute("insertResult",result);
			return "forward:/insertAptReviewReplyForm.do";
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
	@RequestMapping(value = "/updateAptReviewForm.do", method = RequestMethod.POST)
	public String updateAptReviewForm(DefaultVO defaultVO, ModelMap model, int aptReviewNo) throws Exception {
//		@RequestMapping updateAptReviewForm() 진입로그
		LOGGER.info("@RequestMapping updateAptReviewForm() in");

//		유효성 검사
		
		/** 현재페이지 번호 */
		if(aptReviewNo == 0 || aptReviewNo < 0){
			LOGGER.info("aptReviewNo == 0 || aptReviewNo < 0");
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
			
//			유효성 검사
			
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
			if(pageIndex == 0 || pageIndex < 0){
				LOGGER.info("pageIndex == 0 || pageIndex < 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit < 0){
				LOGGER.info("pageUnit == 0 || pageUnit < 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize < 0){
				LOGGER.info("pageSize == 0 || pageSize < 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex < 0){
				LOGGER.info("firstIndex == 0 || firstIndex < 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex < 0){
				LOGGER.info("lastIndex == 0 || lastIndex < 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage < 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage < 0");
			}
		}	
		/** defaultVO 객체 유효성검사 else End */

//		모집공고번호 기준 데이터 조회
//		List<?> List = tbAptReviewService.selectAptReviewView(aptReviewNo);
		HashMap<?, ?> Map = tbAptReviewService.selectAptReviewView(aptReviewNo);
//		모집공고번호 기준 데이터 저장
		model.addAttribute("updateAptReviewForm", Map);
		
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
		
//		유효성 검사
		
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
			
//			유효성 검사
			
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
			if(pageIndex == 0 || pageIndex < 0){
				LOGGER.info("pageIndex == 0 || pageIndex < 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit < 0){
				LOGGER.info("pageUnit == 0 || pageUnit < 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize < 0){
				LOGGER.info("pageSize == 0 || pageSize < 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex < 0){
				LOGGER.info("firstIndex == 0 || firstIndex < 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex < 0){
				LOGGER.info("lastIndex == 0 || lastIndex < 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage < 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage < 0");
			}
		}	
		/** defaultVO 객체 유효성검사 else End */

//		유효성 검사
		
		/** aptReviewVO 객체 */
		if(aptReviewVO==null || aptReviewVO.equals("")){
			LOGGER.info("aptReviewVO == null || aptReviewVO.equals('')");
		
		}else{
			
			/** 후기게시판번호 */
			int aptReviewNo = aptReviewVO.getAptReviewNo();
			/** 작성자 */
			int memberNo = aptReviewVO.getMemberNo();
			/** 지역코드 */
			String aptZoneCode = aptReviewVO.getAptZoneCode();
			/** 단지코드 */
			String aptBlockCode = aptReviewVO.getAptBlockCode();
			/** 제목 */
			String aptReviewTitle = aptReviewVO.getAptReviewTitle();
			/** 내용 */
			String aptReviewContent = aptReviewVO.getAptReviewContent();
			/** 조회수 */
			int aptReviewClick = aptReviewVO.getAptReviewClick();
			/** 답변 게시물 그룹 */
			int aptReviewGroup = aptReviewVO.getAptReviewGroup();
			/** 답변 게시물 순서 */
			int aptReviewNum = aptReviewVO.getAptReviewNum();
			/** 답변 게시물 깊이 */
			int aptReviewDepth = aptReviewVO.getAptReviewDepth();
			/** 작성일 */
			String aptReviewCreateDate = aptReviewVO.getAptReviewCreateDate();
			/** 수정일 */
			String aptReviewUpdateDate = aptReviewVO.getAptReviewUpdateDate();
			
//			유효성 검사
			
			/** 후기게시판번호 */
			if(aptReviewNo == 0 || aptReviewNo < 0){
				LOGGER.info("aptReviewNo == 0 || aptReviewNo < 0");
			}
			
			/** 작성자 */
			if(memberNo == 0 || memberNo < 0){
				LOGGER.info("memberNo == 0 || memberNo < 0");
			}
			
			/** 지역코드 */
			if(aptZoneCode == null || aptZoneCode.equals("")){
				LOGGER.info("aptZoneCode == null || aptZoneCode.equals('')");
			}
			
			/** 단지코드 */
			if(aptBlockCode == null || aptBlockCode.equals("")){
				LOGGER.info("aptBlockCode == null || aptBlockCode.equals('')");
			}
			
			/** 제목 */
			if(aptReviewTitle == null || aptReviewTitle.equals("")){
				LOGGER.info("aptReviewTitle == null || aptReviewTitle.equals('')");
			}
			
			/** 내용 */
			if(aptReviewContent == null || aptReviewContent.equals("")){
				LOGGER.info("aptReviewContent == null || aptReviewContent.equals('')");
			}
			
			/** 조회수 */
			if(aptReviewClick == 0 || aptReviewClick < 0){
				LOGGER.info("aptReviewClick == 0 || aptReviewClick < 0");
			}
			
			/** 답변 게시물 그룹 */
			if(aptReviewGroup == 0 || aptReviewGroup < 0){
				LOGGER.info("aptReviewGroup == 0 || aptReviewGroup < 0");
			}
			
			/** 답변 게시물 순서 */
			if(aptReviewNum == 0 || aptReviewNum < 0){
				LOGGER.info("aptReviewNum == 0 || aptReviewNum < 0");
			}
			
			/** 답변 게시물 깊이 */
			if(aptReviewDepth == 0 || aptReviewDepth < 0){
				LOGGER.info("aptReviewDepth == 0 || aptReviewDepth < 0");
			}
			
			/** 작성일 */
			if(aptReviewCreateDate == null || aptReviewCreateDate.equals("")){
				LOGGER.info("aptReviewCreateDate == null || aptReviewCreateDate.equals('')");
			}
			
			/** 수정일 */
			if(aptReviewUpdateDate == null || aptReviewUpdateDate.equals("")){
				LOGGER.info("aptReviewUpdateDate == null || aptReviewUpdateDate.equals('')");
			}
		}//	aptReviewVO 객체 유효성검사 else End
		
//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);	
		
//		후기게시판 게시글 수정
		int result; 
		try{
			result = tbAptReviewService.updateAptReview(aptReviewVO);
			LOGGER.info("TBAptReviewController updateAptReview result: "+result);
		}catch(Exception e){
			result = -1;
			LOGGER.info("TBAptReviewController updateAptReview Exception result: "+result);
			e.printStackTrace();
		}
		
//		수정결과 확인
		if(result >= 1){
		model.addAttribute("updateResult",result);

//		@RequestMapping updateAptReview() 아웃로그
		LOGGER.info("@RequestMapping updateAptReview() Out");
		
//		후기게시판 목록으로 이동
		return "forward:/selectAptReviewList.do";
		
		}else{
			LOGGER.info("수정오류");
			result = -1;
			model.addAttribute("updateResult",result);
			return "forward:/updateAptReviewForm.do";
		}
		
}
	
	
	
	
	
	/**
	 * 글을 삭제한다.
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @param aptReviewNo - 삭제 기준 번호
	 * @return "forward:/selectAptReviewList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteAptReview.do", method = RequestMethod.POST)
	public String deleteAptReview(DefaultVO defaultVO, ModelMap model, int aptReviewNo) throws Exception {
//		@RequestMapping deleteAptReview() 진입로그
		LOGGER.info("@RequestMapping deleteAptReview() In" + aptReviewNo);
		
//		유효성 검사
		
		/** 현재페이지 번호 */
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
			
//			유효성 검사
			
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
			if(pageIndex == 0 || pageIndex < 0){
				LOGGER.info("pageIndex == 0 || pageIndex < 0");
			}
			/** 페이지갯수 */
			if(pageUnit == 0 || pageUnit < 0){
				LOGGER.info("pageUnit == 0 || pageUnit < 0");
			}
			/** 페이지사이즈 */
			if(pageSize == 0 || pageSize < 0){
				LOGGER.info("pageSize == 0 || pageSize < 0");
			}
			/** firstIndex */
			if(firstIndex == 0 || firstIndex < 0){
				LOGGER.info("firstIndex == 0 || firstIndex < 0");
			}
			/** lastIndex */
			if(lastIndex == 0 || lastIndex < 0){
				LOGGER.info("lastIndex == 0 || lastIndex < 0");
			}
			/** recordCountPerPage */
			if(recordCountPerPage == 0 || recordCountPerPage < 0){
				LOGGER.info("recordCountPerPage == 0 || recordCountPerPage < 0");
			}
		}	
		/** defaultVO 객체 유효성검사 else End */
		
		
//		페이지 정보 저장
		model.addAttribute("defaultVO", defaultVO);		
		
//		후기게시판 게시글 삭제
		int result; 
		try{
			result = tbAptReviewService.deleteAptReview(aptReviewNo);
			LOGGER.info("TBAptReviewController deleteAptReview result: "+result);
		}catch(Exception e){
			result = -1;
			LOGGER.info("TBAptReviewController deleteAptReview Exception result: "+result);
			e.printStackTrace();
		}
		
//		입력결과 확인
		if(result >= 1){
		model.addAttribute("deleteResult",result);

//		@RequestMapping deleteAptReview() 아웃로그
		LOGGER.info("@RequestMapping deleteAptReview() Out");
		
		//게시글 조회페이지로 이동
		return "forward:/selectAptReviewList.do";
		
		}else{
			LOGGER.info("삭제오류");
			result = -1;
			model.addAttribute("deleteResult",result);
			return "forward:/selectAptReviewList.do";
		}

	}

	
	
	
	
	

	
	
	@RequestMapping(value = "/ajaxTest.do", method = RequestMethod.POST)
	public @ResponseBody JSONObject ajaxTest(@RequestParam Map<String, String> aptZoneCode) throws Exception{
		LOGGER.info("@RequestMapping test() In:   "+aptZoneCode);

		JSONObject result = new JSONObject();
//		String resultCode = "";
//		String resultMsg = "";
//		Object[] args = null;
		
		String aptBlockCode = tbAptReviewService.selectBlockCode2(aptZoneCode);
		String aptBlockCodeValue = tbAptReviewService.selectBlockCodeValue(aptZoneCode);
		result.put(aptBlockCode, aptBlockCodeValue);
		
		LOGGER.info("@RequestMapping test() Out:   "+result);
		
		return result;
	}

	
	@RequestMapping(value = "/aptPlanImage.do", method = RequestMethod.POST)
	public @ResponseBody JSONObject aptPlanImage(@RequestParam Map<String, String> aptBlockCodeValue) throws Exception{
		LOGGER.info("@RequestMapping test() In:   "+aptBlockCodeValue);

		JSONObject result = new JSONObject();
//		String resultCode = "";
//		String resultMsg = "";
//		Object[] args = null;
		
		String aptPlanCodeValue = tbAptReviewService.selectPlanCodeValue();
		result.put("aptPlanCodeValue", aptPlanCodeValue);
		
		LOGGER.info("@RequestMapping test() Out:   "+result);
		
		return result;
	}
	
//	@RequestMapping(value = "/ajaxTest.do", method = RequestMethod.POST)
//	public @ResponseBody String ajaxTest(@RequestParam("aptZoneCode") String params) throws Exception{
//		LOGGER.info("@RequestMapping test() In:   "+params);
//		
//		List<TBBlockCodeVO> result= tbAptReviewService.selectBlockCode2(params);
//		Map<String, String> map = tbAptReviewService.selectBlockCode3(params);
//		
//		LOGGER.info("@RequestMapping test() Out:   "+result);
//		LOGGER.info("@RequestMapping test() map Out:   "+map);
//		return params;
//	}
	

//	@RequestMapping(value = "/ajaxTest.do", method = RequestMethod.POST)
//	public @ResponseBody TBBlockCodeVO ajaxTest(@RequestParam("aptZoneCode") TBBlockCodeVO params) throws Exception{
//		LOGGER.info("@RequestMapping test() In:   "+params);
//
//		TBBlockCodeVO result = tbAptReviewService.selectBlockCode2(params);
//		
//		LOGGER.info("@RequestMapping test() Out:   "+result);
//		
//		return params;
//	}
	
	


	
}//Controller End
