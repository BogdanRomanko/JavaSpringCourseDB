<template>
  <b-form inline @click.prevent="addGroup">
    <label class="sr-only" for="title">Название</label>
    <b-form-input
      id="title"
      v-model="form_data.title"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите название"
    ></b-form-input>

    <b-form-select
      id="dep_id"
      v-model="form_data.dep_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="dep_options"
      :value="null"
    ></b-form-select>

    <b-form-select
      id="praestor_id"
      v-model="form_data.praestor_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="stud_options"
      :value="null"
    ></b-form-select>

    <label class="sr-only" for="sum">Сумма обучения</label>
    <b-form-input
      type="number"
      id="sum"
      v-model="form_data.sum"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите сумму обучения"
    ></b-form-input>

    <label class="sr-only" for="dir_st">Форма обучения</label>
    <b-form-input
      id="dir_st"
      v-model="form_data.dir_st"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите форму обучения"
    ></b-form-input>

    <b-form-select
      id="curator_id"
      v-model="form_data.curator_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="work_options"
      :value="null"
    ></b-form-select>

    <label class="sr-only" for="size">Размер группы</label>
    <b-form-input
      type="number"
      id="size"
      v-model="form_data.size"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите размер"
    ></b-form-input>

    <input type="submit" value="Добавить" class="btn btn-dark">
  </b-form>
</template>

<script>

import axios from "axios"

export default {
  name: "groups",
  props: {
    called_func: {
      required: false
    }
  },
  data() {
    return {
      dep_options: [ 
      { value: null, text: 'Выберите кафедру' },
      ],
      stud_options: [ 
      { value: null, text: 'Выберите старосту' },
      ],
      work_options: [ 
      { value: null, text: 'Выберите куратора' },
      ],
      form_data: {
        title: "",
        dep_id: null,
        praestor_id: null,
        sum: null,
        dir_st: "",
        curator_id: null,
        size: null
      }
    }
  },
  async mounted() {
    const deps = await axios.get("https://spring-db-course.herokuapp.com/departments/getAllDepartments")
    deps.data.forEach(item => {
        this.dep_options.push({value: item.id, text: item.title})
    })

    const work = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")
    work.data.forEach(item => {
        this.work_options.push({value: item.id, text: item.name})
    })

    const stud = await axios.get("https://spring-db-course.herokuapp.com/students/getAllStudents")
    stud.data.forEach(item => {
        this.stud_options.push({value: item.id, text: item.name})
    })
  },
  methods: {
    async addGroup() {
      try{
        if (this.form_data.title === "" || this.form_data.dep_id === null || this.form_data.praestor_id === null
            || this.form_data.sum === null || this.form_data.dir_st === "" || this.form_data.curator_id === null
            || this.form_data.size === null)
          return

        const form = new FormData()
        form.append("title", this.form_data.title)
        form.append("dep_id", this.form_data.dep_id)
        form.append("praepostor_id", this.form_data.praestor_id)
        form.append("sum_year", this.form_data.sum)
        form.append("direction_on_study", this.form_data.dir_st)
        form.append("curator_id", this.form_data.curator_id)
        form.append("size", this.form_data.size)

        const res = await axios.post("https://spring-db-course.herokuapp.com/groups/add", form)
        this.form_data.title = ""
        this.form_data.dep_id = null
        this.form_data.praestor_id = null
        this.form_data.sum = null
        this.form_data.dir_st = ""
        this.form_data.curator_id = null
        this.form_data.size = null
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
