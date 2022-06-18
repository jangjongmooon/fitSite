package com.dining.user.dao;

import org.springframework.dao.DataAccessException;
import com.dining.vo.UserVO;

public interface UserDAO {
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	public int addUser(UserVO userVO) throws DataAccessException;
	
	
	
}
