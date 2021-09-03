package com.triviet.project.viet24.service;

import com.triviet.project.viet24.entity.CustomUserDetail;
import com.triviet.project.viet24.entity.User;
import com.triviet.project.viet24.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();

        User user = userRepository.findByUsername(userName);
        grantedAuthorities.add(new SimpleGrantedAuthority(String .valueOf(user.getRole())));
        if(user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new CustomUserDetail((long) user.getId(), user.getUserName(), user.getEmail(), user.getPassword(),grantedAuthorities);
    }
}
