package ua.axiom.model.wearable;

import ua.axiom.model.Knight;

import java.util.*;

public enum ClothingPiece implements Wearable {
    Hat(7811.F, HEAD_BODY_PARTS),
    BackPack(150.F, CHEST_BODY_PART),
    Shoes(780.F, FEET_BODY_PART),
    Gloves(410.F, PALM_BODY_PART);

    private final float price;
    private final Set<Knight.BodyPart> canBeWornAt;

    ClothingPiece(float price, Set<Knight.BodyPart> canBeWornAt) {
        this.price = price;
        this.canBeWornAt = canBeWornAt;
    }

    public float getPrice() {
        return price;
    }

    public float getWeight() {
        return 0;
    }

    public Set<Knight.BodyPart> canBeWornAt() {
        return canBeWornAt;
    }
}
