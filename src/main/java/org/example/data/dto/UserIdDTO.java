package org.example.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserIdDTO {
    private Integer userId;

    public UserIdDTO(Integer userId) {
        this.userId = userId;
    }
}
