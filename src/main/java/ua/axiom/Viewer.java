package ua.axiom;

import java.io.PrintStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Viewer {
    public enum Message{WELCOME_MSG, QUESTION_MSG,
        MAIN_MENU_REQUEST_MSG,
        CORRECT_DIGITAL_INPUT_MSG, INVALID_DIGITAL_INPUT_MSG,
        ARMOR_SELECTION_MENU_MSG,
        MENU_KNIGHT_INVENTORY_DESC,
        MENU_KNIGHT_INVENTORY_EMPTY}

    private final Map<Message, String> localizedMessagesMap = new EnumMap<>(Message.class);

    private PrintStream printStream;

    private Viewer(PrintStream printStream) {
        this.printStream = printStream;
    }

    private Viewer() {
        this(System.out);
    }

    public void setLocalisation(ResourceBundle resources) {
        for (Message message : Message.values()) {
            try {
                localizedMessagesMap.put(message, resources.getString(message.toString()));
            } catch (NullPointerException npe) {
                System.err.println("No localisation data for entry <" + message.toString() + ">");
                localizedMessagesMap.put(message, "errorOccurred");
                throw npe;
            }
        }
    }

    public void printLocalizedMessage(Message message) {
        print(getLocalisedMessage(message));
    }

    public String getLocalisedMessage(Message message) {
        return localizedMessagesMap.get(message);
    }

    public void print(String s) {
        printStream.println(s);
    }


    public static class ViewerBuilder {
        private ResourceBundle resourceBundle;
        private PrintStream outStream = System.out;

        public ViewerBuilder() {
        }

        public ViewerBuilder setPrintStream(PrintStream stream) {
            outStream = stream;
            return this;
        }

        public ViewerBuilder setResourceBundle(ResourceBundle resourceBundle) {
            this.resourceBundle = resourceBundle;
            return this;
        }

        public Viewer build() {
            Viewer viewer = new Viewer();
            viewer.printStream = outStream ;
            viewer.setLocalisation(resourceBundle);
            return viewer;
        }
    }
}
