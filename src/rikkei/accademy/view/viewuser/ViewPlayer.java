package rikkei.accademy.view.viewuser;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.PLayerController;
import rikkei.accademy.model.teamfootball.Player;

import java.awt.*;
import java.util.List;

public class ViewPlayer {
    PLayerController pLayerController = new PLayerController();
    List<Player> playerList = pLayerController.getListPlayer();

    public void playerManage(){
        System.out.println("╔═════════════════════════════════════════════════════════════╗\n"+
                           "║                        Player Manage                        ║\n"+
                           "║═════════════════════════════════════════════════════════════║\n"+
                           "║                    1: Show list player                      ║\n"+
                           "║                                                             ║\n"+
                           "║                    2: Create player                         ║\n"+
                           "║                                                             ║\n"+
                           "║                    3: Update player                         ║\n"+
                           "║                                                             ║\n"+
                           "║                    4: Delete player                         ║\n"+
                           "║                                                             ║\n"+
                           "║                    5: Detail player                         ║\n"+
                           "║                                                             ║\n"+
                           "║                    6: Back                                  ║\n"+
                           "║                                                             ║\n"+
                           "╚═════════════════════════════════════════════════════════════╝");
                           System.out.println("Enter your choice: ");

        int choice = Config.scanner().nextInt();
        switch (choice){
            case 1:
                formShowListPlayer();
                break;
            case 2:
                formCreatePlayer();
                break;
            case 3:
                formEditPlayer();
                break;

            case 4:
                formDeletePlayer();
                break;
            case 5:
                detailPlayer();
                break;
            case 6:
                new ViewHome();
                break;
            default:
                System.out.println("Invalid choice");

        }
        playerManage();
    }

    private void formEditPlayer() {
        System.out.println("Enter id to edit");
        int idEdit = Config.scanner().nextInt();
        if (pLayerController.detailPlayer(idEdit) == null){
            System.out.println("not found");
        }else {
            Player player = pLayerController.detailPlayer(idEdit);
            System.out.println("OLD Player: " + player.getName());
            System.out.println("Enter Player want to edit");
            String newPlayer = Config.scanner().nextLine();
            if (!newPlayer.trim().equals("")){
                player.setName(newPlayer);
                return;
            }
            pLayerController.editPlayer(idEdit,player);

            System.out.println("Success");
        }

    }

    private void formDeletePlayer() {
        System.out.println("Enter id Player to delete");
        int idPlayer = Config.scanner().nextInt();
        if (pLayerController.detailPlayer(idPlayer) == null){
            System.out.println("Not found");
        }else {
            System.out.println("Enter 1 to delete or 2 to not delete");
            int choiceDelete = Config.scanner().nextInt();
            switch (choiceDelete){
                case 1:
                    pLayerController.deletePlayer(idPlayer);
                    formShowListPlayer();
                    pLayerController.getListPlayer();
                    break;
                case 2:
                    playerManage();
                    break;
            }
        }
    }
    private void detailPlayer(){
        System.out.println("Enter id Player");
        int idDetail = Config.scanner().nextInt();
        if (pLayerController.detailPlayer(idDetail) == null){
            System.out.println("Not Found");
        }else {
            Player player = pLayerController.detailPlayer(idDetail);
            System.out.println(player);
        }
        System.out.println("Enter quit to back ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")){
            playerManage();
        }
    }

    public void formShowListPlayer() {

        System.out.println(" ╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(" ║  ID  ║          Player         ║         Weight          ║           Height          ║      JerseyNumber     ║       Position        ║");
        System.out.println(" ║══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════║");
        for (Player player: playerList) {
         System.out.printf(" ║  %-4s║    %-20s ║    %-20s ║    %-20s   ║    %-15d    ║     %-15s   ║\n", player.getId(),player.getName(),player.getWeight(),player.getHeight(),player.getJerseyNumber(),player.getPosition());
        }
        System.out.println(" ╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("Enter quit to back ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")){
            playerManage();
        }
    }

    private void formCreatePlayer() {
        int lastId;
        if (playerList.isEmpty()){
        lastId = 1;
    }else {
        lastId = playerList.get(playerList.size() - 1).getId() + 1;
    }
        System.out.print("Enter name player: ");
        String playerName = Config.scanner().nextLine();
        System.out.print("Enter the weight: ");
        double weight=Config.scanner().nextDouble();
        System.out.print("Enter the height: ");
        double height=Config.scanner().nextDouble();
        System.out.print("Enter the jerseyNumber: ");
        int jerseyNumber=Config.scanner().nextInt();
        System.out.print("Enter the position: ");
        String position=Config.scanner().nextLine();
        Player player = new Player(lastId,playerName,weight,height,jerseyNumber,position);
        pLayerController.createPlayer(player);
        System.out.println("Create Player Success!!!");
        System.out.println(pLayerController.getListPlayer());
        System.out.println("Enter quit to back or another enter key to continue");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            playerManage();


        }
    }
}
