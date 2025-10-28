package com.kim.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kim.demo.vo.Member;

@Mapper
public interface MemberDao {
    
    @Insert("""
            INSERT INTO `member`
            SET reg_date = NOW(),
                update_date = NOW(),
                login_id = #{loginId},
                login_pw = #{loginPw},
                auth_level = 2,
                `name` = #{name},
                nickname = #{nickname},
                cellphonenum = #{cellphoneNum},
                email = #{email}
            """)
    public void memberJoin(String loginId, String loginPw, String name, String nickname, 
                          String cellphoneNum, String email);
    
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
            WHERE login_id = #{loginId}
            """)
    public Member getMemberByLoginId(String loginId);
}
