package ua.axiom.model;

import ua.axiom.model.wearable.*;

import java.util.*;

public class Model {
    private Knight knight;

    public Model() {
        this.knight = new Knight();
    }

    public void addArmorPiece(ArmorPiece armorPiece, Knight.BodyPart bodyPart) {
        knight.addArmorPiece(armorPiece, bodyPart);
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

    public static Set<Wearable> getAllItems() {
        Set<Wearable> result = new HashSet<>();
        result.addAll(getAllArmorItems());
        result.addAll(getAllClothingPiece());

        return result;
    }

    public static Set<ArmorPiece> getAllArmorItems() {
        return new HashSet<>(Arrays.asList(ArmorPiece.values()));
    }

    public static Set<ClothingPiece> getAllClothingPiece() {
        return new HashSet<>(Arrays.asList(ClothingPiece.values()));
    }

    public float getTotalWornPrice() {
        return (float)knight.getDressedItems().stream().mapToDouble(Wearable::getPrice).reduce(0., Double::sum);
    }

    public boolean isRunning() {
        return true;
    }

}
