<template>
  <div>

  <!--use animate.css and font-awesome -->
  <vue-star animate="animated rubberBand" color="#33FFFF">
   <i  style="font-size: 2em; " slot="icon" class="far fa-thumbs-up"></i>
  </vue-star>
<br>
<br>

 <vue-star animate="animated bounceIn" color="#33FFFF">
     <i style="font-size: 2em; " slot="icon"  class="fas fa-bookmark"></i>
  
  </vue-star>

  </div>
</template>

<script>
import VueStar from 'vue-star'
export default {
    components: {
         VueStar
  }
}
</script>

<style>

</style>