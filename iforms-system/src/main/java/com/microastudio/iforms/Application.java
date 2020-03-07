package com.microastudio.iforms;

import com.microastudio.iforms.common.annotation.AnonymousAccess;
import com.microastudio.iforms.common.utils.SpringContextHolder;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${http.port}")
    Integer httpPort;
    @Value("${server.port}")
    Integer httpsPort;
    @Value("${https.enabled}")
    private Boolean httpsEnabled;

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
        if (httpsEnabled) {
            //Http request setRedirectPort to https
            TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
                @Override
                protected void postProcessContext(Context context) {
                    SecurityConstraint constraint = new SecurityConstraint();
                    constraint.setUserConstraint("CONFIDENTIAL");
                    SecurityCollection collection = new SecurityCollection();
                    collection.addPattern("/*");
                    constraint.addCollection(collection);
                    context.addConstraint(constraint);
                }
            };
            tomcat.addAdditionalTomcatConnectors(httpConnector());
            return tomcat;
        } else {
            // only support http or https
            TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
            fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
            return fa;
        }
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(httpPort);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(httpsPort);
        return connector;
    }

    /**
     * 访问首页提示
     *
     * @return /
     */
    @GetMapping("/")
    @AnonymousAccess
    public String index() {
        return "Backend service started successfully";
    }
}
