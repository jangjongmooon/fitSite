package com.dining.user.controller;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.dining.vo.UserVO;
import com.dining.user.dao.UserDAO;

@Controller("userController")
public class UserControllerImpl implements UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserVO userVO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 메인 화면 불러오기 layout
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "main";
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 유형 선택 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/signUpClassForm.do", method=RequestMethod.GET)
	private ModelAndView signUpClassForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/signUpForm");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/signUp.do", method=RequestMethod.GET)
	public ModelAndView signUpForm(@RequestParam("fr_class") String fr_class, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("controller class==> " + fr_class);
		ModelAndView mav = new ModelAndView();
		if(fr_class.equals("02")) {		// 업체 회원 가입 폼 
			mav.setViewName("/user/signUpForm");		
		} 	
		else if(fr_class.equals("13")) {		// 일반 회원 회원가입 폼
			mav.setViewName("/user/signUpForm");
		}
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/user/addUser.do", method=RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("userVO") UserVO userVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("controllermVO ==>" + userVO);
		
		int result = userDAO.addUser(userVO);
		
		ModelAndView mav = new ModelAndView();
		mav = new ModelAndView("redirect:/index.do");
		return mav;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 취소
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/imsi/loginForm.do", method=RequestMethod.GET)
	public ModelAndView deleteSignUp(@ModelAttribute("userVO") UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index.do");
		return mav;
	}
}
