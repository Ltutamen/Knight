package ua.axiom.model;

import ua.axiom.model.wearable.ArmorPiece;
import ua.axiom.model.wearable.ClothingPiece;
import ua.axiom.model.wearable.Wearable;

import java.util.HashSet;
import java.util.Set;

public class Knight {
    private Set<ArmorPiece> armor;
    private Set<ClothingPiece> clothes;

    public Set<Wearable> getDressedItems() {
        Set<Wearable> result = new HashSet<Wearable>();
        result.addAll(armor);
        result.addAll(clothes);

        return result;
    }

    public void addArmorPiece(ArmorPiece armorPiece) {
        armor.add(armorPiece);
    }

    private void addClothingPiece(ClothingPiece clothingPiece) {
        clothes.add(clothingPiece);
    }

}
