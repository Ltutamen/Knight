package ua.axiom.controller;

import ua.axiom.Viewer;
import ua.axiom.model.Model;
import ua.axiom.model.wearable.Wearable;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.axiom.Viewer.Message.*;

public class Controller {
    private Model model;
    private Viewer viewer;
    private ResourceBundle resourceBundle;
    private Scanner scanner;

    public Controller(Model model, Viewer viewer, ResourceBundle resourceBundle, Scanner scanner) {
        this.model = model;
        this.viewer = viewer;
        this.resourceBundle = resourceBundle;
        this.scanner = scanner;
    }

    public void processLoop() {
        Scanner scanner = new Scanner(System.in);

        viewer.printLocalizedMessage(Viewer.Message.WELCOME_MSG);

        while (model.isRunning()) {
            showKnightStatus();
            viewer.printLocalizedMessage(Viewer.Message.QUESTION_MSG);
            mainMenuLoop();

        }
    }

    private int getInput() {
        Pattern pattern = Pattern.compile("[1-9]");

        //  viewer.print(resources.getString("MESSAGE." + propertiesPrefix + "_REQUEST_MSG"));

        while (true) {
            Matcher matcher = pattern.matcher(scanner.nextLine());
            if(matcher.matches()) {
                viewer.printLocalizedMessage(CORRECT_DIGITAL_INPUT_MSG);
                return Integer.parseInt(matcher.group());

            } else {
                viewer.print(
                        resourceBundle.getString("Wrong digital input format"));
            }
        }
    }

    public void mainMenuLoop() {
        boolean runningMainLoop = true;
        while (runningMainLoop) {
            viewer.printLocalizedMessage(MAIN_MENU_REQUEST_MSG);
            switch (getInput()) {
                case 1: {
                    System.out.println("gaga1");
                    break;
                }
                case 2: {
                    System.out.println("frfrf2");
                    break;
                }
                case 3: {
                    System.out.println("Rgsf3");
                    break;
                }
                default: {
                    //  todo refactor move into method
                    viewer.printLocalizedMessage(INVALID_DIGITAL_INPUT_MSG);
                }
            }
        }
    }

    public void addArmorItemLoop() {
        boolean runAddArmorLoop = true;
        while (runAddArmorLoop) {
            viewer.printLocalizedMessage(ARMOR_SELECTION_MENU_MSG);
            switch (getInput()) {
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                default: {
                    viewer.printLocalizedMessage(INVALID_DIGITAL_INPUT_MSG);
                }
            }

        }

    }

    public void addClothingLoop() {
        boolean runAddClothingLoop = true;
        while (runAddClothingLoop) {

        }

    }

    public void getStatisticsLoop() {

    }

    public void showKnightStatus() {
        Set<Wearable> worn = model.getAllWornItems();
        viewer.printLocalizedMessage(MENU_KNIGHT_INVENTORY_DESC);
        if(worn.size() == 0) {
            viewer.printLocalizedMessage(MENU_KNIGHT_INVENTORY_EMPTY);
            return;
        }

        viewer.print(model.getArmorItems().toString());
        viewer.print(model.getWornClothing().toString());
    }
}
