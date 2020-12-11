package com.example.volunteer.Service;


import com.example.volunteer.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public boolean addUser(User user);

    public User findUserById(int id);

    public boolean update(User user);

    public boolean deleteById(int id);
}
