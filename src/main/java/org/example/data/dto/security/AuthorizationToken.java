package org.example.data.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorizationToken {
    private Integer subject;
    private Date issuedAt;
    private Date expiration;
    private Date refreshmentExpiration;
}
