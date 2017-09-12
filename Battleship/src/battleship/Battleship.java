/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.Random;
import java.util.Scanner;

/*	
Author:	Camden Wade
Date:	08/27/17
Assignment: Program #1	- Battleship
File: main.cpp
Description: This file shows execution of the game battleship using basic java concepts	
 */
public class Battleship {

    /**
     * @param args the command line arguments
     */
    
    // Declare and initialize two static variables to hold the amount on fits each player has used
    static int oHits = 0;
    static int pHits = 0;

    public static void main(String[] args) {
        
        // Declare variables to hold ship location and direction
        String acDirection = "";
        String acLocation = "";
        String bsDirection = "";
        String bsLocation = "";
        String cDirection = "";
        String cLocation = "";
        String dDirection = "";
        String dLocation = "";
        String s1Direction = "";
        String s1Location = "";
        
        // create an array for each grid that will be used in the game
        char[][] pGrid = new char[7][7];    
        char[][] oGrid = new char[7][7];
        char[][] hitGrid = new char[7][7];
        
        // Initialize all of your grids
        initGrid(pGrid);
        initGrid(oGrid);
        initGrid(hitGrid);
        
        // create a scanner to read user input
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Place ships on player grid");

        /* Implement a do- while loop to perform everything necessary to read in input from the user,
          and save to the variables, and place on player grid*/
        do {
            System.out.println("Enter location for your AirCraft Carrier");
            acLocation = keyboard.next();
            System.out.println("Enter direction for ship");
            acDirection = keyboard.next();
        } while (checkSpot(acLocation, Directions.valueOf(acDirection), 6, pGrid) == false);

        placeOnGrid(acLocation, Directions.valueOf(acDirection), 6, pGrid);
        printGrid(pGrid, false);

        do {
            System.out.println("Enter location for your Battle Ship");
            bsLocation = keyboard.next();
            System.out.println("Enter direction for ship");
            bsDirection = keyboard.next();
        } while (checkSpot(bsLocation, Directions.valueOf(bsDirection), 5, pGrid) == false);
        placeOnGrid(bsLocation, Directions.valueOf(bsDirection), 5, pGrid);
        printGrid(pGrid, false);

        do {
            System.out.println("Enter location for your Crusier");
            cLocation = keyboard.next();
            System.out.println("Enter direction for ship");
            cDirection = keyboard.next();
        } while (checkSpot(cLocation, Directions.valueOf(cDirection), 4, pGrid) == false);
        placeOnGrid(cLocation, Directions.valueOf(cDirection), 4, pGrid);
        printGrid(pGrid, false);

        do {
            System.out.println("Enter location for your Destroyer");
            dLocation = keyboard.next();
            System.out.println("Enter direction for ship");
            dDirection = keyboard.next();
        } while (checkSpot(dLocation, Directions.valueOf(dDirection), 3, pGrid) == false);
        placeOnGrid(dLocation, Directions.valueOf(dDirection), 3, pGrid);
        printGrid(pGrid, false);
        
        //Implement do - while loops to perform actions to create an opponenent grid (repeated for every ship)

        Random rand = new Random(); // Random generator to generate coordinates on grid
        do {
            int x = rand.nextInt(7);
            int y = rand.nextInt(7);

            acLocation = (char) ('A' + y) + Integer.toString(x);
            acDirection = rand.nextBoolean() ? "horizontal" : "vertical";
        } while (checkSpot(acLocation, Directions.valueOf(acDirection), 6, oGrid) == false);
        placeOnGrid(acLocation, Directions.valueOf(acDirection), 6, oGrid);
        //printGrid(oGrid, false);

        do {
            int x = rand.nextInt(7);
            int y = rand.nextInt(7);

            bsLocation = (char) ('A' + y) + Integer.toString(x);
            bsDirection = rand.nextBoolean() ? "horizontal" : "vertical";
        } while (checkSpot(bsLocation, Directions.valueOf(bsDirection), 6, oGrid) == false);
        placeOnGrid(bsLocation, Directions.valueOf(bsDirection), 6, oGrid);
        //printGrid(oGrid, false);

        do {
            int x = rand.nextInt(7);
            int y = rand.nextInt(7);

            cLocation = (char) ('A' + y) + Integer.toString(x);
            cDirection = rand.nextBoolean() ? "horizontal" : "vertical";
        } while (checkSpot(cLocation, Directions.valueOf(cDirection), 6, oGrid) == false);
        placeOnGrid(cLocation, Directions.valueOf(cDirection), 6, oGrid);
        //printGrid(oGrid, false);

        do {
            int x = rand.nextInt(7);
            int y = rand.nextInt(7);

            dLocation = (char) ('A' + y) + Integer.toString(x);
            dDirection = rand.nextBoolean() ? "horizontal" : "vertical";
        } while (checkSpot(dLocation, Directions.valueOf(dDirection), 6, oGrid) == false);
        placeOnGrid(dLocation, Directions.valueOf(dDirection), 6, oGrid);
        //printGrid(oGrid, false);

        boolean turn = true; //boolean to recognize who's turn it is
        
        // while loop representing the amount of turns it takes to end the game
        while (oHits < 18 && pHits < 18) { 
            if (turn) {
                playerTurn(oGrid, hitGrid);

            } else {
                oppTurn(pGrid);
            }
            turn = !turn;

        }
        
        if(18 == pHits){
            System.out.print("Player Wins");
        } else {
            System.out.print("Opponent Wins");
        }

    }
    
    // Method created to initialize a grid
    public static void initGrid(char[][] grid) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = '~';
            }
        }
    }
    
    // Method created to print an initialized a grid
    public static void printGrid(char[][] grid, boolean hide) {
        System.out.println("  1 2 3 4 5 6 7"); // Print line to print 1 2 3 4 5 6 7 at top of grid
        for (int i = 0; i < 7; i++) {
            System.out.print((char) ('A' + i) + " "); // Print line to put the letters A-G vertical on the grid
            for (int j = 0; j < 7; j++) {
                if (grid[i][j] >= 2 && grid[i][j] <= 6) {
                    if (hide) {
                        System.out.print("~ ");
                    } else {
                        System.out.print("# ");
                    }
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    
    // method created to place a player's input on the grid
    public static void placeOnGrid(String location, Directions direction, int size, char[][] grid) {
        int x = Integer.parseInt(location.substring(1)) - 1;
        int y = location.charAt(0) - 'A';

        if (direction == Directions.horizontal) {
            for (int i = x; i < x + size; i++) {
                grid[y][i] = (char) size;
            }
        } else if (direction == Directions.vertical) {
            for (int i = y; i < y + size; i++) {
                grid[i][x] = (char) size;
            }
        }
    }
    
    //Method to make sure a spot chosen is available to put a ship
    public static boolean checkSpot(String location, Directions direction, int size, char[][] grid) {
        int x = Integer.parseInt(location.substring(1)) - 1;
        int y = location.charAt(0) - 'A';

        if (x < 0 || y < 0) {
            return false;
        }
        if (direction == Directions.horizontal) {
            if (x + size > 7) {
                return false;
            }
            for (int i = x; i < x + size; i++) {
                if (grid[y][i] != '~') {
                    return false;
                }
            }
        } else if (direction == Directions.vertical) {
            if (y + size > 7) {
                return false;
            }
            for (int i = y; i < y + size; i++) {
                if (grid[i][x] != '~') {
                    return false;
                }
            }
        }
        return true;
    }

    // Creaete method to set a spot on the grid
    public static void setSpot(int y, int x, char spot, char[][] grid) {
        grid[y][x] = spot;
    }
    
    // a method to control action when it is the players turn
    public static void playerTurn(char[][] oGrid, char[][] hitGrid) {
        Scanner turn = new Scanner(System.in);
        String spot;

        int x = 0;
        int y = 0;
        
        do {
            printGrid(hitGrid, false);
            System.out.println("FIRE AWAY\n!!"
                    + "Player Grid is at the top and Oppenent Grid is at the bottom");
            
            spot = turn.next();
            x = Integer.parseInt(spot.substring(1)) - 1;
            y = spot.charAt(0) - 'A';
        } while (hitGrid[y][x] != '~');
        if (oGrid[y][x] >= 2 && oGrid[y][x] <= 6 ) {
            hitGrid[y][x] = 'X';
            System.out.println("PLAYER HIT!");
            oHits++;
        } else {
            hitGrid[y][x] = '*';
            System.out.println("PLAYER MISSED");

        }
        printGrid(hitGrid, false);

    }
    
    // a method to control action when it is the opponents turn
    public static void oppTurn(char[][] pGrid) {
        Random hit = new Random();
        int x = 0;
        int y = 0;
        do {
            x = hit.nextInt(7);
            y = hit.nextInt(7);
        } while (pGrid[y][x] == '*' || pGrid[y][x] == 'X');    

        if (pGrid[y][x] >= 2 && pGrid[y][x] <= 6) {
            pGrid[y][x] = 'X';
            System.out.println("OPPONENT HIT!");
            pHits++;
        } else {
            pGrid[y][x] = '*';
            System.out.println("OPPONENT MISSED");

        }
        printGrid(pGrid, false);

    }

}
