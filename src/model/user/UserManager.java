package model.user;
import model.DataBase.SaveLoginInfo;

public class UserManager {
    SaveLoginInfo s = new SaveLoginInfo();
    public void createUser(User user){
        SaveLoginInfo.Repository.getUsers().add(user);
    }
}


