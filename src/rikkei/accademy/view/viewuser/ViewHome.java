package rikkei.accademy.view.viewuser;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.UserController;
import rikkei.accademy.model.role.Role;
import rikkei.accademy.model.role.RoleName;
import rikkei.accademy.model.User;
import rikkei.accademy.model.teamfootball.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ViewHome {
    UserController userController = new UserController();
    User currenUser = userController.grtCurrenUser();
    List<User> userList = userController.getUserList();
    Set<Role> roleSet = currenUser.getRoles();
    List<Role> roleList = new ArrayList<>(roleSet);
    RoleName roleName = roleList.get(0).getRoleName();

    public ViewHome(){
       switch (roleName){
           case PM:
           menuPm();
           break;
           case COACH:
               menuCoach();
               break;
           case PLAYER:
               menuPlayer();
               break;


       }
    }

    private void menuPm() {
        System.out.println("Hello: " + roleName + " : " + currenUser.getName() );
        System.out.println("╔════════════════════════ "+"WELCOME"+" ════════════════════════════╗\n"+
                "║                                                             ║\n"+
                "║═════════════════════════════════════════════════════════════║\n"+
                "║                       1: Player Manage                      ║\n"+
                "║                                                             ║\n"+
                "║                       2: User manage                        ║\n"+
                "║                                                             ║\n"+
                "║                       3: Match manage                       ║\n"+
                "║                                                             ║\n"+
                "║                       4: Show list account                  ║\n"+
                "║                                                             ║\n"+
                "║                       5: Log out                            ║\n"+
                "║                                                             ║\n"+
                "╚═════════════════════════════════════════════════════════════╝");
                System.out.print("Enter your choice: ");


        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                new ViewPlayer().playerManage();
            case 2:
                formUserManage();
                break;
            case 3:
                new ViewMatch().MatchManage();
                break;
            case 4:
                formShowListUser();
                break;
            case 5:
                 userController.logOut();
                new ViewMainMenu().menu();
                return;
            default:
                System.out.println("Invalid choice");
        }
        menuPm();

    }


    public void menuPlayer(){
        System.out.println("Hello: " + roleName + " : " + currenUser.getName() );
        System.out.println("╔════════════════════════ "+"WELCOME"+" ════════════════════════════╗\n"+
                           "║                                                             ║\n"+
                "║═════════════════════════════════════════════════════════════║\n"+
                "║                      1: MY Profile                          ║\n"+
                "║                                                             ║\n"+
                "║                      2: Show Schedule                       ║\n"+
                "║                                                             ║\n"+
                "║                      3: Log out                             ║\n"+
                "║                                                             ║\n"+
                "╚═════════════════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                new ViewChangeProFile().menuProFile();
                break;
            case 2:
                new ViewMatch().formShowListMatch();
                break;
            case 3:
                userController.logOut();
                new ViewMainMenu().menu();
                return;
            default:
                System.out.println("Invalid choice");
        }
        menuPlayer();
    }



    public void menuCoach(){
        System.out.println("Hello: " + roleName + " : " + currenUser.getName() );
        System.out.println("╔════════════════════════ "+"WELCOME"+" ════════════════════════════╗\n"+
                           "║                                                             ║\n"+
                "║═════════════════════════════════════════════════════════════║\n"+
                "║                      1: MY Profile                          ║\n"+
                "║                                                             ║\n"+
                "║                      2: Match manage                        ║\n"+
                "║                                                             ║\n"+
                "║                      3: Player manage                       ║\n"+
                "║                                                             ║\n"+
                "║                      4: Log out                             ║\n"+
                "║                                                             ║\n"+
                "╚═════════════════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice){
            case 1:
                userController.logOut();
                new ViewMainMenu().menu();
                return;
            case 2:
                new ViewMatch().MatchManage();
                break;
            case 3:
                new ViewPlayer().playerManage();
                break;
            case 4:
                userController.logOut();
                new ViewMainMenu().menu();
                return;
            default:
                System.out.println("Invalid choice");
        }
        menuCoach();
    }
    private void formShowListUser() {

        System.out.println(" ╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(" ║  ID  ║          Name           ║       Username          ║            Email          ║        Password       ║                  Role                ║");
        System.out.println(" ║═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════║");
        for (User user: userList) {
            System.out.printf(" ║  %-4s║    %-20s ║    %-20s ║    %-20s   ║    %-15s    ║     %-30s   ║\n", user.getId(),user.getName(), user.getUsername(), user.getEmail(), user.getPassword(),user.getRoles());
        }
        System.out.println(" ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("Enter quit to back ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")){
            menuPm();
        }
    }
    private void formUserManage() {
       new ViewUserManage().menu();

    }
}

