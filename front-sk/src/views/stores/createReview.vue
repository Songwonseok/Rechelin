<template>
    <div>
        <v-card class="mx-auto" max-width="344">
            <v-card-text>
                <v-form ref="form" v-model="valid" lazy-validation>
                    <b-form-textarea id="textarea-small" size="sm" v-model="newComment" @keyup.enter="submitComment" placeholder="comment"></b-form-textarea>
                    <div class="my-2">
                        <v-btn class="ma-2" outlined small fab color="indigo" @click="submitComment">
                            <v-icon>{{icons.checkOutline}}</v-icon>
                        </v-btn>
                    </div>
                </v-form>

                <div class="text--primary">
                    <b-list-group>
                        <b-list-group-item>Cras justo odio</b-list-group-item>
                        <b-list-group-item>Dapibus ac facilisis in</b-list-group-item>
                        <b-list-group-item>Morbi leo risus</b-list-group-item>
                        <b-list-group-item>Porta ac consectetur ac</b-list-group-item>
                        <b-list-group-item>Vestibulum at eros</b-list-group-item>
                    </b-list-group>
                </div>
            </v-card-text>

        </v-card>
    </div>
</template>

<script>

    import {
mdiCheckOutline
} from '@mdi/js'

    export default {
        data() {
            return {
                icons: {
                    checkOutline: mdiCheckOutline
                },
                newComment: '',
            }
        },
        created() {

        },
        methods: {
            submitComment() {
                var payload = {
                    id: 'review.id',
                    contents: this.newComment
                }
                this.$store.dispatch('createComment', payload)
            }
        }

    }
</script>