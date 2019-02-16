import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loginState: -1
  },
  getters: {
    checkLogin(state) {
      return state.loginState;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      state.loginState = payload;
    },
    LOGOUT(state) {
      state.loginState = -1;
    }
  },
  actions: {
    login(context, payload) {
      context.commit('LOGIN', payload);
    },
    logout(context) {
      context.commit('LOGOUT');
    }
  }
});