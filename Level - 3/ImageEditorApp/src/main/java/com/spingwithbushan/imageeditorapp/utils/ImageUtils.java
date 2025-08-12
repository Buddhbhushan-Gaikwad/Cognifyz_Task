package com.spingwithbushan.imageeditorapp.utils;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;

public class ImageUtils {

    public static Image chooseImageFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            return new Image(file.toURI().toString());
        }
        return null;
    }
}
