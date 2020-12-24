package com.example.myapplication;

import ohos.aafwk.content.Intent;
import ohos.ace.ability.AceAbility;

public class JSAbility extends AceAbility {
    @Override
    public void onStart(Intent intent) {
        setInstanceName("default2");
        super.onStart(intent);

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}