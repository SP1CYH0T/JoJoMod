package com.SP1CYH0T.github.jojomod.player;

public class PlayerBlood implements IPlayerBlood{

    public float blood = 0.0f;
    public float maxBlood = 0.0f;
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

    @Override
    public float adjustMaxBlood(float adjustment) {
        return this.maxBlood + adjustment;
    }

    @Override
    public float adjustBlood(float adjustment) {
        return this.blood + adjustment;
    }

    //Might also just do adjustMaxBlood(-int)
    @Override
    public float decreaseMaxBlood(float decreasement) {
        return this.maxBlood -= decreasement;
    }

    //Might also just do adjustBlood(-int)
    @Override
    public float decreaseBlood(float decreasement) {
        return this.blood -= decreasement;
    }
}
