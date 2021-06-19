package TestCase;


import Excel.ImportEcxel;
import Utilities.Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class TestCase {
    private String name;
    private String status;
    private int numberClient;
    private int order;
    private String result;
    private ArrayList<Step> steps;


    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public TestCase(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberClient() {
        return numberClient;
    }

    public void setNumberClient(int numberClient) {
        this.numberClient = numberClient;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String generateTestScript(Step step) throws Exception {
        String testScript = " ";
        if(step.getOrder() != null){
            String stt = step.getOrder().toString().split("\\.")[0].replaceAll("\\,", "");
            if(step.getRolePlay()!= null){
                String client = step.getRolePlay().toString();
                if(step.getAction()!=null){
                    String action = step.getAction().toString();
                    if(step.getParameter()!=null){
                        String para = step.getParameter().toString().split("\\.")[0].replaceAll("\\,", "");
                        if (action.equalsIgnoreCase("openGame")) {
                            testScript = testScript.concat("       Method method" + stt + " = Client.class.getDeclaredMethod(\"openGame\", null);\n" +
                                    "        method" + stt + ".setAccessible(true);\n" +
                                    "        method" + stt + ".invoke(" + client + ", null);\n\n");
                        } else if (action.equalsIgnoreCase("login")) {
                            testScript = testScript.concat("       Method method" + stt + " = Client.class.getDeclaredMethod(\"login\", String.class);\n" +
                                    "        method" + stt + ".setAccessible(true);\n" +
                                    "        method" + stt + ".invoke(" + client + ", \"" + para + "\");\n\n");
                        } else if (action.equalsIgnoreCase("clickBet")) {
                            testScript = testScript.concat("       Method method" + stt + " = Channel.class.getDeclaredMethod(\"clickBet\", null);\n" +
                                    "        method" + stt + ".setAccessible(true);\n" +
                                    "        method" + stt + ".invoke(" + client + ".channel, " + "null);\n\n");
                        } else if (action.equalsIgnoreCase("clickChannel")) {
                            testScript = testScript.concat("       Method method" + stt + " = Channel.class.getDeclaredMethod(\"clickChannel\", String.class);\n" +
                                    "        method" + stt + ".setAccessible(true);\n" +
                                    "        method" + stt + ".invoke(" + client + ".channel, \"" + para + "\");\n\n");
                        }else if (action.equalsIgnoreCase("closeWeb")) {
                            testScript = testScript.concat("       Method method" + stt + " = ZPCheat.class.getDeclaredMethod(\"closeWeb\", null);\n" +
                                    "        method" + stt + ".setAccessible(true);\n" +
                                    "        method" + stt + ".invoke(cheat, null);\n\n");
                        }else if (action.equalsIgnoreCase("closeGame")) {
                            testScript = testScript.concat("       Method method" + stt + " = Client.class.getDeclaredMethod(\"closeGame\", null);\n" +
                                    "        method" + stt + ".setAccessible(true);\n" +
                                    "        method" + stt + ".invoke(" + client + ", null);\n\n");
                        }
                    }
                }
            }
        }
        return testScript;
    }

    public String generateCheatScript(Step step) throws Exception {
        String cheatScript = " ";
        String action = step.getAction().toString();
        String para = step.getParameter().toString().split("\\.")[0].replaceAll("\\,", "");

        if (action.equalsIgnoreCase("cheatData")) {
            cheatScript = cheatScript.concat("     ZPCheat cheat = new ZPCheat(); \n" +
                    "        ZPCheat.cheatData(" + para + ");\n\n");
        }
        return cheatScript;
    }

    public String generateAllScript(ArrayList<Step> steps) throws Exception {
        String testScripts = " ";
        String Client1 = steps.get(1).getRolePlay().toString();
        System.out.println(Client1);
        testScripts = testScripts.concat(generateCheatScript(steps.get(0)));
        testScripts = testScripts.concat("     Client " + Client1 + " = new Client(\"" + Client1 + "\");\n");
        for (int i = 1; i < steps.size(); i++) {
            if (steps.get(i).getRolePlay().toString() != Client1) {
                testScripts = testScripts.concat("     Client " + steps.get(i).getRolePlay().toString() + " = new Client(\"" + steps.get(i).getRolePlay().toString() + "\");\n");
//                Client1 = steps.get(i).getRolePlay().toString();
            }
            testScripts = testScripts.concat(generateTestScript(steps.get(i)));

        }
        return testScripts;
    }

    public void writer(String content) throws Exception {
        FileWriter fw = new FileWriter("src/TestScript/CheckChannel/" + this.getName() + ".java");

        fw.write("package TestScript.CheckChannel;\n");
        String fileHeaderName = "src/TestScript/header";
        File header = new File(fileHeaderName);
        FileReader fr1 = new FileReader(header);
        BufferedReader br1 = new BufferedReader(fr1);
        String line;
        while ((line = br1.readLine()) != null) {
            fw.write(line + "\n");
        }

        fw.write("public class " + this.getName() + "{\n" +
                "    public static void main(String[] args) throws Exception {\n" +
                "        " + this.getName()+ ".runTest();\n" +
                "    }\n" +
                "    public static void runTest() throws Exception {\n");
//        fw.write(content);
        fw.write(content);

        String fileFooterName = "src/TestScript/footer";
        File footer = new File(fileFooterName);
        FileReader fr2 = new FileReader(footer);
        BufferedReader br2 = new BufferedReader(fr2);
        String line2;
        while ((line2 = br2.readLine()) != null) {
            fw.write(line2 + "\n");
        }

        fw.close();
        System.out.println("Success...");
    }

    public String createFullTestScript(ArrayList<TestCase> testCases) throws Exception{
        String newFile = " ";
        for (TestCase item : testCases) {
            System.out.println(item.getName() + " " + item.getNumberClient());

//            for (Step s : item.getSteps())
//                h += item.generateTestScript(s);
            newFile+= item.generateAllScript(item.getSteps()) ;

//                System.out.println(s.getAction());
//            System.out.println("-------");
        }
        return newFile;
    }

}
