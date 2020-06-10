package com.example.rawredis.Service;


import com.example.rawredis.Dao.UserDao;
import com.example.rawredis.Dto.UserDto;
import com.example.rawredis.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class
JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),grantauthority(user.getRoles()));
    }

    public Set<GrantedAuthority> grantauthority(ArrayList<String> role)
     {
         Set<GrantedAuthority> auth = new HashSet<>();
         for (String i : role)
         {
             auth.add(new SimpleGrantedAuthority("ROLE_"+i));
         }

//         new SimpleGrantedAuthority()
         return auth;


     }

    public User save(UserDto user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRoles(user.getRoles());
        newUser.setEmail(user.getEmail());
        return userDao.save(newUser);
    }
}
