package com.suchness.realscene.common.service.system;

import com.suchness.realscene.common.cache.DictCache;
import com.suchness.realscene.common.dao.system.DictRepository;
import com.suchness.realscene.common.entity.system.Dict;
import com.suchness.realscene.common.service.BaseService;
import com.suchness.realscene.common.utils.factory.MutiStrFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: rs
 * @date: 2020/7/2 8:46
 * @description:
 * 字典服务
 */
@Service
public class DictService extends BaseService<Dict,Long, DictRepository> {
    private Logger logger = LoggerFactory.getLogger(DictService.class);
    @Resource
    DictRepository dictRepository;

    @Autowired
    private DictCache dictCache;

    /**
     * 添加字典
     * @param dictName  名
     * @param dictValues 值
     */
    public void addDict(String dictName, String dictValues) {
        //判断有没有该字典
        List<Dict> dicts = dictRepository.findByNameAndPid(dictName,0L);
        if(dicts != null && dicts.size() > 0){
            return ;
        }

        //解析dictValues
        List<Map<String, String>> items = MutiStrFactory.parseKeyValue(dictValues);

        //添加字典
        Dict dict = new Dict();
        dict.setName(dictName);
        dict.setNum("0");
        dict.setPid(0L);
        this.dictRepository.save(dict);

        //添加字典条目
        for (Map<String, String> item : items) {
            String num = item.get(MutiStrFactory.MUTI_STR_KEY);
            String name = item.get(MutiStrFactory.MUTI_STR_VALUE);
            Dict itemDict = new Dict();
            itemDict.setPid(dict.getId());
            itemDict.setName(name);
            try {
                itemDict.setNum(num);
            }catch (NumberFormatException e){
                logger.error(e.getMessage(),e);
            }
            this.dictRepository.save(itemDict);
        }
        dictCache.cache();
    }

    /**
     * 编辑字典
     * @param dictId   字典ID
     * @param dictName  字典名称
     * @param dicts    字典值
     */
    public void editDict(Long dictId, String dictName, String dicts) {
        //删除之前的字典
        this.delteDict(dictId);

        //重新添加新的字典
        this.addDict(dictName,dicts);

        this.

        dictCache.cache();
    }

    /**
     * 删除字典
     * @param dictId  字典ID
     */
    public void delteDict(Long dictId) {
        //删除这个字典的子词典
        List<Dict> subList = dictRepository.findByPid(dictId);
        dictRepository.deleteAll(subList);
        //删除这个词典
        dictRepository.deleteById(dictId);

        dictCache.cache();
    }

    /**
     * 获取字典值
     * @param id 主键
     * @return
     */
    @Override
    public Dict get(Long id) {
        Optional<Dict> optional = dictRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    /**
     * 根据名称匹配记录
     * @param name
     * @return
     */
    public List<Dict> findByNameLike(String name) {
        return dictRepository.findByNameLike(name);
    }

    /**
     * 根据父ID查找记录
     * @param pid
     * @return
     */
    public List<Dict> findByPid(Long pid) {
        return dictRepository.findByPid(pid);
    }

    /**
     * @Author wangchan
     * @Description //TODO 根据id查询管线类型
     * @Date 10:32 2020/8/28
     * @Param [id]
     * @return java.lang.String
    **/
     
    public  String queryTypeById(int id){
        return dictRepository.queryTypeById(id);
    }
}
