<template>
    <v-container>
        <v-layout align-space-around column>
            <message-form :messageAttr="message"/>
            <message-row v-for="message in sortedMessages"
                         :key="message.id"
                         :message="message"
                         :editMessage="editMessage"/>
        </v-layout>
    </v-container>
</template>

<script>
    import MessageRow from './MessageRow.vue'
    import MessageForm from './MessageForm.vue'
    import {mapGetters} from 'vuex'

    export default {
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        computed: mapGetters([
            'sortedMessages'
        ]),
        methods: {
            editMessage(message) {
                this.message = message
            }
        }
    }
</script>

<style>

</style>
