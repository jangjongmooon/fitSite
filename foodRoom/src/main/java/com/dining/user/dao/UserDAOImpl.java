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
	// ID 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int checkId(UserDTO userDTO) throws DataAccessException {
		System.out.println("dao checkId ==>" + userDTO);
		int result = sqlSession.selectOne("mapper.user.checkId", userDTO);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int signUp(UserDTO userDTO) throws DataAccessException {
		System.out.println("addUser signUp ==>" + userDTO);
		int result = sqlSession.insert("mapper.user.signUp", userDTO);
		System.out.println("signUp result ==>" + result);
		return result;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public UserDTO login(UserDTO userDTO) throws DataAccessException {
		System.out.println("userDTO 로그인 ==>" + userDTO);
		UserDTO result = sqlSession.selectOne("mapper.user.login", userDTO);
		System.out.println("userDTO 로그인 결과 ==>" + result);
		return result;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public UserDTO findId(UserDTO userDTO) throws DataAccessException {
		System.out.println("dao id찾기 ==>" + userDTO);
		UserDTO result = sqlSession.selectOne("mapper.user.findId", userDTO);
		System.out.println("dao id찾기 결과 ==>" + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public UserDTO findPwd(UserDTO userDTO) throws DataAccessException {
		System.out.println("dao pwd찾기 ==>" + userDTO);
		UserDTO result = sqlSession.selectOne("mapper.user.findPwd", userDTO);
		System.out.println("dao pwd찾기 결과 ==>" + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이페이지 변경하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int updateMyPage(UserDTO userDTO) throws DataAccessException {
		System.out.println("dao 마이페이지 변경하기 ==>" + userDTO);
		int result = sqlSession.update("mapper.user.updateMyPage", userDTO);
		System.out.println("dao 마이페이지 결과값 ==>" + result);
		return result; 
	}
	
	
}
