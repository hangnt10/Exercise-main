package Functions;

import Utilities.Client;

public class HiritOrGood {
    Client client;
    public static final String imgPath = System.getProperty("user.dir") + "\\Image\\";

    public HiritOrGood(Client client) {
        this.client = client;
    }

    public void clickHirit() throws Exception{
            this.client.window.wait(imgPath + "hirit.png", Double.POSITIVE_INFINITY);
            this.client.window.click(imgPath + "hirit.png");
    }

    public void clickGood() throws Exception{
        this.client.window.wait(imgPath + "good.png", Double.POSITIVE_INFINITY);
        this.client.window.click(imgPath + "good.png");
    }
}
