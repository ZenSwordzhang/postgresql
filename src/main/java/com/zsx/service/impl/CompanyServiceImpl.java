package com.zsx.service.impl;

import com.zsx.entity.Company;
import com.zsx.generator.jooq.tables.records.CompanyRecord;
import com.zsx.mapper.CompanyMapper;
import com.zsx.service.CompanyService;
import org.jooq.DSLContext;
import org.jooq.DeleteConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private DSLContext dslContext;

    private com.zsx.generator.jooq.tables.Company company = com.zsx.generator.jooq.tables.Company.COMPANY.as("company");

    @Override
    public Company get(Long id) {
        return companyMapper.findById(id);
    }

    @Override
    public List<Company> getCompanies() {
        return dslContext.selectFrom(company).fetch().into(Company.class);
    }

    @Override
    public boolean delete(Integer id) {
        DeleteConditionStep<CompanyRecord> deleteConditionStep = dslContext.delete(company).where(company.ID.eq(id));
        if (deleteConditionStep.execute() > 0) {
            return true;
        }
        return false;
    }
}
