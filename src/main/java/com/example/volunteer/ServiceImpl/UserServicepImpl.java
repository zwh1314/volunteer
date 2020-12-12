package com.example.volunteer.ServiceImpl;

import com.example.volunteer.Entity.User;
import com.example.volunteer.Dao.UserDao;
import com.example.volunteer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> findUserByUserId(int userid){
        List<User> user_list =userdao.getUserByUserId(userid);
        //System.out.println(user);
        return user_list;
    }

    @Override
    public boolean update(User user) {
        if(userdao.update(user) >= 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteByUserId(int userid) {
        if(userdao.deleteByUserId(userid) >= 0)
            return true;
        else
            return false;
    }


}
