package ru.itmo.common.entity.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BaseEntityFilter{
    public static final int DEFAULT_LIMIT = 1000;
    public static final int DEFAULT_PAGE = 0;

    private List<Integer> ids;
    private List<String> sortBy;
    private List<String> sortDir;
    private int limit = DEFAULT_LIMIT;
    private int page = DEFAULT_PAGE;

    @JsonIgnore
    public Sort getSort(){
        if (sortBy == null || sortBy.isEmpty()) return Sort.unsorted();
        List<Sort.Order> orders = new ArrayList<>();

        for (int i = 0; i < sortBy.size(); i++) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortDir.get(i)), sortBy.get(i)));
        }

        return Sort.by(orders);
    }

    @JsonIgnore
    public PageRequest getPageRequest(){
        return PageRequest.of(page, limit);
    }
}
