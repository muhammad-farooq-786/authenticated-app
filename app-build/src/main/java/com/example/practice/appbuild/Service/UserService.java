package com.example.practice.appbuild.Service;

import com.example.practice.appbuild.DAO.UserRepo;
import com.example.practice.appbuild.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepo.findById(id);
    }

    public User save(User reqUser) {
        User user = userRepo.save(reqUser);
        return user;
    }

    public void deleteById(String id) {
        userRepo.deleteById(id);
    }

    public Optional<User> findByUserName(String name) {
        return userRepo.findByUserName(name);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUserName(username)
                .map(User::new)
                .orElseThrow(()-> new UsernameNotFoundException("User Name not found"));
    }
}
