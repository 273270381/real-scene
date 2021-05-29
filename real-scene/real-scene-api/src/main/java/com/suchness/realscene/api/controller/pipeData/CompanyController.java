package com.suchness.realscene.api.controller.pipeData;

import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.constant.factory.PageFactory;
import com.suchness.realscene.common.entity.pipe.Company;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.service.pipe.CompanyService;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.utils.ToolUtil;
import com.suchness.realscene.common.utils.factory.Page;
import com.suchness.realscene.common.vo.Rets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: rs
 * @date: 2020/7/8 9:31
 * @description:
 * 企业管理控制器
 */
@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController extends BaseController {

    @Autowired
    CompanyService companyService;

    /**
     * 企业信息列表
     * @param name
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.COMPANY})
    public Object list(@RequestParam(required = false) String name){
        Page<Company> page = new PageFactory<Company>().defaultPage();
        if(StringUtil.isNotEmpty(name)){
            page.addFilter(SearchFilter.build("name", SearchFilter.Operator.LIKE, name));
        }
        page = companyService.queryPage(page);
        return Rets.success(page);
    }

    /**
     * 企业添加
     * @param company
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @RequiresPermissions(value = {Permission.COMPANY_EDIT})
    public Object save(Company company){
        if(ToolUtil.isOneEmpty(company,company.getName(),company.getLandLine())){
            return Rets.failure("参数不能为空，请重试。");
        }

        if(StringUtil.isNullOrEmpty(company.getId())){
            companyService.insert(company);
        }else{
            companyService.update(company);
        }
        return Rets.success();
    }

    /**
     * 删除企业信息
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @RequiresPermissions(value = {Permission.COMPANY_DEL})
    public Object delete(@RequestParam Integer id){
        companyService.delete(id);
        return Rets.success();
    }


    /**
     * 是否存在用户信息（名称/手机号/邮箱）
     * @return
     */
    @RequestMapping(value = "isHave",method = RequestMethod.GET)
    public Object index(Company company){
        Boolean isHave = false;
        Company cObj = companyService.getCompanyInfo(company);
        if(StringUtil.isNotNullOrEmpty(cObj)){
            isHave = true;
        }
        return Rets.success(isHave);
    }

}
