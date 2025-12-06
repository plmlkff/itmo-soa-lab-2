package ru.itmo.ebay.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
public class EbayServiceConfig {

    @Value("${server.ssl.trust-store}")
    private Resource trustStore;

    @Value("${server.ssl.trust-store-password}")
    private String trustStorePassword;

    @Bean(name = "productRestClient")
    public RestClient getProductRestClient(
            @Value("${services.product.baseUrl}")
            String baseUrl
    ) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("ContentType", MediaType.APPLICATION_JSON_VALUE)
                .requestInterceptor(new ProductRestClientRequestsLoggingInterceptor())
                .build();
    }

    @Slf4j
    private static class ProductRestClientRequestsLoggingInterceptor implements ClientHttpRequestInterceptor{

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            var startTime = System.currentTimeMillis();

            log.info("Starting request to: {}", request.getURI());

            var response = execution.execute(request, body);

            log.info("Request completed in {}ms with status code: {}", System.currentTimeMillis() - startTime, response.getStatusCode());

            return response;
        }
    }
}
