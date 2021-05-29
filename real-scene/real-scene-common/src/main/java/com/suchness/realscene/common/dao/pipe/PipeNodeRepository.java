package com.suchness.realscene.common.dao.pipe;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.pipe.PipeNode;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PipeNodeRepository extends BaseRepository<PipeNode, Integer> {

    PipeNode findById(int id);


    /**
     * @Author hejunfeng
     * @Date 14:49 2020/8/28 0028
     * @Param [probePoint]
     * @return com.suchness.realscene.common.entity.pipe.PipeNode
     * @Description 管点查询sql
     **/
    @Query(value = "SELECT * FROM pipe_node AS A WHERE A.probe_point = :probePoint", nativeQuery = true)
    PipeNode queryByProbePoint(@Param("probePoint")String probePoint);



    @Query(value = "select b.type, b.features , count(b.features) as sum  from (select type,features ,probe_point from pipe_node  ) b  where b.probe_point in :nodeRecordIds and  " +
            "  b.type in :types " +
            "group " +
            "by b.type ,b.features ",nativeQuery = true)
    List queryFeature(@Param("nodeRecordIds")String[] nodeRecordIds, @Param("types")int[] types);


    /**
     * @Author hejunfeng
     * @Date 14:50 2020/8/28 0028
     * @Param [probePoint, types]
     * @return java.util.List
     * @Description 管点附属物查询sql
     **/
    @Query(value = "SELECT A.TYPE , A.appendages , COUNT(A.appendages) AS count FROM pipe_node AS A WHERE " +
            "A.probe_point in :probePoint AND A.TYPE IN :types " +
            "GROUP BY A.TYPE , A.appendages ORDER BY A.TYPE" , nativeQuery = true)
    List queryAppendages(@Param("probePoint")String[] probePoint, @Param("types")Integer[] types);


    /**
     * @Author hejunfeng
     * @Date 14:51 2020/8/28 0028
     * @Param [types]
     * @return java.util.List
     * @Description 管点附属物查询sql
     **/
    @Query(value = "SELECT A.TYPE , A.appendages , COUNT(A.appendages) AS count FROM pipe_node AS A WHERE " +
            "A.TYPE IN :types " +
            "GROUP BY A.TYPE , A.appendages ORDER BY A.TYPE" , nativeQuery = true)
    List queryAppendages(@Param("types")Integer[] types);

/**
 * @Author wangchan
 * @Description //TODO 查询所有管点sql
 * @Date 10:33 2020/8/28
 * @Param []
 * @return java.util.List<java.lang.String>
**/
 
    @Query(value = "select probe_point from pipe_node",nativeQuery = true)
    List<String> queryAllIds();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from pipe_node where probe_point = ?1",nativeQuery = true)
    void deleteByProbe_point(String probe_point);
}
