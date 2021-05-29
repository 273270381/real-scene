package com.suchness.realscene.common.service;

import com.suchness.realscene.common.bean.dto.pipe.in.BuildingDto;
import com.suchness.realscene.common.dao.pipe.BuildingRepository;
import com.suchness.realscene.common.entity.pipe.Building;
import com.suchness.realscene.common.bean.dto.pipe.out.BuildingConditionsResult;
import com.suchness.realscene.common.bean.dto.pipe.out.BuildingResult;
import com.suchness.realscene.common.bean.dto.pipe.out.BuildingType;
import com.suchness.realscene.common.utils.ResultUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.suchness.realscene.common.bean.constant.Constants;

/**
 * @Author wangchan
 * @Description 建筑物service
 * @Date 9:54 2020/8/28
 **/
@Service
public class BuildingService extends BaseService<Building, String, BuildingRepository> {

    @Autowired
    private BuildingRepository buildingRepository;

    /**
     * 根据id 查询建筑物
     *
     * @return java.util.List<com.suchness.realscene.common.bean.dto.pipe.out.BuildingResult>
     * @Date 9:54 2020/8/28
     * @Param [id]
     **/
    public List<BuildingResult> findById(String id) throws Exception {

        List<BuildingType> buildingTypes = new ArrayList<>();
        //映射sql查询结果
        List<BuildingType> results = ResultUtils.castEntity(buildingRepository.findByStringId(id), BuildingType.class);
        //拼接图片路径； 间隔
        results.parallelStream().collect(Collectors.groupingBy(o -> (o.getId()), Collectors.toList())).forEach(
                (Id, transfer) -> {
                    transfer.stream().reduce((a, b) -> new BuildingType(a.getImagePath() + ";" + b.getImagePath(), a.getName(), a.getType(), a.getCommunity(), a.getRoad(), a.getPhone(), a.getBrief(), a.getId())).ifPresent(buildingTypes::add);
                }
        );
        //图片路径以数组形式返回
        List<BuildingResult> buildingResults = reloadBuildingResult(buildingTypes);
        return buildingResults;
    }

    /**
     * 根据条件数据库查询建筑物
     *
     * @return java.util.List<com.suchness.realscene.common.bean.dto.pipe.out.BuildingResult>
     * @Date 9:55 2020/8/28
     * @Param [name, type, community, road]
     **/
    public List<BuildingResult> queryByParams(BuildingDto dto) throws Exception {
        String name = dto.getName();
        String type = dto.getType();
        String community = dto.getCommunity();
        String road = dto.getRoad();
        //判断查询条件是否为全部或空
        if (TextUtils.isEmpty(dto.getType()) || dto.getType().equals("全部")) {
            type = "null";
        }
        if (TextUtils.isEmpty(community) || community.equals("全部")) {
            community = "null";
        }
        if (TextUtils.isEmpty(road) || road.equals("全部")) {
            road = "null";
        }
        List<BuildingType> buildingTypes = new ArrayList<>();
        //条件模糊查询建筑物
        List<BuildingType> results = ResultUtils.castEntity(buildingRepository.findByParams("%" + name + "%", type, community, road), BuildingType.class);

        //拼接图片路径
        results.parallelStream().collect(Collectors.groupingBy(o -> (o.getId()), Collectors.toList())).forEach(
                (Id, transfer) -> {
                    transfer.stream().reduce((a, b) -> new BuildingType(a.getImagePath() + ";" + b.getImagePath(), a.getName(), a.getType(), a.getCommunity(), a.getRoad(), a.getPhone(), a.getBrief(), a.getId())).ifPresent(buildingTypes::add);
                }
        );
        //构建建筑物的返回类型
        List<BuildingResult> buildingResults = reloadBuildingResult(buildingTypes);
        buildingResults.sort((BuildingResult r1,BuildingResult r2)->r1.getName().compareTo(r2.getName()));
        return buildingResults;

    }

    /**
     * 根据所有范围内建筑物id查询
     *
     * @return java.util.List<com.suchness.realscene.common.bean.dto.pipe.out.BuildingResult>
     * @Date 9:56 2020/8/28
     * @Param [ids]
     **/
    public List<BuildingResult> queryRange(String[] ids) throws Exception {
        List<BuildingType> buildingTypes = new ArrayList<>();
        //映射sql查询结果
        List<BuildingType> results = ResultUtils.castEntity(buildingRepository.findByIds(ids), BuildingType.class);
        //拼接图片路径
        results.parallelStream().collect(Collectors.groupingBy(o -> (o.getId()), Collectors.toList())).forEach(
                (id, transfer) -> {
                    transfer.stream().reduce((a, b) -> new BuildingType(a.getImagePath() + ";" + b.getImagePath(), a.getName(), a.getType(), a.getCommunity(), a.getRoad(), a.getPhone(), a.getBrief(), a.getId())).ifPresent(buildingTypes::add);
                }
        );
        List<BuildingResult> buildingResults = reloadBuildingResult(buildingTypes);
        buildingResults.sort((BuildingResult r1,BuildingResult r2)->r1.getName().compareTo(r2.getName()));

        return buildingResults;

    }


    /**
     * 构建建筑物返回值
     *
     * @return java.util.List<com.suchness.realscene.common.bean.dto.pipe.out.BuildingResult>
     * @Date 9:57 2020/8/28
     * @Param [results]
     **/
    public static List<BuildingResult> reloadBuildingResult(List<BuildingType> results) {
        List<BuildingResult> buildingResults = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            String[] split = results.get(i).getImagePath().split(";");
            for (int j = 0; j < split.length; j++) {
                //拼接图片url
                split[j] = Constants.URL + split[j];
            }
            BuildingResult buildingResult = new BuildingResult(results.get(i).getId(), results.get(i).getName(), results.get(i).getType(), results.get(i).getCommunity(), results.get(i).getRoad(), results.get(i).getPhone(), results.get(i).getBrief(), split);
            buildingResults.add(buildingResult);
        }
        return buildingResults;
    }

    /**
     * 查询所有相关条件
     *
     * @Author wangchan
     * @Date 16:30 2020/8/28
     * @Return BuildingConditionsResult
     **/
    public BuildingConditionsResult queryAllConditions() throws Exception {
        List<String> types = new ArrayList<>();
        types.add("全部");
        List<String> community = new ArrayList<>();
        community.add("全部");
        List<String> roads = new ArrayList<>();
        roads.add("全部");
        //条件list中添加'全部'字段
        types.addAll(1, buildingRepository.queryAllTypes());
        community.addAll(1, buildingRepository.queryAllCommunity());
        roads.addAll(1, buildingRepository.queryAllRoads());
        BuildingConditionsResult buildingConditions = new BuildingConditionsResult(types, community, roads);
        return buildingConditions;
    }
}
