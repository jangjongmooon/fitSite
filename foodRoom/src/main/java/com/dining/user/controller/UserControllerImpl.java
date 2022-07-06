package com.dining.user.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dining.dto.UserDTO;
import com.dining.user.dao.UserDAO;

@Controller("userController")
public class UserControllerImpl implements UserController {
	
	@Autowired
	private UserDAO userDAO;	
	
	@Autowired
	private UserDTO userDTO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 메인 화면 불러오기 layout
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "main";
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 페이지
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/signUpPage.do", method=RequestMethod.GET)
	public ModelAndView signUpForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/signUpPage");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/checkId.do", method=RequestMethod.POST)
	public int checkId(UserDTO userDTO) throws Exception {
		
		System.out.println("controller checkId ==>" + userDTO);
		int result = userDAO.checkId(userDTO);
		
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/signUp.do", method=RequestMethod.POST)
	public ModelAndView signUp(@ModelAttribute("userDTO") UserDTO userDTO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("controller signUp userDTO ==>" + userDTO);
		
		int result = userDAO.signUp(userDTO);
		
		System.out.println("controller signUp 결과 ==>" + result);
		
		ModelAndView mav = new ModelAndView();
		mav = new ModelAndView("redirect:/index.do");
		return mav;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 취소
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/cancelMembership.do", method=RequestMethod.GET)
	public ModelAndView cancelMembership(@ModelAttribute("userDTO") UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index.do");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 페이지 이동
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goLoginPage.do", method=RequestMethod.GET)
	public ModelAndView goLoginPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/loginPage");
		return mav;
	}		
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("userDTO")UserDTO userDTO, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("controller 로그인 ==> " + userDTO);
		UserDTO loginInfo = userDAO.login(userDTO);
		System.out.println("controller 로그인 결과 ==> " + loginInfo);
		
		ModelAndView mav = new ModelAndView();
		
		if(loginInfo == null) { // 로그인 정보가 없을 경우
			// 다시 로그인 페이지로
			rAttr.addAttribute("result", "loginFailed");
			mav = new ModelAndView("redirect:/goLoginPage.do");
			
		} else { // 로그인 정보가 있는 경우
			// 입력한 id, pwd 가 등록된 id, pwd와 같을 경우
			if(loginInfo.getFr_id().equals(userDTO.getFr_id()) && loginInfo.getFr_pwd().equals(userDTO.getFr_pwd())) { 
				
				//  등록된 회원 로그인
				// 세션 생성
				HttpSession session = request.getSession();
				
				session.setAttribute("fr_id", loginInfo.getFr_id());
				session.setAttribute("fr_pwd", loginInfo.getFr_pwd());
				session.setAttribute("fr_name", loginInfo.getFr_name());
				session.setAttribute("fr_p_number", loginInfo.getFr_p_number());
				session.setAttribute("fr_email", loginInfo.getFr_email());
				session.setAttribute("fr_class", loginInfo.getFr_class());
				session.setAttribute("isLogOn", 	true);
				session.setMaxInactiveInterval(60*120);
				
				mav = new ModelAndView("redirect:/index.do");
								
			}	
		}
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// id pwd 찾기 페이지로 가기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goFindIdPwdPage.do", method=RequestMethod.GET)
	private ModelAndView findIdPwdForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/findIdPwdPage");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/findId.do", method=RequestMethod.POST)
	public UserDTO findId(@RequestParam("fr_name") String fr_name, @RequestParam("fr_email") String fr_email, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("controller id찾기 ==>" + fr_name + ", " + fr_email);
		UserDTO user = new UserDTO();
		user.setFr_name(fr_name);
		user.setFr_email(fr_email);
		
		userDTO = userDAO.findId(user);
		
		System.out.println("id찾기 결과 ==>" + userDTO);
		
		return userDTO;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/findPwd.do", method=RequestMethod.POST)
	public UserDTO findPwd(@RequestParam("fr_id") String fr_id, @RequestParam("fr_email") String fr_email, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		System.out.println("controller pwd찾기 ==>" + fr_id + ", " + fr_email);
		UserDTO user = new UserDTO();
		user.setFr_id(fr_id);
		user.setFr_email(fr_email);
		
		userDTO = userDAO.findPwd(user);
		
		System.out.println("pwd찾기 결과 ==>" + userDTO);
		
		return userDTO;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이페이지로 가기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goMyPage.do", method=RequestMethod.GET)
	private ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/user/myPage");
		mav.addObject("userDTO", userDTO);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이페이지 변경폼으로 가기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goUpdateMyPage.do", method=RequestMethod.GET)
	private ModelAndView goUpdateMyPageForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/user/updateMyPage");
		mav.addObject("userDTO", userDTO);
		return mav;
	}		

	//-----------------------------------------------------------------------------------------------------------
    // 마이페이지 변경하기
    //-----------------------------------------------------------------------------------------------------------
    @RequestMapping(value="/updateMyPage.do", method=RequestMethod.POST)
    public ModelAndView updateMyPage(@ModelAttribute("userDTO") UserDTO userDTO, HttpServletRequest request, HttpServletResponse response)
          throws Exception {
    
       userDAO.updateMyPage(userDTO);

       HttpSession session = request.getSession();
      
       session.removeAttribute("fr_name");
       session.removeAttribute("fr_p_number");
       session.removeAttribute("fr_email");
       session.removeAttribute("fr_pwd");
      
       session.setAttribute("fr_name", userDTO.getFr_name());
       session.setAttribute("fr_p_number", userDTO.getFr_p_number());
       session.setAttribute("fr_email", userDTO.getFr_email());
       session.setAttribute("fr_pwd", userDTO.getFr_pwd());
      
       ModelAndView mav = new ModelAndView("redirect:goMyPage.do");
      
      
       return mav;
    }
    
	//-----------------------------------------------------------------------------------------------------------
	// 로그아웃
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
			session.removeAttribute("fr_id");
			session.removeAttribute("fr_pwd");
			session.removeAttribute("fr_name");
			session.removeAttribute("fr_p_number");
			session.removeAttribute("fr_email");
			session.removeAttribute("fr_class");
			session.removeAttribute("isLogOn");
			
		ModelAndView mav = new ModelAndView("redirect:/index.do");
		
		return mav;
	}

}
