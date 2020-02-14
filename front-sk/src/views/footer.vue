<template>
  <div>
      
  <v-app id="footer">
      <v-card
      color="white lighten-4"
      flat
      height="200px"
      tile
    >
  
      </v-card>
    <v-footer
      dark
      padless
      fluid fill-height
    >
      <v-card
        flat
        tile
        height = "600px"
        class="orange lighten-1 white--text text-center"
      >
        <v-card-text>
          <v-btn
            v-for="icon in icons"
            :key="icon"
            class="mx-4 white--text btnColor"
            icon
          >
            <v-icon size="24px">{{ icon }}</v-icon>
          </v-btn>
        </v-card-text>
  
        <v-card-text class="white--text pt-0">
          만든사람:송원석,김현지,하다연,김주연,황인규도움을준사람:신광식,김민호,이인경,조경은,김시효,박민식,이수영
Created by: Song Won Suk, Kim Hyun Ji, Hada Yeon, Kim Joo Yeon, Hwang In Kyu
創建人：宋元錫，金賢姬，哈達妍，金柱妍，黃仁圭作成者：ソング宝石用原石、ギムヒョンジ、ある年、キム・ジュヨン、ファンインギュ助けを与えた人：シングァンシク、ギムミンホ、異人頃、造園は、ギムシヒョ、バクミンシク、イ・スヨンCreado por: Song Won Suk, Kim Hyun Ji, Hada Yeon, Kim Ju Yeon, Hwang In Kyu que ayudó: Shin Kwang Sik, Kim Min Ho, Lee In Kyung, Landscape Eun, Kim Si Hyo, Park Min Sik, Lee Soo Young
        </v-card-text>
  
        <v-divider></v-divider>
  
        <v-card-text class="white--text">
          {{ new Date().getFullYear() }} — <strong>Vuetify \ThankYou\🔥</strong>
        </v-card-text>
      </v-card>

       
    </v-footer>
  </v-app>
</div>
</template>

<script>
export default {
  data: () => ({
    icons: [
      'fab fa-facebook',
      'fab fa-twitter',
      'fab fa-google-plus',
      'fab fa-linkedin',
      'fab fa-instagram',
    ],
  }),
}
</script>

<style scoped>
.v-application--wrap{
  z-index:-1 !important;
  position: relative !important;
}

</style>