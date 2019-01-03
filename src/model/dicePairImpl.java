
package model;

import java.io.Serializable;

import model.interfaces.DicePair;

public class dicePairImpl implements DicePair, Serializable {
    //all variables
	private int dice1;
	private int dice2;
	private int numFaces;
	
	public dicePairImpl(int dice1, int dice2, int numFaces) {
            //constructor
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.numFaces = numFaces;
	}
//get methods
	@Override
	public int getDice1() {
		return this.dice1;
	}

	@Override
	public int getDice2() {
		return this.dice2;
	}

	@Override
	public int getNumFaces() {
		return this.numFaces;
	}

}
