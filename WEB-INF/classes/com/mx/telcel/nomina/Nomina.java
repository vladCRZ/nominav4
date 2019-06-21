/*     */ package com.mx.telcel.nomina;
/*     */ 
/*     */ import com.infomedia.utils.PropertyLoader;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.Properties;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Nomina
/*     */ {
/*  25 */   private static final Logger log = Logger.getLogger(Nomina.class);
/*  26 */   public static final Properties prop = PropertyLoader.load("nominas.properties");
/*  27 */   public static final String vsConexionBD = prop.getProperty("URL_RC_DB");
/*  28 */   public static final String vsUsuarioBD = prop.getProperty("USUARIO_RC_DB");
/*  29 */   public static final String vsPassBD = prop.getProperty("PASS_RC_DB");
/*     */   
/*     */   Connection cn;
/*     */   
/*     */   ResultSet rs;
/*     */   Statement st;
/*     */   
/*     */   public Connection conectar()
/*     */   {
/*  38 */     Connection cn = null;
/*     */     try {
/*  40 */       cn = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=100.127.3.15)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=ARS81)))\n", "remedy", "Temp.123");
/*     */     }
/*     */     catch (SQLException ex)
/*     */     {
/*  44 */       log.error("Error (" + ex.getErrorCode() + "): " + ex.getMessage());
/*     */     }
/*  46 */     return cn;
/*     */   }
/*     */   
/*     */   public void Insertar(String sql) {
/*  50 */     this.st = null;
/*  51 */     Nomina conexion = new Nomina();
/*  52 */     this.cn = conexion.conectar();
/*     */     
/*     */ 
/*     */     try
/*     */     {
/*  57 */       this.st = this.cn.createStatement();
/*  58 */       this.st.executeUpdate(sql); return;
/*     */     }
/*     */     catch (Exception s)
/*     */     {
/*  62 */       log.debug("NO se Inserto" + sql);
/*  63 */       CerrarConexion();
/*  64 */       s.printStackTrace();
/*  65 */       log.error(s);
/*     */     } finally {
/*  67 */       if (this.st != null) {
/*     */         try {
/*  69 */           this.st.close();
/*     */         } catch (SQLException ex) {
/*  71 */           log.error("Error4 (" + ex.getErrorCode() + "): " + ex.getMessage());
/*     */         }
/*     */       }
/*  74 */       if (this.cn != null)
/*     */         try {
/*  76 */           this.cn.close();
/*     */         } catch (SQLException ex) {
/*  78 */           log.error("Error (" + ex.getErrorCode() + "): " + ex.getMessage());
/*     */         }
/*     */     }
/*     */   }
/*     */   
/*     */   public void modificar(String sql) {
/*  84 */     this.st = null;
/*  85 */     Nomina conexion = new Nomina();
/*  86 */     this.cn = conexion.conectar();
/*     */     try {
/*  88 */       this.st = this.cn.createStatement();
/*  89 */       this.st.executeUpdate(sql); return;
/*     */     } catch (Exception s) {
/*  91 */       CerrarConexion();
/*  92 */       s.printStackTrace();
/*  93 */       log.error(s);
/*     */     } finally {
/*  95 */       if (this.st != null) {
/*     */         try {
/*  97 */           this.st.close();
/*     */         } catch (SQLException ex) {
/*  99 */           log.error("Error4 (" + ex.getErrorCode() + "): " + ex.getMessage());
/*     */         }
/*     */       }
/* 102 */       if (this.cn != null)
/*     */         try {
/* 104 */           this.cn.close();
/*     */         } catch (SQLException ex) {
/* 106 */           log.error("Error (" + ex.getErrorCode() + "): " + ex.getMessage());
/*     */         }
/*     */     }
/*     */   }
/*     */   
/*     */   public void eliminar() {
/* 112 */     this.st = null;
/* 113 */     Nomina conexion = new Nomina();
/* 114 */     this.cn = conexion.conectar();
/*     */     try {
/* 116 */       this.st = this.cn.createStatement();
/* 117 */       this.st.executeUpdate("DELETE FROM NominaTEMP WHERE 1=1"); return;
/*     */     } catch (Exception s) {
/* 119 */       CerrarConexion();
/* 120 */       s.printStackTrace();
/* 121 */       log.error(s);
/*     */     } finally {
/* 123 */       if (this.st != null) {
/*     */         try {
/* 125 */           this.st.close();
/*     */         } catch (SQLException ex) {
/* 127 */           log.error("Error4 (" + ex.getErrorCode() + "): " + ex.getMessage());
/*     */         }
/*     */       }
/* 130 */       if (this.cn != null)
/*     */         try {
/* 132 */           this.cn.close();
/*     */         } catch (SQLException ex) {
/* 134 */           log.error("Error (" + ex.getErrorCode() + "): " + ex.getMessage());
/*     */         }
/*     */     }
/*     */   }
/*     */   
/*     */   public void EliminaNomi() {
/* 140 */     ResultSet rs = null;
/* 141 */     String vsRs = "";
/* 142 */     Statement st = null;
/* 143 */     Connection cn = null;
/*     */     
/*     */     try
/*     */     {
/* 147 */       log.info("vsConexionBD " + vsConexionBD);
/* 148 */       cn = DriverManager.getConnection(vsConexionBD + "", vsUsuarioBD, vsPassBD);
/* 149 */       log.info("vsConexionBD " + vsConexionBD);
/* 150 */       st = cn.createStatement();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 159 */       rs = st.executeQuery("delete from T3477 where 1=1");
/* 160 */       rs = st.executeQuery("delete from B3477 where 1=1");
/* 161 */       rs = st.executeQuery("delete from H3477 where 1=1"); return;
/*     */ 
/*     */     }
/*     */     catch (SQLException s)
/*     */     {
/*     */ 
/* 167 */       log.error(s);
/*     */     } finally {
/* 169 */       if (rs != null) {
/*     */         try {
/* 171 */           rs.close();
/*     */         }
/*     */         catch (SQLException ex) {
/* 174 */           log.error("Error3 (" + ex.getErrorCode() + "): " + ex.getMessage());
/*     */         }
/*     */       }
/* 177 */       if (st != null) {
/*     */         try {
/* 179 */           st.close();
/*     */         }
/*     */         catch (SQLException ex) {
/* 182 */           log.error("Error4 (" + ex.getErrorCode() + "): " + ex.getMessage());
/*     */         }
/*     */       }
/* 185 */       if (cn != null) {
/*     */         try {
/* 187 */           cn.close();
/*     */         }
/*     */         catch (SQLException ex) {
/* 190 */           log.error("Error (" + ex.getErrorCode() + "): " + ex.getMessage());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void CerrarConexion() {
/*     */     try {
/* 198 */       this.st.close();
/* 199 */       this.cn.close();
/*     */     } catch (SQLException ex) {
/* 201 */       log.debug(ex);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\VSCruz\Documents\nominaD\Nominav2.zip!\Nominav2\WEB-INF\classes\com\mx\telcel\nomina\Nomina.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */