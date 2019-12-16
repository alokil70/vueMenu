import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        messages: frontendData.messages,
        profile: frontendData.profile
    },
    getters: {
        sortedMessages(state) {
            return state.messages.sort((a, b) => -(a.id - b.id))
        }
    },
    mutations: {
        addMessageMutation(state, message) {
            state.messages = [...state.messages, message]
        },
        updateMessageMutation(state, message) {

        },
        removeMessageMutation(state, message) {

        }
    },
    actions: {
        addMessageAction({commit}, message) {

        },
        updateMessageAction({commit}, message) {

        },
        removeMessageAction({commit}, message) {

        }
    },
    modules: {}
})
