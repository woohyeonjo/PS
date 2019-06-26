<template>
    <div>
      <input type="text" v-model="newTodoItem">
    </div>
</template>

<script>
    export default {
        name: "TodoInput",
        data: function(){
          return {
            "newTodoItem" : ''
          }
        }
    }
</script>

<style scoped>

</style>
