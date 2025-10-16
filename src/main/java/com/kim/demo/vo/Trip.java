package com.kim.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   
@NoArgsConstructor      
@AllArgsConstructor 
public class Trip {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int boardId;
	private String province;
	private String city;
	private String placeName;
	private int price;
	private String body;
	private String boardName;
	private String nickname;
	
}
