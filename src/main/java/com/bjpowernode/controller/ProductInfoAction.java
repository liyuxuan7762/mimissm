package com.bjpowernode.controller;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.service.ProductInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    private static final int PAGE_SIZE = 5;
    @Autowired
    private ProductInfoService productInfoService = null;

    // 显示所有的商品信息 已弃用
    @RequestMapping("/getAllProductInfo.action")
    public String getAllProductInfo(HttpServletRequest request) {
        List<ProductInfo> productInfoList = productInfoService.getProductInfoList();
        request.setAttribute("list", productInfoList);
        return "product";
    }

    // 显示第一页的商品信息
    @RequestMapping("/split.action")
    public String split(HttpServletRequest request) {
        PageInfo info = productInfoService.spiltPage(1, PAGE_SIZE);
        request.setAttribute("info", info);
        return "product";
    }

    // 使用ajax实现分页
    @ResponseBody
    @RequestMapping("/ajaxSplit.action")
    public void ajaxSplit(int page, HttpSession session) {
        PageInfo info = productInfoService.spiltPage(page, PAGE_SIZE);
        session.setAttribute("info", info);

    }
}
