package Excel;

import TestCase.TestCase;
import TestCase.Step;
import Utilities.Client;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import TestCase.General;

public class ImportEcxel {
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String fileName) {
        this.filePath = System.getProperty("user.dir") + "\\Data\\" + fileName;
    }

    public ImportEcxel(String fileName) throws IOException {
        this.filePath = System.getProperty("user.dir") + "\\Data\\" + fileName;
    }

    //Lấy dữ liệu trang đầu tiên
    public ArrayList<General> getGeneral() throws IOException {
        ArrayList<General> generalList = new ArrayList<General>();
        String testcaseName = "";
        String status = "";

        FileInputStream inputStream = new FileInputStream(new File(filePath));

        XSSFWorkbook excelFile = new XSSFWorkbook(inputStream);

        XSSFSheet generalSheet = excelFile.getSheet("General");
        for (int i = 0; i < generalSheet.getLastRowNum(); i++) {
            General general = new General(testcaseName, status);
            if(generalSheet.getRow(i + 1).getCell(0) != null){
                testcaseName = generalSheet.getRow(i + 1).getCell(0).toString();
                if(generalSheet.getRow(i + 1).getCell(1) != null){
                    status = generalSheet.getRow(i + 1).getCell(1).toString();
                }
            }
            general.setTestcaseName(testcaseName);
            general.setStatus(status);
            generalList.add(general);
        }
        inputStream.close();
        excelFile.close();
        return generalList;
    }

    //lấy dữ liệu từng testcase
    public ArrayList<TestCase> getTestCase() throws IOException {
        String name = "Hằng";
        String status = "";
        int numberClient;
        String result = "";
        String client = "";
        String stt;

        String action = "";
        String parameter = "";

        ArrayList<TestCase> testcases = new ArrayList<TestCase>();

        FileInputStream inputStream = new FileInputStream(new File(filePath));

        XSSFWorkbook excelFile = new XSSFWorkbook(inputStream);

        ArrayList<General> general = this.getGeneral();
        for (int i = 0; i < general.size(); i++) {
            if (general.get(i).getStatus().equals("test")) {
                XSSFSheet tc = excelFile.getSheet(general.get(i).getTestcaseName());
                name = tc.getRow(0).getCell(1).toString();
                numberClient = (int) tc.getRow(1).getCell(1).getNumericCellValue();
                TestCase testcase = new TestCase(name, status);
                testcase.setName(name);
                testcase.setNumberClient(numberClient);
                ArrayList<Step> steps = new ArrayList<Step>();
                int index = 11;
                while (index < tc.getLastRowNum()) {
                    Step step = new Step();
                    if(tc.getRow(index).getCell(0) != null){
                        stt = tc.getRow(index).getCell(0).toString().split("\\.")[0].replaceAll("\\,","");
                        step.setOrder(stt);
                        if (tc.getRow(index).getCell(1) != null) {
                            client = tc.getRow(index).getCell(1).toString();
                            step.setRolePlay(client);
                            if (tc.getRow(index).getCell(2) != null) {
                                action = tc.getRow(index).getCell(2).toString();
                                step.setAction(action);
                                if (tc.getRow(index).getCell(3) != null) {
                                    parameter = tc.getRow(index).getCell(3).toString();
                                    step.setParameter(parameter.split("\\.")[0].replaceAll("\\,",""));
                                }
                                steps.add(step);
                            }
                        }
                    }
                    index++;
                }
                testcase.setSteps(steps);
                testcases.add(testcase);
            }
        }
        inputStream.close();
        excelFile.close();
        return testcases;
    }

    public void exportResult(TestCase testCase) throws Exception{
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);

        // Đối tượng workbook cho file XSL
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // Lấy ra sheet đầu tiên từ workbook
        XSSFSheet sheet = workbook.getSheet(testCase.getName());
        XSSFCell cell = sheet.getRow(3).getCell(1);
        cell.setCellValue(testCase.getResult());

        inputStream.close();

        // Ghi file
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
    }
}
