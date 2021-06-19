package Utilities;

import Functions.Channel;
import Functions.HiritOrGood;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sikuli.script.*;
import org.sikuli.util.Run;

import java.io.IOException;
import java.util.ArrayList;


public class Client {
    public Channel channel;
    public HiritOrGood isGood;
    public String title;
    public float width;
    public float height;

    public App app;
    public Region window;

    public AdbLog adbLog;

    public static final String imgPath = System.getProperty("user.dir") + "\\Image\\";
    public static JSONObject config = Json.read(System.getProperty("user.dir") + "\\Config\\config.json");

    public float screenWidth = config.getFloat("screenWidth");
    public float screenHeight = config.getFloat("screenHeight");

    private static final int topBarHeight = 35;


    /**
     * @param title title of app
     */
    public Client(String title) throws IOException {
        this.title = title;
        this.app = new App(title);
        if (app.window(0) == null) return;
        Region window1 = app.window(0);
        this.window = new Region((window1.x), window1.y + topBarHeight, window1.w - topBarHeight, window1.h - topBarHeight);
        this.width = window.w;
        this.height = window.h;
        this.window.highlight(1);

        String adbAddress = config.getJSONObject(title).getString("adbAddress");
        // adbAddress: khong co dinh
        String[] commands = new String[]{"adb", "-s", adbAddress, "logcat", "-t", "50000"};
        Process process = null;
        if (process != Runtime.getRuntime().exec(commands) && (title == "Client1")) {
            adbAddress = "127.0.0.1:5555";
            commands = new String[]{"adb", "-s", adbAddress, "logcat", "-t", "50000"};
            process = Runtime.getRuntime().exec(commands);
        } else if (process != Runtime.getRuntime().exec(commands) && (title == "Client2")) {
            adbAddress = "127.0.0.1:5557";
            commands = new String[]{"adb", "-s", adbAddress, "logcat", "-t", "50000"};
            process = Runtime.getRuntime().exec(commands);
        }else{
            process = Runtime.getRuntime().exec(commands);
        }

        adbLog = new AdbLog(adbAddress);
        this.channel = new Channel(this);
        this.isGood = new HiritOrGood(this);
    }


    /**
     * Calculate absolute location
     *
     * @param location relative location
     * @return absolute location
     */
    public Location calLocation(Location location) {
        return new Location(location.x + window.x, location.y + window.y);
    }

    private Location convertNodeLocation(Node node) {
        return calLocation(new Location(node.x * width / screenWidth, (screenHeight - node.y) * height / screenHeight));
    }

    public boolean refreshLog() throws Exception {
        window.click(imgPath + "cheatIcon.png");
        Thread.sleep(100);
        return true;
    }


    public JSONObject getUserInfo() throws Exception {
        JSONObject userInfo = null;
        refreshLog();
        try {
            userInfo = adbLog.getUserInfo();
        } catch (JSONException e) {
            System.out.println("JSONException");
            Thread.sleep(500);
            userInfo = getUserInfo();
        } catch (StringIndexOutOfBoundsException e) {
//            e.printStackTrace();
            System.out.println("String index out of bounds exception");
            Thread.sleep(500);
            userInfo = getUserInfo();
        }
        return userInfo;
    }

    public JSONArray getPositionLog() throws Exception {
        app.focus();
        JSONArray positionLog = null;
        refreshLog();
        try {
            positionLog = adbLog.getPositionLog();
        } catch (JSONException e) {
            System.out.println("JSONException");
            Thread.sleep(500);
            positionLog = getPositionLog();
        } catch (StringIndexOutOfBoundsException e) {
//            e.printStackTrace();
            System.out.println("String index out of bounds exception");
            Thread.sleep(500);
            positionLog = getPositionLog();
        }
        return positionLog;
    }


    public void click(ArrayList<String> filter) throws Exception {
        app.focus();
        JSONArray positionLog = getPositionLog();
        JSONObject jsonObject = Json.findJsonObjectByFilter(positionLog, filter);
        assert jsonObject != null;
        Node node = new Node(jsonObject);
        System.out.println("Relative location: " + node.x + " " + node.y);
        this.window.click(convertNodeLocation(node));
    }

    public void click(String name) throws Exception {
        ArrayList<String> filter = new ArrayList<>();
        filter.add("name:" + name);
        click(filter);
    }


    public void type(String text) {
//      window.type(Key.BACKSPACE);
        window.type(text);
    }

    public void openGame() throws Exception {
        window.wait(imgPath + "luckyNineIcon.png", Double.POSITIVE_INFINITY);
        window.click(imgPath + "luckyNineIcon.png");
    }

    public void login(String id) throws Exception {
        window.wait(imgPath + "loginOptions.png", Double.POSITIVE_INFINITY);
        this.click("btn_ZID");
        this.click("sprite_Name");
        this.type(id);
        this.click("btn_Ok");
        this.refreshLog();
    }

    public void closeGame() throws Exception {
        this.window.wait(2);
        Runtime.getRuntime().exec(getBack());
        window.wait(imgPath + "closeGame.png", Double.POSITIVE_INFINITY);
        window.click(imgPath + "closeGame.png");
        window.click(imgPath + "btn_Ok.png");
        this.refreshLog();
    }

    private String getBack() {
        return "adb shell input keyevent 4";
    }

    public void clickGiftCode(String giftCode) throws Exception {
        window.wait(imgPath + "enterGiftcode.png", Double.POSITIVE_INFINITY);
        window.click(imgPath + "enterGiftcode.png");
        window.click(imgPath + "touchinput.png");
        this.type(giftCode);
        window.click(imgPath + "claim.png");
        window.wait(imgPath + "congratScreen.png", Double.POSITIVE_INFINITY);
        window.click(imgPath + "receive.png");
    }

    //chọn trực tiếp icon GiftCode tại màn hình lobby
    public void enterGiftCode_directly(String giftCode) throws Exception {
        window.wait(imgPath + "giftcode.png", Double.POSITIVE_INFINITY);
        this.window.click(imgPath + "giftcode.png");
        clickGiftCode(giftCode);
        this.refreshLog();
    }

    // chọn GiftCode tại màn hình Settings
    public void enterGiftCode_settings(String giftCode) throws Exception {
        window.wait(imgPath + "settings.png", Double.POSITIVE_INFINITY);
        this.window.click(imgPath + "settings.png");
        this.click("btn_GiftCode");
        clickGiftCode(giftCode);
        this.refreshLog();
    }

    public String checkCode(String code) throws Exception {
        String result = "";

        return result;
    }
}
