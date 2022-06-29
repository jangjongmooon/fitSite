package com.dining.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dining.dto.RoomDTO;
import com.dining.dto.StoreDTO;
import com.dining.member.dao.MemberDAO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {

	@Autowired
	private MemberDAO memberDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 전체 업체목록
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goReservationStore.do", method=RequestMethod.GET)
	public ModelAndView allStoreList(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		List<StoreDTO> allStoreList = memberDAO.allStoreList();
		ModelAndView mav = new ModelAndView("/member/storeListPage");
		mav.addObject("storeList", allStoreList);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 검색 업체목록
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/findStoreLlist.do", method=RequestMethod.GET)
	public ModelAndView findStoreList(@RequestParam("fr_address") String fr_address, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		List<StoreDTO> findStoreList = memberDAO.findStoreList(fr_address);
		ModelAndView mav = new ModelAndView("/member/storeListPage");
		mav.addObject("storeList", findStoreList);
		mav.addObject("fr_address", fr_address);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 룸목록 페이지 가기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goMemberRoomListPage.do", method=RequestMethod.GET)
	private ModelAndView RoomList(@RequestParam("fr_no") int fr_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<RoomDTO> mRoomList = memberDAO.mRoomList(fr_no);	
		 
		ModelAndView mav = new ModelAndView("/member/roomListPage");
		mav.addObject("mRoomList", mRoomList);
		mav.addObject("mRoomList", fr_no);
		
		return mav;
	}
	
}
