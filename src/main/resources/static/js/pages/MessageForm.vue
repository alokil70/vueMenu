<template>
    <v-layout row>
        <v-text-field
                label="New message"
                placeholder="Write something"
                v-model="text"
        />
        <v-text-field
                label="New message1"
                placeholder="Write something1"
                v-model="text1"
                @keyup.enter="save"
        />
        <v-btn @click="save">
            Save
        </v-btn>
    </v-layout>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        props: ['messageAttr'],
        data() {
            return {
                text: '',
                text1: '',
                id: ''
            }
        },
        watch: {
            messageAttr(newVal, oldVal) {
                this.text = newVal.text;
                this.id = newVal.id
            }
        },
        methods: {
            ...mapActions(['addMessageAction', 'updateMessageAction']),
            save() {
                const message = {
                    id: this.id,
                    text: this.text,
                    text1: this.text1
                };

                if (this.id) {
                    this.updateMessageAction(message)
                } else {
                    this.addMessageAction(message)
                }

                this.text = '';
                this.text1 = '';
                this.id = ''
            }
        }
    }
</script>

<style>

</style>
