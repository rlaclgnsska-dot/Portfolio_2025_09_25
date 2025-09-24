package com.kim.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kim.demo.vo.Board;
import com.kim.demo.vo.RecommendPoint;
import com.kim.demo.vo.Reply;

import lombok.Delegate;

@Mapper
public interface ReplyDao {
	
	@Insert("""
			INSERT INTO reply
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{memberId}
					, relTypeCode = #{relTypeCode}
					, relId = #{relId}
					, `body` = #{body}
			""")
	public void writeReply(String body, int loginedMemberId, String relTypeCode, int relId);

	@Select("SELECT LAST_INSERT_ID()")
	int getLastInsertId();

	@Select("""
			SELECT R.id
			, regDate
			, updateDate
			, memberId
			, relTypeCode
			, relId
			, `body`
			, M.nickname AS writrName
				FROM reply R
				INNER JOIN member M
				ON R.memberId = M.Id
				WHERE R.relTypeCode = #{relTypeCode}
				AND R.relId = #{relId}
			""")
	public List<Reply> getReplys(String relTypeCode, int relId);
	


}
