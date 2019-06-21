/*    */ package com.mx.telcel.bajas;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ConexionFTP
/*    */ {
/* 15 */   IDMDESA("10.191.106.190", 21, "idm", "1dm4$ft"), 
/* 16 */   IDMPROD("10.191.106.170", 21, "idm", "1dm4$ft");
/*    */   
/*    */   private final String server;
/*    */   private final int port;
/*    */   private final String user;
/*    */   private final String pass;
/*    */   
/* 23 */   private ConexionFTP(String server, int port, String user, String pass) { this.server = server;
/* 24 */     this.port = port;
/* 25 */     this.user = user;
/* 26 */     this.pass = pass;
/*    */   }
/*    */   
/* 29 */   public String getServer() { return this.server; }
/*    */   
/*    */   public int getPort()
/*    */   {
/* 33 */     return this.port;
/*    */   }
/*    */   
/*    */   public String getUser() {
/* 37 */     return this.user;
/*    */   }
/*    */   
/*    */   public String getPass() {
/* 41 */     return this.pass;
/*    */   }
/*    */ }


/* Location:              C:\Users\VSCruz\Documents\nominaD\Nominav2.zip!\Nominav2\WEB-INF\classes\com\mx\telcel\bajas\ConexionFTP.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */