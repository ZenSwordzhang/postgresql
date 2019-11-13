package com.zsx.test;

import com.zsx.generator.jooq.Tables;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
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

    @Test
    void testSelectCompany() {
        Condition condition = DSL.trueCondition();
        condition = condition.and(DSL.field("name").eq("Paul1"));
        System.out.println(condition);
        System.out.println("====================");
        int m = dslContext.fetchCount(Tables.COMPANY, condition);
        System.out.println("m====================" + m);
    }
}
