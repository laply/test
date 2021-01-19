package user;

public class User {

    private String userID;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userPhone;

    public void setUserID(String userID){
        this.userID = userID;
    }

    public String getUserID(){
        return userID;
    }

    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }
    
    public String getUserPassword(){
        return userPassword;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public String getUserName(){
        return userName;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }
    
    public String getUserEmail(){
        return userEmail;
    }    
    
    public void setUserPhone(String userPhone){
        this.userPhone = userPhone;
    }
    
    public String getUserPhone(){
        return userPhone;
    }
}