package ru.itmo.ebay.config;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itmo.ebay.service.EbayFilterService;

@Configuration
public class SoapClientConfig {
    @Value("${services.ebay-soap.url}")
    private String ebaySoapUrl;

    @Bean(name = "ebaySoapClient")
    public EbayFilterService ebaySoapClient() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(EbayFilterService.class);
        factory.setAddress(ebaySoapUrl);
        return (EbayFilterService) factory.create();
    }
}

