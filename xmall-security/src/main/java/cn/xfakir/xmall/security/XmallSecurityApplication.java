package cn.xfakir.xmall.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("cn.xfakir.xmall")
@SpringBootApplication
public class XmallSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallSecurityApplication.class, args);
    }

}
