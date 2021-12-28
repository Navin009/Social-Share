package com.blog.socialshare.Controller;

import java.util.List;

import com.blog.socialshare.model.User;
import com.blog.socialshare.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logincustom")
    public String validateLogin(@ModelAttribute("userForm") User user, BindingResult bindingResult) {
        // LoginValidationService.validate(user, bindingResult);
        return "/index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
