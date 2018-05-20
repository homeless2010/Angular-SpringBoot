 package com.piedpiper.platform.commons.utils.web;
 
 import java.util.ArrayList;
 import java.util.List;
 
 
 
 
 
 
 
 public class TreeNode
 {
   private String id;
   private String text;
   private String iconCls;
   private String state;
   private String _parentId;
   private String checked;
   private Object attributes;
   private List<TreeNode> children;
   
   public String getChecked()
   {
     return this.checked;
   }
   
   public void setChecked(String checked) { this.checked = checked; }
   
   public List<TreeNode> getChildren() {
     if (null == this.children) this.children = new ArrayList();
     return this.children;
   }
   
   public void setChildren(List<TreeNode> children) { this.children = children; }
   
   public String get_parentId() {
     return this._parentId;
   }
   
   public void set_parentId(String _parentId) { this._parentId = _parentId; }
   
   public String getId()
   {
     return this.id;
   }
   
   public void setId(String id) { this.id = id; }
   
   public String getText() {
     return this.text;
   }
   
   public void setText(String text) { this.text = text; }
   
   public String getIconCls() {
     return this.iconCls;
   }
   
   public void setIconCls(String iconCls) { this.iconCls = iconCls; }
   
   public String getState() {
     return this.state;
   }
   
   public void setState(String state) { this.state = state; }
   
   public Object getAttributes() {
     return this.attributes;
   }
   
   public void setAttributes(Object attributes) { this.attributes = attributes; }
 }


