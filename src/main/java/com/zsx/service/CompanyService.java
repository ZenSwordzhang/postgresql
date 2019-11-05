package com.zsx.service;

import com.zsx.entity.Company;

import java.util.List;

public interface CompanyService {

    Company get(Long id);

    List<Company> getCompanies();

    boolean delete(Integer id);
}
