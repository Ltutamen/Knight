package ua.axiom.controller;

import ua.axiom.Viewer;
import ua.axiom.model.Model;

public class Controller {
    private Model model;
    private Viewer viewer;

    public void processLoop() {
        while (model.isRunning()) {

        }
    }
}
