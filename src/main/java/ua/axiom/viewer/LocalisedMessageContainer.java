package ua.axiom.viewer;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.model.Knight;
import ua.axiom.model.Model;
import ua.axiom.model.wearable.Wearable;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class LocalisedMessageContainer {
    private final Map<String, String> localizedMessagesMap = new HashMap<String, String>();

    public LocalisedMessageContainer(ResourceBundle resources) {
        try {
            for (Viewer.Message message : Viewer.Message.values()) {
                localizedMessagesMap.put(message.toString(), resources.getString(message.toString()));
            }

            for (Wearable w : Model.getAllItems()) {
                localizedMessagesMap.put(w.toString(), resources.getString(w.toString()));
            }

            for (Knight.BodyPart bp : Knight.BodyPart.values()) {
                localizedMessagesMap.put(bp.toString(), resources.getString(bp.toString()));
            }

        } catch (NullPointerException npe) {
            System.err.println("No localisation data for entry");
            throw npe;
        }
    }

    public String getLocalisedMessage(String descriptor) {
        return localizedMessagesMap.get(descriptor);
    }

    private static <T> Set<Class<? extends T>> getImplementations() {
        throw new NotImplementedException();
    }
}
