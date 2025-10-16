package com.kim.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kim.demo.vo.Trip;

@Mapper
public interface KeepTripDao {

	@Select("""
			SELECT COUNT(*)
			FROM keep_trip
			WHERE memberId = #{memberId}
			""")
	int getTripsCnt(int memberId);

	@Select("""
			SELECT t.id
			, DATE_FORMAT(t.regDate, "%Y-%m-%d") AS regDate
			, DATE_FORMAT(t.updateDate, "%Y-%m-%d") AS updateDate
			, t.memberId
			, t.boardId
			, t.province_city AS provinceCity
			, t.place_name AS placeName
			, t.price
			, t.`body`
			, b.name AS boardName
			, m.nickname AS nickname
			FROM trip t
			INNER JOIN keep_trip k
			ON t.id = k.tripId
			INNER JOIN trip_board b
			ON t.boardId = b.id
			INNER JOIN member m
			ON t.memberId = m.id
			WHERE k.memberId = #{memberId}
			GROUP BY t.id
			ORDER BY t.id DESC
			LIMIT #{limitStart}, #{itemsInApage}
			""")
	List<Trip> getKeepTrips(int limitStart, int itemsInApage, int memberId);

	@Insert("""
			<script>
			    INSERT INTO keep_trip (regDate, updateDate, tripId, memberId)
				VALUES
			    <foreach collection="tripCheckList" item="tripId" separator=",">
			        (NOW(), NOW(), #{tripId}, #{loginedMemberId})
			    </foreach>
			</script>
		    """)
	
	void doKeepTrip(@Param("tripCheckList") List<Integer> tripCheckList, @Param("loginedMemberId") int loginedMemberId);

	@Select("""
			SELECT tripId
			FROM keep_trip
			WHERE memberId = #{loginedMemberId}
			""")
	List<Integer> memberKeepTrips(int loginedMemberId);

	@Delete("""
			<script>
				DELETE FROM keep_trip
				WHERE memberId = #{loginedMemberId}
				AND tripId IN
					<foreach collection="duplicated" item="tripId" open="(" separator="," close=")">
						#{tripId}
					</foreach>
			</script>
			""")
	void doKeepTripDelete(List<Integer> duplicated, int loginedMemberId);

}
