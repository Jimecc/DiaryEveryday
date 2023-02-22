<template>
<!--  <article class="markdown-body" style="text-align:left" v-html="compiledMarkdown"></article>-->
<!--  <article class="markdown-body" style="text-align:left" v-html="compiledMarkdown"></article>-->
  <div v-html="compiledMarkdown"></div>
</template>


<script>
import {marked} from 'marked';
export default {
  name: "DiaryArticle",
  props:{
    article:''
  },
  data(){
    return{
      input: '# hello'
    }
  },
  computed: {
    compiledMarkdown: function() {
      return marked(this.input, { sanitize: true });
    }
  },
  methods: {
    update: _.debounce(function(e) {
      this.input = e.target.value;
    }, 300)
  }
}
</script>


<style scoped>
.markdown-body {
  box-sizing: border-box;
  min-width: 200px;
  /* max-width: 980px; */
  /* padding: 45px; */
  max-width: 98%;
  margin: 0 auto;
  box-shadow: 2px 4px 6px gray;
  padding-left: 20px;
  padding-right: 15px;
  padding-top: 40px;
  padding-bottom: 45px;
  margin-bottom: 100px
}

.markdown-body {
  box-sizing: border-box;
  min-width: 200px;
  max-width: 980px;
  margin: 0 auto;
  padding: 45px;
}

@media (max-width: 767px) {
  .markdown-body {
    padding: 15px;
  }
}
</style>
