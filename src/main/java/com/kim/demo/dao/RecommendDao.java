package com.kim.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kim.demo.vo.Board;
import com.kim.demo.vo.RecommendPoint;

import lombok.Delegate;

@Mapper
public interface RecommendDao {
	
	@Select("""
			SELECT *
				FROM recommendPoint
				WHERE memberId = #{loginedMemberId}
				AND relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	public RecommendPoint getRecommendPoint(int loginedMemberId, String relTypeCode, int relId);

	@Insert("""
			INSERT INTO recommendPoint
					SET memberId = #{loginedMemberId}
						, relTypeCode = #{relTypeCode}
						, relId = #{relId}
						, `point` = 1
			""")
	public void insertRecommendPoint(int loginedMemberId, String relTypeCode, int relId);
	
	@Delete("""
			DELETE FROM recommendPoint
					WHERE memberId = #{loginedMemberId}
					AND	relTypeCode = #{relTypeCode}
					AND	relId = #{relId}
			""")
	public void deleteRecommendPoint(int loginedMemberId, String relTypeCode, int relId);


	


}
