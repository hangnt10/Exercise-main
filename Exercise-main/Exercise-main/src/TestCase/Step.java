package TestCase;



import java.io.FileWriter;

public class Step {
    private String rolePlay;
    private String action;
    private String parameter;
    private String order;

    public Step() {
    }

    public Step(String rolePlay, String action, String parameter, String order) {
        this.rolePlay = rolePlay;
        this.action = action;
        this.parameter = parameter;
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getRolePlay() {
        return rolePlay;
    }

    public void setRolePlay(String rolePlay) {
        this.rolePlay = rolePlay;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }


}
