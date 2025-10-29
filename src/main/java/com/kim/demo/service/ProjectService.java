package com.kim.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kim.demo.dao.ProjectDao;
import com.kim.demo.vo.Project;

@Service
public class ProjectService {
	
	private ProjectDao projectDao;
	
	public ProjectService(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void writeProject(int memberId, String title, String body, String techStack, 
			String githubUrl, String demoUrl, String startDate, String endDate, String status) {
		projectDao.writeProject(memberId, title, body, techStack, githubUrl, demoUrl, startDate, endDate, status);
	}

	public Project getProjectById(int id) {
		return projectDao.getProjectById(id);
	}

	public void deleteProject(int id) {
		projectDao.deleteProject(id);
	}

	public void modifyProject(int id, String title, String body, String techStack, 
			String githubUrl, String demoUrl, String startDate, String endDate, String status) {
		projectDao.modifyProject(id, title, body, techStack, githubUrl, demoUrl, startDate, endDate, status);
	}

	public List<Project> getProjects(int limitStart, int itemsInApage) {
		return projectDao.getProjects(limitStart, itemsInApage);
	}
	
	public int getLastInsertId() {
		return projectDao.getLastInsertId();
	}

	public int getProjectsCnt() {
		return projectDao.getProjectsCnt();
	}

}
