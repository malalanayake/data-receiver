package com.sysensor.data.controller;

import com.sysensor.data.config.URLConfig;
import com.sysensor.data.entity.User;
import com.sysensor.data.repository.BaseRepository;
import com.sysensor.data.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(URLConfig.USER)
public class UserAPI {

    BaseService<User, BaseRepository<User>> userService;

    public UserAPI(BaseService<User, BaseRepository<User>> userService) {
        this.userService = userService;
    }

    @RequestMapping("/getAll")
    @ResponseBody
    List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping("/add")
    @ResponseBody
    User create() throws Exception {
        User user = new User();
        user.setContextDate(LocalDate.now());
        user.setUserName("test");
        user.setPassWord("test");
        userService.create(user);
        return user;
    }
}
