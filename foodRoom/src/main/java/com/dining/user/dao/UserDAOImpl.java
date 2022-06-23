package com.dining.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dining.dto.UserDTO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addUser(UserDTO userDTO) throws DataAccessException {
		System.out.println("addUser userDTO ==>" + userDTO);
		int result = sqlSession.insert("mapper.user.addUser", userDTO);
		System.out.println("addUser result ==>" + result);
		return result;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public UserDTO login(UserDTO userDTO) throws DataAccessException {
		System.out.println("userDTO 로그인 ==>" + userDTO);
		UserDTO result = sqlSession.selectOne("mapper.mapper.login", userDTO);
		System.out.println("userDTO 로그인 결과 ==>" + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public UserDTO findId(UserDTO userDTO) throws DataAccessException {
		System.out.println("dao id찾기 ==>" + userDTO);
		UserDTO result = sqlSession.selectOne("mapper.mapper.findId", userDTO);
		System.out.println("dao id찾기 결과 ==>" + result);
		return result;
	}

	//-----------------------------------------------------------------------------------------------------------
	// ID 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int checkId(UserDTO userDTO) throws DataAccessException {
		return sqlSession.selectOne("mapper.mapper.checkId", userDTO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public UserDTO findPwd(UserDTO userDTO) throws DataAccessException {
		System.out.println("dao pwd찾기 ==>" + userDTO);
		UserDTO result = sqlSession.selectOne("mapper.mapper.findPwd", userDTO);
		System.out.println("dao pwd찾기 결과 ==>" + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이페이지 변경하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int myPageUpdateGo(UserDTO userDTO) throws DataAccessException {
		System.out.println("테스트값 ==>" + userDTO);
		int result = sqlSession.update("mapper.mapper.myPageUpdateGo", userDTO);
		System.out.println("결과값 ==>" + result);
		return result; 
	}
	
	
}
