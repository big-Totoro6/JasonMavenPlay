package com.jason.example.service.impl;

import com.jason.example.common.enums.EnumSex;
import com.jason.example.common.enums.VipEnum;
import com.jason.example.dao.UserDao;
import com.jason.example.domain.User;
import com.jason.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> getLists() {
        ArrayList<User> list = new ArrayList<>();
        userDao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Long save(User user) {
        Logger.getGlobal().info(user.toString());
        User save = userDao.saveAndFlush(user);
        return save.getPhid();
    }
}
