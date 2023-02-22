<template>
    <div class="output" v-html="output"></div>
</template>

<script>
import { marked } from 'marked'

export default {
  data: () => ({
    input: '# hello,\n- 你好'
  }),
  props:{
    article: String
  },
  computed: {
    output() {
      return marked(this.article);
    }
  },
  methods: {
  }
}
</script>



<style>
body {
  margin: 0;
}

.output {
  overflow: auto;
  border-bottom: .1px #dedede solid;
  white-space: pre-wrap;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  padding: 0 20px;
  word-wrap:break-word
}
code {
  color: #f66;
}
</style>
