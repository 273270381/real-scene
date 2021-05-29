package com.suchness.realscene.common.service.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Strings;
import com.suchness.realscene.common.bean.vo.node.DeptNode;
import com.suchness.realscene.common.bean.vo.node.DeptOptions;
import com.suchness.realscene.common.bean.vo.node.TreeSelect;
import com.suchness.realscene.common.dao.system.DeptRepository;
import com.suchness.realscene.common.entity.system.Dept;
import com.suchness.realscene.common.service.BaseService;
import com.suchness.realscene.common.utils.StringUtil;
import com.suchness.realscene.common.vo.node.ZTreeNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
@Service
public class DeptService extends BaseService<Dept,Long, DeptRepository> {
    @Autowired
    private DeptRepository deptRepository;

    public List<ZTreeNode> tree() {
        String sql = "SELECT id, pid AS pId, simplename AS NAME, ( CASE WHEN (pId = 0 OR pId IS NULL) THEN 'true' ELSE 'false' END ) AS open FROM t_sys_dept";
        List nodes = deptRepository.queryObjBySql(sql,ZTreeNode.class);
        return nodes;
    }


    public List<Dept> query(String condition) {
        List<Dept> list = null;
        if(Strings.isNullOrEmpty(condition)){
            list =   deptRepository.findAll();
        }else{
            condition = "%"+condition+"%";
            list = deptRepository.findBySimplenameLikeOrFullnameLike(condition,condition);
        }
        return list;
    }

    public void deleteDept(Long deptId) {
        Dept dept = get(deptId);
        List<Dept> subDepts = deptRepository.findByPidsLike("%[" + dept.getId() + "]%");
        deptRepository.deleteAll(subDepts);
        deptRepository.delete(dept);
    }

    public List<DeptNode> queryAllNode() {
        List<Dept> list = queryAll();
        return generateTree(list);
    }

    public void deptSetPids(Dept dept) {
        if ( dept.getPid() ==null || dept.getPid().intValue() == 0) {
            dept.setPid(0L);
            dept.setPids("[0],");
        } else {
            Long pid = dept.getPid();
            Dept temp = get(pid);
            String pids = "";
            if(temp!=null){
                pids = temp.getPids();
            }
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }

    private List<DeptNode> generateTree(List<Dept> list){

        List<DeptNode> nodes = new ArrayList<>(20);
        for(Dept dept:list){
            DeptNode deptNode = new DeptNode();
            BeanUtils.copyProperties(dept,deptNode);
            nodes.add(deptNode);
        }
        for(DeptNode deptNode:nodes){
            for(DeptNode child:nodes){
                if(child.getPid().intValue() == deptNode.getId().intValue()){
                    deptNode.getChildren().add(child);
                }
            }
        }
        List<DeptNode> result = new ArrayList<>(20);
        for(DeptNode node:nodes){
            if(node.getPid().intValue() == 0){
                result.add(node);
            }
        }
        return result;


    }


    /**
     * 获取部门下拉框选项
     * @return
     */
    public List<DeptOptions> queryOptionsList() {
        List<Dept> list = queryAll();
        return generateOptions(list);
    }

    /**
     * 获取部门下拉框选项
     * @return
     */
    private List<DeptOptions> generateOptions(List<Dept> list){
        List<DeptOptions> nodes = new ArrayList<>(20);
        for(Dept dept:list){
            DeptOptions options = new DeptOptions(dept);
            nodes.add(options);
        }

        for(DeptOptions options:nodes){
            for(DeptOptions child:nodes){
                if(child.getPid().intValue() == options.getValue().intValue()){
                    options.getChildren().add(child);
                }
            }
        }

        List<DeptOptions> result = new ArrayList<>(20);
        for(DeptOptions node:nodes){
            if(node.getPid().intValue() == 0){
                result.add(node);
            }
        }
        return result;

    }

    public List<TreeSelect> buildDeptTreeSelect(){
        List<DeptNode> nodes = new ArrayList<>(20);
        List<Dept> depts = queryAll();
        for(Dept dept:depts){
            DeptNode deptNode = new DeptNode();
            BeanUtils.copyProperties(dept,deptNode);
            nodes.add(deptNode);
        }
        List<DeptNode> deptTrees = buildDeptTree(nodes);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }


    public List<DeptNode> buildDeptTree(List<DeptNode> depts)
    {
        List<DeptNode> returnList = new ArrayList<DeptNode>();
        List<Long> tempList = new ArrayList<Long>();
        for (DeptNode dept : depts)
        {
            tempList.add(dept.getId());
        }
        for (Iterator<DeptNode> iterator = depts.iterator(); iterator.hasNext();)
        {
            DeptNode dept = (DeptNode) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getPid()))
            {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = depts;
        }
        return returnList;
    }

    private void recursionFn(List<DeptNode> list, DeptNode t)
    {
        // 得到子节点列表
        List<DeptNode> childList = getChildList(list, t);
        t.setChildren(childList);
        for (DeptNode tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<DeptNode> it = childList.iterator();
                while (it.hasNext())
                {
                    DeptNode n = (DeptNode) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<DeptNode> getChildList(List<DeptNode> list, DeptNode t)
    {
        List<DeptNode> tlist = new ArrayList<DeptNode>();
        Iterator<DeptNode> it = list.iterator();
        while (it.hasNext())
        {
            DeptNode n = (DeptNode) it.next();
            if (StringUtil.isNotNull(n.getPid()) && n.getPid().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<DeptNode> list, DeptNode t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }


    /**
     * 部门名称或全称不能重复
     * @param dept
     * @return
     */
    public Dept getDeptByName(Dept dept){
        return deptRepository.getDeptByName(dept.getSimplename(),dept.getFullname());
    }


}
