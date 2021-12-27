package com.blog.socialshare.Controller;

import java.util.List;

import com.blog.socialshare.model.User;
import com.blog.socialshare.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "user/search", params = { "name" })
    @ResponseBody
    public List<User> search(@RequestParam("name") String name) {
        return userService.findByName(name);
    }
}
