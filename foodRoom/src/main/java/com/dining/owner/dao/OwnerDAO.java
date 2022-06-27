package com.dining.owner.dao;

import org.springframework.dao.DataAccessException;

import com.dining.dto.StoreDTO;

public interface OwnerDAO {

	//-----------------------------------------------------------------------------------------------------------
	// 등록업체 수 확인
	//-----------------------------------------------------------------------------------------------------------
	public int checkRegiFoodRoom(String sfr_id) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체명 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	public int checkStoreName(String fr_store_name) throws DataAccessException;
		
	//-----------------------------------------------------------------------------------------------------------
	// 업체 등록 처리
	//-----------------------------------------------------------------------------------------------------------
	public int regiFoodRoom(StoreDTO storeDTO) throws DataAccessException;
	
}
