<template>
  <b-form inline class="workers-add-form" @click.prevent="addWorker">
    <label class="sr-only" for="worker_name">Имя</label>
    <b-form-input
      id="worker_name"
      v-model="form_data.worker_name"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите имя"
    ></b-form-input>

    <b-form-select
      id="dep_id"
      v-model="form_data.dep_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="options"
      :value="null"
    ></b-form-select>

    <label class="sr-only" for="worker_role">Роль</label>
    <b-form-input
      id="worker_role"
      v-model="form_data.role"
      placeholder="Введите роль"
    ></b-form-input>

    <input type="submit" value="Добавить" class="btn btn-dark">
  </b-form>
</template>

<script>

import axios from "axios"

export default {
  name: "workers",
  data() {
    return {
        options: [ 
        { value: null, text: 'Выберите кафедру' },
        ],
        form_data: {
        worker_name: "",
        dep_id: null,
        role: ""
      }
    }
  },
  async mounted() {
    const dep = await axios.get("https://spring-db-course.herokuapp.com/departments/getAllDepartments")
    dep.data.forEach(item => {
        this.options.push({value: item.id, text: item.title})
    })
  },
  methods: {
    async addWorker() {
      try{
        if (this.form_data.worker_name === "" || this.form_data.dep_id === null || this.form_data.role === "")
          return
          
        const form = new FormData()
        form.append("name", this.form_data.worker_name)
        form.append("id", this.form_data.dep_id)
        form.append("role", this.form_data.role)
        console.log(this.form_data)

        const res = await axios.post("https://spring-db-course.herokuapp.com/workers/add", form)
      } catch (e) {
        console.log(e)
      }
      this.$emit("onAddData")
    }
  }
}
</script>


<style scoped>
.workers-add-form{
  margin-bottom: 15px;
}
</style>
