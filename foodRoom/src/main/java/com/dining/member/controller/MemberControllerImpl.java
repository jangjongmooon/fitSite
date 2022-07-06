package com.dining.member.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dining.dto.ReservationDTO;
import com.dining.dto.RoomDTO;
import com.dining.dto.SelectDTO;
import com.dining.dto.StoreDTO;
import com.dining.member.dao.MemberDAO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {

	@Autowired
	private MemberDAO memberDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 이용 가능한 업체 리스트 페이지
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goReservationStore.do", method=RequestMethod.GET)
	public ModelAndView allStoreList(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		List<StoreDTO> allStoreList = memberDAO.allStoreList();
		ModelAndView mav = new ModelAndView("/member/storeListPage");
		mav.addObject("storeList", allStoreList);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 이용 가능한 업체 지역(대분류)별 검색
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/findStoreList.do", method=RequestMethod.GET)
	public ModelAndView findStoreList(@RequestParam("findBigStore") String findBigStore, @RequestParam("findSmallStore") String findSmallStore, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SelectDTO select = new SelectDTO();			// 비어있는 selectVO 생성
		select.setFindBigStore(findBigStore);		// findBigStore 값 VO에 담기
		if(findSmallStore.equals("모두")) {			// 소분류가 모두인 경우 findSmallStore값을 / 로 바꿔준다.
			select.setFindSmallStore("/");
		}
		// else가 필요한 이유는 모두가 들어오고 else가 없으면 다시 select.setFindSmallStore(findSmallStore);를 통해 모두로 돌아간다. 즉, 모두 -> / -> 모두로 변한다.
		else {											// 소분류가 지정된 경우 가져온 소분류 값을 그대로 VO에 담는다.
			select.setFindSmallStore(findSmallStore);	// findSmallStore 값 VO에 담기
			System.out.println("소분류 확인 findSmallStore 값 => "+ select);
		}
		
		List<StoreDTO> findStoreList = memberDAO.findStoreList(select);
		ModelAndView mav = new ModelAndView("/member/storeListPage");
		mav.addObject("storeList", findStoreList);
		mav.addObject("findBigStore", findBigStore);		// 지역검색 후 메뉴 및 인원 검색 시 주소 재사용을 위하여 다시 보내준다.
		mav.addObject("findSmallStore", findSmallStore);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 지역검색 이후 메뉴 및 인원으로 검색
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/detailFindStoreList.do", method=RequestMethod.POST)
	public ModelAndView detailFindStoreList(SelectDTO selectDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("화면에서 받아온 selectVO 값 => " + selectDTO);
		
		List<StoreDTO> detailFindStoreList = new ArrayList<> ();
		
		// findBigStore가 모두인 경우 /로 바꿔 준다.
		// /로 바꿔주어야 지역을 대분륜만 선택 했을 경우에도 검색이 가능하게 된다.
		if(selectDTO.getFindSmallStore().equals("모두")) {
			selectDTO.setFindSmallStore("/");
		}
		System.out.println("화면에서 받아온 값 if문 이후" + selectDTO);
		
		int personNo = (int) selectDTO.getPersonNo();// VO에 담겨 있는 고객이 검색 시 입력한 인원수를 가져와 저장
		System.out.println("화면에서 받아온 인원수를 personNo 담기 =>" + personNo);
		
		detailFindStoreList	= memberDAO.detailFindStoreList(selectDTO);		
		System.out.println("FindStore2 가져온 값 => "+ detailFindStoreList);
		
		ModelAndView mav = new ModelAndView("/member/storeListPage");
		mav.addObject("storeViewList", detailFindStoreList);
		mav.addObject("personNo", personNo); // 고객이 입력한 인원수도 같이 보내준다.
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 선택한 업체의 방 리스트 페이지 (예약가능 / 예약완료)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/selectStoreRoomListForm.do", method=RequestMethod.GET)
	public ModelAndView storeViewRoomList(@RequestParam("fr_no") int fr_no, @RequestParam("personNo") int personNo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("화면에서 받아온 fr_no, personNo 값 => " + fr_no + "," + personNo);
		
		Date today = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String date = simpleDateFormat.format(today);
		System.out.println("오늘날짜 가져오기 => "+ date);		
		
		HashMap<Object, Object> map = new HashMap<Object, Object>(); // HashMap 컬렉션 생성 후 화면에서 받아온 값 담기
		map.put("fr_no",fr_no);
		map.put("personNo",personNo);
		
		List<RoomDTO> selectStoreRoomList = new ArrayList<> ();		// 선택한 업체의 예약 가능한 방 List로 보여주기
		selectStoreRoomList = memberDAO.selectStoreRoomList(map);		
		System.out.println("selectStoreRoomList 가져온 값 => "+ selectStoreRoomList);
		
		List<RoomDTO> completionRoomList = new ArrayList<> ();	// 선택한 업체의 예약 완료된 방 List로 보여주기
		completionRoomList = memberDAO.completionRoomList(map);			
		System.out.println("completionRoomList 가져온 값 => "+ completionRoomList);
		
		
		ModelAndView mav = new ModelAndView("/member/roomListPage");
		mav.addObject("selectStoreRoomList", selectStoreRoomList);
		mav.addObject("completionRoomList", completionRoomList);
		mav.addObject("fr_no", fr_no);
		mav.addObject("personNo", personNo);
		mav.addObject("date", date);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 선택한 날짜와 업체의 방 리스트 페이지 (예약 가능/ 예약 완료)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/selectDayStoreRoomListForm.do", method=RequestMethod.GET)
	public ModelAndView storeDayRoomList(@RequestParam("fr_no") int fr_no, @RequestParam("personNo") int personNo, 
			@RequestParam("fr_reservation_date") String fr_reservation_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("화면에서 받아온 fr_no, personNo, fr_reservation_date 값 => " + fr_no + "," + personNo + "," + fr_reservation_date);		
		
		HashMap<Object, Object> map = new HashMap<Object, Object>(); // HashMap 컬렉션 생성 후 화면에서 받아온 값 담기
		map.put("fr_no",fr_no);
		map.put("personNo",personNo);
		map.put("fr_reservation_date",fr_reservation_date);
		
		List<RoomDTO> selectDateRoomList = memberDAO.selectDateRoomList(map);	// 선택한 날짜와 업체의 예약 가능한 방 List로 보여주기	
		System.out.println("selectDateRoomList 가져온 값 => "+ selectDateRoomList);
		
		List<RoomDTO> completionDateRoomList = memberDAO.completionDateRoomList(map);	// 선택한 날짜와 업체의 예약 완료된 방 List로 보여주기	
		System.out.println("completionDateRoomList 가져온 값 => "+ completionDateRoomList);
		
		ModelAndView mav = new ModelAndView("/member/roomListPage");
		mav.addObject("selectStoreRoomList", selectDateRoomList);
		mav.addObject("completionRoomList", completionDateRoomList);
		mav.addObject("fr_no", fr_no);
		mav.addObject("personNo", personNo);
		mav.addObject("date", fr_reservation_date);
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 온라인 예약하기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/onlineReservationForm.do", method=RequestMethod.POST)
	public ModelAndView onlineReservation(@ModelAttribute("reservationDTO") ReservationDTO reservationDTO, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("reservationDTO 가져온 값 => "+ reservationDTO);
		
		Date today = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String date = simpleDateFormat.format(today);
		System.out.println("오늘날짜 가져오기 => "+ date);
		
		// 세션 가져오기
		HttpSession session = request.getSession();
		// 세션에 담긴 이름/연락처 저장
		String sfr_name  	= (String) session.getAttribute("fr_name");
		String sfr_p_number = (String) session.getAttribute("fr_p_number");
		// reservationVO에 세션에 담긴 이름/연락처 값 저장
		reservationDTO.setFr_name(sfr_name);
		reservationDTO.setFr_p_number(sfr_p_number);
		
		String day = reservationDTO.getFr_reservation_date();
		
		System.out.println("reservationVO에 세션 추가 값 => "+ reservationDTO);
		memberDAO.onlineReservation(reservationDTO);
		
		ModelAndView mav = new ModelAndView("redirect:/index.do");
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
		mav.addObject("fr_no", fr_no);
		
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 예약내역 확인하기 페이지
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/myReservationForm.do", method=RequestMethod.GET)
	public ModelAndView myReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션 가져오기
		HttpSession session = request.getSession();
		// 세션에 담긴 이름/연락처 저장
		String sfr_name  	= (String) session.getAttribute("fr_name");
		String sfr_p_number = (String) session.getAttribute("fr_p_number");
		
		HashMap<Object, Object> map = new HashMap<Object, Object>(); // HashMap 컬렉션 생성 후 세션에서 받아온 값 담기
		map.put("fr_name",sfr_name);
		map.put("fr_p_number",sfr_p_number);
		System.out.println("map(이름,연락처)에 세션 추가 값 => "+ map);
		
		List<HashMap<String, Object>> myReservationList = memberDAO.myReservation(map);	// 회원이 예약한 룸 List로 보여주기	
		System.out.println("myReservation DB에서 가져온 값 => "+ myReservationList);
		
		ModelAndView mav = new ModelAndView("/member/lookReservationList");
		mav.addObject("myReservationList", myReservationList);
		return mav;
	}
	
} // End - public class MemberControllerImpl implements MemberController
