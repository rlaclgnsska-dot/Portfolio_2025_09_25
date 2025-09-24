package com.kim.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kim.demo.service.MemberService;
import com.kim.demo.util.Util;
import com.kim.demo.vo.Member;
import com.kim.demo.vo.ResultData;
import com.kim.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	private Rq rq;
	
	public UsrMemberController (MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin (HttpSession session, String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if (session.getAttribute("loginedMember") != null) {
			return ResultData.from("F-L", "로그아웃 후 이용해주세요");
		}
		
		if (Util.empty(loginId)) {
			return ResultData.from("F-1" ,"아이디를 입력해주세요");
		}
		
		if (Util.empty(loginPw)) {
			return ResultData.from("F-2" ,"비밀번호를 입력해주세요");
		}
		
		if (Util.empty(name)) {
			return ResultData.from("F-3" ,"이름을 입력해주세요");
		}
		
		if (Util.empty(nickname)) {
			return ResultData.from("F-4" ,"닉네임을 입력해주세요");
		}
		
		if (Util.empty(cellphoneNum)) {
			return ResultData.from("F-5" ,"핸드폰번호를 입력해주세요");
		}
		
		if (Util.empty(email)) {
			return ResultData.from("F-6" ,"이메일을 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member != null) {
			return ResultData.from("F-7" ,Util.f("%s는 이미 사용중인 아이디입니다", loginId));
		}
		
		memberService.memberJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberService.getLastInsertId();
		
		return ResultData.from("F-1", "가입 완료", memberService.getMemberById(id));
	}
	
	@RequestMapping("/usr/member/login")
	public String login() {
		return "usr/member/login";
	}
	
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		
		Member loginedMember = memberService.getMemberByLoginId(loginId);
		
		if (loginedMember == null) {
			return Util.jsHistoryBack(Util.f("%s는 존재하지 않는 아이디입니다", loginId));
		}
		
		if (loginedMember.getLoginPw().equals(loginPw) == false) {
			return Util.jsHistoryBack("비밀번호가 일치하지 않습니다");
		}
		
		rq.Login(loginedMember);
		
		return Util.jsReplace(Util.f("%s님 환영합니다", loginedMember.getNickname()), "/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		
		rq.Logout();
		
		return Util.jsReplace("로그아웃되었습니다", "/");
	}
	

	
	
}