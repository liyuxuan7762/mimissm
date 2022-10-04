package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import com.bjpowernode.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper = null;

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

    @Override
    public int save(ProductInfo productInfo) {
        return productInfoMapper.insert(productInfo);
    }

    @Override
    public ProductInfo getProductById(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int updateProduct(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int deleteById(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] pids) {
        return productInfoMapper.deleteBatch(pids);
    }

    @Override
    public List<ProductInfo> searchProduct(ProductInfoVo vo) {
        return productInfoMapper.searchProduct(vo);
    }

    @Override
    public PageInfo<ProductInfo> searchProductSplit(ProductInfoVo vo, int pageSize) {
        // 设置分页插件
        PageHelper.startPage(vo.getPageNo(), pageSize);
        List<ProductInfo> list = productInfoMapper.searchProduct(vo);
        return new PageInfo<>(list);
    }
}
