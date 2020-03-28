package com.SP1CYH0T.github.jojomod.player;

public class PlayerBlood implements IPlayerBlood{

    public float blood = 0.0f;
    public float maxBlood = 10.0f;
    public PlayerBlood() {
    }
    @Override
    public float getBlood() {
        return this.blood;
    }

    @Override
    public float getMaxBlood() {
        return this.maxBlood;
    }

    @Override
    public float setBlood(float blood) {
        return this.blood = blood;
    }

    @Override
    public float setMaxBlood(float maxBlood) {
        return this.maxBlood = maxBlood;
    }
}
