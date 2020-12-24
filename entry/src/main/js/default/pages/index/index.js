import router from '@system.router'
const injectRef = Object.getPrototypeOf(global) || global;
injectRef.regeneratorRuntime = require('@babel/runtime/regenerator');

// abilityType: 0-Ability; 1-Internal Ability
const ABILITY_TYPE_EXTERNAL = 0;
const ABILITY_TYPE_INTERNAL = 1;
// syncOption(Optional, default sync): 0-Sync; 1-Async
const ACTION_SYNC = 0;
const ACTION_ASYNC = 1;
const ACTION_MESSAGE_CODE_OPEN = 1002;
const ACTION_MESSAGE_CODE_OPEN_NATIVE = 1003;

export default {
    data: {
        title: ""
    },
    translate(type) {
        console.log('hello')
        router.push({
            uri: `pages/${type}/index`
        })
    },
    onInit() {
        const keys = Object.keys(this.$app.data)
        console.log(this.$app.data.name)
        console.log('==============')
        this.title = this.$t('strings.world');
    },
    openApp: async function(type) {
        const actionData = {
            type: type
        };
        const action = {};
        action.bundleName = 'com.example.myapplication';
        action.abilityName = 'com.example.myapplication.service.ComputeServiceAbility';
        action.messageCode = ACTION_MESSAGE_CODE_OPEN;
        action.data = actionData;
        action.abilityType = ABILITY_TYPE_EXTERNAL;
        action.syncOption = ACTION_SYNC;
        await FeatureAbility.callAbility(action);
    }
}
