package ua.axiom;

import ua.axiom.controller.Controller;
import ua.axiom.model.Model;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ResourceBundle resources = ResourceBundle.getBundle("text_" + (args.length > 0 ? args[0] : "US"));

        Viewer viewer = new Viewer.ViewerBuilder().setResourceBundle(resources).build();
        Model model = new Model();

        Controller controller = new Controller(model, viewer, resources, new Scanner(System.in));
        controller.processLoop();
    }
}
