package com.suchness.realscene.common.dao.pipe;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.pipe.PathCruise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**

 * @author wangchan
 */
public interface TrackRepository  extends BaseRepository<PathCruise, Integer> {
    PathCruise findById(int id);

}
