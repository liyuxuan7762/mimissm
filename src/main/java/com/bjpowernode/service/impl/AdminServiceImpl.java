package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.AdminService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper mapper = null;

    @Override
    public Admin Login(String name, String pwd) {
        // 【1】创建条件对象
        AdminExample example = new AdminExample();
        example.createCriteria().andANameEqualTo(name);
        // 【2】执行查询
        List<Admin> admins = mapper.selectByExample(example);
        // 【3】判断查询结果
        if (admins.size() > 0) {
            // 【4】将输入密码和查询出来的密码进行对比
            if (pwd.equals(admins.get(0).getaPass()))
                return admins.get(0);
            else
                return null;
        }
        return null;
    }
}
