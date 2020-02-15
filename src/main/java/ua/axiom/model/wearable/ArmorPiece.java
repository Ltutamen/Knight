package ua.axiom.model.wearable;

public enum  ArmorPiece implements Wearable {
    ChestPlate(20.F, 4212.F),
    Helmet(5.F, 1200.F),
    Leggins(10.F, 1500.F),
    Boots(3.F ,500.F)
    ;

    private final float weight;
    private final float price;


    private ArmorPiece(float weight, float price) {
        this.weight = weight;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}