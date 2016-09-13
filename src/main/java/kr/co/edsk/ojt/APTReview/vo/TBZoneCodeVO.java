package kr.co.edsk.ojt.APTReview.vo;

import java.io.Serializable;

/**
 * @author v570
 *
 */
public class TBZoneCodeVO implements Serializable{

	/** serialVersion UID */
	private static final long serialVersionUID = 1L;

	/** 지역코드 */
	private String aptZoneCode;
	
	/** 지역코드 값 */
	private String aptZoneCodeValue;
	
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
	public String getAptZoneCodeValue() {
		return aptZoneCodeValue;
	}
	
	/**
	 * @param aptZoneCodeValue
	 */
	public void setAptZoneCodeValue(String aptZoneCodeValue) {
		this.aptZoneCodeValue = aptZoneCodeValue;
	}

	@Override
	public String toString() {
		return "TBZoneCode [aptZoneCode=" + aptZoneCode + ", aptZoneCodeValue="
				+ aptZoneCodeValue + "]";
	}
	
	
}
