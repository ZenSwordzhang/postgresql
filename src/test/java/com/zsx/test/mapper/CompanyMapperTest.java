package com.zsx.test.mapper;

import com.zsx.mapper.CompanyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CompanyMapperTest {

    @Autowired
    private CompanyMapper companyMapper;

    @Test
    void testFindById() {
        System.out.println(companyMapper.findById(1L));
    }

    @Test
    void testFindAll() {
        System.out.println(companyMapper.findAll());
    }
}
