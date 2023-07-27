package com.vscg.itemmanagerapiv3.service;

import java.util.ArrayList;

import com.vscg.itemmanagerapiv3.model.DAOUser;
import com.vscg.itemmanagerapiv3.model.UserDTO;
import com.vscg.itemmanagerapiv3.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    private UserDao userDao;

    @Autowired
    @Lazy
    private PasswordEncoder bcryptEncoder;

    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("vlad".equals(username)) {
            return new User("vlad", "$2a$10$G6tKpR85fCYwJYP6RxArn.0IHmblNugCYy73pvHsUHfwtanVBGUSC",
                    new ArrayList<>());
        }
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DAOUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public DAOUser save(UserDTO user) {
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }
}