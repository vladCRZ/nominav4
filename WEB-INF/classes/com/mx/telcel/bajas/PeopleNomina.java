/*    */ package com.mx.telcel.bajas;
/*    */ 
/*    */ import com.infomedia.utils.PropertyLoader;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.util.Properties;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PeopleNomina
/*    */ {
/*    */   Properties propNomina;
/*    */   
/*    */   public PeopleNomina()
/*    */   {
/* 25 */     this.propNomina = PropertyLoader.load("nominas.properties");
/*    */   }
/*    */   
/* 28 */   private static final Logger log = Logger.getLogger(PeopleNomina.class);
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean SelectNominas(String NUMEROEMPLEADO)
/*    */   {
/* 34 */     String Cadena = "";
/* 35 */     String UrlParametros = "";
/* 36 */     String actualizacion = "";
/*    */     try {
/* 38 */       UrlParametros = UrlParametros + this.propNomina.getProperty("CLIENTE_ARS");
/* 39 */       UrlParametros = UrlParametros + this.propNomina.getProperty("FORM_PEOPLE");
/* 40 */       UrlParametros = UrlParametros + this.propNomina.getProperty("DAT_COLUMNS");
/* 41 */       UrlParametros = UrlParametros + "1&cCondiciones='1000000054'='" + NUMEROEMPLEADO + "'";
/* 42 */       URL Nomina = new URL(this.propNomina.getProperty("URL_SELECT"));
/*    */       
/* 44 */       URLConnection conn = Nomina.openConnection();
/* 45 */       conn.setDoOutput(true);
/* 46 */       try { OutputStreamWriter write = new OutputStreamWriter(conn.getOutputStream());Throwable localThrowable2 = null;
/* 47 */         try { write.write(UrlParametros);
/* 48 */           write.flush();
/* 49 */           BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
/* 50 */           while ((Cadena = reader.readLine()) != null)
/*    */           {
/* 52 */             if (Cadena.startsWith("<value>"))
/*    */             {
/* 54 */               return true;
/*    */             }
/* 56 */             if (Cadena.startsWith("<RESULTADO/>")) {
/* 57 */               int finalError = Cadena.indexOf("</ERROR>");
/*    */               
/*    */ 
/* 60 */               return false;
/*    */             }
/*    */           }
/*    */         }
/*    */         catch (Throwable localThrowable3)
/*    */         {
/* 46 */           localThrowable2 = localThrowable3;throw localThrowable3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         }
/*    */         finally
/*    */         {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 63 */           if (write != null) if (localThrowable2 != null) try { write.close(); } catch (Throwable x2) { localThrowable2.addSuppressed(x2); } else write.close();
/* 64 */         } } catch (Exception e) { log.error(e);
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 68 */       log.error(e);
/*    */     }
/*    */     
/* 71 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\VSCruz\Documents\nominaD\Nominav2.zip!\Nominav2\WEB-INF\classes\com\mx\telcel\bajas\PeopleNomina.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */