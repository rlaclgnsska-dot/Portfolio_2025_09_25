package com.kim.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kim.demo.dao.KeepTripDao;
import com.kim.demo.vo.Trip;

@Service
public class KeepTripService {
	
	private static KeepTripDao keepTripDao;
	
	public KeepTripService (KeepTripDao keepTripDao) {
		KeepTripService.keepTripDao = keepTripDao;
	}

	public int getTripsCnt(int memberId) {
		return keepTripDao.getTripsCnt(memberId);
	}

	public List<Trip> getKeepTrips(int limitStart, int itemsInApage, int memberId) {
		return keepTripDao.getKeepTrips(limitStart, itemsInApage, memberId);
	}

	public static void doKeepTrip(List<Integer> tripIds, int loginedMemberId) {
		keepTripDao.doKeepTrip(tripIds, loginedMemberId);
		
	}

	public static List<Integer> memberKeepTrips(int loginedMemberId) {
		return keepTripDao.memberKeepTrips(loginedMemberId);
	}

	public static void doKeepTripDelete(List<Integer> duplicated, int loginedMemberId) {
		keepTripDao.doKeepTripDelete(duplicated, loginedMemberId);
	}





}
