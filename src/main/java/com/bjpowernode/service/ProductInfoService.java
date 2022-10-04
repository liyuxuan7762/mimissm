package com.bjpowernode.service;

import com.bjpowernode.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {
    // 显示所有商品信息不分页
    List<ProductInfo> getProductInfoList();

    // 显示所有商品信息分页
    PageInfo spiltPage(int pageNum, int pageSize);

    // 保存商品信息
    int save(ProductInfo productInfo);

    // 根据主键查询商品信息
    ProductInfo getProductById(int pid);

    // 更新商品信息
    int updateProduct(ProductInfo info);

    // 删除单个商品信息
    int deleteById(int pid);

    // 批量删除商品
    int deleteBatch(String[] pids);
}
