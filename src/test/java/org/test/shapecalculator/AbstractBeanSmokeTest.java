package org.test.shapecalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public abstract class AbstractBeanSmokeTest<B> {

    @Autowired
    private B bean;

    @Test
    @DisplayName("Bean is autowired")
    void contextLoad() {
        assertThat(bean).isNotNull();
    }
}
