package com.kim.demo.vo;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoAutoStart
public class Trip {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int boardId;
	private String tripStartTime;
	private String tripEndTime;
	private String provinceCity;
	private String placeName;
	private int price;
	private String body;
	private String boardName;
	private String nickname;
	
}
