package ua.axiom.model.wearable;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.model.Knight;

import java.util.*;

public enum ClothingPiece implements Wearable {
    Hat(7811.F),
    BackPack(150.F),
    Shoes(780.F),
    Gloves(410.F);

    private static Map<Locale, Map<String, String>> localisations = new HashMap<>();

    private final float price;

    ClothingPiece(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public float getWeight() {
        return 0;
    }

    public Set<Knight.BodyPart> canBeWornAt() {
        throw new NotImplementedException();
    }
}
