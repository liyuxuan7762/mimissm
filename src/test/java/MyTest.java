import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import com.bjpowernode.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_dao.xml", "classpath:applicationContext_service.xml"})

public class MyTest {

    @Autowired
    ProductInfoMapper mapper;

    @Test
    public void test(){
        ProductInfoVo vo = new ProductInfoVo();
        vo.setpName("4");
        vo.setTypeId(1);
        vo.setLowPrice(1000);
        // vo.setHighPrice(3000);
        System.out.println(vo);
        List<ProductInfo> list = mapper.searchProduct(vo);
        list.forEach(System.out::println);

    }
}
