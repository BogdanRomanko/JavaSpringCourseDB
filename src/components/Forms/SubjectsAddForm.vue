<template>
  <b-form inline @click.prevent="addSubject">
    <label class="sr-only" for="title">Название</label>
    <b-form-input
      id="title"
      v-model="form_data.title"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите название"
    ></b-form-input>

    <b-form-select
      id="teacher_id"
      v-model="form_data.teacher_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="teacher_options"
      :value="null"
    ></b-form-select>

    <b-form-select
      id="is_exam"
      v-model="form_data.is_exam"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="exam_options"
      :value="null"
    ></b-form-select>

    <input type="submit" value="Добавить" class="btn btn-dark">
  </b-form>
</template>

<script>

import axios from "axios"

export default {
  name: "subjects",
  props: {
    called_func: {
      required: false
    }
  },
  data() {
    return {
      teacher_options: [ 
        { value: null, text: 'Выберите преподавателя' },
      ],
      exam_options: [
        { value: null, text: 'Экзамен' },
        { value: 0, text: 'Нет' },
        { value: 1, text: 'Да' }
      ],
      form_data: {
        title: "",
        teacher_id: null,
        is_exam: null,
      }
    }
  },
  async mounted() {
    const teachers = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")

    teachers.data.forEach(teacher => {
        this.teacher_options.push({value: teacher.id, text: teacher.name})
    })
  },
  methods: {
    async addSubject() {
      try{
        if (this.form_data.title === "" || this.form_data.teacher_id === null || this.form_data.is_exam === null)
          return

        const form = new FormData()
        form.append("title", this.form_data.title)
        form.append("teacher_id", this.form_data.teacher_id)
        form.append("is_exam", this.form_data.is_exam)

        const res = await axios.post("https://spring-db-course.herokuapp.com/subjects/add", form)
        this.form_data.title = ""
        this.form_data.teacher_id = null
        this.form_data.is_exam = null
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
