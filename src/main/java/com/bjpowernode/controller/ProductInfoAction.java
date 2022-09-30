package com.bjpowernode.controller;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.service.ProductInfoService;
import com.bjpowernode.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    private static final int PAGE_SIZE = 5;
    @Autowired
    ProductInfoService productInfoService = null;

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

    // 实现文件的上传
    @ResponseBody
    @RequestMapping("/ajaxImg.action")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request) {
        // 生成服务器端的文件名
        String saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(Objects.requireNonNull(pimage.getOriginalFilename()));
        // 设置上传路径
        // 将服务器项目地址转换为实际的硬盘存储路径
        String path = request.getServletContext().getRealPath("/image_big");
        try {
            pimage.transferTo(new File(path + File.separator + saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将图片文件在服务器上的路径以Json对象的形式返回
        JSONObject obj = new JSONObject();
        obj.put("imgurl", saveFileName);

        return obj.toString();
    }
}
