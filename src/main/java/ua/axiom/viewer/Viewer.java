package ua.axiom.viewer;

import ua.axiom.model.Knight;
import ua.axiom.model.Model;
import ua.axiom.model.wearable.ArmorPiece;
import ua.axiom.model.wearable.Wearable;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Supplier;

public class Viewer {
    public enum Message {
        WELCOME_MSG, QUESTION_MSG,
        MAIN_MENU_REQUEST_MSG,
        CORRECT_DIGITAL_INPUT_MSG, INVALID_DIGITAL_INPUT_MSG,
        ARMOR_SELECTION_MENU_MSG,
        CLOTHING_SELECTION_MENU_MSG,
        BODY_PART_SELECTION_MENU_MSG,
        MENU_KNIGHT_INVENTORY_DESC,
        MENU_KNIGHT_INVENTORY_EMPTY,
        MENU_CANNOT_WEAR_ITEM_ON_BODYPART,
        WORD_PRICE,
        MENU_SHOW_STATISTICS_MSG,
        TOTAL_WORN_PRICE_MSG,
        WORD_CLOTHES,
        WORD_ARMOR,
        MSG_SORTED_ITEMS_SHOW,
        MSG_UPPER_BOUND_QUESTION,
        MSG_LOVER_BOUND_QUESTION
    }

    private Model model;
    private PrintStream printStream;

    public void printLocalisedMessage(Message message) {
        print(messageContainer.getLocalisedMessage(message.toString()) + "\n");
    }

    public void printLocalisedMessage(String message) {
        printLocalisedMessage(message, "\n");
    }

    public void printLocalisedMessage(String message, String postfix) {
        print(messageContainer.getLocalisedMessage(message) + postfix);
    }

    @Deprecated
    public String getArmorSelectionMessage() {
        StringBuilder result = new StringBuilder();
        List<ArmorPiece> armors = model.getOrderedWearableObjects(ArmorPiece::values);

        for (int i = 0; i<armors.size(); ++i) {
            result.append(i).append(". ");
            ArmorPiece ap = armors.get(i);

            result.append(messageContainer.getLocalisedMessage(ap.toString()));
            result.append(", price - ").append(ap.getPrice()).append(",\n");
        }

        return result.toString();
    }

    public String getWearableSelectionMsg(Supplier<Wearable[]> supplier) {
        StringBuilder result = new StringBuilder();
        List<Wearable> wearables = model.getOrderedWearableObjects(supplier);

        for(int i = 0; i < wearables.size() ; ++i) {
            result.append(i).append(". ");
            Wearable w = wearables.get(i);

            result.append(messageContainer.getLocalisedMessage(w.toString()));
            result.append(", ").append(messageContainer.getLocalisedMessage(Message.WORD_PRICE.toString())).append(" - ");
            result.append(w.getPrice()).append(",\n");
        }

        return result.toString();
    }

    public String getBodyPartSelectionMessage() {
        StringBuilder result = new StringBuilder();
        List<Knight.BodyPart> bodyParts = model.getOrderedBodyParts();

        for (int i = 0 ; i<bodyParts.size() ; ++i) {
            result.append(i).append(". ");
            Knight.BodyPart bp = bodyParts.get(i);

            result.append(messageContainer.getLocalisedMessage(bp.toString()));
            result.append("\n");
        }

        return result.toString();
    }

    public <T extends Wearable> void printLocalisedWearableMap(Map<Knight.BodyPart, T> map) {
        for(Map.Entry entry : map.entrySet()) {
            printLocalisedMessage(entry.getKey().toString(), " - ");
            printLocalisedMessage(entry.getValue().toString(), ";");
            print("\n");
        }
    }

    public void showItemList(List<Wearable> items) {
        for (Wearable item : items) {
            printLocalisedMessage(item.toString(), ", ");
        }
    }

    public void print(String s) {
        printStream.print(s);
    }

    private LocalisedMessageContainer messageContainer;

    private Viewer(Model model, ResourceBundle resourceBundle, PrintStream printStream) {
        this.model = model;
        this.printStream = printStream;
        this.messageContainer = new LocalisedMessageContainer(resourceBundle);
    }

    private Viewer(Model model, ResourceBundle resourceBundle) {
        this(model, resourceBundle, System.out);
    }

    public static class ViewerBuilder {

        private Model model;

        private ResourceBundle resourceBundle;
        private PrintStream outStream = System.out;


        public ViewerBuilder(Model model, ResourceBundle resourceBundle) {
            this.model = model;
            this.resourceBundle = resourceBundle;
        }

        public ViewerBuilder setPrintStream(PrintStream stream) {
            outStream = stream;
            return this;
        }
        public Viewer build() {
            Viewer viewer = new Viewer(model, resourceBundle);
            viewer.printStream = outStream ;
            return viewer;
        }

    }
}
