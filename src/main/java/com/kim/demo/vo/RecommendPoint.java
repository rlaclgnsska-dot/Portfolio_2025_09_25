package com.kim.demo.vo;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoAutoStart
public class RecommendPoint {
	private int id;
	private int memberId;
	private String relTypeCode;
	private int relId;
	private int point;
	
}
