package com.kim.demo.service;

import org.springframework.stereotype.Service;

import com.kim.demo.dao.RecommendDao;
import com.kim.demo.vo.RecommendPoint;
import com.kim.demo.vo.ResultData;

@Service
public class RecommendService {
	
	private RecommendDao recommendDao;
	
	public RecommendService (RecommendDao recommendDao) {
		this.recommendDao = recommendDao;
	}

	public ResultData<RecommendPoint> getRecommendPoint(int loginedMemberId, String relTypeCode, int relId) {
		
		RecommendPoint recommendPoint = recommendDao.getRecommendPoint(loginedMemberId, relTypeCode, relId);
		
		if (recommendPoint == null) {
			return ResultData.from("F-1", "좋아요 정보 없음");
		}
		
		return ResultData.from("S-1", "좋아요 정보 있음", recommendPoint);
	}
	
	public void insertRecommendPoint(int loginedMemberId, String relTypeCode, int relId) {
		recommendDao.insertRecommendPoint(loginedMemberId, relTypeCode, relId);
	}

	public void deleteRecommendPoint(int loginedMemberId, String relTypeCode, int relId) {
		recommendDao.deleteRecommendPoint(loginedMemberId, relTypeCode, relId);
	}


	
	
	


}
