package com.dining.member.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.dining.dto.RoomDTO;
import com.dining.dto.StoreDTO;
import com.dining.dto.SelectDTO;

public interface MemberDAO {

	//-----------------------------------------------------------------------------------------------------------
    // 전체 업체목록
    //-----------------------------------------------------------------------------------------------------------
	public List<StoreDTO> allStoreList() throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
    // 이용 가능한 업체 지역(대분류)별 검색
    //-----------------------------------------------------------------------------------------------------------
	public List<StoreDTO> findStoreList(SelectDTO selectDTO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
    // 지역검색 이후 메뉴 및 인원으로 검색
    //-----------------------------------------------------------------------------------------------------------
	public List<StoreDTO> detailFindStoreList(SelectDTO selectDTO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 선택한 업체의 방 리스트 페이지
	//-----------------------------------------------------------------------------------------------------------
	public List<RoomDTO> selectStoreRoomList(HashMap<Object, Object> map) throws DataAccessException;
		
	//-----------------------------------------------------------------------------------------------------------
    // 룸 목록
    //-----------------------------------------------------------------------------------------------------------
	public List<RoomDTO> mRoomList(int fr_no) throws DataAccessException;
	
}
