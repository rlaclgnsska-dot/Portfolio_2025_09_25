package com.kim.demo.vo;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoAutoStart
public class TripBoard {
	private int id;
	private String regDate;
	private String updateDate;
	private String code;
	private String name;
	
}
