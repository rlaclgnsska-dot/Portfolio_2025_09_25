package com.kim.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kim.demo.vo.Project;

@Mapper
public interface ProjectDao {

	@Select("""
			SELECT p.id
				, p.regDate
				, p.updateDate
				, p.memberId
				, p.title
				, p.body
				, p.techStack
				, p.githubUrl
				, p.demoUrl
				, p.startDate
				, p.endDate
				, p.status
				, m.name as writerName
			FROM project p
			INNER JOIN `member` m
			ON p.memberId = m.id
			ORDER BY p.id DESC
			LIMIT #{limitStart}, #{itemsInApage}
			""")
	public List<Project> getProjects(int limitStart, int itemsInApage);

	@Select("""
			SELECT p.id
				, p.regDate
				, p.updateDate
				, p.memberId
				, p.title
				, p.body
				, p.techStack
				, p.githubUrl
				, p.demoUrl
				, p.startDate
				, p.endDate
				, p.status
				, m.name as writerName
			FROM project p
			INNER JOIN `member` m
			ON p.memberId = m.id
			WHERE p.id = #{id}
			""")
	public Project getProjectById(int id);

	@Insert("""
			INSERT INTO project
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{memberId}
					, title = #{title}
					, `body` = #{body}
					, techStack = #{techStack}
					, githubUrl = #{githubUrl}
					, demoUrl = #{demoUrl}
					, startDate = #{startDate}
					, endDate = #{endDate}
					, `status` = #{status}
			""")
	public void writeProject(int memberId, String title, String body, String techStack, 
			String githubUrl, String demoUrl, String startDate, String endDate, String status);

	@Delete("""
			DELETE FROM project
			WHERE id = #{id}
			""")
	public void deleteProject(int id);

	@Update("""
			UPDATE project
				SET updateDate = NOW()
					, title = #{title}
					, `body` = #{body}
					, techStack = #{techStack}
					, githubUrl = #{githubUrl}
					, demoUrl = #{demoUrl}
					, startDate = #{startDate}
					, endDate = #{endDate}
					, `status` = #{status}
				WHERE id = #{id}
			""")
	public void modifyProject(int id, String title, String body, String techStack, 
			String githubUrl, String demoUrl, String startDate, String endDate, String status);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT COUNT(*)
			FROM project
			""")
	public int getProjectsCnt();

}
