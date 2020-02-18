package ua.axiom;

import ua.axiom.controller.Controller;
import ua.axiom.model.Model;
import ua.axiom.viewer.Viewer;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ResourceBundle resources = ResourceBundle.getBundle("text_" + (args.length > 0 ? args[0] : "US"));

        Model model = new Model();
        Viewer viewer = new Viewer.
                ViewerBuilder(model, resources).
                build();

        Controller controller = new Controller(model, viewer, resources, new Scanner(System.in));
        controller.processLoop();
    }
}
