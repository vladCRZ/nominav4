/*     */ package com.mx.telcel.bajas;
/*     */ 
/*     */ import com.csvreader.CsvReader;
/*     */ import com.jcraft.jsch.ChannelSftp;
/*     */ import com.mx.telcel.nomina.EnvioDatos;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Date;
/*     */ import jxl.Workbook;
/*     */ import jxl.WorkbookSettings;
/*     */ import jxl.write.Label;
/*     */ import jxl.write.WritableSheet;
/*     */ import jxl.write.WritableWorkbook;
/*     */ import jxl.write.WriteException;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BajasNomina
/*     */ {
/*  30 */   private static final Logger log = Logger.getLogger(BajasNomina.class);
/*     */   
/*  32 */   EnvioDatos env = new EnvioDatos();
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
/*     */   public int tamanio(String ruta)
/*     */   {
/*  47 */     int i = 0;
/*     */     try {
/*  49 */       CsvReader cont = new CsvReader(ruta);
/*  50 */       cont.readHeaders();
/*  51 */       while (cont.readRecord()) {
/*  52 */         i++;
/*     */       }
/*  54 */       cont.close();
/*     */     } catch (Exception e) {
/*  56 */       log.error(e);
/*     */     }
/*  58 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String[][] DatosBajas(String ruta, int opc)
/*     */   {
/*  71 */     String[][] Arrexternos = new String[tamanio(ruta)][21];
/*  72 */     int i = 0;
/*     */     try {
/*  74 */       InputStream inputstream = new FileInputStream(ruta);
/*  75 */       CsvReader Usuarios1 = new CsvReader(inputstream, Charset.forName("iso-8859-1"));
/*  76 */       Usuarios1.readHeaders();
/*  77 */       while (Usuarios1.readRecord()) {
/*  78 */         switch (opc) {
/*     */         case 1: 
/*  80 */           if (Usuarios1.get(0).startsWith("-")) {
/*  81 */             Arrexternos[i][0] = Usuarios1.get(0).replace("-", "");
/*  82 */             Arrexternos[i][1] = Usuarios1.get(1);
/*  83 */             Arrexternos[i][2] = Usuarios1.get(2);
/*  84 */             Arrexternos[i][3] = Usuarios1.get(3);
/*  85 */             Arrexternos[i][4] = Usuarios1.get(4);
/*  86 */             Arrexternos[i][5] = Usuarios1.get(5);
/*  87 */             Arrexternos[i][6] = Usuarios1.get(6);
/*  88 */             Arrexternos[i][7] = Usuarios1.get(7);
/*  89 */             Arrexternos[i][8] = Usuarios1.get(8);
/*  90 */             Arrexternos[i][9] = Usuarios1.get(9);
/*  91 */             Arrexternos[i][10] = Usuarios1.get(10);
/*  92 */             Arrexternos[i][11] = Usuarios1.get(11);
/*  93 */             Arrexternos[i][12] = Usuarios1.get(12);
/*  94 */             Arrexternos[i][13] = Usuarios1.get(13);
/*  95 */             Arrexternos[i][14] = Usuarios1.get(14);
/*  96 */             Arrexternos[i][15] = Usuarios1.get(15);
/*  97 */             Arrexternos[i][16] = Usuarios1.get(16);
/*  98 */             Arrexternos[i][17] = Usuarios1.get(17);
/*  99 */             Arrexternos[i][18] = Usuarios1.get(18);
/* 100 */             Arrexternos[i][19] = Usuarios1.get(19);
/* 101 */             Arrexternos[i][20] = Usuarios1.get(20);
/* 102 */             i++;
/*     */           }
/*     */           break;
/*     */         case 2: 
/* 106 */           if (Usuarios1.get(0).startsWith("+")) {
/* 107 */             Arrexternos[i][0] = Usuarios1.get(0).replace("+", "");
/* 108 */             Arrexternos[i][1] = Usuarios1.get(1);
/* 109 */             Arrexternos[i][2] = Usuarios1.get(2);
/* 110 */             Arrexternos[i][3] = Usuarios1.get(3);
/* 111 */             Arrexternos[i][4] = Usuarios1.get(4);
/* 112 */             Arrexternos[i][5] = Usuarios1.get(5);
/* 113 */             Arrexternos[i][6] = Usuarios1.get(6);
/* 114 */             Arrexternos[i][7] = Usuarios1.get(7);
/* 115 */             Arrexternos[i][8] = Usuarios1.get(8);
/* 116 */             Arrexternos[i][9] = Usuarios1.get(9);
/* 117 */             Arrexternos[i][10] = Usuarios1.get(10);
/* 118 */             Arrexternos[i][11] = Usuarios1.get(11);
/* 119 */             Arrexternos[i][12] = Usuarios1.get(12);
/* 120 */             Arrexternos[i][13] = Usuarios1.get(13);
/* 121 */             Arrexternos[i][14] = Usuarios1.get(14);
/* 122 */             Arrexternos[i][15] = Usuarios1.get(15);
/* 123 */             Arrexternos[i][16] = Usuarios1.get(16);
/* 124 */             Arrexternos[i][17] = Usuarios1.get(17);
/* 125 */             Arrexternos[i][18] = Usuarios1.get(18);
/* 126 */             Arrexternos[i][19] = Usuarios1.get(19);
/* 127 */             Arrexternos[i][20] = Usuarios1.get(20);
/* 128 */             i++;
/*     */           }
/*     */           break;
/*     */         case 3: 
/* 132 */           if (Usuarios1.get(0).startsWith("!")) {
/* 133 */             Arrexternos[i][0] = Usuarios1.get(0).replace("!", "");
/* 134 */             Arrexternos[i][1] = Usuarios1.get(1);
/* 135 */             Arrexternos[i][2] = Usuarios1.get(2);
/* 136 */             Arrexternos[i][3] = Usuarios1.get(3);
/* 137 */             Arrexternos[i][4] = Usuarios1.get(4);
/* 138 */             Arrexternos[i][5] = Usuarios1.get(5);
/* 139 */             Arrexternos[i][6] = Usuarios1.get(6);
/* 140 */             Arrexternos[i][7] = Usuarios1.get(7);
/* 141 */             Arrexternos[i][8] = Usuarios1.get(8);
/* 142 */             Arrexternos[i][9] = Usuarios1.get(9);
/* 143 */             Arrexternos[i][10] = Usuarios1.get(10);
/* 144 */             Arrexternos[i][11] = Usuarios1.get(11);
/* 145 */             Arrexternos[i][12] = Usuarios1.get(12);
/* 146 */             Arrexternos[i][13] = Usuarios1.get(13);
/* 147 */             Arrexternos[i][14] = Usuarios1.get(14);
/* 148 */             Arrexternos[i][15] = Usuarios1.get(15);
/* 149 */             Arrexternos[i][16] = Usuarios1.get(16);
/* 150 */             Arrexternos[i][17] = Usuarios1.get(17);
/* 151 */             Arrexternos[i][18] = Usuarios1.get(18);
/* 152 */             Arrexternos[i][19] = Usuarios1.get(19);
/* 153 */             Arrexternos[i][20] = Usuarios1.get(20);
/* 154 */             i++;
/*     */           }
/*     */           break;
/*     */         }
/*     */       }
/* 159 */       Usuarios1.close();
/*     */     } catch (FileNotFoundException e) {
/* 161 */       System.out.println(e);
/* 162 */       log.error(e);
/*     */     } catch (IOException e) {
/* 164 */       System.out.println(e);
/* 165 */       log.error(e);
/*     */     }
/* 167 */     return Arrexternos;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ModAdmExcel(int opc)
/*     */   {
/* 179 */     String[][] Eliminar = (String[][])null;
/* 180 */     if (opc == 1) {
/* 181 */       Eliminar = DatosBajas("/home/remedy/NominaTMP/BajasNomina.csv", 1);
/*     */     }
/* 183 */     if (opc == 2) {
/* 184 */       Eliminar = DatosBajas("/home/remedy/NominaTMP/BajasNomina.csv", 2);
/*     */     }
/* 186 */     if (opc == 3) {
/* 187 */       Eliminar = DatosBajas("/home/remedy/NominaTMP/BajasNomina.csv", 3);
/*     */     }
/*     */     
/*     */ 
/* 191 */     WorkbookSettings configurar = new WorkbookSettings();
/* 192 */     configurar.setEncoding("iso-8859-1");
/* 193 */     File file = null;
/*     */     try
/*     */     {
/* 196 */       switch (opc)
/*     */       {
/*     */       case 1: 
/* 199 */         file = new File("/home/remedy/NominaTMP/lista_bajas.xls");
/* 200 */         break;
/*     */       
/*     */       case 2: 
/* 203 */         file = new File("/home/remedy/NominaTMP/lista_Nuevos.xls");
/* 204 */         break;
/*     */       
/*     */       case 3: 
/* 207 */         file = new File("/home/remedy/NominaTMP/lista_Modificados.xls");
/*     */       }
/*     */       
/*     */       
/*     */ 
/* 212 */       if (!file.exists()) {
/* 213 */         file.createNewFile();
/*     */       }
/*     */       
/*     */ 
/* 217 */       WritableWorkbook wworkbook = Workbook.createWorkbook(file, configurar);
/* 218 */       WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
/*     */       
/* 220 */       wsheet.addCell(new Label(0, 0, "Numero de empleado"));
/* 221 */       wsheet.addCell(new Label(1, 0, "Nombre completo"));
/* 222 */       wsheet.addCell(new Label(2, 0, "Usuario universal"));
/* 223 */       wsheet.addCell(new Label(3, 0, "Gerencia"));
/* 224 */       wsheet.addCell(new Label(4, 0, "IdGerencia"));
/* 225 */       wsheet.addCell(new Label(5, 0, "Departamento"));
/* 226 */       wsheet.addCell(new Label(6, 0, "IdDepartamento"));
/* 227 */       wsheet.addCell(new Label(7, 0, "Puesto"));
/* 228 */       wsheet.addCell(new Label(8, 0, "IdPuesto"));
/* 229 */       wsheet.addCell(new Label(9, 0, "Fecha de movimiento"));
/*     */       
/* 231 */       if (Eliminar.length != 0) {
/* 232 */         for (int i = 0; i < Eliminar.length; i++) {
/* 233 */           for (int j = 0; j < Eliminar[i].length; j++) {
/* 234 */             if (Eliminar[i][j] == null) {
/* 235 */               Eliminar[i][j] = "";
/*     */             }
/*     */           }
/* 238 */           if ((Eliminar[i][1] == null) || (Eliminar[i][1].isEmpty()) || (Eliminar[i][1] == "")) {
/*     */             break;
/*     */           }
/* 241 */           wsheet.addCell(new Label(0, i + 1, Eliminar[i][1]));
/* 242 */           wsheet.addCell(new Label(1, i + 1, Eliminar[i][0]));
/* 243 */           wsheet.addCell(new Label(2, i + 1, Eliminar[i][2]));
/* 244 */           wsheet.addCell(new Label(3, i + 1, Eliminar[i][10]));
/* 245 */           wsheet.addCell(new Label(4, i + 1, " "));
/* 246 */           wsheet.addCell(new Label(5, i + 1, Eliminar[i][11]));
/* 247 */           wsheet.addCell(new Label(6, i + 1, Eliminar[i][12]));
/* 248 */           wsheet.addCell(new Label(7, i + 1, Eliminar[i][13]));
/* 249 */           wsheet.addCell(new Label(8, i + 1, " "));
/* 250 */           wsheet.addCell(new Label(9, i + 1, ""));
/*     */         }
/*     */         
/*     */ 
/* 254 */         wworkbook.write();
/* 255 */         wworkbook.close();
/*     */       }
/*     */     }
/*     */     catch (IOException ex) {
/* 259 */       log.error(ex);
/*     */     }
/*     */     catch (WriteException ex) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void UsuariosMatch()
/*     */   {
/* 274 */     String[][] arrNomina = DatosBajas("/home/remedy/NominaTMP/BajasNomina.csv", 1);
/* 275 */     PeopleNomina objPeop = new PeopleNomina();
/* 276 */     Writer salida = null;
/*     */     
/* 278 */     WorkbookSettings configurar = new WorkbookSettings();
/* 279 */     configurar.setEncoding("iso-8859-1");
/* 280 */     File file = null;
/*     */     try
/*     */     {
/* 283 */       file = new File("/home/remedy/NominaTMP/BajasPendientes.xls");
/* 284 */       if (!file.exists()) {
/* 285 */         file.createNewFile();
/*     */       }
/*     */       
/*     */ 
/* 289 */       WritableWorkbook wworkbook = Workbook.createWorkbook(file, configurar);
/* 290 */       WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
/*     */       
/* 292 */       wsheet.addCell(new Label(0, 0, "Numero de empleado"));
/* 293 */       wsheet.addCell(new Label(1, 0, "Nombre completo"));
/* 294 */       wsheet.addCell(new Label(2, 0, "Usuario universal"));
/* 295 */       wsheet.addCell(new Label(3, 0, "Gerencia"));
/* 296 */       wsheet.addCell(new Label(4, 0, "IdGerencia"));
/* 297 */       wsheet.addCell(new Label(5, 0, "Departamento"));
/* 298 */       wsheet.addCell(new Label(6, 0, "IdDepartamento"));
/* 299 */       wsheet.addCell(new Label(7, 0, "Puesto"));
/* 300 */       wsheet.addCell(new Label(8, 0, "IdPuesto"));
/* 301 */       wsheet.addCell(new Label(9, 0, "Fecha de movimiento"));
/*     */       
/* 303 */       if (arrNomina.length != 0) {
/* 304 */         for (int i = 0; i < arrNomina.length; i++) {
/* 305 */           for (int j = 0; j < arrNomina[i].length; j++) {
/* 306 */             if (arrNomina[i][j] == null) {
/* 307 */               arrNomina[i][j] = "";
/*     */             }
/*     */           }
/* 310 */           if ((arrNomina[i][1] == null) || (arrNomina[i][1].isEmpty()) || (arrNomina[i][1] == "")) {
/*     */             break;
/*     */           }
/*     */           
/* 314 */           if (objPeop.SelectNominas(arrNomina[i][1]) == true) {
/* 315 */             wsheet.addCell(new Label(0, i + 1, arrNomina[i][1]));
/* 316 */             wsheet.addCell(new Label(1, i + 1, arrNomina[i][0]));
/* 317 */             wsheet.addCell(new Label(2, i + 1, arrNomina[i][2]));
/* 318 */             wsheet.addCell(new Label(3, i + 1, arrNomina[i][10]));
/* 319 */             wsheet.addCell(new Label(4, i + 1, " "));
/* 320 */             wsheet.addCell(new Label(5, i + 1, arrNomina[i][11]));
/* 321 */             wsheet.addCell(new Label(6, i + 1, arrNomina[i][12]));
/* 322 */             wsheet.addCell(new Label(7, i + 1, arrNomina[i][13]));
/* 323 */             wsheet.addCell(new Label(8, i + 1, " "));
/* 324 */             wsheet.addCell(new Label(9, i + 1, ""));
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 329 */         wworkbook.write();
/* 330 */         wworkbook.close();
/*     */       }
/*     */     } catch (Exception ex) {
/* 333 */       log.error(ex);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean Processo()
/*     */   {
/* 347 */     EnvioDatos env = new EnvioDatos();
/* 348 */     DescBajas Djba = new DescBajas();
/* 349 */     ChannelSftp sftp = null;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 356 */       Djba.ExlExterno();
/*     */       
/* 358 */       Djba.CVSExternos();
/* 359 */       Djba.CVSInternos("/home/remedy/NominaTMP/LogIntBajas_" + env.link(1));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 364 */       boolean bool1 = true;return bool1;
/* 365 */     } catch (Exception e) { e = e;
/*     */       
/* 367 */       log.error(e);
/* 368 */       boolean bool2 = false;return bool2;
/*     */     }
/*     */     finally {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 378 */     EnvioDatos env = new EnvioDatos();
/* 379 */     BajasNomina Bjnom = new BajasNomina();
/* 380 */     if (Bjnom.Processo() == true) {
/* 381 */       Date vdFecha = new Date();
/* 382 */       if ((vdFecha.getDay() != 0) && (vdFecha.getDay() != 1)) {
/* 383 */         env.ExcCommand("rm /home/remedy/NominaTMP/NominaExternosAct_" + env.link(5));
/*     */       }
/* 385 */       if ((vdFecha.getDay() != 6) && (vdFecha.getDay() != 0) && (vdFecha.getDay() != 1)) {
/* 386 */         env.ExcCommand("rm /home/remedy/NominaTMP/LogIntBajas_" + env.link(1));
/* 387 */         env.ExcCommand("rm /home/remedy/NominaTMP/NominaInternosAct_" + env.link(1));
/*     */       }
/* 389 */       env.ExcCommand("rm /home/remedy/NominaTMP/Externos2ago.csv");
/* 390 */       env.ExcCommand("rm /home/remedy/NominaTMP/Externos3ago.csv");
/* 391 */       env.ExcCommand("rm /home/remedy/NominaTMP/ExternosTMP.csv");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\VSCruz\Documents\nominaD\Nominav2.zip!\Nominav2\WEB-INF\classes\com\mx\telcel\bajas\BajasNomina.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */