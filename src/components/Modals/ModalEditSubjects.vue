<template>
    <b-modal id="my-modal" title="Изменить дисциплину" :edit_id="this.edit_id" @hidden="hidden()" @close="hidden()">
        <b-form>
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
        </b-form>

        <template #modal-footer="{ ok }">
            <input size="sm" type="submit" value="Сохранить" class="btn btn-dark" @click="editSubject(); ok()">
        </template>
    </b-modal>
</template>

<script>
import axios from "axios"

export default {
    name: "edit-subjects",
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
            },
            is_edited: false
        }
    },
    async mounted() {
        const teachers = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")

        teachers.data.forEach(teacher => {
            this.teacher_options.push({value: teacher.id, text: teacher.name})
        })
    },
    async updated() {
        if (!this.is_edited) {
            const data = await axios.get(`https://spring-db-course.herokuapp.com/subjects/get?id=${this.edit_id}`)
            this.form_data.title = data.data.title
            this.form_data.teacher_id = data.data.teacher_id
            this.form_data.is_exam = data.data.is_exam
            this.is_edited = true
        }
    },
    methods: {
        async editSubject(){
            if (this.form_data.title === "" || this.form_data.teacher_id === null || this.form_data.is_exam === null)
                return
          
            const form = new FormData()
            form.append("id", this.edit_id)
            form.append("title", this.form_data.title)
            form.append("teacher_id", this.form_data.teacher_id)
            form.append("is_exam", this.form_data.is_exam)

            const res = await axios.put("https://spring-db-course.herokuapp.com/subjects/edit", form)
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
