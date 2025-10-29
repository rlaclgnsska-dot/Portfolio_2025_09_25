package com.kim.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String title;
	private String body;
	private String techStack;
	private String githubUrl;
	private String demoUrl;
	private String startDate;
	private String endDate;
	private String status;
	private String writerName;
}
