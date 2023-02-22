import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    editor_diary_page: false
  },
  getters: {
  },
  mutations: {
    show_editor_page_true(state){
      state.editor_diary_page = true
    },
    show_editor_page_false(state){
      state.editor_diary_page = false
    }
  },
  actions: {
  },
  modules: {
  }
})
