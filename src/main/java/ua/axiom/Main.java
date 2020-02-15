package ua.axiom;

import ua.axiom.controller.Controller;
import ua.axiom.model.Model;

public class Main {
    public static void main(String[] args) {
        Viewer viewer = new Viewer();
        Model model = new Model();

        Controller controller = new Controller();
        controller.processLoop();
    }
}
