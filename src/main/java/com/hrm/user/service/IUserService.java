package com.hrm.user.service;

import com.hrm.commons.beans.User;
import com.hrm.utils.PageModel;

import java.util.List;

public interface IUserService {
    User findUserByLoginUser(User user);

    List<User> findUser(User user, PageModel pageModel);

    int findUserCount(User user);

    User findUserById(Integer id);

    int modifyUser(User user);

    int addUser(User user);

    int removeUser(Integer[] ids);

    User findLoginname(String loginname);
}
