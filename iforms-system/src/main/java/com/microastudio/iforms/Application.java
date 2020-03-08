package com.microastudio.iforms;

import com.microastudio.iforms.common.annotation.AnonymousAccess;
import com.microastudio.iforms.common.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peng
 */
@EnableAsync
@RestController
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.microastudio.iforms.modules.form.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    /**
     * 配置同时支持http和https
     *
     * @return /
     */
    @Bean
    public ServletWebServerFactory webServerFactory() {
        // only support http or https
        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
        fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
        return fa;
    }

    /**
     * 访问首页提示
     *
     * @return /
     */
    @GetMapping("/api/connect")
    @AnonymousAccess
    public String index() {
        return "Backend service started successfully";
    }
}
