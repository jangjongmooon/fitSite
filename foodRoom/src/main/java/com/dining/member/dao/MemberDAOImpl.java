package com.dining.member.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dining.dto.RoomDTO;
import com.dining.dto.SelectDTO;
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
    // 이용 가능한 업체 지역(대분류)별 검색
    //-----------------------------------------------------------------------------------------------------------
	@Override
	public List<StoreDTO> findStoreList(SelectDTO selectDTO) throws DataAccessException {
		
		List<StoreDTO> findStoreList = sqlSession.selectList("mapper.store.findStoreList", selectDTO);		
		System.out.println("결과값 ==>" + findStoreList);
		return findStoreList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
    // 지역검색 이후 메뉴 및 인원으로 검색
    //-----------------------------------------------------------------------------------------------------------
	@Override
	public List<StoreDTO> detailFindStoreList(SelectDTO selectDTO) throws DataAccessException {
		
		List<StoreDTO> detailFindStoreList = sqlSession.selectList("mapper.store.detailFindStoreList", selectDTO);		
		System.out.println("결과값 ==>" + detailFindStoreList);
		return detailFindStoreList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 선택한 업체의 방 리스트 페이지
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<RoomDTO> selectStoreRoomList(HashMap<Object, Object> map) throws DataAccessException {
		List<RoomDTO> selectRoomList = sqlSession.selectList("mapper.room.selectRoomList", map);
		return selectRoomList;
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
