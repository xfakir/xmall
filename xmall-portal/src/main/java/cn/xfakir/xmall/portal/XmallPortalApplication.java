package cn.xfakir.xmall.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@tk.mybatis.spring.annotation.MapperScan(basePackages = "cn.xfakir.xmall.common.mapper")
@ComponentScan("cn.xfakir")
@SpringBootApplication
public class XmallPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallPortalApplication.class, args);
    }

}
