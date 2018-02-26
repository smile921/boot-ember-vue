<template>
  <div>
      <el-button type="primary" @click="dialogVisible = true">添 加</el-button>
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
  </div>
</template>
<script>
export default {
    name: 'db-add-user',
    data() {
        return {
            dialogVisible: false,
            newUser: {
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
                        },
                    },
                    {
                        text: '昨天',
                        onClick(picker) {
                            const date = new Date();
                            let time = 3600 * 1000;
                            time *= 24;
                            date.setTime(date.getTime() - time);
                            picker.$emit('pick', date);
                        },
                    },
                    {
                        text: '一周前',
                        onClick(picker) {
                            const date = new Date();
                            let time = 3600 * 1000;
                            time *= 24;
                            time *= 7;
                            date.setTime(date.getTime() - time);
                            picker.$emit('pick', date);
                        },
                    },
                ],
            },
        };
    },
    methods: {
        addItem() { 
            const formName = 'newUser';
            /* eslint-disable no-unused-vars */
            const form = this.$refs[formName];
            // console.log(this.newUser);
            // let itemId =  this.newUser.id;
            this.$axios.post(`${API.apiBase}/api/users/detail/${this.newUser}`)
                .then(function handleError(response) {
                    console.log(response);
                    this.form = response.data;
                })
                .catch(error => console.log(error));
            location.reload();
        },        
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => done())
                .catch(_ => {});
        },
    },
}
</script>

