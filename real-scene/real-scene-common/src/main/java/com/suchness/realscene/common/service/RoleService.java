package com.suchness.realscene.common.service;

import com.suchness.realscene.common.dao.system.RelationRepository;
import com.suchness.realscene.common.dao.system.RoleRepository;
import com.suchness.realscene.common.entity.system.Relation;
import com.suchness.realscene.common.entity.system.Role;
import com.suchness.realscene.common.utils.Convert;
import com.suchness.realscene.common.vo.node.Node;
import com.suchness.realscene.common.vo.node.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***
 *  author: wch
 *  create_time : 2020/6/16 8:56
 *******/
@Service
public class RoleService  extends BaseService<Role,Long,RoleRepository>{

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RelationRepository relationRepository;

    public List<Role> findByName(String name){
        return roleRepository.findByName(name);
    }

    public void delRoleById(Long roleId){
        //delete role
        roleRepository.deleteById(roleId);

        //delete role permissions
        relationRepository.deleteByRoleId(roleId);
    }


    public void setAuthority(Long roleId,String ids){
        //delete role permissions
        relationRepository.deleteByRoleId(roleId);

        //add new permissions
        for(Long id : Convert.toLongArray(true, Convert.toStrArray(",", ids))){
            Relation relation = new Relation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            relationRepository.save(relation);
        }
    }

    public List<ZTreeNode> roleTreeList(){
        List list = roleRepository.roleTreeList();
        List<ZTreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] arr = (Object[]) list.get(i);
            ZTreeNode node = new ZTreeNode();
            node.setId(Long.valueOf(arr[0].toString()));
            node.setPId(Long.valueOf(arr[1].toString()));
            node.setName(arr[2].toString());
            node.setOpen(Boolean.valueOf(arr[3].toString()));
            treeNodes.add(node);
        }
        return treeNodes;
    }

    public List<ZTreeNode> roleTreeListByRoleId(Long[] ids) {
        List list = roleRepository.roleTreeListByRoleId(ids);
        List<ZTreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] arr = (Object[]) list.get(i);
            ZTreeNode node = new ZTreeNode();
            node.setId(Long.valueOf(arr[0].toString()));
            node.setPId(Long.valueOf(arr[1].toString()));
            node.setName(arr[2].toString());
            node.setOpen(Boolean.valueOf(arr[3].toString()));
            node.setChecked(Boolean.valueOf(arr[4].toString()));
            treeNodes.add(node);
        }
        return treeNodes;
    }

    public List<Node> generateRoleTree(List<ZTreeNode> list){

        List<Node> nodes = new ArrayList<>();
        for (ZTreeNode role : list) {
            Node roleNode = new Node();
            roleNode.setId(role.getId());
            roleNode.setName(role.getName());
            roleNode.setPid(role.getPId());
            roleNode.setChecked(role.getChecked());
            nodes.add(roleNode);
        }
        return nodes;

    }

}
