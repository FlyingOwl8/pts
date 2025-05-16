package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class TestDTO {
    private Integer idTest;

    private Integer userId;
    private Integer statusId;

    private Float result;
    private Boolean success;

    private Date passDate;
}
