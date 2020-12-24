package com.example.myapplication;

import com.example.myapplication.service.ComputeInternalAbility;
import com.example.myapplication.utils.LogUtil;
import ohos.ace.ability.AceAbility;
import ohos.aafwk.content.Intent;


public class MainAbility extends AceAbility {
    @Override
    public void onStart(Intent intent) {
        LogUtil.debugInfo("Ability Start");
        setInstanceName("default");
        ComputeInternalAbility.register(this);
        super.onStart(intent);
//        ComputeServiceAbility.register(this);
    }

    @Override
    public void onStop() {
        LogUtil.debugInfo("Ability Stop");
        ComputeInternalAbility.deregister();
        super.onStop();
    }
}
