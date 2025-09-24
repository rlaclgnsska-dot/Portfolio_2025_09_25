package com.kim.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kim.demo.vo.Member;

@Mapper
public interface MemberDao {
	
	@Insert("""
			INSERT INTO member
				SET regDate = NOW()
					, updateDate = NOW()
					, loginId = #{loginId}
					, loginPw = #{loginPw}
					, authLevel = 2
					, name = #{name}
					, nickname = #{nickname}
					, cellphoneNum = #{cellphoneNum}
					, cellphoneNum = #{email}
			""")
	public void memberJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT *
				FROM `member`
				WHERE id = #{id}
			""")
	public Member getMemberById(int id);

	@Select("""
			SELECT *
				FROM `member`
				WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);

}
