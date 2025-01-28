package org.test.criteria;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Customer {

    @Id
    private Long id;
    private final LocalDate createdAt;
    private final Class<?> typeToken;
}
