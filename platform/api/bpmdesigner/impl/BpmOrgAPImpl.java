 package com.piedpiper.platform.api.bpmdesigner.impl;
 
 import com.piedpiper.platform.api.bpmdesigner.BpmOrgAPI;
 import com.piedpiper.platform.api.bpmdesigner.dto.Group;
 import com.piedpiper.platform.api.bpmdesigner.dto.Position;
 import com.piedpiper.platform.api.bpmdesigner.dto.Role;
 import com.piedpiper.platform.api.sysuser.SysDeptAPI;
 import com.piedpiper.platform.api.sysuser.SysGroupAPI;
 import com.piedpiper.platform.api.sysuser.SysOrgAPI;
 import com.piedpiper.platform.api.sysuser.SysPositionAPI;
 import com.piedpiper.platform.api.sysuser.SysRoleAPI;
 import com.piedpiper.platform.api.sysuser.SysUserAPI;
 import com.piedpiper.platform.api.sysuser.SysUserDeptPositionAPI;
 import com.piedpiper.platform.api.sysuser.dto.SysGroup;
 import com.piedpiper.platform.api.sysuser.dto.SysPosition;
 import com.piedpiper.platform.api.sysuser.dto.SysRole;
 import java.util.ArrayList;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 
 
 
 
 
 
 
 
 
 public class BpmOrgAPImpl
   implements BpmOrgAPI
 {
   @Autowired
   private SysUserAPI sysUserAPI;
   @Autowired
   private SysRoleAPI sysRoleAPI;
   @Autowired
   private SysOrgAPI sysOrgAPI;
   @Autowired
   private SysDeptAPI sysDeptAPI;
   @Autowired
   private SysPositionAPI sysPositionAPI;
   @Autowired
   private SysGroupAPI sysGroupAPI;
   @Autowired
   private SysUserDeptPositionAPI sysUserDeptPositionAPI;
   private String language = "zh_CN";
   
   public List<Role> getRoleListByAppId(String appId) throws Exception
   {
     List<Role> resultList = new ArrayList();
     List<SysRole> sysRoles = this.sysRoleAPI.getAllSysRolesByAppId(appId);
     for (SysRole sysRole : sysRoles) {
       Role role = new Role();
       role.setId(sysRole.getId());
       role.setRoleName(sysRole.getRoleName());
       resultList.add(role);
     }
     return resultList;
   }
   
   public List<Role> getAllRoleList() throws Exception
   {
     List<Role> resultList = new ArrayList();
     List<SysRole> sysRoles = this.sysRoleAPI.getAllSysRoles();
     for (SysRole sysRole : sysRoles) {
       Role role = new Role();
       role.setId(sysRole.getId());
       role.setRoleName(sysRole.getRoleName());
       resultList.add(role);
     }
     return resultList;
   }
   
   public List<Position> getPositionListByOrgId(String orgId) throws Exception
   {
     List<Position> resultList = new ArrayList();
     List<SysPosition> sysPositions = this.sysPositionAPI.getAllSysPositionListByOrgId(orgId);
     for (SysPosition sysPosition : sysPositions) {
       Position position = new Position();
       position.setId(sysPosition.getId());
       position.setPositionName(this.sysPositionAPI.getSysPositionNameBySysPositionId(sysPosition.getId(), this.language));
       resultList.add(position);
     }
     return resultList;
   }
   
   public List<Position> getAllPositionList() throws Exception
   {
     List<Position> resultList = new ArrayList();
     List<SysPosition> sysPositions = this.sysPositionAPI.getAllSysPositionList();
     for (SysPosition sysPosition : sysPositions) {
       Position position = new Position();
       position.setId(sysPosition.getId());
       position.setPositionName(this.sysPositionAPI.getSysPositionNameBySysPositionId(sysPosition.getId(), this.language));
       resultList.add(position);
     }
     return resultList;
   }
   
   public List<Group> getSysTemGroupListByOrgId(String orgId) throws Exception
   {
     List<Group> resultList = new ArrayList();
     List<SysGroup> sysGroups = this.sysGroupAPI.getSubSysGroupListBySysOrgId(orgId);
     for (SysGroup sysGroup : sysGroups) {
       if (sysGroup.getType().equals("1")) {
         Group group = new Group();
         group.setId(sysGroup.getId());
         group.setGroupName(this.sysGroupAPI.getSysGroupNameBySysGroupId(sysGroup.getId(), this.language));
         resultList.add(group);
       }
     }
     return resultList;
   }
   
   public List<Group> getAllSysTemGroupList() throws Exception
   {
     List<Group> resultList = new ArrayList();
     List<SysGroup> sysGroups = this.sysGroupAPI.getAllSysGroupList();
     for (SysGroup sysGroup : sysGroups) {
       if (sysGroup.getType().equals("1")) {
         Group group = new Group();
         group.setId(sysGroup.getId());
         group.setGroupName(this.sysGroupAPI.getSysGroupNameBySysGroupId(sysGroup.getId(), this.language));
         resultList.add(group);
       }
     }
     return resultList;
   }
 }


