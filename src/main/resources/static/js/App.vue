<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Marafon</v-toolbar-title>
            <v-btn text
                   v-if="profile"
                   :disabled="$route.path === '/'"
                   @click="showMessages">
                Messages
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn text
                   v-if="profile"
                   :disabled="$route.path === '/profile'"
                   @click="showProfile">
                Profile
            </v-btn>
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



            <router-view></router-view>

        </v-content>
    </v-app>


</template>

<script>
    import {mapState} from 'vuex'

    export default {
        /*        components: {
                    MessagesList
                },*/
        computed: mapState(['profile']),
        methods: {
            showMessages() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('profile')
            }
        }
    }
</script>

<style>

</style>
