package rikkei.accademy.view.viewuser;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.UserController;
import rikkei.accademy.model.role.RoleName;
import rikkei.accademy.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewUserManage {
    UserController userController = new UserController();
    User currenUser = userController.grtCurrenUser();
    List<User> userList = userController.getUserList();


    public void menu() {
        System.out.println("╔═════════════════════════════════════════════════════════════╗\n"+
                "║                        USER MANAGE                          ║\n"+
                "║═════════════════════════════════════════════════════════════║\n"+
                "║                    1: Change Roler                          ║\n"+
                "║                                                             ║\n"+
                "║                    2: BlockUser                             ║\n"+
                "║                                                             ║\n"+
                "║                    3: DeleteUser                            ║\n"+
                "║                                                             ║\n"+
                "║                    4: Edit Profile                          ║\n"+
                "║                                                             ║\n"+
                "║                    5: back                                  ║\n"+
                "║                                                             ║\n"+
                "╚═════════════════════════════════════════════════════════════╝");
        System.out.println("Enter your choice: ");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                this.formChangeRole();
                break;
            case 2:
                this.formBlockUser();

                break;
            case 3:
                this.formDeleteUser();
                break;
            case 4:
                new ViewChangeProFile().menuProFile();
                break;
            case 5:
                this.backMenu();
                break;

        }
        menu();

    }
    private void backMenu(){
        new ViewHome();
    }

  private void formChangeRole( ) {
        System.out.println("══════════════════════════ CHANGE ROLE ══════════════════════════");
      while (true){
          System.out.println("update role player");
          int targetId =Config.scanner().nextInt();
          if (userController.detailUser(targetId)==null){
              System.out.println("id does not exist");
          }else{
              User user=userController.detailUser(targetId);
              System.out.println(user);
              userController.changeRole(user);

          }
          menu();
      }
    }

    private void formBlockUser() {
        RoleName maxRole = currenUser.getListRole();
        System.out.println("══════════════════════════ BLOCK USER FORM ══════════════════════════");
        List<User> userList;
        if (maxRole == RoleName.PM) {
            userList = new ArrayList<>(this.userList);
            User current = null;
            for (User user : userList) {
                if (user.getUsername().equals(currenUser.getUsername())) {
                    current = user;
                }
            }
            userList.remove(current);
        } else {
            userList = userController.findByRoleName(RoleName.PLAYER);
        }
        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "STATUS");
        userList.forEach(user -> {
            System.out.printf("%-5d%-15s%s%n", user.getId(), user.getUsername(), user.isStatus());
        });

        System.out.println("Enter user id to block");

        int id = Config.scanner().nextInt();

        if (!userList.contains(userController.findById(id))) {
            System.out.println("ID not found");
            return;
        }

        userController.changeStatus(id);

        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "STATUS");
        userList.forEach(user -> {
            System.out.printf("%-5d%-15s%s%n", user.getId(), user.getUsername(), user.isStatus());
        });

    }

    private void formDeleteUser() {

        RoleName maxRole = currenUser.getListRole();
        System.out.println("══════════════════════════ DELETE USER FORM ══════════════════════════");
        List<User> userList;
        if (maxRole == RoleName.PM) {
            userList = new ArrayList<>(this.userList);
            User current = null;
            for (User user : userList) {
                if (user.getUsername().equals(currenUser.getUsername())) {
                    current = user;
                }
            }
            userList.remove(current);
        } else {
            userList = userController.findByRoleName(RoleName.PLAYER);
        }
        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "STATUS");
        userList.forEach(user -> {
            System.out.printf("%-5d%-15s%s%n", user.getId(), user.getUsername(), user.isStatus());
        });

        System.out.println("Enter user id to delete");

        int id = Config.scanner().nextInt();

        if (!userList.contains(userController.findById(id))) {
            System.out.println("ID not found");
            return;
        }

        userController.deleteUserById(id);
        User remove = null;
        for (User user : userList) {
            if (user.getId() == id) {
                remove = user;
            }
        }
        userList.remove(remove);

        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "STATUS");
        userList.forEach(user -> {
            System.out.printf("%-5d%-15s%s%n", user.getId(), user.getUsername(), user.isStatus());
        });

    }

}
