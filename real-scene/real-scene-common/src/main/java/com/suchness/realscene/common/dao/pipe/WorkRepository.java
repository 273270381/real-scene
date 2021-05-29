package com.suchness.realscene.common.dao.pipe;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.pipe.CxLine;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends BaseRepository<CxLine,Integer> {
}
