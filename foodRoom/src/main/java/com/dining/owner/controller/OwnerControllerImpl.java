package com.dining.owner.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dining.dto.StoreDTO;
import com.dining.dto.UserDTO;
import com.dining.owner.dao.OwnerDAO;

@Controller("OwnerController")
public class OwnerControllerImpl implements OwnerController {

	@Autowired
	private OwnerDAO ownerDAO;
	
    //-----------------------------------------------------------------------------------------------------------
    // 업체정보 등록페이지 가기 
    //-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goRegiFoodRoomPage.do", method=RequestMethod.GET)
		public ModelAndView regiFoodRoomPage(RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception { 	   
	   
		// 세션 가져오기
		HttpSession session = request.getSession();
		// 세션에 담긴 아이디 저장
		String sfr_id  = (String) session.getAttribute("fr_id");
		System.out.println("controllerFr_id ==>" + sfr_id );
		
		// 세션으로 가져온 아이디가 등록한 업체 수 확인
		int idCount = ownerDAO.checkRegiFoodRoom(sfr_id);
		
		ModelAndView mav = new ModelAndView();
		
		// 등록한 업체가 없는 경우
		if(idCount == 0) {
			mav.setViewName("/owner/regiFoodRoomPage"); // 업체 등록 페이지로 이동
		}
		// 등록한 업체가 있는 경우
		else {
			mav = new ModelAndView("redirect:/index.do"); // 메인 페이지로 이동
			rAttr.addAttribute("result", "regiSuccess");
		}	
		return mav;
	 }

	//-----------------------------------------------------------------------------------------------------------
	// 업체명 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/checkStoreName.do", method=RequestMethod.POST)
	public int checkStoreName(@RequestParam("fr_store_name") String fr_store_name) throws Exception {
		
		System.out.println("controller checkStoreName ==>" + fr_store_name);
		int result = ownerDAO.checkStoreName(fr_store_name);
		System.out.println("업체명 중복 체크 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체 등록 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/regiFoodRoom.do", method=RequestMethod.POST)
	public ModelAndView regiFoodRoom(@ModelAttribute("store") StoreDTO storeDTO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 세션 가져오기
		HttpSession session = request.getSession();
		// 세션에 담긴 아이디 저장
		String sfr_id  = (String) session.getAttribute("fr_id");
		// storeVO에 세션에 담긴 아이디 값 저장
		storeDTO.setFr_id(sfr_id);
		System.out.println("controllerVO ==>" + storeDTO);
		
		ModelAndView mav = new ModelAndView();
		
		int result = ownerDAO.regiFoodRoom(storeDTO); // 업체 등록
		System.out.println("dao 업체등록 결과 ==>" + result);
		mav = new ModelAndView("redirect:/index.do");
		
		return mav;
	}
   
    //-----------------------------------------------------------------------------------------------------------
    // 업체 예약관리 달력 페이지
    //-----------------------------------------------------------------------------------------------------------
    @RequestMapping(value="/reservation.do", method=RequestMethod.GET)
    private ModelAndView reservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/owner/reservation");
        return mav;
    }
   
    //-----------------------------------------------------------------------------------------------------------
    // 업체 예약정보 수정 페이지
    //-----------------------------------------------------------------------------------------------------------
    @RequestMapping(value="/reservationForm.do", method=RequestMethod.GET)
    private ModelAndView reservationForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/owner/reservationForm");
        return mav;
    }
    
}
