package com.example.volunteer.ServiceImpl;

import com.example.volunteer.Entity.User;
import com.example.volunteer.Dao.UserDao;
import com.example.volunteer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicepImpl implements UserService {
    @Autowired
    UserDao userdao;

    @Override
    public boolean addUser(User user) {
        if(userdao.insertUser(user)>=0)
            return true;
        else
            return false;
    }

    @Override
    public User findUserById(int id){
        User user =userdao.getUserById(id);
        //System.out.println(user);
        return user;
    }

    @Override
    public boolean update(User user) {
        if(userdao.update(user) >= 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteById(int id) {
        if(userdao.deleteById(id) >= 0)
            return true;
        else
            return false;
    }


}
