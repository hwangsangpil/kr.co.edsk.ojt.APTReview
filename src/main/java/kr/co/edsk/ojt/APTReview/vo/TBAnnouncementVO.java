package kr.co.edsk.ojt.APTReview.vo;

import java.io.Serializable;

/**
 * @Class Name : TBAnnouncementVO.java
 * @Description : TBAnnouncementVO class
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
public class TBAnnouncementVO implements Serializable{
	
	/** serialVersion UID */
	private static final long serialVersionUID = -8836677171133325534L;
	
	/**공고번호*/
	private int announcementNo;
	
	/**작성자 회원번호*/
	/*private TBMemberVO tbmemberVO;*/
	private int memberNo;
	
	/**지역코드*/
	private String aptZoneCode;
	
	/**공고명*/
	private String announcementName;
	
	/**공고일*/
	private String announcementStart;
	
	/**마감일*/
	private String announcementEnd;
	
	/**소재지*/
	private String announcementAddress;
	
	/**전용면적*/
	private String announcementArea;
	
	/**총세대수*/
	private String announcementAllResident;
	
	/**내용*/
	private String announcementContents;
	
	/**조회수*/
	private int announcementClicks;
	
	/**작성일*/
	private String announcementCreateDate;
	
	/**수정일*/
	private String announcementUpdateDate;

	public TBAnnouncementVO() {
		super();
	}
	/**
	 * @param announcementNo
	 * @param memberNo
	 * @param aptZoneCode
	 * @param announcementName
	 * @param announcementStart
	 * @param announcementEnd
	 * @param announcementAddress
	 * @param announcementArea
	 * @param announcementAllResident
	 * @param announcementContents
	 * @param announcementClicks
	 * @param announcementCreateDate
	 * @param announcementUpdateDate
	 */
	
	public TBAnnouncementVO(int announcementNo,
			String aptZoneCode, String announcementName,
			String announcementStart, String announcementEnd,
			String announcementAddress, String announcementArea,
			String announcementAllResident,
			int announcementClicks, String announcementCreateDate) {
		super();
		this.announcementNo = announcementNo;
		this.aptZoneCode = aptZoneCode;
		this.announcementName = announcementName;
		this.announcementStart = announcementStart;
		this.announcementEnd = announcementEnd;
		this.announcementAddress = announcementAddress;
		this.announcementArea = announcementArea;
		this.announcementAllResident = announcementAllResident;
		this.announcementClicks = announcementClicks;
		this.announcementCreateDate = announcementCreateDate;
	}

	/**
	 * @param aptZoneCode
	 * @param announcementName
	 * @param announcementStart
	 * @param announcementEnd
	 * @param announcementAddress
	 * @param announcementArea
	 * @param announcementAllResident
	 * @param announcementContents
	 * @param announcementUpdateDate
	 */
	public TBAnnouncementVO(String aptZoneCode,
			String announcementName, String announcementStart,
			String announcementEnd, String announcementAddress,
			String announcementArea, String announcementAllResident,
			String announcementContents, String announcementUpdateDate) {
		super();
		this.aptZoneCode = aptZoneCode;
		this.announcementName = announcementName;
		this.announcementStart = announcementStart;
		this.announcementEnd = announcementEnd;
		this.announcementAddress = announcementAddress;
		this.announcementArea = announcementArea;
		this.announcementAllResident = announcementAllResident;
		this.announcementContents = announcementContents;
		this.announcementUpdateDate = announcementUpdateDate;
	}

	/**
	 * @param memberNo
	 * @param aptZoneCode
	 * @param announcementName
	 * @param announcementStart
	 * @param announcementEnd
	 * @param announcementAddress
	 * @param announcementArea
	 * @param announcementAllResident
	 * @param announcementContents
	 * @param announcementClicks
	 * @param announcementCreateDate
	 * @param announcementUpdateDate
	 */
	public TBAnnouncementVO(int memberNo, String aptZoneCode,
			String announcementName, String announcementStart,
			String announcementEnd, String announcementAddress,
			String announcementArea, String announcementAllResident,
			String announcementContents, int announcementClicks, 
			String announcementCreateDate, String announcementUpdateDate) {
		super();
		this.memberNo = memberNo;
		this.aptZoneCode = aptZoneCode;
		this.announcementName = announcementName;
		this.announcementStart = announcementStart;
		this.announcementEnd = announcementEnd;
		this.announcementAddress = announcementAddress;
		this.announcementArea = announcementArea;
		this.announcementAllResident = announcementAllResident;
		this.announcementContents = announcementContents;
		this.announcementClicks = announcementClicks;
		this.announcementCreateDate = announcementCreateDate;
		this.announcementUpdateDate = announcementUpdateDate;
	}

	/**
	 * @return the announcementNo
	 */
	public int getAnnouncementNo() {
		return announcementNo;
	}

	/**
	 * @param announcementNo the announcementNo to set
	 */
	public void setAnnouncementNo(int announcementNo) {
		this.announcementNo = announcementNo;
	}

	/**
	 * @return the memberNo
	 */
	public int getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the aptZoneCode
	 */
	public String getAptZoneCode() {
		return aptZoneCode;
	}

	/**
	 * @param aptZoneCode the aptZoneCode to set
	 */
	public void setAptZoneCode(String aptZoneCode) {
		this.aptZoneCode = aptZoneCode;
	}

	/**
	 * @return the announcementName
	 */
	public String getAnnouncementName() {
		return announcementName;
	}

	/**
	 * @param announcementName the announcementName to set
	 */
	public void setAnnouncementName(String announcementName) {
		this.announcementName = announcementName;
	}

	/**
	 * @return the announcementStart
	 */
	public String getAnnouncementStart() {
		return announcementStart;
	}

	/**
	 * @param announcementStart the announcementStart to set
	 */
	public void setAnnouncementStart(String announcementStart) {
		this.announcementStart = announcementStart;
	}

	/**
	 * @return the announcementEnd
	 */
	public String getAnnouncementEnd() {
		return announcementEnd;
	}

	/**
	 * @param announcementEnd the announcementEnd to set
	 */
	public void setAnnouncementEnd(String announcementEnd) {
		this.announcementEnd = announcementEnd;
	}

	/**
	 * @return the announcementAddress
	 */
	public String getAnnouncementAddress() {
		return announcementAddress;
	}

	/**
	 * @param announcementAddress the announcementAddress to set
	 */
	public void setAnnouncementAddress(String announcementAddress) {
		this.announcementAddress = announcementAddress;
	}

	/**
	 * @return the announcementArea
	 */
	public String getAnnouncementArea() {
		return announcementArea;
	}

	/**
	 * @param announcementArea the announcementArea to set
	 */
	public void setAnnouncementArea(String announcementArea) {
		this.announcementArea = announcementArea;
	}

	/**
	 * @return the announcementAllResident
	 */
	public String getAnnouncementAllResident() {
		return announcementAllResident;
	}

	/**
	 * @param announcementAllResident the announcementAllResident to set
	 */
	public void setAnnouncementAllResident(String announcementAllResident) {
		this.announcementAllResident = announcementAllResident;
	}

	/**
	 * @return the announcementContents
	 */
	public String getAnnouncementContents() {
		return announcementContents;
	}

	/**
	 * @param announcementContents the announcementContents to set
	 */
	public void setAnnouncementContents(String announcementContents) {
		this.announcementContents = announcementContents;
	}

	/**
	 * @return the announcementClicks
	 */
	public int getAnnouncementClicks() {
		return announcementClicks;
	}

	/**
	 * @param announcementClicks the announcementClicks to set
	 */
	public void setAnnouncementClicks(int announcementClicks) {
		this.announcementClicks = announcementClicks;
	}

	/**
	 * @return the announcementCreateDate
	 */
	public String getAnnouncementCreateDate() {
		return announcementCreateDate;
	}

	/**
	 * @param announcementCreateDate the announcementCreateDate to set
	 */
	public void setAnnouncementCreateDate(String announcementCreateDate) {
		this.announcementCreateDate = announcementCreateDate;
	}

	/**
	 * @return the announcementUpdateDate
	 */
	public String getAnnouncementUpdateDate() {
		return announcementUpdateDate;
	}

	/**
	 * @param announcementUpdateDate the announcementUpdateDate to set
	 */
	public void setAnnouncementUpdateDate(String announcementUpdateDate) {
		this.announcementUpdateDate = announcementUpdateDate;
	}

	/** (non-Javadoc)
	  @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TBAnnouncementVO [announcementNo=" + announcementNo
				+ ", memberNo=" + memberNo + ", aptZoneCode=" + aptZoneCode
				+ ", announcementName=" + announcementName
				+ ", announcementStart=" + announcementStart
				+ ", announcementEnd=" + announcementEnd
				+ ", announcementAddress=" + announcementAddress
				+ ", announcementArea=" + announcementArea
				+ ", announcementAllResident=" + announcementAllResident
				+ ", announcementContents=" + announcementContents
				+ ", announcementClicks=" + announcementClicks
				+ ", announcementCreateDate=" + announcementCreateDate
				+ ", announcementUpdateDate=" + announcementUpdateDate + "]";
	}

}
