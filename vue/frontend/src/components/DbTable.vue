<template>
    <div>
        <db-add-user></db-add-user>       
        <el-table
                :data="tableData"
                border
                style="width: 100%"
                class="table">
            <el-table-column
                    fixed
                    prop="id"
                    label="item_id"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="username"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="email"
                    label="email"
                    width="240">
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="phone"
                    width="130">
            </el-table-column>
            <el-table-column
                    prop="sex"
                    label="sex"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="zone"
                    label="zone"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="create_datetime"
                    label="create_datetime"
                    width="300"
                    :formatter="formatter">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="Operation"
                    width="110">
                <template slot-scope="scope">
                    <el-button @click="editItem(scope.$index, tableData)" type="text"
                    size="large">Edit</el-button>
                </template>
            </el-table-column>

        </el-table>
        <el-pagination class="pagination" layout="prev, pager, next" :total="total"
        :page-size="pageSize" v-on:current-change="changePage">
        </el-pagination>
        <db-modal
            :dialogFormVisible="dialogFormVisible"
            :form="form"
            v-on:canclemodal="dialogVisible">
        </db-modal>
    </div>

</template>

<script>
import Bus from '../eventBus';
/* eslint-disable-next-line */
import DbModal from './DbModal.vue';
import DbAddUser from './DbAddUser.vue';
import API from '../config/API.config';

export default {
    data() {
        return {
            tableData: [],
            apiUrl: `${API.apiBase}/api/users`,
            total: 0,
            pageSize: 10,
            currentPage: 1,
            sex: '',
            email: '',
            dialogFormVisible: false,            
            form: {},
        };
    },
    components: {
        DbModal,
        DbAddUser,
    },
    mounted() {
        this.getCustomers();
        Bus.$on('filterResultData', (data) => {
            this.tableData = data.results;
            this.total = data.total_pages;
            this.pageSize = data.count;
            this.email = data.email;
            this.sex = data.sex;
        });
        console.log('调用了mounted钩子函数');
    },

    methods: {
        getCustomers() {
            this.$axios.get(this.apiUrl, {
                params: {
                    page: this.currentPage,
                    sex: this.sex,
                    email: this.email,
                },
            }).then((response) => {
                this.tableData = response.data.data.results;
                this.total = response.data.data.total;
                this.pageSize = response.data.data.count;
                console.log(response.data.data);
            }).catch(response => console.log(response));
        },
        changePage(currentPage) {
            this.currentPage = currentPage;
            this.getCustomers();
        },
        dialogVisible() {
            this.dialogFormVisible = false;
        },
        editItem(index, rows) {
            // debugger;
            this.dialogFormVisible = true;
            const itemId = rows[index].id;
            const idurl = `${API.apiBase}/api/users/detail/${itemId}`;
            /* eslint-disable no-return-assign */
            this.$axios.get(idurl).then(response => this.form = response.data)
                .catch(response => console.log(response));
        },
        formatter(row, column) {
            console.log(column);
            const data = this.$moment(row.create_datetime, this.$moment.ISO_8601);
            return data.format('YYYY-MM-DD');
        },
    },
    beforeCreate() {
        console.log('调用了beforeCreat钩子函数');
    },
    created() {
        console.log('调用了created钩子函数');
    },
    beforeMount() {
        console.log('调用了beforeMount钩子函数');
    },
    beforeUpdate() {
        console.log(`${this.dialogFormVisible}, table 调用了beforeUpdate钩子函数`);
    },
    updated() {
        console.log(`${this.dialogFormVisible}, table 调用了updated钩子函数`);
    },
};
</script>

<style>
    .table {
        margin-top: 30px;
    }

    .pagination {
        margin-top: 10px;
        float: right;
    }

</style>
