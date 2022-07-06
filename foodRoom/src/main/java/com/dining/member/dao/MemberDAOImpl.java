package com.dining.member.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dining.dto.ReservationDTO;
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
	// 선택한 업체의 예약 가능한 방 리스트 페이지
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<RoomDTO> selectStoreRoomList(HashMap<Object, Object> map) throws DataAccessException {
		
		List<RoomDTO> selectRoomList = sqlSession.selectList("mapper.room.selectRoomList", map);
		return selectRoomList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 선택한 업체의 예약 완료된 방 리스트 페이지
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<RoomDTO> completionRoomList(HashMap<Object, Object> map) throws DataAccessException {
		
		List<RoomDTO> completionRoomList = sqlSession.selectList("mapper.room.completionRoomList", map);
		return completionRoomList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 선택한 날짜와 업체의 예약 가능한 방 리스트 페이지
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<RoomDTO> selectDateRoomList(HashMap<Object, Object> map) throws DataAccessException {
		
		List<RoomDTO> selectDateRoomList = sqlSession.selectList("mapper.room.selectDateRoomList", map);
		return selectDateRoomList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 선택한 날짜와 업체의 예약 완료된 방 리스트 페이지
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<RoomDTO> completionDateRoomList(HashMap<Object, Object> map) throws DataAccessException {
		
		List<RoomDTO> completionDateRoomList = sqlSession.selectList("mapper.room.completionDateRoomList", map);
		return completionDateRoomList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 온라인 예약하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int onlineReservation(ReservationDTO reservationDTO) throws DataAccessException {	
		
		return sqlSession.insert("mapper.room.onlineReservation", reservationDTO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 예약내역 확인하기 페이지
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<HashMap<String, Object>> myReservation(HashMap<Object, Object> map) throws DataAccessException {
		
		List<HashMap<String, Object>> myReservation = sqlSession.selectList("mapper.room.myReservation", map);
		return myReservation;
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
