package sample.model;

public class Project
{
    private int pId;
    private String pName, pDesc, pStartDate,pDeadline;

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getpStartDate() {
        return pStartDate;
    }

    public void setpStartDate(String pStartDate) {
        this.pStartDate = pStartDate;
    }

    public String getpDeadline() {
        return pDeadline;
    }

    public void setpDeadline(String pDeadline) {
        this.pDeadline = pDeadline;
    }
}
