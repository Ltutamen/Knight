package ua.axiom.model.wearable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ua.axiom.model.Knight;
import static ua.axiom.model.Knight.BodyPart.*;

public interface Wearable {
    Set<Knight.BodyPart> HEAD_BODY_PARTS
            = Collections.unmodifiableSet(new HashSet<Knight.BodyPart>(Collections.singletonList(HEAD)));
    Set<Knight.BodyPart> CHEST_BODY_PART
            = Collections.unmodifiableSet(new HashSet<Knight.BodyPart>(Collections.singletonList(CHEST)));
    Set<Knight.BodyPart> LEGS_BODY_PART
            = Collections.unmodifiableSet(new HashSet<Knight.BodyPart>(Collections.singletonList(CHEST)));
    Set<Knight.BodyPart> HANDS_BODY_PART
            = Collections.unmodifiableSet(new HashSet<Knight.BodyPart>(Arrays.asList(ARM_L, ARM_R)));
    Set<Knight.BodyPart> PALM_BODY_PART
            = Collections.unmodifiableSet(new HashSet<Knight.BodyPart>(Arrays.asList(PALM_L, PALM_R)));
    Set<Knight.BodyPart> FEET_BODY_PART
            = Collections.unmodifiableSet(new HashSet<Knight.BodyPart>(Arrays.asList(FEET_L, FEET_R)));

    abstract float getPrice();

    default float getWeight() {
        return 0.F;
    }

    abstract Set<Knight.BodyPart> canBeWornAt();

}
