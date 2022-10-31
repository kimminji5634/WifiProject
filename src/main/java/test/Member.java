package test;

import java.util.Date;
import test.DBcon;

public class Member {

	public Integer id;
	public Double LAT;
	public Double LNT;
	public String selectDate;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getLAT() {
		return LAT;
	}
	public void setLAT(Double lAT) {
		LAT = lAT;
	}
	public Double getLNT() {
		return LNT;
	}
	public void setLNT(Double lNT) {
		LNT = lNT;
	}
	public String getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(String selectDate2) {
		this.selectDate = selectDate2;
	}
}