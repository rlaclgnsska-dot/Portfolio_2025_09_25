package com.kim.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.kim.demo.vo.Trip;

@Mapper
public interface TripDao {
    
    @Select("""
            SELECT COUNT(*)
            FROM trip
            """)
    int getTripsCnt();
    
    @Select("""
            <script>
                SELECT 
                    t.id,
                    DATE_FORMAT(t.reg_date, '%Y-%m-%d') AS regDate,
                    DATE_FORMAT(t.update_date, '%Y-%m-%d') AS updateDate,
                    t.member_id,
                    t.board_id,
                    t.province,
                    t.city,
                    t.place_name,
                    t.price,
                    t.body,
                    b.name AS boardName,
                    m.nickname
                FROM trip t
                INNER JOIN trip_board b ON t.board_id = b.id
                INNER JOIN member m ON t.member_id = m.id
                WHERE 1=1
                <if test="boardId != 0">
                    AND t.board_id = #{boardId}
                </if>
                ORDER BY t.id DESC
                LIMIT #{limitStart}, #{itemsInApage}
            </script>
            """)
    List<Trip> getTrips(int limitStart, int itemsInApage, int boardId);
    
    @Insert("""
            INSERT INTO trip
            SET reg_date = NOW(),
                update_date = NOW(),
                member_id = #{loginedMemberId},
                board_id = #{boardId},
                province = #{province},
                city = #{city},
                place_name = #{placeName},
                price = #{price},
                body = #{body}
            """)
    void writeTrip(int boardId, String province, String city, String placeName, int price, String body, int loginedMemberId);
    
    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();
    
    @Select("""
            SELECT 
                t.id,
                DATE_FORMAT(t.reg_date, '%Y-%m-%d') AS regDate,
                DATE_FORMAT(t.update_date, '%Y-%m-%d') AS updateDate,
                t.member_id,
                t.board_id,
                t.province,
                t.city,
                t.place_name,
                t.price,
                t.body,
                b.name AS boardName,
                m.nickname
            FROM trip t
            INNER JOIN trip_board b ON t.board_id = b.id
            INNER JOIN member m ON t.member_id = m.id
            WHERE t.id = #{id}
            """)
    Trip getTripById(int id);
    
    @Delete("""
            DELETE FROM trip
            WHERE id = #{id}
            """)
    void deleteTrip(int id);
    
    @Update("""
            UPDATE trip
            SET update_date = NOW(),
                province = #{province},
                city = #{city},
                place_name = #{placeName},
                price = #{price},
                body = #{body}
            WHERE id = #{id}
            """)
    void modifyTrip(int id, String province, String city, String placeName, int price, String body);
}