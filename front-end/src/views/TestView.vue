<template>
  <div>
    <!-- Masthead-->
    <HeaderCom></HeaderCom>
    <!-- About-->
    <SectionCom></SectionCom>
    <!-- Footer-->
    <FooterCom></FooterCom>
  </div>
</template>

<script>
import HeaderCom from "@/components/test/HeaderCom.vue";
import SectionCom from "@/components/test/SectionCom.vue";
import FooterCom from "@/components/test/FooterCom.vue";
import loadWin from "@/assets/js/test/scripts";

export default {
  name: "TestView",
  components: { FooterCom, HeaderCom, SectionCom },
  // 화면이 뜨자마자 실행되는 이벤트
  mounted() {
    loadWin();
  },
};
</script>

<style>
@import "@/assets/css/test/styles.css";
</style>
