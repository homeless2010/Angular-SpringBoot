 package com.piedpiper.platform.commons.utils.web;
 
 import javax.xml.namespace.QName;
 import org.apache.cxf.endpoint.Endpoint;
 import org.apache.cxf.service.Service;
 import org.apache.cxf.service.model.BindingInfo;
 import org.apache.cxf.service.model.BindingOperationInfo;
 import org.apache.cxf.service.model.EndpointInfo;
 
 
 
 
 
 
 public class WsUtil
 {
   public static QName getOperationName(Endpoint endpoint, String operation)
   {
     QName opName = new QName(endpoint.getService().getName().getNamespaceURI(), operation);
     BindingInfo bindingInfo = endpoint.getEndpointInfo().getBinding();
     if (bindingInfo.getOperation(opName) == null) {
       for (BindingOperationInfo operationInfo : bindingInfo.getOperations()) {
         if (operation.equals(operationInfo.getName().getLocalPart())) {
           opName = operationInfo.getName();
           break;
         }
       }
     }
     return opName;
   }
 }


