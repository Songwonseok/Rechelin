<template>
  <v-app id="inspire_adminUserList">
    <v-card
      color="white lighten-2"
      flat
      height=" 700px"
      tile
    >
    <div class="text-center">
      <v-pagination
        v-model="page"
        :length=this.len
        @input = "paginationBtn"
        circle
      ></v-pagination>
      </div> <br>
      <v-toolbar height = 55oxdense>
        <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->
  
        <v-toolbar-title style="color : orange">
            
            
        </v-toolbar-title>
        
        <div>
      <v-text-field style="padding : 45px margin-bottom : 10px" >  
          
      </v-text-field>
    </div>
    <v-btn icon>
                <v-icon style="color : black">mdi-magnify</v-icon>
            </v-btn>
  
        <v-spacer></v-spacer>
      
        <!-- <v-btn icon>
          <v-icon>mdi-dots-vertical</v-icon>
        </v-btn> -->
      </v-toolbar>
      <b-table style="color : black" striped hover :items="userList_paging"  
       @row-clicked="expandAdditionalInfo" 
       flex 
       >
       <template slot="row-details" >
    <b-card>
      <!-- click 했을 때 child 로 나오는 것임 -->
      <v-text-field label ="비밀번호 변경" style="width : 400px" :rules="rules"></v-text-field>
      
      
      <v-btn  :value="activeBtn"
      color="orange lighten-1">
        <span>delete</span>
        <v-icon>mdi-delete</v-icon>
      </v-btn>
    </b-card>
  </template>
       </b-table>
        
    </v-card>
    
  </v-app>
</template>

<script>


export default {
    created(){
      this.lenCalc_page();
      console.log('len')
      console.log(this.page);
      this.page = this.len;
    },
    data(){
        return {
        maps: [
          { nickname: 40, email: 'Dickerson', id: 'Macdonald', _showDetails: false },
          { nickname: 21, email: 'Larsen', id: 'Shaw' , _showDetails: false},
          { nickname: 89, email: 'Geneva', id: 'Wilson' , _showDetails: false},
          { nickname: 38, email: 'Jami', id: 'Carney' , _showDetails: false},
          { nickname: 40, email: 'Dickerson', id: 'Macdonald' , _showDetails: false},
          { nickname: 21, email: 'Larsen', id: 'Shaw' , _showDetails: false},
          { nickname: 89, email: 'Geneva', id: 'Wilson' , _showDetails: false},
          { nickname: 38, email: 'Jami', id: 'Carney', _showDetails: false }
        ],
        page : 1,
        rules: [
         value => !!value || '빈칸으로 둘 수 없습니다.',
         value => (value || '').length <= 20 || '최대 20글자 입니다.',
      
         ],
        userList_paging : [],
        len : 0,
        }
    },
    methods : {
      expandAdditionalInfo(row) {
        row._showDetails = !row._showDetails;
      },
      paginationBtn(){
        console.log('pagination check');
        this.userList_paging = [];
        for(let i=(this.page*3)-3; i<this.page*3; i++){
                    this.userList_paging.push(this.maps[i]);
          } 
          this.$store.state.userPage = this.page;
          console.log(this.userList_paging);
      },
      lenCalc_page(){
        console.log('lenCalc');
                    if(this.maps.length%3!=0)
                        this.len = Math.ceil(this.maps.length/3);
                    else
                        this.len = Math.ceil(this.maps.length/3);
                
            }
    }
}
</script>

<style>
.inspire_adminUserList{

}
.v-input input text{
    max-height: 32px;
    margin-bottom: 20px;
    color: orange;
}
</style>