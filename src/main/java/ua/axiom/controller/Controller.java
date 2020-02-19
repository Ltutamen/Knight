package ua.axiom.controller;

import ua.axiom.model.*;
import ua.axiom.model.wearable.*;
import ua.axiom.viewer.Viewer;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private Model model;
    private Viewer viewer;
    private Scanner scanner;

    public Controller(Model model, Viewer viewer, Scanner scanner) {
        this.model = model;
        this.viewer = viewer;
        this.scanner = scanner;
    }

    /**
     * runs REPL, showing main menu of the programm,
     * with options to add Items, show statistics ot exit
     */
    public void processLoop() {
        viewer.printLocalisedMessage(Viewer.Message.WELCOME_MSG);

        while (true) {
            showKnightStatus();
            viewer.printLocalisedMessage(Viewer.Message.QUESTION_MSG);
            mainMenuLoop();
        }
    }

    /**
     * Shows main menu and calls other menu methods
     * @see Controller.mainMenuLoop()
     * @see Controller.addClothingitemLoop()
     * @see Controller.getStatisticsLoop()
     */
    private void mainMenuLoop() {
        viewer.printLocalisedMessage(Viewer.Message.MAIN_MENU_REQUEST_MSG);
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
            case 4: {
                System.exit(0);
            }
            default: {
                //  todo refactor move into method
                viewer.printLocalisedMessage(Viewer.Message.INVALID_DIGITAL_INPUT_MSG);
            }
        }
    }

    /**
     * Runs armor addition loop
     */
    private void addArmorItemLoop() {
        //  todo refactor
        viewer.printLocalisedMessage(Viewer.Message.ARMOR_SELECTION_MENU_MSG);
        viewer.print(viewer.getWearableSelectionMsg(ArmorPiece::values));

        int armorItemToAdd = getInput();
        int bodyPartToAdd = bodyPartSelectionLoop(model.getWearableByNumber(armorItemToAdd, ArmorPiece::values));

        model.addArmorPiece(model.getWearableByNumber(armorItemToAdd, ArmorPiece::values), model.getBodyPartByNumber(bodyPartToAdd));
    }

    /**
     * Runs clothing addition loop
     */
    private void addClothingItemLoop() {
        //  todo refactor
        viewer.printLocalisedMessage(Viewer.Message.CLOTHING_SELECTION_MENU_MSG);
        viewer.print(viewer.getWearableSelectionMsg(ClothingPiece::values));

        int clothingItemToAdd = getInput();
        int bodyPartToAdd = bodyPartSelectionLoop(model.getWearableByNumber(clothingItemToAdd, ClothingPiece::values));

        model.addClothingPiece(model.getWearableByNumber(clothingItemToAdd, ClothingPiece::values), model.getBodyPartByNumber(bodyPartToAdd));

    }

    public void getStatisticsLoop() {
        showKnightStatus();

        viewer.printLocalisedMessage(Viewer.Message.MENU_SHOW_STATISTICS_MSG);
        int statisticEntry = getInput();

        switch (statisticEntry) {
            case 1: {
                showWornItemsPrice();
                break;
            }
            case 2: {
                showSortedWornItems();
                break;
            }
            case 3: {
                showItemsInPriceRange();
                break;
            }
        }
    }

    /**
     * Shows prices for all worn items
     */
    private void showWornItemsPrice() {
        viewer.printLocalisedMessage(Viewer.Message.TOTAL_WORN_PRICE_MSG);
        viewer.print("" + model.getTotalWornPrice() + "\n");
    }

    /**
     * Shows Knight items, sorted by weight
     */
    private void showSortedWornItems() {
        viewer.printLocalisedMessage(Viewer.Message.MSG_SORTED_ITEMS_SHOW);

        List<Wearable> wornItems = model.
                getAllWornItems(w -> true).
                stream().
                sorted(Comparator.comparing(Wearable::getWeight)).
                collect(Collectors.toList());

        viewer.showItemList(wornItems);
    }

    /**
     * Allows user to enter two integer price values,
     * and shows all items in Knight's inventory, that lies in given interval
     */
    private void showItemsInPriceRange() {
        viewer.printLocalisedMessage(Viewer.Message.MSG_UPPER_BOUND_QUESTION);
        int upperBound = getInput();
        viewer.printLocalisedMessage(Viewer.Message.MSG_LOVER_BOUND_QUESTION);
        int loverBound = getInput();

        List<Wearable> wornItems = model.getAllWornItems(w -> w.getPrice() > loverBound && w.getPrice() < upperBound);

        viewer.showItemList(wornItems);
    }


    /**
     * @param itemToWear returned number represents such BodyPart, that
     *                   given item can be put on it
     * @return number that represents specific body part, that was entered by user
     * and which can be used to get this BodyPart again
     * @see ua.axiom.model.Model.getOrderedBodyParts()
     */
    @Deprecated
    public int bodyPartSelectionLoop(Wearable itemToWear) {
        //  todo print msg that excludes unfitting body parts
        while (true) {
            viewer.printLocalisedMessage(Viewer.Message.BODY_PART_SELECTION_MENU_MSG);
            viewer.print(viewer.getBodyPartSelectionMessage());

            int selectedBodyPart = getInput();
            if(itemToWear.canBeWornAt().contains(model.getBodyPartByNumber(selectedBodyPart))) {
                return selectedBodyPart;
            }
            viewer.printLocalisedMessage(Viewer.Message.MENU_CANNOT_WEAR_ITEM_ON_BODYPART);
        }
    }

    /**
     * @param predicate additional test applied to input
     * @return number that represents specific BodyPart, that was entered by the user,
     * and passes Predicate test
     * @see Controller.bodyPartSelectionLoop()
     * @see ua.axiom.model.Model.getOrderedBodyParts()
     */
    public int bodyPartSelectionLoop(Predicate<Knight.BodyPart> predicate) {
        while (true) {
            viewer.printLocalisedMessage(Viewer.Message.BODY_PART_SELECTION_MENU_MSG);
            viewer.print(viewer.getBodyPartSelectionMessage());

            int selectedBodyPart = getInput();
            if(predicate.test(model.getBodyPartByNumber(selectedBodyPart))) {
                return selectedBodyPart;
            }
            viewer.printLocalisedMessage(Viewer.Message.MENU_CANNOT_WEAR_ITEM_ON_BODYPART);
        }
    }


    /**
     * Shows all items in knight's inventory
     */
    public void showKnightStatus() {
        Map<Knight.BodyPart, List<Wearable>> worn = model.getAllWornItems();
        viewer.printLocalisedMessage(Viewer.Message.MENU_KNIGHT_INVENTORY_DESC);
        if(worn.size() == 0) {
            viewer.printLocalisedMessage(Viewer.Message.MENU_KNIGHT_INVENTORY_EMPTY);
            return;
        }

        viewer.printLocalisedMessage(Viewer.Message.WORD_ARMOR);
        viewer.print(model.getArmorItems().toString() + "\n");
        viewer.printLocalisedMessage(Viewer.Message.WORD_CLOTHES);
        viewer.print(model.getWornClothing().toString() + "\n");
    }

    /**
     * Reads input from the keyboard, that can be any positive integer value
     * @return integer value, entered by user
     */
    private int getInput() {
        Pattern pattern = Pattern.compile("[0-9]+");

        while (true) {
            Matcher matcher = pattern.matcher(scanner.nextLine());
            if(matcher.matches()) {
                viewer.printLocalisedMessage(Viewer.Message.CORRECT_DIGITAL_INPUT_MSG);
                return Integer.parseInt(matcher.group());

            } else {
                viewer.printLocalisedMessage("INVALID_DIGITAL_INPUT_MSG");
            }
        }
    }
}
