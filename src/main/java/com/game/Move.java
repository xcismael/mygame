package com.game;

public class Move {
    
    int id;
    String moveName;
    String type;
    int power;
    int powerPoint;
    int accuracy;        
    char specialCondition;
    
    /*
    SPECIAL CONDITIONS 
    0 = Recharge
    1 = Poisoned
    2 = Sleep
    3 = Paralized
    */

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return this.id;
    }

    public void setMoveName(String moveName){
        this.moveName = moveName;
    }

    public String getMoveName(){
        return this.moveName;
    }

    public void setMoveType(String type){
        this.type = type;
    }

    public String getMoveType(){
        return this.type;
    }

    public void setPower(int power){
        this.power = power;
    }

    public int getPower(){
        return this.power;
    }

    public void setPowerPoint(int powerPoint){
        this.powerPoint = powerPoint;
    }

    public int getPowerPoint(){
        return this.powerPoint;
    }

    public void setAccuracy(int accuracy){
        this.accuracy = accuracy;
    }

    public int getAccuracy(){
        return this.accuracy;
    }

    public void setSpecialCondition(char specialCondition){
        this.specialCondition = specialCondition;
    }   
    
}
