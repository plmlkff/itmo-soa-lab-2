package ru.itmo.product.entity.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.itmo.product.entity.Country;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class ProductFilter extends BaseEntityFilter{
    private List<String> names;

    private Integer coordXBelow;

    private Integer coordXAbove;

    private Integer coordYBelow;

    private Integer coordYAbove;

    private Date creationDateFrom;

    private Date creationDateTo;

    private Integer priceBelow;

    private Integer priceAbove;

    private Float manufactCostBelow;

    private Float manufactCostAbove;

    private List<String> ownerNames;

    private List<Country> ownerNations;

    private List<Integer> ownerIds;
}
