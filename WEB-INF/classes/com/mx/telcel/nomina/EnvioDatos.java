/*     */ package com.mx.telcel.nomina;
/*     */ 
/*     */ import com.csvreader.CsvReader;
/*     */ import com.csvreader.CsvWriter;
/*     */ import com.infomedia.utils.PropertyLoader;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import jxl.Cell;
/*     */ import jxl.Sheet;
/*     */ import jxl.Workbook;
/*     */ import jxl.WorkbookSettings;
/*     */ import jxl.read.biff.BiffException;
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
/*     */ public class EnvioDatos
/*     */ {
/*  37 */   public static final Properties prop = PropertyLoader.load("nominas.properties");
/*  38 */   public static final String vsRutaPam = prop.getProperty("RUTA_PANAMA");
/*  39 */   public static final String vsRutaCol = prop.getProperty("RUTA_COLOMBIA");
/*  40 */   public static final String vsRutaCostaRica = prop.getProperty("RUTA_COSTA_RICA");
/*  41 */   public static final String vsRutaGuatemala = prop.getProperty("RUTA_GUATEMALA");
/*  42 */   public static final String vsRutaHonduras = prop.getProperty("RUTA_HONDURAS");
            public static final String vsRutaAMX = prop.getProperty("RUTA_AMX");
            public static final String vsRutaARG = prop.getProperty("RUTA_ARG");
/*  43 */   public static final String vsRutaNicaragua = prop.getProperty("RUTA_NICARAGUA");
/*  44 */   public static final String vsRutaSalvador = prop.getProperty("RUTA_EL_SALVADOR");
/*  45 */   public static final String vsRutaDominicana = prop.getProperty("RUTA_REP_DOMINICANA");
/*  46 */   public static final String vsRutaPuertoRico = prop.getProperty("RUTA_PUERTO_RICO");
/*     */   
/*  48 */   public static final String vsGrpSoportePan = prop.getProperty("GRP_SOPORTE_PAN");
/*  49 */   public static final String vsGrpSoporteCol = prop.getProperty("GRP_SOPORTE_COL");
/*  50 */   public static final String vsGrpSoporteCos = prop.getProperty("GRP_SOPORTE_COS");
/*  51 */   public static final String vsGrpSoporteGua = prop.getProperty("GRP_SOPORTE_GUA");
/*  52 */   public static final String vsGrpSoporteHon = prop.getProperty("GRP_SOPORTE_HON");
/*  52 */   public static final String vsGrpSoporteAMX = prop.getProperty("GRP_SOPORTE_AMX");
/*  52 */   public static final String vsGrpSoporteARG = prop.getProperty("GRP_SOPORTE_ARG");
/*  53 */   public static final String vsGrpSoporteNic = prop.getProperty("GRP_SOPORTE_NIC");
/*  54 */   public static final String vsGrpSoporteSal = prop.getProperty("GRP_SOPORTE_SAL");
/*  55 */   public static final String vsGrpSoporteDom = prop.getProperty("GRP_SOPORTE_DOM");
/*  56 */   public static final String vsGrpSoportePri = prop.getProperty("GRP_SOPORTE_PRI");
/*     */   
/*     */ 
/*     */ 
/*  60 */   public static final String vsRutaAct = prop.getProperty("RUTA_ARCACT");
/*  61 */   private static final Logger log = Logger.getLogger(EnvioDatos.class);
/*  62 */   private static final String urlint = " " + prop.getProperty("RUTA_INTERNOS");
/*  63 */   private static final String urlext = " " + prop.getProperty("RUTA_EXTERNOS");
/*  64 */   private final String BajasInternos = " " + prop.getProperty("RUTA_BAJAS");
/*  65 */   private final String vsRti = prop.getProperty("RUTA_RC_INTERNOS");
/*  66 */   private final String vsRutaEx = prop.getProperty("RUTA_EXT_TELCEL");
/*  67 */   private final String vsRutaIn = prop.getProperty("RUTA_INT_TELCEL");
/*  68 */   private final String vsRte = prop.getProperty("RUTA_RC_EXTERNOS");
/*  69 */   private final String vsRbi = prop.getProperty("RUTA_RC_BAJAS_IN");
/*  70 */   private final String vsRutaBajasIn = prop.getProperty("RUTA_BAJAS_INTERNOS");
/*  71 */   private final String vsComandoPam = prop.getProperty("COM_PAM");
/*     */   
/*     */ 
/*     */   private static final String xls = ".xls";
/*     */   
/*     */ 
/*     */   String[][] NominaEXT;
/*     */   
/*     */ 
/*     */   String[][] AuxNominaEXT;
/*     */   
/*     */   String[][] NominaINT;
/*     */   
/*     */   String[][] voNominaPanama;
/*     */   
/*     */   String[][] voNominaCostaRica;
/*     */   
/*     */   String[][] voNominaGuatemala;
/*     */   
/*     */   String[][] voNominaHonduras;

/*     */   String[][] voNominaAMX;

/*     */   String[][] voNominaARG;
/*     */   
/*     */   String[][] voNominaNicaragua;
/*     */   
/*     */   String[][] voNominaSalvador;
/*     */   
/*     */   String[][] voNominaRepDominicana;
/*     */   
/*     */   String[][] voNominaPuertiRico;
/*     */   
/*     */ 
/*     */   public String link(int opc)
/*     */   {
/* 103 */     Date fecha = new Date();
/* 104 */     StringBuilder[] urls = new StringBuilder[2];
/* 105 */     Calendar calendar = Calendar.getInstance();
/* 106 */     calendar.setTime(fecha);
/* 107 */     SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
/* 108 */     SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
/*     */     
/* 110 */     switch (opc) {
/*     */     case 1: 
/* 112 */       if (fecha.getDay() == 0) {
/* 113 */         calendar.add(6, -1);
/* 114 */       } else if (fecha.getDay() == 1) {
/* 115 */         calendar.add(6, -2);
/*     */       } else {
/* 117 */         calendar.add(6, 0);
/*     */       }
/* 119 */       break;
/*     */     case 2: 
/* 121 */       if (fecha.getDay() == 0) {
/* 122 */         calendar.add(6, -2);
/* 123 */       } else if ((fecha.getDay() == 1) || (fecha.getDay() == 2)) {
/* 124 */         calendar.add(6, -3);
/*     */       } else {
/* 126 */         calendar.add(6, -1);
/*     */       }
/* 128 */       break;
/*     */     case 3: 
/*     */       break;
/*     */     case 4: 
/* 132 */       if (fecha.getDay() == 0) {
/* 133 */         calendar.add(6, -1);
/* 134 */       } else if (fecha.getDay() == 1) {
/* 135 */         calendar.add(6, -2);
/*     */       } else {
/* 137 */         calendar.add(6, 0);
/*     */       }
/* 139 */       return sdf2.format(calendar.getTime()) + ".xls";
/*     */     case 5: 
/* 141 */       if ((fecha.getDay() == 2) || (fecha.getDay() == 3)) {
/* 142 */         calendar.add(6, -4);
/*     */       } else {
/* 144 */         calendar.add(6, -2);
/*     */       }
/*     */       break;
/*     */     }
/* 148 */     return sdf.format(calendar.getTime()) + ".xls";
/*     */   }
/*     */   
/*     */   public void DownFile()
/*     */   {
/* 153 */     ExcCommand("wget -P  /home/remedy/NominaTMP/" + this.BajasInternos + link(1));
/* 154 */     ExcCommand("wget -P /home/remedy/NominaTMP/" + urlext + link(1));
/* 155 */     ExcCommand("wget -P /home/remedy/NominaTMP/" + urlext + link(2));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Properties getProperties()
/*     */   {
/*     */     try
/*     */     {
/* 168 */       Properties propiedades = new Properties();
/* 169 */       propiedades.load(getClass().getResourceAsStream("nominas.properties"));
/* 170 */       if (!propiedades.isEmpty()) {
/* 171 */         return propiedades;
/*     */       }
/* 173 */       return null;
/*     */     }
/*     */     catch (IOException e) {
/* 176 */       e.printStackTrace(); }
/* 177 */     return null;
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
/*     */   public void ExcCommand(String command)
/*     */   {
/* 191 */     String s = null;
/*     */     try
/*     */     {
/* 194 */       Process p = Runtime.getRuntime().exec(command);
/* 195 */       BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
/*     */       
/* 197 */       p.waitFor();
/*     */       
/* 199 */       while ((s = stdInput.readLine()) != null) {
/* 200 */         log.info(s);
/*     */       }
/* 202 */       log.info("COMADO:");
/* 203 */       log.info(command);
/* 204 */       if (p.exitValue() == 0) {
/* 205 */         log.info("Ejecutado exitosamente!!");
/*     */       } else {
/* 207 */         log.info("No executado!!");
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 212 */       log.error(e);
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
/*     */   public void insertarNom(String urle)
/*     */   {
/* 225 */     ExcCommand("mkdir /home/remedy/NominaTMP");
/* 226 */     ExcCommand("wget -P /home/remedy/NominaTMP/" + urle + link(1));
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
/*     */   public int tamanio(String ruta)
/*     */   {
/* 239 */     int i = 0;
/*     */     try {
/* 241 */       CsvReader cont = new CsvReader(ruta);
/* 242 */       cont.readHeaders();
/* 243 */       while (cont.readRecord()) {
/* 244 */         i++;
/*     */       }
/* 246 */       cont.close();
/*     */     } catch (Exception e) {
/* 248 */       log.error(e);
/*     */     }
/* 250 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String[][] DatosExternos(String ruta)
/*     */   {
/* 262 */     Nomina nomi = new Nomina();
/* 263 */     String[][] Arrexternos = new String[tamanio(ruta)][8];
/* 264 */     int i = 0;
/*     */     try {
/* 266 */       CsvReader Usuarios1 = new CsvReader(ruta);
/* 267 */       Usuarios1.readHeaders();
/*     */       
/* 269 */       while (Usuarios1.readRecord())
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 279 */         Arrexternos[i][0] = Usuarios1.get(0);
/* 280 */         Arrexternos[i][1] = Usuarios1.get(1);
/* 281 */         Arrexternos[i][2] = Usuarios1.get(2);
/* 282 */         Arrexternos[i][3] = Usuarios1.get(3);
/* 283 */         Arrexternos[i][4] = Usuarios1.get(4);
/* 284 */         Arrexternos[i][5] = Usuarios1.get(5);
/* 285 */         Arrexternos[i][6] = Usuarios1.get(6);
/* 286 */         Arrexternos[i][7] = Usuarios1.get(7);
/* 287 */         i++;
/*     */       }
/* 289 */       Usuarios1.close();
/*     */     } catch (FileNotFoundException e) {
/* 291 */       log.error(e);
/*     */     } catch (IOException e) {
/* 293 */       log.error(e);
/*     */     }
/* 295 */     return Arrexternos;
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
/*     */   public void crearcvs()
/*     */   {
/* 308 */     String outputFile = "/home/remedy/NominaTMP/NominaCompleto.csv";
/* 309 */     boolean alreadyExists = new File(outputFile).exists();
/* 310 */     if (alreadyExists) {
/* 311 */       File ArchivoEmpleados = new File(outputFile);
/* 312 */       ArchivoEmpleados.delete();
/*     */     }
/*     */     try {
/* 315 */       OutputStream output = new FileOutputStream(outputFile);
/* 316 */       CsvWriter csvOutput = new CsvWriter(output, ',', Charset.forName("iso-8859-1"));
/* 317 */       csvOutput.write("Short Description");
/* 318 */       csvOutput.write("NUMEROEMPLEADO");
/* 319 */       csvOutput.write("USUARIO");
/* 320 */       csvOutput.write("PATERNO");
/* 321 */       csvOutput.write("MATERNO");
/* 322 */       csvOutput.write("NOMBRE");
/* 323 */       csvOutput.write("EMAIL");
/* 324 */       csvOutput.write("REGION");
/* 325 */       csvOutput.write("DIRECCION");
/* 326 */       csvOutput.write("SUBDIRECCION");
/* 327 */       csvOutput.write("GERENCIA");
/* 328 */       csvOutput.write("DEPARTAMENTO");
/* 329 */       csvOutput.write("IDDEPTO");
/* 330 */       csvOutput.write("PUESTO");
/* 331 */       csvOutput.write("NUMEMPJEFE");
/* 332 */       csvOutput.write("JEFEINMEDIATO");
/* 333 */       csvOutput.write("COMPANIA");
/* 334 */       csvOutput.write("TipoEmpleado");
/* 335 */       csvOutput.write("IdRegion");
/* 336 */       csvOutput.write("Convencional");
/* 337 */       csvOutput.write("Extension");
/* 338 */       csvOutput.write("Celular");
/* 339 */       csvOutput.write("Company");
/* 340 */       csvOutput.write("Assignee Groups");
/* 341 */       csvOutput.endRecord();
/* 342 */       log.info("Comienza Nomina Internos");
/* 343 */       log.info("Tamaño: " + this.NominaINT.length);
/* 344 */       for (int i = 0; i < this.NominaINT.length; i++) {
/* 345 */         if ((this.NominaINT[i][1] == null) || (this.NominaINT[i][1].isEmpty())) {
/* 346 */           log.info("Registros procesados: " + i);
/* 347 */           break;
/*     */         }
/*     */         
/* 350 */         csvOutput.write(this.NominaINT[i][0]);
/* 351 */         csvOutput.write(this.NominaINT[i][1]);
/* 352 */         csvOutput.write(this.NominaINT[i][2]);
/* 353 */         csvOutput.write(this.NominaINT[i][3]);
/* 354 */         csvOutput.write(this.NominaINT[i][4]);
/* 355 */         csvOutput.write(this.NominaINT[i][5]);
/* 356 */         csvOutput.write(this.NominaINT[i][6]);
/* 357 */         csvOutput.write(this.NominaINT[i][7]);
/* 358 */         csvOutput.write(this.NominaINT[i][8]);
/* 359 */         csvOutput.write(this.NominaINT[i][9]);
/* 360 */         csvOutput.write(this.NominaINT[i][10]);
/* 361 */         csvOutput.write(this.NominaINT[i][11]);
/* 362 */         csvOutput.write(this.NominaINT[i][12]);
/* 363 */         csvOutput.write(this.NominaINT[i][13]);
/* 364 */         csvOutput.write(this.NominaINT[i][14]);
/* 365 */         csvOutput.write(this.NominaINT[i][15]);
/* 366 */         csvOutput.write(this.NominaINT[i][16]);
/* 367 */         csvOutput.write(this.NominaINT[i][17]);
/* 368 */         csvOutput.write(this.NominaINT[i][18]);
/* 369 */         csvOutput.write(this.NominaINT[i][19]);
/* 370 */         csvOutput.write(this.NominaINT[i][20]);
/* 371 */         csvOutput.write(this.NominaINT[i][21]);
/* 372 */         csvOutput.write(this.NominaINT[i][22]);
/* 373 */         csvOutput.write(this.NominaINT[i][23]);
/* 374 */         csvOutput.endRecord();
/*     */       }
/* 376 */       log.info("Comienza Nomina Externos");
/* 377 */       log.info("Tamaño: " + this.AuxNominaEXT.length);
/* 378 */       for (int i = 0; i < this.AuxNominaEXT.length; i++)
/*     */       {
/* 380 */         if ((this.AuxNominaEXT[i][1] == null) || (this.AuxNominaEXT[i][1].isEmpty())) {
/* 381 */           log.info("Registros procesados: " + i);
/* 382 */           break;
/*     */         }
/* 384 */         csvOutput.write(this.AuxNominaEXT[i][0]);
/* 385 */         csvOutput.write(this.AuxNominaEXT[i][1]);
/* 386 */         csvOutput.write(this.AuxNominaEXT[i][2]);
/* 387 */         csvOutput.write(this.AuxNominaEXT[i][3]);
/* 388 */         csvOutput.write(this.AuxNominaEXT[i][4]);
/* 389 */         csvOutput.write(this.AuxNominaEXT[i][5]);
/* 390 */         csvOutput.write(this.AuxNominaEXT[i][6]);
/* 391 */         csvOutput.write(this.AuxNominaEXT[i][7]);
/* 392 */         csvOutput.write(this.AuxNominaEXT[i][8]);
/* 393 */         csvOutput.write(this.AuxNominaEXT[i][9]);
/* 394 */         csvOutput.write(this.AuxNominaEXT[i][10]);
/* 395 */         csvOutput.write(this.AuxNominaEXT[i][11]);
/* 396 */         csvOutput.write(this.AuxNominaEXT[i][12]);
/* 397 */         csvOutput.write(this.AuxNominaEXT[i][13]);
/* 398 */         csvOutput.write(this.AuxNominaEXT[i][14]);
/* 399 */         csvOutput.write(this.AuxNominaEXT[i][15]);
/* 400 */         csvOutput.write(this.AuxNominaEXT[i][16]);
/* 401 */         csvOutput.write(this.AuxNominaEXT[i][17]);
/* 402 */         csvOutput.write(this.AuxNominaEXT[i][18]);
/* 403 */         csvOutput.write(this.AuxNominaEXT[i][19]);
/* 404 */         csvOutput.write(this.AuxNominaEXT[i][20]);
/* 405 */         csvOutput.write(this.AuxNominaEXT[i][21]);
/* 406 */         csvOutput.write(this.AuxNominaEXT[i][22]);
/* 407 */         csvOutput.write(this.AuxNominaEXT[i][23]);
/* 408 */         csvOutput.endRecord();
/*     */       }
/* 410 */       log.info("Comienza Nomina Panama");
/* 411 */       log.info("Tamaño:" + this.voNominaPanama.length);
/* 412 */       for (int i = 0; i < this.voNominaPanama.length; i++) {
/* 413 */         if ((this.voNominaPanama[i][1] == null) || (this.voNominaPanama[i][1].isEmpty())) {
/* 414 */           log.info("Registros procesados: " + i);
/* 415 */           break;
/*     */         }
/* 417 */         csvOutput.write(this.voNominaPanama[i][0]);
/* 418 */         csvOutput.write(this.voNominaPanama[i][1]);
/* 419 */         csvOutput.write(this.voNominaPanama[i][2]);
/* 420 */         csvOutput.write(this.voNominaPanama[i][3]);
/* 421 */         csvOutput.write(this.voNominaPanama[i][4]);
/* 422 */         csvOutput.write(this.voNominaPanama[i][5]);
/* 423 */         csvOutput.write(this.voNominaPanama[i][6]);
/* 424 */         csvOutput.write(this.voNominaPanama[i][7]);
/* 425 */         csvOutput.write(this.voNominaPanama[i][8]);
/* 426 */         csvOutput.write(this.voNominaPanama[i][9]);
/* 427 */         csvOutput.write(this.voNominaPanama[i][10]);
/* 428 */         csvOutput.write(this.voNominaPanama[i][11]);
/* 429 */         csvOutput.write(this.voNominaPanama[i][12]);
/* 430 */         csvOutput.write(this.voNominaPanama[i][13]);
/* 431 */         csvOutput.write(this.voNominaPanama[i][14]);
/* 432 */         csvOutput.write(this.voNominaPanama[i][15]);
/* 433 */         csvOutput.write(this.voNominaPanama[i][16]);
/* 434 */         csvOutput.write(this.voNominaPanama[i][17]);
/* 435 */         csvOutput.write(this.voNominaPanama[i][18]);
/* 436 */         csvOutput.write(this.voNominaPanama[i][19]);
/* 437 */         csvOutput.write(this.voNominaPanama[i][20]);
/* 438 */         csvOutput.write(this.voNominaPanama[i][21]);
/* 439 */         csvOutput.write(this.voNominaPanama[i][22]);
/* 440 */         csvOutput.write(this.voNominaPanama[i][23]);
/* 441 */         csvOutput.endRecord();
/*     */       }
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
/* 478 */       fncCsvWriter(csvOutput, this.voNominaCostaRica);
/* 479 */       fncCsvWriter(csvOutput, this.voNominaHonduras);
/* 479 */       fncCsvWriter(csvOutput, this.voNominaAMX);
/* 479 */       fncCsvWriter(csvOutput, this.voNominaARG);
/* 480 */       fncCsvWriter(csvOutput, this.voNominaGuatemala);
/* 481 */       fncCsvWriter(csvOutput, this.voNominaNicaragua);
/* 482 */       fncCsvWriter(csvOutput, this.voNominaSalvador);
/* 483 */       fncCsvWriter(csvOutput, this.voNominaRepDominicana);
/* 484 */       fncCsvWriter(csvOutput, this.voNominaPuertiRico);
/*     */       
/* 486 */       csvOutput.close();
/*     */     }
/*     */     catch (IOException e) {
/* 489 */       log.error(e);
/* 490 */       log.info(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void fncCsvWriter(CsvWriter poWriter, String[][] poArchivo) {
/*     */     try {
/* 496 */       log.info("Entro al proceso fncCsvWriter");
/* 497 */       log.info("Tamaño del arreglo" + poArchivo.length);
/* 498 */       for (int i = 0; i < poArchivo.length; i++) {
/* 499 */         poWriter.write(poArchivo[i][0]);
/* 500 */         poWriter.write(poArchivo[i][1]);
/* 501 */         poWriter.write(poArchivo[i][2]);
/* 502 */         poWriter.write(poArchivo[i][3]);
/* 503 */         poWriter.write(poArchivo[i][4]);
/* 504 */         poWriter.write(poArchivo[i][5]);
/* 505 */         poWriter.write(poArchivo[i][6]);
/* 506 */         poWriter.write(poArchivo[i][7]);
/* 507 */         poWriter.write(poArchivo[i][8]);
/* 508 */         poWriter.write(poArchivo[i][9]);
/* 509 */         poWriter.write(poArchivo[i][10]);
/* 510 */         poWriter.write(poArchivo[i][11]);
/* 511 */         poWriter.write(poArchivo[i][12]);
/* 512 */         poWriter.write(poArchivo[i][13]);
/* 513 */         poWriter.write(poArchivo[i][14]);
/* 514 */         poWriter.write(poArchivo[i][15]);
/* 515 */         poWriter.write(poArchivo[i][16]);
/* 516 */         poWriter.write(poArchivo[i][17]);
/* 517 */         poWriter.write(poArchivo[i][18]);
/* 518 */         poWriter.write(poArchivo[i][19]);
/* 519 */         poWriter.write(poArchivo[i][20]);
/* 520 */         poWriter.write(poArchivo[i][21]);
/* 521 */         poWriter.write(poArchivo[i][22]);
/* 522 */         poWriter.write(poArchivo[i][23]);
/* 523 */         poWriter.endRecord();
/*     */       }
/*     */     } catch (Exception e) {
/* 526 */       log.info(e);
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
/*     */   public int BuscarCampo(String dato)
/*     */   {
/* 540 */     int k = 0;
/* 541 */     for (int i = 1; i < this.NominaEXT.length; i++) {
/* 542 */       if (this.NominaEXT[i][1] == null) {
/*     */         break;
/*     */       }
/* 545 */       if (this.NominaEXT[i][1].equals(dato)) {
/* 546 */         k = i;
/* 547 */         break;
/*     */       }
/*     */     }
/* 550 */     return k;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void crearExternosCVS(String Named, String ruta)
/*     */   {
/* 562 */     String[][] AuxNominaEXT = (String[][])null;
/*     */     
/* 564 */     AuxNominaEXT = NominasEXccel(ruta);
/*     */     
/* 566 */     String outputFile = "/home/remedy/NominaTMP/" + Named + ".csv";
/* 567 */     boolean alreadyExists = new File(outputFile).exists();
/* 568 */     if (alreadyExists) {
/* 569 */       File ArchivoEmpleados = new File(outputFile);
/* 570 */       ArchivoEmpleados.delete();
/*     */     }
/*     */     try {
/* 573 */       OutputStream output = new FileOutputStream(outputFile);
/* 574 */       CsvWriter csvOutput = new CsvWriter(output, ',', Charset.forName("iso-8859-1"));
/* 575 */       csvOutput.write("Short Description");
/* 576 */       csvOutput.write("NUMEROEMPLEADO");
/* 577 */       csvOutput.write("USUARIO");
/* 578 */       csvOutput.write("PATERNO");
/* 579 */       csvOutput.write("MATERNO");
/* 580 */       csvOutput.write("NOMBRE");
/* 581 */       csvOutput.write("EMAIL");
/* 582 */       csvOutput.write("REGION");
/* 583 */       csvOutput.write("DIRECCION");
/* 584 */       csvOutput.write("SUBDIRECCION");
/* 585 */       csvOutput.write("GERENCIA");
/* 586 */       csvOutput.write("DEPARTAMENTO");
/* 587 */       csvOutput.write("IDDEPTO");
/* 588 */       csvOutput.write("PUESTO");
/* 589 */       csvOutput.write("NUMEMPJEFE");
/* 590 */       csvOutput.write("JEFEINMEDIATO");
/* 591 */       csvOutput.write("COMPANIA");
/* 592 */       csvOutput.write("TipoEmpleado");
/* 593 */       csvOutput.write("IdRegion");
/* 594 */       csvOutput.write("Convencional");
/* 595 */       csvOutput.write("Extension");
/* 596 */       csvOutput.write("Celular");
/* 597 */       csvOutput.write("Company");
/* 598 */       csvOutput.endRecord();
/* 599 */       if (AuxNominaEXT.length > 0) {
/* 600 */         for (int i = 0; i < AuxNominaEXT.length; i++) {
/* 601 */           if ((AuxNominaEXT[i][1] == null) || (AuxNominaEXT[i][1].isEmpty())) {
/*     */             break;
/*     */           }
/* 604 */           csvOutput.write(AuxNominaEXT[i][0]);
/* 605 */           csvOutput.write(AuxNominaEXT[i][1]);
/* 606 */           csvOutput.write(AuxNominaEXT[i][2]);
/* 607 */           csvOutput.write(AuxNominaEXT[i][3]);
/* 608 */           csvOutput.write(AuxNominaEXT[i][4]);
/* 609 */           csvOutput.write(AuxNominaEXT[i][5]);
/* 610 */           csvOutput.write(AuxNominaEXT[i][6]);
/* 611 */           csvOutput.write(AuxNominaEXT[i][7]);
/* 612 */           csvOutput.write(AuxNominaEXT[i][8]);
/* 613 */           csvOutput.write(AuxNominaEXT[i][9]);
/* 614 */           csvOutput.write(AuxNominaEXT[i][10]);
/* 615 */           csvOutput.write(AuxNominaEXT[i][11]);
/* 616 */           csvOutput.write(AuxNominaEXT[i][12]);
/* 617 */           csvOutput.write(AuxNominaEXT[i][13]);
/* 618 */           csvOutput.write(AuxNominaEXT[i][14]);
/* 619 */           csvOutput.write(AuxNominaEXT[i][15]);
/* 620 */           csvOutput.write(AuxNominaEXT[i][16]);
/* 621 */           csvOutput.write(AuxNominaEXT[i][17]);
/* 622 */           csvOutput.write(AuxNominaEXT[i][18]);
/* 623 */           csvOutput.write(AuxNominaEXT[i][19]);
/* 624 */           csvOutput.write(AuxNominaEXT[i][20]);
/* 625 */           csvOutput.write(AuxNominaEXT[i][21]);
/* 626 */           csvOutput.write(AuxNominaEXT[i][22]);
/* 627 */           csvOutput.endRecord();
/*     */         }
/*     */       }
/* 630 */       csvOutput.close();
/*     */     }
/*     */     catch (IOException e) {
/* 633 */       log.error(e);
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
/*     */   public String[][] Actualizar(String ruta)
/*     */   {
/* 646 */     String rte = this.vsRte + link(1);
/* 647 */     String[][] arr = DatosExternos(ruta);
/* 648 */     String[][] NominaEXTAc = NominasEXccel(rte);
/*     */     
/* 650 */     log.info("Entro al proceso de actualizar se actualizaron: " + arr.length + " registro");
/*     */     
/* 652 */     for (int i = 0; i < arr.length; i++) {
/* 653 */       int j = BuscarCampo(arr[i][0]);
/* 654 */       if (j > 0)
/*     */       {
/* 656 */         String NUMEROEMPLEADO = arr[i][0];
/* 657 */         String EMAIL = arr[i][1];
/* 658 */         String PUESTO = arr[i][2];
/* 659 */         String NUMEMPJEFE = arr[i][3];
/* 660 */         String JEFEINMEDIATO = arr[i][4];
/* 661 */         String COMPANIA = arr[i][5];
/* 662 */         String DEPARTAMENTO = arr[i][6];
/* 663 */         String COMPANY = "AMERICA MOVIL";
/* 664 */         String Assignee_Groups = "1000000443";
/* 665 */         String REGION = arr[i][7];
/*     */         
/* 667 */         NominaEXTAc[j][1] = NUMEROEMPLEADO;
/* 668 */         NominaEXTAc[j][6] = EMAIL;
/* 669 */         NominaEXTAc[j][7] = REGION;
/* 670 */         NominaEXTAc[j][13] = PUESTO;
/* 671 */         NominaEXTAc[j][14] = NUMEMPJEFE;
/* 672 */         NominaEXTAc[j][15] = JEFEINMEDIATO;
/* 673 */         NominaEXTAc[j][16] = COMPANIA;
/* 674 */         NominaEXTAc[j][11] = DEPARTAMENTO;
/* 675 */         NominaEXTAc[j][22] = COMPANY;
/* 676 */         NominaEXTAc[j][23] = Assignee_Groups;
/*     */       }
/*     */     }
/* 679 */     return NominaEXTAc;
/*     */   }
/*     */   
/*     */   public String[][] fncNominaExcel(String vsRuta, String vsGrupoSoporte) {
/* 683 */     log.info("Entro a fncNominaExcel");
/* 684 */     log.info(vsRuta);
/* 685 */     log.info(vsGrupoSoporte);
/* 686 */     String[][] voArrNomina = (String[][])null;
/* 687 */     Set voSet = new HashSet();
/* 688 */     int i = 0;
/*     */     try {
/* 690 */       WorkbookSettings vwConfigurar = new WorkbookSettings();
/* 691 */       vwConfigurar.setEncoding("iso-8859-1");
/* 692 */       Workbook voWorkbook = Workbook.getWorkbook(new File(vsRuta), vwConfigurar);
/* 693 */       Sheet voSheet = voWorkbook.getSheet(1);
/* 694 */       voArrNomina = new String[voSheet.getRows()][voSheet.getColumns() + 1];
/*     */       
/* 696 */       for (int viFila = 3; viFila < voSheet.getRows(); viFila++) {
/* 697 */         if (voSet.add(voSheet.getCell(1, viFila).getContents())) {
/* 698 */           for (int viColumna = 0; viColumna < voSheet.getColumns(); viColumna++) {
/* 699 */             voArrNomina[i][viColumna] = voSheet.getCell(viColumna, viFila).getContents();
/*     */           }
/*     */           
/*     */ 
/*     */ 
/* 704 */           voArrNomina[i][23] = vsGrupoSoporte;
/*     */           
/*     */ 
/* 707 */           i++;
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */     catch (IOException|BiffException voEx)
/*     */     {
/* 714 */       log.error(voEx);
/*     */     }
/*     */     
/* 717 */     return voArrNomina;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String[][] NominasEXccel(String Ruta)
/*     */   {
/* 728 */     log.info("Ruta: " + Ruta);
/* 729 */     String[][] arginternos = (String[][])null;
/* 730 */     String[][] arrgNomi = (String[][])null;
/*     */     try
/*     */     {
/* 733 */       WorkbookSettings configurar = new WorkbookSettings();
/* 734 */       configurar.setEncoding("iso-8859-1");
/* 735 */       Workbook workbook = Workbook.getWorkbook(new File(Ruta), configurar);
/* 736 */       Sheet sheet = workbook.getSheet(0);
/*     */       
/*     */ 
/* 739 */       arginternos = new String[sheet.getRows()][sheet.getColumns()];
/* 740 */       arrgNomi = new String[sheet.getRows()][24];
/* 741 */       for (int fila = 4; fila < sheet.getRows(); fila++) {
/* 742 */         for (int columna = 0; columna < sheet.getColumns(); columna++) {
/* 743 */           arginternos[fila][columna] = sheet.getCell(columna, fila).getContents();
/*     */         }
/*     */       }
/* 746 */       if (sheet.getColumns() == 32) {
/* 747 */         for (int i = 4; i < arginternos.length; i++) {
/* 748 */           for (int j = 0; j < arginternos[i].length; j++) {
/* 749 */             if ((j == arginternos[i].length - 1) && (!arginternos[i][31].isEmpty())) {
/* 750 */               String cell = arginternos[i][j].replace("(", "");
/* 751 */               arginternos[i][j] = cell;
/* 752 */               cell = arginternos[i][j].replace(")", "");
/* 753 */               arginternos[i][j] = cell;
/* 754 */               cell = arginternos[i][j].replace(" ", "");
/* 755 */               arginternos[i][j] = cell;
/*     */             }
/*     */           }
/* 758 */           if (!arginternos[i][21].isEmpty()) {
/* 759 */             int num = Integer.valueOf(arginternos[i][21]).intValue();
/* 760 */             arginternos[i][21] = String.valueOf(num);
/*     */           }
/*     */         }
/*     */       } else {
/* 764 */         for (int i = 4; i < arginternos.length; i++) {
/* 765 */           if ((!arginternos[i][22].isEmpty()) && (arginternos[i][22].matches("\\d*"))) {
/* 766 */             int num = Integer.valueOf(arginternos[i][22]).intValue();
/* 767 */             arginternos[i][22] = String.valueOf(num);
/*     */           }
/* 769 */           if (!arginternos[i][6].isEmpty()) {
/* 770 */             arginternos[i][6] = arginternos[i][6].replace("'", " ");
/*     */           }
/*     */         }
/*     */       }
/* 774 */       int j = 0;
/* 775 */       for (int i = 4; i < arginternos.length; i++)
/*     */       {
/* 777 */         switch (sheet.getColumns()) {
/*     */         case 32: 
/* 779 */           arrgNomi[j][0] = arginternos[i][2];
/* 780 */           arrgNomi[j][1] = arginternos[i][0];
/* 781 */           arrgNomi[j][2] = arginternos[i][1];
/* 782 */           arrgNomi[j][3] = arginternos[i][3];
/* 783 */           arrgNomi[j][4] = arginternos[i][4];
/* 784 */           arrgNomi[j][5] = arginternos[i][5];
/* 785 */           arrgNomi[j][6] = arginternos[i][6];
/* 786 */           arrgNomi[j][7] = arginternos[i][7];
/* 787 */           arrgNomi[j][8] = arginternos[i][9];
/* 788 */           arrgNomi[j][9] = arginternos[i][11];
/* 789 */           arrgNomi[j][10] = arginternos[i][13];
/* 790 */           arrgNomi[j][11] = arginternos[i][15];
/* 791 */           arrgNomi[j][12] = arginternos[i][16];
/* 792 */           arrgNomi[j][13] = arginternos[i][17];
/* 793 */           arrgNomi[j][14] = arginternos[i][21];
/* 794 */           arrgNomi[j][15] = arginternos[i][22];
/* 795 */           arrgNomi[j][16] = "TELCEL";
/* 796 */           arrgNomi[j][17] = "0";
/* 797 */           arrgNomi[j][18] = arginternos[i][8];
/* 798 */           arrgNomi[j][19] = arginternos[i][29];
/* 799 */           arrgNomi[j][20] = arginternos[i][30];
/* 800 */           arrgNomi[j][21] = arginternos[i][31];
/* 801 */           arrgNomi[j][22] = "TELCEL";
/* 802 */           arrgNomi[j][23] = "1000000021";
/* 803 */           j++;
/* 804 */           break;
/*     */         
/*     */         case 33: 
/* 807 */           arrgNomi[j][0] = arginternos[i][2];
/* 808 */           arrgNomi[j][1] = arginternos[i][0];
/* 809 */           arrgNomi[j][2] = arginternos[i][1];
/* 810 */           arrgNomi[j][3] = arginternos[i][3];
/* 811 */           arrgNomi[j][4] = arginternos[i][4];
/* 812 */           arrgNomi[j][5] = arginternos[i][5];
/* 813 */           arrgNomi[j][6] = arginternos[i][6];
/* 814 */           arrgNomi[j][7] = arginternos[i][7];
/* 815 */           arrgNomi[j][8] = arginternos[i][9];
/* 816 */           arrgNomi[j][9] = arginternos[i][11];
/* 817 */           arrgNomi[j][10] = arginternos[i][13];
/* 818 */           arrgNomi[j][11] = arginternos[i][15];
/* 819 */           arrgNomi[j][12] = arginternos[i][16];
/* 820 */           arrgNomi[j][13] = arginternos[i][17];
/* 821 */           arrgNomi[j][14] = arginternos[i][22];
/* 822 */           arrgNomi[j][15] = arginternos[i][23];
/* 823 */           arrgNomi[j][16] = arginternos[i][19];
/* 824 */           arrgNomi[j][17] = "1";
/* 825 */           arrgNomi[j][18] = arginternos[i][8];
/* 826 */           arrgNomi[j][19] = " ";
/* 827 */           arrgNomi[j][20] = " ";
/* 828 */           arrgNomi[j][21] = " ";
/* 829 */           arrgNomi[j][22] = "TELCEL";
/* 830 */           arrgNomi[j][23] = "1000000021";
/* 831 */           j++;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (IOException ex) {
/* 836 */       log.error(ex);
/*     */     } catch (BiffException ex) {
/* 838 */       log.error(ex);
/*     */     }
/* 840 */     return arrgNomi;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean Proces()
/*     */   {
/* 852 */     String rte = this.vsRte + link(1);
/* 853 */     String rti = this.vsRti + link(1);
/* 854 */     String rbi = this.vsRbi + link(1);
/* 855 */     String vsRutaTelcelIn = this.vsRutaIn + link(4);
/* 856 */     String vsRutaTelcelExt = this.vsRutaEx + link(4);
/* 857 */     String vsRutaBajasInternos = this.vsRutaBajasIn + link(4);
/*     */     try {
/* 859 */       ExcCommand("mv " + vsRutaTelcelExt + " " + rte);
/* 860 */       ExcCommand("mv " + vsRutaTelcelIn + " " + rti);
/* 861 */       ExcCommand("mv " + vsRutaBajasInternos + " " + rbi);
/*     */       
/* 863 */       ExcCommand(this.vsComandoPam);
/*     */       
/* 865 */       this.NominaINT = NominasEXccel(rti);
/* 866 */       Thread.sleep(1000L);
/*     */       
/* 868 */       this.NominaEXT = NominasEXccel(rte);
/* 869 */       Thread.sleep(1000L);
/*     */       
/* 871 */       this.voNominaPanama = fncNominaExcel(vsRutaPam, vsGrpSoportePan);
/* 872 */       Thread.sleep(1000L);
/* 873 */       this.voNominaCostaRica = fncNominaExcel(vsRutaCostaRica, vsGrpSoporteCos);
/* 874 */       Thread.sleep(1000L);
/* 875 */       this.voNominaHonduras = fncNominaExcel(vsRutaHonduras, vsGrpSoporteHon);
/* 876 */       Thread.sleep(1000L);
/* 875 */       this.voNominaAMX = fncNominaExcel(vsRutaAMX, vsGrpSoporteAMX);
/* 876 */       Thread.sleep(1000L);
/* 875 */       this.voNominaARG = fncNominaExcel(vsRutaARG, vsGrpSoporteARG);
/* 876 */       Thread.sleep(1000L);
/* 877 */       this.voNominaGuatemala = fncNominaExcel(vsRutaGuatemala, vsGrpSoporteGua);
/* 878 */       Thread.sleep(1000L);
/* 879 */       this.voNominaNicaragua = fncNominaExcel(vsRutaNicaragua, vsGrpSoporteNic);
/* 880 */       Thread.sleep(1000L);
/* 881 */       this.voNominaSalvador = fncNominaExcel(vsRutaSalvador, vsGrpSoporteSal);
/* 882 */       Thread.sleep(1000L);
/* 883 */       this.voNominaRepDominicana = fncNominaExcel(vsRutaDominicana, vsGrpSoporteDom);
/* 884 */       Thread.sleep(1000L);
/* 885 */       this.voNominaPuertiRico = fncNominaExcel(vsRutaPuertoRico, vsGrpSoportePri);
/* 886 */       Thread.sleep(1000L);
/*     */       
/*     */ 
/*     */ 
/* 890 */       this.AuxNominaEXT = Actualizar(vsRutaAct);
/* 891 */       crearcvs();
/* 892 */       Thread.sleep(1000L);
/*     */       String rte3;
/* 894 */       return true;
/*     */     } catch (InterruptedException ex) { String rte2;
/* 896 */       log.error(ex);
/* 897 */       String rte3; return false;
/*     */ 
/*     */ 
/*     */     }
/*     */     finally
/*     */     {
/*     */ 
/*     */ 
/* 905 */       String rte2 = this.vsRte + link(1);
/* 906 */       String rte3 = this.vsRte + link(2);
/*     */       
/*     */ 
/* 909 */       crearExternosCVS("Externos2ago", rte2);
/* 910 */       crearExternosCVS("Externos3ago", rte3);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/* 916 */     EnvioDatos envd = new EnvioDatos();
/* 917 */     Nomina nomi = new Nomina();
/* 918 */     if (envd.Proces() == true) {
/* 919 */       nomi.EliminaNomi();
/*     */     } else {
/* 921 */       envd.Proces();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\VSCruz\Documents\nominaD\Nominav2.zip!\Nominav2\WEB-INF\classes\com\mx\telcel\nomina\EnvioDatos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */