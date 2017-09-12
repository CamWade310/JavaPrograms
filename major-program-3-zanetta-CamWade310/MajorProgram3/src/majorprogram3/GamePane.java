/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogram3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

/**
 *
 * @author Cambino10
 */
public class GamePane extends BorderPane {

    int turns;
    Label numTurns = new Label();
    private int rows;
    private int cols;
    private int numClicks;
    private int numMatched;
    private Card clickCardOne;
    private Card clickCardTwo;
    private int cardSize;
    private CardGridPane cardPane;
    private CommandPane cp = new CommandPane();
    int[] scores = new int[6];
    int levelIndex;
    String[] dates = new String[6];

    class CommandPane extends HBox {

        Button exit = new Button("Exit");
        Button newGame = new Button("New Game");
        Label turnCount = new Label("Turns: ");

        ObservableList<String> Level
                = FXCollections.observableArrayList(
                        "Level 1",
                        "Level 2",
                        "Level 3",
                        "Level 4",
                        "Level 5",
                        "Level 6"
                );
        ComboBox<String> levelSelect = new ComboBox(Level);

        CommandPane() {
            this.getChildren().add(exit);
            this.getChildren().add(levelSelect);
            this.getChildren().add(newGame);
            this.getChildren().add(turnCount);
            this.getChildren().add(numTurns);
            newGame.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //cardPane.setMaxCols(3);
                    if ("Level 1".equals(levelSelect.getValue())) {
                        levelIndex = 0;
                        cardPane.initCards(2, 3);
                        registerCardListeners();

                    } else if ("Level 2".equals(levelSelect.getValue())) {
                        levelIndex = 1;
                        cardPane.initCards(2, 4);
                        registerCardListeners();

                    } else if ("Level 3".equals(levelSelect.getValue())) {
                        levelIndex = 2;
                        cardPane.initCards(4, 4);
                        registerCardListeners();

                    } else if ("Level 4".equals(levelSelect.getValue())) {
                        levelIndex = 3;
                        cardPane.initCards(4, 6);
                        registerCardListeners();

                    } else if ("Level 5".equals(levelSelect.getValue())) {
                        levelIndex = 4;
                        cardPane.initCards(6, 6);
                        registerCardListeners();
                    } else if ("Level 6".equals(levelSelect.getValue())) {
                        levelIndex = 5;
                        cardPane.initCards(8, 8);
                        registerCardListeners();
                    }
                    numClicks = 0;
                    numMatched = 0;
                    turns = 0;
                }

            });

            exit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        PrintWriter save = new PrintWriter(new File("scores.txt"));
                        for (int i = 0; i < 6; i++) {
                            save.println(scores[i]);
                            save.println(dates[i]);

                        }
                        save.close();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    System.exit(0);
                }

            });

        }
    }

    public GamePane() {
        this(64);
    }

    public GamePane(int cardSize) {
        this.cardSize = cardSize;
        rows = 8;
        cols = 8;
        numClicks = 0;
        numMatched = 0;
        levelIndex = 0;
        try {
            Scanner read = new Scanner(new File("scores.txt"));
            for (int i = 0; i < 6; i++) {
                scores[i] = read.nextInt();
                dates[i] = read.next();

            }
            read.close();
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(GamePane.class.getName()).log(Level.SEVERE, null, ex);
        }

        cardPane = new CardGridPane();
        cardPane.initCards(rows, cols);
        this.setCenter(cardPane);
        this.setBottom(cp);
        this.registerCardListeners();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getNumClicks() {
        return numClicks;
    }

    public void setNumClicks(int numClicks) {
        this.numClicks = numClicks;
    }

    public int getNumMatched() {
        return numMatched;
    }

    public void setNumMathced(int numMathced) {
        this.numMatched = numMathced;
    }

    public Card getClickCardOne() {
        return clickCardOne;
    }

    public void setClickCardOne(Card clickCardOne) {
        this.clickCardOne = clickCardOne;
    }

    public Card getClickCardTwo() {
        return clickCardTwo;
    }

    public void setClickCardTwo(Card clickCardTwo) {
        this.clickCardTwo = clickCardTwo;
    }

    public void registerCardListeners() {

        for (int i = 0; i < cardPane.getCurrentRows(); i++) {
            for (int j = 0; j < cardPane.getCurrentCols(); j++) {
                cardPane.getCard(i, j).setOnMousePressed(new FlipIt());
                cardPane.getCard(i, j).activate();
            }
        }
    }

    class MyTimer extends AnimationTimer {

        long previous = System.nanoTime();

        @Override
        public void handle(long now) {
            if (now - previous >= 800000000) {
                stop();
                turns++;
                System.out.println(turns);
                numTurns.setText("" + turns);
                if (clickCardOne.getProperty().equals(clickCardTwo.getProperty())) {
                    clickCardOne.setMatched(true);
                    clickCardTwo.setMatched(true);
                    numMatched++;
                    if (numMatched == cardPane.getCurrentRows() * cardPane.getCurrentCols() / 2) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setHeaderText("Mission Accomplished");
                        numMatched = 0;
                        if (scores[levelIndex] > turns || scores[levelIndex] == 0) {
                            scores[levelIndex] = turns;
                            SimpleDateFormat date = new SimpleDateFormat("MM/dd/YYYY");
                            dates[levelIndex] = date.format(new Date());
                            try {
                                PrintWriter save = new PrintWriter(new File("scores.txt"));
                                for (int i = 0; i < 6; i++) {
                                    save.println(scores[i]);
                                    save.println(dates[i]);

                                }
                                save.close();
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }

                        }
                        String string = "";

                        for (int i = 0; i < 6; i++) {
                            string += "Level " + (i + 1) + System.lineSeparator() + (scores[i]) + System.lineSeparator() + (dates[i]) + System.lineSeparator();

                        }
                        alert.setContentText(string);
                        alert.show();
                    }
                    clickCardOne.setStyle("-fx-background-color: black;");
                    clickCardTwo.setStyle("-fx-background-color: black;");
                    String sound = "Cha-ching-sound-plastic-toy.mp3";
                    Media media = new Media(new File(sound).toURI().toString());
                    MediaPlayer mp = new MediaPlayer(media);
                    mp.play();
                } else {
                    clickCardOne.setFlipped(false);
                    clickCardTwo.setFlipped(false);
                    clickCardOne.flipCard();
                    clickCardTwo.flipCard();
                    String sound = "Wrong-answer-sound-effect.mp3";
                    Media media = new Media(new File(sound).toURI().toString());
                    MediaPlayer mp = new MediaPlayer(media);
                    mp.play();
                }
                numClicks = 0;
            }
        }

    }

    private class FlipIt implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            Card c = (Card) event.getSource();

            if (!c.isFlipped() && numClicks < 2) {
                c.setFlipped(true);
                c.flipCard();
                System.out.println(c);
                if (numClicks == 0) {
                    clickCardOne = c;

                } else if (numClicks == 1) {
                    clickCardTwo = c;
                    MyTimer timer = new MyTimer();
                    timer.start();
                }
                numClicks++;
            }

        }

    }

}
