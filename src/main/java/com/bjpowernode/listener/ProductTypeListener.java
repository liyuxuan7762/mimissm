package com.bjpowernode.listener;

import com.bjpowernode.pojo.ProductType;
import com.bjpowernode.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 从IOC容器中获取到service对象
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProductTypeService productTypeServiceImpl = (ProductTypeService) context.getBean("productTypeServiceImpl");
        List<ProductType> list = productTypeServiceImpl.getAll();
        sce.getServletContext().setAttribute("typeList", list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
