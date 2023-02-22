<template>
<div style="text-align: center">
  <h1>{{diary_id}}</h1>
</div>
</template>

<script>
export default {
  name: "TestProps",
  props:{
    diary_id:String
  },
  mounted() {
    this.test();
  },
  methods:{
    test(){
      alert(this.diary_id);
    }
  }

}
</script>

<style scoped>

</style>
