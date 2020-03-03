<template>
    

<!--User가 최근 검색한 history를 보여줌 --> 
<!-- <div class="input-container">
<i class="fas fa-search"></i>
<b-form-input  class="searchTerm" type="text" v-on:keyup.enter="changeInput" v-model="search" list="this.$store.state.recentUser.nickname"></b-form-input>
  <datalist  id="this.$store.state.recentUser.nickname"> 
        <option v-for="user in this.$store.state.recentUser.nickname" v-bind:key="user">{{user}}</option>
  </datalist>
  </div>
 </div> -->
 <!--User 정보 전체를 보여줌 -->
<div >
<v-app id="inspire" >
         
          <!-- <v-select
            :items="this.$store.state.searchUser.nickname"
            :filter="customFilter"
            v-model="a1"
            item-text="name"
            label="Select"
            autocomplete
          ></v-select> -->

     
        <v-container>
          <v-row >
           
              <v-autocomplete
                v-model="friends"
               
                width="350"
                :items="this.$store.state.searchUser"
                filled
                chips
                color="blue-grey lighten-2"
                label="Select"
                @keyup.enter = "feedUserSearch"
                item-text="name"
                item-value="name"
                no-data-text="팔로잉해주세요, 팔로잉 결과가 없습니다."
               
              >
                <template  v-slot:selection="data">
                  <v-chip
                   
                    v-bind="data.attrs"
                    :input-value="data.selected"
                    close
                    @keyup.enter="dataSelect()"
                    @click:close="remove(data.item)"
                  >
                    <v-avatar  left>
                      <v-img :src="data.item.avatar"></v-img>
                    </v-avatar>
                    {{ data.item.name }}
                  </v-chip>
                </template>
                <template  v-slot:item="data">
                  
                    <v-list-item-avatar>
                      <img :src="data.item.avatar">
                    </v-list-item-avatar>
                    <v-list-item-content>
                      <v-list-item-title v-html="data.item.name"></v-list-item-title>
                     
                    </v-list-item-content>
                </template>
              </v-autocomplete>
           
            </v-row>
        </v-container>
     
      
</v-app>
</div>
</template>

<script>
import SearchApi from '../../apis/SearchApi';
import ReviewApi from '../../apis/ReviewApi';
import FollowApi from '../../apis/FollowApi';
export default {
    created() {
       
  
        FollowApi.requestStarList(this.$store.state.userid, res=>{
            
            let jbAry = [];
            
            for(let i =0; i<res.length; i++){
            let item = {};
            item['name'] = res[i].nickname;
            item['email'] = res[i].email;
              if(res[i].profile == null){
                item['avatar'] = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png";
              }
              else{
                  item['avatar'] = res[i].profile;
              }
              JSON.stringify(item);
              jbAry.push(item);
            }
            this.$store.state.searchUser = jbAry;
        }, error=>{
          
        });
    },
 
    data(){
       
         return{
           searchUserid : 0,
           autoUpdate: true,
      friends: [],
      isUpdating: false,
      
     
      title: 'The summer breeze',
       
        search: '',
        history : {
            id : '',
            nickname : '',
            moveUser : '',
        },
        dataFlag : false, 
        }
    },
    methods: {
        remove (item) {
            const index = this.friends.indexOf(item.name)
            if (index >= 0) this.friends.splice(index, 1)
        },
      
        customFilter (item, queryText, itemText) {
        const hasValue = val => val != null ? val : ''
        const text = hasValue(item.name)
        const query = hasValue(queryText)
        return text.toString()
          .toLowerCase()
          .indexOf(query.toString().toLowerCase()) > -1
      },
      dataSelect(){

      },
      feedUserSearch(){
        
         this.$store.state.feedUserList = this.friends;
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
  #inspire {
    
    width: 100%;
    right: 0;
    left : 100;
    margin-bottom: 100px;
   
    bottom: 100px;

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


.v-menu__content theme--light menuable__content__active v-autocomplete__content{
  background-color:#f4d9bc;
  z-index:1;
}

</style>