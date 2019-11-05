package com.zsx.test.service;

import com.zsx.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Test
    void testGetCompanies() {
        System.out.println(companyService.getCompanies());
    }
}
