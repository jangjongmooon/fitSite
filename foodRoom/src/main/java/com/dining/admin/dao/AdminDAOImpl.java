package com.dining.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dining.dto.RoomDTO;
import com.dining.dto.StoreDTO;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
    // 미승인된 업체  List
    //-----------------------------------------------------------------------------------------------------------
	@Override
	public List<StoreDTO> unapproveFoodRoomList() throws DataAccessException {
		
		
		List<StoreDTO> approveList = sqlSession.selectList("mapper.store.unapproveFoodRoomList");		
		System.out.println("결과값 ==>" + approveList);
		return approveList; 	
		
	}

	//-----------------------------------------------------------------------------------------------------------
	// 미등록 업체 승인하기 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int approveFoodRoom(StoreDTO storeDTO) throws DataAccessException {
		
		return sqlSession.update("mapper.store.approveFoodRoom", storeDTO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체 승인시 fr_class를 12로 변경 
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int changeOwnerClass(StoreDTO storeDTO) throws DataAccessException {
		System.out.println("승인 시 fr_class 변경 테스트값 ==> " + storeDTO);
		
		return sqlSession.update("mapper.store.changeOwnerClass", storeDTO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 승인업체 목록
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<StoreDTO> lookApproveFoodRoomList(StoreDTO storeDTO) throws DataAccessException {
		List<StoreDTO> lookApproveFoodRoomList = sqlSession.selectList("mapper.store.lookApproveFoodRoomList", storeDTO);	
		return lookApproveFoodRoomList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 룸 목록
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<RoomDTO> roomList(int fr_no) throws DataAccessException {
		List<RoomDTO> roomList = sqlSession.selectList("mapper.room.roomList", fr_no);	
		return roomList;
	}
		
	//-----------------------------------------------------------------------------------------------------------
	// 룸 정보 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addRoomImage(RoomDTO roomDTO) throws DataAccessException {
		System.out.println("addRoomImage DAO ==> " + roomDTO);
		int result = sqlSession.insert("mapper.room.addRoomImage", roomDTO);
		return result;
	}
	
}
