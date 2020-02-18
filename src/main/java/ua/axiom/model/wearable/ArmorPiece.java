package ua.axiom.model.wearable;


import ua.axiom.model.Knight;

import java.util.Set;

public enum  ArmorPiece implements Wearable {
    ChestPlate(20.F, 4212.F, CHEST_BODY_PART),
    Helmet(5.F, 1200.F, HEAD_BODY_PARTS),
    Leggins(10.F, 1500.F, LEGS_BODY_PART),
    Boots(3.F ,500.F, FEET_BODY_PART)
    ;

    private final float weight;
    private final float price;
    private final Set<Knight.BodyPart> canBeWornAt;

    private ArmorPiece(float weight, float price, Set<Knight.BodyPart> canBeWornAt) {
        this.weight = weight;
        this.price = price;
        this.canBeWornAt = canBeWornAt;
    }

    public float getPrice() {
        return price;
    }

    public float getWeight() {
        return weight;
    }

    public Set<Knight.BodyPart> canBeWornAt() {
        return canBeWornAt;
    }
}