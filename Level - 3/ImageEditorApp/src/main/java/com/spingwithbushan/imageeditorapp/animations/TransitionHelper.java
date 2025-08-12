package com.spingwithbushan.imageeditorapp.animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class TransitionHelper {
    public static void fadeIn(Node node, int millis) {
        FadeTransition ft = new FadeTransition(Duration.millis(millis), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
