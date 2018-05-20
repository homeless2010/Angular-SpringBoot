package com.piedpiper.platform.api.syslookup;

import com.piedpiper.platform.api.syslookup.dto.SysLookupHibearchy;
import com.piedpiper.platform.commons.utils.web.TreeNode;
import java.util.List;

public abstract interface SysLookupHibearchyAPI
{
  public abstract void reLoad()
    throws Exception;
  
  public abstract boolean containsLookupType(String paramString);
  
  public abstract List<TreeNode> getHibearchyTreeData(String paramString);
  
  public abstract List<TreeNode> getHibearchyTreeData(String paramString1, String paramString2);
  
  public abstract String getTypeValue(String paramString1, String paramString2);
  
  public abstract String getTypeValueByAppId(String paramString1, String paramString2, String paramString3);
  
  public abstract List<SysLookupHibearchy> getHibearchyList(String paramString);
  
  public abstract List<SysLookupHibearchy> getHibearchyList(String paramString1, String paramString2);
}


