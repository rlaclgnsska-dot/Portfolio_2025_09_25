package com.kim.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kim.demo.vo.Trip;

import lombok.Delegate;

@Mapper
public interface TripDao {

	@Select("""
			SELECT COUNT(*)
			FROM trip
			""")
	int getTripsCnt();

	@Select("""
			SELECT t.id
			, t.regDate
			, t.updateDate
			, t.memberId
			, t.boardId
			, t.trip_start_time AS tripStartTime
			, t.trip_end_time AS tripEndTime
			, t.province_city AS provinceCity
			, t.place_name AS placeName
			, t.price
			, t.description
			, b.name AS boardName
			, m.nickname AS nickname
			FROM trip t
			INNER JOIN trip_board b
			ON t.boardId = b.id
			INNER JOIN member m
			ON t. memberId = m.id
			GROUP BY t.id	
			ORDER BY t.id DESC
			LIMIT #{limitStart}, #{itemsInApage}
			""")
	List<Trip> getTrips(int limitStart, int itemsInApage);

	@Insert("""
	        INSERT INTO trip
	        SET regDate = NOW(),
	            updateDate = NOW(),
	            memberId = #{loginedMemberId},
	            boardId = #{boardId},
	            province_city = #{provinceCity},
	            place_name = #{placeName},
	            price = #{price},
	            `body` = #{body}
	        """)
	void writeTrip(int boardId, String provinceCity, String placeName, int price, String body, int loginedMemberId);

	@Select("SELECT LAST_INSERT_ID()")
	int getLastInsertId();

	@Select("""
			SELECT *
			FROM trip t
			INNER JOIN trip_board b
			ON t.boardId = b.id
			INNER JOIN member m
			ON t. memberId = m.id
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
            SET updateDate = NOW(),
            	province_city = #{provinceCity},
	            placeName = #{placeName},
	            price = #{price},
	            `description` = #{description}
	            WHERE id = #{id}
	        """)
	void modifyTrip(int id, String provinceCity, String placeName, int price, String description);
	


}
