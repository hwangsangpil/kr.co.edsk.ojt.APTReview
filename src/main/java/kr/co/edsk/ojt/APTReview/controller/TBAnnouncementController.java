package kr.co.edsk.ojt.APTReview.controller;

import java.security.MessageDigest;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.edsk.ojt.APTReview.service.TBAnnouncementService;
import kr.co.edsk.ojt.APTReview.vo.DefaultVO;
import kr.co.edsk.ojt.APTReview.vo.TBAnnouncementVO;
import kr.co.edsk.ojt.APTReview.vo.TBZoneCodeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : TBAnnouncementController.java
 * @Description : TBAnnouncementController Class
 * @Modification Information
 * @
 * @  수정일      	  수정자         수정내용
 * @ ---------   	---------   -------------------------------
 * @ 20016.08.17  	  장윤재         최초생성
 *
 * @author 한국이디에스 장윤재
 * @since 2016.08.17
 * @version 1.0
 * @see
 */
@Controller
public class TBAnnouncementController{
	
	/** AnnouncementService */
	@Resource(name = "tbAnnouncementService")
	private TBAnnouncementService tbAnnouncementService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TBAnnouncementController.class);

	/** 모집공고글 목록 조회(paging)
	 *  
	 * @param defaultVO - 조회할 정보가 담긴 DefaultVO
	 * @param model
	 * 					Model → Interface , ModelMap → 구현체
	 * 					Spring @mvc 이전에는 ModelAndView를 통해서 뷰 페이지를 전달
						Spring @mvc 이후에는 Model or ModelMap를 사용한다.
	 * @return "announcementList"
	 * @exception Exception
	 *  
	 *  */
	@RequestMapping(value = "/announcementList.do")
	public String selectTBAnnouncementList(@ModelAttribute("defaultVO") DefaultVO defaultVO, ModelMap model) 
			throws Exception {
		LOGGER.info("Controller 모집공고 리스트 : "+defaultVO.toString());
		/** 페이지갯수 */
		defaultVO.setPageUnit(propertiesService.getInt("pageUnit"));
		/** 페이지사이즈 */
		defaultVO.setPageSize(propertiesService.getInt("pageSize"));
		
		// pageing setting 
		PaginationInfo paginationInfo = new PaginationInfo();
		
		/** 현재페이지 */
		paginationInfo.setCurrentPageNo(defaultVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(defaultVO.getPageUnit());
		paginationInfo.setPageSize(defaultVO.getPageSize());

		defaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		defaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
		/** recordCountPerPage */
		defaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<DefaultVO> List = tbAnnouncementService.selectAnnouncementList(defaultVO);

		model.addAttribute("AnnouncementList", List);

		int totCnt = tbAnnouncementService.selectAnnouncementListTotalCount(defaultVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		return "announcement/announcementList";
		
	}

	/**
	 * 모집공고 작성페이지 조회한다.
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "announcementRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAnnouncementForm.do")
	public String insertAnnouncementForm(@ModelAttribute("defaultVO") DefaultVO defaultVO, Model model) throws Exception{
		TBZoneCodeVO zoneCodeVO = new TBZoneCodeVO();
		List<TBZoneCodeVO> zoneCodeVOList = tbAnnouncementService.selectZoneCode(zoneCodeVO);
		model.addAttribute("zoneCodeVO", zoneCodeVOList);
		model.addAttribute("announcementVO", new TBAnnouncementVO());
		return "announcement/announcementRegister";
	}


	public byte[] messageDigest(Object setName, String algorithm){
		try{
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(((String) setName).getBytes());
			return md.digest();
		}catch(java.security.NoSuchAlgorithmException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 모집공고글 작성
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param announcementVO - 등록할 정보가 담긴 VO
	 * @param model
	 * @param request
	 * @param session
	 * 		-sessionStatus의 setComplete 함수를 호출함으로서, 
	 * 		해당 컨트롤러에 선언되어 있던 모든 세션 어트리뷰트를 제거해준다
	 * @return "announcementRegisterOk"
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAnnouncement.do" , method = RequestMethod.POST)
	public String insertAnnouncement(@ModelAttribute("defaultVO") DefaultVO defaultVO, TBAnnouncementVO announcementVO, Model model, HttpServletRequest request, SessionStatus session)
		throws Exception{
		
			String announcementName = request.getParameter("announcementName");
			String announcementStart = request.getParameter("announcementStart");
			String announcementEnd = request.getParameter("announcementEnd");
			String announcementAddress = request.getParameter("announcementAddress");
			String announcementArea = request.getParameter("announcementArea");
			String announcementAllResident = request.getParameter("announcementAllResident");
			String announcementContents = request.getParameter("announcementContents");
			
			if(announcementName == null || announcementName == ""){
				LOGGER.info("announcementName(공고명)이 널이나 공백");
				return "announcement/announcementRegisterFail";
			}
			if(announcementName.length() > 100){
				LOGGER.info("announcementName이 100자 이상은 안되");
				return "announcement/announcementRegisterFail";
			}
			if(announcementStart == null || announcementStart == ""){
				LOGGER.info("announcementStart(공고일)이 널이나 공백");
				return "announcement/announcementRegisterFail";
			}
			if(announcementEnd == null || announcementEnd == ""){
				LOGGER.info("announcementEnd(마감일)이 널이나 공백");
				return "announcement/announcementRegisterFail";
			}
			if(announcementAddress == null || announcementAddress == ""){
				LOGGER.info("announcementAddress이 널이나 공백");
				return "announcement/announcementRegisterFail";
			}
			if(announcementAddress.length() > 100){
				LOGGER.info("announcementAddress(소재지)가 100자 이상 안되");
				return "announcement/announcementRegisterFail";
			}
			if(announcementArea == null || announcementArea == ""){
				LOGGER.info("announcementArea(전용면적)이 널이나 공백");
				return "announcement/announcementRegisterFail";
			}
			if(announcementAllResident == null || announcementAllResident == ""){
				LOGGER.info("announcementAllResident(총세대수)이 널이나 공백");
				return "announcement/announcementRegisterFail";
			}
			if(announcementContents == null || announcementContents == ""){
				LOGGER.info("announcementContents(내용)이 널이나 공백");
				return "announcement/announcementRegisterFail";
			}
				
			if(defaultVO == null || announcementVO == null || session == null){
				LOGGER.info("session, announcement, default가 널 ");
				return "announcement/announcementRegisterFail";
			}
			
			try{
				int insertCount = tbAnnouncementService.insertAnnouncement(announcementVO);
				model.addAttribute("insertCount", insertCount);
			}catch(Exception e){
				e.getMessage();
			}finally{
				LOGGER.info("insertController 테스트중" + announcementVO);
				session.setComplete();
			}
			
			return "announcement/announcementRegisterOk";
			
		}
	/**
	 * 모집공고 상세페이지
	 * @param announcementNo - 상세보기 할 모집공고 번호 announcementNo
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @param request - HttpServletRequest
	 * 		- request를 요청하여 session에 정보를 setAttribute하여 view 페이지에서 
	 * 		  정보를 사용하기 위해 사용
	 * @return "announcementDetailView"
	 * @exception Exception
	 */
	
	@RequestMapping(value="/selectDetailAnnouncementView.do")
	public String selectDetailAnnouncementView(@RequestParam("selectedAnnouncementNo") int announcementNo, @ModelAttribute("defaultVO") DefaultVO defaultVO, Model model, HttpServletRequest request) throws Exception {
		if(announcementNo == 0 || defaultVO == null){
			return "announcement/announcementList";
		}
		
		TBAnnouncementVO announcementVO = new TBAnnouncementVO();
		announcementVO.setAnnouncementNo(announcementNo);
		
		try{
			announcementVO = selectAnnouncement(announcementVO, defaultVO);
			request.getSession().setAttribute("announcementRequestVO", announcementVO);
			model.addAttribute("announcementDetailVO", announcementVO);
			
		}catch(Exception e){
			e.getMessage();
		}finally{
			LOGGER.info("Controller : selectDetailView" + announcementVO);
			
		}

		return "announcement/announcementDetailView";
	}
	
	/**
	 * 모집공고 게시글을 조회한다.
	 * @param announcementVO - 조회할 게시글 정보가 담긴 VO
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @return TBAnnouncementVO
	 * @exception Exception
	 */
	@RequestMapping(value = "/selectAnnouncement.do")
	public TBAnnouncementVO selectAnnouncement(TBAnnouncementVO announcementVO, @ModelAttribute("defaultVO") DefaultVO defaultVO) throws Exception {
		if(announcementVO == null || defaultVO == null){
			LOGGER.debug("selectAnnouncement 에서 null"); 
		}
		return tbAnnouncementService.selectAnnouncement(announcementVO);
	}

	/**
	 * 글을 삭제한다.
	 * @param announcementNo - 삭제할 모집공고 게시글의 공고번호
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @param request
	 * @return "announcementDeleteOk"
	 * @exception Exception
	 */
	@RequestMapping("/deleteAnnouncement.do")
	public String deleteAnnouncement(int announcementNo, @ModelAttribute("defaultVO") DefaultVO defaultVO, Model model, HttpServletRequest request) throws Exception {
		LOGGER.info(announcementNo+"글이 삭제 되었습니다.");
		if(announcementNo == 0 || defaultVO == null){
			return "announcement/announcementDeleteFail";
		}
		try{
			request.setAttribute("announcementNo", announcementNo);
			int deleteCount = tbAnnouncementService.deleteAnnouncement(announcementNo);
			model.addAttribute("deleteCount", deleteCount);
		}catch(Exception e){
			e.getMessage();
		}finally{
			LOGGER.debug(announcementNo+"모집공고 글 delete");
		}
		
		return "announcement/announcementDeleteOk";
	}
	
	/**
	 * 모집공고 수정페이지 조회한다.
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @param session
	 * @return "announcementUpdate"
	 * @exception Exception
	 */
	
	@RequestMapping("/updateAnnouncementForm.do")
	public String updateAnnouncementForm(@ModelAttribute("defaultVO") DefaultVO defaultVO, Model model, HttpSession session) throws Exception{
		TBZoneCodeVO zoneCodeVO = new TBZoneCodeVO();
		List<TBZoneCodeVO> zoneCodeVOList = tbAnnouncementService.selectZoneCode(zoneCodeVO);
		model.addAttribute("zoneCodeVO", zoneCodeVOList);
		try{
			model.addAttribute("announcementVO", session.getAttribute("announcementRequestVO"));
		}catch(Exception e){
			e.getMessage();
		}
		return "announcement/announcementUpdate";
	}
	
	
	/**
	 * 모집공고글 수정
	 * @param defaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param announcementVO - 수정할 정보가 담긴 VO
	 * @param model
	 * @param request
	 * @return "announcementUpdateOk"
	 * @exception Exception
	 */
	@RequestMapping("/updateAnnouncement.do")
	public String updateAnnouncement(@ModelAttribute("defaultVO") DefaultVO defaultVO, TBAnnouncementVO announcementVO, Model model, HttpServletRequest request)
			throws Exception{
		String announcementNo = request.getParameter("announcementNo");
		String memberNo = request.getParameter("memberNo");
		String announcementName = request.getParameter("announcementName");
		String announcementStart = request.getParameter("announcementStart");
		String announcementEnd = request.getParameter("announcementEnd");
		String announcementAddress = request.getParameter("announcementAddress");
		String announcementArea = request.getParameter("announcementArea");
		String announcementAllResident = request.getParameter("announcementAllResident");
		String announcementContents = request.getParameter("announcementContents");
		
		if(announcementNo == null || announcementNo == ""){
			LOGGER.info("announcementNo(공고번호)가 널이나 공백");
			return "announcement/announcementUpdateFail";
		}
		if(memberNo == null || memberNo == ""){
			LOGGER.info("memberNo(회원번호)가 널이나 공백");
			return "announcement/announcementUpdateFail";
		}
		
		if(announcementName == null || announcementName == ""){
			LOGGER.info("announcementName(공고명)이 널이나 공백");
			return "announcement/announcementUpdateFail";
		}
		if(announcementName.length() > 100){
			LOGGER.info("announcementName이 100자 이상은 안되");
			return "announcement/announcementUpdateFail";
		}
		if(announcementStart == null || announcementStart == ""){
			LOGGER.info("announcementStart(공고일)이 널이나 공백");
			return "announcement/announcementUpdateFail";
		}
		if(announcementEnd == null || announcementEnd == ""){
			LOGGER.info("announcementEnd(마감일)이 널이나 공백");
			return "announcement/announcementUpdateFail";
		}
		if(announcementAddress == null || announcementAddress == ""){
			LOGGER.info("announcementAddress이 널이나 공백");
			return "announcement/announcementUpdateFail";
		}
		if(announcementAddress.length() > 100){
			LOGGER.info("announcementAddress(소재지)가 100자 이상 안되");
			return "announcement/announcementUpdateFail";
		}
		if(announcementArea == null || announcementArea == ""){
			LOGGER.info("announcementArea(전용면적)이 널이나 공백");
			return "announcement/announcementUpdateFail";
		}
		if(announcementAllResident == null || announcementAllResident == ""){
			LOGGER.info("announcementAllResident(총세대수)이 널이나 공백");
			return "announcement/announcementUpdateFail";
		}
		if(announcementContents == null || announcementContents == ""){
			LOGGER.info("announcementContents(내용)이 널이나 공백");
			return "announcement/announcementUpdateFail";
		}
		if(defaultVO == null || announcementVO == null || request.getSession() == null){
			LOGGER.info("session, announcement, default가 널 ");
			return "announcement/announcementUpdateFail";
		}

		LOGGER.info("updateController 테스트중" + announcementVO);
		try{
			tbAnnouncementService.updateAnnouncement(announcementVO);
			model.addAttribute("selectedAnnouncementNo", announcementVO.getAnnouncementNo());
		}catch(Exception e){
			e.getMessage();
		}
		return "announcement/announcementUpdateOk";
	}
	

}
