/*    */ package com.mx.telcel.bajas;
/*    */ 
/*    */ import com.jcraft.jsch.UserInfo;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SUserInfo
/*    */   implements UserInfo
/*    */ {
/*    */   private String password;
/*    */   private String passPhrase;
/*    */   
/*    */   public SUserInfo(String password, String passPhrase)
/*    */   {
/* 20 */     this.password = password;
/* 21 */     this.passPhrase = passPhrase;
/*    */   }
/*    */   
/*    */   public String getPassphrase() {
/* 25 */     return this.passPhrase;
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 29 */     return this.password;
/*    */   }
/*    */   
/*    */   public boolean promptPassphrase(String arg0) {
/* 33 */     return true;
/*    */   }
/*    */   
/*    */   public boolean promptPassword(String arg0) {
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   public boolean promptYesNo(String arg0) {
/* 41 */     return true;
/*    */   }
/*    */   
/*    */   public void showMessage(String arg0) {
/* 45 */     System.out.println("SUserInfo.showMessage()");
/*    */   }
/*    */ }


/* Location:              C:\Users\VSCruz\Documents\nominaD\Nominav2.zip!\Nominav2\WEB-INF\classes\com\mx\telcel\bajas\SUserInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */