package rikkei.accademy.model.teamfootball;

import java.io.Serializable;

public class Player implements Serializable {
    private int id;
    private String name;
    private double weight;
    private double height;
    private int jerseyNumber;

    private String position;



    public Player() {
    }
    public Player(int id, String name, double weight, double height, int jerseyNumber, String position) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
    }

    public Player(int lastId, String playerName) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", jerseyNumber=" + jerseyNumber +
                ", position='" + position + '\'' +
                '}';
    }
}
