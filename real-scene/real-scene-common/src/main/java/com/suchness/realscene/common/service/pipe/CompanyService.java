package com.suchness.realscene.common.service.pipe;

import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.dao.pipe.CompanyRepository;
import com.suchness.realscene.common.entity.pipe.Company;
import com.suchness.realscene.common.service.BaseService;
import com.suchness.realscene.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: rs
 * @date: 2020/7/8 9:30
 * @description:
 * 企业管理服务类
 */
@Service
public class CompanyService extends BaseService<Company,Integer, CompanyRepository> {

    @Autowired
    private CompanyRepository companyRepository;


    /**
     * 获取企业信息
     * @param company
     * @return
     */
    public Company getCompanyInfo(Company company){
        List<SearchFilter> filters = new ArrayList<>();
        SearchFilter filter = null;
        if(StringUtil.isNotNullOrEmpty(company.getPhone())){
            filter =  SearchFilter.build("phone", company.getPhone() ,SearchFilter.Join.or);
            filters.add(filter);
        }

        if(StringUtil.isNotNullOrEmpty(company.getName())){
            filter =  SearchFilter.build("name", company.getName(),SearchFilter.Join.or);
            filters.add(filter);
        }

        if(StringUtil.isNotNullOrEmpty(company.getEmail())){
            filter =  SearchFilter.build("email", company.getEmail(),SearchFilter.Join.or);
            filters.add(filter);
        }

        return this.get(filters);
    }



}
