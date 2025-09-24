package com.kim.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kim.demo.service.ReplyService;
import com.kim.demo.util.Util;
import com.kim.demo.vo.Rq;

@Controller
public class UsrReplyController {
	
	private ReplyService replyService;
	private Rq rq;
	
	UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String body, String relTypeCode, int relId) {
		
		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		replyService.writeReply(body, (int) rq.getLoginedMemberId(), relTypeCode, relId);
		
		int id = replyService.getLastInsertId();
		
		return Util.jsReplace(Util.f("%d번 댓글이 생성되었습니다", id), Util.f("../article/detail?id=%d", relId));
	}
	
	
}
