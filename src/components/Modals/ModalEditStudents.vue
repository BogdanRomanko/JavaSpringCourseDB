<template>
    <b-modal id="my-modal" title="Изменить студента" :edit_id="this.edit_id" @hidden="hidden()" @close="hidden()">
        <b-form>
            <label class="sr-only" for="name">Имя</label>
            <b-form-input
            id="name"
            v-model="form_data.name"
            class="mb-2 mr-sm-2 mb-sm-0"
            placeholder="Введите имя"
            ></b-form-input>

            <b-form-select
            id="group_id"
            v-model="form_data.group_id"
            class="mb-2 mr-sm-2 mb-sm-0"
            :options="group_options"
            :value="null"
            ></b-form-select>

            <b-form-select
            id="dep_id"
            v-model="form_data.dep_id"
            class="mb-2 mr-sm-2 mb-sm-0"
            :options="dep_options"
            :value="null"
            ></b-form-select>

            <label class="sr-only" for="dir_st">Форма обучения</label>
            <b-form-input
            id="type_of_training"
            v-model="form_data.type_of_training"
            class="mb-2 mr-sm-2 mb-sm-0"
            placeholder="Введите форму обучения"
            ></b-form-input>

            <b-form-select
            id="is_budget"
            v-model="form_data.is_budget"
            class="mb-2 mr-sm-2 mb-sm-0"
            :options="budget_options"
            :value="null"
            ></b-form-select>

            <b-form-select
            id="is_praestor"
            v-model="form_data.is_praestor"
            class="mb-2 mr-sm-2 mb-sm-0"
            :options="praestor_options"
            :value="null"
            ></b-form-select>
        </b-form>

        <template #modal-footer="{ ok }">
                <input size="sm" type="submit" value="Сохранить" class="btn btn-dark" @click="editStudent(); ok()">
        </template>
    </b-modal>
</template>

<script>
import axios from "axios"

export default {
    name: "edit-students",
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
            group_options: [ 
                { value: null, text: 'Выберите группу' },
            ],
            praestor_options: [
                { value: null, text: 'Является старостой группы' },
                { value: 0, text: 'Нет' },
                { value: 1, text: 'Да' }
            ],
            budget_options: [
                { value: null, text: 'На бюджете' },
                { value: 0, text: 'Нет' },
                { value: 1, text: 'Да' }
            ],
            form_data: {
                name: "",
                group_id: null,
                dep_id: null,
                type_of_training: "",
                is_budget: null,
                is_praestor: null
            },
            is_edited: false
        }
    },
    async mounted() {
        const deps = await axios.get("https://spring-db-course.herokuapp.com/departments/getAllDepartments")
        deps.data.forEach(item => {
            this.dep_options.push({value: item.id, text: item.title})
        })

        const groups = await axios.get("https://spring-db-course.herokuapp.com/groups/getAllGroups")
        groups.data.forEach(item => {
            this.group_options.push({value: item.id, text: item.title})
        })
    },
    async updated() {
        if (!this.is_edited) {
            const data = await axios.get(`https://spring-db-course.herokuapp.com/students/get?id=${this.edit_id}`)
            this.form_data.name = data.data.name
            this.form_data.group_id = data.data.group_id
            this.form_data.dep_id = data.data.dep_id
            this.form_data.type_of_training = data.data.type_of_training
            this.form_data.is_budget = data.data.is_budget
            this.form_data.is_praestor = data.data.is_praestor
            this.is_edited = true
        }
    },
    methods: {
        async editStudent(){
            if (this.form_data.name === "" || this.form_data.dep_id === null || this.form_data.group_id === null
                || this.form_data.is_budget === null || this.form_data.is_praestor === "" || this.form_data.type_of_training === "")
                return
          
            const form = new FormData()
            form.append("id", this.edit_id)
            form.append("name", this.form_data.name)
            form.append("group_id", this.form_data.group_id)
            form.append("dep_id", this.form_data.dep_id)
            form.append("type_of_tr", this.form_data.type_of_training)
            form.append("is_budget", this.form_data.is_budget)
            form.append("is_praestor", this.form_data.is_praestor)

            const res = await axios.put("https://spring-db-course.herokuapp.com/students/edit", form)
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
