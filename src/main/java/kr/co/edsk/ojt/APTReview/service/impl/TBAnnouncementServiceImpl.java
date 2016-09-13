package kr.co.edsk.ojt.APTReview.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.co.edsk.ojt.APTReview.mapper.TBAnnouncementMapper;
import kr.co.edsk.ojt.APTReview.service.TBAnnouncementService;
import kr.co.edsk.ojt.APTReview.vo.DefaultVO;
import kr.co.edsk.ojt.APTReview.vo.TBAnnouncementVO;
import kr.co.edsk.ojt.APTReview.vo.TBZoneCodeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Class Name : TBAnnouncementServiceImpl.java
 * @Description : TBAnnouncementService class
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
 */

@Service("tbAnnouncementService")
public class TBAnnouncementServiceImpl extends EgovAbstractServiceImpl implements TBAnnouncementService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TBAnnouncementServiceImpl.class);
	
	/** mybatis 사용 */
	@Resource(name="tbAnnouncementMapper")
	private TBAnnouncementMapper tbAnnouncementMapper;
	
	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	/* (non-Javadoc)
	 * @see kr.co.edsk.ojt.APTReview.service.TBAnnouncementService#selectAnnouncementList(kr.co.edsk.ojt.APTReview.vo.DefaultVO)
	 */
	/**
	 * 모집공고 전체목록 보기
	 * @param defaultVO - 조회할 정보가 담긴 DefaultVO
	 * @return List - 글 목록
	 * @exception Exception
	 */
	@Override
	public List<DefaultVO> selectAnnouncementList(DefaultVO defaultVO) throws Exception {
		if(defaultVO == null){
			throw processException("모집공고 목록 정보가 없습니다.");
		}
		try{
			int anNum = tbAnnouncementMapper.selectAnnouncementListTotalCount(defaultVO);
	
			if(anNum == 0){
				throw processException("모집공고가 목록이 없습니다.");
			}
		}catch(Exception e){
			e.getMessage();
		}finally{
			LOGGER.info("Service selectAnnouncementList Log : "+defaultVO.toString());			
		}
		return tbAnnouncementMapper.selectAnnouncementList(defaultVO);
	}

	/* (non-Javadoc)
	 * @see kr.co.edsk.ojt.APTReview.service.TBAnnouncementService#insertAnnouncement(kr.co.edsk.ojt.APTReview.vo.TBAnnouncementVO)
	 */
	/**
	 * 모집공고 작성
	 * @param announcementVO - 등록할 정보가 담긴 TBAnnouncementVO
	 * @return
	 * @exception Exception
	 */
	@Override
	public int insertAnnouncement(TBAnnouncementVO announcementVO)
			throws Exception {
		LOGGER.info("Service insertAnnouncement Log : "+announcementVO.toString());			
		return tbAnnouncementMapper.insertAnnouncement(announcementVO);
	}
	
	/* (non-Javadoc)
	 * @see kr.co.edsk.ojt.APTReview.service.TBAnnouncementService#updateAnnouncement(kr.co.edsk.ojt.APTReview.vo.TBAnnouncementVO)
	 */
	/**
	 * 모집공고 수정
	 * @param announcementVO - 수정할 정보가 담긴 TBAnnouncementVO
	 * @return 
	 * @exception Exception
	 */
	@Override
	public void updateAnnouncement(TBAnnouncementVO announcementVO)
			throws Exception {
			
		if(announcementVO == null){
			throw processException("수정하려는 모집공고가 없습니다.");
		}
		try{
			TBAnnouncementVO resultVO = tbAnnouncementMapper.selectAnnouncement(announcementVO);
			
			if(resultVO == null){
				throw processException("수정하려는 모집공고를 찾을 수 없습니다.");
			}
			tbAnnouncementMapper.updateAnnouncement(announcementVO);
		}catch(Exception e){
			e.getMessage();
		}finally{
			LOGGER.debug("ServiceImpl : updateAnnouncement");
		}
	}
	
	/* (non-Javadoc)
	 * @see kr.co.edsk.ojt.APTReview.service.TBAnnouncementService#deleteAnnouncement(int)
	 */
	/**
	 * 모집공고를 삭제
	 * @param announcementNo - 삭제할 모집공고 글의 번호
	 * @return 
	 * @exception Exception
	 */
	/*@Transactional*/
	@Override
	public int deleteAnnouncement(int announcementNo)
			throws Exception {
		if(announcementNo == 0){
			throw processException("삭제하려는 모집공고가 없습니다.");
		}
		LOGGER.info("Service deleteAnnouncement Log : "+announcementNo);
		
		return tbAnnouncementMapper.deleteAnnouncement(announcementNo);
	}
	
	/* (non-Javadoc)
	 * @see kr.co.edsk.ojt.APTReview.service.TBAnnouncementService#selectAnnouncement(kr.co.edsk.ojt.APTReview.vo.TBAnnouncementVO)
	 */
	/**
	 * 모집공고 상세 보기
	 * @param announcementVO - 조회할 정보가 담긴 VO
	 * @return 상세보기할 announcementVO
	 * @exception Exception
	 */
	
//	@Transactional
	@Override
	public TBAnnouncementVO selectAnnouncement(TBAnnouncementVO announcementVO)
			throws Exception {
		
		if(announcementVO == null){
			throw processException("검색하려는 announcementVO 정보가 없습니다.");
		}
		try{
			selectAnnouncementClickHit(announcementVO.getAnnouncementNo());
			LOGGER.info("Service selectAnnouncement Log : "+announcementVO.toString());
			TBAnnouncementVO resultVO = tbAnnouncementMapper.selectAnnouncement(announcementVO);
			if(resultVO == null){
				throw processException("해당 모집공고가 없습니다.");
			}
		}catch(Exception e){
			e.getMessage();
		}finally{
			LOGGER.debug("ServiceImpl : selectAnnouncement");
		}
		return tbAnnouncementMapper.selectAnnouncement(announcementVO);

	}
	
	/* (non-Javadoc)
	 * @see kr.co.edsk.ojt.APTReview.service.TBAnnouncementService#selectAnnouncementListTotalCount(kr.co.edsk.ojt.APTReview.vo.DefaultVO)
	 */
	/**
	 * 모집공고 총 갯수를 조회한다.
	 * @param defaultVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	@Override
	public int selectAnnouncementListTotalCount(DefaultVO defaultVO)
			throws Exception {

		if(defaultVO == null){
			throw processException("defaultVO 정보가 없습니다.");
		}
		try{
			int	anNum = tbAnnouncementMapper.selectAnnouncementListTotalCount(defaultVO);	
			if(anNum == 0){
				throw processException("모집공고 글이 없습니다.");
			}
		}catch(Exception e){
			e.getMessage();
		}finally{
			LOGGER.debug("ServiceImpl : selectAnnouncementListTotalCount");
		}
		
		return tbAnnouncementMapper.selectAnnouncementListTotalCount(defaultVO);
	}
	
	/* (non-Javadoc)
	 * @see kr.co.edsk.ojt.APTReview.service.TBAnnouncementService#selectAnnouncementClickHit(int)
	 */
	/**
	 * 해당 모집공고 글의 조회수를 증가한다.
	 * @param announcementNo - 조회수가 증가될 모집공고의 글번호
	 * @return void형
	 * @exception
	 */
	@Override
	public void selectAnnouncementClickHit(int announcementNo) throws Exception{
		if(announcementNo == 0){
			throw processException("해당 모집공고글이 존재하지 않습니다.");
		}
		try{
			tbAnnouncementMapper.selectAnnouncementClickHit(announcementNo);
		}catch(Exception e){
			e.getMessage();
		}finally{
			LOGGER.debug("ServiceImpl : SelectAnnouncementClickHit");
		}
	}
	
	/* (non-Javadoc)
	 * @see kr.co.edsk.ojt.APTReview.service.TBAptReviewService#selectZoneCode(kr.co.edsk.ojt.APTReview.vo.TBZoneCodeVO)
	 */
	/**
	 * 지역코드를 조회한다.
	 * @param zoneCodeVO - 지역 코드와 값이 담긴 VO
	 * @return list - 지역 코드와 값을 list형으로 return
	 * @exception
	 */
	@Override
	public List<TBZoneCodeVO> selectZoneCode(TBZoneCodeVO zoneCodeVO)
			throws Exception {
		return tbAnnouncementMapper.selectZoneCode(zoneCodeVO);
	}
	
}
