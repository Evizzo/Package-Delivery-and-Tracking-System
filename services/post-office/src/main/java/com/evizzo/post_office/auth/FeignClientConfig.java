package com.evizzo.post_office.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                String token = (String) RequestContextHolder.currentRequestAttributes()
                        .getAttribute("jwtToken", RequestAttributes.SCOPE_REQUEST);
                if (token != null && !token.isEmpty()) {
                    requestTemplate.header("Authorization", "Bearer " + token);
                }

            }
        };
    }
}
