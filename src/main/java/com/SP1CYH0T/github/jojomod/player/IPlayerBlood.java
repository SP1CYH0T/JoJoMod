package com.SP1CYH0T.github.jojomod.player;

public interface IPlayerBlood {
    float getBlood();
    float getMaxBlood();

    float setBlood(float blood);
    float setMaxBlood(float maxBlood);

    float adjustMaxBlood(float adjustment);
    float adjustBlood(float adjustment);

    float decreaseMaxBlood(float decreasement);
    float decreaseBlood(float decreasement);
}
