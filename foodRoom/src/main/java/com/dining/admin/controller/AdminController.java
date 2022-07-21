package com.dining.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dining.dto.RoomDTO;

public interface AdminController {

	//-----------------------------------------------------------------------------------------------------------
	// 룸 정보 추가
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addRoomInfo(@ModelAttribute("RoomDTO") RoomDTO roomDTO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 룸 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView roomDelete(@RequestParam("fr_room_no") int fr_room_no, @RequestParam("fr_no") int fr_no, @RequestParam("fr_room_name") String fr_room_name,
		HttpServletRequest request, HttpServletResponse response) throws Exception;
		
} // End - public interface AdminController
