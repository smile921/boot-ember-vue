<template>
    <el-dialog
        title="Edit"
        :visible.sync="dialogFormVisible"
        :close-on-click-modal="false"
        :show-close="true">
        <el-form :model="form">
            <el-form-item label="item_id" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.id" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="username" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.username" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item label="email" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.email" auto-complete="off"></el-input>
            </el-form-item>

            <el-form-item label="phone" :label-width="formLabelWidth">
                <el-input v-model="form.phone" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="sex" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.sex" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="zone" :label-width="formLabelWidth">
                <el-input v-model="form.zone" auto-complete="off"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button :plain="true" type="danger" @click="canclemodal">Cancel</el-button>             
            <el-button :plain="true" @click="updateForm(form)">Save</el-button>
        </div>
    </el-dialog>
</template>


<script>
import API from '../config/API.config';

export default {
    name: 'db-modal',
    data() {
        return {
            formLabelWidth: '120px',
        };
    },
    props: ['dialogFormVisible', 'form'],

    methods: {
        updateForm(formName) {
            const itemId = formName.id;
            const phone = formName.phone;
            const zone = formName.zone;
            this.$axios.put(`${API.apiBase}/api/users/detail/${itemId}`, {
                phone,
                zone,
            })
                .then(function callback(response) {
                    console.log(response);
                    this.form = response.data;
                })
                .catch(error => console.log(error));
            location.reload();
        },
        canclemodal() {
            console.log('cancle modal triggered');
            this.$emit('canclemodal'); // 这里运行时会报错？
            // this.dialogFormVisible = false; // 这里是警告提示不建议这么用
        },
        // handleClose(done) {
        //     done();
        // },
    },
    beforeUpdate() {
        console.log(`${this.dialogFormVisible}, modal 调用了beforeUpdate钩子函数`); 
    },
    updated() {
        console.log(`${this.dialogFormVisible}, modal 调用了updated钩子函数`);
    },
    mounted() {
        console.log(`${this.dialogFormVisible}, modal 调用了 mounted 钩子函数`);
    },
};

</script>
