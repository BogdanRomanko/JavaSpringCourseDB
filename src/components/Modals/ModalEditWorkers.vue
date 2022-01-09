<template>
    <b-modal id="my-modal" title="Изменить сотрудника" :edit_id="this.edit_id" @hidden="hidden()" @close="hidden()">
        <b-form class="workers-add-form">
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
        </b-form>

        <template #modal-footer="{ ok }">
                <input size="sm" type="submit" value="Сохранить" class="btn btn-dark" @click="editWorker(); ok()">
        </template>
    </b-modal>
</template>

<script>
import axios from "axios"

export default {
    name: "edit-workers",
    props: {
        edit_id: {
            required: true
        },
    },
    data() {
        return {
            options: [ 
                { value: null, text: 'Выберите кафедру' },
            ],
            form_data: {
                worker_name: "",
                dep_id: null,
                role: ""
            },
            is_edited: false
        }
    },
    async mounted() {
        const dep = await axios.get("https://spring-db-course.herokuapp.com/departments/getAllDepartments")
        dep.data.forEach(item => {
            this.options.push({value: item.id, text: item.title})
        })
    },
    async updated() {
        if (!this.is_edited) {
            const data = await axios.get(`https://spring-db-course.herokuapp.com/workers/get?id=${this.edit_id}`)
            this.form_data.worker_name = data.data.name
            this.form_data.dep_id = data.data.dep_id
            this.form_data.role = data.data.role
            this.is_edited = true
        }
    },
    methods: {
        async editWorker(){
             if (this.form_data.worker_name === "" || this.form_data.dep_id === null || this.form_data.role === "")
                return
          
            const form = new FormData()
            form.append("id", this.edit_id)
            form.append("name", this.form_data.worker_name)
            form.append("dep_id", this.form_data.dep_id)
            form.append("role", this.form_data.role)

            const res = await axios.put("https://spring-db-course.herokuapp.com/workers/edit", form)
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
