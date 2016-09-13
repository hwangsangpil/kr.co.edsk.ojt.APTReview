package kr.co.edsk.ojt.APTReview.service;

import java.util.List;
import java.util.Map;

import kr.co.edsk.ojt.APTReview.vo.DefaultVO;
import kr.co.edsk.ojt.APTReview.vo.TBAnnouncementVO;
import kr.co.edsk.ojt.APTReview.vo.TBZoneCodeVO;

/**
 * @Class Name : TBAnnouncementService.java
 * @Description : TBAnnouncementService interface
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
public interface TBAnnouncementService {
	
	/**
	 * 모집공고 작성
	 * @param announcementVO - 등록할 정보가 담긴 TBAnnouncementVO
	 * @return void형
	 * @exception Exception
	 */
	public int insertAnnouncement(TBAnnouncementVO announcementVO) throws Exception;
	
	/**
	 * 모집공고 수정
	 * @param announcementVO - 수정할 정보가 담긴 TBAnnouncementVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateAnnouncement(TBAnnouncementVO announcementVO) throws Exception;
	
	/**
	 * 모집공고를 삭제
	 * @param announcementNo - 삭제할 글의 번호 
	 * @return void형
	 * @exception Exception
	 */
	public int deleteAnnouncement(int announcementNo) throws Exception;
	
	/**
	 * 모집공고 상세 보기
	 * @param announcementVO - 조회할 정보가 담긴 VO
	 * @return 상세보기할 정보의 announcementVO
	 * @exception Exception
	 */
	public TBAnnouncementVO selectAnnouncement(TBAnnouncementVO announcementVO) throws Exception;
	
	/**
	 * 모집공고 전체목록 보기
	 * @param defaultVO - 조회할 정보가 담긴 DefaultVO
	 * @return List - 글 목록
	 * @exception Exception
	 */
	public List<DefaultVO> selectAnnouncementList(DefaultVO defaultVO) throws Exception;
	
	/**
	 * 모집공고 총 갯수를 조회한다.
	 * @param defaultVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	public int selectAnnouncementListTotalCount(DefaultVO defaultVO) throws Exception;

	/**
	 * 모집공고글의 조회수를 증가한다.
	 * @param announcementNo - 조회하여 조회수를 증가시킬 모집공고글의 번호
	 * @return void형
	 * @exception
	 */
	public void selectAnnouncementClickHit(int announcementNo) throws Exception;
	
	/**
	 * @param zoneCodeVO - 지역 코드와 값이 담긴 VO
	 * @return list - 지역 코드와 값을 list형으로 return
	 * @throws Exception
	 */
	public List<TBZoneCodeVO> selectZoneCode(TBZoneCodeVO zoneCodeVO) throws Exception;

}
