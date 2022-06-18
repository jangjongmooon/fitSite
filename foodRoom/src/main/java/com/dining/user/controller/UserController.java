package com.dining.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.dining.vo.UserVO;

public interface UserController {
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView signUpForm(@RequestParam("fr_class") String fr_class,
			HttpServletRequest request, HttpServletResponse response) throws Exception;	
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addUser(@ModelAttribute("info") UserVO userVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
		
	
	
}
