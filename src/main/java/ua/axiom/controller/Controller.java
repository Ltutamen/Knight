package ua.axiom.controller;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.model.*;
import ua.axiom.model.wearable.*;
import ua.axiom.viewer.Viewer;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        viewer.printLocalizedMessage(Viewer.Message.WELCOME_MSG);

        while (model.isRunning()) {
            showKnightStatus();
            viewer.printLocalizedMessage(Viewer.Message.QUESTION_MSG);
            mainMenuLoop();

        }
    }

    public void mainMenuLoop() {
        viewer.printLocalizedMessage(Viewer.Message.MAIN_MENU_REQUEST_MSG);
        switch (getInput()) {
            case 1: {
                addArmorItemLoop();
                break;
            }
            case 2: {
                addClothingItemLoop();
                break;
            }
            case 3: {
                getStatisticsLoop();
                break;
            }
            default: {
                //  todo refactor move into method
                viewer.printLocalizedMessage(Viewer.Message.INVALID_DIGITAL_INPUT_MSG);
            }
        }
    }

    public void addArmorItemLoop() {
        //  todo refactor
        viewer.printLocalizedMessage(Viewer.Message.ARMOR_SELECTION_MENU_MSG);
        viewer.print(viewer.getWearableSelectionMsg(ArmorPiece::values));

        int armorItemToAdd = getInput();
        int bodyPartToAdd = bodyPartSelectionLoop(model.getWearableByNumber(armorItemToAdd, ArmorPiece::values));

        model.addArmorPiece(model.getWearableByNumber(armorItemToAdd, ArmorPiece::values), model.getBodyPartByNumber(bodyPartToAdd));
    }

    public void addClothingItemLoop() {
        //  todo refactor
        viewer.printLocalizedMessage(Viewer.Message.CLOTHING_SELECTION_MENU_MSG);
        viewer.print(viewer.getWearableSelectionMsg(ClothingPiece::values));

        int clothingItemToAdd = getInput();
        int bodyPartToAdd = bodyPartSelectionLoop(model.getWearableByNumber(clothingItemToAdd, ClothingPiece::values));

        model.addClothingPiece(model.getWearableByNumber(clothingItemToAdd, ClothingPiece::values), model.getBodyPartByNumber(bodyPartToAdd));

    }

    @Deprecated
    public int bodyPartSelectionLoop(Wearable itemToWear) {
        //  todo print msg that excludes unfitting body parts
        while (true) {
            viewer.printLocalizedMessage(Viewer.Message.BODY_PART_SELECTION_MENU_MSG);
            viewer.print(viewer.getBodyPartSelectionMessage(itemToWear));

            int selectedBodyPart = getInput();
            if(itemToWear.canBeWornAt().contains(model.getBodyPartByNumber(selectedBodyPart))) {
                return selectedBodyPart;
            }
            viewer.printLocalizedMessage(Viewer.Message.MENU_CANNOT_WEAR_ITEM_ON_BODYPART);
        }
    }

    public int bodyPartSelectionLoop(Predicate<Wearable> predicate) {
        throw new NotImplementedException();
    }

    public void getStatisticsLoop() {
        boolean runStatisticsLoop = true;
        while (runStatisticsLoop) {

        }
    }

    public void showKnightStatus() {
        Map<Knight.BodyPart, List<Wearable>> worn = model.getAllWornItems();
        viewer.printLocalizedMessage(Viewer.Message.MENU_KNIGHT_INVENTORY_DESC);
        if(worn.size() == 0) {
            viewer.printLocalizedMessage(Viewer.Message.MENU_KNIGHT_INVENTORY_EMPTY);
            return;
        }

        viewer.print(model.getArmorItems().toString());
        viewer.print(model.getWornClothing().toString());
    }

    //  todo move into other class
    private int getInput() {
        Pattern pattern = Pattern.compile("[0-9]");

        while (true) {
            Matcher matcher = pattern.matcher(scanner.nextLine());
            if(matcher.matches()) {
                viewer.printLocalizedMessage(Viewer.Message.CORRECT_DIGITAL_INPUT_MSG);
                return Integer.parseInt(matcher.group());

            } else {
                viewer.printLocalisedMessage("INVALID_DIGITAL_INPUT_MSG");
            }
        }
    }
}
