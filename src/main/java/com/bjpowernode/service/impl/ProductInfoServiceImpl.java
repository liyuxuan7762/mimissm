package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import com.bjpowernode.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper = null;

    @Override
    // 显示所有商品信息不分页
    public List<ProductInfo> getProductInfoList() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo spiltPage(int pageNum, int pageSize) {
        // 使用PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum, pageSize);

        // 创建查询对象 按照pid降序排列查询出所有的商品信息
        ProductInfoExample example = new ProductInfoExample();
        example.setOrderByClause("p_id desc");

        // 调用mapper实现查询
        List<ProductInfo> list = productInfoMapper.selectByExample(example);

        // 创建pageInfo对象
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }
}
