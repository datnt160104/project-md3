package rikkei.accademy.view.viewuser;

import rikkei.accademy.config.Config;
import rikkei.accademy.controller.MatchController;
import rikkei.accademy.model.schedule.Match;
import rikkei.accademy.model.teamfootball.Player;

import java.util.List;

public class ViewMatch {
    MatchController matchController = new MatchController();
     List<Match> matchList = matchController.getListMatch();
    public void MatchManage(){
        System.out.println("╔═════════════════════════════════════════════════════════════╗\n"+
                "║                        Match Manage                         ║\n"+
                "║═════════════════════════════════════════════════════════════║\n"+
                "║                    1: Show list Match                       ║\n"+
                "║                                                             ║\n"+
                "║                    2: Create match                          ║\n"+
                "║                                                             ║\n"+
                "║                    3: Update match                          ║\n"+
                "║                                                             ║\n"+
                "║                    4: Delete match                          ║\n"+
                "║                                                             ║\n"+
                "║                    5: Detail match                          ║\n"+
                "║                                                             ║\n"+
                "║                    6: Back                                  ║\n"+
                "║                                                             ║\n"+
                "╚═════════════════════════════════════════════════════════════╝");
        System.out.println("Enter your choice: ");

        int choice = Config.scanner().nextInt();
        switch (choice){
            case 1:
                formShowListMatch();
                break;
            case 2:
                formCreateMatch();
                break;
            case 3:
                formEditMatch();
                break;
            case 4:
                formDeleteMatch() ;
                break;
            case 5:
                detailMatch() ;
                break;
            case 6:
                new ViewHome();
                break;
            default:
                System.out.println("Invalid choice");

        }
        MatchManage();
    }
    private void formEditMatch() {
        System.out.println("Enter id to edit");
        int idEdit = Config.scanner().nextInt();
        if (matchController.detailMatch(idEdit) == null){
            System.out.println("not found");
        }else {
            Match match = matchController.detailMatch(idEdit);
            System.out.println("OLD match: " + match.getDate());
            System.out.println("Enter Match want to edit");
            String newMatch = Config.scanner().nextLine();
            if (!newMatch.trim().equals("")){
                match.setDate(newMatch);
                return;
            }
            matchController.editMatch(idEdit,match);

            System.out.println("Success");
        }

    }

    private void formDeleteMatch() {
        System.out.println("Enter id category to delete");
        int idMatch = Config.scanner().nextInt();
        if (matchController.detailMatch(idMatch) == null){
            System.out.println("Not found");
        }else {
            System.out.println("Enter 1 to delete or 2 to not delete");
            int choiceDelete = Config.scanner().nextInt();
            switch (choiceDelete){
                case 1:
                    matchController.deleteMatch(idMatch);
                    formShowListMatch();
                    matchController.getListMatch();
                    break;
                case 2:
                    MatchManage();
                    break;
            }
        }
    }
    private void detailMatch(){
        System.out.println("Enter id Player");
        int idDetail = Config.scanner().nextInt();
        if (matchController.detailMatch(idDetail) == null){
            System.out.println("Not Found");
        }else {
            Match match = matchController.detailMatch(idDetail);
            System.out.println(match);
        }
        System.out.println("Enter quit to back ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")){
            MatchManage();
        }
    }

    public void formShowListMatch() {
        System.out.println(" ╔══════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(" ║  ID  ║         Hometeam        ║        Awayteam         ║             Datet         ║ ");
        System.out.println(" ║══════════════════════════════════════════════════════════════════════════════════════║");
        for (Match match: matchList) {
            System.out.printf(" ║  %-4s║    %-20s ║    %-20s ║    %-20s   ║\n", match.getId() ,match.getHomeTeam(),match.getAwayTeam(),match.getDate());
        }
        System.out.println(" ╚══════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("Enter quit to back ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")){
            MatchManage();
        }
    }

    private void formCreateMatch() {
        int lastId;
        if (matchList.isEmpty()){
            lastId = 1;
        }else {
            lastId = matchList.get(matchList.size() - 1).getId() + 1;
        }
        System.out.print("Enter name hometeam: ");
        String homeTeam = Config.scanner().nextLine();
        System.out.print("Enter the awayteam: ");
        String awayTeam=Config.scanner().nextLine();
        System.out.print("Enter the date: ");
        String date=Config.scanner().nextLine();
        Match match = new Match(lastId,homeTeam,awayTeam,date);
        matchController.createMatch(match);
        System.out.println("Create Match Success!!!");
        System.out.println(matchController.getListMatch());
        System.out.println("Enter quit to back ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            MatchManage();


        }
    }
}
