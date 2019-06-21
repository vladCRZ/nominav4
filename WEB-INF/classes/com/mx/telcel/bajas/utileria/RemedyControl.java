/*    */ package com.mx.telcel.bajas.utileria;
/*    */ 
/*    */ import com.infomedia.utils.PropertyLoader;
/*    */ import com.telcel.rcontrol.services.remedy.generic.FormEntry;
/*    */ import com.telcel.rcontrol.services.remedy.generic.ListOfFields;
/*    */ import com.telcel.rcontrol.services.remedy.generic.RemedyPort;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Properties;
/*    */ import javax.xml.namespace.QName;
/*    */ import javax.xml.ws.Service;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemedyControl
/*    */ {
/* 24 */   private static final Logger log = Logger.getLogger(RemedyControl.class);
/* 25 */   Properties prop = PropertyLoader.load("nominas.properties");
/* 26 */   private final String vsUsuarioRC = this.prop.getProperty("CLIENTE_ARS_NOMINA");
/* 27 */   private final String vsFormCompany = this.prop.getProperty("FORM_COMPANY");
/*    */   
/*    */   public List<String> fncConsultarCampanias()
/*    */   {
/* 31 */     List<String> voCompañias = new ArrayList();
/*    */     try {
/* 33 */       RemedyPort voRP = getPort(this.prop.getProperty("wsdlURL"));
/* 34 */       for (ListOfFields lofCompañia : voRP.rmdSelect(this.vsUsuarioRC, this.vsFormCompany, "'1000000006' LIKE \"%Operating Company%\" AND '7'=1", new int[] { 1000000001 }, null)
/*    */       
/* 36 */         .getEntry()) {
/* 37 */         voCompañias.add(lofCompañia.getValue(1000000001));
/*    */       }
/*    */     } catch (Exception e) {
/* 40 */       log.error(e);
/*    */     }
/*    */     
/* 43 */     return voCompañias;
/*    */   }
/*    */   
/*    */   private RemedyPort getPort(String psEndPoint) throws MalformedURLException {
/* 47 */     QName serviceQN = new QName(this.prop.getProperty("namespace"), this.prop.getProperty("serviceName"));
/* 48 */     QName portQN = new QName(this.prop.getProperty("namespace"), this.prop.getProperty("portName"));
/* 49 */     Service service = Service.create(new URL(psEndPoint), serviceQN);
/* 50 */     return (RemedyPort)service.getPort(portQN, RemedyPort.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\VSCruz\Documents\nominaD\Nominav2.zip!\Nominav2\WEB-INF\classes\com\mx\telcel\baja\\utileria\RemedyControl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */