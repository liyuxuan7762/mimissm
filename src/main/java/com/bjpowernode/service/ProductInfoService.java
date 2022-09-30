package com.bjpowernode.service;

import com.bjpowernode.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {
    // 显示所有商品信息不分页
    public List<ProductInfo> getProductInfoList();

    // 显示所有商品信息分页
    public PageInfo spiltPage(int pageNum, int pageSize);
}
