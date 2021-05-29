package com.suchness.realscene.common.service;

import com.suchness.realscene.common.dao.pipe.WorkRepository;
import com.suchness.realscene.common.entity.pipe.CxLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MyService {

    @Autowired
    WorkRepository workRepository;


    public List<CxLine> findWorkAll() {
        return workRepository.findAll();
    }
}
