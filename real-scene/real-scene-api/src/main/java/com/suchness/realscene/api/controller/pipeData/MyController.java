package com.suchness.realscene.api.controller.pipeData;

import com.alibaba.fastjson.JSONObject;
import com.suchness.realscene.common.entity.pipe.CxLine;
import com.suchness.realscene.common.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    MyService myService;




    @RequestMapping("/workAll")
    @ResponseBody
    public JSONObject workAll(){
        List<CxLine> workList = myService.findWorkAll();
        JSONObject json = new JSONObject();
        json.put("data",workList);
        return json;
    }
}
