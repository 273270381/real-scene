package com.suchness.realscene.common.service;

import com.alibaba.fastjson.JSONObject;
import com.suchness.realscene.common.Constants;
import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.dto.pipe.out.PipeNodeAppendagesResult;
import com.suchness.realscene.common.bean.dto.pipe.out.PipeNodeFeatureResult;
import com.suchness.realscene.common.bean.dto.pipe.out.PipeNodeResult;
import com.suchness.realscene.common.cache.impl.DictCacheImpl;
import com.suchness.realscene.common.dao.pipe.PipeNodeRepository;
import com.suchness.realscene.common.entity.pipe.*;
import com.suchness.realscene.common.entity.system.Dict;
import com.suchness.realscene.common.exception.CustomException;
import com.suchness.realscene.common.service.pipe.CompanyService;
import com.suchness.realscene.common.service.system.DictService;
import com.suchness.realscene.common.utils.DateUtils;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service

public class PipeNodeService extends BaseService<PipeNode,Integer, PipeNodeRepository> {

    @Autowired
    private PipeNodeRepository pipeNodeRepository;

    @Autowired
    private DictCacheImpl dictCache;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DictService dictService;



    /**
     * @Author hejunfeng
     * @Date 14:21 2020/8/28 0028
     * @Param [probePoint]
     * @return java.util.Map
     * @Description 根据probePoint 查询管点
     **/
    public Map findById(String probePoint){
        PipeNode pipeNode = pipeNodeRepository.queryByProbePoint(probePoint);
        Map<String , Object> result = new LinkedHashMap<>(1);
        PipeNodeResult pipeNodeResult = new PipeNodeResult();
        if (ToolUtil.isNotEmpty(pipeNode)){
            if (ToolUtil.isNotEmpty(pipeNode.getType())  &&  ToolUtil.isNotEmpty(dictService.get(pipeNode.getType().longValue()))
                    && ToolUtil.isNotEmpty(dictService.get(pipeNode.getType().longValue()).getName())){
                pipeNodeResult.setType(dictService.get(pipeNode.getType().longValue()).getName());
            }else{
                pipeNodeResult.setType("");
            }
            if (ToolUtil.isNotEmpty(pipeNode.getX_coordinates())){
                pipeNodeResult.setXCoordinates(Double.parseDouble(String.valueOf(pipeNode.getX_coordinates())));
            }else{
                pipeNodeResult.setXCoordinates(0.00);
            }
            if (ToolUtil.isNotEmpty(pipeNode.getY_coordinates()) ){
                pipeNodeResult.setYCoordinates(Double.parseDouble(String.valueOf(pipeNode.getY_coordinates())));
            }else{
                pipeNodeResult.setYCoordinates(0.00);
            }
            if (ToolUtil.isNotEmpty(pipeNode.getAppendages())  && StringUtil.isNotEmpty(pipeNode.getAppendages())){
                pipeNodeResult.setAppendages(pipeNode.getAppendages());
            }else{
                pipeNodeResult.setAppendages("");
            }
            if (ToolUtil.isNotEmpty(pipeNode.getCompany()) && ToolUtil.isNotEmpty(pipeNode.getCompany().getName()) ){
                pipeNodeResult.setDetectCompany(pipeNode.getCompany().getName());
            }else{
                pipeNodeResult.setDetectCompany("");
            }
            if (ToolUtil.isNotEmpty(pipeNode.getFeatures()) && StringUtil.isNotEmpty(pipeNode.getFeatures())){
                pipeNodeResult.setFeatures(pipeNode.getFeatures());
            }else{
                pipeNodeResult.setFeatures("");
            }
            if (ToolUtil.isNotEmpty(pipeNode.getManhole_diameter())&& StringUtil.isNotEmpty(pipeNode.getManhole_diameter())){
                pipeNodeResult.setManholeDiameter(pipeNode.getManhole_diameter());
            }else{
                pipeNodeResult.setManholeDiameter("");
            }
            if (ToolUtil.isNotEmpty(pipeNode.getManhole_material())  && StringUtil.isNotEmpty(pipeNode.getManhole_material())){
                pipeNodeResult.setManholeMaterial(pipeNode.getManhole_material());
            }else{
                pipeNodeResult.setManholeMaterial("");
            }
            if (ToolUtil.isNotEmpty(pipeNode.getHole_depth()) && StringUtil.isNotEmpty(pipeNode.getHole_depth())){
                pipeNodeResult.setHoleDepth(pipeNode.getHole_depth());
            }else{
                pipeNodeResult.setHoleDepth("");
            }
            if (ToolUtil.isNotEmpty(pipeNode.getRoad_name()) && StringUtil.isNotEmpty(pipeNode.getRoad_name())){
                pipeNodeResult.setRoadName(pipeNode.getRoad_name());
            }else{
                pipeNodeResult.setRoadName("");
            }
            if (ToolUtil.isNotEmpty(pipeNode.getBuild_date())){
                pipeNodeResult.setBuildDate(DateUtils.dateYear(pipeNode.getBuild_date()));
            }else{
                pipeNodeResult.setBuildDate("");
            }

        }
        result.put("data", JSONObject.toJSON(pipeNodeResult));
        result.put("type","管点");
        return result;
    }

    /**
     * @Author wangchan
     * @Date 15:43 2020/8/28
     * @Param [nodeRecordIds, types]
     * @Return java.util.List<com.suchness.realscene.common.bean.dto.pipe.out.PipeNodeFeatureResult>
     * @Description  根据管点特征分类分组查询
    **/

    public List<PipeNodeFeatureResult> queryFeature(String[] nodeRecordIds, int[] types) {

        List results = pipeNodeRepository.queryFeature(nodeRecordIds,  types);
        List<PipeNodeFeatureResult> lists=new ArrayList<PipeNodeFeatureResult>();
        for(int i=0;i<results.size();i++){
            Object[] arr=  (Object[]) results.get(i);
            PipeNodeFeatureResult pipeNodeFeature=new PipeNodeFeatureResult();
            pipeNodeFeature.setType(dictService.queryTypeById((int)arr[0]));
            pipeNodeFeature.setFeatures((String)arr[1]);
            pipeNodeFeature.setCount(((BigInteger)arr[2]).intValue());
            lists.add(pipeNodeFeature);
        }
        return  lists;
    }


    /**
     * @Author hejunfeng
     * @Date 14:28 2020/8/28 0028
     * @Param [probePoints, types]
     * @return java.util.List<com.suchness.realscene.common.bean.dto.pipe.out.PipeNodeAppendagesResult>
     * @Description 根据管点附属物进行分类
     **/
    public List<PipeNodeAppendagesResult> queryAppendages(List<String> probePoints, List<Integer> types){
        List result = new ArrayList();
        if (probePoints.size() == 0){
            result = pipeNodeRepository.queryAppendages((Integer[]) types.toArray(new Integer[types.size()]));
        }else {
            result = pipeNodeRepository.queryAppendages((String[])probePoints.toArray(new String[probePoints.size()]),(Integer[]) types.toArray(new Integer[types.size()]));
        }
        List<PipeNodeAppendagesResult> ls = new ArrayList<>();
        for (int i = 0 ; i < result.size() ; i++){
            Object[] arr = (Object[]) result.get(i);
            PipeNodeAppendagesResult appendages = new PipeNodeAppendagesResult();
            appendages.setType(dictService.get(((Integer) arr[0]).longValue()).getName());
            appendages.setAppendages((String) arr[1]);
            appendages.setCount(((BigInteger) arr[2]).intValue());
            ls.add(appendages);
        }
        return ls;
    }

    public String importUser(List<PipeNode> pipeNodeList){

        if(StringUtil.isNull(pipeNodeList) || pipeNodeList.size() ==0){
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for(PipeNode pipenode:pipeNodeList){
            try{

                //管线类型
                List<Dict> pipeNodeType  = dictCache.getDictsByPname(Constants.PIPELINETYPE);
                for(Dict dict:pipeNodeType){
                    if(pipenode.getPipeNodeType().contains(dict.getName())){
                        pipenode.setType(dict.getId().intValue());
                    }
                }

                //井盖类型
                List<Dict> manholeType  = dictCache.getDictsByPname(Constants.MANHOLETYPE);
                for(Dict dict:manholeType){
                    if(dict.getName().equals(pipenode.getManholeTypeName())){
                        pipenode.setManhole_type(dict.getId().intValue());
                    }
                }

                //检测单位
                if(pipenode.getDetectionUnitName() != null && !"".equals(pipenode.getDetectionUnitName())){
                    Company dectCompany = companyService.get(SearchFilter.build("name", SearchFilter.Operator.LIKE, pipenode.getDetectionUnitName()));
                    if(dectCompany !=null){
                        pipenode.setDetection_unit(dectCompany.getId());
                    }
                }


                //监理单位
                if(pipenode.getSupervisorUnitName() != null && !"".equals(pipenode.getSupervisorUnitName())){
                    Company superviseCompany = companyService.get(SearchFilter.build("name", SearchFilter.Operator.LIKE, pipenode.getSupervisorUnitName()));
                    if(superviseCompany != null){
                        pipenode.setSupervisor_unit(superviseCompany.getId());
                    }
                }

                //权属单位
                if(pipenode.getOwnershipUnitName()!=null && !"".equals(pipenode.getOwnershipUnitName())){
                    Company ownershipCompany = companyService.get(SearchFilter.build("name", SearchFilter.Operator.LIKE, pipenode.getOwnershipUnitName()));
                    if(ownershipCompany !=null){
                        pipenode.setOwnership_unit(ownershipCompany.getId());
                    }
                }

/*                PipeNode pipeNode2 = pipeNodeRepository.queryByRecord_Id(pipenode.getRecord_id());
                if(pipeNode2 != null){
                    pipenode.setId(pipeNode2.getId());
                }*/
                pipeNodeRepository.save(pipenode);
                successNum++;
            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、管点 " + pipenode.getId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }

        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            return failureMsg.toString();
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条。");
            return successMsg.toString();
        }


    }


    public  void delPipeNodeById(Integer id){
        pipeNodeRepository.deleteById(id);
    }

    /**
     * @Author wangchan
     * @Description //TODO 查询所有管点
     * @Date 10:33 2020/8/28
     * @Param []
     * @return java.lang.String[]
    **/
    public String[] queryAllIds() {
        List<String> list = pipeNodeRepository.queryAllIds();
        String[] strings = new String[list.size()];

        strings=   list.toArray(strings);

        return  strings;
    }


    public void delPipeNodeByProbe_point(String probe_point) {
        pipeNodeRepository.deleteByProbe_point(probe_point);
    }

    public PipeNode findByProbePoint(String probe_point) {
        return pipeNodeRepository.queryByProbePoint(probe_point);
    }
}
