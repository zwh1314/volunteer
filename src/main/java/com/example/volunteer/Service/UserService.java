package com.example.volunteer.Service;


import com.example.volunteer.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public boolean addUser(User user);

    public List<User> findUserByUserId(int userid);

    public boolean update(User user);

    public boolean deleteByUserId(int userid);
}
