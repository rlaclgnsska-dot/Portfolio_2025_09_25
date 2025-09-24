package com.kim.demo.service;

import org.springframework.stereotype.Service;

import com.kim.demo.dao.MemberDao;
import com.kim.demo.vo.Member;

@Service
public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService (MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void memberJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		memberDao.memberJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
	}

	public int getLastInsertId() {
		return memberDao.getLastInsertId();
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

}
