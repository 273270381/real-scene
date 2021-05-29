package com.suchness.realscene.common.dao.pipe;

import com.suchness.realscene.common.dao.BaseRepository;
import com.suchness.realscene.common.entity.pipe.PathFavCruise;

public interface TrackFavRepository  extends BaseRepository<PathFavCruise, Integer> {
    PathFavCruise findById(int id);



}
