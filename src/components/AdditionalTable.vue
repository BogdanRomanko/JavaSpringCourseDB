<template>
<div>
      <b-table class="my-table"
      :bordered="bordered"
      :dark="dark"
      :items="items"
      :fields="fields"
      :head-variant="headVariant"
      >

    </b-table>
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
  },
  data() {
    return {
      dark: false,
      bordered: true,
      headVariant: 'dark',
    }
  },
}
</script>

<style scoped>
.my-table {
    margin-top: 10px !important;
    margin-bottom: 15 px !important;
}
</style>