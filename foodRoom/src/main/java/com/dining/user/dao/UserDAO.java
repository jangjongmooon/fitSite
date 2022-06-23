package com.dining.user.dao;

import org.springframework.dao.DataAccessException;

import com.dining.dto.UserDTO;

public interface UserDAO {
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	public int addUser(UserDTO userDTO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	public UserDTO login(UserDTO userDTO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	public UserDTO findId(UserDTO userDTO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	public int checkId(UserDTO userDTO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	public UserDTO findPwd(UserDTO userDTO) throws DataAccessException;

	//-----------------------------------------------------------------------------------------------------------
	// 마이페이지 변경하기
	//-----------------------------------------------------------------------------------------------------------
	public int myPageUpdateGo(UserDTO userDTO) throws DataAccessException;
	
}
