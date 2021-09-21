	package com.baseClasses;
	
	import java.awt.AWTException;
	import java.awt.Color;
	import java.awt.Graphics2D;
	import java.awt.Rectangle;
	import java.awt.Robot;
	import java.awt.Toolkit;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.net.InetAddress;
	import java.net.MalformedURLException;
	import java.text.DecimalFormat;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Calendar;
	import java.util.Date;
	import java.util.HashMap;
	import java.util.Hashtable;
	import java.util.List;
	import java.util.Map;
	import java.util.Set;
	
	import javax.imageio.ImageIO;
	
	
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.jfree.chart.ChartFactory;
	import org.jfree.chart.JFreeChart;
	import org.jfree.chart.plot.PiePlot;
	import org.jfree.chart.title.TextTitle;
	import org.jfree.data.general.DefaultPieDataset;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	
	import com.itextpdf.awt.DefaultFontMapper;
	import com.itextpdf.text.BaseColor;
	import com.itextpdf.text.Chunk;
	import com.itextpdf.text.Document;
	import com.itextpdf.text.DocumentException;
	import com.itextpdf.text.Element;
	import com.itextpdf.text.Font;
	import com.itextpdf.text.Font.FontFamily;
	import com.itextpdf.text.FontFactory;
	import com.itextpdf.text.Image;
	import com.itextpdf.text.PageSize;
	import com.itextpdf.text.Paragraph;
	import com.itextpdf.text.Phrase;
	import com.itextpdf.text.pdf.PdfContentByte;
	import com.itextpdf.text.pdf.PdfPCell;
	import com.itextpdf.text.pdf.PdfPTable;
	import com.itextpdf.text.pdf.PdfTemplate;
	import com.itextpdf.text.pdf.PdfWriter;
	
	import java.awt.AWTException;
	import java.awt.Color;
	import java.awt.Graphics2D;
	import java.awt.Rectangle;
	import java.awt.Robot;
	import java.awt.Toolkit;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.net.InetAddress;
	import java.net.MalformedURLException;
	import java.text.DecimalFormat;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Calendar;
	import java.util.Date;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.jfree.chart.ChartFactory;
	import org.jfree.chart.JFreeChart;
	import org.jfree.chart.plot.PiePlot;
	import org.jfree.chart.title.TextTitle;
	import org.jfree.data.general.DefaultPieDataset;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import com.itextpdf.awt.DefaultFontMapper;
	import com.itextpdf.text.BaseColor;
	import com.itextpdf.text.Chunk;
	import com.itextpdf.text.Document;
	import com.itextpdf.text.DocumentException;
	import com.itextpdf.text.Element;
	import com.itextpdf.text.Font;
	import com.itextpdf.text.Font.FontFamily;
	import com.itextpdf.text.FontFactory;
	import com.itextpdf.text.Image;
	import com.itextpdf.text.PageSize;
	import com.itextpdf.text.Paragraph;
	import com.itextpdf.text.Phrase;
	import com.itextpdf.text.pdf.PdfContentByte;
	import com.itextpdf.text.pdf.PdfPCell;
	import com.itextpdf.text.pdf.PdfPTable;
	import com.itextpdf.text.pdf.PdfTemplate;
	import com.itextpdf.text.pdf.PdfWriter;
	
	
	public class PDFResultReport{
		public Map<String, String> testData = new HashMap<String, String>();
		public Map<String, String> testDataValue = new HashMap<String, String>();;
		public  Paragraph paragraph = null;
		public  Paragraph paragraph1 = null;
		public static PdfPTable table = null;
		public static PdfPTable table3 = null;
	
		public  PdfPTable table1 = null;
		public  PdfPTable table2 = null;
		public  Font bfBold12;
		public  Font bf12;
		
		public  Font Fontcolor;
		public  Font Fontcolor1;
		public  Document doc = null;
		public  Document document = null;
		public PdfWriter docWriter;
		public  PdfWriter docWriter1;
		public  long testCaseStartTime;
		public  long testCaseStopTime;
		public  long testCaseElapsedTime;
		public  String strCaseStartTime;
		public  String strCaseStopTime;
		public  static File suiteFolder;
		protected static File testFolder;
		private static File suiteFile;
		public static String suiteFilePath;
		public  HeaderFooterPageEvent event;
		public static String suiteName;
		public static String testCaseReportsFolder = "Test";
		public static String snapShotsFolder = "Snapshots";
		public  String filePath;
		public  List<String> testCaseStartTimeList = new ArrayList();
		public  List<Integer> failCount = new ArrayList();
		public  List<Integer> passCount = new ArrayList();
		public  List<String> testCaseEndTimeList = new ArrayList();
		public  List<String> testCaseNameList = new ArrayList();
		public  List<String> testCaseDescriptionList = new ArrayList();
		public  final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		public  final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
		public static  final SimpleDateFormat DATE_FORMAT_NOW = new SimpleDateFormat("yyyyMMddHHmmss");
		public static long testStepTempTime;
		public  List<String> testStepElapsedTimeList = new ArrayList();
		public static List<String> testCaseElapsedTimeList = new ArrayList();
		public static File snapShotFolder;
		public  List<String> snapShotPathList = new ArrayList();
		public static  Map<String, String> statusMap = new HashMap();
		public  List<String> stepCount = new ArrayList();
		public  List<String> stepDescription = new ArrayList();
		public  List<String> stepDescription1 = new ArrayList();
	
		public  List<String> stepPrequisite = new ArrayList();
		public  List<String> stepExpected = new ArrayList();
		public  List<String> stepActual = new ArrayList();
		public  List<String> stepStatus = new ArrayList();
		public  static List<String> stepStatus1 = new ArrayList();
	
		public  List<String> objectEvidence = new ArrayList();
		public List<String> testCaseName = new ArrayList();
		public  List<String> testCaseDesc = new ArrayList();
		public static List<String> testCaseName1 = new ArrayList();
		public static List<String> testFullName= new ArrayList();
		public static List<String> testmethod1 = new ArrayList();
		public static List<String> testCaseDesc1 = new ArrayList();
		public static List<String> testCaseStartTimeList1 = new ArrayList();
		public static List<String> testCaseEndTimeList1 = new ArrayList();
		public static List<String> classes= new ArrayList <String>();
		public static List<String> methods= new ArrayList <String>();
		public  int l = 2; 
		public  int iter = 0;
		public static List<String> errorDes= new ArrayList <String>();
		public static List<String> ScreenShots= new ArrayList <String>();
		public  String screenShotPath = null;
		public static String path=null;
		public void createPDFfile(String pdfFilename){
			try {
				doc = new Document(PageSize.A4);
				DecimalFormat df = new DecimalFormat("0.00");
				bfBold12 = new Font(FontFamily.UNDEFINED, 9, Font.BOLD, new BaseColor(0, 0, 0)); 
				bf12 = new Font(FontFamily.UNDEFINED, 8);
				Fontcolor = new Font(FontFamily.UNDEFINED,8);
				Fontcolor.setColor(BaseColor.GREEN);
	
				Fontcolor1 = new Font(FontFamily.UNDEFINED,8);
				Fontcolor1.setColor(BaseColor.RED);
				//String path = testFolder+"//"+pdfFilename+".pdf";
				path = testFolder+"/"+pdfFilename+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".pdf";
				System.out.println("path in pdfresult report :::::"+path);
				docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
				event = new HeaderFooterPageEvent();
				docWriter.setPageEvent(event);
				doc.open();
				filePath = testFolder+"//"+pdfFilename+".xls";
				// excelReport.createExcel(filePath);
				//excelReport.createExcelTestReport();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void createTestRunReport(String testScriptName,String testAuthorName,boolean status) throws Exception{
			try{
				String runName = null;
				if(status){
					runName = testData.get("RunName")+ "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					Paragraph header = new com.itextpdf.text.Paragraph();
					header.add(testData.get("ScriptExecution")+"\n");
					if(testData.get("ScriptExecution").trim().equalsIgnoreCase("Post Execution Report")){
						header.add("Test Script Name: "+testScriptName+"[Run Name:"+runName+"]\n");
					}else if(testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
						header.add("Test Script Name: "+testScriptName+"\n");
					}
	
					header.add("System Name: "+testData.get("System Name")+" ; Project Name: "+testData.get("Project Name")+"\n");
					event.setHeader(header);
					//doc.add(new Paragraph(" "));
					//doc.add(new Paragraph(" "));
					paragraph1 = new Paragraph("",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK));
					paragraph1.setAlignment(paragraph.ALIGN_LEFT);
					//doc.add(Chunk.NEWLINE);
					paragraph1 = new Paragraph("",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK));
					doc.add(paragraph1);
	
				}else{
					document.add(Chunk.NEWLINE);
				}
	
	
				paragraph1 = new Paragraph("Test Script Information:",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK));
				paragraph1.setAlignment(paragraph.ALIGN_LEFT);
				doc.add(Chunk.NEWLINE);     //Do not uncomment this
	
				float[] columnWidths = {3f, 6f};
				//float[] columnWidths = {5f, 8f};
				table1 = new PdfPTable(columnWidths);
				table1.setHorizontalAlignment(Element.ALIGN_LEFT);
				table1.setWidthPercentage(50f);
	
				//cell.setColspan(2);
				//cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				//table1.addCell(cell);
				insertCell(table1, "System Name", Element.ALIGN_CENTER, 1, bf12);
				insertCell(table1, testData.get("System Name"), Element.ALIGN_CENTER, 1, bf12);
				if(testData.get("ScriptExecution").trim().equalsIgnoreCase("Post Execution Report")){
					insertCell(table1, "Environment", Element.ALIGN_CENTER, 1, bf12);
					insertCell(table1, testData.get("Environment"), Element.ALIGN_CENTER, 1, bf12);
					insertCell(table1, "Operating System", Element.ALIGN_CENTER, 1, bf12);
					insertCell(table1, testData.get("Operating System"), Element.ALIGN_CENTER, 1, bf12);
					insertCell(table1, "Browser(Version)", Element.ALIGN_CENTER, 1, bf12);
					insertCell(table1, testData.get("Browser(Version)"), Element.ALIGN_CENTER, 1, bf12);
					if(status){
						InetAddress ipAddr = InetAddress.getLocalHost();
						insertCell(table1, "System ID", Element.ALIGN_CENTER, 1, bf12);
						insertCell(table1, ipAddr.getHostAddress(), Element.ALIGN_CENTER, 1, bf12);
					}
				}else if(testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
					if(status == false){
						insertCell(table1, "Operating System", Element.ALIGN_CENTER, 1, bf12);
						insertCell(table1, testData.get("Operating System"), Element.ALIGN_CENTER, 1, bf12);
						insertCell(table1, "Browser(Version)", Element.ALIGN_CENTER, 1, bf12);
						insertCell(table1, testData.get("Browser(Version)"), Element.ALIGN_CENTER, 1, bf12);
					}
				}
				if(status){  
					insertCell(table1, "Test Script Name", Element.ALIGN_CENTER, 1, bf12);
					insertCell(table1, testScriptName, Element.ALIGN_CENTER, 1, bf12);
					/*insertCell(table1, "Test Script MyWorksshop Document ID", Element.ALIGN_CENTER, 1, bf12);
	                   insertCell(table1, myWorkShopID, Element.ALIGN_CENTER, 1, bf12);
	                   insertCell(table1, "Test Script Revision No", Element.ALIGN_CENTER, 1, bf12);
	                   insertCell(table1, testScriptRevNo, Element.ALIGN_CENTER, 1, bf12);
	                   insertCell(table1, "Author:SSO ID of the AUthor", Element.ALIGN_CENTER, 1, bf12);
	                   insertCell(table1, autherSSOID, Element.ALIGN_CENTER, 1, bf12);*/
					//  insertCell(table1, "Test Script Type", Element.ALIGN_CENTER, 1, bf12);
					//   insertCell(table1, testScriptType, Element.ALIGN_CENTER, 1, bf12);
					//   insertCell(table1, "Requirement Document ID of System", Element.ALIGN_CENTER, 1, bf12);
					// insertCell(table1, reqDocID, Element.ALIGN_CENTER, 1, bf12);
					//insertCell(table1, "Requirement ID", Element.ALIGN_CENTER, 1, bf12);
					// insertCell(table1, reqID, Element.ALIGN_CENTER, 1, bf12);
					// insertCell(table1, "GxP Relevant", Element.ALIGN_CENTER, 1, bf12);
					//  insertCell(table1, testData.get("GxP Relevant"), Element.ALIGN_CENTER, 1, bf12);
					insertCell(table1, "Project Name", Element.ALIGN_CENTER, 1, bf12);
					insertCell(table1, testData.get("Project Name"), Element.ALIGN_CENTER, 1, bf12);
					insertCell(table1, "Report Type", Element.ALIGN_CENTER, 1, bf12);
					insertCell(table1, testData.get("ScriptExecution"), Element.ALIGN_CENTER, 1, bf12);   
	
					/*  if(testData.get("ScriptExecution").trim().equalsIgnoreCase("Post Execution Report")){
	                      insertCell(table1, "RunName", Element.ALIGN_CENTER, 1, bf12);
	                      insertCell(table1, runName, Element.ALIGN_CENTER, 1, bf12);
	                   }*/
	
					if(testData.get("ScriptExecution").trim().equalsIgnoreCase("Post Execution Report")){
						insertCell(table1, "Date", Element.ALIGN_CENTER, 1, bf12);
						insertCell(table1, sysDate(), Element.ALIGN_CENTER, 1, bf12);
					}
				}
				paragraph1.setIndentationLeft(3);
	
				paragraph1.add(table1);
				if(status){
					doc.add(paragraph1);
				}else{
					document.add(paragraph1);
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	
		public void addStepDetails(String Param2,String Param3,String Param4,String Param5,String Param6) throws Exception {
	
			stepDescription.add(Param2);
			stepDescription1.add(Param2);
			stepExpected.add(Param3);
			stepActual.add(Param4);
			stepStatus.add(Param5);
			stepStatus1.add(Param5);
			
			String s=null;
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			s=stackTraceElements[3].getClassName();
			
			System.out.println("S value is:            " + s);
			classes.add(s);
			methods.add(Param2);
			errorDes.add(Param4);
			objectEvidence.add(Param6);
			long l = System.currentTimeMillis() - testStepTempTime;
			int i = 0;
			int j = (int)(l / 1000L);
			String str = String.valueOf(j);
			if (j >= 60)
			{
				i = j / 60;
				j %= 60;
				str = String.valueOf(j);
			}
			if (j <= 9)
				str = "0" + String.valueOf(j);
			testStepElapsedTimeList.add(sysTime());
			testStepTempTime = System.currentTimeMillis();
			// captureSnapShot(ThreadLocalWebdriver.getDriver());
			fullScreenCapture(Param6,Param5);
		}
public void addStepDetails(String Param2,String Param3,String Param4,String Param5,String Param6,String param7) throws Exception {
			
			stepDescription.add(Param2);
			stepDescription1.add(Param2);
			stepExpected.add(Param3);
			stepActual.add(Param4);
			stepStatus.add(Param5);
			stepStatus1.add(Param5);
			
			String s=null;
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			s=stackTraceElements[3].getClassName();
			
			System.out.println("S value is:            " + s);
			classes.add(s);
			methods.add(Param2);
			errorDes.add(Param4);
			objectEvidence.add(Param6);
			long l = System.currentTimeMillis() - testStepTempTime;
			int i = 0;
			int j = (int)(l / 1000L);
			String str = String.valueOf(j);
			if (j >= 60)
			{
				i = j / 60;
				j %= 60;
				str = String.valueOf(j);
			}
			if (j <= 9)
				str = "0" + String.valueOf(j);
			testStepElapsedTimeList.add(sysTime());
			testStepTempTime = System.currentTimeMillis();
			// captureSnapShot(ThreadLocalWebdriver.getDriver());
			fullScreenCapture(Param6,Param5,"");
		}
		public void fullScreenCapture(String Parameter,String Parameter1,String param7) throws Exception{
            try {
          Robot robot = new Robot();
          String currentUsersHomeDir = System.getProperty("user.dir");
          String otherFolder = currentUsersHomeDir + File.separator + "Resources" + File.separator + "rfqExcel"+File.separator + "image";
          String format = "jpg";
          String fileName = "SnapShot_" + DATE_FORMAT_NOW.format(new Date()) + ".jpg";
         Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
          BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
          if(((Parameter == "Y") || (Parameter == "N")) && (Parameter1.equalsIgnoreCase("Fail"))){
          // ImageIO.write(screenFullImage, "jpg", new File(path));
           
          
           ImageIO.write(screenFullImage, format, new File(snapShotFolder+"//"+ fileName));
              snapShotPathList.add(fileName);
              screenShotPath=snapShotFolder+"/"+fileName;
              ScreenShots.add(screenShotPath);
          }else if((Parameter == "Y") && (Parameter1.equalsIgnoreCase("Pass"))){
          ImageIO.write(screenFullImage, format, new File(snapShotFolder+"//"+ fileName));
           /* File f=((TakesScreenshot)ThreadLocalWebdriver.getDriver()).getScreenshotAs(OutputType.FILE);
              FileUtils.copyFile(f, new File(snapShotFolder+"/"+fileName));*/
              
             snapShotPathList.add(fileName);
             screenShotPath=snapShotFolder+"/"+fileName;
             ScreenShots.add(screenShotPath);
          }else{
          snapShotPathList.add("");
          }
      } catch (IOException ex) {
          System.err.println(ex);
      }
     }
		public void addPrerequisiteDetails(String Param2,String Param3,String Param4,String Param5,String Param6,String Param7) throws Exception {
			stepPrequisite.add(Param2);
			stepDescription.add(Param3);
			stepDescription1.add(Param3);
			stepExpected.add(Param4);
			stepActual.add(Param5);
			stepStatus.add(Param6);
			stepStatus1.add(Param6);
			objectEvidence.add(Param7);
			long l = System.currentTimeMillis() - testStepTempTime;
			int i = 0;
			int j = (int)(l / 1000L);
			String str = String.valueOf(j);
			if (j >= 60){
				i = j / 60;
				j %= 60;
				str = String.valueOf(j);
			}
			if (j <= 9)
				str = "0" + String.valueOf(j);
			testStepElapsedTimeList.add(sysTime());
			testStepTempTime = System.currentTimeMillis();
			fullScreenCapture(Param7,Param6);
		}
		public void knowTestCaseStatus(String paramString1){
			try{
				int i = 0;
				int m = 0;
				int j = 0;
				for (int n = 0; n < stepStatus.size(); n++){
					if(((String)stepStatus.get(n)).trim().equalsIgnoreCase("pass")){
						m++;
						passCount.add(m);
					}else{
						j++;
						i++;
						failCount.add(j);
					}
				}
				if (i > 0){
					statusMap.put(paramString1, "Fail");
				}else{ 
					statusMap.put(paramString1, "Pass");
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void generatePDFStepdetails(String paramString1) throws Exception{
			try{
				int i = 0;
				int j = 0;
				int k = 0;
				int m = 0;
				int loop = 0;
				boolean st = true;
				testCaseNameList.add(paramString1);
				for (int n = 0; n < stepStatus.size(); n++){
					m = n+1;
					if((stepDescription.get(n) != "") && (stepExpected.get(n) != "") && (stepActual.get(n) != "")){
						j++;
						if(!testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
							if((n>stepPrequisite.size()-1)){
								if((st) || (snapShotPathList.get(n) != "")){
									doc.newPage();
									st = false;
								}
							}
						}else if(j == 4){
							j=1;
							doc.newPage();
						} 
						if(l == docWriter.getPageNumber()){
							doc.add(new Paragraph(" "));
							doc.add(new Paragraph(" "));
							l++;
						}
	
						createParagraph(" ");
						float[] columnWidths = {1f, 2f, 2f,2f};
						table2 = new PdfPTable(columnWidths);
						table2.setWidthPercentage(99f);
						PdfPCell cell = new PdfPCell();
						cell.setColspan(8);
						cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
						table2.addCell(cell);
						createHeader1(table2,"Step No","Status","Objective Evidence","Exe Time",bfBold12);
						String s = sdf1.format(new Date());
						String t = Long.toString(testStepTempTime);
						if(loop != stepPrequisite.size()){
							insertCell(table2, stepPrequisite.get(n), Element.ALIGN_CENTER, 0, bf12);
							loop++;
						}else{
							iter++;
							insertCell(table2, Integer.toString(iter), Element.ALIGN_CENTER, 0, bf12);
						}
						if(!testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
							if (((String)stepStatus.get(n)).trim().equalsIgnoreCase("pass")){
								insertCell(table2, stepStatus.get(n), Element.ALIGN_CENTER, 0, Fontcolor);
							}else{
								insertCell(table2, stepStatus.get(n), Element.ALIGN_CENTER, 0, Fontcolor1);
								i++;
							}
						}else{
							insertCell(table2, "", Element.ALIGN_CENTER, 0, Fontcolor);
						}
						insertCell(table2, objectEvidence.get(n) , Element.ALIGN_CENTER, 0, bf12);
						if(!testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
							insertCell(table2, testStepElapsedTimeList.get(n) , Element.ALIGN_CENTER, 0, bf12);
						}else{
							insertCell(table2, "" , Element.ALIGN_CENTER, 0, bf12);
						}
						insertCell(table2, "Description", Element.ALIGN_LEFT, 4, bfBold12);
						insertCell(table2, stepDescription.get(n), Element.ALIGN_LEFT, 4, bf12);
						insertCell(table2, "Expected", Element.ALIGN_LEFT, 4, bfBold12);
						insertCell(table2, stepExpected.get(n), Element.ALIGN_LEFT, 4, bf12);
						insertCell(table2, "Actual", Element.ALIGN_LEFT, 4, bfBold12);
						if(!testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
							insertCell(table2, stepActual.get(n), Element.ALIGN_LEFT, 4, bf12);
						}else{
							insertCell(table2, "", Element.ALIGN_LEFT, 4, bf12);
						}
						paragraph.add(table2);
						doc.add(paragraph);
					}
					if(!testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
						AddImagetoPDF(1,snapShotFolder+"//"+snapShotPathList.get(n),doc,m);
					}
					//excelReport.createExcelDetailReport();
					//excelReport.enterDetailsReport();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			finally{
				snapShotPathList.clear();
				testCaseName.clear();
				testCaseDesc.clear();
				stepCount.clear();
				stepDescription.clear();
				stepExpected.clear();
				stepActual.clear();
				passCount.clear();
				failCount.clear();
				stepStatus.clear();
				objectEvidence.clear();
				testCaseStartTimeList.clear();
				testCaseEndTimeList.clear();
				testStepElapsedTimeList.clear();
				try{
					// excelReport.closeExcelReport(filePath);
					if (doc != null){
						doc.close();
					}
					if (docWriter != null){
						docWriter.close();
					}
					paragraph.clear();
				}catch (Exception localException)
				{
					localException.printStackTrace();
				}
			}
		}
	
		public  void createParagraph(String Param) throws Exception{
			try{
				paragraph = new Paragraph(Param,FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK));
				paragraph.setAlignment(paragraph.ALIGN_LEFT);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void createTable(){
			try{
				float[] columnWidths = {1f, 1f, 1f, 1f, 2f};
				table = new PdfPTable(columnWidths);
				table.setWidthPercentage(99f);
				PdfPCell cell = new PdfPCell();
				cell.setColspan(8);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table.addCell(cell);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void createTable1(){
			try{
				float[] columnWidths = {1f, 1f, 1f, 2f};
				table = new PdfPTable(columnWidths);
				table.setWidthPercentage(99f);
				PdfPCell cell = new PdfPCell();
				cell.setColspan(8);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table.addCell(cell);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void addSummaryReport(String param2,String param3, String param4){
			try{
				testCaseName.add(param2);
				testCaseDesc.add(param3);
				testCaseName1.add(param2);
				testFullName.add(param4);
				testCaseDesc1.add(param3);
				System.out.println("testCaseName :"+testCaseName);
				System.out.println("testCaseDesc :"+testCaseDesc);
				System.out.println("testCaseName1 :"+testCaseName1);
				System.out.println("testCaseDesc1 :"+testCaseDesc1);
				long l = System.currentTimeMillis() - testStepTempTime;
				int i = 0;
				int j = (int)(l / 1000L);
				String str = String.valueOf(j);
				if (j >= 60)
				{
					i = j / 60;
					j %= 60;
					str = String.valueOf(j);
				}
				if (j <= 9)
					str = "0" + String.valueOf(j);
				testCaseElapsedTimeList.add(i + ":" + str);
				testStepTempTime = System.currentTimeMillis();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void generatePDFSummaryReport(String paramString1) throws Exception{
			try{
				int m;
			    knowTestCaseStatus(paramString1);
				paragraph = new Paragraph("SUMMARY REPORT:",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.RED));
				paragraph.setAlignment(paragraph.ALIGN_LEFT);
	
				doc.add(Chunk.NEWLINE); //Do not comment this
				createTable();
				createHeader2(table,"Test Case ID","Test case name","status","Start Time","End Time","",bfBold12);
				String s = sdf1.format(new Date());
				for (int k = 0; k < testCaseName.size(); k++){
					String t = Long.toString(testStepTempTime);
					m = k+1;
					insertCell(table, Integer.toString(m), Element.ALIGN_CENTER, 0, bf12);
					insertCell(table, testCaseName.get(k), Element.ALIGN_CENTER, 0, bf12);
					if(testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
						insertCell(table, "", Element.ALIGN_CENTER, 0, Fontcolor);
					}else{
						if ((statusMap.get(paramString1) != null) && (((String)statusMap.get(paramString1)).trim().equalsIgnoreCase("Pass"))){
							insertCell(table, "PASS", Element.ALIGN_CENTER, 0, Fontcolor);
						} else if ((statusMap.get(paramString1) != null) && (((String)statusMap.get(paramString1)).trim().equalsIgnoreCase("Fail")))
						{
							insertCell(table, "FAIL", Element.ALIGN_CENTER, 0, Fontcolor1);
						}
					}
					if(testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
						insertCell(table, "", Element.ALIGN_CENTER, 0, bf12);
						insertCell(table, "", Element.ALIGN_CENTER, 0, bf12);
					}else{
						insertCell(table, testCaseStartTimeList.get(0), Element.ALIGN_CENTER, 0, bf12);
						insertCell(table, testCaseEndTimeList.get(0), Element.ALIGN_CENTER, 0, bf12);
					}
				}
				insertCell(table, "Objective/Acceptance Criteria of script:", Element.ALIGN_LEFT, 5, bfBold12);
				insertCell(table, testCaseDesc.get(0), Element.ALIGN_LEFT, 5, bf12);
				paragraph.add(table);
				doc.add(paragraph);
				//ExcelReport.createExcelSummaryReport();
				//excelReport.enterSummaryReport(paramString1);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void insertCell(PdfPTable table, String text, int align, int colspan, Font bfBold12){
			try{
				PdfPCell cell = new PdfPCell(new Phrase(text.trim(), bfBold12));
				cell.setHorizontalAlignment(align);
				cell.setColspan(colspan);
				if((text.trim().equalsIgnoreCase("Description")) || (text.trim().equalsIgnoreCase("Expected")) || (text.trim().equalsIgnoreCase("Actual")) || (text.trim().equalsIgnoreCase("Objective/Acceptance Criteria of script:"))){
					cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				}
				if(text.trim().equalsIgnoreCase("")){
					cell.setMinimumHeight(10f);
				}
				table.addCell(cell);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public  void createHeader(PdfPTable table,String Param1,String Param2,String Param9,String Param4,String Param8,Font bfBold12){
			try{
				insertCell(table, Param1, Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, Param2, Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, Param9, Element.ALIGN_CENTER, 1, bfBold12);/* Test Method*/
				insertCell(table, Param4, Element.ALIGN_CENTER, 1, bfBold12);
				//insertCell(table, Param6, Element.ALIGN_CENTER, 1, bfBold12);
				if(Param8 != ""){
					insertCell(table, Param8, Element.ALIGN_CENTER, 1, bfBold12);
				}
				//insertCell(table, Param7, Element.ALIGN_CENTER, 1, bfBold12);
				table.setHeaderRows(1);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public  void createHeader2(PdfPTable table,String Param1,String Param2,String Param4,String Param6,String Param7,String Param8,Font bfBold12){
			try{
				insertCell(table, Param1, Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, Param2, Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, Param4, Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, Param6, Element.ALIGN_CENTER, 1, bfBold12);
				if(Param8 != ""){
					insertCell(table, Param8, Element.ALIGN_CENTER, 1, bfBold12);
				}
				insertCell(table, Param7, Element.ALIGN_CENTER, 1, bfBold12);
				table.setHeaderRows(1);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	
		public  void createHeader1(PdfPTable table,String Param1,String Param2,String Param3,String Param4,Font bfBold12){
			try{
				insertCell(table, Param1, Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, Param2, Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, Param3, Element.ALIGN_CENTER, 1, bfBold12);
				insertCell(table, Param4, Element.ALIGN_CENTER, 1, bfBold12);
				table.setHeaderRows(1);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	
		public  void AddImagetoPDF(int indentatin,String imagePath,Document doc, int m) throws DocumentException, MalformedURLException, IOException{
			try{
				Image img = Image.getInstance(imagePath);
				if(img != null){
					float scaler = ((doc.getPageSize().getWidth() - doc.leftMargin()
							- doc.rightMargin() - indentatin) / img.getWidth()) * 100;
					img.scalePercent(scaler);
					if((stepDescription.get(m-1) != "") && (stepExpected.get(m-1) != "") && (stepActual.get(m-1) != "")){
						createParagraph("Screen Attachment: Step No "+Integer.toString(iter));
						doc.add(paragraph);
						doc.add(img);
					}else{
						doc.newPage();
						doc.add(new Paragraph(" "));
						doc.add(new Paragraph(" "));
						doc.add(new Paragraph(" "));
						doc.add(img);
						l++;
					}
				}
			}catch(Exception e){
			}
		}
	
		public void captureTestCaseStartTime()
		{
			testCaseStartTime = System.currentTimeMillis();
			strCaseStartTime = sdf.format(new Date());
			testCaseStartTimeList.add(strCaseStartTime);
			testCaseStartTimeList1.add(strCaseStartTime);
			testStepTempTime = testCaseStartTime;
		}
	
		public void captureTestCaseEndTime()
		{
			testCaseStopTime = System.currentTimeMillis();
			strCaseStopTime = sdf.format(new Date());
			testCaseEndTimeList.add(strCaseStopTime);
			testCaseEndTimeList1.add(strCaseStopTime);
			calculateElapsedTime();
		}
	
		private  void calculateElapsedTime(){
			testCaseElapsedTime = testCaseStopTime - testCaseStartTime;
			int i = 0;
			int j = (int)(testCaseElapsedTime / 1000L);
			String str = String.valueOf(j);
			if (j >= 60)
			{
				i = j / 60;
				j %= 60;
				str = String.valueOf(j);
			}
			if (j <= 9)
				str = "0" + String.valueOf(j);
			testCaseElapsedTimeList.add(String.valueOf(i) + ":" + str);
		}
	
		public void captureSnapShot(WebDriver paramWebDriver) {
			try{
				File localFile = (File)((TakesScreenshot)paramWebDriver).getScreenshotAs(OutputType.FILE);
				String str1 = "SnapShot_" + DATE_FORMAT_NOW.format(new Date()) + ".png";
				String str2 = System.getProperty("user.dir").replace("\\", "/")+"/TestReports/Snapshots/" + str1;
				
			//	FileUtils.copyFile(localFile, new File(str2));
				snapShotPathList.add(str1);
				str1 = null;
				str2 = null;
				localFile = null;
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		} 
	
		public void writeChartToPDF(PdfWriter docWriter) throws Exception, DocumentException {  
			System.out.println("excelReport.testData.get(ScriptExecution) :"+testData.get("ScriptExecution"));
			try {
				if(!testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
					DefaultPieDataset pieDataset = new DefaultPieDataset();
					pieDataset.setValue("PASS:"+passCount.size(), passCount.size());
					pieDataset.setValue("FAIL:"+failCount.size(), failCount.size());
					JFreeChart chart1 = ChartFactory.createPieChart("Option click count", pieDataset, true, false, false);
	
					final PiePlot plot = (PiePlot) chart1.getPlot();
					plot.setSectionPaint("PASS:"+passCount.size(), Color.GREEN);
					plot.setSectionPaint("FAIL:"+failCount.size(), Color.RED);
					chart1.setTitle(new TextTitle("Result Report", new java.awt.Font("Times New Roman", Font.BOLD, 12)));
	
					PdfContentByte pdfContentByte = docWriter.getDirectContent();
					PdfTemplate pdfTemplateChartHolder = pdfContentByte.createTemplate(310,225);
					Graphics2D graphicsChart = pdfTemplateChartHolder.createGraphics(310,225,new DefaultFontMapper());
					java.awt.geom.Rectangle2D chartRegion =new java.awt.geom.Rectangle2D.Double(0,0,310,225);
					chart1.draw(graphicsChart,chartRegion);
					graphicsChart.dispose();
					pdfContentByte.addTemplate(pdfTemplateChartHolder,150,300);  //60
					//pdfContentByte.addTemplate(pdfTemplateChartHolder,150,300);
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			doc.newPage();
		} 
	
	
		public  String sysTime(){
			Calendar cal = Calendar.getInstance();
			String time = sdf.format(cal.getTime());
			cal.clear();
			return time;
		}
	
		public  String sysDate(){
			Calendar cal = Calendar.getInstance();
			String date = sdf1.format(cal.getTime());
			cal.clear();
			return date;
		}
	
		public void fullScreenCapture(String Parameter,String Parameter1) throws Exception{
            try {
          Robot robot = new Robot();
          String format = "png";
          String fileName = "SnapShot_" + DATE_FORMAT_NOW.format(new Date()) + ".png";
         Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
          BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
          if(((Parameter == "Y") || (Parameter == "N")) && (Parameter1.equalsIgnoreCase("Fail"))){
           File f=((TakesScreenshot)ThreadLocalWebdriver.getDriver()).getScreenshotAs(OutputType.FILE);
           FileUtils.copyFile(f, new File(snapShotFolder+"//"+fileName));
         //    ImageIO.write(screenFullImage, format, new File(snapShotFolder+"//"+ fileName));
              snapShotPathList.add(fileName);
              screenShotPath=snapShotFolder+"/"+fileName;
              ScreenShots.add(screenShotPath);
          }else if((Parameter == "Y") && (Parameter1.equalsIgnoreCase("Pass"))){
         // ImageIO.write(screenFullImage, format, new File(snapShotFolder+"//"+ fileName));
            File f=((TakesScreenshot)ThreadLocalWebdriver.getDriver()).getScreenshotAs(OutputType.FILE);
              FileUtils.copyFile(f, new File(snapShotFolder+"/"+fileName));
              
             snapShotPathList.add(fileName);
             screenShotPath=snapShotFolder+"/"+fileName;
             ScreenShots.add(screenShotPath);
          }else{
          snapShotPathList.add("");
          }
      } catch (IOException ex) {
          System.err.println(ex);
      }
     }
	
		public static void generateSuiteResultFolder()
		{
			File localFile = new File(System.getProperty("user.dir").replace("\\", "/") + "/" + "TestReports");
			System.out.println("localFile ::::::::"+localFile);
			if (!localFile.exists())
				localFile.mkdir();
			suiteFolder = new File(localFile + "/" + suiteName + "_" + DATE_FORMAT_NOW.format(new Date()));
			System.out.println("suiteFolder ::::::::"+suiteFolder);
			if (!suiteFolder.exists())
				suiteFolder.mkdir();
			testFolder = new File(suiteFolder + "/" + testCaseReportsFolder);
			snapShotFolder = new File(suiteFolder + "/" + snapShotsFolder);
			if (!testFolder.exists())
				testFolder.mkdir();
			if (!snapShotFolder.exists())
				snapShotFolder.mkdir();
			suiteFile = new File(suiteFolder + "/" + suiteName + ".html");
			suiteFilePath = suiteName + ".html";
			System.out.println("suiteFilePath   ----"+suiteFilePath);
		}
	
		public void DefaultReport(String pdfFilename){
			try{
				document = new Document(PageSize.A4);
				DecimalFormat df1 = new DecimalFormat("0.00");
				String path = suiteFolder+"/"+pdfFilename+".pdf";
				System.out.println("Path in DefaultReport : : : "+path);
				docWriter1 = PdfWriter.getInstance(document , new FileOutputStream(path));
				document.open();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	
		/*public void generatePDFSummaryReport1(String paramString1) throws Exception{
			int m;
			System.out.println("paragraph  :::::"+paragraph);
			paragraph.clear();
			try{
				String fileName=System.getProperty("user.dir")+"\\MediaFiles\\Logo.png";     //System.getProperty("user.dir")+"\\Logo.png";
				Image image1 = Image.getInstance(fileName);
				// image1.setAbsolutePosition(1f, 650f);
				document.add(image1);
	
	
	
				document.add(Chunk.NEWLINE);
				paragraph = new Paragraph("SUMMARY REPORT",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.RED));
				paragraph.setAlignment(paragraph.ALIGN_LEFT);
				String s = sdf1.format(new Date());
				System.out.println("testCaseName1.size() :"+testCaseName1.size());
				System.out.println("stepDescription1.size() :"+stepDescription.size());
				for (int k = 0; k < testCaseName1.size(); k++){
					System.out.println("testCaseName1.get(k)  ::::::"+testCaseName1.get(k));
					createTable1();
					createHeader(table,"TC ID","Test case name","Test Method","status","",bfBold12);
					m = k+1;
					for (int j = 0; j < stepDescription1.size(); j++){
						insertCell(table, Integer.toString(m), Element.ALIGN_CENTER, 0, bf12);
						insertCell(table, testCaseName1.get(k), Element.ALIGN_CENTER, 0, bf12);
						insertCell(table, stepDescription1.get(j), Element.ALIGN_CENTER, 0, bf12);
						// insertCell(table, stepDescription1.get(j), Element.ALIGN_CENTER, 0, bf12);
						if(testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
							insertCell(table, " ", Element.ALIGN_CENTER, 0, Fontcolor);
						}else{
							if ((statusMap.get(testCaseName1.get(k)) != null) && (((String)statusMap.get(testCaseName1.get(k))).trim().equalsIgnoreCase("Pass"))){
								insertCell(table, "PASS", Element.ALIGN_CENTER, 0, Fontcolor);
							} else if ((statusMap.get(testCaseName1.get(k)) != null) && (((String)statusMap.get(testCaseName1.get(k))).trim().equalsIgnoreCase("Fail")))
							{
								insertCell(table, "FAIL", Element.ALIGN_CENTER, 0, Fontcolor1);
							}
						}
						// if(testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")){
						// insertCell(table, "", Element.ALIGN_CENTER, 0, bf12);
						// insertCell(table, "", Element.ALIGN_CENTER, 0, bf12);
						// }else{
						// insertCell(table, testCaseStartTimeList1.get(k), Element.ALIGN_CENTER, 0, bf12);
						// insertCell(table, testCaseEndTimeList1.get(k), Element.ALIGN_CENTER, 0, bf12);
						// }
						// insertCell(table, "Objective/Acceptance Criteria of script:", Element.ALIGN_LEFT, 7, bfBold12);
						// insertCell(table, testCaseDesc1.get(k), Element.ALIGN_LEFT, 7, bf12);
						paragraph.add(table);
						document.add(new Paragraph(" "));
						document.add(paragraph);
						paragraph.clear();
					}
				}
	
			}catch(Exception localIOException3){
				localIOException3.printStackTrace();
			}
			finally
			{
				// testCaseNameList.clear();
				// testCaseDescriptionList.clear();
				// testCaseStartTimeList.clear();
				//    testCaseEndTimeList.clear();
				//    testCaseElapsedTimeList.clear();
				//    snapShotPathList.clear();
				//    
				//    testCaseName1.clear();
				//    testCaseDesc1.clear();
				//    testCaseStartTimeList1.clear();
				//    testCaseEndTimeList1.clear();
	
				try
				{
					if (document != null){
						document.close();
					}
					if (docWriter1 != null){
						docWriter1.close();
					}
					paragraph.clear();
				}
				catch (Exception localException)
				{
					localException.printStackTrace();
				}
			}
		}*/
	
		public void generatePDFSummaryReport2(String paramString1) throws Exception {
			int m;
			System.out.println("paragraph  :::::" + paragraph);
			paragraph.clear();
			try {
				String fileName = System.getProperty("user.dir") + "/MediaFiles/logo.png"; // System.getProperty("user.dir")+"\\Logo.png";
				Image image1 = Image.getInstance(fileName);
				// image1.setAbsolutePosition(1f, 650f);
				document.add(image1);
	
				document.add(Chunk.NEWLINE);
				paragraph = new Paragraph("SUMMARY REPORT",
						FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.RED));
				paragraph.setAlignment(paragraph.ALIGN_LEFT);
				String s = sdf1.format(new Date());
				System.out.println("testCaseName1.size() :" + testCaseName1.size());
				for (int k = 0; k < testCaseName1.size(); k++) {
					System.out.println("testCaseName1.get(k)  ::::::" + testCaseName1.get(k));
					createTable();
					createHeader2(table, "TC ID", "Test case name", "status", "Start Time", "End Time", "", bfBold12);
					m = k + 1;
					insertCell(table, Integer.toString(m), Element.ALIGN_CENTER, 0, bf12);
					insertCell(table, testCaseName1.get(k), Element.ALIGN_CENTER, 0, bf12);
					if (testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")) {
						insertCell(table, " ", Element.ALIGN_CENTER, 0, Fontcolor);
					} else {
						if ((statusMap.get(testCaseName1.get(k)) != null)
								&& (((String) statusMap.get(testCaseName1.get(k))).trim().equalsIgnoreCase("Pass"))) {
							insertCell(table, "PASS", Element.ALIGN_CENTER, 0, Fontcolor);
						} else if ((statusMap.get(testCaseName1.get(k)) != null)
								&& (((String) statusMap.get(testCaseName1.get(k))).trim().equalsIgnoreCase("Fail"))) {
							insertCell(table, "FAIL", Element.ALIGN_CENTER, 0, Fontcolor1);
						}
					}
	
					if (testData.get("ScriptExecution").trim().equalsIgnoreCase("Pre Execution Report")) {
						insertCell(table, "", Element.ALIGN_CENTER, 0, bf12);
						insertCell(table, "", Element.ALIGN_CENTER, 0, bf12);
					} else {
						insertCell(table, testCaseStartTimeList1.get(k), Element.ALIGN_CENTER, 0, bf12);
						insertCell(table, testCaseEndTimeList1.get(k), Element.ALIGN_CENTER, 0, bf12);
					}
					insertCell(table, "Objective/Acceptance Criteria of script:", Element.ALIGN_LEFT, 5, bfBold12);
					//insertCell(table, "Current browser is :"+Listener.browsername.get(k), Element.ALIGN_LEFT, 5, bfBold12);
					insertCell(table, testCaseDesc1.get(k), Element.ALIGN_LEFT, 5, bf12);
					paragraph.add(table);
					document.add(new Paragraph(" "));
					document.add(paragraph);
					paragraph.clear();
				}
	
			} catch (Exception localIOException3) {
				localIOException3.printStackTrace();
			}

	
			finally {
				/*testCaseNameList.clear();
				testCaseDescriptionList.clear();
				testCaseStartTimeList.clear();
				testCaseEndTimeList.clear();
				testCaseElapsedTimeList.clear();
				snapShotPathList.clear();
	
				testCaseName1.clear();
				testFullName.clear();
				testCaseDesc1.clear();
				testCaseStartTimeList1.clear();
				testCaseEndTimeList1.clear();*/
	
				try {
					if (document != null) {
						document.close();
					}
					if (docWriter1 != null) {
						docWriter1.close();
					}
					paragraph.clear();
				} catch (Exception localException) {
					localException.printStackTrace();
				}
			}
		}
	
		public  void readTestDataFile(String filePath, String tcID) throws Exception{
			int s = 0;
			try{
				FileInputStream testRunReport = new FileInputStream(filePath);
				Workbook workbook ;
				Sheet  worksheet;
				int n=filePath.indexOf(".");
				String extension=filePath.substring(n);
				if (extension.equalsIgnoreCase(".xlsx"))
				{
					workbook=new XSSFWorkbook(testRunReport);
				} else {
					workbook=new HSSFWorkbook(testRunReport);
	
				}
				int sheets=workbook.getNumberOfSheets();
				System.out.println("sheets "+sheets);
				int j=0;
				List<String> arrayOfString = new ArrayList<String>();;
				// for(s=0; s<sheets; s++ ){
				// XSSFSheet worksheet = workbook.getSheetAt(s);
				String str=workbook.getSheetName(0);
				System.out.println(str);
				if(str.contains("PreCondition"))
				{
					worksheet=workbook.getSheet(str);
					Row Headertrow = worksheet.getRow(0);
					// for(int i=1;i<=totalrows;i++){
					Row currentrowww = worksheet.getRow(1);
					int columns=currentrowww.getLastCellNum();
					for (int k = 0; k < columns; k++) { 
						if(currentrowww !=null){
							Cell cellkey = Headertrow.getCell(k);
							System.out.println("cellkey "+cellkey); 
							Cell cellValue = currentrowww.getCell(k);
							System.out.println("cellValue "+cellValue);
							System.out.println("-----------------------------");
							if(cellkey.toString() != ""){
								if(!arrayOfString.contains(cellkey.toString())){
									arrayOfString.add(cellkey.toString());
									testData.put(cellkey.toString(), cellValue.toString());
								}else{
									System.out.println("Duplicate Key In Test Data File : "+cellkey.toString());
									throw new AutomationException("Duplicate Key In Test Data File : "+cellkey.toString());
								}
							}else{
								System.out.println("Row is empty in Test Data file : ");
							}
						}
					}
					//}
				} 
				String str1=workbook.getSheetName(1); 
				System.out.println(str1);
				if(str1.contains("data"))
				{
					worksheet=workbook.getSheet(str1);
					Row Headertrow = worksheet.getRow(0);
					// int totalrows = worksheet.getLastRowNum()-worksheet.getFirstRowNum();
					int startValue=Integer.parseInt(tcID);
					int endValue=startValue; 
					for(int i=startValue; i<=endValue;i++){
						Row currentrow = worksheet.getRow(i);
						int columns=currentrow.getLastCellNum();
						// if(currentrow.getCell(1).getStringCellValue().equalsIgnoreCase(BaseClass.tcno)){ 
						for (int k = 0; k < columns; k++) { 
							if(currentrow !=null){
								Cell cellkey = Headertrow.getCell(k);
								System.out.println("cellkey "+cellkey); 
								Cell cellValue = currentrow.getCell(k);
								System.out.println("cellValue "+cellValue);
								System.out.println("-----------------------------");
								if(cellkey.toString() != ""){
									if(!arrayOfString.contains(cellkey.toString())){
										arrayOfString.add(cellkey.toString());
										testData.put(cellkey.toString(), cellValue.toString());
										testDataValue.put(cellkey.toString(), cellValue.toString());
									}else{
										System.out.println("Duplicate Key In Test Data File : "+cellkey.toString());
										throw new AutomationException("Duplicate Key In Test Data File : "+cellkey.toString());
									}
								}else{
									System.out.println("Row is empty in Test Data file : "+(i+1));
								}
							}
						}
						//}
					}
				}
			}catch (IOException localIOException){
				System.out.println("Unable to read test data file : " + s);
			}
			catch (Exception localAutomationException){
				throw localAutomationException;
			}  
		}}
	
