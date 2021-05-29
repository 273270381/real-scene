package com.suchness.realscene.api;

import com.suchness.realscene.common.cache.DictCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/***
 *  author: wch
 *  create_time : 2020/7/2 13:50
 *******/
@Component
@Slf4j
public class CacheListener implements CommandLineRunner {

    @Autowired
    private DictCache dictCache;

    public void loadCache(){
        dictCache.cache();
    }


    @Override
    public void run(String... args) throws Exception {
        log.info(".....................load cache........................");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                loadCache();
            }
        });
        thread.start();
    }
}
