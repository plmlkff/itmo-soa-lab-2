package ru.itmo.product;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itmo.common.api.advice.CommonControllerAdvice;

@Configuration
@Import(CommonControllerAdvice.class)
public class ProductServiceConfig {
}
