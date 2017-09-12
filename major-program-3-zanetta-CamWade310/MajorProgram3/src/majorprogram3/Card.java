/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogram3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Cambino10
 */
public class Card extends StackPane {

    private boolean flipped;
    private boolean matched;
    private String property;
    private Image image;
    private ImageView imageView;
    private int row;
    private int col;
    private int numRow;
    private int numCol;

    public Card() {
        flipped = false;
        matched = false;
        property = "";
        image = null;
        imageView = new ImageView();
        row = 0;
        col = 0;
        numRow = 0;
        numCol = 0;
        this.setDisable(true);
        this.setStyle("-fx-background-color: grey");

    }

    public Card(String property) {
        this.property = property;

    }

    public void activate() {
        this.setDisable(false);

        this.setStyle("-fx-background-color: #66FF00");
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
        try {
            FileInputStream images = new FileInputStream(property);
            image = new Image(images);
            imageView.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getNumRow() {
        return numRow;
    }

    public void setNumRow(int numRow) {
        this.numRow = numRow;
    }

    public int getNumCol() {
        return numCol;
    }

    public void setNumCol(int numCol) {
        this.numCol = numCol;
    }

    public void setCardAndImageSize(int width, int height) {
        this.setPrefSize(width, height);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

    }

    public void flipCard() {
        if (!flipped) {
            this.getChildren().clear();
            this.setStyle("-fx-background-color: #66FF00");
        } else {
            this.getChildren().setAll(imageView);
            this.setStyle("-fx-background-color: red");
        }

    }

}
