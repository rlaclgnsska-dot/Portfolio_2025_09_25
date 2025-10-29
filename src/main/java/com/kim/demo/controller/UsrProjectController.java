package com.kim.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kim.demo.service.ProjectService;
import com.kim.demo.util.Util;
import com.kim.demo.vo.Project;
import com.kim.demo.vo.Rq;

@Controller
public class UsrProjectController {
	
	private ProjectService projectService;
	private Rq rq;

	UsrProjectController(ProjectService projectService, Rq rq) {
		this.projectService = projectService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/project/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int page) {
	    
	    if (page <= 0) {
	        return rq.jsReturnOnView("페이지 번호가 올바르지 않습니다");
	    }

	    int getProjectsCnt = projectService.getProjectsCnt();
	    int itemsInApage = 9;

	    int limitStart = (page - 1) * itemsInApage;
	    int pagesCnt = (int) Math.ceil(((double) getProjectsCnt / itemsInApage));

	    List<Project> projects = projectService.getProjects(limitStart, itemsInApage);

	    // 그룹 단위 페이징 계산
	    int pageGroupSize = 10;
	    int currentPageGroup = (page - 1) / pageGroupSize;
	    int startPage = currentPageGroup * pageGroupSize + 1;
	    int endPage = startPage + pageGroupSize - 1;
	    
	    //실제 전체 페이지 수보다 endPage가 더 커지지 않도록 막아주는 보호 코드
	    if (endPage > pagesCnt) {
	        endPage = pagesCnt;
	    }

	    model.addAttribute("projects", projects);
	    model.addAttribute("getProjectsCnt", getProjectsCnt);
	    model.addAttribute("pagesCnt", pagesCnt);
	    model.addAttribute("page", page);
	    model.addAttribute("pageGroupSize", pageGroupSize);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);

	    return "usr/project/list";
	}
	
	@RequestMapping("/usr/project/detail")
	public String detail(Model model, int id) {
		
		Project project = projectService.getProjectById(id);
		
		if (project == null) {
			return rq.jsReturnOnView(Util.f("%d번 프로젝트는 존재하지 않습니다", id));
		}
		
		model.addAttribute("project", project);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		
		return "usr/project/detail";
	}
	
	@RequestMapping("/usr/project/write")
	public String write() {
		return "usr/project/write";
	}
	
	@RequestMapping("/usr/project/doWrite")
	@ResponseBody
	public String doWrite(String title, String body, String techStack, 
			String githubUrl, String demoUrl, String startDate, String endDate, 
			@RequestParam(defaultValue = "completed") String status) {
		
		if (Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요");
		}
		
		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		projectService.writeProject((int) rq.getLoginedMemberId(), title, body, techStack, 
				githubUrl, demoUrl, startDate, endDate, status);
		
		int id = projectService.getLastInsertId();
		
		return Util.jsReplace(Util.f("%d번 프로젝트가 생성되었습니다", id), Util.f("detail?id=%d", id));
	}
	
	@RequestMapping("/usr/project/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Project project = projectService.getProjectById(id);
		
		if (project == null) {
			return Util.jsHistoryBack(Util.f("%d번 프로젝트는 존재하지 않습니다", id));
		}
		
		if (project.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack(Util.f("%d번 프로젝트에 대한 권한이 없습니다", id));
		}
		
		projectService.deleteProject(id);
		
		return Util.jsReplace(Util.f("%d번 프로젝트를 삭제했습니다", id), "list");
	}
	
	@RequestMapping("/usr/project/modify")
	public String modify(Model model, int id) {
		
		Project project = projectService.getProjectById(id);
		
		if (project == null) {
			return rq.jsReturnOnView(Util.f("%d번 프로젝트는 존재하지 않습니다", id));
		}
		
		if (project.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsReturnOnView(Util.f("%d번 프로젝트에 대한 권한이 없습니다", id));
		}
		
		model.addAttribute("project", project);
		
		return "usr/project/modify";
	}
	
	@RequestMapping("/usr/project/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body, String techStack, 
			String githubUrl, String demoUrl, String startDate, String endDate, String status) {
		
		Project project = projectService.getProjectById(id);
		
		if (project == null) {
			return Util.jsHistoryBack(Util.f("%d번 프로젝트는 존재하지 않습니다", id));
		}
		
		if (project.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack(Util.f("%d번 프로젝트에 대한 권한이 없습니다", id));
		}
		
		projectService.modifyProject(id, title, body, techStack, githubUrl, demoUrl, startDate, endDate, status);
		
		return Util.jsReplace(Util.f("%d번 프로젝트를 수정했습니다", id), Util.f("detail?id=%d", id));
	}
	
}
