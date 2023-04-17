package rikkei.accademy.service.schedule;

import rikkei.accademy.config.Config;
import rikkei.accademy.model.User;
import rikkei.accademy.model.schedule.Match;
import rikkei.accademy.model.teamfootball.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchServiceIMPL implements IMatch{

    static String PATH_SCHEDULE= "src/rikkei/accademy/database/schedule.txt";
    static List<Match> matchList = new Config<Match>().readFile(PATH_SCHEDULE);



    @Override
    public List<Match> findAll() {
        new Config<Match>().writeFile(PATH_SCHEDULE,matchList);
        return matchList;
    }

    @Override
    public void save(Match match) {
        matchList.add(match);
        new Config<Match>().writeFile(PATH_SCHEDULE,matchList);
    }

    @Override
    public Match findById(int id) {
        return matchList.stream().filter(match -> match.getId() == id).findAny().orElse(null);
    }

    @Override
    public void remove(int id) {
        matchList.remove(findById(id));
        new Config<Match>().writeFile(PATH_SCHEDULE,matchList);
    }

}
