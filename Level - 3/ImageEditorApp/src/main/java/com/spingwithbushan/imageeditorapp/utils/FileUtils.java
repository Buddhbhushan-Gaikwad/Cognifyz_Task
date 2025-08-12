package com.spingwithbushan.imageeditorapp.utils;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class FileUtils {
    public static File chooseImageFile(Window ownerWindow) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        return fileChooser.showOpenDialog(ownerWindow);
    }
}
