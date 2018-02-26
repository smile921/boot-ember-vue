<template>
  <div id="db-home">
        <db-filterinput></db-filterinput>
        <db-table></db-table>
  </div>
</template>
<script>
/* eslint-disable-next-line */
import DbFilterinput from '../components/DbFilterinput.vue';
/* eslint-disable-next-line */
import DbTable from '../components/DbTable.vue';

export default {
    name: 'db-home',
    data() {
        return {

        };
    },
    components: {
        DbFilterinput,
        DbTable,
    },
};
</script>
