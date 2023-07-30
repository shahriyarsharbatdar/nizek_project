package ui.mainPage;

import manager.UserManager;
import model.UserRole;

public class AddMemberPanelController {
    //regex for adding member
    AddMemberPanel addMemberPanel = new AddMemberPanel();
    //    private final String nameRegex = "^[A-Za-z]{3,}$";
//    private final String lastNameRegex = "^[A-Za-z]{3,}$";
//    private final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
//    final private Pattern namePattern = Pattern.compile(nameRegex);
//    final private Pattern lastNamePattern = Pattern.compile(lastNameRegex);
//    final private Pattern emailPattern = Pattern.compile(emailRegex);
    UserManager userManager = UserManager.getInstance();
    private static AddMemberPanelController instance = null;

    private AddMemberPanelController() {

    }

    public static AddMemberPanelController getInstance() {
        if (instance == null) {
            instance = new AddMemberPanelController();
        }
        return instance;
    }

    public void addMember(String name, String lastName, String email, String password, UserRole role) {
        userManager.addUser(name, lastName, email, password, role);
    }


}
