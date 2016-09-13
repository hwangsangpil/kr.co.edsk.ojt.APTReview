package kr.co.edsk.ojt.APTReview.service;

import java.util.List;

import kr.co.edsk.ojt.APTReview.vo.DefaultVO;
import kr.co.edsk.ojt.APTReview.vo.TBAptReviewVO;
import kr.co.edsk.ojt.APTReview.vo.TBBlockCodeVO;
import kr.co.edsk.ojt.APTReview.vo.TBZoneCodeVO;

/**
 * @Class Name : TBAptReviewService.java
 * @Description : TBAptReviewService interface
 * @Modification Information
 * @
 * @  수정일      	  	    수정자         			수정내용
 * @ ---------   	---------   -------------------------------
 * @ 20016.08.17  	    황상필         			최초생성
 *
 * @author 한국이디에스 황상필
 * @since 2016.08.17
 * @version 1.0
 * @see
 */

public interface TBAptReviewService {
	
	/**
	 * 후기게시판 전체목록 보기
	 * @param defaultVO - 조회할 정보가 담긴 DefaultVO
	 * @return 글 목록
	 * @exception Exception
	 */
	public List<DefaultVO> selectAptReviewList(DefaultVO defaultVO) throws Exception;
	
	/**
	 * 후기게시판 총 갯수를 조회한다.
	 * @param defaultVO - 조회할 정보가 담긴 DefaultVO
	 * @return 글 총 갯수
	 * @exception
	 */
	public int selectAptReviewListTotalCount(DefaultVO defaultVO) throws Exception;
	
	/**
	 * @param zoneCodeVO
	 * @return
	 * @throws Exception
	 */
	public List<TBZoneCodeVO> selectZoneCode(TBZoneCodeVO zoneCodeVO) throws Exception;
	
	/**
	 * @param blockCodeVO
	 * @return
	 * @throws Exception
	 */
	public TBBlockCodeVO selectBlockCode(TBBlockCodeVO blockCodeVO) throws Exception;
	
	/**
	 * 후기게시판 작성
	 * @param aptReviewVO - 등록할 정보가 담긴 TBAptReviewVO
	 * @return void형
	 * @exception Exception
	 */
	public int insertAptReview(TBAptReviewVO aptReviewVO) throws Exception;

	/**
	 * 후기게시판 수정
	 * @param aptReviewVO - 수정할 정보가 담긴 TBAptReviewVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateAptReview(TBAptReviewVO aptReviewVO) throws Exception;
	
	/**
	 * 후기게시판을 삭제
	 * @param aptReviewVO - 삭제할 정보가 담긴 aptReviewVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteAptReview(int aptReviewNo) throws Exception;
	
	/**
	 * 후기게시판 상세 보기
	 * @param aptReviewVO - 조회할 정보가 담긴 TBAptReviewVO
	 * @return 상세보기할 aptReviewVO
	 * @exception Exception
	 */
	public TBAptReviewVO selectAptReviewView(int aptReviewNo) throws Exception;
	
	
}
