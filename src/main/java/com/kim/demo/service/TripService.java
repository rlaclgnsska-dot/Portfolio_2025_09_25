package com.kim.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kim.demo.dao.TripDao;
import com.kim.demo.vo.Trip;

@Service
public class TripService {
	
	private TripDao tripDao;
	
	public TripService (TripDao tripDao) {
		this.tripDao = tripDao;
	}

	public int getTripsCnt() {
		return tripDao.getTripsCnt();
	}

	public List<Trip> getTrips(int limitStart, int itemsInApage) {
		return tripDao.getTrips(limitStart, itemsInApage);
	}


	public int getLastInsertId() {
		return tripDao.getLastInsertId();
	}

	public void writeTrip(int boardId, String provinceCity, String placeName,
			int price, String body, int loginedMemberId) {
		tripDao.writeTrip(boardId, provinceCity, placeName, price, body, loginedMemberId);
		
	}

	public Trip getTripById(int id) {
		return tripDao.getTripById(id);
	}


	public void deleteTrip(int id) {
		tripDao.deleteTrip(id);
	}

	public void modifyTrip(int id, String provinceCity, String placeName, int price, String description) {
		tripDao.modifyTrip(id, provinceCity, placeName, price, description);
	}



}
