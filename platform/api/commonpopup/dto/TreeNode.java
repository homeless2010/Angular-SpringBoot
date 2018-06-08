 package com.piedpiper.platform.api.commonpopup.dto;
 
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 
 
 
 
 
 public class TreeNode
 {
   private String id;
   private String text;
   private String iconCls;
   private String state;
   private Map<String, String> attributes = new HashMap();
   private List<TreeNode> children = new ArrayList();
   
 
   public TreeNode() {}
   
   public TreeNode(String id, String text)
   {
     this.id = id;
     this.text = text;
   }
   
   public String getId() { return this.id; }
   
   public void setId(String id) {
     this.id = id;
   }
   
   public String getText() { return this.text; }
   
   public void setText(String text) {
     this.text = text;
   }
   
   public List<TreeNode> getChildren() { return this.children; }
   
   public void setChildren(List<TreeNode> children) {
     this.children = children;
   }
   
   public String getIconCls() { return this.iconCls; }
   
   public void setIconCls(String iconCls) {
     this.iconCls = iconCls;
   }
   
   public Map<String, String> getAttributes() { return this.attributes; }
   
   public void setAttributes(Map<String, String> attributes) {
     this.attributes = attributes;
   }
   
   public String getState() { return this.state; }
   
   public void setState(String state) {
     this.state = state;
   }
   
   public void addChildren(TreeNode node)
   {
     if (node != null) {
       this.children.add(node);
     }
   }
   
   public void addAttribute(String key, String value) { this.attributes.put(key, value); }
   
   public boolean hasChildren() {
     if (this.children.size() > 0) {
       return true;
     }
     return false;
   }
 }


