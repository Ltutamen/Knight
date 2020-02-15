package ua.axiom.controller;

import ua.axiom.Viewer;
import ua.axiom.model.Model;

public class Controller {
    private Model model;
    private Viewer viewer;

    public Controller(Model model, Viewer viewer) {
        this.model = model;
        this.viewer = viewer;
    }

    public void processLoop() {
        viewer.print("Welcome to the Knight creator!");

        while (model.isRunning()) {

        }
    }
}
