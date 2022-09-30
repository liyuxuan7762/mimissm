package com.bjpowernode.service;

import com.bjpowernode.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {
    // 查询所有的商品类型
    public List<ProductType> getAll();
}
