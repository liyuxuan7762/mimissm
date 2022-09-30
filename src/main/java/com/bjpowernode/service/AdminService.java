package com.bjpowernode.service;

import com.bjpowernode.pojo.Admin;

public interface AdminService {
    Admin Login(String name, String pwd);
}
