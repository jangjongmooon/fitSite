package com.dining.owner.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dining.dto.StoreDTO;

@Repository("ownerDAO")
public class OwnerDAOImpl implements OwnerDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// 등록업체 수 확인
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int checkRegiFoodRoom(String sfr_id) throws DataAccessException {
	
		return sqlSession.selectOne("mapper.store.checkRegiFoodRoom", sfr_id);	
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체명 중복 체크
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int checkStoreName(String fr_store_name) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.store.checkStoreName", fr_store_name);
	}	
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체 등록 처리
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int regiFoodRoom(StoreDTO storeDTO) throws DataAccessException {
		
		return sqlSession.insert("mapper.store.regiFoodRoom", storeDTO);
	}	
	
}
