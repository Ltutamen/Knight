package ua.axiom.model;

import ua.axiom.model.wearable.ArmorPiece;

public class Model {
    private Knight knight;

    public Model() {
        this.knight = new Knight();
    }

    public void addArmorPiece(ArmorPiece armorPiece) {

    }

    public boolean isRunning() {
        return true;
    }
}
