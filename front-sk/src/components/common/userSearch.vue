<template>
    
<div>
<!--User가 최근 검색한 history를 보여줌 --> 
<div v-if="inputStatus">
<div class="input-container">
<i class="fas fa-search"></i>
<b-form-input  class="in" type="text" v-on:keyup="changeInput" v-model="search" list="this.$store.state.recentUser"></b-form-input>
  <datalist  id="this.$store.state.recentUser"> 
        <option v-for="user in this.$store.state.recentUser" v-bind:key="user">{{user}}</option>
  </datalist>
  </div>
 </div>
 <!--User 정보 전체를 보여줌 -->
<div v-else>
    <div class="input-container">
    <i class="fas fa-search"></i>
<b-form-input class="in"  type="text" v-on:keyup.enter="changeInput2" v-model="search" list="this.$store.state.searchUser.nickname"/>
  <datalist id="this.$store.state.searchUser.nickname">
        <option v-for="user in this.$store.state.searchUser.nickname"  v-bind:key="user">{{user}}</option>
       
  </datalist>
  </div>

  <!-- <div v-for="user in filteredList" v-bind:key="user">
      {{user}}
  </div> -->
</div>
</div>
</template>

<script>
import UserApi from '../../apis/UserApi';
export default {
    created() {
        this
            .$store
            .dispatch('LOADING_USERDATA');

        // this
        //     .$store
        //     .dispatch('LOADING_RECENTUSERDATA');
        //유저 email을 넘겨줘야함

    },
    data: () => ({
        search: '',
        films: [
            "A Throne Too Far", "The Cat Wasn't Invited", "You Only Meow Once", "Catless in Seattle"
        ],
        inputStatus: true,
        history : {
            email : '',
            nickname : '',
            moveUser : '',
        },
    }),
    computed: {

        filteredList() {
            return this
                .$store
                .state
                .searchUser
                .filter(post => {
                    return post
                        .toLowerCase()
                        .includes(this.search.toLowerCase())
                })
        }
    },
    methods: {
        changeInput(){ //if-else 
            if(this.inputStatus == true) this.inputStatus = false;
            else
                this.inputStatus = true;
        },
        changeInput2(){ //enter 누를 때, user 정보가 db에 저장된다.
            let find = false;
            
            for(var j =0; j<this.$store.state.searchUser.nickname.length; j++){
                if(this.search == this.$store.state.searchUser.nickname[j]) {
                    this.moveUser = this.$store.state.searchUser.email[j];
                    find = true;
                    break;
                }
               
            }
            if(find == false) this.moveUser = 'notEmail'
            //this.history.email = sessionStorage.getItem("userEmail");s
            this.history.email ="tqbs1024@gmail.com"
            this.history.nickname = this.search;

           
            UserApi.searchUserHistory(this.history, res=>{
              
             if(this.inputStatus == true) this.inputStatus = false;
             else
                this.inputStatus = true;
                let temp = this.history.email;
                this.search="";

                
                UserApi.requestFetchUserData(temp, res=>{
                    console.log(res);
                    console.log(this.$store.state.recentUser);
                 });
                
            },error=>{  
                console.log("userSearch.vue, server 와 통신 실패");
            })
            //DB에 저장하고 email로 유저페이지 이동한다. 
            //this.$router.push({ name: 'userPage', params: { id: this.moveUser }})
             
             
             //다시 inpustState를 변화시키므로써, User가 최근 검색 history를 보여준다. 
             
        }
    }
}
</script>

<style scoped>

.in{
    float:left;
    border: 2px solid #f4d9bc;
    border-radius: 4px;
    width : 30%;    
    
}
.in#btn{
    float:right;
   
}
#btn{
    margin-top : 9px;
}
svg:not(:root).svg-inline--fa {
    overflow: visible;
    font-size: 1.5rem;
    margin-left: 9px;
}
.input-container {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  width: 100%;
  margin-bottom: 15px;
}




</style>
