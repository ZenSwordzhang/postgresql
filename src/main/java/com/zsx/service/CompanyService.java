package com.zsx.service;

import com.zsx.entity.Company;

public interface CompanyService {

    Company get(Long id);

    boolean delete(Integer id);
}
