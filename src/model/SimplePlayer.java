package model;

import java.io.Serializable;
import model.interfaces.DicePair;
import model.interfaces.Player;


public class SimplePlayer implements Player, Serializable{
    //all variables for simplePlayer
    String playerId;
    String playerName;
    int points;
    private int bet;

    public SimplePlayer(String playerId, String playerName, int points) {
        //constructor
		this.playerId = playerId;
		setPlayerName(playerName);
		setPoints(points);
	}
    //get and set methods
    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;	

    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;

    }

    @Override
    public String getPlayerId() {
        return this.playerId;
    }

    @Override
    public boolean placeBet(int bet) {
        //if bet is greater than the current amount of points return false otherwise set bet equal to bet
        if ((bet > this.points) || (bet < 0)) {
            return false;
        } else {
            this.bet = bet;
            this.points -= bet;
            return true;
        }
    }

    @Override
    public int getBet() {
        return 0;
    }

    @Override
    public DicePair getRollResult() {
        return null;
    }

    @Override
    public void setRollResult(DicePair rollResult) {

    }  
}
