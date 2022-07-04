package com.dining.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.dining.dto.SelectDTO;
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
	// 선택한 업체의 방 리스트 페이지
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/selectStoreRoomListForm.do", method=RequestMethod.GET)
	public ModelAndView storeViewRoomList(@RequestParam("fr_no") int fr_no, @RequestParam("personNo") int personNo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("화면에서 받아온 fr_no, personNo 값 => " + fr_no + "," + personNo);
		
		List<RoomDTO> selectStoreRoomList = new ArrayList<> ();
		
		HashMap<Object, Object> map = new HashMap<Object, Object>(); // HashMap 컬렉션 생성 후 화면에서 받아온 값 담기
		map.put("fr_no",fr_no);
		map.put("personNo",personNo);
		
		selectStoreRoomList = memberDAO.selectStoreRoomList(map);	// 선택한 업체의 방 List로 보여주기	
		System.out.println("selectStoreRoomList 가져온 값 => "+ selectStoreRoomList);
		
		ModelAndView mav = new ModelAndView("/member/roomListPage");
		mav.addObject("selectStoreRoomList", selectStoreRoomList);
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
	
}
