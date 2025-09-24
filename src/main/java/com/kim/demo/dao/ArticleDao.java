package com.kim.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kim.demo.vo.Article;

@Mapper
public interface ArticleDao {

	@Select("""
			<script>
				SELECT a.id
					, a.regDate
					, a.updateDate
					, a.memberId
					, a.boardId
					, a.title
					, a.body
					, a.hitCount
					, m.name as writerName
					, IFNULL(SUM(r.point), 0) as `point`
				FROM article a
				INNER JOIN `member` m
				ON a.memberId = m.id
				LEFT JOIN recommendpoint r
				ON r.relTypeCode = 'article'
				AND a.id = r.relId
				WHERE a.boardId = #{boardId}
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchType == 'title'">
							AND title LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchType == 'body'">
							AND `body` LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<otherwise>
							AND (
								title LIKE CONCAT('%', #{searchKeyword}, '%')
								OR `body` LIKE CONCAT('%', #{searchKeyword}, '%')
							)
						</otherwise>
					</choose>
				</if>		
				GROUP BY a.id	
				ORDER BY a.boardId=1 DESC, a.id DESC
				LIMIT #{limitStart}, #{itemsInApage}
			</script>
			""")
	public List<Article> getArticles(int boardId, int limitStart, int itemsInApage, String searchType, String searchKeyword);

	@Select("""
			SELECT a.id
				, a.regDate
				, a.updateDate
				, a.memberId
				, a.boardId
				, a.title
				, a.body
				, a.hitCount
				, m.name as writerName
				, IFNULL(SUM(r.point), 0) as `point`
			FROM article a
			INNER JOIN `member` m
			ON a.memberId = m.id
			LEFT JOIN recommendpoint r
			ON r.relTypeCode = 'article'
			AND a.id = r.relId
			WHERE a.id = #{id}
			GROUP BY a.id
			""")
	public Article getArticleById(int id);

	@Insert("""
			INSERT INTO article
				SET regDate = NOW()
					,updateDate = NOW()
					,boardId = #{boardId}
					,title = #{title}
					,`body` = #{body}
					,memberId = #{memberId}
			""")
	public Article writeArticle(String title, String body, int memberId, int boardId);

	@Delete("""
			DELETE FROM article
					WHERE id = #{id}
			""")
	public void deleteArticle(int id);

	@Update("""
			<script>
				UPDATE article
					SET updateDate = NOW()
						<if test="title != null and title != ''">
							, title = #{title}
						</if>
						<if test="body != null and body != ''">
							, `body` = #{body}
						</if>
					WHERE id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String body);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			<script>
				SELECT COUNT(*)
					FROM article
					WHERE boardId = #{boardId}
					<if test="searchKeyword != ''">
						<choose>
							<when test="searchType == 'title'">
								AND title LIKE CONCAT('%', #{searchKeyword}, '%')
							</when>
							<when test="searchType == 'body'">
								AND `body` LIKE CONCAT('%', #{searchKeyword}, '%')
							</when>
							<otherwise>
								AND (
									title LIKE CONCAT('%', #{searchKeyword}, '%')
									OR `body` LIKE CONCAT('%', #{searchKeyword}, '%')
								)
							</otherwise>
						</choose>
					</if>
			</script>
			""")
	public int getArticlesCnt(int boardId, String searchType, String searchKeyword);

	
	@Update("""
			UPDATE article
				SET hitCount = hitCount + 1
				WHERE id = #{id}
			""")
	public void increaseHitCount(int id);

	@Select("""
			SELECT COUNT(*)
				FROM recommendPoint r
					INNER JOIN article a
					ON r.relId = a.id
						WHERE relTypeCode = 'article'
						AND a.id = #{id}
						AND r.`point` = 1
			""")
	public int getGoodPoint(int id);

}
