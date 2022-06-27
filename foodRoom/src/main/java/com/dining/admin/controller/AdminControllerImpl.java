package com.dining.admin.controller;

import java.io.File;
import java.io.IOException;
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
	private static String FREEBOARD_IMAGE_REPO = "C:\\data\\room_image";
	
	//-----------------------------------------------------------------------------------------------------------
	// 미승인된 업체  List
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goApprovePage.do", method=RequestMethod.GET)
	private ModelAndView unapproveFoodRoomList(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		List<StoreDTO> approve = adminDAO.unapproveFoodRoomList();	
		ModelAndView mav = new ModelAndView("/admin/approvePage");
		mav.addObject("approve", approve);
		
		return mav;
	}
		
	//-----------------------------------------------------------------------------------------------------------
	// 승인하기 
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/approveFoodRoom.do", method=RequestMethod.GET)
	public ModelAndView approveFoodRoom(@ModelAttribute("storeDTO") StoreDTO storeDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
	   
	   adminDAO.approveFoodRoom(storeDTO);
	   ModelAndView mav = new ModelAndView("redirect:/goApprovePage.do");
		   
	   return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 승인업체 관리페이지 가기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goManageFoodRoomPage.do", method=RequestMethod.GET)
	private ModelAndView manageFoodRoom(@ModelAttribute("storeDTO") StoreDTO storeDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<StoreDTO> lookApproveFoodRoomList = adminDAO.lookApproveFoodRoomList(storeDTO);	// 승인 요청 List로 보여주기
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/manageFoodRoomPage");
		mav.addObject("approveOk", lookApproveFoodRoomList);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 룸 목록 페이지 가기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/goRoomListPage.do", method=RequestMethod.GET)
	private ModelAndView RoomList(@RequestParam("fr_no") int fr_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<RoomDTO> roomList = adminDAO.roomList(fr_no);	// 승인 요청 List로 보여주기	
		 
		ModelAndView mav = new ModelAndView("/admin/RoomListPage");
		mav.addObject("roomList", roomList);
		mav.addObject("room_no", fr_no);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 룸정보 추가 페이지
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/addRoomInfo.do", method=RequestMethod.GET)
	private ModelAndView addRoomInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/addRoomInfoPage");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 룸정보 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/addRoomInfoGo.do", method=RequestMethod.POST)
	public ModelAndView addRoomInfo(@ModelAttribute("RoomDTO") RoomDTO roomDTO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("addPosts ==>" + roomDTO);

		ModelAndView mav = new ModelAndView();
		
		Map<String, String> roomImageMap	= uploadRoomImage(request, response);

		System.out.println("Map ==> " + roomImageMap);
		roomDTO.setFr_no(Integer.parseInt(roomImageMap.get("fr_no")));
		roomDTO.setFr_room_name(roomImageMap.get("fr_room_name"));
		roomDTO.setFr_room_person_no(roomImageMap.get("fr_room_person_no"));
		roomDTO.setFr_room_image(roomImageMap.get("fr_room_image"));
		System.out.println("RoomDTO ==> " + roomDTO);
		
		int result = adminDAO.addRoomImage(roomDTO);
		System.out.println("게시글 추가 controller 결과 freeboard_no ==> " + result);
		

		if(roomImageMap.get("fr_room_image") != null && roomImageMap.get("fr_room_image").length() != 0) {
			File srcFile = new File(FREEBOARD_IMAGE_REPO + "\\" + roomImageMap.get("fr_room_image"));
			File destDir = new File(FREEBOARD_IMAGE_REPO + "\\" + result);
			
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}

		mav = new ModelAndView("redirect:/addRoomInfo.do");
	
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 룸정보 이미지 첨부 메서드
	//-----------------------------------------------------------------------------------------------------------
	private Map<String, String> uploadRoomImage(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Map<String, String> roomImageMap = new HashMap<String, String>();
		String encoding	=	"utf-8";
		
		// 업로드할 파일의 경로를 지정한다.
		File				currentDirPath		= new File(FREEBOARD_IMAGE_REPO);
		
		DiskFileItemFactory	factory				= new DiskFileItemFactory();
		
		// 파일 경로를 설정한다.
		factory.setRepository(currentDirPath);
		
		// 업로드될 파일의 최대 크기를 설정한다.
		factory.setSizeThreshold(1024*1024*1024);
		
		ServletFileUpload uploadRoomImage = new ServletFileUpload(factory);
		
		try {
			// request객체에서 매개 변수를 List로 가져온다.
			List items = uploadRoomImage.parseRequest(request);
			
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
						String fileName = fileItem.getName().substring(idx+1);
						
						
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
