package ru.itmo.ebay.ejb.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.ebay.ejb.dto.ProductDto;
import ru.itmo.ebay.ejb.exception.EbayServiceException;

import java.net.URI;
import java.util.List;

public class ProductServiceClient {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);
    private final String baseUrl;
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ProductServiceClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public List<ProductDto> getProductsByManufacturerId(int manufacturerId) {
        String url = baseUrl + "/api/products/?ownerIds=" + manufacturerId;
        return executeGetRequest(url);
    }

    public List<ProductDto> getProductsByPriceRange(int priceFrom, int priceTo) {
        String url = baseUrl + "/api/products/?priceAbove=" + priceFrom + "&priceBelow=" + priceTo;
        return executeGetRequest(url);
    }

    private List<ProductDto> executeGetRequest(String url) {
        HttpGet request = new HttpGet(URI.create(url));
        request.setHeader("Content-Type", "application/json");

        try {
            log.info("Executing request to: {}", url);
            return httpClient.execute(request, response -> {
                int statusCode = response.getCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                
                log.info("Response status: {}", statusCode);
                
                if (statusCode >= 200 && statusCode < 300) {
                    return objectMapper.readValue(responseBody, new TypeReference<List<ProductDto>>() {});
                } else {
                    throw new EbayServiceException(statusCode, 
                            "Product service returned error: " + responseBody);
                }
            });
        } catch (EbayServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error calling product service", e);
            throw new EbayServiceException(500, 
                    "Cannot get data from product service: " + e.getMessage(), e);
        }
    }

    public void close() {
        try {
            httpClient.close();
        } catch (Exception e) {
            log.error("Error closing HTTP client", e);
        }
    }
}

