package com.example.myapplication;

import com.example.myapplication.slice.FirstSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class FirstAbility extends Ability {

    private final String TAG = "FirstAbility";

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(FirstSlice.class.getName());
    }
}
