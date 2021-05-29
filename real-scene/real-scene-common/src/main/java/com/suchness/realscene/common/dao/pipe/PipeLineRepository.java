package com.suchness.realscene.common.dao.pipe;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.pipe.Building;
import com.suchness.realscene.common.entity.pipe.PipeLine;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author wangchan
 * @Description //TODO 管线统计类相关sql
 * @Date 10:29 2020/8/28
 * @Param
 * @return
 **/

public interface PipeLineRepository extends BaseRepository<PipeLine, Integer> {

    PipeLine findById(int id);


    /**
     * @return com.suchness.realscene.common.entity.pipe.PipeLine
     * @Author hejunfeng
     * @Date 14:45 2020/8/28 0028
     * @Param [record_id]
     * @Description 管线查询sql
     **/
    @Query(value = "SELECT * FROM pipe_line AS A WHERE A.record_id = :record_id", nativeQuery = true)
    PipeLine queryByRecordId(@Param("record_id") String record_id);


    /**
     * @return java.util.List
     * @Author wangchan
     * @Description //TODO 管线管径分类sql
     * @Date 10:31 2020/8/28
     * @Param [lineids, types]
     **/

    @Modifying
    //@Query(value="SELECT pipeLineDiameter.TYPE,pipeLineDiameter.pipe_diameter,COUNT ( pipeLineDiameter.pipe_diameter ) AS COUNT,SUM ( pipeLineDiameter.distance ) AS distance FROM ( SELECT C.gid, C.pipe_diameter, C.TYPE, ST_Length ( ST_MakeLine ( ST_MakePoint ( C.stx, C.sty ), ST_MakePoint ( C.endx, C.endy ) ) :: geography ) AS distance FROM ( SELECT A .gid AS gid, A.stx AS stx, A.sty AS sty, b.endx AS endx, b.endy AS endy, b.pipe_diameter, b.TYPE FROM ( SELECT pipe_node.x_coordinates AS stx, pipe_node.y_coordinates AS sty, A.gid FROM pipe_node, ( SELECT pipe_line.gid AS gid, pipe_line.start_point AS st_point FROM pipe_line WHERE pipe_line.gid IN (1,2,3)  ) AS A WHERE pipe_node.probe_point = A.st_point) A, ( SELECT pipe_node.x_coordinates AS endx, pipe_node.y_coordinates AS endy, A.gid, A.pipe_diameter, A.TYPE FROM pipe_node, ( SELECT pipe_line.gid AS gid, pipe_line.end_point AS end_point, pipe_diameter, TYPE FROM pipe_line WHERE pipe_line.gid IN (1,2,3)  ) AS A WHERE pipe_node.probe_point = A.end_point) b WHERE A.gid = b.gid) C WHERE C.gid IN (1,2,3) AND C.TYPE IN (1,2,3)) pipeLineDiameter GROUP BY pipeLineDiameter.TYPE, pipeLineDiameter.pipe_diameter")//@Query(value="SELECT pipeLineDiameter.TYPE,pipeLineDiameter.pipe_diameter,COUNT ( pipeLineDiameter.pipe_diameter ) AS COUNT,SUM ( pipeLineDiameter.distance ) AS distance FROM ( SELECT C.gid, C.pipe_diameter, C.TYPE, ST_Length ( ST_MakeLine ( ST_MakePoint ( C.stx, C.sty ), ST_MakePoint ( C.endx, C.endy ) ) :: geography ) AS distance FROM ( SELECT A .gid AS gid, A.stx AS stx, A.sty AS sty, b.endx AS endx, b.endy AS endy, b.pipe_diameter, b.TYPE FROM ( SELECT pipe_node.x_coordinates AS stx, pipe_node.y_coordinates AS sty, A.gid FROM pipe_node, ( SELECT pipe_line.gid AS gid, pipe_line.start_point AS st_point FROM pipe_line WHERE pipe_line.gid IN (1,2,3)  ) AS A WHERE pipe_node.probe_point = A.st_point) A, ( SELECT pipe_node.x_coordinates AS endx, pipe_node.y_coordinates AS endy, A.gid, A.pipe_diameter, A.TYPE FROM pipe_node, ( SELECT pipe_line.gid AS gid, pipe_line.end_point AS end_point, pipe_diameter, TYPE FROM pipe_line WHERE pipe_line.gid IN (1,2,3)  ) AS A WHERE pipe_node.probe_point = A.end_point) b WHERE A.gid = b.gid) C WHERE C.gid IN (1,2,3) AND C.TYPE IN (1,2,3)) pipeLineDiameter GROUP BY pipeLineDiameter.TYPE, pipeLineDiameter.pipe_diameter")
    @Query(value = "SELECT " +
            "pipeLineDiameter.TYPE," +
            "pipeLineDiameter.pipe_diameter," +
            "COUNT ( pipeLineDiameter.pipe_diameter ) AS COUNT," +
            "SUM ( pipeLineDiameter.distance ) AS distance " +
            "FROM" +
            "(" +
            "SELECT " +
            "  C.record_id," +
            "C.pipe_diameter," +
            "C.TYPE," +
            "ST_Distance (  st_point ( C.stx, C.sty ), st_point ( C.endx, C.endy ) ) AS distance " +
            "FROM" +
            "(" +
            "SELECT A" +
            ".record_id AS record_id," +
            "A.stx AS stx," +
            "A.sty AS sty," +
            "b.endx AS endx," +
            "b.endy AS endy," +
            "b.pipe_diameter," +
            "b.TYPE " +
            "FROM" +
            "(" +
            "SELECT " +
            "pipe_node.x_coordinates AS stx," +
            "pipe_node.y_coordinates AS sty," +
            "A.record_id " +
            "FROM " +
            "pipe_node," +
            "( SELECT pipe_line.record_id AS record_id, pipe_line.start_point AS st_point FROM pipe_line WHERE pipe_line.record_id IN :lineids  ) AS A " +
            "WHERE " +
            "pipe_node.probe_point = A.st_point " +
            ") A," +
            "(" +
            "SELECT " +
            "pipe_node.x_coordinates AS endx," +
            "pipe_node.y_coordinates AS endy," +
            "A.record_id," +
            "A.pipe_diameter," +
            "A.TYPE " +
            "FROM " +
            "pipe_node," +
            "( SELECT pipe_line.record_id AS record_id, pipe_line.end_point AS end_point, pipe_diameter, TYPE FROM pipe_line WHERE pipe_line.record_id IN :lineids  ) AS A " +
            "WHERE " +
            "pipe_node.probe_point = A.end_point " +
            ") b " +
            "WHERE " +
            "A.record_id = b.record_id " +
            ") C " +
            "WHERE " +
            "C.record_id IN :lineids " +
            "AND C.TYPE IN :types  " +
            ") pipeLineDiameter " +
            "GROUP BY " +
            "pipeLineDiameter.TYPE," +
            "pipeLineDiameter.pipe_diameter",
            nativeQuery = true)
    List queryDiameterType(@Param("lineids") String[] lineids, @Param("types") int[] types);


    /**
     * @return java.util.List
     * @Author hejunfeng
     * @Date 14:46 2020/8/28 0028
     * @Param [record_ids, types]
     * @Description 管线材质分类sql
     **/
    @Modifying
    @Query(value = "SELECT " +
            "pipeLineMaterial.TYPE, " +
            "pipeLineMaterial.material_id, " +
            "COUNT(*) AS count, " +
            "SUM(pipeLineMaterial.distance) AS distance " +
            "FROM " +
            "(SELECT " +
            "A.record_id, " +
            "A.material_id, " +
            "A.TYPE, " +
            "st_distance(st_point(A.stx, A.sty),st_point(A.endx, A.endy)) AS distance FROM " +
            "(SELECT " +
            "line1.record_id, " +
            "line1.material_id, " +
            "line1.TYPE, " +
            "node1.probe_point AS p1, " +
            "node2.probe_point AS p2, " +
            "node1.x_coordinates AS stx, " +
            "node1.y_coordinates AS sty, " +
            "node2.x_coordinates AS endx, " +
            "node2.y_coordinates AS endy " +
            "FROM pipe_line AS line1 INNER JOIN pipe_node AS node1 on line1.start_point = node1.probe_point INNER JOIN pipe_node AS " +
            "node2 ON line1.end_point = node2.probe_point)A WHERE A.record_id IN :record_ids AND A.TYPE IN :types) pipeLineMaterial " +
            "GROUP BY " +
            "pipeLineMaterial.TYPE, " +
            "pipeLineMaterial.material_id " +
            "ORDER BY " +
            "pipeLineMaterial.TYPE ", nativeQuery = true)
    List queryMaterialType(@Param("record_ids") String[] record_ids, @Param("types") Long[] types);


    /**
     * @return java.util.List
     * @Author hejunfeng
     * @Date 14:46 2020/8/28 0028
     * @Param [types]
     * @Description 管线材质分类sql
     **/
    @Modifying
    @Query(value = "SELECT " +
            "pipeLineMaterial.TYPE, " +
            "pipeLineMaterial.material_id, " +
            "COUNT(*) AS count, " +
            "SUM(pipeLineMaterial.distance) AS distance " +
            "FROM " +
            "(SELECT " +
            "A.record_id, " +
            "A.material_id, " +
            "A.TYPE, " +
            "st_distance(st_point(A.stx, A.sty),st_point(A.endx, A.endy)) AS distance FROM " +
            "(SELECT " +
            "line1.record_id, " +
            "line1.material_id, " +
            "line1.TYPE, " +
            "node1.probe_point AS p1, " +
            "node2.probe_point AS p2, " +
            "node1.x_coordinates AS stx, " +
            "node1.y_coordinates AS sty, " +
            "node2.x_coordinates AS endx, " +
            "node2.y_coordinates AS endy " +
            "FROM pipe_line AS line1 INNER JOIN pipe_node AS node1 on line1.start_point = node1.probe_point INNER JOIN pipe_node AS " +
            "node2 ON line1.end_point = node2.probe_point)A WHERE A.TYPE IN :types) pipeLineMaterial " +
            "GROUP BY " +
            "pipeLineMaterial.TYPE, " +
            "pipeLineMaterial.material_id " +
            "ORDER BY " +
            "pipeLineMaterial.TYPE ", nativeQuery = true)
    List queryMaterialType(@Param("types") Long[] types);


    @Query(value = "select record_id from pipe_line", nativeQuery = true)
    List<String> queryAllIds();


    /**
     * @return java.util.List
     * @Author hejunfeng
     * @Date 14:47 2020/8/28 0028
     * @Param []
     * @Description 查询所有管线管径sql
     **/
    @Query(value = "SELECT distinct A.pipe_diameter  FROM pipe_line as A", nativeQuery = true)
    List queryDiameters();


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from pipe_line where record_id = ?1", nativeQuery = true)
    void deleteByRecord_Id(String record_id);


    /**
     * @return java.util.List<com.suchness.realscene.common.entity.pipe.PipeLine>
     * @Author hejunfeng
     * @Date 14:48 2020/8/28 0028
     * @Param [recordIds, str]
     * @Description 管线分析sql
     **/
    @Modifying()
    @Query(value = "SELECT * FROM pipe_line WHERE pipe_line.record_id IN ?1  order by position(pipe_line.record_id \\:\\: text IN ?2)", nativeQuery = true)
    List<PipeLine> findByRecordIds(String[] recordIds, String str);
}
