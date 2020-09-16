package org.golu.common.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
public class RoutesConfig extends RouteBuilder {

    @Autowired
    private Environment env;

    @Value("${camel.component.servlet.mapping.context-path}")
    private String contextPath;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .skipBindingOnErrorCode(false)
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)
                .port(env.getProperty("server.port", "8080"))
                .contextPath(contextPath.substring(0, contextPath.length() - 2))
                // swagger api-doc
                .apiContextPath("/api-doc")
                    .apiProperty("api.title", "Products API")
                    .apiProperty("api.version", "1.0.0");
    }

    @Controller
    public class SwaggerConfig {
        @RequestMapping("/swagger-ui")
        public String redirectToSwaggerUi() {
            return "redirect:/webjars/swagger-ui/index.html?url=/api/api-doc&validatorUrl=";
        }
    }
}
