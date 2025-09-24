package com.kim.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kim.demo.vo.Board;

@Mapper
public interface BoardDao {
	
	@Select("""
			SELECT *
				FROM board
				WHERE id = #{boardId}
			""")
	public Board getBoardById(int boardId);



}
