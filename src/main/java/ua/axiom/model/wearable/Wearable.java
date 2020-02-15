package ua.axiom.model.wearable;

public interface Wearable {
    enum BODY_PART {HEAD, CHEST, ARM_L, ARM_R, LEG_R, LEG_L, PALM_R, PALM_L, FEET_L, FEET_R }

    float getPrice();
}
