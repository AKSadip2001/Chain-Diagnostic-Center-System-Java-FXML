package users;

import java.io.Serializable;


public abstract class userclass implements Serializable{
    protected int id;
    protected String password;

    public userclass() {
    }

    public userclass(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
    public boolean verifyUser(int x, String pass){
        if(this.id==x){
            if(this.password.equals(pass)){
                return true;
            }
        }
        return false;
    }
    
}
