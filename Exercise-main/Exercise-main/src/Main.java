import Excel.ImportEcxel;
import Functions.Channel;
import TestCase.General;
import TestCase.TestCase;
import TestCase.Step;
import Utilities.*;
import org.sikuli.android.ADBTest;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws Exception {
//        int index = 46; // giá trị dòng thứ i tùy ý cần test trong bảng excel, chọn i > 1
//        String code = getCodeFromExcel(index - 1);
//
//        Client client1 = new Client("Client1");
//        client1.login("1300");
//        client1.enterGiftCode_directly("code"); // chọn trực tiếp icon GiftCode tại màn hình lobby
////        client1.enterGiftCode_settings(code); // chọn GiftCode tại màn hình Settings

//        ZPCheat cheat = new ZPCheat(); // khởi tạo tool cheat
//        ZPCheat.cheatData(2); // nhận tham số là test case id cần cheat
//
//        Bet bet = new Bet(client1);
//        bet.clickBet();
//        bet.clickChannel("manila");
        ImportEcxel excelFile = new ImportEcxel("TestCase_ChiaKenh.xlsx");
        ArrayList<General> general = excelFile.getGeneral();
        ArrayList<TestCase> tc = excelFile.getTestCase();
        for (TestCase item : tc) {
            System.out.println(item.getName() + " " + item.getNumberClient());
            String h = " ";
//            for (Step s : item.getSteps())
//                h += item.generateTestScript(s);
            h+= item.generateAllScript(item.getSteps()) ;
            item.writer(h);
        }

        // run test
//        ZPCheat cheat = new ZPCheat(); // khởi tạo tool cheat
//        ZPCheat.cheatData(31); // nhận tham số là test case id cần cheat

//        Client client1 = new Client("Client1");
//        Method method = Client.class.getDeclaredMethod("openGame", null);
//        method.setAccessible(true);
//        method.invoke(client1, null);
//
//        Method method1 = Client.class.getDeclaredMethod("login", String.class);
//        method1.setAccessible(true);
//        method1.invoke(client1, "100");
//
//        Method method2 = Bet.class.getDeclaredMethod("clickBet", null);
//        method2.setAccessible(true);
//        method2.invoke(client1.bet, null);
//
//        Method method3 = Bet.class.getDeclaredMethod("clickChannel", String.class);
//        method3.setAccessible(true);
//        method3.invoke(client1.bet,"manila");
//
//        Method method4 = ZPCheat.class.getDeclaredMethod("closeWeb",null);
//        method4.setAccessible(true);
//        method4.invoke(cheat,null);
//        Method method5 = Client.class.getDeclaredMethod("closeGame", null);
//        method5.setAccessible(true);
//        method5.invoke(client1, null);
    }

}

