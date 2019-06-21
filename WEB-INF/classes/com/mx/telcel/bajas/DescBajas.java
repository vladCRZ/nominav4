/*     */ package com.mx.telcel.bajas;
/*     */ 
/*     */ import com.csvreader.CsvWriter;
/*     */ import com.jcraft.jsch.ChannelSftp;
/*     */ import com.jcraft.jsch.JSch;
/*     */ import com.jcraft.jsch.JSchException;
/*     */ import com.jcraft.jsch.Session;
/*     */ import com.jcraft.jsch.UserInfo;
/*     */ import com.mx.telcel.nomina.EnvioDatos;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Properties;
/*     */ import jxl.Cell;
/*     */ import jxl.Sheet;
/*     */ import jxl.Workbook;
/*     */ import jxl.WorkbookSettings;
/*     */ import jxl.write.Label;
/*     */ import jxl.write.WritableSheet;
/*     */ import jxl.write.WritableWorkbook;
/*     */ import jxl.write.WriteException;
/*     */ import org.apache.commons.net.ftp.FTPClient;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DescBajas
/*     */ {
/*  36 */   private final String BajasInternosv = "http://intranet.telcel.com:8040/Publico/DswiMasivas/LogIntBajas_";
/*     */   private static final String urlext = "http://intranet.telcel.com:8040/Publico/DswiMasivas/NominaExternosAct_";
/*  38 */   private final String user = "idm";
/*  39 */   private final String host = "10.191.106.190";
/*  40 */   private final Integer port = Integer.valueOf(22);
/*  41 */   private final String pass = "1dm4$ft";
/*     */   private FTPClient ftpCliente;
/*     */   private JSch conexionFTP;
/*     */   private Session sessionConexion;
/*     */   private ChannelSftp sftp;
/*  46 */   private static final Logger log = Logger.getLogger(DescBajas.class);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  52 */   EnvioDatos env = new EnvioDatos();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   Properties prop;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void crearExternosCVS(String Named, String ruta)
/*     */   {
/*  64 */     String[][] AuxNominaEXT = this.env.NominasEXccel(ruta);
/*     */     
/*  66 */     String outputFile = "/home/remedy/NominaTMP/" + Named + ".csv";
/*  67 */     boolean alreadyExists = new File(outputFile).exists();
/*  68 */     if (alreadyExists) {
/*  69 */       File ArchivoEmpleados = new File(outputFile);
/*  70 */       ArchivoEmpleados.delete();
/*     */     }
/*     */     try {
/*  73 */       OutputStream output = new FileOutputStream(outputFile);
/*  74 */       CsvWriter csvOutput = new CsvWriter(output, ',', Charset.forName("iso-8859-1"));
/*  75 */       csvOutput.write("Short Description");
/*  76 */       csvOutput.write("NUMEROEMPLEADO");
/*  77 */       csvOutput.write("USUARIO");
/*  78 */       csvOutput.write("PATERNO");
/*  79 */       csvOutput.write("MATERNO");
/*  80 */       csvOutput.write("NOMBRE");
/*  81 */       csvOutput.write("EMAIL");
/*  82 */       csvOutput.write("REGION");
/*  83 */       csvOutput.write("DIRECCION");
/*  84 */       csvOutput.write("SUBDIRECCION");
/*  85 */       csvOutput.write("GERENCIA");
/*  86 */       csvOutput.write("DEPARTAMENTO");
/*  87 */       csvOutput.write("IDDEPTO");
/*  88 */       csvOutput.write("PUESTO");
/*  89 */       csvOutput.write("NUMEMPJEFE");
/*  90 */       csvOutput.write("JEFEINMEDIATO");
/*  91 */       csvOutput.write("COMPANIA");
/*  92 */       csvOutput.write("TipoEmpleado");
/*  93 */       csvOutput.write("IdRegion");
/*  94 */       csvOutput.write("Convencional");
/*  95 */       csvOutput.write("Extension");
/*  96 */       csvOutput.write("Celular");
/*  97 */       csvOutput.write("Company");
/*  98 */       csvOutput.endRecord();
/*  99 */       for (int i = 0; i < AuxNominaEXT.length; i++) {
/* 100 */         if ((AuxNominaEXT[i][1] == null) || (AuxNominaEXT[i][1].isEmpty())) {
/*     */           break;
/*     */         }
/* 103 */         csvOutput.write(AuxNominaEXT[i][0]);
/* 104 */         csvOutput.write(AuxNominaEXT[i][1]);
/* 105 */         csvOutput.write(AuxNominaEXT[i][2]);
/* 106 */         csvOutput.write(AuxNominaEXT[i][3]);
/* 107 */         csvOutput.write(AuxNominaEXT[i][4]);
/* 108 */         csvOutput.write(AuxNominaEXT[i][5]);
/* 109 */         csvOutput.write(AuxNominaEXT[i][6]);
/* 110 */         csvOutput.write(AuxNominaEXT[i][7]);
/* 111 */         csvOutput.write(AuxNominaEXT[i][8]);
/* 112 */         csvOutput.write(AuxNominaEXT[i][9]);
/* 113 */         csvOutput.write(AuxNominaEXT[i][10]);
/* 114 */         csvOutput.write(AuxNominaEXT[i][11]);
/* 115 */         csvOutput.write(AuxNominaEXT[i][12]);
/* 116 */         csvOutput.write(AuxNominaEXT[i][13]);
/* 117 */         csvOutput.write(AuxNominaEXT[i][14]);
/* 118 */         csvOutput.write(AuxNominaEXT[i][15]);
/* 119 */         csvOutput.write(AuxNominaEXT[i][16]);
/* 120 */         csvOutput.write(AuxNominaEXT[i][17]);
/* 121 */         csvOutput.write(AuxNominaEXT[i][18]);
/* 122 */         csvOutput.write(AuxNominaEXT[i][19]);
/* 123 */         csvOutput.write(AuxNominaEXT[i][20]);
/* 124 */         csvOutput.write(AuxNominaEXT[i][21]);
/* 125 */         csvOutput.write(AuxNominaEXT[i][22]);
/* 126 */         csvOutput.endRecord();
/*     */       }
/* 128 */       csvOutput.close();
/*     */     }
/*     */     catch (IOException e) {
/* 131 */       log.error(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void ExlExterno() {
/* 136 */     BajasNomina bjn = new BajasNomina();
/*     */     
/* 138 */     String[][] BajaExternos = bjn.DatosBajas("/home/remedy/NominaTMP/ExternosBajas.csv", 2);
/* 139 */     WorkbookSettings configurar = new WorkbookSettings();
/* 140 */     configurar.setEncoding("iso-8859-1");
/* 141 */     File file = null;
/* 142 */     WritableWorkbook wworkbook = null;
/*     */     
/*     */     try
/*     */     {
/* 146 */       file = new File("/home/remedy/NominaTMP/BAJAS_EXTERNOS" + this.env.link(3));
/* 147 */       if (!file.exists()) {
/* 148 */         file.createNewFile();
/*     */       }
/*     */       
/* 151 */       wworkbook = Workbook.createWorkbook(file, configurar);
/* 152 */       WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
/*     */       
/* 154 */       wsheet.addCell(new Label(0, 0, "Numero de empleado"));
/* 155 */       wsheet.addCell(new Label(1, 0, "Nombre completo"));
/* 156 */       wsheet.addCell(new Label(2, 0, "Usuario universal"));
/* 157 */       wsheet.addCell(new Label(3, 0, "Gerencia"));
/* 158 */       wsheet.addCell(new Label(4, 0, "IdGerencia"));
/* 159 */       wsheet.addCell(new Label(5, 0, "Departamento"));
/* 160 */       wsheet.addCell(new Label(6, 0, "IdDepartamento"));
/* 161 */       wsheet.addCell(new Label(7, 0, "Puesto"));
/* 162 */       wsheet.addCell(new Label(8, 0, "IdPuesto"));
/* 163 */       wsheet.addCell(new Label(9, 0, "Fecha de movimiento"));
/* 164 */       System.out.println(BajaExternos.length);
/* 165 */       if (BajaExternos.length != 0) {
/* 166 */         for (int i = 0; i < BajaExternos.length; i++) {
/* 167 */           for (int j = 0; j < BajaExternos[i].length; j++) {
/* 168 */             if (BajaExternos[i][j] == null) {
/* 169 */               BajaExternos[i][j] = "";
/*     */             }
/*     */           }
/* 172 */           if ((BajaExternos[i][1] == null) || (BajaExternos[i][1].isEmpty()) || (BajaExternos[i][1] == "")) {
/*     */             break;
/*     */           }
/* 175 */           wsheet.addCell(new Label(0, i + 1, BajaExternos[i][1]));
/* 176 */           wsheet.addCell(new Label(1, i + 1, BajaExternos[i][0]));
/* 177 */           wsheet.addCell(new Label(2, i + 1, BajaExternos[i][2]));
/* 178 */           wsheet.addCell(new Label(3, i + 1, BajaExternos[i][10]));
/* 179 */           wsheet.addCell(new Label(4, i + 1, " "));
/* 180 */           wsheet.addCell(new Label(5, i + 1, BajaExternos[i][11]));
/* 181 */           wsheet.addCell(new Label(6, i + 1, BajaExternos[i][12]));
/* 182 */           wsheet.addCell(new Label(7, i + 1, BajaExternos[i][13]));
/* 183 */           wsheet.addCell(new Label(8, i + 1, " "));
/* 184 */           wsheet.addCell(new Label(9, i + 1, ""));
/*     */         }
/*     */         
/*     */ 
/* 188 */         wworkbook.write();
/* 189 */         wworkbook.close();
/*     */       }
/*     */       else {
/* 192 */         wworkbook.write();
/* 193 */         wworkbook.close();
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 197 */       log.error(e);
/*     */       try {
/* 199 */         wworkbook.write();
/* 200 */         wworkbook.close();
/*     */       } catch (IOException ex) {
/* 202 */         log.error(ex);
/*     */       } catch (WriteException ex) {
/* 204 */         log.error(ex);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public String[][] BajasInternos(String Ruta) {
/* 210 */     int i = 0;
/* 211 */     WorkbookSettings configurar = new WorkbookSettings();
/* 212 */     configurar.setEncoding("iso-8859-1");
/* 213 */     File file = null;
/* 214 */     String[][] argDatos = (String[][])null;
/*     */     try
/*     */     {
/* 217 */       Workbook workbook = Workbook.getWorkbook(new File(Ruta), configurar);
/* 218 */       Sheet sheet = workbook.getSheet(0);
/*     */       
/*     */ 
/* 221 */       argDatos = new String[sheet.getRows()][sheet.getColumns()];
/* 222 */       for (int fila = 3; fila < sheet.getRows(); fila++) {
/* 223 */         for (int columna = 0; columna < sheet.getColumns(); columna++) {
/* 224 */           argDatos[i][columna] = sheet.getCell(columna, fila).getContents();
/*     */         }
/* 226 */         i++;
/*     */       }
/*     */       
/* 229 */       return argDatos;
/*     */     }
/*     */     catch (Exception ex) {
/* 232 */       ex.printStackTrace(); }
/* 233 */     return argDatos;
/*     */   }
/*     */   
/*     */   public void ExlInterno(String Ruta)
/*     */   {
/* 238 */     File file = null;
/* 239 */     String[][] argDatos = BajasInternos(Ruta);
/* 240 */     WorkbookSettings configurar = new WorkbookSettings();
/* 241 */     configurar.setEncoding("iso-8859-1");
/*     */     try
/*     */     {
/* 244 */       file = new File("/home/remedy/NominaTMP/BAJAS_INTERNOS" + this.env.link(3));
/*     */       
/* 246 */       if (!file.exists()) {
/* 247 */         file.createNewFile();
/*     */       }
/*     */       
/* 250 */       WritableWorkbook wworkbook = Workbook.createWorkbook(file, configurar);
/* 251 */       WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
/* 252 */       for (int j = 0; j < argDatos.length; j++) {
/* 253 */         for (int k = 0; k < argDatos[j].length; k++) {
/* 254 */           if (argDatos[j][k] == null) {
/* 255 */             argDatos[j][k] = "";
/*     */           }
/* 257 */           wsheet.addCell(new Label(k, j, argDatos[j][k]));
/*     */         }
/*     */       }
/*     */       
/* 261 */       wworkbook.write();
/* 262 */       wworkbook.close();
/*     */     }
/*     */     catch (Exception e) {
/* 265 */       log.error(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void CVSExternos()
/*     */   {
/* 271 */     BajasNomina bjn = new BajasNomina();
/*     */     
/* 273 */     String[][] BajaExternos = bjn.DatosBajas("/home/remedy/NominaTMP/ExternosBajas.csv", 2);
/* 274 */     String outputFile = "/home/remedy/NominaTMP/BAJAS_EXTERNOS" + this.env.link(3).substring(0, 6) + ".csv";
/* 275 */     boolean alreadyExists = new File(outputFile).exists();
/* 276 */     if (alreadyExists) {
/* 277 */       File ArchivoEmpleados = new File(outputFile);
/* 278 */       ArchivoEmpleados.delete();
/*     */     }
/* 280 */     CsvWriter csvOutput = null;
/*     */     try {
/* 282 */       OutputStream output = new FileOutputStream(outputFile);
/* 283 */       csvOutput = new CsvWriter(output, ',', Charset.forName("iso-8859-1"));
/* 284 */       csvOutput.write("Numero de empleado");
/* 285 */       csvOutput.write("Nombre completo");
/* 286 */       csvOutput.write("Usuario universal");
/* 287 */       csvOutput.write("Gerencia");
/* 288 */       csvOutput.write("IdGerencia");
/* 289 */       csvOutput.write("Departamento");
/* 290 */       csvOutput.write("IdDepartamento");
/* 291 */       csvOutput.write("Puesto");
/* 292 */       csvOutput.write("IdPuesto");
/* 293 */       csvOutput.write("Fecha de movimiento");
/* 294 */       csvOutput.endRecord();
/* 295 */       if (BajaExternos.length != 0) {
/* 296 */         for (int i = 0; i < BajaExternos.length; i++) {
/* 297 */           for (int j = 0; j < BajaExternos[i].length; j++) {
/* 298 */             if (BajaExternos[i][j] == null) {
/* 299 */               BajaExternos[i][j] = "";
/*     */             }
/*     */           }
/* 302 */           if ((BajaExternos[i][1] == null) || (BajaExternos[i][1].isEmpty()) || (BajaExternos[i][1] == "")) {
/*     */             break;
/*     */           }
/* 305 */           log.info("---BAJAS_EXTERNOS" + this.env.link(3).substring(0, 6));
/* 306 */           log.info("Numero de empleado: " + BajaExternos[i][1]);
/* 307 */           log.info("Nombre completo: " + BajaExternos[i][0]);
/* 308 */           log.info("Usuario universal: " + BajaExternos[i][2]);
/* 309 */           log.info("Gerencia: " + BajaExternos[i][10]);
/* 310 */           log.info("IdGerencia: ");
/* 311 */           log.info("Departamento: " + BajaExternos[i][11]);
/* 312 */           log.info("IdDepartamento: " + BajaExternos[i][12]);
/* 313 */           log.info("Puesto: " + BajaExternos[i][13]);
/* 314 */           log.info("IdPuesto: ");
/* 315 */           log.info("Fecha de movimiento: ");
/* 316 */           log.info("------------------------------------");
/* 317 */           csvOutput.write(BajaExternos[i][1]);
/* 318 */           csvOutput.write(BajaExternos[i][0]);
/* 319 */           csvOutput.write(BajaExternos[i][2]);
/* 320 */           csvOutput.write(BajaExternos[i][10]);
/* 321 */           csvOutput.write(" ");
/* 322 */           csvOutput.write(BajaExternos[i][11]);
/* 323 */           csvOutput.write(BajaExternos[i][12]);
/* 324 */           csvOutput.write(BajaExternos[i][13]);
/* 325 */           csvOutput.write(" ");
/* 326 */           csvOutput.write("");
/* 327 */           csvOutput.endRecord();
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */     catch (IOException ex)
/*     */     {
/* 334 */       System.out.println(ex);
/*     */     }
/*     */     finally {
/* 337 */       csvOutput.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void CVSInternos(String Ruta)
/*     */   {
/* 344 */     String[][] argDatos = BajasInternos(Ruta);
/* 345 */     String outputFile = "/home/remedy/NominaTMP/BAJAS_INTERNOS" + this.env.link(3).substring(0, 6) + ".csv";
/* 346 */     int i = 0;int l = 0;
/* 347 */     boolean alreadyExists = new File(outputFile).exists();
/* 348 */     if (alreadyExists) {
/* 349 */       File ArchivoEmpleados = new File(outputFile);
/* 350 */       ArchivoEmpleados.delete();
/*     */     }
/* 352 */     CsvWriter csvOutput = null;
/*     */     try {
/* 354 */       OutputStream output = new FileOutputStream(outputFile);
/* 355 */       csvOutput = new CsvWriter(output, ',', Charset.forName("iso-8859-1"));
/*     */       
/* 357 */       for (int j = 0; j < argDatos.length; j++) {
/* 358 */         if (!argDatos[j][0].isEmpty())
/*     */         {
/* 360 */           for (int k = 0; k < argDatos[j].length; k++) {
/* 361 */             if (!argDatos[j][0].isEmpty()) {
/* 362 */               csvOutput.write(argDatos[j][k]);
/*     */             }
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 371 */           log.info("-----BAJAS_INTERNOS" + this.env.link(3).substring(0, 6));
/* 372 */           log.info("Numero de empleado: " + argDatos[j][0]);
/* 373 */           log.info("Nombre completo: " + argDatos[j][1]);
/* 374 */           log.info("Usuario universal: " + argDatos[j][2]);
/* 375 */           log.info("Gerencia: " + argDatos[j][3]);
/* 376 */           log.info("IdGerencia: " + argDatos[j][4]);
/* 377 */           log.info("Departamento: " + argDatos[j][5]);
/* 378 */           log.info("IdDepartamento: " + argDatos[j][6]);
/* 379 */           log.info("Puesto: " + argDatos[j][7]);
/* 380 */           log.info("IdPuesto: " + argDatos[j][8]);
/* 381 */           log.info("Fecha de movimiento: " + argDatos[j][9]);
/* 382 */           log.info("--------------------------------------");
/*     */         }
/* 384 */         if (!argDatos[j][0].isEmpty()) {
/* 385 */           csvOutput.endRecord();
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 391 */       log.error(ex);
/*     */     }
/*     */     finally {
/* 394 */       csvOutput.close();
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
/*     */   public void Ftp_IdmRemedy()
/*     */   {
/*     */     try
/*     */     {
/* 446 */       String FILETOTRANSFER = "/home/remedy/NominaTMP/BAJAS_EXTERNOS" + this.env.link(3);
/*     */       
/* 448 */       this.conexionFTP = new JSch();
/*     */       
/* 450 */       UserInfo ui = new SUserInfo("1dm4$ft", null);
/*     */       
/* 452 */       this.sessionConexion = this.conexionFTP.getSession("idm", "10.191.106.190", 22);
/*     */       
/* 454 */       this.sessionConexion.setUserInfo(ui);
/* 455 */       this.sessionConexion.setPassword("1dm4$ft");
/*     */       
/* 457 */       this.sessionConexion.connect();
/*     */       
/* 459 */       this.sftp = ((ChannelSftp)this.sessionConexion.openChannel("sftp"));
/* 460 */       this.sftp.connect();
/*     */       
/* 462 */       if (this.sftp.isConnected()) {
/* 463 */         System.out.println("conectado");
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (JSchException ex)
/*     */     {
/*     */ 
/* 473 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\VSCruz\Documents\nominaD\Nominav2.zip!\Nominav2\WEB-INF\classes\com\mx\telcel\bajas\DescBajas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */