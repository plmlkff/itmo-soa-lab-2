package ru.itmo.ebay.config;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itmo.ebay.service.EbayFilterService;

@Configuration
public class CxfConfig {
    @Autowired
    private Bus bus;

    @Autowired
    private EbayFilterService ebayFilterService;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, ebayFilterService);
        endpoint.publish("/EbayService");
        return endpoint;
    }
}

