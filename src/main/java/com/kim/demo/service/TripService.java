package com.kim.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kim.demo.dao.TripDao;
import com.kim.demo.vo.Trip;

@Service
public class TripService {
    
    private TripDao tripDao;
    
    public TripService(TripDao tripDao) {
        this.tripDao = tripDao;
    }
    
    // 조회 관련
    public int getTripsCnt() {
        return tripDao.getTripsCnt();
    }
    
    public List<Trip> getTrips(int limitStart, int itemsInApage, int boardId) {
        return tripDao.getTrips(limitStart, itemsInApage, boardId);
    }
    
    public Trip getTripById(int id) {
        return tripDao.getTripById(id);
    }
    
    // 생성 관련
    public void writeTrip(int boardId, String province, String city, String placeName, 
                         int price, String body, int loginedMemberId) {
        tripDao.writeTrip(boardId, province, city, placeName, price, body, loginedMemberId);
    }
    
    public int getLastInsertId() {
        return tripDao.getLastInsertId();
    }
    
    // 수정 관련
    public void modifyTrip(int id, String province, String city, String placeName, 
                          int price, String body) {
        tripDao.modifyTrip(id, province, city, placeName, price, body);
    }
    
    // 삭제 관련
    public void deleteTrip(int id) {
        tripDao.deleteTrip(id);
    }
}