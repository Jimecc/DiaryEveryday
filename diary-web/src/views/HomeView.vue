<template>
  <div class="home">
    <el-input v-model="input" placeholder="请输入内容"></el-input>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  data() {
    return {
      input: ''
    }
  },
  components: {
    HelloWorld
  }
}
</script>
