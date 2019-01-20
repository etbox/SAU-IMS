<template>
  <!-- banner begin -->
  <div id="swiper">
    <swiper :options="swiperOption">
      <swiper-slide>
        <img src="@/images/slide1.png" alt="First slide">
      </swiper-slide>
      <swiper-slide>
        <img src="@/images/slide2.png" alt="Second slide">
      </swiper-slide>
      <swiper-slide>
        <img src="@/images/slide3.png" alt="Third slide">
      </swiper-slide>
      <div class="swiper-pagination" slot="pagination"></div>
      <div class="swiper-button-prev" slot="button-prev"></div>
      <div class="swiper-button-next" slot="button-next"></div>
    </swiper>
  </div>
  <!-- banner end -->
</template>

<script>
import "swiper/dist/css/swiper.css";

import { swiper, swiperSlide } from "vue-awesome-swiper";

export default {
  data() {
    return {
      swiperOption: {
        autoplay: true,
        slidesPerView: 1,
        spaceBetween: 30,
        loop: true,
        pagination: {
          el: ".swiper-pagination",
          clickable: true
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev"
        }
      }
    };
  },
  components: {
    swiper,
    swiperSlide
  }
};
</script>

<style lang="scss" scoped>
#swiper {
  padding: 30px;
}
</style>

