 package com.piedpiper.platform.api.commonpopup.impl;
 
 import com.piedpiper.platform.api.commonpopup.CommonSelectionAPI;
 import com.piedpiper.platform.api.commonpopup.dto.Node;
 import com.piedpiper.platform.api.sysuser.SysDeptAPI;
 import com.piedpiper.platform.api.sysuser.SysOrgAPI;
 import com.piedpiper.platform.api.sysuser.SysUserDeptPositionAPI;
 import com.piedpiper.platform.api.sysuser.dto.SysDept;
 import com.piedpiper.platform.api.sysuser.dto.SysDeptTl;
 import com.piedpiper.platform.api.sysuser.dto.SysOrg;
 import com.piedpiper.platform.api.sysuser.dto.SysOrgTl;
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 
 
 
 
 public class CommonSelectionAPImpl
   implements CommonSelectionAPI
 {
   @Autowired
   private SysOrgAPI sysOrgAPI;
   @Autowired
   private SysDeptAPI sysDeptAPI;
   @Autowired
   private SysUserDeptPositionAPI sysUserDeptPositionAPI;
   
   public Node getOrgNodeBySysOrgId(String orgId, String languageCode, String selectType, Map<String, Object> parameter)
   {
     List<Node> nodeList = new ArrayList();
     Node orgNode = getOrgNode(languageCode, orgId, parameter);
     List<SysOrg> orgs = this.sysOrgAPI.getSubSysOrgListBySysOrgId(orgId);
     if (orgs != null) {
       for (SysOrg sysOrg : orgs) {
         parameter.put("root", "1");
         if ((sysOrg != null) && (sysOrg.getId() != null))
           nodeList.add(getOrgNodeBySysOrgId(sysOrg.getId(), languageCode, selectType, parameter));
       }
     }
     List<Node> depts = getSysDeptNodesByOrgId(languageCode, orgId, selectType, parameter);
     if ((depts != null) && (depts.size() > 0)) {
       nodeList.addAll(nodeList.size(), depts);
     }
     orgNode.setChildren(nodeList);
     return orgNode;
   }
   
   public Node getOrgNode(String languageCode, String orgId, Map<String, Object> parameter)
   {
     SysOrg org = this.sysOrgAPI.getSysOrgBySysOrgId(orgId);
     Node node = new Node();
     SysOrgTl orgTl = this.sysOrgAPI.getSysOrgTl(org.getId(), languageCode);
     node.setId(org.getId());
     node.setText(orgTl.getOrgName());
     node.setIconCls("icon-home");
     node.setChecked(false);
     if ((null != parameter) && (!parameter.isEmpty())) {
       String root = (String)parameter.get("root");
       String singleOrg = (String)parameter.get("singleOrg");
       if (null != root) {
         node.setIconCls("icon-home");
         if (root.equals("0")) {
           if ((singleOrg == null) || (singleOrg.equals("1"))) {
             node.setState("open");
           }
           else
           {
             boolean deptChilds = this.sysDeptAPI.existsChilds(orgId);
             boolean orgChilds = this.sysOrgAPI.existsChilds(orgId);
             if ((deptChilds) || (orgChilds)) {
               node.setState("closed");
             } else {
               node.setState("open");
             }
           }
         } else if (root.equals("1"))
         {
 
           boolean deptChilds = this.sysDeptAPI.existsChilds(orgId);
           boolean orgChilds = this.sysOrgAPI.existsChilds(orgId);
           if ((deptChilds) || (orgChilds)) {
             node.setState("closed");
           } else {
             node.setState("open");
           }
         }
       }
       else if ((singleOrg == null) || (singleOrg.equals("1"))) {
         node.setState("open");
       } else {
         node.setState("closed");
       }
     }
     
     Map<String, String> attr = new HashMap();
     attr.put("nodeType", "org");
     node.setAttributes(attr);
     return node;
   }
   
   public List<Node> getSysDeptNodesByOrgId(String languageCode, String orgId, String selectType, Map<String, Object> parameter)
   {
     List<Node> nodeList = new ArrayList();
     List<SysDept> depts = this.sysDeptAPI.getSysDeptsByOrgId(orgId);
     if (depts != null) {
       for (SysDept sysDept : depts)
         if (sysDept.getParentDeptId().equals("-1"))
         {
 
           Node node = new Node();
           node.setId(sysDept.getId());
           node.setIconCls("icon-dept");
           node.setChecked(false);
           Map<String, String> attributes = new HashMap();
           attributes.put("nodeType", "dept");
           if (("dept".equals(selectType)) || ("dept_Comprehensive".equals(selectType))) {
             node = setDeptNodeState(node, sysDept.getId(), languageCode);
           } else if (("user".equals(selectType)) || ("user_Comprehensive".equals(selectType))) {
             String subDeptId = this.sysDeptAPI.getAllSubSysDeptIdBySysDeptId(sysDept.getId(), ",");
             List<SysUser> userList = this.sysUserDeptPositionAPI.getSysUserListBySysDeptId(sysDept.getId(), false);
             if (((subDeptId != null) && (subDeptId.length() > 0)) || ((userList != null) && (!userList.isEmpty()))) {
               node.setState("closed");
             } else {
               node.setState("open");
             }
           }
           SysDeptTl tl = this.sysDeptAPI.getSysDeptTlWithoutLanguageCode(sysDept.getId());
           node.setAttributes(attributes);
           if (tl != null) {
             node.setText(tl.getDeptName());
           }
           
           nodeList.add(node);
         }
     }
     return nodeList;
   }
   
   private Node setDeptNodeState(Node node, String deptId, String languageCode) {
     if (deptId == null) {
       deptId = node.getId();
     }
     String subDeptId = this.sysDeptAPI.getAllSubSysDeptIdBySysDeptId(deptId, ",");
     if ((null != subDeptId) && (subDeptId.length() > 0)) {
       node.setState("closed");
     } else {
       node.setState("open");
     }
     subDeptId = null;
     return node;
   }
 }


