package com.zsx.controller;

import com.zsx.annotation.CompanyDeal;
import com.zsx.entity.Company;
import com.zsx.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    @CompanyDeal("hello world")
    public Company get(@PathVariable("id") Long id) {
        return companyService.get(id);
    }

    @DeleteMapping("/{id}")
    public boolean get(@PathVariable("id") Integer id) {
        return companyService.delete(id);
    }
}
