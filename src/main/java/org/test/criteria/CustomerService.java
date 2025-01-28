package org.test.criteria;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static org.test.criteria.CustomerSpecification.hasBirthday;
import static org.test.criteria.CustomerSpecification.isLongTermCustomer;

public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAllLongTermCustomerByBirthday() {
        return repository.findAll(where(hasBirthday()).and(isLongTermCustomer()));
    }
}
