package com.suchness.realscene.api.controller;

import com.suchness.realscene.common.bean.constant.Constants;
import com.suchness.realscene.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/***
 *  author: wch
 *  create_time : 2020/7/1 13:31
 *******/
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {


    /**
     * download file
     * @param fileName
     */
    @RequestMapping(value="download",method = RequestMethod.GET)
    public void download(@RequestParam("fileName") String fileName){

        HttpServletResponse response = HttpUtil.getResponse();
        response.setContentType("application/x-download");
        try {
            //fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        OutputStream os;

        try{

            File file = new File(Constants.DOWNLOAD_PATH + File.separator + fileName);
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while(i != -1){
                os.write(buffer);
                i = bis.read(buffer);
            }
        }catch (Exception e){
            log.error("download error",e);
        }finally {
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                log.error("close inputstream error", e);
            }
        }


    }



}
