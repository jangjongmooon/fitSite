package com.dining.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dining.admin.dao.AdminDAO;
import com.dining.dto.RoomDTO;
import com.dining.dto.StoreDTO;

@Controller("adminController")
public class AdminControllerImpl implements AdminController {

	@Autowired
	private AdminDAO adminDAO;
	private static String ROOM_IMAGE_REPO = "C:\\data\\room_image";
	
	//-----------------------------------------------------------------------------------------------------------
	// 미승인된 업체들 승인하는 페이지 가기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goApproveFoodRoomPage.do", method=RequestMethod.GET)
	private ModelAndView unapproveFoodRoomList(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		List<StoreDTO> approve = adminDAO.unapproveFoodRoomList();	
		ModelAndView mav = new ModelAndView("/admin/approveFoodRoomPage");
		mav.addObject("approve", approve);
		
		return mav;
	}
		
	//-----------------------------------------------------------------------------------------------------------
	// 미등록 업체 승인하기, 업체 승인시 fr_class를 12로 변경
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/approveFoodRoom.do", method=RequestMethod.GET)
	public ModelAndView approveFoodRoom(@ModelAttribute("storeDTO") StoreDTO storeDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
	   
	   adminDAO.approveFoodRoom(storeDTO);
	   adminDAO.changeOwnerClass(storeDTO);
	   ModelAndView mav = new ModelAndView("redirect:/goApproveFoodRoomPage.do");
		   
	   return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 승인업체 관리페이지 가기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goManageFoodRoomPage.do", method=RequestMethod.GET)
	public ModelAndView manageFoodRoom(@ModelAttribute("storeDTO") StoreDTO storeDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<StoreDTO> lookApproveFoodRoomList = adminDAO.lookApproveFoodRoomList(storeDTO);	// 승인 요청 List로 보여주기
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/manageFoodRoomPage");
		mav.addObject("approveOk", lookApproveFoodRoomList);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 승인업체 검색 (업체검색 [업체명], 업체검색 [주소])
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/findStoreList.do", method=RequestMethod.POST)
	public ModelAndView findStoreList(@RequestParam("selectChk") String selectChk, @RequestParam("selectText") String selectText, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("셀렉박스값" + selectChk);
		System.out.println("넘긴값" + selectText);
		List<StoreDTO> approveOkSelect = new ArrayList<> ();

		if(selectChk.equals("fr_store_name")) {
			System.out.println("fr_store_name => "+selectText);
			approveOkSelect = adminDAO.findStoreOk(selectText);		
		}
		else {
			System.out.println("fr_address => "+selectText);
			approveOkSelect = adminDAO.findAddressOk(selectText);
		}
		
		ModelAndView mav = new ModelAndView("/admin/manageFoodRoomPage");
		mav.addObject("approveOk", approveOkSelect);
		return mav;
	}
		
	//-----------------------------------------------------------------------------------------------------------
	// 룸목록 페이지 가기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goRoomListPage.do", method=RequestMethod.GET)
	private ModelAndView RoomList(@RequestParam("fr_no") int fr_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<RoomDTO> roomList = adminDAO.roomList(fr_no);	// 승인 요청 List로 보여주기	
		 
		ModelAndView mav = new ModelAndView("/admin/roomListPage");
		mav.addObject("roomList", roomList);
		mav.addObject("fr_no", fr_no);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 룸정보 추가페이지 가기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goAddRoomInfoPage.do", method=RequestMethod.GET)
	private ModelAndView addRoomInfoPage(@RequestParam("fr_no") int fr_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/addRoomInfoPage");
		mav.addObject("fr_no", fr_no);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 룸정보 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/addRoomInfo.do", method=RequestMethod.POST)
	public ModelAndView addRoomInfo(@ModelAttribute("roomDTO") RoomDTO roomDTO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("addRoom ==>" + roomDTO);
		
		Map<String, String> roomImageMap	= uploadRoomInfo(request, response);

		roomDTO.setFr_no(Integer.parseInt(roomImageMap.get("fr_no")));
		roomDTO.setFr_room_name(roomImageMap.get("fr_room_name"));
		roomDTO.setFr_room_person_no(roomImageMap.get("fr_room_person_no"));
		roomDTO.setFr_room_image(roomImageMap.get("fr_room_image"));
		System.out.println(roomDTO);
		
		int result = adminDAO.addRoomImage(roomDTO);
		System.out.println("게시글 추가 controller 결과 freeboard_no ==> " + result);

		if(roomImageMap.get("fr_room_image") != null && roomImageMap.get("fr_room_image").length() != 0) { // 둘 중 하나가 작동 하지 않을 때가 있다.
			File srcFile = new File(ROOM_IMAGE_REPO + "\\" + roomImageMap.get("fr_room_image")); 		   // 이미지 파일을 저장한 경로
			File destDir = new File(ROOM_IMAGE_REPO + "\\" + roomDTO.getFr_no());						   // 해당 경로 + fr_no(업체번호) 폴더를 생성
			
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		
		ModelAndView mav = new ModelAndView();
		mav = new ModelAndView("redirect:/goRoomListPage.do?fr_no=" + roomDTO.getFr_no());
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 룸정보 파일업로드
	//-----------------------------------------------------------------------------------------------------------
	private Map<String, String> uploadRoomInfo(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Map<String, String> roomImageMap = new HashMap<String, String>();
		String encoding	=	"utf-8";
		
		// 업로드할 파일의 경로를 지정한다.
		File				currentDirPath		= new File(ROOM_IMAGE_REPO);
		
		DiskFileItemFactory	factory				= new DiskFileItemFactory();
		
		// 파일 경로를 설정한다.
		factory.setRepository(currentDirPath);
		
		// 업로드될 파일의 최대 크기를 설정한다.
		factory.setSizeThreshold(1024*1024*1024);
		
		ServletFileUpload uploadRoomImage = new ServletFileUpload(factory);
		
		try {
			// request객체에서 매개 변수를 List로 가져온다.
			List<FileItem> items = uploadRoomImage.parseRequest(request);
			
			for(int i = 0; i < items.size(); i++) {
				//	파일 업로드 창에서 업로드된 항목들을 하나씩 가져와서 작업을 한다.
				FileItem fileItem = (FileItem) items.get(i);
				
				// 폼 필드이면 전송된 매개 변수의 값을 출력한다.
				if(fileItem.isFormField()) {
					roomImageMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else { // 폼 필드가 아니면 파일 업로드 기능을 실행한다.
					// 업로드한 파일의 이름을 가져온다.
					// 파일의 사이즈가 0보다 큰 경우만 업로드를 한다.
					if(fileItem.getSize() > 0) {
						// 변수.lastIndexOf(검색값) => 변수에서 검색값 들 중에서 마지막 것을 말한다.
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) { // 이런(\\) 경로가 아니라면 / 경로의 마지막에서 파일이름을 찾는다.
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						// 경로에서 파일 이름을 추출한다.
						// "ABCDEFGHIJ"
						// substring(4) = > 인덱스번호 4이상 모든 값 => EFGHIJ
						// substring(3, 7) => 인덱스번호 3번 부터 7번 전까지 => DEFG
						// String fileName = fileItem.getName().substring(idx+1);
						
						// 파일 이름을 room_img로 통일한다. (추 후 수정편의성)
						String reName = roomImageMap.get("fr_no")+"-"+roomImageMap.get("fr_room_name");
						
						// 파일 고유 네임값을 불러온 뒤
						String f_Name = fileItem.getName();
						// 파일 확장자를 분리한다.
						String ext = f_Name.substring(f_Name.lastIndexOf(".") + 1);
						// 저장할 파일명을 reName과 확장자의 조합으로 명명한다.
						String fileName = reName+"."+ext;
						
						// 업로드한 파일의 이름으로 저장소(currentDirPath)에 파일을 업로드 한다.
						// File uploadFile = new File(currentDirPath + "\\" + fileName);
						// 파일이름이 중복되면 마지막에 올린 파일만 존재하게 되므로 임시 파일에 저장시키고,
						// 책 번호를 부여받게 되면 책 번호의 폴더를 만들어서 저장시키도록 한다.
						// upload()를 호출한 곳으로 bookInfoMap에 파일 정보를 넣어준다.
						roomImageMap.put(fileItem.getFieldName(), fileName);
						
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);
					}
					
				} // End - if
				
			} // End - for
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roomImageMap;
	}	
		
}
