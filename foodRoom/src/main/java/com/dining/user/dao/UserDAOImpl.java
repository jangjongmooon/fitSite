package com.dining.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.dining.vo.UserVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addUser(UserVO userVO) throws DataAccessException {
		System.out.println("daoVO ==>" + userVO);
		int result = sqlSession.insert("mapper.user.addUser", userVO);
		return result;
	}
	
	
	
	
}
