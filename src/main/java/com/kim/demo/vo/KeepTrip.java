package com.kim.demo.vo;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoAutoStart
public class KeepTrip {
    private int id;
    private String regDate;
    private String updateDate;
    private int tripId;
    private int memberId;
}
