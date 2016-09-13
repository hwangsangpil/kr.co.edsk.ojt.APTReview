/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.co.edsk.ojt.APTReview.mapper;

import java.util.List;
import java.util.Map;

import kr.co.edsk.ojt.APTReview.vo.DefaultVO;
import kr.co.edsk.ojt.APTReview.vo.TBAnnouncementVO;
import kr.co.edsk.ojt.APTReview.vo.TBZoneCodeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 모집공고(announcement)에 관한 데이터처리 매퍼 클래스
 *
 * @author  한국이디에스 장윤재
 * @since 2016.08.17
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *      수정일             수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *    2016.08.17           장윤재           최초 생성
 *
 * </pre>
 */
@Mapper("tbAnnouncementMapper")
public interface TBAnnouncementMapper {
	

	/**
	 * 모집공고 전체목록 보기
	 * @param defaultVO - 조회할 정보가 담긴 DefaultVO
	 * @return 글 목록	- List<DefaultVO>
	 * @exception Exception
	 */
	public List<DefaultVO> selectAnnouncementList(DefaultVO defaultVO) throws Exception;
	
	/**
	 * 모집공고 작성
	 * @param announcementVO - 등록할 정보가 담긴 TBAnnouncementVO
	 * @return int형 - 등록된 모집공고 갯수
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
	 * @param announcementVO - 삭제할 정보가 담긴 TBAnnouncementVO
	 * @return int형- 삭제된 모집공고 갯수
	 * @exception Exception
	 */
	public int deleteAnnouncement(int announcementNo) throws Exception;

	/**
	 * 모집공고 상세 보기
	 * @param announcementVO - 상세보기할 모집공고의 정보가 담긴 VO
	 * @return 상세보기할 announcementVO
	 * @exception Exception
	 */
	public TBAnnouncementVO selectAnnouncement(TBAnnouncementVO announcementVO) throws Exception;
	

	/**
	 * 모집공고 총 갯수를 조회한다.
	 * @param defaultVO - 조회할 정보가 담긴 VO
	 * @return int형 - 글 총 갯수
	 * @exception
	 */
	public int selectAnnouncementListTotalCount(DefaultVO defaultVO) throws Exception;
	
	
	/**
	 * 모집공고 조회수 증가.
	 * @param announcementNo - 조회수를 증가시킬 모집공고 글번호
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

