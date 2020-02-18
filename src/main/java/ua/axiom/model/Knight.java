package ua.axiom.model;

import ua.axiom.model.wearable.*;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Knight {
    public enum BodyPart {HEAD, CHEST, ARM_L, ARM_R, LEGS, PALM_R, PALM_L, FEET_L, FEET_R }

    private Map<BodyPart, ArmorPiece> armor;
    private Map<BodyPart, ClothingPiece> clothes;

    public Knight() {
        armor = new EnumMap<BodyPart, ArmorPiece>(BodyPart.class);
        clothes = new EnumMap<BodyPart, ClothingPiece>(BodyPart.class);
    }

    public Set<Wearable> getDressedItems() {
        Set<Wearable> result = new HashSet<Wearable>();
        result.addAll(armor.values());
        result.addAll(clothes.values());

        return result;
    }

    public Set<ClothingPiece> getClothes() {
        return new HashSet<>(clothes.values());
    }

    public Set<ArmorPiece> getArmors() {
        return new HashSet<>(armor.values());
    }

    public void addArmorPiece(ArmorPiece armorPiece, BodyPart bodyPart) {
        armor.put(bodyPart, armorPiece);
    }


    private void addClothingPiece(ClothingPiece clothingPiece, BodyPart bodyPart) {
        clothes.put(bodyPart, clothingPiece);
    }

}
