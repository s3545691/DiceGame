package model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
public class GameEngineCallbackImpl implements GameEngineCallback, Serializable
{//initializes logger
	private Logger logger = Logger.getLogger("assignment1");

	public GameEngineCallbackImpl()
	{//sets logger level
		// FINE shows rolling output, INFO only shows result
		logger.setLevel(Level.INFO);
	}

	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
	{//finds intermediate result and displays it through the logger
		
                int diceOne = dicePair.getDice1();
		int diceTwo = dicePair.getDice2();
		int total = diceOne + diceTwo;
                
               logger.log(Level.INFO, String.format("Player: %1s \nIntermediate Result: %2s", player.getPlayerName(), total));
	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine)
	{//finds final result and displays it through the logger
            
		int diceOne = result.getDice1();
		int diceTwo = result.getDice2();
		int total = diceOne + diceTwo;

            logger.log(Level.INFO, String.format("Player: %1s \nRolled final result: %2s", player.getPlayerName(), total));
	}

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
        //finds intermediate house result and displays it through the logger
		int diceOne = dicePair.getDice1();
		int diceTwo = dicePair.getDice2();
		int total = diceOne + diceTwo;
                logger.log(Level.INFO, String.format("House: \nIntermediate Result: %2s", total));
	}

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {
        //finds final house result and displays it through the logger
		int diceOne = result.getDice1();
		int diceTwo = result.getDice2();
		int total = diceOne + diceTwo;
                 logger.log(Level.INFO, String.format("The House total: %1s", total));
	}

}
