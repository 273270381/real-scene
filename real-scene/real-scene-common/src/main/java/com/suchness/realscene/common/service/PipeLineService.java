package com.suchness.realscene.common.service;

import com.alibaba.fastjson.JSONObject;
import com.suchness.realscene.common.Constants;
import com.suchness.realscene.common.SearchFilter;
import com.suchness.realscene.common.bean.dto.pipe.in.PipeLineDto;
import com.suchness.realscene.common.bean.dto.pipe.out.PipeComResult;
import com.suchness.realscene.common.bean.dto.pipe.out.PipeLineDiameterResult;
import com.suchness.realscene.common.bean.dto.pipe.out.PipeLineResult;
import com.suchness.realscene.common.bean.dto.pipe.out.pipeLineMaterialResult;
import com.suchness.realscene.common.cache.impl.DictCacheImpl;
import com.suchness.realscene.common.dao.pipe.PipeLineRepository;
import com.suchness.realscene.common.entity.pipe.*;
import com.suchness.realscene.common.entity.system.Dict;
import com.suchness.realscene.common.exception.CustomException;
import com.suchness.realscene.common.service.pipe.CompanyService;
import com.suchness.realscene.common.service.system.DictService;
import com.suchness.realscene.common.utils.DateUtils;
import com.suchness.realscene.common.utils.SqlUtil;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.utils.ToolUtil;
import com.suchness.realscene.common.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class PipeLineService extends BaseService<PipeLine, Integer, PipeLineRepository> {

    @Autowired
    private PipeLineRepository pipeLineRepository;

    @Autowired
    private DictCacheImpl dictCache;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DictService dictService;

    public long count() {
        long count = pipeLineRepository.count();
        return count;
    }


    /**
     * @return java.util.Map<java.lang.String                                                                                                                               ,                                                                                                                               java.lang.Object>
     * @Author hejunfeng
     * @Date 13:46 2020/8/28 0028
     * @Param [types, recordIds, limit, page]
     * @Description 根据管线类型和recordId查询管线
     **/
    public Map<String, Object> queryByRange(List<Integer> types, List<String> recordIds, Integer limit, Integer page) {

        List<SearchFilter> s = new ArrayList<>();
        List<Map> linelist = new ArrayList<>();
        Map<String, Object> result = new LinkedHashMap<>(1);
        Page<PipeLine> linePage = new Page<>();
        if (types.size() > 0 && recordIds.size() > 0) {
            s.add(new SearchFilter("type", SearchFilter.Operator.IN, types));
            s.add(new SearchFilter("record_id", SearchFilter.Operator.IN, recordIds));
            Page<PipeLine> p = new Page<>(page, limit);
            p.setFilters(s);
            linePage = queryPage(p);
            if (linePage.getSize() == 0) {
                return null;
            }
            List<PipeLine> lines = linePage.getRecords();
            for (PipeLine pipeLine : lines) {
                Map m = setObject2(pipeLine);
                linelist.add(m);
            }
        }
        result.put("data", JSONObject.toJSON(linelist));
        result.put("count", linePage.getTotal());
        result.put("limit", limit);
        result.put("page", page);
        result.put("totalPage", linePage.getPages());
        return result;
    }


    /**
     * @return java.util.Map<java.lang.String                                                                                                                               ,                                                                                                                               java.lang.Object>
     * @Author hejunfeng
     * @Date 13:47 2020/8/28 0028
     * @Param [recordIds, types, recordId, materials, pipeDiameters, limit, page]
     * @Description 根据条件查询管线
     **/
    public Map<String, Object> queryByComplex(List<String> recordIds, List<Integer> types, String recordId, String materials, String pipeDiameters, Integer limit, Integer page) {
        List<SearchFilter> s = new ArrayList<>();
        if (recordIds.size() > 0) {
            s.add(new SearchFilter("record_id", SearchFilter.Operator.IN, recordIds));
        }
        if (types.size() > 0 && types.get(0) > 0) {
            s.add(new SearchFilter("type", SearchFilter.Operator.IN, types));
        }
        if (ToolUtil.isNotEmpty(recordId) && StringUtil.isNotEmpty(recordId)) {
            s.add(new SearchFilter("record_id", SearchFilter.Operator.LIKE, recordId));
        }
        if (ToolUtil.isNotEmpty(materials) && StringUtil.isNotEmpty(materials) && ToolUtil.isNotEmpty(dictService.findByNameLike(materials)) &&
                dictService.findByNameLike(materials).size() > 0) {
            Integer meterrial = dictService.findByNameLike(materials).get(0).getId().intValue();
            s.add(new SearchFilter("material_id", SearchFilter.Operator.EQ, meterrial));
        }
        if (ToolUtil.isNotEmpty(pipeDiameters) && StringUtil.isNotEmpty(pipeDiameters)) {
            s.add(new SearchFilter("pipe_diameter", SearchFilter.Operator.EQ, pipeDiameters));
        }
        Page<PipeLine> p = new Page<>(page, limit);
        p.setFilters(s);
        Page<PipeLine> linePage = queryPage(p);
        if (linePage.getSize() == 0) {
            return null;
        }
        List<PipeLine> lines = linePage.getRecords();
        List<Object> linelist = new ArrayList<>();
        for (PipeLine pipeLine : lines) {
            Map m = setObject2(pipeLine);
            linelist.add(m);
        }
        Map<String, Object> result = new LinkedHashMap<>(1);
        result.put("data", JSONObject.toJSON(linelist));
        result.put("count", linePage.getTotal());
        result.put("limit", limit);
        result.put("page", page);
        result.put("totalPage", linePage.getPages());
        return result;
    }


    /**
     * @return java.util.Map
     * @Author hejunfeng
     * @Date 13:48 2020/8/28 0028
     * @Param [recordId]
     * @Description 根据recordId查询管线
     **/
    public Map findById(String recordId) {

        PipeLine pipeLine = pipeLineRepository.queryByRecordId(recordId);
        Map<String, Object> result = new LinkedHashMap<>(1);
        List<Object> ls = new ArrayList<>();
        if (pipeLine != null) {
            PipeLineResult m = setObject(pipeLine);
            ls.add(m);
        }
        result.put("data", JSONObject.toJSON(ls));
        result.put("type", "管线");
        return result;
    }


    /**
     * @return com.suchness.realscene.common.bean.dto.pipe.out.PipeLineResult
     * @Author hejunfeng
     * @Date 13:48 2020/8/28 0028
     * @Param [pipeLine]
     * @Description 管线输出字段
     **/
    private PipeLineResult setObject(PipeLine pipeLine) {

        PipeLineResult result = new PipeLineResult();
        if (ToolUtil.isNotEmpty(pipeLine.getBuild_date())) {
            result.setBuildDate(DateUtils.dateYear(pipeLine.getBuild_date()));
        } else {
            result.setBuildDate("");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getCable_number())) {
            result.setCableNumber(pipeLine.getCable_number());
        } else {
            result.setCableNumber(0);
        }
        if (ToolUtil.isNotEmpty(pipeLine.getCompany()) && ToolUtil.isNotEmpty(pipeLine.getCompany().getName())) {
            result.setDetectCompany(pipeLine.getCompany().getName());
        } else {
            result.setDetectCompany("");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getEnd_depth())) {
            result.setEndDepth(pipeLine.getEnd_depth());
        } else {
            result.setEndDepth("");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getEnd_ground_height())) {
            result.setEndGroundHeight(pipeLine.getEnd_ground_height());
        } else {
            result.setEndGroundHeight(0.00);
        }
        if (!StringUtil.isNull(pipeLine.getFill_type_id()) && ToolUtil.isNotEmpty(dictService.get(pipeLine.getFill_type_id().longValue()))
                && ToolUtil.isNotEmpty(dictService.get(pipeLine.getFill_type_id().longValue()).getName())) {
            result.setFillType(dictService.get(pipeLine.getFill_type_id().longValue()).getName());
        } else {
            result.setFillType("");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getMaterial_id()) && ToolUtil.isNotEmpty(dictService.get(pipeLine.getMaterial_id().longValue()))
                && ToolUtil.isNotEmpty(dictService.get(pipeLine.getMaterial_id().longValue()).getName())) {
            result.setMaterial(dictService.get(pipeLine.getMaterial_id().longValue()).getName());
        } else {
            result.setMaterial("");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getPipe_diameter()) && !"".equals(pipeLine.getPipe_diameter())) {
            result.setPipeDiameter(pipeLine.getPipe_diameter());
        } else {
            result.setPipeDiameter("");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getRoad_name()) && !"".equals(pipeLine.getRoad_name())) {
            result.setRoadName(pipeLine.getRoad_name());
        } else {
            result.setRoadName("");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getStart_depth()) && !"".equals(pipeLine.getStart_depth())) {
            result.setStartDepth(pipeLine.getStart_depth());
        } else {
            result.setStartDepth("");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getStart_ground_height())) {
            result.setStartGroundHeight(pipeLine.getStart_ground_height());
        } else {
            result.setStartGroundHeight(0.00);
        }
        if (ToolUtil.isNotEmpty(pipeLine.getType()) && ToolUtil.isNotEmpty(dictService.get(pipeLine.getType().longValue()))
                && ToolUtil.isNotEmpty(dictService.get(pipeLine.getType().longValue()).getName())) {
            result.setType(dictService.get(pipeLine.getType().longValue()).getName());
        } else {
            result.setType("");
        }
        return result;
    }


    /**
     * @return
     * @Author hejunfeng
     * @Description 管线输出字段
     * @Date 10:17 2020/8/28 0028
     * @Param
     **/
    private Map<String, Object> setObject2(PipeLine pipeLine) {
        Map<String, Object> m = new LinkedHashMap<>();

        if (ToolUtil.isNotEmpty(pipeLine.getId())) {
            m.put("lineId", pipeLine.getId());
        }
        if (ToolUtil.isNotEmpty(pipeLine.getType()) && ToolUtil.isNotEmpty(dictService.get(pipeLine.getType().longValue()))
                && ToolUtil.isNotEmpty(dictService.get(pipeLine.getType().longValue()).getName())) {
            m.put("type", dictService.get(pipeLine.getType().longValue()).getName());
        } else {
            m.put("type", 0);
        }
        if (ToolUtil.isNotEmpty(pipeLine.getRecord_id())) {
            m.put("recordId", pipeLine.getRecord_id());
        } else {
            m.put("recordId", "");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getStart_point())) {
            m.put("startPoint", pipeLine.getStart_point());
        } else {
            m.put("startPoint", "");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getEnd_point())) {
            m.put("endPoint", pipeLine.getEnd_point());
        } else {
            m.put("endPoint", "");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getStart_depth())) {
            m.put("startDepth", pipeLine.getStart_depth());
        } else {
            m.put("startDepth", "");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getEnd_depth())) {
            m.put("endDepth", pipeLine.getEnd_depth());
        } else {
            m.put("endDepth", "");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getCompany()) && ToolUtil.isNotEmpty(pipeLine.getCompany().getName())) {
            m.put("detectCompany", pipeLine.getCompany().getName());
        } else {
            m.put("detectCompany", "");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getPipe_diameter())) {
            m.put("pipeDiameter", pipeLine.getPipe_diameter());
        } else {
            m.put("pipeDiameter", "");
        }
        if (ToolUtil.isNotEmpty(pipeLine.getMaterial_id()) && ToolUtil.isNotEmpty(dictService.get(pipeLine.getMaterial_id().longValue())) &&
                ToolUtil.isNotEmpty(dictService.get(pipeLine.getMaterial_id().longValue()).getName())) {
            m.put("material", dictService.get(pipeLine.getMaterial_id().longValue()).getName());
        } else {
            m.put("material", "");
        }
        return m;
    }

    /**
     * @Author wangchan
     * @Date 15:30 2020/8/28
     * @Param [lineIds, types]
     * @Return java.util.List<com.suchness.realscene.common.bean.dto.pipe.out.PipeLineDiameterResult>
     * @Description 根据数组id 数组管线类型，查询管径分类，并计算管径总长度
     **/
    public List<PipeLineDiameterResult> queryDiameterType(String[] lineIds, int[] types) {
        List results = pipeLineRepository.queryDiameterType(lineIds, types);
        List<PipeLineDiameterResult> lists = new ArrayList<PipeLineDiameterResult>();
        for (int i = 0; i < results.size(); i++) {
            Object[] arr = (Object[]) results.get(i);
            BigDecimal bg = new BigDecimal((double) arr[3]);
            double distance = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            PipeLineDiameterResult pipeLineDiameterResult = new PipeLineDiameterResult();
            pipeLineDiameterResult.setType(dictService.queryTypeById((int) arr[0]));
            pipeLineDiameterResult.setPipe_diameter((String) arr[1]);
            pipeLineDiameterResult.setCount(((BigInteger) arr[2]).intValue());
            pipeLineDiameterResult.setDistance(distance);
            lists.add(pipeLineDiameterResult);
        }
        return lists;
    }


    /**
     * @return java.util.List<com.suchness.realscene.common.bean.dto.pipe.out.pipeLineMaterialResult>
     * @Author hejunfeng
     * @Date 14:15 2020/8/28 0028
     * @Param [recordIds, types]
     * @Description 根据管线材质分类
     **/
    public List<pipeLineMaterialResult> queryMaterialType(List<String> recordIds, List<Long> types) {

        List results = new ArrayList();
        if (recordIds.size() == 0) {
            results = pipeLineRepository.queryMaterialType((Long[]) types.toArray(new Long[types.size()]));
        } else {
            results = pipeLineRepository.queryMaterialType((String[]) recordIds.toArray(new String[recordIds.size()]), (Long[]) types.toArray(new Long[types.size()]));
        }
        List<pipeLineMaterialResult> lists = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] arr = (Object[]) results.get(i);
            BigDecimal bg = new BigDecimal((double) arr[3]);
            double distance = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            pipeLineMaterialResult p = new pipeLineMaterialResult();
            if (ToolUtil.isNotEmpty(arr[0]) && ToolUtil.isNotEmpty(dictService.get(((Integer) arr[0]).longValue()))) {
                p.setType(dictService.get(((Integer) arr[0]).longValue()).getName());
            }
            if (ToolUtil.isNotEmpty(arr[1]) && ToolUtil.isNotEmpty(dictService.get(((Integer) arr[1]).longValue()))) {
                p.setPipe_material(dictService.get(((Integer) arr[1]).longValue()).getName());
            }
            p.setCount(((BigInteger) arr[2]).intValue());
            p.setDistance(distance);
            lists.add(p);
        }
        return lists;
    }

    /**
     * @Author wangchan
     * @Date 15:22 2020/8/28
     * @Param [type, condition, lineIds]
     * @Return java.util.List
     * @Description 管线综合统计
     **/
    public List queryCompreStatistics(int[] type, String condition, String[] lineIds) {
        String sql = "select   pipeLineDiameter.{1} , count(record_id) as count ,sum(pipeLineDiameter.distance) as distance  from  (" +
                " select  " +
                " c.record_id," +
                "c.{1}," +
                " c.type," +
                "ST_Distance(" +
                "st_point(c.stx,c.sty)," +
                "st_point(c.endx,c.endy)" +
                ") as distance" +
                " FROM" +
                "   (select  a.record_id as record_id ,a.stx as stx,a.sty as sty,b.endx as endx,b.endy as endy ,b.{1} ,b.type  from (select pipe_node.x_coordinates as stx, pipe_node.y_coordinates as sty,a.record_id" +
                " from  pipe_node,(SELECT pipe_line.record_id as record_id , pipe_line.start_point as st_point" +
                " FROM pipe_line" +
                ") as a where pipe_node.probe_point=a.st_point) a ," +
                "" +
                "(select pipe_node.x_coordinates as endx, pipe_node.y_coordinates as endy,a.record_id ,a.{1},a.type" +
                " from  pipe_node,(SELECT pipe_line.record_id as record_id,pipe_line.end_point as end_point ,{1} ,type" +
                " FROM pipe_line" +
                " ) as a where pipe_node.probe_point=a.end_point) b  where a.record_id = b.record_id) c" +
                "  where" +
                "  c.type in {0}  {2}) pipeLineDiameter group" +
                " by  pipeLineDiameter.{1}";
        String resultSql = "";
        if ("all".equals(lineIds[0])) {
            resultSql = MessageFormat.format(sql, SqlUtil.printArray(type), condition, "");
        } else {
            resultSql = MessageFormat.format(sql, SqlUtil.printArray(type), condition, SqlUtil.printStringArray(lineIds));

        }
        System.out.println(resultSql);
        List<Map> maps = pipeLineRepository.queryBySql(resultSql);
        List<PipeComResult> lists = new ArrayList<PipeComResult>();
        for (Map map : maps) {
            PipeComResult pipeComResult = new PipeComResult();
            pipeComResult = new PipeComResult();
            BigDecimal bg = new BigDecimal((double) map.get("distance"));
            double distance = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            pipeComResult.setCount(((BigInteger) map.get("count")).intValue());
//            pipeComResult.setType(dictService.queryTypeById((int) map.get("type")));
            pipeComResult.setDistance(distance);
            if ((map.get(condition)) instanceof String) {
                pipeComResult.setCondition((String) (map.get(condition)));
            } else {
                if (map.get(condition) == null) {
                    pipeComResult.setCondition(null);
                } else {
                    int id = (int) map.get(condition);

                    pipeComResult.setCondition(dictService.queryTypeById(id));
                }
            }
            lists.add(pipeComResult);
        }
        return lists;
    }

    /**
     * @Author wangchan
     * @Date 2020/8/28
     * @Param [type, lineIds]
     * @Return java.util.List
     * @Description 管线综合统计
     **/
    public List queryAllCompreStatistics(int[] type, String[] lineIds) {

        String sql = "select pipeLineDiameter.type , count(record_id) as count ,sum(pipeLineDiameter.distance) as distance  from  (" +
                " select  " +
                " c.record_id," +
                " c.type," +
                "ST_Distance(" +
                "st_point(c.stx,c.sty)," +
                "st_point(c.endx,c.endy)" +
                ") as distance" +
                " FROM" +
                "   (select a.record_id as record_id ,a.stx as stx,a.sty as sty,b.endx as endx,b.endy as endy ,b.type  from (select pipe_node.x_coordinates as stx, pipe_node.y_coordinates as sty,a.record_id" +
                " from  pipe_node,(SELECT pipe_line.record_id as record_id , pipe_line.start_point as st_point" +
                " FROM pipe_line" +
                ") as a where pipe_node.probe_point=a.st_point) a ," +
                "" +
                "(select pipe_node.x_coordinates as endx, pipe_node.y_coordinates as endy,a.record_id ,a.type" +
                " from  pipe_node,(SELECT pipe_line.record_id as record_id,pipe_line.end_point as end_point ,type" +
                " FROM pipe_line" +
                " ) as a where pipe_node.probe_point=a.end_point) b  where a.record_id = b.record_id) c" +
                "  where" +
                "  c.type in {0}  {1}) pipeLineDiameter  group by  pipeLineDiameter.type";
        String resultSql = "";
        if ("all".equals(lineIds[0])) {
            resultSql = MessageFormat.format(sql, SqlUtil.printArray(type), "");
        } else {
            resultSql = MessageFormat.format(sql, SqlUtil.printArray(type), SqlUtil.printStringArray(lineIds));
        }
        List<Map> maps = pipeLineRepository.queryBySql(resultSql);
        List<PipeComResult> lists = new ArrayList<PipeComResult>();
        System.out.println(sql);
        for (Map map : maps) {
            PipeComResult pipeComResult = new PipeComResult();
            pipeComResult = new PipeComResult();
            BigDecimal bg = new BigDecimal((double) map.get("distance"));
            double disntance = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            pipeComResult.setCount(((BigInteger) map.get("count")).intValue());
           pipeComResult.setType(dictService.queryTypeById((int) map.get("type")));
            pipeComResult.setDistance(disntance);
            lists.add(pipeComResult);
        }
        return lists;
    }

    public String importUser(List<PipeLine> pipeLineList) {

        if (StringUtil.isNull(pipeLineList) || pipeLineList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (PipeLine pipeLine : pipeLineList) {
            try {
                //管线类型
                List<Dict> pipeNodeType = dictCache.getDictsByPname(Constants.PIPELINETYPE);
                for (Dict dict : pipeNodeType) {
                    if (pipeLine.getPipeLineTypeName().contains(dict.getName())) {
                        pipeLine.setType(dict.getId().intValue());
                    }
                }

                //材质类型
                List<Dict> materialType = dictCache.getDictsByPname(Constants.MATERIALTYPE);
                for (Dict dict : materialType) {
                    if (dict.getName().equals(pipeLine.getMaterial())) {
                        pipeLine.setMaterial_id(dict.getId().intValue());
                    }
                }

                //材质类型
                List<Dict> fillType = dictCache.getDictsByPname(Constants.FILLTYPE);
                for (Dict dict : fillType) {
                    if (dict.getName().equals(pipeLine.getFillType())) {
                        pipeLine.setFill_type_id(dict.getId().intValue());
                    }
                }


                //检测单位
                if (pipeLine.getDetectCompany() != null && !"".equals(pipeLine.getDetectCompany())) {
                    Company dectCompany = companyService.get(SearchFilter.build("name", SearchFilter.Operator.LIKE, pipeLine.getDetectCompany()));
                    if (dectCompany != null) {
                        pipeLine.setDetect_company_id(dectCompany.getId());
                    }
                }


                //监理单位
                if (pipeLine.getSuperviseCompany() != null && !"".equals(pipeLine.getSuperviseCompany())) {
                    Company superviseCompany = companyService.get(SearchFilter.build("name", SearchFilter.Operator.LIKE, pipeLine.getSuperviseCompany()));
                    if (superviseCompany != null) {
                        pipeLine.setSupervise_company_id(superviseCompany.getId());
                    }
                }

                //权属单位
                if (pipeLine.getOwnershipCompany() != null && !"".equals(pipeLine.getOwnershipCompany())) {
                    Company ownershipCompany = companyService.get(SearchFilter.build("name", SearchFilter.Operator.LIKE, pipeLine.getOwnershipCompany()));
                    if (ownershipCompany != null) {
                        pipeLine.setOwnership_company_id(ownershipCompany.getId());
                    }
                }


                pipeLineRepository.save(pipeLine);
                successNum++;
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、管线 " + pipeLine.getRecord_id() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }

        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            return failureMsg.toString();
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条。");
            return successMsg.toString();
        }


    }

    /**
     * @Author wangchan
     * @Date 15:25 2020/8/28
     * @Param []
     * @Return java.lang.String[]
     * @Description 查询所有管线
     **/
    public String[] queryAllIds() {
        List<String> list = pipeLineRepository.queryAllIds();
        String[] strings = new String[list.size()];

        strings = list.toArray(strings);

        return strings;
    }


    /**
     * @return java.util.List<java.lang.String>
     * @Author hejunfeng
     * @Date 14:16 2020/8/28 0028
     * @Param []
     * @Description 查询所有管线管径
     **/
    public List<String> findAllDiameters() {

        List<String> diameters = pipeLineRepository.queryDiameters();
        return diameters;
    }

    public void delPipeLineByRecord_Id(String record_id) {
        pipeLineRepository.deleteByRecord_Id(record_id);
    }


    /**
     * @return java.util.List<java.lang.Object>
     * @Author hejunfeng
     * @Date 14:17 2020/8/28 0028
     * @Param [recordIds]
     * @Description 管线分析
     **/
    public List<Object> findByRecordIds(List<String> recordIds) {

        String str = recordIds.toString().trim();
        String s = str.substring(1, str.length() - 1);
        List<PipeLine> lines = pipeLineRepository.findByRecordIds((String[]) recordIds.toArray(new String[recordIds.size()]), s);
        List<Object> linelist = new ArrayList<>();
        for (PipeLine pipeLine : lines) {
            Map m = setObject2(pipeLine);
            if (ToolUtil.isNotEmpty(pipeLine.getRoad_name())) {
                m.put("roadName", pipeLine.getRoad_name());
            } else {
                m.put("roadName", "");
            }
            if (ToolUtil.isNotEmpty(pipeLine.getStart_ground_height())) {
                m.put("startGroundHeight", pipeLine.getStart_ground_height());
            } else {
                m.put("startGroundHeight", 0.00);
            }
            if (ToolUtil.isNotEmpty(pipeLine.getEnd_ground_height())) {
                m.put("endGroundHeight", pipeLine.getEnd_ground_height());
            } else {
                m.put("endGroundHeight", 0.00);
            }
            if (ToolUtil.isNotEmpty(pipeLine.getPipeNode()) && ToolUtil.isNotEmpty(pipeLine.getPipeNode().getX_coordinates())) {
                m.put("startX", pipeLine.getPipeNode().getX_coordinates());
            } else {
                m.put("startX", 0.00);
            }
            if (ToolUtil.isNotEmpty(pipeLine.getPipeNode()) && ToolUtil.isNotEmpty(pipeLine.getPipeNode().getY_coordinates())) {
                m.put("startY", pipeLine.getPipeNode().getY_coordinates());
            } else {
                m.put("startY", 0.00);
            }
            if (ToolUtil.isNotEmpty(pipeLine.getPipeNode2()) && ToolUtil.isNotEmpty(pipeLine.getPipeNode2().getX_coordinates())) {
                m.put("endX", pipeLine.getPipeNode2().getX_coordinates());
            } else {
                m.put("endX", 0.00);
            }
            if (ToolUtil.isNotEmpty(pipeLine.getPipeNode2()) && ToolUtil.isNotEmpty(pipeLine.getPipeNode2().getY_coordinates())) {
                m.put("endY", pipeLine.getPipeNode2().getY_coordinates());
            } else {
                m.put("endY", 0.00);
            }

            linelist.add(m);
        }
        return linelist;
    }


    /**
     * @return com.suchness.realscene.common.entity.pipe.PipeLine
     * @Author hejunfeng
     * @Date 14:44 2020/8/28 0028
     * @Param [record_id]
     * @Description 管线查询
     **/
    public PipeLine findByRecordId(String record_id) {
        return pipeLineRepository.queryByRecordId(record_id);
    }


    /**
     * @return java.util.Map
     * @Author hejunfeng
     * @Date 14:20 2020/8/28 0028
     * @Param [recordIds, types, date]
     * @Description 年限分析
     **/
    public Map queryByDate(List<String> recordIds, List<Integer> types, Integer date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -date);
        String time = format.format(c.getTime());
        List<SearchFilter> s = new ArrayList<>();
        if (recordIds.size() > 0) {
            s.add(new SearchFilter("record_id", SearchFilter.Operator.IN, recordIds));
        }
        if (types.size() > 0) {
            s.add(new SearchFilter("type", SearchFilter.Operator.IN, types));
        }
        if (StringUtil.isNotEmpty(time)) {
            s.add(new SearchFilter("build_date", SearchFilter.Operator.LTE, DateUtils.parseDate(time)));
        }
        Page<PipeLine> p = new Page<>();
        p.setFilters(s);
        Page<PipeLine> pages = queryPage(p);
        List<Object> linelist = new ArrayList<>();
        List<PipeLine> lines = pages.getRecords();
        Map<String, Object> result = new LinkedHashMap<>();
        for (PipeLine line : lines) {
            Map m = setObject2(line);
            if (line.getBuild_date() != null) {
                m.put("buildDate", DateUtils.dateYear(line.getBuild_date()));
            } else {
                m.put("buildDate", "");
            }
            linelist.add(m);
        }
        result.put("data", linelist);
        result.put("count", pages.getTotal());
        return result;
    }
}
