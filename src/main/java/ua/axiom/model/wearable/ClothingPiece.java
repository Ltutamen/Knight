package ua.axiom.model.wearable;

import ua.axiom.model.Knight;

import java.util.Set;

public enum ClothingPiece implements Wearable {
    Hat(7811.F),
    BackPack(150.F),
    Shoes(780.F),
    Gloves(410.F);

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

    public Set<Knight.BODY_PART> canBeWornAt() {
        return null;
    }

    public String getName() {
        return this.toString();
    }
}
