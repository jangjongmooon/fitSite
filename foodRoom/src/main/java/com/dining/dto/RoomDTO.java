package com.dining.dto;

import org.springframework.stereotype.Component;

@Component("roomDTO")
public class RoomDTO {

	private int 	fr_room_no;
	private int 	fr_no;
	private String 	fr_room_name;
	private String 	fr_room_person_no;
	private String 	fr_room_image;	

	public int getFr_room_no() {
		return fr_room_no;
	}

	public void setFr_room_no(int fr_room_no) {
		this.fr_room_no = fr_room_no;
	}

	public int getFr_no() {
		return fr_no;
	}

	public void setFr_no(int fr_no) {
		this.fr_no = fr_no;
	}

	public String getFr_room_name() {
		return fr_room_name;
	}

	public void setFr_room_name(String fr_room_name) {
		this.fr_room_name = fr_room_name;
	}

	public String getFr_room_person_no() {
		return fr_room_person_no;
	}

	public void setFr_room_person_no(String fr_room_person_no) {
		this.fr_room_person_no = fr_room_person_no;
	}

	public String getFr_room_image() {
		return fr_room_image;
	}

	public void setFr_room_image(String fr_room_image) {
		this.fr_room_image = fr_room_image;
	}

	@Override
	public String toString() {
		return "RoomVO [fr_room_no=" + fr_room_no + ", fr_no=" + fr_no + ", fr_room_name=" + fr_room_name
				+ ", fr_room_person_no=" + fr_room_person_no + ", fr_room_image=" + fr_room_image + "]";
	}
	
	
}
