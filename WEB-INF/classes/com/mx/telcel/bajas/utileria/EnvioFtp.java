/*    */ package com.mx.telcel.bajas.utileria;
/*    */ 
/*    */ import com.infomedia.utils.PropertyLoader;
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import java.util.Properties;
/*    */ import org.apache.commons.net.ftp.FTPClient;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnvioFtp
/*    */ {
/* 23 */   private static final Logger log = Logger.getLogger(EnvioFtp.class);
/* 24 */   Properties vpProp = PropertyLoader.load("nominas.properties");
/*    */   
/*    */   public void fncImportarArchivo(List<String> poCompañias) {
/* 27 */     ProcesarArchivo voProcesarArchivo = new ProcesarArchivo();
/* 28 */     BufferedInputStream voInputStream = null;
/* 29 */     String vsUsuarioR = this.vpProp.getProperty("USUARIO_IDM");
/* 30 */     String vsPassR = this.vpProp.getProperty("PASS_IDM");
/* 31 */     String vsRutaRemotaR = "";
/* 32 */     String vsRutaIdm = "";
/*    */     
/* 34 */     FTPClient voFTPClient = new FTPClient();
/* 35 */     System.out.println("vpProp.getProperty(\"USUARIO_IDM\") " + this.vpProp.getProperty("USUARIO_IDM"));
/* 36 */     System.out.println("vpProp.getProperty(\"pass_IDM\") " + this.vpProp.getProperty("PASS_IDM"));
/* 37 */     System.out.println("vpProp.getProperty(\"ip_IDM\") " + this.vpProp.getProperty("IP_IDM"));
/*    */     try {
/* 39 */       voFTPClient.connect(this.vpProp.getProperty("IP_IDM"));
/* 40 */       voFTPClient.login(vsUsuarioR, vsPassR);
/* 41 */       voFTPClient.setFileType(2);
/* 42 */       voFTPClient.enterLocalPassiveMode();
/* 43 */       for (String poCompañia : poCompañias) {
/* 44 */         vsRutaRemotaR = this.vpProp.getProperty("PATH_BAJAS_INTERNOS") + poCompañia + voProcesarArchivo.fncGetDate() + ".csv";
/* 45 */         vsRutaIdm = this.vpProp.get("PATH_IDM") + poCompañia + voProcesarArchivo.fncGetDate() + ".csv";
/* 46 */         voInputStream = new BufferedInputStream(new FileInputStream(vsRutaIdm));
/* 47 */         voFTPClient.storeFile(vsRutaRemotaR, voInputStream);
/*    */       }
/*    */       
/* 50 */       voInputStream.close(); return;
/*    */     }
/*    */     catch (IOException e) {
/* 53 */       log.error("ERROR CATCH");
/*    */     } finally {
/*    */       try {
/* 56 */         voFTPClient.logout();
/* 57 */         voFTPClient.disconnect();
/*    */       } catch (Exception e) {
/* 59 */         log.error("ERROR FINALLY " + e);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\VSCruz\Documents\nominaD\Nominav2.zip!\Nominav2\WEB-INF\classes\com\mx\telcel\baja\\utileria\EnvioFtp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */