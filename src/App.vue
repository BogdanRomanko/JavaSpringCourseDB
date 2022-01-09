<template>

<div>
  <my-menu @onUpdateTable="updateTable"/>
  <b-container class="main-container">
  <my-table 
    :fields="fields" 
    :items="items" 
    :form_component="form_component"
    :edit_form_component="edit_form_component"
    :called_func="called_func"
    v-if="this.fields.length != 0 && !this.is_additional"
    @onDeleteItem="deleteItem"
  />

  <additional-table
    :fields="fields"
    :items="items"
    v-if="this.is_additional"
  />
  </b-container>
</div>

</template>

<script>
import MyTable from './components/MyTable.vue'
import MyMenu from "./components/MyMenu.vue"
import axios from "axios"
import AdditionalTable from './components/AdditionalTable.vue'

export default {
  components: { MyMenu, MyTable, AdditionalTable },
  data() {
    return {
      fields: [],
      items: [],
      form_component: null,
      edit_form_component: null,
      is_additional: false,
      called_func: null
    }
  },
  methods: {
    async updateTable(result) {
      this.fields = result.fields
      this.items = result.items
      this.form_component = result.form_component
      this.edit_form_component = result.edit_form_component
      this.is_additional = result.is_additional
      this.called_func = result.called_func
    },
    async deleteItem(result) {
      const res = await axios.delete(`https://spring-db-course.herokuapp.com/${result.table}/delete?id=${result.id}`)
      const reload = await this.called_func()
    }
  }
}
</script>


<style>
*{
  padding: 0px;
  margin: 0px;
  border: 0px;
}

body{
  background-color: #d9d9d9 !important;
  height: 100px;
}

.main-container{
  padding-bottom: 15px;
}

</style>
