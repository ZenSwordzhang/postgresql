package com.zsx.test;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JooqTest {

    @Autowired
    private DSLContext dslContext;
    private com.zsx.generator.jooq.tables.Company company = com.zsx.generator.jooq.tables.Company.COMPANY.as("company");

    @Test
    void testSelect() {
        Result<Record> result = dslContext.select().from(company).fetch();
        System.out.println(result);
    }
}
