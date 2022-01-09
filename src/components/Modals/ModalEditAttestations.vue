<template>
    <b-modal id="my-modal" title="Изменить результаты" :edit_id="this.edit_id" @hidden="hidden()" @close="hidden()">
        <b-form>
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
            max="5"
            id="value"
            v-model="form_data.value"
            class="mb-2 mr-sm-2 mb-sm-0"
            placeholder="Введите значение"
            ></b-form-input>
        </b-form>
        <template #modal-footer="{ ok }">
            <input size="sm" type="submit" value="Сохранить" class="btn btn-dark" @click="editAttestation(); ok()">
        </template>
    </b-modal>
</template>

<script>
import axios from "axios"

export default {
    name: "edit-attestations",
    props: {
        edit_id: {
            required: true
        },
        called_func: {
            required: true
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
            student_options: [
                { value: null, text: 'Выберите студента' },
            ],
            form_data: {
                group_id: null,
                subject_id: null,
                student_id: null,
                value: null
            },
            is_edited: false
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
  async updated() {
        if (!this.is_edited) {
            const data = await axios.get(`https://spring-db-course.herokuapp.com/attestation/get?id=${this.edit_id}`)
            this.form_data.group_id = data.data.group_id
            this.form_data.subject_id = data.data.subject_id
            this.form_data.student_id = data.data.student_id
            this.form_data.value = data.data.value

            this.is_edited = true
        }
    },
    methods: {
        async editResult(){
            if (this.form_data.group_id === null || this.form_data.subject_id === null || this.form_data.student_id === null || this.form_data.value === null)
                return
          
            const form = new FormData()
            form.append("id", this.edit_id)
            form.append("group_id", this.form_data.group_id)
            form.append("subject_id", this.form_data.subject_id)
            form.append("student_id", this.form_data.student_id)
            form.append("value", this.form_data.value)

            const res = await axios.put("https://spring-db-course.herokuapp.com/attestation/edit", form)
            this.is_edited = false
            await this.called_func()
        },
        hidden() {
            this.is_edited = false
        }
    }
}
</script>

<style scoped>

</style>
