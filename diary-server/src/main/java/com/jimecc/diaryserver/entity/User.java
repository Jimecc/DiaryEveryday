package com.jimecc.diaryserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @author Jim
 * @Description
 * @createTime 2023年02月22日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    private int id;
    private int age;
    private String name;
    private String username;
    private String password;
    private String info;
    private String email;
    private Date create_date;
    // 一些权限设置
    private int banned;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
