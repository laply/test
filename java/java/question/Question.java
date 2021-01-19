package question;

public class Question {

    private int questionID;
    private String questionName;
    private String questionDate;
    private String questionEmail;
    private String questionPhone;
    private String questionContents;
    private int questionAvailable;
    private String questionSelect;
    private String questionPrice;
    private String questionCompany;
    private int questionAgree;

    public void setQuestionID(int id) {
        this.questionID = id;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionName(String name) {
        this.questionName = name;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionEmail(String email) {
        this.questionEmail = email;
    }

    public String getQuestionEmail() {
        return questionEmail;
    }

    public void setQuestionPhone(String phone) {
        this.questionPhone = phone;
    }

    public String getQuestionPhone() {
        return questionPhone;
    }

    public void setQuestionContents(String contents) {
        this.questionContents = contents;
    }

    public String getQuestionContents() {
        return questionContents;
    }

    public void setQuestionAvailable(int questionAvailable) {
        this.questionAvailable = questionAvailable;
    }

    public int getQuestionAvailable() {
        return questionAvailable;
    }

    // 새로 추가된 부분
    public void setQuestionSelect(String questionSelect) {
        this.questionSelect = questionSelect;
    }

    public String getQuestionSelect() {
        return questionSelect;
    }

    public void setQuestionPrice(String questionPrice) {
        this.questionPrice = questionPrice;
    }

    public String getQuestionPrice() {
        return questionPrice;
    }

    public void setQuestionCompany(String questionCompany) {
        this.questionCompany = questionCompany;
    }

    public String getQuestionCompany() {
        return questionCompany;
    }

    public void setQuestionAgree(int questionAgree) {
        this.questionAgree = questionAgree;
    }

    public int getQuestionAgree() {
        return questionAgree;
    }

}