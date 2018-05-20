 package com.piedpiper.platform.core.excel.web.servlet.view;
 
 import java.util.Locale;
 import java.util.Map;
 import org.springframework.core.Ordered;
 import org.springframework.web.servlet.View;
 import org.springframework.web.servlet.view.AbstractCachingViewResolver;
 
 
 public class ExcelViewResolve
   extends AbstractCachingViewResolver
   implements Ordered
 {
   private String chartSuffix;
   private Map<String, View> mapView;
   private int order = Integer.MIN_VALUE;
   
 
   protected View loadView(String viewName, Locale locale)
     throws Exception
   {
     if (!viewName.endsWith(getChartSuffix())) {
       return null;
     }
     String name = viewName.substring(0, viewName.indexOf(getChartSuffix()));
     if (!this.mapView.containsKey(name))
       return null;
     return (View)this.mapView.get(name);
   }
   
   public String getChartSuffix() { return this.chartSuffix; }
   
   public void setChartSuffix(String chartSuffix) {
     this.chartSuffix = chartSuffix;
   }
   
   public int getOrder() {
     return this.order;
   }
   
   public Map<String, View> getMapView() {
     return this.mapView;
   }
   
   public void setMapView(Map<String, View> mapView) {
     this.mapView = mapView;
   }
   
   public void setOrder(int order) {
     this.order = order;
   }
 }


