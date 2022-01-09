<template>
  <b-form inline @click.prevent="addDepartment">
    <label class="sr-only" for="title">Название</label>
    <b-form-input
      id="title"
      v-model="form_data.title"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите название"
    ></b-form-input>

    <b-form-select
      id="dep_head"
      v-model="form_data.head_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="options"
      :value="null"
    ></b-form-select>

    <input type="submit" value="Добавить" class="btn btn-dark">
  </b-form>
</template>

<script>

import axios from "axios"

export default {
  name: "departments",
  props: {
    called_func: {
      required: false
    }
  },
  data() {
    return {
      options: [ 
      { value: null, text: 'Выберите сотрудника' },
      ],
      form_data: {
        title: "",
        head_id: null
      }
    }
  },
  async mounted() {
    const work = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")
    work.data.forEach(item => {
        this.options.push({value: item.id, text: item.name})
    })
  },
  methods: {
    async addDepartment() {
      try{
        if (this.form_data.title === "" || this.form_data.head_id === null)
          return

        const form = new FormData()
        form.append("title", this.form_data.title)
        form.append("head_id", this.form_data.head_id)

        const res = await axios.post("https://spring-db-course.herokuapp.com/departments/add", form)
        this.form_data.title = ""
        this.form_data.head_id = null
      } catch (e) {
        console.log(e)
      }
      await this.called_func()
    }
  }
}
</script>


<style scoped>
</style>
