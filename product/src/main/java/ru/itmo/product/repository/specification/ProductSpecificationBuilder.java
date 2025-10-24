package ru.itmo.product.repository.specification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.itmo.common.entity.Country;
import ru.itmo.product.entity.Product;
import ru.itmo.common.entity.filter.ProductFilter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSpecificationBuilder {
    private Specification<Product> specification = Specification.where(null);


    public static ProductSpecificationBuilder getBuilder() {
        return new ProductSpecificationBuilder();
    }

    public static Specification<Product> buildFromFilter(ProductFilter filter) {
        return ProductSpecificationBuilder.getBuilder()
                .hasIds(filter.getIds())
                .hasNames(filter.getNames())
                .coordXBelow(filter.getCoordXBelow())
                .coordXAbove(filter.getCoordXAbove())
                .coordYBelow(filter.getCoordYBelow())
                .coordYAbove(filter.getCoordYAbove())
                .creationDateFrom(filter.getCreationDateFrom())
                .creationDateTo(filter.getCreationDateTo())
                .priceBelow(filter.getPriceBelow())
                .priceAbove(filter.getPriceAbove())
                .manufactCostBelow(filter.getManufactCostBelow())
                .manufactCostAbove(filter.getManufactCostAbove())
                .hasOwnerNames(filter.getOwnerNames())
                .hasOwnerNations(filter.getOwnerNations())
                .hasOwnerIds(filter.getOwnerIds())
                .build();
    }

    public ProductSpecificationBuilder hasIds(List<Integer> productIds){
        specification = specification.and(((root, query, criteriaBuilder) -> {
            if (productIds == null || productIds.isEmpty()) {
                return null;
            }
            return root.get("id").in(productIds);
        }));

        return this;
    }

    public ProductSpecificationBuilder hasNames(List<String> names) {
        specification = specification.and((root, query, criteriaBuilder) -> {
            if (names == null || names.isEmpty()) {
                return null;
            }
            return root.get("name").in(names);
        });

        return this;
    }

    public ProductSpecificationBuilder coordXBelow(Integer coordXBelow) {
        specification = specification.and((root, query, criteriaBuilder) ->
                Optional.ofNullable(coordXBelow).map(value -> criteriaBuilder.lessThanOrEqualTo(root.get("coordinates").get("x"), value))
                        .orElse(null));

        return this;
    }

    public ProductSpecificationBuilder coordXAbove(Integer coordXAbove) {
        specification = specification.and((root, query, criteriaBuilder) ->
                Optional.ofNullable(coordXAbove).map(value -> criteriaBuilder.greaterThanOrEqualTo(root.get("coordinates").get("x"), value))
                        .orElse(null));

        return this;
    }

    public ProductSpecificationBuilder coordYBelow(Integer coordYBelow) {
        specification = specification.and((root, query, criteriaBuilder) ->
                Optional.ofNullable(coordYBelow).map(value -> criteriaBuilder.lessThanOrEqualTo(root.get("coordinates").get("y"), value))
                        .orElse(null));

        return this;
    }

    public ProductSpecificationBuilder coordYAbove(Integer coordYAbove) {
        specification = specification.and((root, query, criteriaBuilder) ->
                Optional.ofNullable(coordYAbove).map(value -> criteriaBuilder.greaterThanOrEqualTo(root.get("coordinates").get("y"), value))
                        .orElse(null));

        return this;
    }

    public ProductSpecificationBuilder creationDateFrom(Date creationDateFrom) {
        specification = specification.and((root, query, criteriaBuilder) ->
                Optional.ofNullable(creationDateFrom).map(date -> criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), date))
                        .orElse(null));

        return this;
    }

    public ProductSpecificationBuilder creationDateTo(Date creationDateTo) {
        specification = specification.and((root, query, criteriaBuilder) ->
                Optional.ofNullable(creationDateTo).map(date -> criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), date))
                        .orElse(null));

        return this;
    }

    public ProductSpecificationBuilder priceBelow(Integer priceBelow) {
        specification = specification.and((root, query, criteriaBuilder) ->
                Optional.ofNullable(priceBelow).map(value -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), value))
                        .orElse(null));

        return this;
    }

    public ProductSpecificationBuilder priceAbove(Integer priceAbove) {
        specification = specification.and((root, query, criteriaBuilder) ->
                Optional.ofNullable(priceAbove).map(value -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value))
                        .orElse(null));

        return this;
    }

    public ProductSpecificationBuilder manufactCostBelow(Float manufactCostBelow) {
        specification = specification.and((root, query, criteriaBuilder) ->
                Optional.ofNullable(manufactCostBelow).map(value -> criteriaBuilder.lessThanOrEqualTo(root.get("manufactureCost"), value))
                        .orElse(null));

        return this;
    }

    public ProductSpecificationBuilder manufactCostAbove(Float manufactCostAbove) {
        specification = specification.and((root, query, criteriaBuilder) ->
                Optional.ofNullable(manufactCostAbove).map(value -> criteriaBuilder.greaterThanOrEqualTo(root.get("manufactureCost"), value))
                        .orElse(null));

        return this;
    }

    public ProductSpecificationBuilder hasOwnerNames(List<String> ownerNames) {
        specification = specification.and((root, query, criteriaBuilder) -> {
            if (ownerNames == null || ownerNames.isEmpty()) {
                return null;
            }
            return root.get("owner").get("name").in(ownerNames);
        });

        return this;
    }

    public ProductSpecificationBuilder hasOwnerNations(List<Country> ownerNations) {
        specification = specification.and((root, query, criteriaBuilder) -> {
            if (ownerNations == null || ownerNations.isEmpty()) {
                return null;
            }
            return root.get("owner").get("nationality").in(ownerNations);
        });

        return this;
    }

    public ProductSpecificationBuilder hasOwnerIds(List<Integer> ownerIds) {
        specification = specification.and((root, query, criteriaBuilder) -> {
            if (ownerIds == null || ownerIds.isEmpty()) {
                return null;
            }
            return root.get("owner").get("id").in(ownerIds);
        });

        return this;
    }

    public Specification<Product> build(){
        return specification;
    }
}
