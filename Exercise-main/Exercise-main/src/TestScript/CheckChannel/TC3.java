package TestScript.CheckChannel;
import Excel.ImportEcxel;
import Functions.Channel;
import TestCase.General;
import TestCase.TestCase;
import TestCase.Step;
import Utilities.*;
import org.sikuli.android.ADBTest;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class TC3{
    public static void main(String[] args) throws Exception {
        TC3.runTest();
    }
    public static void runTest() throws Exception {
        ZPCheat cheat = new ZPCheat(); 
        ZPCheat.cheatData(33);

     Client Client1 = new Client("Client1");
        Method method2 = Client.class.getDeclaredMethod("openGame", null);
        method2.setAccessible(true);
        method2.invoke(Client1, null);

        Method method3 = Client.class.getDeclaredMethod("login", String.class);
        method3.setAccessible(true);
        method3.invoke(Client1, "100");

        Method method4 = Channel.class.getDeclaredMethod("clickBet", null);
        method4.setAccessible(true);
        method4.invoke(Client1.channel, null);

        Method method5 = Channel.class.getDeclaredMethod("clickChannel", String.class);
        method5.setAccessible(true);
        method5.invoke(Client1.channel, "manila");

        Method method6 = Client.class.getDeclaredMethod("closeGame", null);
        method6.setAccessible(true);
        method6.invoke(Client1, null);

        Method method7 = ZPCheat.class.getDeclaredMethod("closeWeb", null);
        method7.setAccessible(true);
        method7.invoke(cheat, null);

    }
}
