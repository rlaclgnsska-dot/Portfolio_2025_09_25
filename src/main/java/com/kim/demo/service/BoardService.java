package com.kim.demo.service;

import org.springframework.stereotype.Service;

import com.kim.demo.dao.BoardDao;
import com.kim.demo.vo.Board;

@Service
public class BoardService {
	
	private BoardDao boardDao;
	
	public BoardService (BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	public Board getBoardById(int boardId) {
		return boardDao.getBoardById(boardId);
	}
	
	


}
