package com.dining.dto;

import org.springframework.stereotype.Component;

@Component("setRevDTO")
public class SetRevDTO {

	private int fr_no;						// 룸 번호

	private String fr_reservation_date;			// 예약일자

	public SetRevDTO() {}
	
	public SetRevDTO(int fr_no, String fr_reservation_date) {
		this.fr_no					= fr_no;
		this.fr_reservation_date	= fr_reservation_date;
	}

	public int getFr_no() {
		return fr_no;
	}

	public void setFr_no(int fr_no) {
		this.fr_no = fr_no;
	}

	public String getFr_reservation_date() {
		return fr_reservation_date;
	}

	public void setFr_reservation_date(String fr_reservation_date) {
		this.fr_reservation_date = fr_reservation_date;
	}

	@Override
	public String toString() {
		return "SetRevVO [fr_no=" + fr_no + ", fr_reservation_date=" + fr_reservation_date  + "]";
	}

		
}
