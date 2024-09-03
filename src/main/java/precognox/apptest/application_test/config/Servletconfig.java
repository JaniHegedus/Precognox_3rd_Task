package precognox.apptest.application_test.config;

import precognox.apptest.application_test.servlet.DataProcessorServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ServletConfig {

    @Bean
    public ServletRegistrationBean<DataProcessorServlet> dataProcessorServlet() {
        return new ServletRegistrationBean<>(new DataProcessorServlet(), "/processData");
    }
}
