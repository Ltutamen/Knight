package ua.axiom.model;

import ua.axiom.model.wearable.*;

import java.util.Set;

public class Model {
    private Knight knight;

    public Model() {
        this.knight = new Knight();
    }

    public void addArmorPiece(ArmorPiece armorPiece) {

    }

    public Set<ClothingPiece> getWornClothing() {
        return knight.getClothes();
    }

    public Set<ArmorPiece> getArmorItems() {
        return knight.getArmors();
    }

    public Set<Wearable> getAllWornItems() {
        return knight.getDressedItems();
    }

    public float getTotalWornPrice() {
        return (float)knight.getDressedItems().stream().mapToDouble(Wearable::getPrice).reduce(0., Double::sum);
    }

    public boolean isRunning() {
        return true;
    }



}
