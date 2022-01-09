<template>
  <b-form inline @click.prevent="addResult">
    <label class="sr-only" for="statement_id">Ведомость</label>
    <b-form-input
      type="number"
      id="statement_id"
      v-model="form_data.statement_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите номер ведомости"
    ></b-form-input>

    <label for="date">Выберите дату</label>
    <b-form-datepicker
      id="date"
      v-model="form_data.date"
      class="mb-2"
    ></b-form-datepicker>

    <label class="sr-only" for="classroom">Аудитория</label>
    <b-form-input
      type="number"
      id="classroom"
      v-model="form_data.classroom"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите номер аудитории"
    ></b-form-input>

    <b-form-select
      id="group_id"
      v-model="form_data.group_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="group_options"
      :value="null"
    ></b-form-select>

    <b-form-select
      id="subject_id"
      v-model="form_data.subject_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="subject_options"
      :value="null"
    ></b-form-select>

    <b-form-select
      id="teacher_id"
      v-model="form_data.teacher_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="teacher_options"
      :value="null"
    ></b-form-select>

    <b-form-select
      id="student_id"
      v-model="form_data.student_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="student_options"
      :value="null"
    ></b-form-select>

    <label class="sr-only" for="value">Значение</label>
    <b-form-input
      type="number"
      min="0"
      max="5"
      id="value"
      v-model="form_data.value"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите значение"
    ></b-form-input>

    <input type="submit" value="Добавить" class="btn btn-dark">
  </b-form>
</template>

<script>

import axios from "axios"

export default {
  name: "results",
  props: {
    called_func: {
      required: false
    }
  },
  data() {
    return {
      group_options: [ 
        { value: null, text: 'Выберите группу' },
      ],
      subject_options: [ 
        { value: null, text: 'Выберите дисциплину' },
      ],
      teacher_options: [
        { value: null, text: 'Выберите преподавателя' },
      ],
      student_options: [
        { value: null, text: 'Выберите студента' },
      ],
      form_data: {
        statement_id: "",
        date: null,
        classroom: "",
        group_id: null,
        subject_id: null,
        teacher_id: null,
        student_id: null,
        value: null
      }
    }
  },
  async mounted() {
    const groups = await axios.get("https://spring-db-course.herokuapp.com/groups/getAllGroups")
    groups.data.forEach(item => {
        this.group_options.push({value: item.id, text: item.title})
    })

    const subjects = await axios.get("https://spring-db-course.herokuapp.com/subjects/getAllSubjects")
    subjects.data.forEach(item => {
        this.subject_options.push({value: item.id, text: item.title})
    })

    const teachers = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")
    teachers.data.forEach(item => {
        this.teacher_options.push({value: item.id, text: item.name})
    })

    const students = await axios.get("https://spring-db-course.herokuapp.com/students/getAllStudents")
    students.data.forEach(item => {
        this.student_options.push({value: item.id, text: item.name})
    })
  },
  methods: {
    async addResult() {
      try{
        if (this.form_data.statement_id === null || this.form_data.date === null || this.form_data.classroom === null
            || this.form_data.group_id === null || this.form_data.subject_id === null || this.form_data.teacher_id === null
            || this.form_data.student_id === null || this.form_data.value === null)
          return

        const form = new FormData()
        form.append("statement_id", this.form_data.statement_id)
        form.append("date", this.form_data.date)
        form.append("classroom", this.form_data.classroom)
        form.append("group_id", this.form_data.group_id)
        form.append("subject_id", this.form_data.subject_id)
        form.append("teacher_id", this.form_data.teacher_id)
        form.append("student_id", this.form_data.student_id)
        form.append("value", this.form_data.value)

        const res = await axios.post("https://spring-db-course.herokuapp.com/results/add", form)
        this.form_data.statement_id = null
        this.form_data.date = null
        this.form_data.classroom = null
        this.form_data.group_id = null
        this.form_data.subject_id = null
        this.form_data.teacher_id = null
        this.form_data.student_id = null
        this.form_data.value = null
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
