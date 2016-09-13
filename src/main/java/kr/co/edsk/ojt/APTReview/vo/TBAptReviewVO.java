package kr.co.edsk.ojt.APTReview.vo;

import java.io.Serializable;

public class TBAptReviewVO implements Serializable{

	private static final long serialVersionUID = -4394617263719310719L;

	//후기게시판번호
	private int aptReviewNo;
	
	//작성자
	private int memberNo;
	
	//지역코드
	private String aptZoneCode;
	
	//단지코드
	private String aptBlockCode;
	
	//제목
	private String aptReviewTitle;
	
	//내용
	private String aptReviewContent;
	
	//조회수
	private int aptReviewClick;
	
	//답변 게시물 그룹
	private int aptReviewGroup;
	
	//답변 게시물 순서
	private int aptReviewNum;
	
	//답변 게시물 깊이
	private int aptReviewDepth;
	
	//작성일
	private String aptReviewCreateDate;
	
	//수정일
	private String aptReviewUpdateDate;

	
	
	public int getAptReviewNo() {
		return aptReviewNo;
	}

	public void setAptReviewNo(int aptReviewNo) {
		this.aptReviewNo = aptReviewNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getAptZoneCode() {
		return aptZoneCode;
	}

	public void setAptZoneCode(String aptZoneCode) {
		this.aptZoneCode = aptZoneCode;
	}

	public String getAptBlockCode() {
		return aptBlockCode;
	}

	public void setAptBlockCode(String aptBlockCode) {
		this.aptBlockCode = aptBlockCode;
	}

	public String getAptReviewTitle() {
		return aptReviewTitle;
	}

	public void setAptReviewTitle(String aptReviewTitle) {
		this.aptReviewTitle = aptReviewTitle;
	}

	public String getAptReviewContent() {
		return aptReviewContent;
	}

	public void setAptReviewContent(String aptReviewContent) {
		this.aptReviewContent = aptReviewContent;
	}

	public int getAptReviewClick() {
		return aptReviewClick;
	}

	public void setAptReviewClick(int aptReviewClick) {
		this.aptReviewClick = aptReviewClick;
	}

	public int getAptReviewGroup() {
		return aptReviewGroup;
	}

	public void setAptReviewGroup(int aptReviewGroup) {
		this.aptReviewGroup = aptReviewGroup;
	}

	public int getAptReviewNum() {
		return aptReviewNum;
	}

	public void setAptReviewNum(int aptReviewNum) {
		this.aptReviewNum = aptReviewNum;
	}

	public int getAptReviewDepth() {
		return aptReviewDepth;
	}

	public void setAptReviewDepth(int aptReviewDepth) {
		this.aptReviewDepth = aptReviewDepth;
	}

	public String getAptReviewCreateDate() {
		return aptReviewCreateDate;
	}

	public void setAptReviewCreateDate(String aptReviewCreateDate) {
		this.aptReviewCreateDate = aptReviewCreateDate;
	}

	public String getAptReviewUpdateDate() {
		return aptReviewUpdateDate;
	}

	public void setAptReviewUpdateDate(String aptReviewUpdateDate) {
		this.aptReviewUpdateDate = aptReviewUpdateDate;
	}

	@Override
	public String toString() {
		return "TBAptReviewVO [aptReviewNo=" + aptReviewNo + ", memberNo="
				+ memberNo + ", aptZoneCode=" + aptZoneCode + ", aptBlockCode="
				+ aptBlockCode + ", aptReviewTitle=" + aptReviewTitle
				+ ", aptReviewContent=" + aptReviewContent
				+ ", aptReviewClick=" + aptReviewClick + ", aptReviewGroup="
				+ aptReviewGroup + ", aptReviewNum=" + aptReviewNum
				+ ", aptReviewDepth=" + aptReviewDepth
				+ ", aptReviewCreateDate=" + aptReviewCreateDate
				+ ", aptReviewUpdateDate=" + aptReviewUpdateDate + "]";
	}
	
	
}
