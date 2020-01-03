<template>
<v-container>
    profile
    <v-layout>
        <v-flex :xs6="!$vuetify.breakpoint.xsOnly">

            <v-flex>{{profile.name}}</v-flex>
        </v-flex>
    </v-layout>
</v-container>
</template>

<script>
    import {mapState} from 'vuex'
    export default {
        name: "Profile",
        computed: mapState(["profile"])
    }
</script>

<style scoped>

</style>
