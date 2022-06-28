package com.dining.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dining.dto.RoomDTO;
import com.dining.dto.StoreDTO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
    // 전체 업체목록
    //-----------------------------------------------------------------------------------------------------------
	@Override
	public List<StoreDTO> allStoreList() throws DataAccessException {
		
		List<StoreDTO> allStoreList = sqlSession.selectList("mapper.store.allStoreList");		
		System.out.println("결과값 ==>" + allStoreList);
		return allStoreList;
	}

	//-----------------------------------------------------------------------------------------------------------
    // 검색 업체목록
    //-----------------------------------------------------------------------------------------------------------
	@Override
	public List<StoreDTO> findStoreList(String fr_address) throws DataAccessException {
		
		List<StoreDTO> findStoreList = sqlSession.selectList("mapper.store.findStoreList", fr_address);		
		System.out.println("결과값 ==>" + findStoreList);
		return findStoreList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
    // 룸 목록
    //-----------------------------------------------------------------------------------------------------------
	@Override
	public List<RoomDTO> mRoomList(int fr_no) throws DataAccessException {

		List<RoomDTO> mRoomList = sqlSession.selectList("mapper.room.mRoomList", fr_no);
		System.out.println("결과값 ==>" + mRoomList);
		return mRoomList;
	}

}
