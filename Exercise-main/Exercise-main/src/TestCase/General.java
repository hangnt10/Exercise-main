package TestCase;

public class General {
    private String testcaseName;
    private String status;

    public General(String testcaseName, String status) {
        this.testcaseName = testcaseName;
        this.status = status;
    }

    public String getTestcaseName() {
        return testcaseName;
    }

    public void setTestcaseName(String testcaseName) {
        this.testcaseName = testcaseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
