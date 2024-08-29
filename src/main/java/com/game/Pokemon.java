package com.game;

public class Pokemon {

    int id;
    String name;
    Move[] moveSet = new Move[4];
           
    String[] type = {"",""};
    int speed;
    int heartPoints;
    
    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setMoveSet(Move move1, Move move2, Move move3, Move move4){
        this.moveSet[0] = move1; 
        this.moveSet[1] = move2; 
        this.moveSet[2] = move3; 
        this.moveSet[3] = move4; 
    }

    public Move[] getMoveSet(){
        return this.moveSet;
    }    

    public void setType(String firstType, String secondType){
        this.type[0] = firstType;
        this.type[1] = secondType;
    }

    public String[] getType(){
        return this.type;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void setHeartPoints(int heartPoints){
        this.heartPoints = heartPoints;
    }

    public int getHeartPoints(){
        return this.heartPoints;
    }
    
}
