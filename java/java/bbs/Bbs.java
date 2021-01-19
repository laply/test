package bbs;

public class Bbs {
    private int bbsID;
    private String bbsTitle;
    private String bbsDate;
    private String bbsWriter;
    private String bbsPassword;
    private String bbsContent;
    private String bbsPrivate;
    private int bbsAvailable;


    public void setBbsID(int bbsID){
        this.bbsID = bbsID;
    }

    public int getBbsID(){
        return bbsID;
    }

    public void setBbsTitle(String bbsTitle){
        this.bbsTitle = bbsTitle;
    }
    
    public String getBbsTitle(){
        return bbsTitle;
    }

    public void setBbsDate(String bbsDate){
        this.bbsDate = bbsDate;
    }
    
    public String getBbsDate(){
        return bbsDate;
    }

    public void setBbsWriter(String bbsWriter){
        this.bbsWriter = bbsWriter;
    }
    
    public String getBbsWriter(){
        return bbsWriter;
    }

    public void setBbsPassword(String bbsPassword){
        this.bbsPassword = bbsPassword;
    }
    
    public String getBbsPassword(){
        return bbsPassword;
    }

    public void setBbsContent(String bbsContent){
        this.bbsContent = bbsContent;
    }
    
    public String getBbsContent(){
        return bbsContent;
    }

    public void setBbsPrivate(String bbsPrivate){
        this.bbsPrivate = bbsPrivate;
    }
    
    public String getBbsPrivate(){
        return bbsPrivate;
    }

    public void setBbsAvailable(int bbsAvailable){
        this.bbsAvailable = bbsAvailable;
    }
    
    public int getBbsAvailable(){
        return bbsAvailable;
    }
}
