package ua.axiom.model;

import sun.security.acl.WorldGroupImpl;
import ua.axiom.model.wearable.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Model {
    private Knight knight;

    public Model() {
        this.knight = new Knight();
    }

    public static Set<Wearable> getAllItems() {
        Set<Wearable> result = new HashSet<>();
        result.addAll(getAllArmorItems());
        result.addAll(getAllClothingItems());

        return result;
    }

    public static Set<ArmorPiece> getAllArmorItems() {
        return new HashSet<>(Arrays.asList(ArmorPiece.values()));
    }

    public static Set<ClothingPiece> getAllClothingItems() {
        return new HashSet<>(Arrays.asList(ClothingPiece.values()));
    }

    public void addArmorPiece(ArmorPiece armorPiece, Knight.BodyPart bodyPart) {
        knight.addArmorPiece(armorPiece, bodyPart);
    }

    public void addClothingPiece(ClothingPiece clothingPiece, Knight.BodyPart bodyPart) {
        knight.addClothingPiece(clothingPiece, bodyPart);
    }

    public Map<Knight.BodyPart, ClothingPiece> getWornClothing() {
        return knight.getClothes();
    }

    public Map<Knight.BodyPart, ArmorPiece> getArmorItems() {
        return knight.getArmors();
    }

    public Map<Knight.BodyPart, List<Wearable>> getAllWornItems() {
        return knight.getDressedItems();
    }

    public <T extends Wearable> List<T> getOrderedWearableObjects(Supplier<T[]> supplier) {
        List<T> result = new ArrayList<>(Arrays.asList(supplier.get()));

        result.sort(Comparator.comparing(Wearable::toString));

        return result;
    }

    public <T extends Wearable> T getWearableByNumber(int number, Supplier<T[]> supplier) {
        List<T> orderedWearableList = getOrderedWearableObjects(supplier);

        return orderedWearableList.get(number);
    }

    public List<Knight.BodyPart> getOrderedBodyParts() {
        List<Knight.BodyPart> result = new ArrayList<>(Arrays.asList(Knight.BodyPart.values()));

        result.sort(Comparator.comparing(Knight.BodyPart::toString));

        return result;
    }

    public List<Wearable> getAllWearableItems() {
        List<Wearable> result = new ArrayList<>();

        result.addAll(Arrays.asList(ClothingPiece.values()));
        result.addAll(Arrays.asList(ArmorPiece.values()));

        return result;
    }

    public List<Wearable> getAllWornItems(Predicate<Wearable> predicate) {
        Map<Knight.BodyPart, List<Wearable>> wornList = getAllWornItems();
        return wornList.values().
                stream().
                flatMap(List::stream).
                filter(predicate).
                collect(Collectors.toList());

    }

    public Knight.BodyPart getBodyPartByNumber(int number) {
        return getOrderedBodyParts().get(number);
    }

    public float getTotalWornPrice() {
        return (float)knight.
                getDressedItems().
                values().
                stream().
                flatMap(List::stream).
                mapToDouble(Wearable::getPrice).
                sum();

    }

    public boolean isRunning() {
        return true;
    }

}
