package com.bjpowernode.controller;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    @Autowired
    private ProductInfoService productInfoService = null;

    @RequestMapping("/getAllProductInfo.action")
    public String getAllProductInfo(HttpServletRequest request) {
        List<ProductInfo> productInfoList = productInfoService.getProductInfoList();
        request.setAttribute("list", productInfoList);
        return "product";
    }
}
