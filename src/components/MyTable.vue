<template>
<div>
      <b-table class="my-table"
      :bordered="bordered"
      :dark="dark"
      :items="items"
      :fields="fields"
      :head-variant="headVariant"
      >

       <template #cell(edited)="row">
         <b-button-group class="table-b-group">
            <b-button size="sm" class="mr-1" v-b-modal.my-modal @click="editItem(row.item.id)">
              Изменить
            </b-button>
            <b-button size="sm" class="mr-1" @click="deleteItem(row.item.id)">
              Удалить
            </b-button>
         </b-button-group>
      </template>

    </b-table>
    <component :is="form_component" @onAddData="reload"/>
    <component :is="edit_form_component" :edit_id="this.edit_id"/>

</div>
</template>

<script>

export default {
  props: {
    fields: {
      type: Array,
      required: true
    },
    items: {
      type: Array,
      required: true
    },
    form_component: {
      required: true
    },
    edit_form_component: {
      required: true
    }
  },
  data() {
    return {
      dark: false,
      bordered: true,
      headVariant: 'dark',
      edit_id: null,
      show: false
    }
  },
  methods: {
      async reload() {
        this.$forceUpdate()
      },
      editItem(edit_id) {
        this.edit_id = edit_id
      },
      deleteItem(delete_id) {
        this.$emit("onDeleteItem", {
        id: delete_id,
        table: this.form_component.name
        })
      }
  }
}
</script>


<style scoped>
.my-table {
    margin-top: 10px !important;
    margin-bottom: 15 px !important;
}
</style>