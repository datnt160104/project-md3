package rikkei.accademy.controller;

import rikkei.accademy.model.schedule.Match;
import rikkei.accademy.service.schedule.IMatch;

import rikkei.accademy.service.schedule.MatchServiceIMPL;

import java.util.List;

public class MatchController {
    IMatch matchService = new MatchServiceIMPL();


    public List<Match> getListMatch() {
        return matchService.findAll();
    }
    public void createMatch(Match match){
        matchService.save(match);
    }

    public void deleteMatch(int id){
          matchService.remove(id);

    }
    public Match detailMatch(int id){
        return matchService.findById(id);
    }
    public void editMatch(int id, Match match){
        Match match1 = matchService.findById(id);
        match1.setDate(match.getDate());
        getListMatch();
    }
}
