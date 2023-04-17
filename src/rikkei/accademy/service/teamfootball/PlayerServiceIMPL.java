package rikkei.accademy.service.teamfootball;

import rikkei.accademy.config.Config;
import rikkei.accademy.model.User;
import rikkei.accademy.model.teamfootball.Player;

import java.util.List;

public class PlayerServiceIMPL implements IPlayerService{
    static String PATH_PLAYER = "src/rikkei/accademy/database/player.txt";
    static List<Player> playerList = new Config<Player>().readFile(PATH_PLAYER);

    @Override
    public List findAll() {
        new Config<Player>().writeFile(PATH_PLAYER,playerList);
        return playerList;
    }

    @Override
    public void save(Player player) {
        playerList.add(player);
        new Config<Player>().writeFile(PATH_PLAYER,playerList);
    }

    @Override
    public Player findById(int id) {
        return playerList.stream().filter(player -> player.getId() == id).findAny().orElse(null);
    }

    @Override
    public void remove(int id) {
        playerList.remove(findById(id));
        new Config<Player>().writeFile(PATH_PLAYER,playerList);
    }
}
