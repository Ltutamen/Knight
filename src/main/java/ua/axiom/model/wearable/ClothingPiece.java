package ua.axiom.model.wearable;

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

    public String getName() {
        return this.toString();
    }
}
