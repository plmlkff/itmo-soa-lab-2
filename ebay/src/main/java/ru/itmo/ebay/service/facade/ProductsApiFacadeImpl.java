package ru.itmo.ebay.service.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.ebay.api.dto.ProductDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductsApiFacadeImpl implements ProductsApiFacade{
    private static final int MAX_RETRY_ATTEMPTS = 5;
    private static final int BACKOFF_MAX_DELAY = 10_000;
    private static final int BACKOFF_MULTIPLIER = 2;

    private final RestClient productRestClient;


    @Override
    @Retryable(
            maxAttempts = MAX_RETRY_ATTEMPTS,
            backoff = @Backoff(maxDelay = BACKOFF_MAX_DELAY, multiplier = BACKOFF_MULTIPLIER)
    )
    public List<ProductDto> getProductsByManufacturerId(int manufacturerId) {
        try {
            return tryGetProductsByManufacturerId(manufacturerId);
        } catch (ResourceAccessException e){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Products service isn't available now!");
        } catch (RestClientResponseException e){
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Can not get data from products service!");
        }
    }

    private List<ProductDto> tryGetProductsByManufacturerId(int manufacturerId){
        return productRestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/products/")
                        .queryParam("ownerIds", manufacturerId)
                        .build()
                ).retrieve()
                .body(new ParameterizedTypeReference<List<ProductDto>>() {});
    }
}
