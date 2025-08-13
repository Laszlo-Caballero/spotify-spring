package com.spotify.rest.utils;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;
import lombok.Setter;

@Setter
public class JpaSpecification<T> implements Specification<T> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria == null) {
          return builder.conjunction();
        }
        Path<?> path;

        if (criteria.getKey().contains(".")) {
            String[] parts = criteria.getKey().split("\\.");
            From<?, ?> join = root.join(parts[0], JoinType.LEFT);
            path = join.get(parts[1]);
        } else {
            path = root.get(criteria.getKey());
        }

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(path.as(String.class), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(path.as(String.class), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (path.getJavaType() == String.class) {
                return builder.like(path.as(String.class), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(path, criteria.getValue());
            }
        }
        return null;
    }
}
