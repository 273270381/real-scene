package com.suchness.realscene.api.controller.pipeData;

import com.suchness.realscene.api.controller.BaseController;
import com.suchness.realscene.common.Constants;
import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.constant.factory.PageFactory;
import com.suchness.realscene.common.cache.impl.DictCacheImpl;
import com.suchness.realscene.common.entity.pipe.Company;
import com.suchness.realscene.common.entity.pipe.PipeNode;
import com.suchness.realscene.common.entity.system.Dict;
import com.suchness.realscene.common.enums.Permission;
import com.suchness.realscene.common.service.pipe.CompanyService;
import com.suchness.realscene.common.service.PipeNodeService;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author wuchanghao
 * @Description  pipenode operation
 * @Date  2020/6/29 11:34
 **/
@RestController
@RequestMapping("/pipenode")
@Slf4j
public class PipeNodeController extends BaseController {


    @Autowired
    private PipeNodeService pipeNodeService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DictCacheImpl dictCache;

    /**
     * @Author wuchanghao
     * @Description show pipenode list
     * @Date
     * @Param type,roadName
     * @return Object
     **/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.PIPENODE})
    public Object list(@RequestParam(required = false) Integer type, @RequestParam(required = false) String roadName){
        Page<PipeNode> page = new PageFactory<PipeNode>().defaultPage();
        if(type != null){
            page.addFilter(SearchFilter.build("type", SearchFilter.Operator.EQ, type));
        }
        if(StringUtil.isNotEmpty(roadName)){
            page.addFilter(SearchFilter.build("road_name", SearchFilter.Operator.LIKE, roadName));
        }
        page = pipeNodeService.queryPage(page);
        List<PipeNode> pipeNodes = page.getRecords();
        for(PipeNode pipeNode:pipeNodes){
           String dictStr = dictCache.getDict(pipeNode.getType().longValue());
           pipeNode.setTypeName(dictStr);
        }
        return Rets.success(page);
    }

    /**
     * @Author wuchanghao
     * @Description import pipenode excel data
     * @Date
     * @Param file
     * @return Object
     **/
    @RequestMapping(value = "/importData",method = RequestMethod.POST)
    @RequiresPermissions(value = {Permission.PIPENODE})
    public Object importData(@RequestParam(value = "file") MultipartFile file) throws Exception{
        ExcelUtil<PipeNode> util = new ExcelUtil<>(PipeNode.class);
        try {
            List<PipeNode> pipeNodeList = util.importExcel(file.getInputStream());
            String message = pipeNodeService.importUser(pipeNodeList);
            return Rets.success(message);
        }catch (Exception e){
            return  Rets.failure("请使用正确的模板导入数据！可点击下载模板后重新导入。");
        }
    }

    /**
     * @Author wuchanghao
     * @Description dowmload pipenode template
     * @Date
     * @Param
     * @return Object
     **/
    @GetMapping("/importTemplate")
    public Object importTemplate(){
        ExcelUtil<PipeNode> util = new ExcelUtil<>(PipeNode.class);
        return util.importTemplateExcel("管点数据");
    }

    /**
     * @Author wuchanghao
     * @Description export pipenodes
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

        List<PipeNode> list = pipeNodeService.queryAll(searchFilterList);

        for(PipeNode pipeNode:list){
            //管线类型
            List<Dict> pipeNodeType  = dictCache.getDictsByPname(Constants.PIPELINETYPE);
            for(Dict dict:pipeNodeType){
                if(dict.getId().intValue() == pipeNode.getType()){
                    pipeNode.setTypeName(dict.getName());
                }
            }

            //井盖类型
            if(pipeNode.getManhole_type() !=null){
                List<Dict> manholeType  = dictCache.getDictsByPname(Constants.MANHOLETYPE);
                for(Dict dict:manholeType){
                    if(dict.getId().intValue() == pipeNode.getManhole_type()){
                        pipeNode.setManholeTypeName(dict.getName());
                    }
                }
            }


            //检测单位
            if(pipeNode.getDetection_unit() !=null){
                Company dectCompany = companyService.get(pipeNode.getDetection_unit());
                if(dectCompany !=null){
                    pipeNode.setDetectionUnitName(dectCompany.getName());
                }
            }

            //监理单位
            if(pipeNode.getSupervisor_unit() != null){
                Company superviseCompany = companyService.get(pipeNode.getSupervisor_unit());
                if(superviseCompany != null){
                    pipeNode.setSupervisorUnitName(superviseCompany.getName());
                }

            }

            //权属单位
            if(pipeNode.getOwnership_unit() != null){
                Company ownershipCompany = companyService.get(pipeNode.getOwnership_unit());
                if(ownershipCompany !=null){
                    pipeNode.setOwnershipUnitName(ownershipCompany.getName());
                }
            }

        }

        ExcelUtil<PipeNode> util = new ExcelUtil<>(PipeNode.class);
        return util.exportExcel(list,"pipenode");
    }

    /**
     * @Author wuchanghao
     * @Description add or edit a pipenode
     * @Date
     * @Param pipeNode
     * @return Object
     **/
    @RequestMapping(method = RequestMethod.POST)
    public Object save(PipeNode pipeNode){

        HttpServletRequest request = HttpUtil.getRequest();
        Long idUser = getIdUser(request);

        if(pipeNode.getId() == null){
            pipeNode.setCrtuser(idUser.toString());
            pipeNode.setCrttime(new Date());
            pipeNodeService.insert(pipeNode);

        }else{
            pipeNode.setModuser(idUser.toString());
            pipeNode.setModtime(new Date());
            pipeNodeService.update(pipeNode);

        }
        return Rets.success();
    }

    /**
     * @Author wuchanghao
     * @Description delete a pipenode
     * @Date
     * @Param probePoints
     * @return Object
     **/
    @DeleteMapping("/{probePoints}")
    public Object delete(@PathVariable String[] probePoints){
        for(String probe_point : probePoints){
            pipeNodeService.delPipeNodeByProbe_point(probe_point);
        }
        return Rets.success();
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


    @GetMapping(value = "/manholeType/{dictName}")
    public Object dictCompanyType(@PathVariable String dictName){
        return Rets.success(dictCache.getDictsByPname(dictName));
    }

    /**
     * @Author wuchanghao
     * @Description get pipenode detail by probe_point
     * @Date
     * @Param probe_point
     * @return Object
     **/
    @RequestMapping(value = "/detail/{probe_point}",method = RequestMethod.GET)
    public Object pipeNodeDetail(@PathVariable String probe_point){
        PipeNode pipeNode = pipeNodeService.findByProbePoint(probe_point);
        String dictStr = dictCache.getDict(pipeNode.getType().longValue());
        pipeNode.setTypeName(dictStr);

        if(pipeNode.getManhole_type() != null){
            String manholeStr = dictCache.getDict(pipeNode.getManhole_type().longValue());
            pipeNode.setManholeTypeName(manholeStr);
        }
        //检测单位
        if(pipeNode.getDetection_unit() !=null){
            Company dectCompany = companyService.get(pipeNode.getDetection_unit());
            if(dectCompany !=null){
                pipeNode.setDetectionUnitName(dectCompany.getName());
            }
        }

        //监理单位
        if(pipeNode.getSupervisor_unit() != null){
            Company superviseCompany = companyService.get(pipeNode.getSupervisor_unit());
            if(superviseCompany != null){
                pipeNode.setSupervisorUnitName(superviseCompany.getName());
            }

        }

        //权属单位
        if(pipeNode.getOwnership_unit() != null){
            Company ownershipCompany = companyService.get(pipeNode.getOwnership_unit());
            if(ownershipCompany !=null){
                pipeNode.setOwnershipUnitName(ownershipCompany.getName());
            }
        }
        return Rets.success(pipeNode);
    }

}
