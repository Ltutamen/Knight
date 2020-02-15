package ua.axiom.model.wearable;

import ua.axiom.model.Knight;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static ua.axiom.model.Knight.BODY_PART.*;



public interface Wearable {
    Set<Knight.BODY_PART> HEAD_BODY_PARTS
            = Collections.unmodifiableSet(new HashSet<Knight.BODY_PART>(Arrays.asList(HEAD)));
    Set<Knight.BODY_PART> CHEST_BODY_PART
            = Collections.unmodifiableSet(new HashSet<Knight.BODY_PART>(Arrays.asList(CHEST)));
    Set<Knight.BODY_PART> LEGS_BODY_PART
            = Collections.unmodifiableSet(new HashSet<Knight.BODY_PART>(Arrays.asList(CHEST)));
    Set<Knight.BODY_PART> HANDS_BODY_PART
            = Collections.unmodifiableSet(new HashSet<Knight.BODY_PART>(Arrays.asList(ARM_L, ARM_R)));
    Set<Knight.BODY_PART> PALM_BODY_PART
            = Collections.unmodifiableSet(new HashSet<Knight.BODY_PART>(Arrays.asList(PALM_L, PALM_R)));
    Set<Knight.BODY_PART> FEET_BODY_PART
            = Collections.unmodifiableSet(new HashSet<Knight.BODY_PART>(Arrays.asList(FEET_L, FEET_R)));

    float getPrice();

    float getWeight();

    Set<Knight.BODY_PART> canBeWornAt();

}
