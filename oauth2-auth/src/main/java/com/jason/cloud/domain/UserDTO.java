package com.jason.cloud.domain;

import lombok.*;

import java.util.List;

/**
 * Created by macro on 2020/6/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;

}
