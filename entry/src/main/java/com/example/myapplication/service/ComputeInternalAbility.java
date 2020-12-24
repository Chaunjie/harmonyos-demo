package com.example.myapplication.service;
// ohos相关接口包
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.ace.ability.AceInternalAbility;
import ohos.app.AbilityContext;
import ohos.rpc.IRemoteObject;
import ohos.rpc.MessageOption;
import ohos.rpc.MessageParcel;
import ohos.rpc.RemoteException;
import ohos.utils.zson.ZSONObject;

import java.util.HashMap;
import java.util.Map;

public class ComputeInternalAbility extends AceInternalAbility {
    private static final String TAG = ComputeInternalAbility.class.getSimpleName();
    private static final String BUNDLE_NAME = "com.example.myapplication";
    private static final String ABILITY_NAME = "com.example.myapplication.service.ComputeInternalAbility";
    private static final int ERROR = -1;
    private static final int SUCCESS = 0;
    private static final int PLUS = 1001;

    private static ComputeInternalAbility instance;
    private AbilityContext abilityContext;

    // 如果多个Ability实例都需要注册当前InternalAbility实例，需要更改构造函数，设定自己的bundleName和abilityName
    public ComputeInternalAbility() {
        super(BUNDLE_NAME, ABILITY_NAME);
    }

    public boolean onRemoteRequest(int code, MessageParcel data, MessageParcel reply, MessageOption option) {
        switch (code) {
            case PLUS: {
                String zsonStr = data.readString();
                RequestParam param = ZSONObject.stringToClass(zsonStr, RequestParam.class);
                // 返回结果当前仅支持String，对于复杂结构可以序列化为ZSON字符串上报
                Map<String, Object> zsonResult = new HashMap<String, Object>();
                zsonResult.put("code", SUCCESS);
                zsonResult.put("abilityResult", param.getFirstNum() + param.getSecondNum());
                // SYNC
                if (option.getFlags() == MessageOption.TF_SYNC) {
                    reply.writeString(ZSONObject.toZSONString(zsonResult));
                } else {
                    // ASYNC
                    MessageParcel reponseData = MessageParcel.obtain();
                    reponseData.writeString(ZSONObject.toZSONString(zsonResult));
                    IRemoteObject remoteReply = reply.readRemoteObject();
                    try {
                        remoteReply.sendRequest(0, reponseData, MessageParcel.obtain(), new MessageOption());
                        reponseData.reclaim();
                    } catch (RemoteException exception) {
                        return false;
                    }
                }
                break;
            }
            default: {
                reply.writeString("service not defined");
                return false;
            }
        }
        return true;
    }

    // Internal ability registration.
    public static void register(AbilityContext abilityContext) {
        instance = new ComputeInternalAbility();
        instance.onRegister(abilityContext);
    }

    private void onRegister(AbilityContext abilityContext) {
        this.abilityContext = abilityContext;
        this.setInternalAbilityHandler((code, data, reply, option) -> {
            return this.onRemoteRequest(code, data, reply, option);
        });
    }

    // Internal ability deregistration.
    public static void deregister() {
        instance.onDeregister();
    }

    private void onDeregister() {
        abilityContext = null;
        this.setInternalAbilityHandler(null);
    }
}