import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.Assert;
import org.junit.Test;
import ua.axiom.model.Knight;
import ua.axiom.model.Model;
import ua.axiom.model.wearable.ArmorPiece;
import ua.axiom.model.wearable.ClothingPiece;

import javax.jws.WebParam;

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
}
