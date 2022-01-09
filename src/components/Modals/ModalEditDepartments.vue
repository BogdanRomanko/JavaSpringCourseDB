<template>
    <b-modal id="my-modal" title="Изменить кафедру" :edit_id="this.edit_id" @hidden="hidden()" @close="hidden()">
        <b-form >
            <label class="sr-only" for="title">Название</label>
            <b-form-input
            id="title"
            v-model="form_data.title"
            class="mb-2 mr-sm-2 mb-sm-0"
            placeholder="Введите название"
            ></b-form-input>

            <b-form-select
            id="dep_head"
            v-model="form_data.head_id"
            class="mb-2 mr-sm-2 mb-sm-0"
            :options="options"
            :value="null"
            ></b-form-select>

        </b-form>
        <template #modal-footer="{ ok }">
                <input size="sm" type="submit" value="Сохранить" class="btn btn-dark" @click="editDepartment(); ok()">
        </template>
    </b-modal>
</template>

<script>
import axios from "axios"

export default {
    name: "edit-departments",
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
            options: [ 
            { value: null, text: 'Выберите сотрудника' },
            ],
            form_data: {
                title: "",
                head_id: null
            },
            is_edited: false
        }
  },
  async mounted() {
    const work = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")

    work.data.forEach(item => {
        this.options.push({value: item.id, text: item.name})
    })
  },
  async updated() {
        if (!this.is_edited) {
            const data = await axios.get(`https://spring-db-course.herokuapp.com/departments/get?id=${this.edit_id}`)
            this.form_data.title = data.data.title
            this.form_data.head_id = data.data.head_of_dep_id
            this.is_edited = true
        }
    },
    methods: {
        async editDepartment(){
            if (this.form_data.title === "" || this.form_data.head_id === null)
                return
          
            const form = new FormData()
            form.append("id", this.edit_id)
            form.append("title", this.form_data.title)
            form.append("head_id", this.form_data.head_id)

            const res = await axios.put("https://spring-db-course.herokuapp.com/departments/edit", form)
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
