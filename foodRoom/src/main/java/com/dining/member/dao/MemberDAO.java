package com.dining.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.dining.dto.RoomDTO;
import com.dining.dto.StoreDTO;

public interface MemberDAO {

	//-----------------------------------------------------------------------------------------------------------
    // 전체 업체목록
    //-----------------------------------------------------------------------------------------------------------
	public List<StoreDTO> allStoreList() throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
    // 검색 업체목록
    //-----------------------------------------------------------------------------------------------------------
	public List<StoreDTO> findStoreList(String fr_address) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
    // 룸 목록
    //-----------------------------------------------------------------------------------------------------------
	public List<RoomDTO> mRoomList(int fr_no) throws DataAccessException;
	
}
