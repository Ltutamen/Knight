package ua.axiom.model;

import ua.axiom.model.wearable.*;

import java.util.*;

public class Knight {
    public enum BodyPart {HEAD, CHEST, ARM_L, ARM_R, LEGS, PALM_R, PALM_L, FEET_L, FEET_R }

    private Map<BodyPart, ArmorPiece> armor;
    private Map<BodyPart, ClothingPiece> clothes;

    public Knight() {
        armor = new EnumMap<BodyPart, ArmorPiece>(BodyPart.class);
        clothes = new EnumMap<BodyPart, ClothingPiece>(BodyPart.class);
    }

    public Map<BodyPart, List<Wearable>> getDressedItems() {
        Map<BodyPart, List<Wearable>> result = new HashMap<>();
        for(BodyPart bp : BodyPart.values()) {
            List<Wearable> currentBodyPartList = new ArrayList<>(2);
            currentBodyPartList.add(armor.get(bp));
            currentBodyPartList.add(clothes.get(bp));

            result.put(bp, currentBodyPartList);

        }

        return result;
    }

    public Map<BodyPart, ClothingPiece> getClothes() {
        return clothes;
    }

    public Map<BodyPart, ArmorPiece> getArmors() {
        return armor;
    }

    public void addArmorPiece(ArmorPiece armorPiece, BodyPart bodyPart) {
        armor.put(bodyPart, armorPiece);
    }

    public void addClothingPiece(ClothingPiece clothingPiece, BodyPart bodyPart) {
        clothes.put(bodyPart, clothingPiece);
    }


}
