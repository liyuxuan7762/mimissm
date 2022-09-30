package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import com.bjpowernode.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper = null;

    @Override
    public List<ProductInfo> getProductInfoList() {

        return productInfoMapper.selectByExample(new ProductInfoExample());
    }
}
