package com.spingwithbushan.imageeditorapp.models;

import javafx.scene.image.Image;

public class ImageModel {
    private Image image;
    private double zoomFactor;

    public ImageModel() {
        this.zoomFactor = 1.0;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

    public void zoomIn() {
        this.zoomFactor += 0.1;
    }

    public void zoomOut() {
        this.zoomFactor -= 0.1;
    }
}
