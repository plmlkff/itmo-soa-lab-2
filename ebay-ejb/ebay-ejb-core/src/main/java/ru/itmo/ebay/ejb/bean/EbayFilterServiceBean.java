package ru.itmo.ebay.ejb.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.ebay.ejb.client.ProductServiceClient;
import ru.itmo.ebay.ejb.dto.ProductDto;
import ru.itmo.ebay.ejb.exception.EbayServiceException;
import ru.itmo.ebay.ejb.remote.EbayFilterServiceRemote;

import java.util.List;

@Stateless
public class EbayFilterServiceBean implements EbayFilterServiceRemote {
    private static final Logger log = LoggerFactory.getLogger(EbayFilterServiceBean.class);
    private ProductServiceClient productServiceClient;

    @PostConstruct
    public void init() {
        log.info("Initializing EbayFilterServiceBean");
        String productServiceUrl = System.getProperty("product.service.url", "http://localhost:8080");
        productServiceClient = new ProductServiceClient(productServiceUrl);
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying EbayFilterServiceBean");
        if (productServiceClient != null) {
            productServiceClient.close();
        }
    }

    @Override
    public List<ProductDto> getProductsByManufacturerId(int manufacturerId) {
        if (manufacturerId < 0) {
            throw new EbayServiceException(400, "Manufacturer id should be above 0");
        }

        log.info("Getting products by manufacturer id: {}", manufacturerId);
        List<ProductDto> products = productServiceClient.getProductsByManufacturerId(manufacturerId);
        
        if (products == null || products.isEmpty()) {
            throw new EbayServiceException(404, "Manufacturer hasn't got products");
        }
        
        log.info("Found {} products for manufacturer id: {}", products.size(), manufacturerId);
        return products;
    }

    @Override
    public List<ProductDto> getProductsByPriceRange(int priceFrom, int priceTo) {
        if (priceFrom < 1 || priceTo < priceFrom) {
            throw new EbayServiceException(400, "Provided values are not valid!");
        }

        log.info("Getting products with price in range [{}, {}]", priceFrom, priceTo);
        List<ProductDto> products = productServiceClient.getProductsByPriceRange(priceFrom, priceTo);
        
        log.info("Found {} products in price range [{}, {}]", products.size(), priceFrom, priceTo);
        return products;
    }
}

