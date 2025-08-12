package com.spingwithbushan.imageeditorapp.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageProcessor {

    public static void rotate(ImageView imageView, double angle) {
        imageView.setRotate(imageView.getRotate() + angle);
    }

    public static void applyGrayscale(ImageView imageView) {
        imageView.setEffect(new javafx.scene.effect.ColorAdjust(0, -1, 0, 0));
    }

    public static void applySepia(ImageView imageView) {
        imageView.setEffect(new javafx.scene.effect.SepiaTone());
    }
}
