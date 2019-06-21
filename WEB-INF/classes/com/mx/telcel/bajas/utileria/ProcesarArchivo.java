/*     */ package com.mx.telcel.bajas.utileria;
/*     */ 
/*     */ import com.csvreader.CsvWriter;
/*     */ import com.infomedia.utils.PropertyLoader;
/*     */ import com.mx.telcel.nomina.vo.NominaVO;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProcesarArchivo
/*     */ {
/*  40 */   private static final Logger log = Logger.getLogger(ProcesarArchivo.class);
/*  41 */   public static final Properties prop = PropertyLoader.load("nominas.properties");
/*     */   public final String vsRuta;
/*     */   
/*     */   public ProcesarArchivo() {
/*  45 */     this.vsRuta = prop.getProperty("RUTA_RMDCONTROL");
/*     */   }
/*     */   
/*     */   public List<NominaVO> fncLeerArchivo(String psRuta)
/*     */   {
/*  50 */     Set voSet = new HashSet();
/*  51 */     List<NominaVO> voSetArchivo = new ArrayList();
/*  52 */     File voArchivo = new File(psRuta);
/*  53 */     if (voArchivo.exists()) {
/*     */       try {
/*  55 */         WorkbookSettings vwConfigurar = new WorkbookSettings();
/*  56 */         vwConfigurar.setEncoding("iso-8859-1");
/*  57 */         Workbook voWorkbook = Workbook.getWorkbook(new File(psRuta), vwConfigurar);
/*  58 */         Sheet voSheet = voWorkbook.getSheet(1);
/*  59 */         for (int viFila = 3; viFila < voSheet.getRows(); viFila++) {
/*  60 */           NominaVO voRegistroN = new NominaVO();
/*  61 */           if (voSet.add(voSheet.getCell(1, viFila).getContents())) {
/*  62 */             voRegistroN.setVsNombreCompleto(voSheet.getCell(0, viFila).getContents());
/*  63 */             voRegistroN.setVsCorporateID(voSheet.getCell(1, viFila).getContents());
/*  64 */             voRegistroN.setVsUsuario(voSheet.getCell(2, viFila).getContents());
/*  65 */             voRegistroN.setVsPaterno(voSheet.getCell(3, viFila).getContents());
/*  66 */             voRegistroN.setVsMaterno(voSheet.getCell(4, viFila).getContents());
/*  67 */             voRegistroN.setVsNombre(voSheet.getCell(5, viFila).getContents());
/*  68 */             voRegistroN.setVsEmail(voSheet.getCell(6, viFila).getContents());
/*  69 */             voRegistroN.setVsRegion(voSheet.getCell(7, viFila).getContents());
/*  70 */             voRegistroN.setVsDireccion(voSheet.getCell(8, viFila).getContents());
/*  71 */             voRegistroN.setVsSubdireccion(voSheet.getCell(9, viFila).getContents());
/*  72 */             voRegistroN.setVsGerencia(voSheet.getCell(10, viFila).getContents());
/*  73 */             voRegistroN.setVsDepartamento(voSheet.getCell(11, viFila).getContents());
/*  74 */             voRegistroN.setVsIdDepto(voSheet.getCell(12, viFila).getContents());
/*  75 */             voRegistroN.setVsPuesto(voSheet.getCell(13, viFila).getContents());
/*  76 */             voRegistroN.setVsNumeroJefe(voSheet.getCell(14, viFila).getContents());
/*  77 */             voRegistroN.setVsJefeInmediato(voSheet.getCell(15, viFila).getContents());
/*  78 */             voRegistroN.setVsCompañia(voSheet.getCell(16, viFila).getContents());
/*  79 */             voRegistroN.setVsTipoEmpleado(voSheet.getCell(17, viFila).getContents());
/*  80 */             voRegistroN.setVsIdRegion(voSheet.getCell(18, viFila).getContents());
/*  81 */             voRegistroN.setVsConvencional(voSheet.getCell(19, viFila).getContents());
/*  82 */             voRegistroN.setVsExtencion(voSheet.getCell(20, viFila).getContents());
/*  83 */             voRegistroN.setVsCelular(voSheet.getCell(21, viFila).getContents());
/*  84 */             voRegistroN.setVsCompany(voSheet.getCell(22, viFila).getContents());
/*  85 */             voSetArchivo.add(voRegistroN);
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (IOException|BiffException voEx)
/*     */       {
/*  91 */         log.error(voEx);
/*     */       }
/*     */     }
/*  94 */     System.out.println("Longitud del HastSet " + voSetArchivo.size());
/*  95 */     return voSetArchivo;
/*     */   }
/*     */   
/*     */   public List<String> fncListaID(String psRuta) {
/*  99 */     Set voSet = new HashSet();
/* 100 */     List<String> voListId = new ArrayList();
/* 101 */     File voArchivo = new File(psRuta);
/* 102 */     if (voArchivo.exists()) {
/*     */       try {
/* 104 */         WorkbookSettings vwConfigurar = new WorkbookSettings();
/* 105 */         vwConfigurar.setEncoding("iso-8859-1");
/* 106 */         Workbook voWorkbook = Workbook.getWorkbook(new File(psRuta), vwConfigurar);
/* 107 */         Sheet voSheet = voWorkbook.getSheet(1);
/* 108 */         for (int viFila = 3; viFila < voSheet.getRows(); viFila++) {
/* 109 */           if (voSet.add(voSheet.getCell(1, viFila).getContents())) {
/* 110 */             voListId.add(voSheet.getCell(1, viFila).getContents());
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (IOException|BiffException voEx)
/*     */       {
/* 116 */         log.error(voEx);
/*     */       }
/*     */     }
/* 119 */     System.out.println("Longitud de la lista " + voListId.size());
/* 120 */     return voListId;
/*     */   }
/*     */   
/*     */   public List<List<NominaVO>> fncCompararArchivos() {
/* 124 */     RemedyControl voRemedyControl = new RemedyControl();
/* 125 */     List<String> voCompañias = voRemedyControl.fncConsultarCampanias();
/* 126 */     String vsPrefijo = prop.getProperty("PATH_RUTA");
/* 127 */     String vsPrefijoAnt = prop.getProperty("PATH_RUTA_ANT");
/*     */     
/*     */ 
/* 130 */     List<NominaVO> voArchivoAux = new ArrayList();
/* 131 */     List<List<NominaVO>> voListCompañia = new ArrayList();
/* 132 */     for (String voCompañia : voCompañias) {
/* 133 */       voArchivoAux = new ArrayList();
/* 134 */       System.out.println(this.vsRuta + voCompañia + vsPrefijo);
/* 135 */       System.out.println(this.vsRuta + voCompañia + vsPrefijoAnt);
/* 136 */       List<String> voArchivoAct = fncListaID(this.vsRuta + voCompañia + vsPrefijo);
/* 137 */       List<NominaVO> voArchivoAnt = fncLeerArchivo(this.vsRuta + voCompañia + vsPrefijoAnt);
/* 138 */       for (NominaVO nominaVO : voArchivoAnt) {
/* 139 */         if (!voArchivoAct.contains(nominaVO.getVsCorporateID())) {
/* 140 */           voArchivoAux.add(nominaVO);
/*     */         }
/*     */       }
/*     */       
/* 144 */       voListCompañia.add(voArchivoAux);
/* 145 */       System.out.println(voArchivoAux.size());
/*     */     }
/* 147 */     return voListCompañia;
/*     */   }
/*     */   
/*     */   public void fncCreararchivo(List<List<NominaVO>> poListaBajas, List<String> poCompañias) {
/* 151 */     String outputFile = prop.getProperty("PATH_BAJAS_INTERNOS");
/* 152 */     String outputFileExternos = prop.getProperty("PATH_BAJAS_EXTERNOS");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 157 */     String vsRutaArchivoInternos = "";
/* 158 */     String vsRutaArchivoExternos = "";
/*     */     
/* 160 */     int i = 0;
/* 161 */     for (List<NominaVO> poListaBaja : poListaBajas) {
/* 162 */       vsRutaArchivoInternos = outputFile + (String)poCompañias.get(i) + "/BAJAS_INTERNOS_" + (String)poCompañias.get(i) + "_" + fncGetDate() + ".csv";
/* 163 */       vsRutaArchivoExternos = outputFileExternos + (String)poCompañias.get(i) + "/BAJAS_EXTERNOS_" + (String)poCompañias.get(i) + "_" + fncGetDate() + ".csv";
/* 164 */       if (!poListaBaja.isEmpty()) {
/* 165 */         if (new File(vsRutaArchivoInternos).exists()) {
/* 166 */           File vfArchivobajas = new File(vsRutaArchivoInternos);
/* 167 */           vfArchivobajas.delete(); }
/*     */         File vfArchivobajas;
/* 169 */         if (new File(vsRutaArchivoExternos).exists()) {
/* 170 */           vfArchivobajas = new File(vsRutaArchivoExternos);
/* 171 */           vfArchivobajas.delete();
/*     */         }
/*     */         try {
/* 174 */           OutputStream output = new FileOutputStream(vsRutaArchivoInternos);
/* 175 */           CsvWriter csvOutput = new CsvWriter(output, ',', Charset.forName("iso-8859-1"));
/* 176 */           csvOutput.write("Numero de empleado");
/* 177 */           csvOutput.write("Nombre completo");
/* 178 */           csvOutput.endRecord();
/* 179 */           OutputStream outputExternos = new FileOutputStream(vsRutaArchivoExternos);
/* 180 */           CsvWriter csvOutputExternos = new CsvWriter(outputExternos, ',', Charset.forName("iso-8859-1"));
/* 181 */           csvOutputExternos.write("Numero de empleado");
/* 182 */           csvOutputExternos.write("Nombre completo");
/* 183 */           csvOutputExternos.endRecord();
/* 184 */           for (NominaVO nominaVO : poListaBaja) {
/* 185 */             if (nominaVO.getVsTipoEmpleado() == "0") {
/* 186 */               csvOutput.write(nominaVO.getVsCorporateID());
/* 187 */               csvOutput.write(nominaVO.getVsNombreCompleto());
/* 188 */               csvOutput.endRecord();
/*     */             } else {
/* 190 */               csvOutputExternos.write(nominaVO.getVsCorporateID());
/* 191 */               csvOutputExternos.write(nominaVO.getVsNombreCompleto());
/* 192 */               csvOutputExternos.endRecord();
/*     */             }
/*     */           }
/*     */           
/* 196 */           csvOutput.close();
/* 197 */           csvOutputExternos.close();
/*     */         } catch (Exception e) {
/* 199 */           log.error(e);
/*     */         }
/*     */       }
/*     */       
/* 203 */       i++;
/*     */     }
/*     */   }
/*     */   
/*     */   public String fncGetDate()
/*     */   {
/* 209 */     SimpleDateFormat vsFormato = new SimpleDateFormat("yyyyMMdd");
/* 210 */     Date voFecha = new Date();
/* 211 */     return vsFormato.format(voFecha);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 215 */     ProcesarArchivo voArchivo = new ProcesarArchivo();
/*     */     
/* 217 */     RemedyControl voRemedyControl = new RemedyControl();
/* 218 */     List<String> voCompañias = voRemedyControl.fncConsultarCampanias();
/* 219 */     voArchivo.fncCreararchivo(voArchivo.fncCompararArchivos(), voCompañias);
/*     */   }
/*     */ }


/* Location:              C:\Users\VSCruz\Documents\nominaD\Nominav2.zip!\Nominav2\WEB-INF\classes\com\mx\telcel\baja\\utileria\ProcesarArchivo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */