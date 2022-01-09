<template>
   <b-navbar type="dark" variant="dark" id="my-nav">
    <b-navbar-nav align="center">
      
    <b-nav-item class="nav-menu-item" @click="getAllDepartments">Кафедры</b-nav-item>
    <b-nav-item class="nav-menu-item" @click="getAllWorkers">Сотрудники</b-nav-item>
    <b-nav-item class="nav-menu-item" @click="getAllGroups">Группы</b-nav-item>
    <b-nav-item class="nav-menu-item" @click="getAllStudents">Студенты</b-nav-item>
    <b-nav-item class="nav-menu-item" @click="getAllSubjects">Дисциплины</b-nav-item>
    <b-nav-item class="nav-menu-item" @click="getAllAttestations">Аттестация</b-nav-item>
    <b-nav-item class="nav-menu-item" @click="getAllResults">Результаты</b-nav-item>
    <b-nav-item-dropdown
      class="nav-menu-item"
      id="my-nav-dropdown"
      text="Дополнительно"
      toggle-class="nav-link-custom"
      right
    >
      <b-dropdown-item @click="getAllPraestors">Список старост</b-dropdown-item>
      <b-dropdown-item @click="getAllCurators">Список кураторов</b-dropdown-item>
      <b-dropdown-item @click="getAllBudgets">Список бюджетников</b-dropdown-item>
      <b-dropdown-item @click="getAllContracts">Список контрактников</b-dropdown-item>
    </b-nav-item-dropdown>
    
    </b-navbar-nav>
   </b-navbar>
</template>

<script>
import axios from "axios"

import WorkersAddForm from './Forms/WorkersAddForm.vue'
import DepartmentsAddForm from './Forms/DepartmentsAddForm.vue'
import GroupsAddForm from "./Forms/GroupsAddForm.vue"
import StudentsAddForm from "./Forms/StudentsAddForm.vue"
import SubjectsAddForm from "./Forms/SubjectsAddForm.vue"
import ResultsAddForm from "./Forms/ResultsAddForm.vue"
import AttestationsAddForm from "./Forms/AttestationsAddForm.vue"

import ModalEditWorkers from "./Modals/ModalEditWorkers.vue"
import ModalEditDepartments from "./Modals/ModalEditDepartments.vue"
import ModalEditGroups from "./Modals/ModalEditGroups.vue"
import ModalEditStudents from "./Modals/ModalEditStudents.vue"
import ModalEditSubjects from "./Modals/ModalEditSubjects.vue"
import ModalEditResults from "./Modals/ModalEditResults.vue"
import ModalEditAttestations from "./Modals/ModalEditAttestations.vue"

export default {
  data() {
    return {
      fields: [],
      items: []
    }
  },
  methods: {
    async getAllWorkers() {
      const data = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")
      const dep = await axios.get("https://spring-db-course.herokuapp.com/departments/getAllDepartments")

      data.data.forEach(worker => {
        dep.data.forEach(depart => {
          if (worker.dep_id === depart.id)
            worker.dep_id = depart.title
        })
      })

      this.fields = [
        {
          key: "id",
          label: "Номер",
          sortable: true
          },
        {
          key: "name",
          label: "Имя",
          sortable: true
        },
        {
          key: "dep_id",
          label: "Кафедра",
          sortable: true
        },
        {
          key: "role",
          label: "Роль",
          sortable: true
        },
        {
          key: "edited",
          label: "Действия",
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        form_component: WorkersAddForm,
        edit_form_component: ModalEditWorkers,
        called_func: this.getAllWorkers
      })
      return {fields: this.fields, items: this.items}
    },
    async getAllDepartments() {
      const data = await axios.get("https://spring-db-course.herokuapp.com/departments/getAllDepartments")
      const workers = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")

      data.data.forEach(dep => {
        workers.data.forEach(worker => {
          if (dep.head_of_dep_id === worker.id)
            dep.head_of_dep_id = worker.name
        })
      })

      this.fields = [
        {
          key: "id",
          label: "Номер",
          sortable: true
          },
        {
          key: "title",
          label: "Название",
          sortable: true
        },
        {
          key: "head_of_dep_id",
          label: "Заведующий",
          sortable: true
        },
        {
          key: "edited",
          label: "Действия",
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        form_component: DepartmentsAddForm,
        edit_form_component: ModalEditDepartments,
        called_func: this.getAllDepartments
      })
      return {fields: this.fields, items: this.items}
    },
    async getAllGroups() {
      const data = await axios.get("https://spring-db-course.herokuapp.com/groups/getAllGroups")
      const dep = await axios.get("https://spring-db-course.herokuapp.com/departments/getAllDepartments")
      const students = await axios.get("https://spring-db-course.herokuapp.com/students/getAllStudents")
      const workers = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")

      data.data.forEach(group => {
        dep.data.forEach(depart => {
          if (group.dep_id === depart.id)
            group.dep_id = depart.title
        })
        students.data.forEach(student => {
          if (group.praestor_id === student.id)
            group.praestor_id = student.name
        })

        workers.data.forEach(worker => {
          if (group.curator_id === worker.id)
            group.curator_id = worker.name
        })
      })

      this.fields = [
        {
          key: "id",
          label: "Номер",
          sortable: true
          },
        {
          key: "title",
          label: "Название",
          sortable: true
        },
        {
          key: "dep_id",
          label: "Кафедра",
          sortable: true
        },
        {
          key: "praestor_id",
          label: "Староста",
          sortable: true
        },
        {
          key: "sum_year",
          label: "Сумма обучения",
          sortable: true
        },
        {
          key: "direction_on_study",
          label: "Форма обучения",
          sortable: true
        },
        {
          key: "curator_id",
          label: "Куратор",
          sortable: true
        },
        {
          key: "size",
          label: "Размер группы",
          sortable: true
        },
        {
          key: "edited",
          label: "Действия",
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        form_component: GroupsAddForm,
        edit_form_component: ModalEditGroups,
        called_func: this.getAllGroups
      })
      return {fields: this.fields, items: this.items}
    },
    async getAllStudents() {
      const data = await axios.get("https://spring-db-course.herokuapp.com/students/getAllStudents")
      const groups = await axios.get("https://spring-db-course.herokuapp.com/groups/getAllGroups")
      const dep = await axios.get("https://spring-db-course.herokuapp.com/departments/getAllDepartments")

      data.data.forEach(student => {
        dep.data.forEach(depart => {
          if (student.dep_id === depart.id)
            student.dep_id = depart.title
        })
        groups.data.forEach(group => {
          if (student.group_id === group.id)
            student.group_id = group.title
        })
        if (student.is_budget === 1)
          student.is_budget = "Да"
        else
          student.is_budget = "Нет"

        if (student.is_praestor === 1)
          student.is_praestor = "Да"
        else
          student.is_praestor = "Нет"
      })

      this.fields = [
        {
          key: "id",
          label: "Номер",
          sortable: true
          },
        {
          key: "name",
          label: "Имя",
          sortable: true
        },
        {
          key: "group_id",
          label: "Группа",
          sortable: true
        },
        {
          key: "dep_id",
          label: "Кафедра",
          sortable: true
        },
        {
          key: "type_of_training",
          label: "Форма обучения",
          sortable: true
        },
        {
          key: "is_budget",
          label: "Бюджет",
          sortable: true
        },
        {
          key: "is_praestor",
          label: "Староста",
          sortable: true
        },
        {
          key: "edited",
          label: "Действия",
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        form_component: StudentsAddForm,
        edit_form_component: ModalEditStudents,
        called_func: this.getAllStudents
      })
      return {fields: this.fields, items: this.items}
    },
    async getAllSubjects() {
      const data = await axios.get("https://spring-db-course.herokuapp.com/subjects/getAllSubjects")
      const teachers = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")

      data.data.forEach(subject => {
        teachers.data.forEach(teacher => {
          if (subject.teacher_id === teacher.id)
            subject.teacher_id = teacher.name
        })

        if (subject.is_exam === 1)
          subject.is_exam = "Да"
        else
          subject.is_exam = "Нет"
      })

      this.fields = [
        {
          key: "id",
          label: "Номер",
          sortable: true
          },
        {
          key: "title",
          label: "Название",
          sortable: true
        },
        {
          key: "teacher_id",
          label: "Преподаватель",
          sortable: true
        },
        {
          key: "is_exam",
          label: "Экзамен",
          sortable: true
        },
        {
          key: "edited",
          label: "Действия",
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        form_component: SubjectsAddForm,
        edit_form_component: ModalEditSubjects,
        called_func: this.getAllSubjects
      })
      return {fields: this.fields, items: this.items}
    },
    async getAllAttestations(){
      const data = await axios.get("https://spring-db-course.herokuapp.com/attestation/getAllAttestations")
      const subjects = await axios.get("https://spring-db-course.herokuapp.com/subjects/getAllSubjects")
      const groups = await axios.get("https://spring-db-course.herokuapp.com/groups/getAllGroups")
      const students = await axios.get("https://spring-db-course.herokuapp.com/students/getAllStudents")

      data.data.forEach(attestation => {
        subjects.data.forEach(subject => {
          if (attestation.subject_id === subject.id)
            attestation.subject_id = subject.title
        })

        groups.data.forEach(group => {
          if (attestation.group_id === group.id)
            attestation.group_id = group.title
        })

        students.data.forEach(student => {
          if (attestation.student_id === student.id)
            attestation.student_id = student.name
        })
      })
      console.log(data.data)
      this.fields = [
        {
          key: "id",
          label: "Номер",
          sortable: true
          },
        {
          key: "subject_id",
          label: "Дисциплина",
          sortable: true
        },
        {
          key: "group_id",
          label: "Группа",
          sortable: true
        },
        {
          key: "student_id",
          label: "Студент",
          sortable: true
        },
        {
          key: "value",
          label: "Аттестация",
          sortable: true
        },
        {
          key: "edited",
          label: "Действия",
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        form_component: AttestationsAddForm,
        edit_form_component: ModalEditAttestations,
        called_func: this.getAllAttestations
      })
      return {fields: this.fields, items: this.items}
    },
    async getAllResults() {
      const data = await axios.get("https://spring-db-course.herokuapp.com/results/getAllResults")
      const groups = await axios.get("https://spring-db-course.herokuapp.com/groups/getAllGroups")
      const subjects = await axios.get("https://spring-db-course.herokuapp.com/subjects/getAllSubjects")
      const teachers = await axios.get("https://spring-db-course.herokuapp.com/workers/getAllWorkers")
      const students = await axios.get("https://spring-db-course.herokuapp.com/students/getAllStudents")

      data.data.forEach(result => {
        groups.data.forEach(group => {
          if (result.group_id === group.id)
            result.group_id = group.title
        })

        subjects.data.forEach(subject => {
          if (result.subject_id === subject.id)
            result.subject_id = subject.title
        })

        teachers.data.forEach(teacher => {
          if (result.teacher_id === teacher.id)
            result.teacher_id = teacher.name
        })

        students.data.forEach(student => {
          if (result.student_id === student.id)
            result.student_id = student.name
        })
      })

      this.fields = [
        {
          key: "id",
          label: "Номер",
          sortable: true
          },
        {
          key: "statement_id",
          label: "Номер ведомости",
          sortable: true
        },
        {
          key: "date",
          label: "Дата",
          sortable: true
        },
        {
          key: "classroom",
          label: "Кабинет",
          sortable: true
        },
        {
          key: "group_id",
          label: "Группа",
          sortable: true
        },
        {
          key: "subject_id",
          label: "Дисциплина",
          sortable: true
        },
        {
          key: "teacher_id",
          label: "Преподаватель",
          sortable: true
        },
        {
          key: "student_id",
          label: "Студент",
          sortable: true
        },
        {
          key: "value",
          label: "Значение",
          sortable: true
        },
        {
          key: "edited",
          label: "Действия",
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        form_component: ResultsAddForm,
        edit_form_component: ModalEditResults,
        called_func: this.getAllResults
      })
      return {fields: this.fields, items: this.items}
    },
    async getAllPraestors() {
      const data = await axios.get("https://spring-db-course.herokuapp.com/additional/praestors")

      this.fields = [
        {
          key: "name",
          label: "Имя",
          sortable: true
          },
        {
          key: "title",
          label: "Название",
          sortable: true
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        is_additional: true
      })
    },
    async getAllCurators() {
      const data = await axios.get("https://spring-db-course.herokuapp.com/additional/curators")

      this.fields = [
        {
          key: "full_name",
          label: "Имя",
          sortable: true
          },
        {
          key: "title",
          label: "Группа",
          sortable: true
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        is_additional: true
      })
    },
    async getAllBudgets() {
      const data = await axios.get("https://spring-db-course.herokuapp.com/additional/budgets")

      this.fields = [
        {
          key: "full_name",
          label: "Имя",
          sortable: true
          },
        {
          key: "title",
          label: "Группа",
          sortable: true
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        is_additional: true
      })
    },
    async getAllContracts() {
      const data = await axios.get("https://spring-db-course.herokuapp.com/additional/contracts")

      this.fields = [
        {
          key: "full_name",
          label: "Имя",
          sortable: true
          },
        {
          key: "title",
          label: "Группа",
          sortable: true
        }
      ]
      this.items = data.data
      this.$emit("onUpdateTable", {
        fields: this.fields,
        items: this.items,
        is_additional: true
      })
    },
  }
}

</script>

<style scoped>

</style>