<template>
    <div>
        <el-button type="text" @click="dialogVisible = true">添 加</el-button>

        <el-dialog
            title="新增用户"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
            
            <el-form :inline="true" :model="newUser" class="demo-form-inline">
                <el-form-item label="id">
                    <el-input v-model="newUser.id" placeholder="123"></el-input>
                </el-form-item>
                <el-form-item label="username">
                    <el-input v-model="newUser.username" placeholder="张三"></el-input>
                </el-form-item>
                <el-form-item label="email">
                    <el-input v-model="newUser.email" placeholder="zhangsan@gmail.com"></el-input>
                </el-form-item>
                <el-form-item label="phone">
                    <el-input v-model="newUser.phone" placeholder="18666888976"></el-input>
                </el-form-item>
                <el-form-item label="sex"> 
                    <el-select v-model="newUser.sex" placeholder="性别">
                        <el-option label="男" value="male"></el-option>
                        <el-option label="女" value="female"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="zone">
                    <el-input v-model="newUser.zone" placeholder="300600"></el-input>
                </el-form-item>
                <br>
                <el-form-item label="create date time"> 
                    <el-date-picker
                        v-model="newUser.create_datetime"
                        type="datetime"
                        placeholder="选择日期时间"
                        align="right"
                        size="large"
                        format="yyyy-MM-dd HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        :picker-options="pickerOptions">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="addItem">添 加</el-button>
                </el-form-item>
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>
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
                <template scope="scope">
                    <el-button @click="editItem(scope.$index, tableData)" type="text" size="large">Edit</el-button>
                </template>
            </el-table-column>
            
        </el-table>
        <el-pagination class="pagination" layout="prev, pager, next" :total="total" :page-size="pageSize"
                       v-on:current-change="changePage">
        </el-pagination>
        <db-modal :dialogFormVisible="dialogFormVisible" :form="form" v-on:canclemodal="dialogVisible"></db-modal>
    </div>

</template>

<script>
    import Bus from '../eventBus'
    import DbModal from './DbModal.vue'
    import API from '../config/API.config'

    export default {
        data(){
            return {
                tableData: [],
                apiUrl: API.apiBase + '/api/users',
                total: 0,
                pageSize: 10,
                currentPage: 1,
                sex: '',
                email: '',
                dialogFormVisible: false,
                form: '',
                dialogVisible: false,
                newUser:{
                    id: '',
                    username: '',
                    email: '',
                    phone: '',                    
                    sex: '',
                    zone: '',
                    create_datetime: '',
                },
                pickerOptions: {
                    shortcuts: [
                        {
                        text: '今天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, 
                    {
                        text: '昨天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, 
                    {
                        text: '一周前',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                },
            }
        },
        components: {
            DbModal
        },
        mounted () {
            this.getCustomers();
            Bus.$on('filterResultData', (data) => {
                this.tableData = data.results;
                this.total = data.total_pages;
                this.pageSize = data.count;
                this.email = data.email;
                this.sex = data.sex;

            });
        },

        methods: {
            getCustomers: function () {
                this.$axios.get(this.apiUrl, {
                    params: {
                        page: this.currentPage,
                        sex: this.sex,
                        email: this.email
                    }
                }).then((response) => {
                    this.tableData = response.data.data.results;
                    this.total = response.data.data.total;
                    this.pageSize = response.data.data.count;
                    console.log(response.data.data);
                }).catch(function (response) {
                    console.log(response)
                });
            },
            changePage: function (currentPage) {
                this.currentPage = currentPage;
                this.getCustomers()
            },
            editItem: function (index, rows) {
                this.dialogFormVisible = true;
                const itemId = rows[index].id;
                const idurl = API.apiBase + '/api/users/detail/' + itemId;
                this.$axios.get(idurl).then((response) => {
                    this.form = response.data;
                }).catch(function (response) {
                    console.log(response)
                });
            },
            addItem: function(){
                this.dialogVisible =  true;
                const formName = 'newUser';
                let form = this.$refs[formName];                 
                // console.log(this.newUser); 
                let itemId =  this.newUser.id; 
                this.$axios.post( API.apiBase +'/api/users/detail/' , this.newUser)
                    .then(function (response) {
                        console.log(response);
                        this.form = response.data;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                location.reload();
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
            },
            
            formatter(row, column) {
                let data = this.$moment(row.create_datetime, this.$moment.ISO_8601);
                return data.format('YYYY-MM-DD')
            },
        }
    }
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