<template>
    <div>
        <v-card class="mx-auto" max-width="344">
            <v-card-text>
                <v-form ref="form" lazy-validation>
                    <b-form-textarea id="textarea-small" size="sm" v-model="newComment" @keyup.enter="submitComment(newComment)"
                        placeholder="comment"></b-form-textarea>
                    <div class="my-2">
                        <v-btn class="ma-2" text outlined small color="primary" @click="submitComment(newComment)">
                            댓글등록
                        </v-btn>
                    </div>

                </v-form>

                <div class="text--primary" v-if="comments">
                    <b-list-group v-for="(c, index) in comments" :key="index">
                        <b-list-group-item>{{c.content}}
                            <div v-if="canDelete(c.user.email)">
                                <v-btn text icon @click="commentDelete(c.num)">
                                <v-icon>{{icons.contentDelete}}</v-icon>
                            </v-btn>
                            </div>
                            
                        </b-list-group-item>
                    </b-list-group>
                </div>
            </v-card-text>

        </v-card>

    </div>
</template>

<script>
    import {
        mdiDelete
    } from '@mdi/js';
    import Axios from "axios"
    
    export default {
        data() {
            return {
                newComment: '',
                reviewNum: null,
                icons: {
                    contentDelete: mdiDelete
                },
                comments : this.$route.params.comments,
            }
        },
        
        created() {
           this.init()
        },
        mounted() {
            
        }
        ,
        methods: {
            submitComment(n) {
                var payload = {
                    userEmail: this.$store.state.userEmail,
                    reviewID: this.reviewNum,
                    newContent: this.newComment
                }
                this.$store.dispatch('createComment', payload)
            },
            init() {
                
                // let list = [...this.comments];
                // list = this.$store.state.commentsOfreview
                var tempNum = this.$route.params.reviewID
                this.reviewNum = tempNum
            return new Promise(function(resolve, reject) {
                    resolve('성공')
                    
                })
            },
            canDelete(user) {
                if (this.$store.state.userEmail===user) {
                    return true
                } else {
                    return false
                }
            },
            commentDelete(num) {
                this.$$store.dispatch('commentDelete', num)
            }
                
        },
 



    }
</script>