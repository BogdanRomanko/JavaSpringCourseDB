<template>
    <b-modal id="my-modal" title="Изменить группу" :edit_id="this.edit_id" @hidden="hidden()" @close="hidden()">
        <b-form>
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
                placeholder="Введите сумму"
            ></b-form-input>

            <label class="sr-only" for="dir_st">Форма обучения</label>
            <b-form-input
                id="dir_st"
                v-model="form_data.dir_st"
                class="mb-2 mr-sm-2 mb-sm-0"
                placeholder="Введите форму"
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
        </b-form>
        <template #modal-footer="{ ok }">
                <input size="sm" type="submit" value="Сохранить" class="btn btn-dark" @click="editGroup(); ok()">
        </template>
    </b-modal>
</template>

<script>
import axios from "axios"

export default {
    name: "edit-groups",
    props: {
        edit_id: {
            required: true
        },
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
            },
            is_edited: false
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
  async updated() {
        if (!this.is_edited) {
            const data = await axios.get(`https://spring-db-course.herokuapp.com/groups/get?id=${this.edit_id}`)
            this.form_data.title = data.data.title
            this.form_data.dep_id = data.data.dep_id
            this.form_data.praestor_id = data.data.praestor_id
            this.form_data.sum = data.data.sum_year
            this.form_data.dir_st = data.data.direction_on_study
            this.form_data.curator_id = data.data.curator_id
            this.form_data.size = data.data.size

            this.is_edited = true
        }
    },
    methods: {
        async editGroup(){
            if (this.form_data.title === "" || this.form_data.dep_id === null || this.form_data.praestor_id === null
                 || this.form_data.sum === null || this.form_data.dir_st === "" || this.form_data.curator_id === null
                 || this.form_data.size === null)
                return
          
            const form = new FormData()
            form.append("id", this.edit_id)
            form.append("title", this.form_data.title)
            form.append("dep_id", this.form_data.dep_id)
            form.append("praepostor_id", this.form_data.praestor_id)
            form.append("sum_year", this.form_data.sum)
            form.append("direction_on_study", this.form_data.dir_st)
            form.append("curator_id", this.form_data.curator_id)
            form.append("size", this.form_data.size)

            const res = await axios.put("https://spring-db-course.herokuapp.com/groups/edit", form)
            this.is_edited = false
        },
        hidden() {
            this.is_edited = false
        }
    }
}
</script>

<style scoped>

</style>
