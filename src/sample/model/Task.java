package sample.model;

public class Task
{
    private int tId,tPId,tEId;
    private String tName, tDesc, tStartDate, tDeadline, tComment, tStatus;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int gettPId() {
        return tPId;
    }

    public void settPId(int tPId) {
        this.tPId = tPId;
    }

    public int gettEId() {
        return tEId;
    }

    public void settEId(int tEId) {
        this.tEId = tEId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettDesc() {
        return tDesc;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    public String gettStartDate() {
        return tStartDate;
    }

    public void settStartDate(String tStartDate) {
        this.tStartDate = tStartDate;
    }

    public String gettDeadline() {
        return tDeadline;
    }

    public void settDeadline(String tDeadline) {
        this.tDeadline = tDeadline;
    }

    public String gettComment() {
        return tComment;
    }

    public void settComment(String tComment) {
        this.tComment = tComment;
    }

    public String gettStatus() {
        return tStatus;
    }

    public void settStatus(String tStatus) {
        this.tStatus = tStatus;
    }
}
