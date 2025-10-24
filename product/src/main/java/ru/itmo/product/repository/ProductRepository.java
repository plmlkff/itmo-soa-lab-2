package ru.itmo.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.product.entity.Product;
import ru.itmo.product.entity.projection.ProductCountByDate;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    @Query("""
        SELECT new ru.itmo.product.entity.projection.ProductCountByDate(p.creationDate, count(p))
        FROM Product p
        GROUP BY p.creationDate
        ORDER BY p.creationDate
    """)
    List<ProductCountByDate> countProductsByCreationDate();

    @Query("""
        SELECT avg(p.price)
        FROM Product p
    """)
    int averagePriceByProducts();

    @Query("""
        SELECT DISTINCT p.manufactureCost
        FROM Product p
    """)
    List<Float> uniqueManufactureCosts();
}
