package org.test.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import static com.google.common.base.Preconditions.checkNotNull;

public interface AbstractSpecification extends Specification<Customer> {

    default Predicate toPredicate(Root<Customer> root,
                                  CriteriaQuery<?> query,
                                  CriteriaBuilder criteriaBuilder) {
        checkNotNull(root);
        checkNotNull(query);
        checkNotNull(criteriaBuilder);
        return newPredicate(root, query, criteriaBuilder);
    }

    Predicate newPredicate(Root<Customer> root,
                           CriteriaQuery<?> query,
                           CriteriaBuilder criteriaBuilder);
}
