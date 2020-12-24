const injectRef = Object.getPrototypeOf(global) || global;
injectRef.regeneratorRuntime = require('@babel/runtime/regenerator');

// abilityType: 0-Ability; 1-Internal Ability
const ABILITY_TYPE_EXTERNAL = 0;
const ABILITY_TYPE_INTERNAL = 1;
// syncOption(Optional, default sync): 0-Sync; 1-Async
const ACTION_SYNC = 0;
const ACTION_ASYNC = 1;
const ACTION_MESSAGE_CODE_PLUS = 1001;

export default {
    data: {
        title: 'World',
        num1: 1024,
        num2: 2048,
        result1: '',
        result2: ''
    },
    changeNum1(ev) {
        console.log(ev.value)
        this.num1 = ev.value
    },
    changeNum2(ev) {
        console.log(ev.value)
        this.num2 = ev.value
    },
    computedAbility: async function() {
        var actionData = {
            firstNum: this.num1,
            secondNum: this.num2
        };

        var action = {};
        action.bundleName = 'com.example.myapplication';
        action.abilityName = 'com.example.myapplication.service.ComputeServiceAbility';
        action.messageCode = ACTION_MESSAGE_CODE_PLUS;
        action.data = actionData;
        action.abilityType = ABILITY_TYPE_EXTERNAL;
        action.syncOption = ACTION_SYNC;

        var result = await FeatureAbility.callAbility(action);
        var ret = JSON.parse(result);
        if (ret.code == 0) {
            this.result1 = ret.abilityResult
            console.info('plus result is:' + JSON.stringify(ret.abilityResult));
        } else {
            console.error('plus error code:' + JSON.stringify(ret.code));
        }
    },
    computedInternalAbility: async function() {
        var actionData = {
            firstNum: this.num1,
            secondNum: this.num2
        };

        var action = {};
        action.bundleName = 'com.example.myapplication';
        action.abilityName = 'com.example.myapplication.service.ComputeInternalAbility';
        action.messageCode = ACTION_MESSAGE_CODE_PLUS;
        action.data = actionData;
        action.abilityType = ABILITY_TYPE_INTERNAL;
        action.syncOption = ACTION_SYNC;

        var result = await FeatureAbility.callAbility(action);
        var ret = JSON.parse(result);
        if (ret.code == 0) {
            this.result2 = ret.abilityResult
            console.info('plus result is:' + JSON.stringify(ret.abilityResult));
        } else {
            console.error('plus error code:' + JSON.stringify(ret.code));
        }
    }
}
