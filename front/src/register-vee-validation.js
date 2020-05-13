import {
    extend,
    ValidationObserver,
    ValidationProvider,
    setInteractionMode
  } from 'vee-validate'
  import * as defaultVeeRules from 'vee-validate/dist/rules'
  import _ from 'lodash'
  import Vue from 'vue'
  
  function registerRules() {
    _.forEach(defaultVeeRules, (rule, name) => {
      extend(name, rule)
    })
  }
  
  function registerVeeValidation() {
    setInteractionMode('eager')
    registerRules()
  }
  
  Vue.component('ValidationObserver', ValidationObserver)
  Vue.component('ValidationProvider', ValidationProvider)
  
  export { registerVeeValidation }
  