package com.kim.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kim.demo.service.ArticleService;
import com.kim.demo.service.BoardService;
import com.kim.demo.service.MemberService;
import com.kim.demo.service.ReplyService;
import com.kim.demo.util.Util;
import com.kim.demo.vo.Article;
import com.kim.demo.vo.Board;
import com.kim.demo.vo.Reply;
import com.kim.demo.vo.Rq;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	private BoardService boardService;
	private ReplyService replyService;
	private MemberService memberService;
	private Rq rq;

	UsrArticleController(ArticleService articleService, BoardService boardService, ReplyService replyService, MemberService memberService, Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.replyService = replyService;
		this.memberService = memberService;
		this.rq = rq;
	}
	
	List<Article> articles;
	int lastArticleId;
	
	@RequestMapping("/usr/article/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int boardId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "title") String searchType, @RequestParam(defaultValue = "") String searchKeyword) {
	    
	    if (page <= 0) {
	        return rq.jsReturnOnView("페이지 번호가 올바르지 않습니다");
	    }

	    Board board = boardService.getBoardById(boardId);

	    if (board == null) {
	        return rq.jsReturnOnView("존재하지 않는 게시판입니다");
	    }

	    int getArticlesCnt = articleService.getArticlesCnt(boardId, searchType, searchKeyword);
	    int itemsInApage = 10;

	    int limitStart = (page - 1) * itemsInApage;
	    int pagesCnt = (int) Math.ceil(((double) getArticlesCnt / itemsInApage));

	    List<Article> articles = articleService.getArticles(board.getId(), limitStart, itemsInApage,  searchType, searchKeyword);

	    // 그룹 단위 페이징 계산
	    int pageGroupSize = 10;
	    int currentPageGroup = (page - 1) / pageGroupSize;
	    int startPage = currentPageGroup * pageGroupSize + 1;
	    int endPage = startPage + pageGroupSize - 1;
	    
	    //실제 전체 페이지 수보다 endPage가 더 커지지 않도록 막아주는 보호 코드
	    if (endPage > pagesCnt) {
	        endPage = pagesCnt;
	    }

	    model.addAttribute("articles", articles);
	    model.addAttribute("searchType", searchType);
	    model.addAttribute("searchKeyword", searchKeyword);
	    model.addAttribute("board", board);
	    model.addAttribute("getArticlesCnt", getArticlesCnt);
	    model.addAttribute("pagesCnt", pagesCnt);
	    model.addAttribute("page", page);
	    
	    model.addAttribute("pageGroupSize", pageGroupSize);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);

	    return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String detail(HttpServletRequest req, HttpServletResponse resp , Model model, int id) {
		
		Cookie oldCookie = null;
		Cookie[] cookies = req.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("hitCount")) {
					oldCookie = cookie;
				}
			}
		}
		
		if (oldCookie != null) {
			if (oldCookie.getValue().contains("[" + id + "]") == false) {
				articleService.increaseHitCount(id);
				oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(5);
				resp.addCookie(oldCookie);
			}
		} 
		
		else {
			articleService.increaseHitCount(id);
			Cookie newCookie = new Cookie("hitCount", "[" + id + "]");
			newCookie.setPath("/");
			newCookie.setMaxAge(5);
			resp.addCookie(newCookie);
		}
		
		Article article = articleService.getArticleById(id);
		
		List<Reply> replys = replyService.getReplys("article", id);
		
		model.addAttribute("article", article);
		model.addAttribute("replys", replys);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/write")
	public String write() {
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String body, int boardId) {
		
		if (Util.empty(title)) {
			return Util.jsHistoryBack("제목를 입력해주세요");
		}
		
		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		articleService.writeArticle(title, body, (int) rq.getLoginedMemberId(), boardId);
		
		int id = articleService.getLastInsertId();
		
		return Util.jsReplace(Util.f("%d번 게시물이 생성되었습니다", id), Util.f("detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack(Util.f("%d번 게시물에 대한 권한이 없습니다", id));
		}
		
		articleService.deleteArticle(id);
		
		return Util.jsReplace(Util.f("%d번 게시물을 삭제했습니다", id), "list");
	}
	@RequestMapping("/usr/article/modify")
	public String modify(Model model, int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return rq.jsReturnOnView(Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsReturnOnView(Util.f("%d번 게시물에 대한 권한이 없습니다", id));
		}
		
		model.addAttribute("article", article);
		
		
		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack(Util.f("%d번 게시물에 대한 권한이 없습니다", id));
		}
		
		articleService.modifyArticle(id, title, body);
		
		return Util.jsReplace(Util.f("%d번 게시물을 수정했습니다", id), Util.f("detail?id=%d", id));
	}
	
}