package kr.co.edsk.ojt.APTReview.vo;

import java.io.Serializable;

public class TBMemberVO implements Serializable{

	/** serialVersion UID */
	private static final long serialVersionUID = 4433783843219637550L;
	
	/** 번호 */
	private int memberNo;
	
	/** 아이디 */
	private String memberId;
	
	/** 비밀번호 */
	private String memberPw;
	
	/** 이름 */
	private String memberName;
	
	/** 핸드폰번호 */
	private String memberPhone;
	
	/** 이메일 */
	private String memberEmail;
	
	/** 생성일 */
	private String memberCreateDate;
	
	/** 권한 */
	private String memberAuthority;

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
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the memberPw
	 */
	public String getMemberPw() {
		return memberPw;
	}

	/**
	 * @param memberPw the memberPw to set
	 */
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the memberPhone
	 */
	public String getMemberPhone() {
		return memberPhone;
	}

	/**
	 * @param memberPhone the memberPhone to set
	 */
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	/**
	 * @return the memberEmail
	 */
	public String getMemberEmail() {
		return memberEmail;
	}

	/**
	 * @param memberEmail the memberEmail to set
	 */
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	/**
	 * @return the memberCreateDate
	 */
	public String getMemberCreateDate() {
		return memberCreateDate;
	}

	/**
	 * @param memberCreateDate the memberCreateDate to set
	 */
	public void setMemberCreateDate(String memberCreateDate) {
		this.memberCreateDate = memberCreateDate;
	}

	/**
	 * @return the memberAuthority
	 */
	public String getMemberAuthority() {
		return memberAuthority;
	}

	/**
	 * @param memberAuthority the memberAuthority to set
	 */
	public void setMemberAuthority(String memberAuthority) {
		this.memberAuthority = memberAuthority;
	}
	
	
}
