import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const mLogin = {
    // namespaced: true,
    state: {
      loginState: -1,
    },
    getters: {
      checkLogin(state) {
        return state.loginState;
      },
    },
    mutations: {
      LOGIN(state, payload) {
        state.loginState = payload;
      },
      LOGOUT(state) {
        state.loginState = -1;
      },
    },
    actions: {
      login(context, payload) {
        context.commit('LOGIN', payload);
      },
      logout(context) {
        context.commit('LOGOUT');
      },
    },
  },
  mCheck = {
    state: {
      checkedId: new Set(),
    },
    getters: {},
    mutations: {
      ADD_CHECKED(state, payload) {
        state.checkedId.add(payload);
      },
      DELETE_CHECKED(state, payload) {
        state.checkedId.delete(payload);
      },
      CLEAR_CHECKEDS(state) {
        state.checkedId.clear();
      },
    },
    actions: {
      addChecked(context, payload) {
        context.commit('ADD_CHECKED', payload);
      },
      deleteChecked(context, payload) {
        context.commit('DELETE_CHECKED', payload);
      },
      clearCheckeds(context) {
        context.commit('CLEAR_CHECKEDS');
      },
    },
  },
  mURL = {
    state: {
      identity: ['/member', '/club', '/sau'],
      orgs: ['/org', '/other', '/club'],
    },
  };

export default new Vuex.Store({
  modules: {
    mLogin,
    mCheck,
    mURL,
  },
});
