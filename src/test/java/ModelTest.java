import org.junit.Assert;
import org.junit.Test;
import ua.axiom.model.Knight;
import ua.axiom.model.Model;
import ua.axiom.model.wearable.ArmorPiece;
import ua.axiom.model.wearable.ClothingPiece;
import ua.axiom.model.wearable.Wearable;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class ModelTest {
    @Test
    public void testGetAllItems() {
        int getSize = Model.getAllItems().size();

        int actualSize = 0;
        actualSize += ClothingPiece.values().length;
        actualSize += ArmorPiece.values().length;

        Assert.assertEquals(actualSize, getSize);
    }

    @Test
    public void testGetAllArmorPiece() {
        int getSize = Model.getAllArmorItems().size();

        Assert.assertEquals(getSize, ArmorPiece.values().length);
    }

    @Test
    public void testGetAllClothingPiece() {
        int getSize = Model.getAllClothingItems().size();

        Assert.assertEquals(getSize, ClothingPiece.values().length);
    }

    @Test
    public void testAddArmorPiece() {
        Model model = new Model();

        model.addArmorPiece(ArmorPiece.Boots, Knight.BodyPart.FEET_L);
        Assert.assertEquals(model.getAllWornItems(w -> true).size(), 1);

        model.addArmorPiece(ArmorPiece.ChestPlate, Knight.BodyPart.CHEST);
        Assert.assertEquals(model.getAllWornItems(w -> true).size(), 2);

        model.addArmorPiece(ArmorPiece.ChestPlate, Knight.BodyPart.CHEST);
        Assert.assertEquals(model.getAllWornItems(w -> true).size(), 2);
    }

    @Test
    public void testAddClothingPiece() {
        Model model = new Model();

        model.addClothingPiece(ClothingPiece.Hat, Knight.BodyPart.HEAD);
        Assert.assertEquals(model.getAllWornItems((w) -> true).size(), 1);

        model.addClothingPiece(ClothingPiece.Hat, Knight.BodyPart.HEAD);
        Assert.assertEquals(model.getAllWornItems((w) -> true).size(), 1);

        model.addClothingPiece(ClothingPiece.Gloves, Knight.BodyPart.PALM_R);
        Assert.assertEquals(model.getAllWornItems((w) -> true).size(), 2);
    }

    @Test
    public void testGetWornClothing() {
        Model model = new Model();

        model.addClothingPiece(ClothingPiece.Gloves, Knight.BodyPart.HEAD);
        model.addClothingPiece(ClothingPiece.Shoes, Knight.BodyPart.FEET_L);

        Map<Knight.BodyPart, ClothingPiece> resultMap = model.getWornClothing();

        Assert.assertEquals(resultMap.get(Knight.BodyPart.HEAD), ClothingPiece.Gloves);
        Assert.assertEquals(resultMap.get(Knight.BodyPart.FEET_L), ClothingPiece.Shoes);
    }

    @Test
    public void testGetWornArmors() {
        Model model = new Model();

        model.addArmorPiece(ArmorPiece.Leggins, Knight.BodyPart.LEGS);
        model.addArmorPiece(ArmorPiece.ChestPlate, Knight.BodyPart.CHEST);

        Map<Knight.BodyPart, ArmorPiece> resultMap = model.getWornArmors();

        Assert.assertEquals(resultMap.get(Knight.BodyPart.LEGS), ArmorPiece.Leggins);
        Assert.assertEquals(resultMap.get(Knight.BodyPart.CHEST), ArmorPiece.ChestPlate);
    }

    @Test
    public void testGetAllWornItems() {
        Model model = new Model();

        model.addArmorPiece(ArmorPiece.ChestPlate, Knight.BodyPart.CHEST);
        model.addClothingPiece(ClothingPiece.BackPack, Knight.BodyPart.CHEST);
        model.addArmorPiece(ArmorPiece.Helmet, Knight.BodyPart.HEAD);

        Map<Knight.BodyPart, List<Wearable>> allWornItems = model.getAllWornItems();

        Assert.assertEquals(allWornItems.get(Knight.BodyPart.CHEST).size(), 2);
        Assert.assertEquals(allWornItems.get(Knight.BodyPart.HEAD).size(), 1);

        Assert.assertTrue(allWornItems.get(Knight.BodyPart.CHEST).contains(ArmorPiece.ChestPlate));
        Assert.assertTrue(allWornItems.get(Knight.BodyPart.CHEST).contains(ClothingPiece.BackPack));

        Assert.assertTrue(allWornItems.get(Knight.BodyPart.HEAD).contains(ArmorPiece.Helmet));


    }

    @Test
    public void testGetOrderedWearableListContainsAll() {
        List<Wearable> testOrdered = Model.getOrderedWearableObjects(ArmorPiece::values);

        for (ArmorPiece ap : ArmorPiece.values()) {
            Assert.assertTrue(testOrdered.contains(ap));
        }

        testOrdered = Model.getOrderedWearableObjects(ClothingPiece::values);

        for (ClothingPiece cp : ClothingPiece.values()) {
            Assert.assertTrue(testOrdered.contains(cp));
        }
    }

    @Test
    public void testOrderedWearableListConsistency() {
        List<Wearable> testOrderedFirst = Model.getOrderedWearableObjects(ArmorPiece::values);
        List<Wearable> testOrderedSecond = Model.getOrderedWearableObjects(ArmorPiece::values);

        for (int i = 0; i < testOrderedFirst.size() ; ++i) {
            Assert.assertEquals(testOrderedFirst.get(i), testOrderedSecond.get(i));
        }
    }

    @Test
    public void testGetWearableByNumber() {
        List<Wearable> orderedList = Model.getOrderedWearableObjects(ClothingPiece::values);

        int specificNumber = new Random().nextInt(orderedList.size());

        Assert.assertEquals(Model.getOrderedWearableObjects(ClothingPiece::values).get(specificNumber), orderedList.get(specificNumber));
    }

    @Test
    public void testGetAllWornItemsAgain() {
        Model model = new Model();

        model.addArmorPiece(ArmorPiece.Helmet, Knight.BodyPart.HEAD);
        model.addArmorPiece(ArmorPiece.ChestPlate, Knight.BodyPart.CHEST);

        model.addClothingPiece(ClothingPiece.BackPack, Knight.BodyPart.CHEST);
        model.addClothingPiece(ClothingPiece.Hat, Knight.BodyPart.HEAD);

        List<Wearable> aResult = model.getAllWornItems(w -> w.getPrice() < 6000 && w.getPrice() > 1000);

        Assert.assertTrue(aResult.contains(ArmorPiece.Helmet));
        Assert.assertTrue(aResult.contains(ArmorPiece.ChestPlate));

        List<Wearable> notAResult = model.getAllWornItems(w -> w.getPrice() >= 6000 || w.getPrice() <= 1000);

        Assert.assertFalse(notAResult.contains(ArmorPiece.Helmet));
        Assert.assertFalse(notAResult.contains(ArmorPiece.ChestPlate));

        Assert.assertTrue(notAResult.contains(ClothingPiece.BackPack));
        Assert.assertTrue(notAResult.contains(ClothingPiece.Hat));
    }
}
