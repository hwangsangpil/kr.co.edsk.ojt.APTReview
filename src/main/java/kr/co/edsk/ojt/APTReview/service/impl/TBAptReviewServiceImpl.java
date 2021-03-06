package kr.co.edsk.ojt.APTReview.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.co.edsk.ojt.APTReview.mapper.TBAptReviewMapper;
import kr.co.edsk.ojt.APTReview.service.TBAptReviewService;
import kr.co.edsk.ojt.APTReview.util.FileUtils;
import kr.co.edsk.ojt.APTReview.vo.DefaultVO;
import kr.co.edsk.ojt.APTReview.vo.TBAptReviewVO;
import kr.co.edsk.ojt.APTReview.vo.TBBlockCodeVO;
import kr.co.edsk.ojt.APTReview.vo.TBZoneCodeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Class Name : TBAptReviewServiceImpl.java
 * @Description : TBAptReviewServiceImpl class
 * @Modification Information
 * @
 * @  수정일      	  	   수정자         			수정내용
 * @ ---------   	---------   -------------------------------
 * @ 20016.08.17  	   황상필         			최초생성
 *
 * @author 한국이디에스 황상필
 * @since 2016.08.17
 * @version 1.0
 * @see
 * 
 * 로그레벨은
	DEBUG > INFO > WARN > ERROR > FATAL 순 입니다.

	# TRACE : 가장 상세한 정보를 나타낼 때 사용한다.
	# DEBUG : 일반 정보를 상세히 나타낼 때 사용한다.
	# INFO : 일반 정보를 나타낼 때 사용한다.
	# WARN : 에러는 아니지만 주의할 필요가 있을 때 사용한다.
	# ERROR : 일반 에러가 일어 났을 때 사용한다.
	# FATAL : 가장 크리티컬한 에러가 일어 났을 때 사용한다.

 * 
 * 
 * 
 */

@Service("tbAptReviewService")
public class TBAptReviewServiceImpl extends EgovAbstractServiceImpl implements TBAptReviewService{
	
	/** lof4j 사용 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TBAptReviewServiceImpl.class);
	
	/** mybatis 사용 */
	@Resource(name = "tbAptReviewMapper")
	private TBAptReviewMapper tbAptReviewMapper;
	
	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;
	
	@Override
	public List<Map<String, String>> selectAptReviewHomeList(DefaultVO defaultVO) throws Exception {
		return tbAptReviewMapper.selectAptReviewHomeList(defaultVO);
	}
	
	@Override
	public List<Map<String, String>> selectAnnouncementHomeList(DefaultVO defaultVO) throws Exception {
		return tbAptReviewMapper.selectAnnouncementHomeList(defaultVO);
	}
	
	/**
	 * 후기게시판 전체목록 보기
	 * @param defaultVO - 조회할 정보가 담긴 DefaultVO
	 * @return List - 글 목록
	 * @exception Exception
	 */
	@Override
	public List<DefaultVO> selectAptReviewList(DefaultVO defaultVO) throws Exception {
		return tbAptReviewMapper.selectAptReviewList(defaultVO);
	}
	
	/**
	 * 후기게시판 총 갯수를 조회한다.
	 * @param defaultVO - 조회할 정보가 담긴 DefaultVO
	 * @return 글 총 갯수
	 * @exception
	 */
	@Override
	public int selectAptReviewListTotalCount(DefaultVO defaultVO) throws Exception {
		return tbAptReviewMapper.selectAptReviewListTotalCount(defaultVO);
	}
	
	/* (non-Javadoc)
	 * @see kr.co.edsk.ojt.APTReview.service.TBAptReviewService#selectZoneCode(kr.co.edsk.ojt.APTReview.vo.TBZoneCodeVO)
	 */
	@Override
	public List<TBZoneCodeVO> selectZoneCode(TBZoneCodeVO zoneCodeVO)
			throws Exception {
		// TODO Auto-generated method stub
		return tbAptReviewMapper.selectZoneCode(zoneCodeVO);
	}
	
	/* (non-Javadoc)
	 * @see kr.co.edsk.ojt.APTReview.service.TBAptReviewService#selectBlockCode(kr.co.edsk.ojt.APTReview.vo.TBBlockCodeVO)
	 */
	@Override
	public TBBlockCodeVO selectBlockCode(TBBlockCodeVO blockCodeVO)
			throws Exception {
		// TODO Auto-generated method stub
		return tbAptReviewMapper.selectBlockCode(blockCodeVO);
	}
	@Override
	public String selectBlockCode2(Map<String, String> aptZoneCode)
			throws Exception {
		// TODO Auto-generated method stub
		return tbAptReviewMapper.selectBlockCode2(aptZoneCode);
	}
	@Override
	public String selectBlockCodeValue(Map<String, String> aptZoneCode)
			throws Exception {
		// TODO Auto-generated method stub
		return tbAptReviewMapper.selectBlockCodeValue(aptZoneCode);
	}
	
	@Override
	public String selectPlanCodeValue() throws Exception {
		// TODO Auto-generated method stub
		return tbAptReviewMapper.selectPlanCodeValue();
	}
	
	/**
	 * 후기게시판 작성
	 * @param aptReviewVO - 등록할 정보가 담긴 TBAptReviewVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public int insertAptReview(TBAptReviewVO aptReviewVO, HttpServletRequest request) throws Exception {
		
//		로그인된 사용자의 일련번호로 작성자를 자동입력하기 위한 테스트
//		작성자 번호 임의지정
//		memberNO 값으로 로그인 일련번호를 넣어준다 
		int memberNo = 2;
		
//		작성자번호 aptReviewVO 에 추가
		aptReviewVO.setMemberNo(memberNo);
		
//		후기게시판 정보 Database에 입력
		int result = tbAptReviewMapper.insertAptReview(aptReviewVO);
		int aptReviewNo = aptReviewVO.getAptReviewNo();		
		LOGGER.info("\n\n------------- aptReviewNo  -------------:  "+aptReviewNo+"\n");
			LOGGER.info("updateAptReviewGroup In");
			int result2 = tbAptReviewMapper.updateAptReviewGroup(aptReviewNo);
			LOGGER.info("updateAptReviewGroup:  "+result2);
		

//		Exception 발생시키기
//		String test = "12345";
//		aptReviewVO.setAptZoneCode(test);
//		result = tbAptReviewMapper.insertAptReview(aptReviewVO);
//		LOGGER.info("2 INSERT :   "+result);
			
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	    Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	    MultipartFile multipartFile = null;
	    
	    while(iterator.hasNext()){
	        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
	        if(multipartFile.isEmpty() == false){
	        	LOGGER.info("------------- file start -------------");
	        	LOGGER.info("name : "+multipartFile.getName());
	        	LOGGER.info("filename : "+multipartFile.getOriginalFilename());
	        	LOGGER.info("size : "+multipartFile.getSize());
	        	LOGGER.info("-------------- file end --------------\n");
	        }
	        if(multipartFile.isEmpty() == true){
	        	LOGGER.info("------------- file !start -------------");
	        	LOGGER.info("name : "+multipartFile.getName());
	        	LOGGER.info("filename : "+multipartFile.getOriginalFilename());
	        	LOGGER.info("size : "+multipartFile.getSize());
	        	LOGGER.info("-------------- file !end --------------\n");
	        }
	        
	    }
		
	    List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(aptReviewNo, request);
	    LOGGER.info("-------------- file !end --------------\n"+list);
		    for(int i=0, size=list.size(); i<size; i++){
		    	tbAptReviewMapper.insertAptReviewFile(list.get(i));
	    }
	    
//		입력결과 로그
		LOGGER.info("TBAptReviewServiceImpl insertAptReview result: "+result+" --- :"+aptReviewNo);
		
//		결과값 리턴
		return result;
	}
	
	
	
	
	
	@Override
	public int insertAptReviewReply(TBAptReviewVO aptReviewVO,
			HttpServletRequest request) throws Exception {

//		로그인된 사용자의 일련번호로 작성자를 자동입력하기 위한 테스트
//		작성자 번호 임의지정
//		memberNO 값으로 로그인 일련번호를 넣어준다 
		int memberNo = 2;
		
//		작성자번호 aptReviewVO 에 추가
		aptReviewVO.setMemberNo(memberNo);
		
		tbAptReviewMapper.updateAptReviewReplyNum(aptReviewVO);
		int aptReviewGroup = tbAptReviewMapper.selectAptReviewReplyFileGroup(aptReviewVO);
		LOGGER.info("\n\n------------- aptReviewGroup  -------------:  "+aptReviewGroup+"\n");
		tbAptReviewMapper.updateAptReviewReplyFileNum(aptReviewGroup);
		
		String aptReviewTitle = aptReviewVO.getAptReviewTitle();
		if(aptReviewTitle.contains("re:")){
			aptReviewVO.setAptReviewDepth(1);
		}
		if(aptReviewTitle.contains("re:re:")){
			aptReviewVO.setAptReviewDepth(2);
		}
		
//		후기게시판 정보 Database에 입력
		int result = tbAptReviewMapper.insertAptReviewReply(aptReviewVO);
		int aptReviewNo = aptReviewVO.getAptReviewNo();		
		LOGGER.info("\n\n------------- aptReviewNo  -------------:  "+aptReviewNo+"\n");
		

//		Exception 발생시키기
//		String test = "12345";
//		aptReviewVO.setAptZoneCode(test);
//		result = tbAptReviewMapper.insertAptReview(aptReviewVO);
//		LOGGER.info("2 INSERT :   "+result);
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	    Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	    MultipartFile multipartFile = null;
	    while(iterator.hasNext()){
	        multipartFile = multipartHttpServletRequest.getFile(iterator.next());
	        if(multipartFile.isEmpty() == false){
	        	LOGGER.info("------------- file start -------------");
	        	LOGGER.info("name : "+multipartFile.getName());
	        	LOGGER.info("filename : "+multipartFile.getOriginalFilename());
	        	LOGGER.info("size : "+multipartFile.getSize());
	        	LOGGER.info("-------------- file end --------------\n");
	        }
	    }
		
	    List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(aptReviewNo, request);
		    for(int i=0, size=list.size(); i<size; i++){
		    	tbAptReviewMapper.insertAptReviewReplyFile(list.get(i));
	        }
	    
//		입력결과 로그
		LOGGER.info("TBAptReviewServiceImpl insertAptReview result: "+result+" --- :"+aptReviewNo);
		
//		결과값 리턴
		return result;
		
	}
	
	
	
	
	
	
	/**
	 * 후기게시판 수정
	 * @param aptReviewVO - 수정할 정보가 담긴 TBAptReviewVO
	 * @return 수정한 TBAptReviewVO
	 * @exception Exception
	 */
	@Override
	public int updateAptReview(TBAptReviewVO aptReviewVO) throws Exception {

//		후기게시판 정보 Database에 입력
		int result = tbAptReviewMapper.updateAptReview(aptReviewVO);

//		Exception 발생시키기
//		int test = 500;
//		aptReviewVO.setAptReviewNo(test);
//		result = tbAptReviewMapper.updateAptReview(aptReviewVO);
//		if(result == 0){
//			LOGGER.info("updateAptReview 강제예외 발생 :   "+result);
//			throw new Exception();
//		}		
		
				
//		입력결과 로그
		LOGGER.info("TBAptReviewServiceImpl updateAptReview result: "+result);
		
//		결과값 리턴
		return result;
	}

	
	/**
	 * 후기게시판을 삭제
	 * @param aptReviewNo - 삭제할 정보가 담긴 TBAptReviewVO
	 * @return 삭제한 TBAptReviewVO
	 * @exception Exception
	 */
	@Override
	public int deleteAptReview(int aptReviewNo) throws Exception {
		
//		후기게시판 정보 Database에 입력
		int result = tbAptReviewMapper.deleteAptReview(aptReviewNo);
				
//		Exception 발생시키기
//		aptReviewNo=500;
//		result = tbAptReviewMapper.deleteAptReview(aptReviewNo);
//		if(result == 0){
//			LOGGER.info("deleteAptReview 강제예외 발생 :   "+result);
//			throw new Exception();
//		}
				
//		입력결과 로그
		LOGGER.info("TBAptReviewServiceImpl deleteAptReview result: "+result);
		
//		결과값 리턴
		return result;
	}
	

	
	/**
	 * 후기게시판 상세 보기
	 * @param aptReviewVO - 조회할 정보가 담긴 TBAptReviewVO
	 * @return 상세보기할 TBAptReviewVO
	 * @exception Exception
	 */
	@Override
	public HashMap<?, ?> selectAptReviewView(int aptReviewNo) throws Exception {
		return tbAptReviewMapper.selectAptReviewView(aptReviewNo);
	}

	@Override
	public Map<String, String> selectAptReviewReply(int aptReviewNo)
			throws Exception {
		return tbAptReviewMapper.selectAptReviewReply(aptReviewNo);
	}

	@Override
	public Map<String, String> selectReplyZoneCode(String aptZoneCode)
			throws Exception {
		return tbAptReviewMapper.selectReplyZoneCode(aptZoneCode);
	}

	@Override
	public Map<String, String> selectReplyBlockCode(String aptBlockCode)
			throws Exception {
		return tbAptReviewMapper.selectReplyBlockCode(aptBlockCode);
	}





}
