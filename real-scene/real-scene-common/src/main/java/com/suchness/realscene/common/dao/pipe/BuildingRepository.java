package com.suchness.realscene.common.dao.pipe;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.pipe.Building;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author wangchan
 * @Description 建筑物相关sql 语句
 * @Date 10:00 2020/8/28
 **/
public interface BuildingRepository extends BaseRepository<Building, String> {


    /**
     * 根据id查询建筑物sql
     *
     * @return java.util.List
     * @Date 10:01 2020/8/28
     * @Param [id]
     **/
    @Query(value = "SELECT rel_building_images.image_path,building.name,building.type,building.community,building.road,building.phone,building.brief, building.id\n" +
            "FROM building\n" +
            "\n" +
            "inner join rel_building_images \n" +
            "ON building.id=rel_building_images.building_id WHERE building.id=?1", nativeQuery = true)
    List findByStringId(String id);

    /**
     * 根据数组id 查询相应建筑物
     *
     * @return java.util.List
     * @Date 10:02 2020/8/28
     * @Param [ids]
     **/
    @Query(value = "SELECT rel_building_images.image_path,building.name,building.type,building.community,building.road,building.phone,building.brief, building.id\n" +
            "FROM building\n" +
            "\n" +
            "inner join rel_building_images \n" +
            "ON building.id=rel_building_images.building_id WHERE building.id in :ids order by building.name", nativeQuery = true)
    List findByIds(@Param("ids") String[] ids);


    /**
     * 根据name, type, community, road 查询建筑物sql
     *
     * @return java.util.List
     * @Date 10:03 2020/8/28
     * @Param [name, type, community, road]
     **/
    @Modifying
    @Query(value = "SELECT  rel_building_images.image_path,building.name,building.type,building.community,building.road,building.phone,building.brief, building.id" +
            "  FROM building " +
            "inner join rel_building_images \n" +
            "ON building.id=rel_building_images.building_id WHERE  name  like  ?1  and\n" +
            " type = (CASE ?2  WHEN   'null' THEN type   ELSE ?2 END) and community =  (CASE ?3  WHEN 'null'  THEN community   ELSE ?3 END)\n" +
            " and road = (CASE ?4  WHEN  'null' THEN road   ELSE ?4 END)  order by building.name", nativeQuery = true)
    List findByParams(String name, String type, String community, String road);


    /**
     * 查询所有建筑物类型
     *
     * @return java.util.List
     * @Date 10:03 2020/8/28
     * @Param []
     **/
    @Query(value = "SELECT  distinct  TYPE from building ", nativeQuery = true)
    List queryAllTypes();

    /**
     * 查询所有社区
     *
     * @return java.util.List
     * @Date 10:03 2020/8/28
     * @Param []
     **/
    @Query(value = "SELECT  distinct  community from building ", nativeQuery = true)
    List queryAllCommunity();

    /**
     * 查询所有道路
     *
     * @return java.util.List
     * @Date 10:03 2020/8/28
     * @Param []
     **/
    @Query(value = "SELECT  distinct  road from building ", nativeQuery = true)
    List queryAllRoads();

}

