package com.dining.owner.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.dining.dto.StoreDTO;

public interface OwnerController {

	//-----------------------------------------------------------------------------------------------------------
	// 업체 등록 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView regiFoodRoom(@ModelAttribute("regi") StoreDTO storeDTO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
		
}
