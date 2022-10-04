package com.bjpowernode.controller;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.service.ProductInfoService;
import com.bjpowernode.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    private static final int PAGE_SIZE = 5;
    @Autowired
    ProductInfoService productInfoService = null;
    String saveFileName = "";

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
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(Objects.requireNonNull(pimage.getOriginalFilename()));
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

    // 实现商品的添加
    @RequestMapping("/save.action")
    public String save(ProductInfo info, HttpServletRequest request) {
        info.setpDate(new Date());
        info.setpImage(saveFileName);

        int result = -1;
        result = productInfoService.save(info);
        if (result > 0) {
            // 添加成功
            request.setAttribute("msg", "添加成功");
        } else {
            request.setAttribute("msg", "添加失败");
        }

        saveFileName = "";

        return "forward:/prod/split.action";
    }

    // 实现商品修改的回显操作
    @RequestMapping("/one.action")
    public String one(int pid, Model model) {
        // 根据主键查询到商品的信息
        ProductInfo product = productInfoService.getProductById(pid);
        // 将商品信息通过model回传到前端页面
        model.addAttribute("prod", product);
        return "update";
    }

    // 实现商品的更新操作
    @RequestMapping("/update.action")
    public String update(ProductInfo info, HttpServletRequest request) {
        // 首先判断图片是否更改了
        if (!saveFileName.equals("")) {
            // 如果不为空，则更改了商品的图片
            info.setpImage(saveFileName);
        }

        int result = -1;
        result = productInfoService.updateProduct(info);
        if (result > 0) {
            request.setAttribute("msg", "更新成功");
        } else {
            request.setAttribute("msg", "更新失败");
        }

        saveFileName = "";

        return "forward:/prod/split.action";
    }

    // 删除单个商品
    @RequestMapping("/delete.action")
    public String delete(int pid, HttpServletRequest request) {
        int result = -1;
        result = productInfoService.deleteById(pid);
        if (result > 0) {
            request.setAttribute("msg", "删除成功");
        } else {
            request.setAttribute("msg", "删除成功");
        }
        return "forward:/prod/deleteAjaxSplit.action";
    }

    @RequestMapping(value = "/deleteAjaxSplit.action", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object deleteAjaxSplit(HttpServletRequest request) {
        // 显示第一页
        PageInfo pageInfo = productInfoService.spiltPage(1, PAGE_SIZE);
        request.getSession().setAttribute("info", pageInfo);
        return request.getAttribute("msg");
    }

    // 实现批量删除
    @RequestMapping("/deleteBatch.action")
    public String deleteBatch(String pids, HttpServletRequest request) {
        // 拆分字符串
        String[] split = pids.split(",");
        try {
            int result = productInfoService.deleteBatch(split);
            if(result>0){
                request.setAttribute("msg", "删除成功");
            } else {
                request.setAttribute("msg", "删除失败");
            }
        } catch (Exception e) {
            request.setAttribute("msg", "无法删除");
        }

        return "forward:/prod/deleteAjaxSplit.action";

    }
}
