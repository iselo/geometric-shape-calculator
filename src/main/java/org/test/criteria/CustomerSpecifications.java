package org.test.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

enum CustomerSpecifications implements Specification<Customer> {

    CUSTOMER_HAS_BIRTHDAY {
        @Override
        public Predicate toPredicate(Root<Customer> root,
                                     CriteriaQuery<?> query,
                                     CriteriaBuilder criteriaBuilder) {
            var birthday = root.<LocalDate>get("birthday");
            var today = LocalDate.now();
            return criteriaBuilder.equal(birthday, today);
        }
    },

    IS_LONG_TERM_CUSTOMER {
        @Override
        public Predicate toPredicate(Root<Customer> root,
                                     CriteriaQuery<?> query,
                                     CriteriaBuilder criteriaBuilder) {
            var createdAt1 = root.<LocalDate>get("createdAt");
            var twoYearsFromNow = LocalDate.now().minusYears(2);
            return criteriaBuilder.lessThan(createdAt1, twoYearsFromNow);
        }
    }
}
