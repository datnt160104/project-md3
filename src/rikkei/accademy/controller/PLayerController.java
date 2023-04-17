package rikkei.accademy.controller;

import rikkei.accademy.model.teamfootball.Player;
import rikkei.accademy.service.role.IRoleService;
import rikkei.accademy.service.role.RoleServiceIMPL;
import rikkei.accademy.service.teamfootball.IPlayerService;
import rikkei.accademy.service.teamfootball.PlayerServiceIMPL;

import java.util.List;


public class PLayerController {
    IPlayerService playerService = new PlayerServiceIMPL();


    public List<Player> getListPlayer() {
        return playerService.findAll();
    }
    public void createPlayer(Player player){
        playerService.save(player);
    }

    public void deletePlayer(int id){
        playerService.remove(id);

    }
    public Player detailPlayer(int id){
        return playerService.findById(id);
    }
    public void editPlayer(int id, Player player){
        Player player1 = playerService.findById(id);
        player1.setName(player.getName());
        getListPlayer();
    }
}