package org.test.criteria;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CustomerSpecification {

    public static Specification<Customer> isLongTermCustomer() {
        return (root, query, criteriaBuilder) -> {
            var createdAt = root.get(Customer_.createdAt);
            var twoYearsFromNow = LocalDate.now().minusYears(2);
            return criteriaBuilder.lessThan(createdAt, twoYearsFromNow);
        };
    }

    public static Specification<Customer> hasBirthday() {
        return (root, query, criteriaBuilder) -> {
            var birthday = root.<LocalDate>get("birthday");
            var today = LocalDate.now();
            return criteriaBuilder.equal(birthday, today);
        };
    }

    public static Specification<Customer> hasSalesOfMoreThan(Integer value) {
        return (root, query, criteriaBuilder) -> {
            return null;
        };
    }
}
