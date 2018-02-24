<template>
    <el-dialog title="Edit" v-model="dialogFormVisible" :close-on-click-modal="false" :show-close="true"  :before-close="handleClose" >
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
            <el-button :plain="true" type="danger" v-on:click="canclemodal">Cancel</el-button>
            <el-button :plain="true" @click="updateForm(form)">Save</el-button>
        </div>
    </el-dialog>
</template>


<script>
    import API from '../config/API.config'
    export default {
        data(){
            return {
                formLabelWidth: '120px',
            }
        },
        props: ['dialogFormVisible', 'form'],

        methods: {
            updateForm: function (formName) {
                let itemId = formName.id;
                let phone = formName.phone;
                let zone = formName.zone;
                this.$axios.put( API.apiBase +'/api/users/detail/' + itemId, {
                    phone: phone,
                    zone: zone
                })
                    .then(function (response) {
                        console.log(response);
                        this.form = response.data;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                location.reload();
            },
            canclemodal: function () {
                console.log('cancle modal triggered');
                this.$emit('canclemodal'); // 这里运行时会报错？
                // this.dialogFormVisible = false; // 这里是警告提示不建议这么用
            },
            handleClose(done) {
                done();                 
            },
        }

    }

</script>