package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import java.util.Random;


public class GameEngineImpl implements GameEngine, Serializable{
    //all variables
    
    private volatile Collection<Player> players = new ArrayList<Player>();
    private volatile ArrayList<GameEngineCallback> gameEngineList = new ArrayList<>();
    private GameEngineCallback gameEngineCallback;

    //places a bet for a player
    @Override
    public boolean placeBet(Player player, int bet) {
        if (player.placeBet(bet)) {
			System.out.println("placed bet : " + player.getPlayerName());
			return true;
		} else {
			System.out.println("Could not place bet for player: " + player.getPlayerName());
			return false;
        }
    }
//rolls the die for player and stores result in setRollResult
    @Override
    
    public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) 
    {
        for(int i = initialDelay; i <= finalDelay; i += delayIncrement) {
            int dice1 = (int) (Math.random() * NUM_FACES + 1);
            int dice2 = (int) (Math.random() * NUM_FACES + 1);
            dicePairImpl dicePairImpl = new dicePairImpl(dice1, dice2,NUM_FACES);
            gameEngineCallback.intermediateResult(player, dicePairImpl, this);
            try {
                Thread.sleep(initialDelay);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        int dice1 = (int) (Math.random() * NUM_FACES + 1);
        int dice2 = (int) (Math.random() * NUM_FACES + 1);
        dicePairImpl dicePairImpl = new dicePairImpl(dice1, dice2,NUM_FACES);
        gameEngineCallback.result(player, dicePairImpl, this);
        player.setRollResult(dicePairImpl);
        
    }
  //rolls the die for house and stores result in setRollResult  
    @Override
    public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
        int diceOne;
        Random random = new Random();
        DicePair dicePair = null;
        final int minNum = 1;
        int houseTotal;
        int diceTwo;
        try {
            while (initialDelay < finalDelay) {
                diceOne = random.nextInt(NUM_FACES) + minNum;
                diceTwo = random.nextInt(NUM_FACES) + minNum;
                dicePair = new dicePairImpl(diceOne, diceTwo, NUM_FACES);
                this.gameEngineCallback.intermediateHouseResult(dicePair, this);
                initialDelay += delayIncrement;
                Thread.sleep(initialDelay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        diceTwo = random.nextInt(NUM_FACES) + minNum;
        diceOne = random.nextInt(NUM_FACES) + minNum;
        dicePair = new dicePairImpl(diceOne, diceTwo, NUM_FACES);
        houseTotal = diceOne + diceTwo;
        this.gameEngineCallback.houseResult(dicePair, this);
    }

    //adds a player
    @Override
    public void addPlayer(Player player) {
        this.players.add(player);
		System.out.println("added player: " + player.getPlayerName());
    }

    //gets a player by id
    @Override
    public Player getPlayer(String id) {
       for (Player player : players) {
            if (player.getPlayerId().equals(id)) {
                return player;
            }
    }
       return null;
    }
//removes a player
    @Override
    public boolean removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
            return true;
        }
        return false;
    }
    //adds a game engine call back
    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
       this.gameEngineCallback = gameEngineCallback;
    }
//removes game engine callback
    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        if (gameEngineList.contains(gameEngineCallback)) {
            gameEngineList.remove(gameEngineCallback);
            return true;
        }
        return false;
    }

    //gets collection of players
    @Override
    public Collection<Player> getAllPlayers() {
        return this.players;
    }
}
   
	
    

