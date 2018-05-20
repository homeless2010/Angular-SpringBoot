 package com.piedpiper.platform.commons.utils.web;
 
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class TreeUtils
 {
   public static TreeNode parseToTree(List<TreeNode> list)
     throws Exception
   {
     TreeNode rootNode = null;
     
     Map<String, TreeNode> treeMap = new HashMap();
     
     for (TreeNode tvo : list) {
       if (!treeMap.containsKey(tvo.getId())) {
         treeMap.put(tvo.getId(), tvo);
       }
     }
     
     for (TreeNode node : treeMap.values()) {
       String parentId = node.get_parentId();
       String text = node.getText();
       
 
       if ("-1".equals(parentId)) {
         rootNode = node;
       }
       else {
         TreeNode parentNode = (TreeNode)treeMap.get(parentId);
         if (null == parentNode) throw new Exception("找不到父节点[" + text + "/" + parentId + "],树不完整，请检查数据！");
         parentNode.getChildren().add(node);
       } }
     return rootNode;
   }
   
 
 
 
 
 
 
 
   public static TreeNode parseToTree(List<TreeNode> list, boolean isOpen)
     throws Exception
   {
     TreeNode rootNode = null;
     
     Map<String, TreeNode> treeMap = new HashMap();
     
     for (TreeNode tvo : list) {
       if (!treeMap.containsKey(tvo.getId())) {
         treeMap.put(tvo.getId(), tvo);
       }
     }
     
     for (TreeNode node : treeMap.values()) {
       String parentId = node.get_parentId();
       
 
 
       if ("-1".equals(parentId)) {
         rootNode = node;
       }
       else {
         TreeNode parentNode = (TreeNode)treeMap.get(parentId);
         if (null != parentNode)
         {
 
 
 
           if (isOpen) {
             parentNode.setState("open");
           }
           parentNode.getChildren().add(node);
         } } }
     return rootNode;
   }
   
 
 
 
 
 
 
   public static TreeNode parseToTree(List<TreeNode> list, boolean isOpen, String parentFlg)
     throws Exception
   {
     TreeNode rootNode = null;
     
     Map<String, TreeNode> treeMap = new HashMap();
     
     for (TreeNode tvo : list) {
       if (!treeMap.containsKey(tvo.getId())) {
         treeMap.put(tvo.getId(), tvo);
       }
     }
     
     for (TreeNode node : treeMap.values()) {
       String parentId = node.get_parentId();
       String text = node.getText();
       
 
       if (parentFlg.equals(parentId)) {
         rootNode = node;
       }
       else {
         TreeNode parentNode = (TreeNode)treeMap.get(parentId);
         if (null == parentNode) { throw new Exception("找不到父节点[" + text + "/" + parentId + "],树不完整，请检查数据！");
         }
         if (isOpen) {
           parentNode.setState("open");
         }
         parentNode.getChildren().add(node);
       } }
     return rootNode;
   }
 }


