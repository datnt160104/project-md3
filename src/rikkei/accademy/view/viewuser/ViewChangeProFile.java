package rikkei.accademy.view.viewuser;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.UserController;
import rikkei.accademy.model.User;
import rikkei.accademy.service.user.IUserService;
import rikkei.accademy.service.user.UserServiceIMPL;

import java.util.List;
import java.util.regex.Pattern;

public class ViewChangeProFile {
    IUserService userService = new UserServiceIMPL();
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();
    User currentUser = userController.grtCurrenUser();
    
    public void menuProFile(){


        System.out.println("╔══════════════════════════ Change Profile ═══════════════════╗\n"+
                "║"+ "                    Email:"+currentUser.getEmail()+"                     ║\n"+
                "║═════════════════════════════════════════════════════════════║\n"+
                "║                      1:  Change name                        ║\n"+
                "║                                                             ║\n"+
                "║                      2:  Change email                       ║\n"+
                "║                                                             ║\n"+
                "║                      3:  Change Password                    ║\n"+
                "║                                                             ║\n"+
                "║                      4:  back                               ║\n"+
                "╚═════════════════════════════════════════════════════════════╝");
        System.out.println("Enter your choice: ");
        int choice = Config.scanner().nextInt();
        switch (choice){
            case 1:
                this.formChangeName();
                break;
            case 2:
                this.formChangeEmail();
                break;
            case 3:
                this.formChangePassword();
                break;
            case 4:
                this.backMenu();
                break;
        }
    }
   public void formChangePassword(){
        User user = userController.grtCurrenUser();
        System.out.println("Enter password");
        String password = Config.scanner().nextLine();
        if (password.equals(user.getPassword()));
        String newPassword;
        boolean validatePassword;
        while (true){
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_YELLOW = "\u001B[33m";
            System.out.println("Enter new password");
//            System.out.println(ANSI_YELLOW + "Password must contain uppercare letters, lowercase letters, numbers and special characters"+ANSI_RESET);
            newPassword = Config.scanner().nextLine();
            validatePassword =  Pattern.matches("[a-zA-Z0-9]{1,40}", newPassword);
            if (validatePassword){
                break;
            }else {
                System.err.println("Password format error! Please enter the correct format!");

            }
        }
        User user1 = new User(user.getId(),user.getName(),user.getUsername(),user.getEmail(),newPassword,user.getRoles());
        userController.upDateProFile(user1);
        System.out.println("Change password Success!!!!!");
        userController.getUserList();



    }
    private void backMenu(){
        new ViewHome();
    }

    private void formChangeEmail() {
        User user = userController.grtCurrenUser();
        String email;
        boolean validateEmail;
        while (true) {
            System.out.println("Enter email");
            email = Config.scanner().nextLine();
            validateEmail = Pattern.matches("^[A-Za-z]+[A-Za-z0-9._]*@[A-Za-z]+(\\.[a-z]+)$", email);
            if (validateEmail) {
                break;
            } else {
                System.out.println("the name failed! please try again!");

            }
        }
        User user1 = new User(user.getId(),user.getName(),user.getUsername(),email,user.getPassword(),user.getRoles());
        if (userController.existByEmail(email)){
            System.out.println("Email_Existed!!!");
        }else {
            userController.upDateProFile(user1);
            System.out.println("Change Email Success!!!!");
        }
        userController.getUserList();
        menuProFile();


    }

    private void formChangeName() {
       User user = userController.grtCurrenUser();
        String name;
        boolean validateName;
        while (true) {
            System.out.println("Enter name");
            name = Config.scanner().nextLine();
            validateName = Pattern.matches("[A-Z][a-zA-Z[\\s]]{1,40}", name);
            if (validateName) {
                break;
            } else {
                System.out.println("the name failed! please try again!");
            }
        }
        User user1 = new User(user.getId(),name,user.getUsername(),user.getEmail(),user.getPassword(),user.getRoles());
        userController.upDateProFile(user1);
        System.out.println("Change name success!");
        userController.getUserList();
        menuProFile();

    }
}
