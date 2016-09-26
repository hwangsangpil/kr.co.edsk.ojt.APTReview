package kr.co.edsk.ojt.APTReview.vo;

import java.io.Serializable;

public class TBPlanCodeVO implements Serializable {

	/** serialVersion UID */
	private static final long serialVersionUID = 1L;

	/** 지역코드 */
	private String aptZoneCode;
	
	/** 단지코드 */
	private String aptBlockCode;
	
	/** 도면도코드 */
	private String aptPlanCode;
	
	/** 도면도코드 값 */
	private String aptPlanCodeValue;
	
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

	public String getAptPlanCode() {
		return aptPlanCode;
	}

	public void setAptPlanCode(String aptPlanCode) {
		this.aptPlanCode = aptPlanCode;
	}

	public String getAptPlanCodeValue() {
		return aptPlanCodeValue;
	}

	public void setAptPlanCodeValue(String aptPlanCodeValue) {
		this.aptPlanCodeValue = aptPlanCodeValue;
	}

	@Override
	public String toString() {
		return "TBPlanCodeVO [aptZoneCode=" + aptZoneCode + ", aptBlockCode="
				+ aptBlockCode + ", aptPlanCode=" + aptPlanCode
				+ ", aptPlanCodeValue=" + aptPlanCodeValue + "]";
	}
	
}
