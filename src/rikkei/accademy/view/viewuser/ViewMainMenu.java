package rikkei.accademy.view.viewuser;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.UserController;
import rikkei.accademy.dto.request.SignInDTO;
import rikkei.accademy.dto.request.SinUpDTO;
import rikkei.accademy.dto.response.ResponseMessenger;
import rikkei.accademy.model.User;
import rikkei.accademy.model.role.RoleName;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewMainMenu {
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();

    public void menu() {
        System.out.println("╔═════════════════════════════════════════════════════════════╗\n"+
                "║                        FOOTBALL MANAGE                      ║\n"+
                "║═════════════════════════════════════════════════════════════║\n"+
                "║                                                             ║\n"+
                "║                          1.   REGISTER                      ║\n"+
                "║                                                             ║\n"+
                "║                          2.   LOGIN                         ║\n"+
                "║                                                             ║\n"+
                "╚═════════════════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                fromRegister();
                break;
            case 2:
                formLogin();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void formLogin() {
        System.out.println("══════════════════════════ FORM LOGIN ═══════════════════════════");
//        UserName

        System.out.println("Enter user name");
        String username = Config.scanner().nextLine();
        System.out.println("Enter password");
        String password = Config.scanner().nextLine();

        SignInDTO signInDTO = new SignInDTO(username, password);
        ResponseMessenger messenger = userController.login(signInDTO);
        if (messenger.getMessenger().equals("Login-Failed!!!")) {
            System.out.println("Login-Failed!!!");
            menu();
        } else {
            messenger.getMessenger().equals("Login-Success");
            System.out.println("Login-Success ");
            new  ViewHome();
        }
    }


    private void fromRegister() {
        System.out.println("══════════════════════════ REGISTER ═══════════════════════════");
//        id
        int id;
        if (userList.isEmpty()) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
//        name
        String name;
        while (true) {
            System.out.print("Enter name: ");
            name = Config.scanner().nextLine();

            if (name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
                break;
            } else {
                System.out.println("Invalid name, try again!!");
            }

        }
//        username
        String username;
        while (true) {
            System.out.print("Enter username: ");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid username, try again!!");
            }
        }
//        email
        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = Config.scanner().nextLine();
            if (email.matches("^(?!.*\\s)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                break;
            } else {
                System.out.println("Invalid email, try again!!");
            }
        }
//        password
        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = Config.scanner().nextLine();
            if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                break;
            } else {
                System.out.println("Invalid password, try again!!");
            }
        }
        Set<String> strRole = new HashSet<>();
        strRole.add("player");
        SinUpDTO sinUpDTO = new SinUpDTO(id, name, username, email, password, strRole);
        ResponseMessenger check_existed = userController.register(sinUpDTO);

        if (check_existed.getMessenger().equals("Username_Existed")) {
            System.out.println("Username the existed, try again!!!");
            fromRegister();
        } else if (check_existed.getMessenger().equals("Email_Existed!!!")) {
            System.out.println("Email the existed, try again!!!");
            fromRegister();
        } else if (check_existed.getMessenger().equals("Create success")) {
            System.out.println("Hello");
        }
        menu();
    }
}
