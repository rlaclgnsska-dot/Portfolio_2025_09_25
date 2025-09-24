package com.kim.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kim.demo.dao.ReplyDao;
import com.kim.demo.vo.Reply;

@Service
public class ReplyService {
	
	private ReplyDao replyDao;
	
	public ReplyService (ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public void writeReply(String body, int loginedMemberId, String relTypeCode, int relId) {
		replyDao.writeReply(body, loginedMemberId, relTypeCode, relId);
	}

	public int getLastInsertId() {
		return replyDao.getLastInsertId();
	}

	public List<Reply> getReplys(String relTypeCode, int relId) {
		return replyDao.getReplys(relTypeCode, relId);
	}



	
	
	


}
