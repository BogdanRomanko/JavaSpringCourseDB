<template>
  <b-form inline>
    <b-form-select
      id="subject_id"
      v-model="form_data.subject_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="subject_options"
      :value="null"
    ></b-form-select>

    <b-form-select
      id="group_id"
      v-model="form_data.group_id"
      class="mb-2 mr-sm-2 mb-sm-0"
      :options="group_options"
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
      max="2"
      id="value"
      v-model="form_data.value"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Введите значение"
    ></b-form-input>

    <input type="submit" value="Добавить" class="btn btn-dark" @click="addAttestation">
  </b-form>
</template>

<script>

import axios from "axios"

export default {
  name: "attestation",
  data() {
    return {
      group_options: [ 
        { value: null, text: 'Выберите группу' },
      ],
      subject_options: [ 
        { value: null, text: 'Выберите дисциплину' },
      ],
      student_options: [
        { value: null, text: 'Выберите студента' },
      ],
      form_data: {
        group_id: null,
        subject_id: null,
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

    const students = await axios.get("https://spring-db-course.herokuapp.com/students/getAllStudents")
    students.data.forEach(item => {
        this.student_options.push({value: item.id, text: item.name})
    })
  },
  methods: {
    async addAttestation() {
      try {
        if (this.form_data.group_id === null || this.form_data.subject_id === null || this.form_data.student_id === null || this.form_data.value === null)
          return

        const form = new FormData()
        form.append("group_id", this.form_data.group_id)
        form.append("subject_id", this.form_data.subject_id)
        form.append("student_id", this.form_data.student_id)
        form.append("value", this.form_data.value)

        const res = await axios.post("https://spring-db-course.herokuapp.com/attestation/add", form)
      } catch (e) {
        console.log(e)
      }
      this.$emit("onAddData")
    }
  }
}
</script>


<style scoped>
</style>
