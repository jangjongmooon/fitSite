package com.dining.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dining.dto.UserDTO;

public interface UserController {
		
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView signUp(@ModelAttribute("info") UserDTO userDTO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
		
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView login(@ModelAttribute("user") UserDTO userDTO,RedirectAttributes rAttr, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;	
	
}
