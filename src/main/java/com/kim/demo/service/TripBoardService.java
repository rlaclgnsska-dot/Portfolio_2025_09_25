package com.kim.demo.service;

import org.springframework.stereotype.Service;

import com.kim.demo.dao.TripBoardDao;
import com.kim.demo.vo.TripBoard;

@Service
public class TripBoardService {
	
	private TripBoardDao tripboardDao;
	
	public TripBoardService (TripBoardDao tripboardDao) {
		this.tripboardDao = tripboardDao;
	}

	


}
