package com.spingwithbushan.imageeditorapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.File;

public class MainController {

    @FXML
    private ImageView imageView; // ImageView where the opened image will be displayed

    private double zoomFactor = 1.0;

    @FXML
    private void handleOpenImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Use the existing window's stage instead of new Stage()
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
            zoomFactor = 1.0; // reset zoom
            imageView.setFitWidth(image.getWidth());
            imageView.setFitHeight(image.getHeight());
        }
    }

    @FXML
    private void handleZoomIn() {
        if (imageView.getImage() != null) {
            zoomFactor += 0.1;
            imageView.setFitWidth(imageView.getImage().getWidth() * zoomFactor);
            imageView.setFitHeight(imageView.getImage().getHeight() * zoomFactor);
        }
    }

    @FXML
    private void handleZoomOut() {
        if (imageView.getImage() != null && zoomFactor > 0.2) {
            zoomFactor -= 0.1;
            imageView.setFitWidth(imageView.getImage().getWidth() * zoomFactor);
            imageView.setFitHeight(imageView.getImage().getHeight() * zoomFactor);
        }
    }



}
