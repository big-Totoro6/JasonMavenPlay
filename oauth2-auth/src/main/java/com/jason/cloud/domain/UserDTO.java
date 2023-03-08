package com.jason.cloud.domain;

import lombok.*;

import java.util.ArrayList;
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
    private List<String> permissionList;
    private Integer status;
    private List<String> roles;

    public UserDTO(Long id, String username, String password,int status, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.status=status;
    }
}
