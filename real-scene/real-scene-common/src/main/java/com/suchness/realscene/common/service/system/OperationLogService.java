package com.suchness.realscene.common.service.system;

import com.suchness.realscene.common.dao.system.OperationLogRepository;
import com.suchness.realscene.common.entity.system.OperationLog;
import com.suchness.realscene.common.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class OperationLogService extends BaseService<OperationLog,Long, OperationLogRepository> {


}
