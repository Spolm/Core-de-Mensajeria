package Entities.M07_Template;

import javax.ws.rs.FormParam;

public class NewParameter {
    private String name;
    private int companyId;

    public NewParameter() {
    }

    public NewParameter(String name, int companyId) {
        this.name = name;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
