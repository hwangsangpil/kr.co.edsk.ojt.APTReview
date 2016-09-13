package kr.co.edsk.ojt.APTReview.vo;

import java.io.Serializable;

public class TBBlockCodeVO implements Serializable{

	/** serialVersion UID */
	private static final long serialVersionUID = 1L;
	
	/** 단지코드 */
	private String aptBlockCode;
	
	/** 지역코드 */
	private String aptZoneCode;
	
	/** 단지코드 값 */
	private String aptBlockCodeValue;

	/**
	 * @return
	 */
	public String getAptBlockCode() {
		return aptBlockCode;
	}

	/**
	 * @param aptBlockCode
	 */
	public void setAptBlockCode(String aptBlockCode) {
		this.aptBlockCode = aptBlockCode;
	}

	/**
	 * @return
	 */
	public String getAptZoneCode() {
		return aptZoneCode;
	}

	/**
	 * @param aptZoneCode
	 */
	public void setAptZoneCode(String aptZoneCode) {
		this.aptZoneCode = aptZoneCode;
	}

	/**
	 * @return
	 */
	public String getAptBlockCodeValue() {
		return aptBlockCodeValue;
	}

	/**
	 * @param aptBlockCodeValue
	 */
	public void setAptBlockCodeValue(String aptBlockCodeValue) {
		this.aptBlockCodeValue = aptBlockCodeValue;
	}

	@Override
	public String toString() {
		return "TBBlockCode [aptBlockCode=" + aptBlockCode + ", aptZoneCode="
				+ aptZoneCode + ", aptBlockCodeValue=" + aptBlockCodeValue
				+ "]";
	}
}
