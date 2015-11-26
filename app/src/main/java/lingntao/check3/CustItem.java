package lingntao.check3;

import android.net.Uri;

public class CustItem {
    private Uri imageId;
    private String date;
    private int idform;
    private int totscore;
    private String SD;
    private String store;
    private String user;
    private String sect;
    private String prob;
    private String note;
    private String idnote;

    public CustItem(Uri con_img, String con_user, String con_title, String con_sect, String con_prob, String con_note, String con_idnote) {
        this.imageId = con_img;
        this.user=con_user;
        this.date = con_title;
        this.sect=con_sect;
        this.prob=con_prob;
        this.note = con_note;
        this.idnote = con_idnote;
    }

    public CustItem(String con_user, String con_title, String con_sect, String con_prob, String con_note, String con_idnote) {
        this.user=con_user;
        this.date = con_title;
        this.sect=con_sect;
        this.prob=con_prob;
        this.note = con_note;
        this.idnote = con_idnote;
    }

    public CustItem(int con_idform, String con_SD, String con_user, String con_store, int con_totscore){
        this.idform = con_idform;
        this.SD=con_SD;
        this.user=con_user;
        this.store=con_store;
        this.totscore=con_totscore;
    }

    public Uri getImageId(){return imageId;}
    public void setImageId(Uri imageId){this.imageId = imageId;}

    public String getUser(){return user;}
    public void setUser(String in_user){this.user = in_user;}

    public String getDate(){return date;}
    public void setDate(String in_title){this.date = in_title;}

    public String getSect(){return sect;}
    public void setSect(String in_sect){this.sect = in_sect;}

    public String getProb(){return prob;}
    public void setProb(String in_prob){this.prob = in_prob;}

    public String getNote(){return note;}
    public void setNote(String in_note){this.note = in_note;}

    public String getSD(){return SD;}
    public void setSD(String in_SD){this.SD = in_SD;}

    public String getStore(){return store;}
    public void setStore(String in_store){this.store = in_store;}

    public int getTotscore(){return totscore;}
    public void setTotscore(int in_totscore){this.totscore = in_totscore;}

    public int getIdform(){return idform;}
    public void setIdform(int in_form){this.idform = in_form;}

    public String getIdnote(){return idnote;}
    public void setIdnote(String in_idnote){this.note = in_idnote;}

    @Override
    public String toString(){return date + "\n" + note;}

}