<template>

    <div class="signup-content">
        <div class="input-with-label">
            <b-container class="bv-example-row">
                <!-- 리뷰 제목 -->
                 <b-row>
            <b-col>
                <input v-model="reviewTitle" id="reviewTitle" placeholder="리뷰 제목을 작성해주세요." type="text" style="
            background-color: white;
            color: black;
            border: 2px solid #008Cy
            BA;" v-bind:class="{error : error.title,
            complete:!error.title&&reviewTitle.length!==0}" />
                <label for="reviewTitle">리뷰 제목</label>
                <div class="error-text" v-if="error.title">
                    {{error.title}}
                </div>
                <!-- 해시 태그 모달 -->
                <br>
                선택된 HashTag : {{area}} {{age}} {{gender}} {{atmosphere}} {{withWho}} <br>
                <b-button id="show-btn"
                    style="width: 50%;height: 15%;color: cornflowerblue;background-color: aliceblue; margin-bottom: 10px;"
                    @click="$bvModal.show('bv-modal-example')">HashTag</b-button>
                <b-modal style="text-align: center; margin-bottom: 10px;" id="bv-modal-example" hide-footer>
                    <template v-slot:modal-title>HashTag</template>
                    <div class="d-block text-center">
                        <h3>지역</h3>
                        <!-- <div v-for = "fa in this.fixArea" v-bind:key=fa>
                                    <input type="checkbox" id="fa" value="fa" v-model="area">
                                    <label for="fa">{{fa}}</label>
                            </div> -->
                        <input type="checkbox" id="gangnam" value="강남" v-model="area" v-on:click="check_area()">
                        <label for="gangnam">강남</label>
                        <input type="checkbox" id="jongro" value="종로" v-model="area" v-on:click="check_area()">
                        <label for="jongro">종로</label>
                        <input type="checkbox" id="jamsil" value="잠실" v-model="area" v-on:click="check_area()">
                        <label for="jamsil">잠실</label>
                        <br>
                        <span>체크 : {{ area}}</span>
                        <h3>연령</h3>
                        <input type="checkbox" id="ten" value="10대" v-model="age" v-on:click="check_age()">
                        <label for="ten">10대</label>
                        <input type="checkbox" id="twienty" value="20대" v-model="age" v-on:click="check_age()">
                        <label for="twienty">20대</label>
                        <input type="checkbox" id="thirty" value="30대" v-model="age" v-on:click="check_age()">
                        <label for="thirty">30대</label>
                        <br>
                        <span>체크 : {{age}}</span>
                        <h3>성별</h3>
                        <input type="checkbox" id="man" value="남" v-model="gender" v-on:click="check_gender()">
                        <label for="man">남</label>
                        <input type="checkbox" id="girl" value="여" v-model="gender" v-on:click="check_gender()">
                        <label for="girl">여</label>
                        <br>
                        <span>체크 : {{gender}}</span>
                        <h3>분위기</h3>
                        <input type="checkbox" id="calm" value="조용한" v-model="atmosphere"
                            v-on:click="check_atmosphere()">
                        <label for="calm">조용한</label>
                        <input type="checkbox" id="comfortable" value="아늑한" v-model="atmosphere"
                            v-on:click="check_atmosphere()">
                        <label for="comfortable">아늑한</label>
                        <input type="checkbox" id="warm" value="따뜻한" v-model="atmosphere"
                            v-on:click="check_atmosphere()">
                        <label for="warm">따뜻한</label>
                        <br>
                        <span>체크 : {{atmosphere}}</span>
                        <span></span>
                        <h3>누구랑 가면 좋을까요?</h3>
                        <input type="checkbox" id="friend" value="친구" v-model="withWho" v-on:click="check_with()">
                        <label for="friend">친구</label>
                        <input type="checkbox" id="family" value="가족" v-model="withWho" v-on:click="check_with()">
                        <label for="family">가족</label>
                        <input type="checkbox" id="coworker" value="동료" v-model="withWho" v-on:click="check_with()">
                        <label for="coworker">동료</label>
                        <br>
                        <span>체크 : {{withWho}}</span>
                    </div>
                    
                    <b-button
                        style="width: 100%;height: 20%; color: cornflowerblue;background-color: aliceblue; margin-bottom: 10px;"
                        class="mt-3" block @click="$bvModal.hide('bv-modal-example')">닫기</b-button>
                </b-modal> <br>

                <h3>음식 사진 업로드</h3>
                <!-- TODO : 이미지가 있는지 없는지 체크해서 보여주기 -->
                <template v-if="this.profile != ''">
                    <!-- {{this.profile}}  -->

                    <img :src="profile" style="max-width:30%">
                </template>
                <template v-else>
                    사진없어요
                </template>


                <h3>사진업로드 테스트</h3>
                <div class="uploadProfile">
                    <input type="file" name="fileToUpload" id="fileToUpload" accept=".gif, .jpg, .png"
                        @change="getProfileForm">
                    <!--change을 통해서 파일의 변화를 감지 -->
                    <button @click="upload">제출</button>
                </div> <br>
                <span contenteditable="true" placeholder="음식점 주소를 등록해주세요.">
                    음식점 주소 : {{address}} <br>
                    음식점 번호 : {{store_num}}
                </span><br>
                <!-- 음식점 사진 : <img :src="store_pic" style="max-width:30%" > -->
                <br>
                 <v-btn class="ma-2" id="show-btn" @click="$bvModal.show('bv-modal-example_adr')">음식점 주소 등록</v-btn>
               
                <b-modal style="text-align: center; margin-bottom: 10px;" id="bv-modal-example_adr" class="modalStore"
                    hide-footer>
                    <template v-slot:modal-title>음식점 주소 등록하기</template>
                    <div class="d-block text-center">
                        <!--Main Modal Page-->
                        <div v-if="eye">
                            검색 완료한 후 enter를 눌러주세요!
                            <b-form-input id="address_search" @keyup="fetchAdr" type="text" v-model="address">
                            </b-form-input>
                            <b-table striped hover :items="map" @row-clicked="clickEvent"></b-table>
                        </div>

                        <div v-else>
                            검색 완료한 후 enter를 눌러주세요!
                            <b-form-input id="address_search2" @keyup="fetchAdr" type="text" v-model="address">
                            </b-form-input>
                            <b-table striped hover :items="map" @row-clicked="clickEvent">

                            </b-table>
                        </div>
                    </div>

                    <b-button
                        style="width: 100%;height: 20%; color: cornflowerblue;background-color: aliceblue; margin-bottom: 10px;"
                        class="mt-3" block @click="$bvModal.hide('bv-modal-example_adr')">닫기</b-button>
                </b-modal>
                <br>
                </b-col>
                <!-- 음식점 주소 등록
            <b-form-input id="address_search" @keyup ="fetchAdr" class="in" type="text"  v-model="address" list="maps"></b-form-input>
                <datalist  id="maps"> 
            <option v-for="map in maps" v-bind:key="map">{{map}}</option>
            </datalist><br>
            <label for="address_search"></label>  -->
<b-col>

               
                 맛 : <star-rating v-model="flavor"  :border-width="4" border-color="#d8d8d8" :rounded-corners="true" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></star-rating> <br>
             가격 : <star-rating v-model="price"  :border-width="4" border-color="#d8d8d8" :rounded-corners="true" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></star-rating> <br>
             친절함 : <star-rating v-model="kindness"  :border-width="4" border-color="#d8d8d8" :rounded-corners="true" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></star-rating> <br>
           
                장점 : <textarea v-model="props" class="props_textarea" placeholder="장점을 작성해주세요~"
                    v-bind:class="{error :error.props, complete :!error.props && props.length!==0}" /> <br>
            <div class="error-text" v-if="error.props">
                        {{error.props}}
            </div>

            단점 : <textarea v-model="cons" class="cons_textarea" placeholder="단점을 작성해주세요~"
                v-bind:class="{error :error.cons, complete :!error.cons && cons.length!==0}"
             /><br>
            <div class="error-text" v-if="error.cons">
                        {{error.cons}}
            </div>
             평점 : <star-rating v-model="rating" :border-width="4" border-color="#d8d8d8" :rounded-corners="true"
                    :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></star-rating>
              <br>
              <v-btn depressed large name="submit" id="submit" @click="reviewConfirm" 
              value="review 등록" >리뷰 등록</v-btn>
              </b-col>
               </b-row>
            </b-container>
      </div>
  </div>
</template>

<script>

import '../../assets/css/style.scss'
import '../../assets/css/user.scss'
import UserApi from '../../apis/UserApi'
import PV from 'password-validator'
import ImgurApi from '../../apis/ImgurApi'
import StarRating from 'vue-star-rating'
import Vue from 'vue'
export default {
    created() {

      this.titleSchema
         .is().min(10)
         .is().max(100)
      
      this.propsSchema
         .is().min(10)
         .is().max(20)
      this.consSchema
         .is().min(10)
         .is().max(20)
      this.getProfile();
      
    },
    watch : {
        reviewTitle : function(v){
            this.checkForm();
        },
        props : function(v){
            this.checkForm_props();
        },
        cons : function(v){
            this.checkForm_cons();
        }
    },
    computed() {
     
    },

    components: {
        StarRating
    },
    methods : {
        check_area(){
            this.area = [];
        },
        check_age(){
            this.age = [];
            
        },
        check_gender(){
            this.gender = [];
        },
        check_atmosphere(){
            this.atmosphere = [];
        },
        check_with(){
            this.withWho = [];
        },
        getProfile(){
                // Axios로 사진 가져오기
                // console.log('프로필 가져오기!!!')

                let email = "ssafy@naver.com";
                let data = {
                        email
                    }
                UserApi.requestProfile( email,res=>{
                    // console.log(res)
                    this.profile = res.object.profile
                    // console.log(this.profile);
                    console.log('프로필 가져오기 성공')
                        
                    },error=>{  
                        // console.log('프로필 가져오기 실패')
                    })
            },
            upload(){
                console.log('이미지 업로드 @@')
                // console.log(this.selectedImage)


                ImgurApi.uploadProfile(this.selectedImage, res =>{
                    // img url - res.link에 저장
                     // 2) Imgur에 저장된 사진 링크를 가져오기
                    
                    this.imageUrl = res.data.link
                    // this.imageUrl = "https://i.imgur.com/91WnlBF.png" // ######TEST 용
                    // console.log(this.imageUrl)
                    this.email = "ssafy@naver.com" // ######TEST 용
                    
                    
                    // 3) 사진링크를 User의 profile 링크로 수정하기
                    UserApi.requestUpload(this.email, this.imageUrl, res =>{
                        // status로 판단
                        console.log(res)
                        if(res.status == true)
                            console.log('프로필 업로드 성공!')
                        this.getProfile();
                    }, error =>{
                        alert('프로필 업로드 실패')
                    })

                }, error =>{
                    alert('Imgur 업로드 실패!')
                })
                
            },
            getProfileForm(event){
                this.selectedImage = event.target.files[0];
            },
            setRating(rating) {
                this.rating = "You have Selected: " + rating + " stars";
                console.log(this.rating);
             },
             fetchAdr(){
               
               //검색시
                this
                .$store
                .dispatch('FETCH_ADR', this.address)
                
               
               
                         if(this.eye == false) this.eye = true;
                else this.eye = false

                //값 init
                
                this.map = this.$store.state.googleStorePlaceView
                console.log(this.$store.state.googleStorePlace);
               
               
             },
            reviewConfirm (){
                let hashtag = this.area + this.age + this.age + this.atmosphere + this.withWho; 
                console.log(hashtag.length);
                console.log(hashtag + " " + this.profile + " " + this.rating + " " + this.cons +
                this.props + " "+ this.flavor + " " + this.price + " " + this.kindness +
                this.reviewTitle + " " + this.store_num)
                
                if(hashtag.length <0 || this.rating <0 || this.flavor <0 || this.price <0 || this.kindness <0
                   || this.store_num<0 
                ) 
                    this.isSubmit = true //validation 나중ㅇ
                //this.isSubmit = true
                if(this.isSubmit)
                {
                       var data = { 
                        'hashtag' : hashtag,
                        'picture' : this.store_pic,
                        'score_kindness' : this.kindness,
                        'score_price' : this.price,
                        'score_taste' : this.flavor,
                        'score_total' : this.rating,
                        'store_num' : 1,//this.store_num,
                        'str' : this.props,
                        'title' : this.reviewTitle,
                        'user_email' : this.user_email,
                        'weak' : this.cons,
                        }
                        console.log(data);
                    UserApi.requestAddReview(data, res=>{
                        console.log("reviewPage 등록 성공");
                    })
                    console.log('all complete');

                }else{
                    alert('리뷰를 작성해주세요~~')
                }
            },
            clickEvent(record, index) {
            // 'record' will be the row data from items
            // `index` will be the visible row number (available in the v-model 'shownItems')
            
            console.log(this.$store.state.googleStorePlace[index]);
             //Q : 리뷰 항목을 다 건네줘야 하는건지?
                 UserApi.requestAddPlace(this.$store.state.googleStorePlace[index],res=>{
                 this.store_num = res.data.object.num;
                 this.store_pic = res.data.object.img;
                 })
            },
            checkForm(){
                //리뷰 제목(0자이상 10자 이하)
                //장점, 단점 (0자 이상 20자 이하)
                if(this.reviewTitle.length>=0 && !this.titleSchema.validate(this.reviewTitle))
                    this.error.title = '리류제목은 0 글자 이상 10글자 이하 이어야 합니다.'
                else 
                    this.error.title = false;
            },
            checkForm_props(){ 
                if(this.props.length>=0 && !this.propsSchema.validate(this.props))
                    this.error.props = '장점은 0글자 이상 20글자 이하 이어야 합니다.'
                else
                     this.error.props = false; 
            },
            checkForm_cons(){
                if(this.cons.length>=0 && !this.consSchema.validate(this.cons))
                    this.error.cons = '단점은 0글자 이상 20글자 이하 이어야 합니다.'
                else
                     this.error.cons = false;


                let isSubmit = true;
                Object.values(this.error).map(v => {
                    if (v) isSubmit = false;
                })
                this.isSubmit = isSubmit;
            },
    },
    data : () =>({
         
         area : [],
         age : [],
         gender : [],
         atmosphere : [],
         withWho : [],
         profile: '',
         mageUrl: '',
         selectedImage: '',
         rating : "",
         cons : '',
         props : '',
         flavor: '',
         price : '',
         kindness : '',
         maps :[
         ],
         address : '',
         eye : false,
         isSubmit : '', //form 완료시 toggle
         reviewTitle : '',
         store_num : '',
         user_email : 'ssafy@ssafy.com', //temp
         titleSchema : new PV(),
         consSchema : new PV(),
         propsSchema : new PV(),
         error : {
             title : false,
             props : false,
             cons : false,
         },
         store_pic : '',
    })
}
</script>

<style scoped>

</style>