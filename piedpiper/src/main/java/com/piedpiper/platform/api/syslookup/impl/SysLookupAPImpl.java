 package com.piedpiper.platform.api.syslookup.impl;
 
 import com.piedpiper.platform.api.session.SessionHelper;
 import com.piedpiper.platform.api.syslookup.SysLookupAPI;
 import com.piedpiper.platform.api.syslookup.dto.SysLookup;
 import com.piedpiper.platform.api.syslookup.dto.SysLookupSimpleVo;
 import com.piedpiper.platform.api.syslookup.dto.SysLookupTl;
 import com.piedpiper.platform.api.syslookup.dto.SysLookupType;
 import com.piedpiper.platform.api.syslookup.dto.SysSecretRelation;
 import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import com.fasterxml.jackson.core.type.TypeReference;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.List;
 import javax.ws.rs.core.GenericType;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.util.StringUtils;
 
 
 
 
 
 
 
 
 public class SysLookupAPImpl
   implements SysLookupAPI
 {
   private static final SysLookupSimpleVo _vo = new SysLookupSimpleVo();
   static { _vo.setLookupCode("");
     _vo.setLookupName("请选择");
   }
   
   private Logger log = LoggerFactory.getLogger(SysLookupAPImpl.class);
   @Autowired
   private BaseCacheManager baseCacheManager;
   
   public void reLoad()
     throws Exception
   {
     String url = RestClientConfig.getRestHost("syslookup") + "/api/platform6/syslookup/SysLookup/reLoad/v1";
     ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {});
     if (!responseMsg.getRetCode().equals("200"))
     {
       throw new Exception(responseMsg.getErrorDesc());
     }
   }
   
   public boolean containsLookupType(String lookupType)
   {
     return this.baseCacheManager.exists("PLATFORM6_LOOKUPTYPE_TYPE_" + lookupType);
   }
   
   public Collection<SysLookupSimpleVo> getLookUpListByType(String lookupType)
   {
     return getLookUpListByTypeByAppId(lookupType, SessionHelper.getApplicationId());
   }
   
   public Collection<SysLookupSimpleVo> getLookUpListByType(String lookupType, String appId, String languageCode) {
     return getLookUpListByTypeByAppIdWithLg(lookupType, appId, languageCode);
   }
   
   public String getNameByLooupTypeCodeAndLooupCode(String lookupTypeCode, String lookupCode)
   {
     return getNameByLooupTypeCodeAndLooupCodeByAppId(lookupTypeCode, lookupCode, SessionHelper.getApplicationId());
   }
   
   public Collection<SysLookupSimpleVo> getLookUpListByTypeByAppId(String lookupType, String appId)
   {
     return getLookUpListByTypeByAppIdWithLg(lookupType, appId, "zh_CN");
   }
   
   public Collection<SysLookupSimpleVo> getLookUpListByTypeByAppIdWithLg(String lookupType, String appId, String languagaCode)
   {
     List<SysLookupSimpleVo> voList = new ArrayList();
     if (StringUtils.isEmpty(languagaCode)) {
       this.log.warn("【" + lookupType + "】所对应的通用代码值在应用" + appId + "的方法getLookUpListByTypeByAppIdWithLg中语言参数" + languagaCode + "传递值为空！");
       return voList;
     }
     List<SysLookupType> sysLookupTypeList = this.baseCacheManager.getAllFromCache("PLATFORM6_LOOKUPTYPE_TYPE_" + lookupType, new TypeReference() {}, appId);
     if (sysLookupTypeList != null) {
       for (SysLookupType sysLookupType : sysLookupTypeList) {
         List<SysLookup> sysLookupList = this.baseCacheManager.getAllFromCache("PLATFORM6_LOOKUP_TYPE_" + sysLookupType.getId(), new TypeReference() {});
         for (SysLookup sysLookup : sysLookupList) {
           List<SysLookupTl> sysLookupTlList = this.baseCacheManager.getAllFromCache("PLATFORM6_LOOKUPTl_TYPE_" + sysLookup.getId(), new TypeReference() {});
           SysLookupSimpleVo vo = null;
           for (SysLookupTl sysLookupTl : sysLookupTlList) {
             if (languagaCode.equals(sysLookupTl.getSysLanguageCode()))
             {
               vo = new SysLookupSimpleVo();
               vo.setLookupCode(sysLookup.getLookupCode());
               vo.setLookupName(sysLookupTl.getLookupName());
               vo.setLookupType(lookupType);
               vo.setSysLookupTlId(sysLookupTl.getId());
               vo.setLookupDes(sysLookupTl.getDescription());
               vo.setDisplayOrder(sysLookup.getDisplayOrder());
               voList.add(vo);
             }
           }
           if (vo == null) {
             vo = new SysLookupSimpleVo();
             vo.setLookupCode(sysLookup.getLookupCode());
             vo.setLookupName(sysLookup.getLookupCode() + "_c");
             vo.setLookupType(lookupType);
             vo.setSysLookupTlId(null);
             vo.setDisplayOrder(sysLookup.getDisplayOrder());
             voList.add(vo);
           }
         }
       }
     }
     if (((voList.size() < 1) || (voList == null)) && 
       (lookupType != null) && (!"".equals(lookupType))) {
       this.log.error("【" + lookupType + "】所对应的通用代码值在应用" + appId + "中为空！");
     }
     return voList;
   }
   
   public String getNameByLooupTypeCodeAndLooupCodeByAppId(String lookupType, String looupCode, String appId)
   {
     String lookupName = "";
     Collection<SysLookupSimpleVo> records = getLookUpListByTypeByAppId(lookupType, appId);
     if ((records != null) && (records.size() > 0)) {
       for (SysLookupSimpleVo sysLookupSimpleVo : records) {
         if (sysLookupSimpleVo.getLookupCode().equals(looupCode)) {
           lookupName = sysLookupSimpleVo.getLookupName();
           break;
         }
       }
     }
     if ((lookupName == null) || ("".equals(lookupName))) {
       this.log.error("【" + lookupType + "】在应用" + appId + "中没有值为" + looupCode + "的通用代码值");
     }
     return lookupName;
   }
   
   public String getNameByLooupTypeCodeAndLooupCodeByAppId(String lookupType, String looupCode, String appId, String languagaCode)
   {
     String lookupName = "";
     Collection<SysLookupSimpleVo> records = getLookUpListByTypeByAppIdWithLg(lookupType, appId, languagaCode);
     if ((records != null) && (records.size() > 0)) {
       for (SysLookupSimpleVo sysLookupSimpleVo : records) {
         if (sysLookupSimpleVo.getLookupCode().equals(looupCode)) {
           lookupName = sysLookupSimpleVo.getLookupName();
           break;
         }
       }
     }
     if ((lookupName == null) || ("".equals(lookupName))) {
       this.log.error("【" + lookupType + "】在应用" + appId + "中没有值为" + looupCode + "的通用代码值");
     }
     return lookupName;
   }
   
   public Collection<SysLookupSimpleVo> enhanceLookupcode(Collection<SysLookupSimpleVo> collection)
   {
     if ((collection instanceof List)) {
       ((List)collection).add(0, _vo);
       return collection;
     }
     Collection<SysLookupSimpleVo> result = new ArrayList(collection.size() + 1);
     result.add(_vo);
     result.addAll(collection);
     return collection;
   }
   
   public boolean isRelation(String wordSecret, String userSecret)
   {
     return this.baseCacheManager.containsKey(SysSecretRelation.SYSSECRETRELATION_WORD_USER, wordSecret + "_" + userSecret);
   }
   
   public SysSecretRelation getSecretRelation(String wordSecret, String userSecret)
   {
     return (SysSecretRelation)this.baseCacheManager.getObjectFromCache(SysSecretRelation.SYSSECRETRELATION_WORD_USER, wordSecret + "_" + userSecret, SysSecretRelation.class);
   }
   
   public List<String> getSecretWordList(String userSecret)
   {
     List<String> result = new ArrayList();
     List<SysSecretRelation> list = this.baseCacheManager.getAllFromCache(SysSecretRelation.SYSSECRETRELATION_WORD_USER, new TypeReference() {});
     for (SysSecretRelation sysSecretRelation : list) {
       if (sysSecretRelation.getUserSecret().equals(userSecret)) {
         result.add(sysSecretRelation.getWordSecret());
       }
     }
     return result;
   }
 }


