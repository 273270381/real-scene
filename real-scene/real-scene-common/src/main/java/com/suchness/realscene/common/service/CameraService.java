package com.suchness.realscene.common.service;

import com.suchness.realscene.common.dao.info.CameraRepository;
import com.suchness.realscene.common.entity.info.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hejunfeng on 2020/9/14 0014
 */
@Service
public class CameraService extends BaseService<Camera, Integer, CameraRepository>{
    @Autowired
    private CameraRepository cameraRepository;


    /**
     * @return java.util.List<com.suchness.realscene.common.entity.info.Camera>
     * @Author hejunfeng
     * @Date 11:15 2020/9/14 0014
     * @Param []
     * @Description 查询所有url
     **/
    public List<Camera> queryCameras(){
        List<Camera> cameraList = cameraRepository.findAll();
        return cameraList;
    }
}
