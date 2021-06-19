package Utilities;

import org.apache.commons.math3.analysis.function.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

public class AdbLog {

    public String adbAddress;
    private String[] commands;

    public AdbLog(String adbAddress) {
        this.adbAddress = adbAddress;
        this.commands = new String[]{"adb", "-s", adbAddress, "logcat", "-t", "50000"};
    }

    private String getAdbLog() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(this.commands);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String log = "";
        assert process != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            log += line;
            log += "\n";
        }

        log = log.substring(log.lastIndexOf("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"), log.lastIndexOf(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"));


        return log;
    }


    public JSONObject getUserInfo() {
        String log = getAdbLog();
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(log.split("\n")));
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).contains("QC_user_info_QC")) {
                lines.remove(i);
                i--;
            }
        }
        String userInfo = "";

        for (String s : lines) {
            userInfo += s.split("    ")[3];
        }
        System.out.println(userInfo);

        return new JSONObject(userInfo);
    }


    public JSONArray getPositionLog() {
        String log = getAdbLog();
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(log.split("\n")));
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).contains("QC_position_data_gui_QC")) {
                lines.remove(i);
                i--;
            }
        }

        String positionLog = "";

        for (String s : lines) {
            String[] parts = s.split("    ");
            positionLog += parts[3];
        }

        System.out.println(positionLog);

        return new JSONArray(positionLog);
    }

    public String getLocalIpAddress() throws SocketException {
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
            NetworkInterface intf = en.nextElement();
            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                InetAddress inetAddress = enumIpAddr.nextElement();
                if (!inetAddress.isLoopbackAddress()) {
                    return inetAddress.getHostAddress().toString();
                }
            }
        }

        return null;
    }

}


