package org.test.criteria;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static org.test.criteria.CustomerSpecifications.CUSTOMER_HAS_BIRTHDAY;
import static org.test.criteria.CustomerSpecifications.IS_LONG_TERM_CUSTOMER;

public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> nothing() {
        return repository.findAll(where(CUSTOMER_HAS_BIRTHDAY).and(IS_LONG_TERM_CUSTOMER));
    }
}
