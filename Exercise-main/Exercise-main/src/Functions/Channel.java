package Functions;

import Utilities.Client;

public class Channel {
    private int userGold;
    Client client;
    public static final String imgPath = System.getProperty("user.dir") + "\\Image\\";

    public Channel(Client client) {
        this.client = client;
    }

    public Channel(int userGold) {
        this.userGold = userGold;
    }

    public int getUserGold() {
        return userGold;
    }

    public void setUserGold(int userGold) {
        this.userGold = userGold;
    }

    public void clickBet() throws Exception {
        this.client.window.wait(imgPath + "bet.png", Double.POSITIVE_INFINITY);
        this.client.window.click(imgPath + "bet.png");
    }

    public void clickChannel(String channel) throws Exception{
        this.client.window.wait(imgPath + channel+".png", Double.POSITIVE_INFINITY);
        this.client.window.click(imgPath + channel+".png");
        if(!(this.client.window.exists(imgPath +"error_channel.png", 3.0) == null)){
            this.client.window.wait(3.0);
            this.client.window.click(imgPath + "error_cancel.png");
        }else if(!(this.client.window.exists(imgPath + "error_channel_2",3.0) == null)){
            this.client.window.click(imgPath + "btn_ok.png");
        } else{
            this.client.window.wait(imgPath + "back.png", Double.POSITIVE_INFINITY);
            this.client.window.click(imgPath + "back.png");
        }
    }

    public String checkResult() throws Exception{
        String result = "pass";
        if(!(this.client.window.exists(imgPath +"error_channel.png", 3.0) == null)
                || !(this.client.window.exists(imgPath + "error_channel_2",3.0) == null) ){
            result = "fail";
        }
        return result;
    }


//    public void clickManila() throws Exception {
//        this.client.window.wait(imgPath + "manila.png", Double.POSITIVE_INFINITY);
//        this.client.window.click(imgPath + "manila.png");
//        if(!(this.client.window.exists(imgPath + "bet_notice.png") == null)){
//            this.client.window.click(imgPath + "btn_ok.png");
//        }
//    }
//
//    public void clickMakati() throws Exception {
//        this.client.window.wait(imgPath + "makati.png", Double.POSITIVE_INFINITY);
//        this.client.window.click(imgPath + "makati.png");
//        if(!(this.client.window.exists(imgPath + "bet_notice.png") == null)){
//            this.client.window.click(imgPath + "btn_ok.png");
//        }
//    }
}
