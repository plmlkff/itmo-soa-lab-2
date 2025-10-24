package ru.itmo.product.entity.projection;

import java.util.Date;

public record ProductCountByDate(
        Date creationDate,
        Long count
) {}
