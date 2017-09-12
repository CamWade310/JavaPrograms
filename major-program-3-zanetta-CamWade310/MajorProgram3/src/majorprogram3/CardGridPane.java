/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogram3;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Cambino10
 */
public class CardGridPane extends GridPane {

    private Card[][] cards;
    private ArrayList<String> cardList;
    private int maxRows;
    private int maxCols;
    private int currentRows;
    private int currentCols;
    private int cardSize;

    public CardGridPane() {
        maxRows = 8;
        maxCols = 8;
        currentRows = 0;
        currentCols = 0;
        cardSize = 64;
        cards = new Card[8][8];
        cardList = new ArrayList();
    }

    public CardGridPane(int cardSize) {
        this.cardSize = cardSize;
        maxRows = 8;
        maxCols = 8;
        currentRows = 0;
        currentCols = 0;
        cards = new Card[8][8];
        cardList = new ArrayList();

        this.setHgap(2);
        this.setVgap(2);

    }

    public Card getCard(int r, int c) {
        return cards[r][c];
    }

    public int getCardListSize() {
        return cardList.size();
    }

    public String getCardList(int index) {
        return cardList.get(index);
    }

    public void setCardList(int index, String element) {
        cardList.set(index, element);
    }

    public void addCardList(String element) {
        cardList.add(element);
    }

    public String deleteCardList(int index) {
        return cardList.remove(index);
    }

    public void setCard(int r, int c, Card card) {
        cards[r][c] = card;

    }

    public void setCardList(ArrayList<String> cardList) {
        this.cardList = cardList;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getMaxCols() {
        return maxCols;
    }

    public void setMaxCols(int maxCols) {
        this.maxCols = maxCols;
    }

    public int getCurrentRows() {
        return currentRows;
    }

    public void setCurrentRows(int currentRows) {
        this.currentRows = currentRows;
    }

    public int getCurrentCols() {
        return currentCols;
    }

    public void setCurrentCols(int currentCols) {
        this.currentCols = currentCols;
    }

    public int getCardSize() {
        return cardSize;
    }

    public void setCardSize(int cardSize) {
        this.cardSize = cardSize;
    }

    public void createCardImageList(int size) {
        cardList.clear();
        int counter = 0;

        //add image paths to array
        for (int i = 0; i < size / 2; i++) {
            cardList.add("images/image_" + counter + ".png");
            cardList.add("images/image_" + counter + ".png");
            counter++;
        }

    }

    public void shuffleCards() {
        Collections.shuffle(cardList);
        
    }

    public void initCards(int rows, int cols) {
        currentRows = rows;
        currentCols = cols;
        createCardImageList(rows*cols);
        shuffleCards();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cards[i][j] = new Card();
                cards[i][j].setCardAndImageSize(cardSize, cardSize);
//                cards[i][j].setOnMousePressed(new FlipIt());
                this.add(cards[i][j], j, i);
                //this.setDisable(true);
            }
        }
        this.setHgap(2);
        this.setVgap(2);
        setCardImages();
    }

    public void setCardImages() {
        int counter = 0;

        for (int i = 0; i < currentRows; i++) {
            for (int j = 0; j < currentCols; j++) {
                System.out.println(cardList.get(counter));
                cards[i][j].setProperty(cardList.get(counter));
                counter++;

            }
        }
    }

}
