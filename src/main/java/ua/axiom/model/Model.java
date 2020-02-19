package ua.axiom.model;

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

    /**
     * @return set of all possible Wearable items
     */
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

    /**
     * Wears given armorPiece to the given Knight bodyPart
     * @param armorPiece Wearable item to place
     * @param bodyPart to place armorPiece at
     */
    public void addArmorPiece(ArmorPiece armorPiece, Knight.BodyPart bodyPart) {
        knight.addArmorPiece(armorPiece, bodyPart);
    }

    /**
     * Wears given clothingPiece to the given Knight bodyPart
     * @param clothingPiece Wearable item to place
     * @param bodyPart to place clothingPiece at
     */
    public void addClothingPiece(ClothingPiece clothingPiece, Knight.BodyPart bodyPart) {
        knight.addClothingPiece(clothingPiece, bodyPart);
    }

    /**
     * @return map from Knight's BodyPart to ClothingItem, currently worn on it
     */
    public Map<Knight.BodyPart, ClothingPiece> getWornClothing() {
        return knight.getClothes();
    }

    /**
     * @return map from Knight's BodyPart to ArmorPiece, currently worn on it
     */
    public Map<Knight.BodyPart, ArmorPiece> getArmorItems() {
        return knight.getArmors();
    }

    /**
     * @return content of Knight inventory: map from bodyPart to worn items
     */
    public Map<Knight.BodyPart, List<Wearable>> getAllWornItems() {
        return knight.getDressedItems();
    }

    /**
     * @param supplier source to take Wearable from
     * @return fixed-ordered list of elements from the supplier
     */
    public <T extends Wearable> List<T> getOrderedWearableObjects(Supplier<T[]> supplier) {
        List<T> result = new ArrayList<>(Arrays.asList(supplier.get()));

        result.sort(Comparator.comparing(Wearable::toString));

        return result;
    }

    /**
     * @param number that identifies Wearable, @see getOrderedWearableObjects
     * @param supplier
     * @param <T>
     * @return
     */
    public <T extends Wearable> T getWearableByNumber(int number, Supplier<T[]> supplier) {
        List<T> orderedWearableList = getOrderedWearableObjects(supplier);

        return orderedWearableList.get(number);
    }

    public List<Knight.BodyPart> getOrderedBodyParts() {
        List<Knight.BodyPart> result = new ArrayList<>(Arrays.asList(Knight.BodyPart.values()));

        result.sort(Comparator.comparing(Knight.BodyPart::toString));

        return result;
    }

    /**
     * @return list of all Wearable items, that exist
     */
    public List<Wearable> getAllWearableItems() {
        List<Wearable> result = new ArrayList<>();

        result.addAll(Arrays.asList(ClothingPiece.values()));
        result.addAll(Arrays.asList(ArmorPiece.values()));

        return result;
    }

    /**
     * @param predicate matches items to return
     * @return all items, worn by Knight at this moment, that match predicate
     */
    public List<Wearable> getAllWornItems(Predicate<Wearable> predicate) {
        Map<Knight.BodyPart, List<Wearable>> wornList = getAllWornItems();
        return wornList.values().
                stream().
                flatMap(List::stream).
                filter(predicate).
                collect(Collectors.toList());

    }

    /**
     * @param number that defines specific body part during runtime
     * @return BodyPart, that can be accessed again thru this method and number
     */
    public Knight.BodyPart getBodyPartByNumber(int number) {
        return getOrderedBodyParts().get(number);
    }

    /**
     * @return total price of all Wearable items, that Knight is currently wearing
     */
    public float getTotalWornPrice() {
        return (float)knight.
                getDressedItems().
                values().
                stream().
                flatMap(List::stream).
                mapToDouble(Wearable::getPrice).
                sum();

    }

}
