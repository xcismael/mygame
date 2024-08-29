package com.game;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String args[]) {

        String fileDirectory = "C:/Users/Ismael/Documents/Dev/MAVEN/mygame/json/";                
        
        LinkedList<Pokemon> pkmList = new LinkedList<Pokemon>();
        LinkedList<Move> moveList = new LinkedList<Move>();

        createMove(1, "Flamethrower", 90, 15, "fire", 100, ' ', moveList);
        createMove(2, "Dragon Claw", 80, 15, "dragon", 100, ' ', moveList);
        createMove(3,"Protect", -1, 10, "normal", 110, ' ', moveList);
        createMove(4, "Fire Punch", 75, 15, "fire", 100, ' ', moveList);
        createMove(5, "Hydro Pump", 110, 5, "water", 80, ' ', moveList);
        createMove(6, "Muddy Water", 90, 10, "water", 85, ' ', moveList);
        createMove(7, "Earthquake", 100, 10, "ground", 100, ' ', moveList);
        createMove(8, "Ice Beam", 90, 10, "ice", 100, ' ', moveList);
        createMove(9, "Thunderbolt", 90, 15, "electric", 100, ' ', moveList);
        createMove(10, "Thunder", 110, 10, "electric", 70, ' ', moveList);
        createMove(11, "Iron Tail", 100, 15, "steel", 75, ' ', moveList);
        createMove(12, "Leaf Storm", 130, 5, "grass", 90, '0', moveList);
        createMove(13, "Sludge Wave", 95, 10, "poison", 100, ' ', moveList);
        createMove(14, "Frenzy Plant", 150, 5, "grass", 90, '0', moveList);
        createMove(20, "Double Kick", 60, 30, "fighting", 100, ' ', moveList);                                               
        
        createPokemon(1, "Charmander", "Fire", null, 200, 65, 1, 2, 4, 3, moveList, pkmList);
        createPokemon(2, "Squirtle", "Water", null, 200, 43, 5, 6, 7, 3, moveList, pkmList);
        createPokemon(3, "Bulbasaur", "Grass", "Poison", 200, 45, 12, 13, 14, 3, moveList, pkmList);
        createPokemon(4, "Pikachu", "Electric", null, 200, 90, 9, 10, 11, 3, moveList, pkmList);                

        createJSONFiles(fileDirectory, moveList, pkmList);
        
        int turnCount = 1;        
        boolean battleEnd = false;
        Pokemon pkm1 = new Pokemon();
        Pokemon pkm2 = new Pokemon();        
        Scanner sc = new Scanner(System.in);
        ArrayList<BattleProperties> battleProperties = new ArrayList<BattleProperties>();
              

        try {                        
            clearScreen();
            // ESCOGER PRIMER POKEMON
            System.out.println("JUGADOR 1 ESCOJA SU POKEMON");
            getPokemonNames(pkmList);           
            int opt1 = sc.nextInt();
            pkm1 = getPokemonByID(pkmList, opt1, pkm1);
            createBattleProperty(pkm1, battleProperties);            
            clearScreen();                        
            // ESCOGER SEGUNDO POKEMON
            System.out.println("JUGADOR 2 ESCOJA SU POKEMON");
            getPokemonNames(pkmList);           
            int opt2 = sc.nextInt();
            pkm2 = getPokemonByID(pkmList, opt2, pkm2);                        
            createBattleProperty(pkm2, battleProperties);
            clearScreen();            
        }   catch(Exception e){
                System.out.println(e.toString());
            }
        do  {
            try {                  
                printHeader(turnCount);
                System.out.println("\nJUGADOR 1, ESCOGE TU MOVIMIENTO");                
                showMovesMenu(pkm1, moveList, turnCount, 1, battleProperties);                

                printHeader(turnCount);
                System.out.println("\nJUGADOR 2, ESCOGE TU MOVIMIENTO");                
                showMovesMenu(pkm2, moveList, turnCount, 2, battleProperties);
                turnCount ++;                
                //battleEnd = true;
            }   catch (Exception e) {
                    System.out.println(e.toString());
                }
        }   while (!battleEnd);        
        System.out.println("!--------- BATALLA FINALIZADA ---------!");
    }
    
    public static void createMove(int id, String moveName, int power, int powerPoint, String type, int accuracy, char specialCondition, LinkedList<Move> moveList){
        Move move = new Move(); 
        try {
            move.setID(id);
            move.setMoveName(moveName);
            move.setPower(power);
            move.setPowerPoint(powerPoint);
            move.setMoveType(type);
            move.setAccuracy(accuracy);
            move.setSpecialCondition(specialCondition);
            moveList.add(move);            
        }   catch (Exception e) {
                System.out.println(e.toString());
            }               
    }

    public static Move getMoveByID(int id, LinkedList<Move> moveList){ 
        try {
            for (Move move : moveList) {            
                if(id == move.getID()){                    
                    return move;
                }
            }
        }   catch (Exception e) {
                System.out.println(e.toString());
            }         
        return null;
    }

    public static void createPokemon(int id, String name, String firstType, String secondType, int heartPoints, int Speed, int moveID1, int moveID2, int moveID3, int moveID4, LinkedList<Move> moveList, LinkedList<Pokemon> pkmList){
        Pokemon pokemon = new Pokemon();
        try {
            pokemon.setID(id);
            pokemon.setName(name);
            pokemon.setType(firstType, secondType);
            pokemon.setHeartPoints(heartPoints);
            pokemon.setSpeed(Speed);
            pokemon.setMoveSet(getMoveByID(moveID1, moveList), getMoveByID(moveID2, moveList), getMoveByID(moveID3, moveList), getMoveByID(moveID4, moveList));
            pkmList.add(pokemon);
        }   catch (Exception e) {
                System.out.println(e.toString());
            }        
    } 
    
    public static void createBattleProperty(Pokemon pkm, List<BattleProperties> battleProperties){
        BattleProperties bProperty = new BattleProperties();
        try {
            bProperty.setPkmHP(pkm.getHeartPoints());
            bProperty.setPkmSpeed(pkm.getSpeed());
            bProperty.setFirstMoveName(pkm.getMoveSet()[0].getMoveName());            
            bProperty.setSecondMoveName(pkm.getMoveSet()[1].getMoveName());            
            bProperty.setThirdMoveName(pkm.getMoveSet()[2].getMoveName());            
            bProperty.setFourthMoveName(pkm.getMoveSet()[3].getMoveName());
            bProperty.setFirstMoveType(pkm.getMoveSet()[0].getMoveType());            
            bProperty.setSecondMoveType(pkm.getMoveSet()[1].getMoveType());            
            bProperty.setThirdMoveType(pkm.getMoveSet()[2].getMoveType());            
            bProperty.setFourthMoveType(pkm.getMoveSet()[3].getMoveType());
            bProperty.setFirstMovePP(pkm.getMoveSet()[0].getPowerPoint());            
            bProperty.setSecondMovePP(pkm.getMoveSet()[1].getPowerPoint());            
            bProperty.setThirdMovePP(pkm.getMoveSet()[2].getPowerPoint());            
            bProperty.setFourthMovePP(pkm.getMoveSet()[3].getPowerPoint());
            battleProperties.add(bProperty);
        }   catch (Exception e) {
                System.out.println(e.toString());
            }
    }

    public static void getPokemonNames(LinkedList<Pokemon> pkmList){
        try {
            for (Pokemon pokemon : pkmList) {
                System.out.println(pokemon.getID() + ".- " + pokemon.getName());
            }     
        }   catch(Exception e){
                System.out.println(e.toString());
            }        
    }

    public static Pokemon getPokemonByID(LinkedList<Pokemon> pkmList, int id, Pokemon pkm){
        try {
            for (Pokemon pokemon : pkmList) {
                if (pokemon.getID() == id) {
                    pkm = pokemon;
                }
            } 
        }   catch (Exception e) {
                System.out.println(e.toString());
            }        
        return pkm;
    }
            
    public static void showMovesMenu(Pokemon pkm, LinkedList<Move> moveList, int turnCount, int currentPlayer, ArrayList<BattleProperties> bProperty){ 
        int moveUsed = 0;
        Scanner sc = new Scanner(System.in);        
        try {
            switch (currentPlayer) {
                case 1:
                    int movePP1 = bProperty.get(0).getFirstMovePP();                    
                    int movePP2 = bProperty.get(0).getSecondMovePP();
                    int movePP3 = bProperty.get(0).getThirdMovePP();
                    int movePP4 = bProperty.get(0).getFourthMovePP();
                    if (turnCount == 1) {                                
                        System.out.println("\n" + pkm.getName().toUpperCase() + " - HP: " + bProperty.get(0).getPkmHP());
                        System.out.println("1. "+ getMoveStringFormat(bProperty.get(0).getFirstMoveName(), movePP1, movePP1, bProperty.get(0).getFirstMoveType())
                                        + "\t2. " + getMoveStringFormat(bProperty.get(0).getSecondMoveName(), movePP1, movePP1, bProperty.get(0).getSecondMoveType()));
                        System.out.println("3. " + getMoveStringFormat(bProperty.get(0).getThirdMoveName(), movePP1, movePP1, bProperty.get(0).getThirdMoveType())
                                        + "\t4. " + getMoveStringFormat(bProperty.get(0).getFourthMoveName(), movePP1, bProperty.get(0).getFourthMovePP(), bProperty.get(0).getFourthMoveType()));
                        moveUsed = sc.nextInt();
                        calculatePowerPoints(bProperty, 1, moveUsed);                                                        
                        clearScreen();                     
                    }   else{
                            System.out.println("\n" + pkm.getName().toUpperCase() + " - HP: " + bProperty.get(0).getPkmHP());
                            System.out.println("1. " + getMoveStringFormat(bProperty.get(0).getFirstMoveName(), bProperty.get(0).getFirstMovePP(), bProperty.get(0).getFirstMovePP(), bProperty.get(0).getFirstMoveType())
                                            + "\t2. " + getMoveStringFormat(bProperty.get(0).getSecondMoveName(), bProperty.get(0).getSecondMovePP(), bProperty.get(0).getSecondMovePP(), bProperty.get(0).getSecondMoveType()));
                            System.out.println("3. " + getMoveStringFormat(bProperty.get(0).getThirdMoveName(), bProperty.get(0).getThirdMovePP(), bProperty.get(0).getThirdMovePP(), bProperty.get(0).getThirdMoveType())
                                            + "\t4. " + getMoveStringFormat(bProperty.get(0).getFourthMoveName(), bProperty.get(0).getFourthMovePP(), bProperty.get(0).getFourthMovePP(), bProperty.get(0).getFourthMoveType()));
                            moveUsed = sc.nextInt();
                            calculatePowerPoints(bProperty, 1, moveUsed);                                                        
                            clearScreen();                                   
                        }
                break;

                case 2:
                if (turnCount == 1) {                                
                    System.out.println("\n" + pkm.getName().toUpperCase() + " - HP: " + bProperty.get(0).getPkmHP());
                    System.out.println("1. "+ getMoveStringFormat(bProperty.get(1).getFirstMoveName(), bProperty.get(1).getFirstMovePP(), bProperty.get(1).getFirstMovePP(), bProperty.get(1).getFirstMoveType())
                                    + "\t2. " + getMoveStringFormat(bProperty.get(1).getSecondMoveName(), bProperty.get(1).getSecondMovePP(), bProperty.get(1).getSecondMovePP(), bProperty.get(1).getSecondMoveType()));
                    System.out.println("3. " + getMoveStringFormat(bProperty.get(1).getThirdMoveName(), bProperty.get(1).getThirdMovePP(), bProperty.get(1).getThirdMovePP(), bProperty.get(1).getThirdMoveType())
                                    + "\t4. " + getMoveStringFormat(bProperty.get(1).getFourthMoveName(), bProperty.get(1).getFourthMovePP(), bProperty.get(1).getFourthMovePP(), bProperty.get(1).getFourthMoveType()));
                    moveUsed = sc.nextInt(); 
                    calculatePowerPoints(bProperty, 1, moveUsed);                                                        
                    clearScreen();                     
                }   else{
                        System.out.println("\n" + pkm.getName().toUpperCase() + " - HP: " + bProperty.get(0).getPkmHP());
                        System.out.println("1. " + getMoveStringFormat(bProperty.get(1).getFirstMoveName(), bProperty.get(1).getFirstMovePP(), bProperty.get(1).getFirstMovePP(), bProperty.get(1).getFirstMoveType())
                                        + "\t2. " + getMoveStringFormat(bProperty.get(1).getSecondMoveName(), bProperty.get(1).getSecondMovePP(), bProperty.get(1).getSecondMovePP(), bProperty.get(1).getSecondMoveType()));
                        System.out.println("3. " + getMoveStringFormat(bProperty.get(1).getThirdMoveName(), bProperty.get(1).getThirdMovePP(), bProperty.get(1).getThirdMovePP(), bProperty.get(1).getThirdMoveType())
                                        + "\t4. " + getMoveStringFormat(bProperty.get(1).getFourthMoveName(), bProperty.get(1).getFourthMovePP(), bProperty.get(1).getFourthMovePP(), bProperty.get(1).getFourthMoveType()));
                        moveUsed = sc.nextInt();
                        calculatePowerPoints(bProperty, 1, moveUsed);                                                        
                        clearScreen();                                    
                    }
                break;
            
                default:
                break;
            }                                       
        }   catch (Exception e) {
                System.out.println(e.toString());
            }        
    }    

    public static String getMoveStringFormat(String moveName, int currentPP, int movePP, String moveType){
        return moveName + " (" + currentPP + "/" + movePP + ")" + " (" + (moveType.toUpperCase()) + ")\t";        
    }

    public static void calculateDamage(Pokemon pkm1, Pokemon pkm2, int moveUsedpkm1, int moveUsedpkm2) {
        String result = (pkm1.getSpeed() < pkm2.getSpeed()) ? pkm2.getName() + " es mas rapido" : pkm1.getName() + " es mas rapido";
        System.out.println(result);
    }

    public static int calculatePowerPoints(ArrayList<BattleProperties> bProperties, int currentPlayer, int movedUsed){ 
        int currentPP = 0;
        switch (movedUsed) {
            case 1:
                bProperties.get(currentPlayer - 1).setFirstMovePP(bProperties.get(currentPlayer - 1).getFirstMovePP() - 1); 
                currentPP =  bProperties.get(currentPlayer - 1).getFirstMovePP();
            break;
            case 2:
                bProperties.get(currentPlayer - 1).setSecondMovePP(bProperties.get(currentPlayer - 1).getSecondMovePP() - 1);
                currentPP =  bProperties.get(currentPlayer - 1).getSecondMovePP();
            break;
            case 3:
                bProperties.get(currentPlayer - 1).setThirdMovePP(bProperties.get(currentPlayer - 1).getThirdMovePP() - 1);
                currentPP =  bProperties.get(currentPlayer - 1).getThirdMovePP();
            break;
            case 4:
                bProperties.get(currentPlayer - 1).setFourthMovePP(bProperties.get(currentPlayer - 1).getFourthMovePP() - 1);
                currentPP =  bProperties.get(currentPlayer - 1).getFourthMovePP();
            break;        
            default:
            break;
        }        
        return currentPP;
    }

    public static void calculateBattle(int pkmHP, int movePP, String moveName){
        //calculatePowerPoints(movePP);
    }
        
    public static void convertListToJSON(LinkedList<?> linkedList, String fileDirectory) {
        JsonParser parser = new JsonParser();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = new Gson().toJson(linkedList);
        JsonElement jElement = parser.parse(jsonString);
        jsonString = gson.toJson(jElement);        
        try {
            FileWriter file = new FileWriter(fileDirectory);
            file.write(jsonString);
            file.close();
        }   catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void createJSONFiles(String fileDirectory, LinkedList<Move> moveList, LinkedList<Pokemon> pkmList){
        try {
            String fileName = "movesJSON.json";                        
            convertListToJSON(moveList, fileDirectory.concat(fileName));
            fileName = "pokemonJSON.json";
            convertListToJSON(pkmList, fileDirectory.concat(fileName));
        }   catch (Exception e){
                System.out.println(e.toString());
            }
    }

    public static void printHeader(int turnCount){
        System.out.println("!--------- BATALLA INICIADA ---------!");
        System.out.println("/---------     TURNO " + turnCount + "     ---------/");    
    }

    public static void clearScreen(){  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

}