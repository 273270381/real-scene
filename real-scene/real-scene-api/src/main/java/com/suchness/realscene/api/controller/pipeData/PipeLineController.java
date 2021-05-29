package com.suchness.realscene.api.controller.pipeData;

import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.Constants;
import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.constant.factory.PageFactory;
import com.suchness.realscene.common.bean.core.BussinessLog;
import com.suchness.realscene.common.cache.impl.DictCacheImpl;
import com.suchness.realscene.common.entity.pipe.Company;
import com.suchness.realscene.common.entity.pipe.PipeLine;
import com.suchness.realscene.common.entity.system.Dict;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.service.PipeLineService;
import com.suchness.realscene.common.service.pipe.CompanyService;
import com.suchness.realscene.common.utils.HttpUtil;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.utils.factory.Page;
import com.suchness.realscene.common.utils.poi.ExcelUtil;
import com.suchness.realscene.common.vo.Rets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author wuchanghao
 * @Description  pipeline operation
 * @Date  2020/6/29 10:32
 **/
@RestController
@RequestMapping("/pipeline")
@Slf4j
public class PipeLineController  extends BaseController {

    @Autowired
    private PipeLineService pipeLineService;
    @Autowired
    private DictCacheImpl dictCache;
    @Autowired
    private CompanyService companyService;

    /**
     * @Author wuchanghao
     * @Description show pipeline list
     * @Date
     * @Param type,roadName
     * @return Object
     **/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.PIPELINE})
    public Object list(@RequestParam(required = false) Integer type,@RequestParam(required = false) String roadName){
        Page<PipeLine> page = new PageFactory<PipeLine>().defaultPage();
        if(type != null){
            page.addFilter(SearchFilter.build("type", SearchFilter.Operator.EQ, type));
        }
        if(StringUtil.isNotEmpty(roadName)){
            page.addFilter(SearchFilter.build("road_name", SearchFilter.Operator.LIKE, roadName));
        }
        page = pipeLineService.queryPage(page);
        List<PipeLine> pipeNodes = page.getRecords();
        //Convert Numbers into Chinese characters
        for(PipeLine pipeLine:pipeNodes){
            //pipeline type
            if(pipeLine.getType() != null){
                String dictStr = dictCache.getDict(pipeLine.getType().longValue());
                pipeLine.setTypeName(dictStr);
            }

            //pipeline material
            if(pipeLine.getMaterial_id() != null){
                String materialStr = dictCache.getDict(pipeLine.getMaterial_id().longValue());
                pipeLine.setMaterial(materialStr);
            }

            //pipeline fillType
            if(pipeLine.getFill_type_id() !=null){
                String fillTypeStr = dictCache.getDict(pipeLine.getFill_type_id().longValue());
                pipeLine.setFillType(fillTypeStr);
            }
        }
        return Rets.success(page);
    }

    /**
     * @Author wuchanghao
     * @Description get pipeline detail by record_id
     * @Date
     * @Param record_id
     * @return Object
     **/
    @RequestMapping(value = "/detail/{record_id}",method = RequestMethod.GET)
    public Object pipeLineDetail(@PathVariable(value = "record_id") String record_id){
        PipeLine pipeLine = pipeLineService.findByRecordId(record_id);
        //Because the list is displayed as Chinese characters, and the database stores Numbers, conversion is required
        //pipeline type
        if(pipeLine.getType() != null){
            String dictStr = dictCache.getDict(pipeLine.getType().longValue());
            pipeLine.setTypeName(dictStr);
        }
        //pipeline material
        if(pipeLine.getMaterial_id() != null){
            String materialStr = dictCache.getDict(pipeLine.getMaterial_id().longValue());
            pipeLine.setMaterial(materialStr);
        }
        //pipeline fillType
        if(pipeLine.getFill_type_id() !=null){
            String fillTypeStr = dictCache.getDict(pipeLine.getFill_type_id().longValue());
            pipeLine.setFillType(fillTypeStr);
        }
        //pipeline detect company
        if(pipeLine.getDetect_company_id() != null){
            Company dectCompany = companyService.get(pipeLine.getDetect_company_id());
            if(dectCompany != null){
                pipeLine.setDetectCompany(dectCompany.getName());
            }
        }
        //pipeline supervise company
        if(pipeLine.getSupervise_company_id() != null){
            Company superviseCompany = companyService.get(pipeLine.getSupervise_company_id());
            if(superviseCompany != null){
                pipeLine.setSuperviseCompany(superviseCompany.getName());
            }
        }
        //pipeline ownership company
        if(pipeLine.getOwnership_company_id() !=null){
            Company ownershipCompany = companyService.get(pipeLine.getOwnership_company_id());
            if(ownershipCompany !=null){
                pipeLine.setOwnershipCompany(ownershipCompany.getName());
            }
        }
        return Rets.success(pipeLine);
    }

    /**
     * @Author wuchanghao
     * @Description import excel pipeline data
     * @Date
     * @Param file
     * @return Object
     **/
    @RequestMapping(value = "/importData",method = RequestMethod.POST)
    @RequiresPermissions(value = {Permission.PIPELINE})
    public Object importData(@RequestParam(value = "file") MultipartFile file) throws Exception{
        //According to PipeLine to create ExcelUtil
        ExcelUtil<PipeLine> util = new ExcelUtil<>(PipeLine.class);
        try{
            //Get the data in EXECl
            List<PipeLine> pipeLineList = util.importExcel(file.getInputStream());
            String message = pipeLineService.importUser(pipeLineList);
            return Rets.success(message);
        }catch (Exception e){
            return  Rets.failure("请使用正确的模板导入数据！可点击下载模板后重新导入。");
        }

    }
    /**
     * @Author wuchanghao
     * @Description download pipeline template
     * @Date
     * @Param
     * @return Object
     **/
    @GetMapping("/importTemplate")
    public Object importTemplate(){
        ExcelUtil<PipeLine> util = new ExcelUtil<>(PipeLine.class);
        return util.importTemplateExcel("管线数据");
    }

    /**
     * @Author wuchanghao
     * @Description export pipeline data
     * @Date
     * @Param type,roadName
     * @return Object
     **/
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public Object export(@RequestParam(required = false) Integer type,@RequestParam(required = false) String roadName){
        List<SearchFilter> searchFilterList = new ArrayList<>();
        if(type != null){
            searchFilterList.add(SearchFilter.build("type", SearchFilter.Operator.EQ, type));
        }
        if(roadName != null){
            searchFilterList.add(SearchFilter.build("road_name", SearchFilter.Operator.LIKE, roadName));
        }

        List<PipeLine> list = pipeLineService.queryAll(searchFilterList);
        for(PipeLine pipeLine : list){

            if(pipeLine.getMaterial_id() != null){
                List<Dict> materialType = dictCache.getDictsByPname(Constants.MATERIALTYPE);
                for(Dict dict:materialType){
                    if(dict.getId().intValue() == pipeLine.getMaterial_id()){
                        pipeLine.setMaterial(dict.getName());
                    }
                }
            }

            if(pipeLine.getType() != null){
                List<Dict> pipeLineType  = dictCache.getDictsByPname(Constants.PIPELINETYPE);
                for(Dict dict:pipeLineType){
                    if(dict.getId().intValue() == pipeLine.getType()){
                        pipeLine.setTypeName(dict.getName());
                    }
                }
            }

            if(pipeLine.getFill_type_id() != null){
                List<Dict> fillType = dictCache.getDictsByPname(Constants.FILLTYPE);
                for(Dict dict:fillType){
                    if(dict.getId().intValue() == pipeLine.getFill_type_id()){
                        pipeLine.setFillType(dict.getName());
                    }
                }
            }

            if(pipeLine.getDetect_company_id() != null){
                Company dectCompany = companyService.get(pipeLine.getDetect_company_id());
                if(dectCompany != null){
                    pipeLine.setDetectCompany(dectCompany.getName());
                }
            }

            if(pipeLine.getSupervise_company_id() != null){
                Company superviseCompany = companyService.get(pipeLine.getSupervise_company_id());
                if(superviseCompany != null){
                    pipeLine.setSuperviseCompany(superviseCompany.getName());
                }
            }

            //权属单位
            if(pipeLine.getOwnership_company_id() !=null){
                Company ownershipCompany = companyService.get(pipeLine.getOwnership_company_id());
                if(ownershipCompany !=null){
                    pipeLine.setOwnershipCompany(ownershipCompany.getName());
                }
            }

        }

        ExcelUtil<PipeLine> util = new ExcelUtil<>(PipeLine.class);
        return util.exportExcel(list,"pipeline");
    }

    /**
     * @Author wuchanghao
     * @Description get fillType
     * @Date
     * @Param dictName
     * @return Object
     **/
    @GetMapping(value = "/getDictFillType/{dictName}")
    public Object dictType(@PathVariable String dictName){
        return Rets.success(dictCache.getDictsByPname(dictName));
    }

    /**
     * @Author wuchanghao
     * @Description get MaterialType
     * @Date
     * @Param dictName
     * @return Object
     **/
    @GetMapping(value = "/getDictMaterialType/{dictName}")
    public Object dictMaterialType(@PathVariable String dictName){
        return Rets.success(dictCache.getDictsByPname(dictName));
    }

    /**
     * @Author wuchanghao
     * @Description get company list
     * @Date
     * @Param
     * @return Object
     **/
    @RequestMapping(value = "/company",method = RequestMethod.GET)
    public Object company(){

        return Rets.success(companyService.queryAll().stream().sorted(Comparator.comparing(Company::getEnglishName,String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toList()));
    }

    @GetMapping(value = "/getDictType/{dictName}")
    public Object dictPipeLineType(@PathVariable String dictName){
        return Rets.success(dictCache.getDictsByPname(dictName));
    }

    /**
     * @Author wuchanghao
     * @Description add or edit a pipeline
     * @Date
     * @Param pipeLine
     * @return Object
     **/
    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "修改管线", key = "lineId")
    public Object save(PipeLine pipeLine){
        HttpServletRequest request = HttpUtil.getRequest();
        Long idUser = getIdUser(request);
        if(pipeLine.getId() == null){
            pipeLine.setCrtuser(idUser.toString());
            pipeLine.setCrttime(new Date());
            pipeLineService.insert(pipeLine);
        }else{
            PipeLine pipeLine1 = pipeLineService.findByRecordId(pipeLine.getRecord_id());
            pipeLine.setModuser(idUser.toString());
            pipeLine.setModtime(new Date());
            pipeLine.setGid(pipeLine1.getGid());
            pipeLineService.update(pipeLine);
        }
        return Rets.success();
    }

    /**
     * @Author wuchanghao
     * @Description delete a pipeline
     * @Date
     * @Param recordIds
     * @return Object
     **/
    @BussinessLog(value = "删除管线", key = "lineId")
    @DeleteMapping("/{recordIds}")
    public Object delete(@PathVariable String[] recordIds){
        for(String record_id : recordIds){
            pipeLineService.delPipeLineByRecord_Id(record_id);
        }
        return Rets.success();
    }



}
