package ua.axiom.model;

import ua.axiom.model.wearable.ArmorPiece;
import ua.axiom.model.wearable.ClothingPiece;
import ua.axiom.model.wearable.Wearable;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Knight {
    public enum BODY_PART {HEAD, CHEST, ARM_L, ARM_R, LEGS, PALM_R, PALM_L, FEET_L, FEET_R }

    private Map<BODY_PART, ArmorPiece> armor;
    private Map<BODY_PART, ClothingPiece> clothes;

    public Knight() {
        armor = new EnumMap<BODY_PART, ArmorPiece>(BODY_PART.class);
        clothes = new EnumMap<BODY_PART, ClothingPiece>(BODY_PART.class);
    }

    public Set<Wearable> getDressedItems() {
        Set<Wearable> result = new HashSet<Wearable>();
        result.addAll(armor.values());
        result.addAll(clothes.values());

        return result;
    }

    public void addArmorPiece(ArmorPiece armorPiece, BODY_PART bodyPart) {
        armor.put(bodyPart, armorPiece);
    }

    private void addClothingPiece(ClothingPiece clothingPiece, BODY_PART bodyPart) {
        clothes.put(bodyPart, clothingPiece);
    }

}
