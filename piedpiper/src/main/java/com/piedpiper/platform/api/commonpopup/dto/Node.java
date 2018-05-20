 package com.piedpiper.platform.api.commonpopup.dto;
 
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 
 
 
 
 
 
 
 public class Node
 {
   private String id;
   private String text;
   private String iconCls;
   private boolean checked;
   private String state;
   private Map<String, String> attributes = new HashMap();
   private List<Node> children = new ArrayList();
   
   public String getId() {
     return this.id;
   }
   
   public void setId(String id) {
     this.id = id;
   }
   
   public String getText() {
     return this.text;
   }
   
   public void setText(String text) {
     this.text = text;
   }
   
   public String getIconCls() {
     return this.iconCls;
   }
   
   public void setIconCls(String iconCls) {
     this.iconCls = iconCls;
   }
   
   public boolean isChecked() {
     return this.checked;
   }
   
   public void setChecked(boolean checked) {
     this.checked = checked;
   }
   
   public String getState() {
     return this.state;
   }
   
   public void setState(String state) {
     this.state = state;
   }
   
   public Map<String, String> getAttributes() {
     return this.attributes;
   }
   
   public void setAttributes(Map<String, String> attributes) {
     this.attributes = attributes;
   }
   
   public List<Node> getChildren() {
     return this.children;
   }
   
   public void setChildren(List<Node> children) {
     this.children = children;
   }
 }


