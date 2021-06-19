package TestScript.CheckChannel;

import java.io.*;
import java.util.ArrayList;

public class runAll {
    public static void main(String[] args) {
        String path = "C:\\Users\\LAP60536_Local\\.jdks\\openjdk-16.0.1\\bin\\java.exe \"-javaagent:C:\\Program Files\\JetBrains\\IntelliJ IDEA 2021.1.1\\lib\\idea_rt.jar=62317:C:\\Program Files\\JetBrains\\IntelliJ IDEA 2021.1.1\\bin\" -Dfile.encoding=UTF-8 -classpath C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\out\\production\\Exercise;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\poi-5.0.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\okio-1.14.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\json-20200518.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\okhttp-3.11.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\curvesapi-1.06.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\guava-25.0-jre.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\xmlbeans-4.0.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\poi-ooxml-5.0.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\commons-exec-1.3.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\sikulixapi-2.0.4.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\SparseBitSet-1.2.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\byte-buddy-1.8.15.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\commons-codec-1.15.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\poi-examples-5.0.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\poi-excelant-5.0.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\commons-math3-3.6.1.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\poi-ooxml-full-5.0.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\poi-ooxml-lite-5.0.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\poi-scratchpad-5.0.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\commons-compress-1.20.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\poi-integration-5.0.0.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\poi-ooxml-schemas-3.17.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\client-combined-3.141.59.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\commons-collections4-4.4.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\client-combined-3.141.59-sources.jar;C:\\Users\\LAP60536_Local\\Downloads\\Exercise-main\\Exercise-main\\Lib\\errai-javax-enterprise-3.2.0.CR1.jar TestScript.CheckChannel.";
//        boolean isDone = runAll.executeCommand(path + "TC1");
//        while (!isDone)
//            Thread.sleep(100);
//        runAll.executeCommand(path + "TC2");

        Integer[] totalTC = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
//        Integer[] totalTC = new Integer[]{12};
        for (Integer idx: totalTC) {
            int i = idx.intValue();
            try {
                boolean isDone = runAll.executeCommand(path + "TC" + (i));
                System.out.println("TC" + (i) + "\n");
//            while (!isDone){
//                Thread.sleep(100);
//            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean executeCommand(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            System.out.println("[OK] Execute \"" + command + "\"");
            System.out.println("Output = [" + output + "]");
            return true;
        } else {
            System.out.println("[FAIL] Execute \"" + command + "\"");
            return false;
        }
    }
}
