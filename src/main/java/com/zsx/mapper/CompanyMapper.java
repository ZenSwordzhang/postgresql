package com.zsx.mapper;

import com.zsx.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMapper {

    Company findById(Long id);

    List<Company> findAll();
}
