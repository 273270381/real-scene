package com.suchness.realscene.api.controller.api;


import com.suchness.realscene.common.Constants;
import com.suchness.realscene.common.bean.dto.pipe.in.PipeLineDto;
import com.suchness.realscene.common.bean.dto.pipe.in.PipeNodeDto;
import com.suchness.realscene.common.bean.dto.pipe.in.PipelineComDto;
import com.suchness.realscene.common.bean.dto.pipe.out.*;
import com.suchness.realscene.common.cache.DictCache;
import com.suchness.realscene.common.entity.system.Dict;
import com.suchness.realscene.common.service.PipeLineService;
import com.suchness.realscene.common.service.PipeNodeService;
import com.suchness.realscene.common.vo.Rets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @Author hejunfeng
 * @Date 13:34 2020/8/28 0028
 * @Description 管线查询controller
 **/
@RestController
@RequestMapping("/api/line")
public class PipeQuireController {
    private Logger logger = LoggerFactory.getLogger(PipeQuireController.class);

    @Autowired
    private PipeLineService pipeLineService;

    @Autowired
    private PipeNodeService pipeNodeService;

    @Autowired
    private DictCache dictCache;


    /**
     * @return java.lang.Object
     * @Author hejunfeng
     * @Date 13:34 2020/8/28 0028
     * @Param [map]
     * @Description 属性查询（管点或管线）
     **/
    @RequestMapping(value = "/getLineAttributes", method = RequestMethod.POST)
    @ResponseBody
    public Object getAttributes(@RequestBody Map<String, Object> map) {
        try {
            int type = (Integer) map.get("type");
            String recordId = (String) map.get("lineId");
            Map<String, Object> result = new LinkedHashMap<>(1);
            //0代表管线
            int s = 0;
            if (s == type) {
                result = pipeLineService.findById(recordId);
                if (result.size() != 0) {
                    return Rets.success(result);
                }
                return Rets.failure("没有符合的管线");
            } else {  //管点
                result = pipeNodeService.findById(recordId);
                if (result.size() != 0) {
                    return Rets.success(result);
                }
                return Rets.failure("没有符合的管点");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }


    /**
     * @return java.lang.Object
     * @Author hejunfeng
     * @Date 13:35 2020/8/28 0028
     * @Param [map]
     * @Description 空间查询
     **/
    @RequestMapping(value = "/getLineRangeAttributes", method = RequestMethod.POST)
    @ResponseBody
    public Object querySpace(@RequestBody Map<String, Object> map) {
        try {
            List<Integer> types = (List<Integer>) map.get("types");
            List<String> recordIds = (List<String>) map.get("lineIds");
            Integer limit = (Integer) map.get("limit");
            Integer page = (Integer) map.get("page");
            if (page < 1 || page == null) {
                page = 1;
            }
            Map<String, Object> result = pipeLineService.queryByRange(types, recordIds, limit, page);
            if (result != null) {
                return Rets.success(result);
            }
            return Rets.success("没有符合的管线");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }


    /**
     * @return java.lang.Object
     * @Author hejunfeng
     * @Date 13:35 2020/8/28 0028
     * @Param [map]
     * @Description 复合查询
     **/
    @RequestMapping(value = "/getComplexLineAttributes", method = RequestMethod.POST)
    @ResponseBody
    public Object queryComplex(@RequestBody Map<String, Object> map) {
        try {
            List<String> recordIds = (List<String>) map.get("lineIds");
            List<Integer> types = (List<Integer>) map.get("types");
            String recordId = (String) map.get("lineId");
            String materials = (String) map.get("materialIds");
            String pipeDiameters = (String) map.get("pipeDiameters");
            Integer limit = (Integer) map.get("limit");
            Integer page = (Integer) map.get("page");
            if (page < 1 || page == null) {
                page = 1;
            }
            Map<String, Object> result = pipeLineService.queryByComplex(recordIds, types, recordId, materials, pipeDiameters, limit, page);
            if (result != null) {
                return Rets.success(result);
            }
            return Rets.success("没有符合的管线");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }


    /**
     * @return java.lang.Object
     * @Author hejunfeng
     * @Date 13:35 2020/8/28 0028
     * @Param [map]
     * @Description 管线材质分类
     **/
    @RequestMapping(value = "/getMaterialClassifica", method = RequestMethod.POST)
    @ResponseBody
    public Object getMaterialClassifica(@RequestBody Map<String, Object> map) {
        try {
            List<String> recordIds = (List<String>) map.get("lineIds");
            List<Integer> t = (List<Integer>) map.get("types");
            List<Long> types = new ArrayList<>();
            for (Integer i : t) {
                types.add(i.longValue());
            }
            List<pipeLineMaterialResult> p = pipeLineService.queryMaterialType(recordIds, types);
            if (p.size() == 0) {
                return Rets.success(new ArrayList<>());
            }
            return Rets.success(p);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }


    /**
     * @return java.lang.Object
     * @Author hejunfeng
     * @Date 13:36 2020/8/28 0028
     * @Param [map]
     * @Description 管点附属物分类
     **/
    @RequestMapping(value = "/getAppendagesClassifica", method = RequestMethod.POST)
    @ResponseBody
    public Object getAppendagesClassifica(@RequestBody Map<String, Object> map) {
        try {
            List<String> nodeRecordIds = (List<String>) map.get("nodeRecordIds");
            List<Integer> types = (List<Integer>) map.get("types");
            List<PipeNodeAppendagesResult> ls = pipeNodeService.queryAppendages(nodeRecordIds, types);
            if (ls.size() == 0) {
                return Rets.success(new ArrayList<>());
            }
            return Rets.success(ls);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }

    /**
     * @Author wangchan
     * @Date 15:15 2020/8/28
     * @Param [dto]
     * @Return java.lang.Object
     * @Description 管线管径分类
     **/
    @RequestMapping(value = "/getpipeDiameterClassifica", method = RequestMethod.POST)
    @ResponseBody
    public Object getAppendagesClassifica(@RequestBody PipeLineDto dto) {
        List<PipeLineDiameterResult> diameterList = new ArrayList<>();
        try {
            String[] lineIds = dto.getLineIds();
            if (lineIds.length == 0) {
                return Rets.success(diameterList);
            }
            if (("all").equals(lineIds[0])) {
                lineIds = pipeLineService.queryAllIds();
            }
            int[] types = dto.getTypes();
            diameterList = pipeLineService.queryDiameterType(lineIds, types);
            return Rets.success(diameterList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }

    /**
     * @Author wangchan
     * @Date 15:18 2020/8/28
     * @Param [dto]
     * @Return java.lang.Object
     * @Description 管点特征分类
     **/
    @RequestMapping(value = "/getFeatureClassifica", method = RequestMethod.POST)
    @ResponseBody
    public Object getFeatureClassifica(@RequestBody PipeNodeDto dto) {
        List<PipeNodeFeatureResult> diameterList = new ArrayList<>();
        try {
            String[] nodeRecordIds = dto.getNodeRecordIds();
            int[] types = dto.getTypes();
            if (types.length == 0) {
                return Rets.success(diameterList);
            }
            if (nodeRecordIds.length == 0) {
                return Rets.success(diameterList);
            }
            if ("all".equals(nodeRecordIds[0])) {
                nodeRecordIds = pipeNodeService.queryAllIds();
            }
            diameterList = pipeNodeService.queryFeature(nodeRecordIds, types);
            return Rets.success(diameterList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.success(diameterList);
        }
    }

    /**
     * @Author wangchan
     * @Date 15:18 2020/8/28
     * @Param [dto]
     * @Return java.lang.Object
     * @Description 管线综合统计
     **/
    @RequestMapping(value = "/getCompreStatistics", method = RequestMethod.POST)
    @ResponseBody
    public Object getCompreStatistics(@RequestBody PipelineComDto dto) {
        String condition = dto.getCondition();
        String[] lineIds = dto.getLineIds();
        int[] type = dto.getTypes();
        List<PipeComResult> diameterList = new ArrayList<>();
        try {
            if (type.length == 0) {
                return Rets.success(diameterList);
            }
            if (lineIds.length == 0) {
                return Rets.success(diameterList);
            }
            if (type[0] == 0) {
                List<Dict> dicts = dictCache.getDictsByPname("管线类型");
                type = new int[dicts.size()];
                for (int i = 0; i < dicts.size(); i++) {
                    type[i] = (dicts.get(i).getId()).intValue();
                }
            }
            if ("type".equals(condition)) {
                diameterList = pipeLineService.queryAllCompreStatistics(type, lineIds);

            } else {
                diameterList = pipeLineService.queryCompreStatistics(type, condition, lineIds);
            }
            return Rets.success(diameterList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.success(diameterList);
        }
    }

    /**
     * @Author wangchan
     * @Date 15:19 2020/8/28
     * @Param []
     * @Return java.lang.Object
     * @Description 所有管线类型查询
     **/
    @RequestMapping(value = "/getAllLineTypes", method = RequestMethod.POST)
    @ResponseBody
    public Object getMaterialClassifica() {
        try {
            List<Dict> p = dictCache.getDictsByPname("管线类型");
            List<PipeLineTypeResult> pipeLineTypes = new ArrayList<PipeLineTypeResult>();
            for (Dict dict : p) {
                System.out.println(dict.getId() + ":" + dict.getName());
                PipeLineTypeResult pipeLineType = new PipeLineTypeResult();
                pipeLineType.setId((dict.getId()).intValue());
                pipeLineType.setName(dict.getName());
                pipeLineTypes.add(pipeLineType);
            }
            if (p.size() == 0) {
                return Rets.success("管线类型为空");
            }
            return Rets.success(pipeLineTypes);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }

    /**
     * @return java.lang.Object
     * @Author hejunfeng
     * @Date 13:37 2020/8/28 0028
     * @Param []
     * @Description 查询管线材质分类
     **/
    @RequestMapping(value = "/getAllLineMaterials", method = RequestMethod.POST)
    @ResponseBody
    public Object getMaterials() {
        try {
            List<Dict> p = dictCache.getDictsByPname(Constants.MATERIALTYPE);
            List<Map<String, Object>> maplist = new ArrayList<>();
            if (p.size() == 0) {
                return Rets.success("管线材质为空");
            }
            for (Dict dict : p) {
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("id", dict.getPid().intValue());
                m.put("materials", dict.getName());
                maplist.add(m);
            }
            return Rets.success(maplist);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }


    /**
     * @return java.lang.Object
     * @Author hejunfeng
     * @Date 13:37 2020/8/28 0028
     * @Param []
     * @Description 查询管线管径分类
     **/
    @RequestMapping(value = "/getAllLineDiameters", method = RequestMethod.POST)
    @ResponseBody
    public Object getDiameters() {
        try {
            List<String> diameters = pipeLineService.findAllDiameters();
            List<Map<String, Object>> maplist = new ArrayList<>();
            if (diameters.size() == 0) {
                return Rets.success("管线管径为空");
            }
            for (String diameter : diameters) {
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("diameter", diameter);
                maplist.add(m);
            }
            return Rets.success(maplist);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }


    /**
     * @return java.lang.Object
     * @Author hejunfeng
     * @Date 13:37 2020/8/28 0028
     * @Param [map]
     * @Description 管线分析
     **/
    @RequestMapping(value = "/getMoreLineAttributes", method = RequestMethod.POST)
    @ResponseBody
    public Object getLineAttributes(@RequestBody Map<String, Object> map) {
        try {
            List<String> recordIds = (List<String>) map.get("lineIds");
            if (recordIds.size() != 0) {
                List<Object> objects = pipeLineService.findByRecordIds(recordIds);
                if (objects.size() == 0) {
                    return Rets.success(new ArrayList<>());
                }
                return Rets.success(objects);
            } else {
                return Rets.success(new ArrayList<>());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }


    /**
     * @return java.lang.Object
     * @Author hejunfeng
     * @Date 13:37 2020/8/28 0028
     * @Param [map]
     * @Description 年限分析
     **/
    @RequestMapping(value = "/getBuildDateClassifica", method = RequestMethod.POST)
    @ResponseBody
    public Object getBuildDateClassifica(@RequestBody Map<String, Object> map) {
        try {
            List<String> recordIds = (List<String>) map.get("lineIds");
            List<Integer> types = (List<Integer>) map.get("types");
            Integer date = (Integer) map.get("date");
            Map result = pipeLineService.queryByDate(recordIds, types, date);
            return Rets.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Rets.failure("查询失败");
        }
    }
}
