package entities;

import java.util.Date;

/**
 *
 * @author jrady
 */

public class Billet {

    private int id;
    private String type;
    private String prix;


    public Billet(){
    }
    public Billet(int id, String type, String prix) {
        this.id = id;
        this.type = type;
        this.prix = prix;

    }
    public Billet(String type, String prix) {

        this.id = id;
        this.type = type;
        this.prix = prix;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }









    @Override
    public String toString() {
        return "Billet{" + "id=" + id + ", type=" + type + ", prix=" + prix + '}';
    }


}
