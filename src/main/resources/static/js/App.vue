<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <router-link to="/login">Login</router-link>
            <v-container v-if="!profile">
                Необходимо авторизоваться через
                <a href="/login">Google</a>
            </v-container>
            <v-container v-if="profile">
                <messages-list/>
            </v-container>
            <router-view></router-view>
        </v-content>
    </v-app>





</template>

<script>
    import MessagesList from './pages/MessageList.vue'
    import {mapState} from 'vuex'

    export default {
        components: {
            MessagesList
        },
        computed: mapState(['profile']),
    }
</script>

<style>

</style>
