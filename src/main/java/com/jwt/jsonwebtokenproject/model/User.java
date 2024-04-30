package com.jwt.jsonwebtokenproject.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_users")


public class User implements UserDetails {

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Id
    @SequenceGenerator(
            name = "usergenerator",
            sequenceName = "usergenerator",
            allocationSize = 1
    )
    @GenericGenerator(
            name = "usergenerator",
            strategy = "increment"
    )
    @Getter @Setter private Integer id;
    @Getter @Setter  private String username;
    @Getter @Setter private String email;
    @Getter @Setter  private String password;
    @Getter @Setter private String role;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_" + role));
        return list;
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
