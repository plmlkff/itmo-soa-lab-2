package ru.itmo.product.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.itmo.product.entity.projection.ProductCountByDate;

import java.util.Date;

public record ProductCountByDateDto(
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date creationDate,
        Long count
) {
    public static ProductCountByDateDto fromEntity(ProductCountByDate productCountByDate){
        return new ProductCountByDateDto(productCountByDate.creationDate(), productCountByDate.count());
    }
}
