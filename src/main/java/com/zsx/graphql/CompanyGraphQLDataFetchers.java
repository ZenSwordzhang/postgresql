package com.zsx.graphql;

import com.zsx.entity.Company;
import com.zsx.service.CompanyService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyGraphQLDataFetchers {

    @Autowired
    private CompanyService companyService;

    public DataFetcher getCompanyByIdDataFetcher() {
        List<Company> companies = companyService.getCompanies();
        return dataFetchingEnvironment -> {
            Long companyId  = Long.valueOf(dataFetchingEnvironment.getArgument("id"));
            return companies
                    .stream()
                    .filter(company -> company.getId().equals(companyId))
                    .findFirst()
                    .orElse(null);
        };
    }

}