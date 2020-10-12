package cn.xfakir.xmall.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@tk.mybatis.spring.annotation.MapperScan(basePackages = "cn.xfakir.xmall.common.mapper")
@SpringBootApplication
public class XmallPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallPortalApplication.class, args);
    }

}
