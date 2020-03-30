package com.SP1CYH0T.github.jojomod.entity;

public class EntityBlood implements IEntityBlood {
    float blood;
    public EntityBlood() {

    }
    @Override
    public float getBlood() {
        return this.blood;
    }

    @Override
    public float setBlood(float blood) {
        return this.blood = blood;
    }
}
