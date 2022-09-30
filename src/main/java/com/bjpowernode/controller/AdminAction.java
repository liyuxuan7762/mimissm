package com.bjpowernode.controller;

import com.bjpowernode.pojo.Admin;
import com.bjpowernode.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    private AdminService adminService = null;

    @RequestMapping("/login.action")
    public String Login(String name, String pwd, HttpServletRequest request) {
        Admin admin = adminService.Login(name, pwd);
        if (admin != null) {
            // 登陆成功
            request.setAttribute("admin", admin);
            return "main";
        } else {
            request.setAttribute("errmsg", "用户名或密码不正确");
            return "login";
        }
    }
}
