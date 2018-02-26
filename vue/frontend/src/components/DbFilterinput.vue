<template>
    <el-form :inline="true" :model="formInline">

        <el-form-item label="Sex">
            <el-select v-model="formInline.sex" clearable placeholder="select sex"
                       v-on:visible-change="selectDemo">
                <el-option
                        v-for="item in type_options"
                        :key="item.id"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
        </el-form-item>

        <el-form-item v-if='formInline.sex' label="Description">
            <el-input v-model="formInline.email" placeholder="Please input suffix of email"></el-input>
        </el-form-item>

        <el-form-item v-else label="Description">
            <el-input v-model="formInline.email" disabled placeholder="Please input suffix of email"></el-input>
        </el-form-item>

    </el-form>
</template>

<script>
import lodash from 'lodash'; /* eslint-disable-line */
import Bus from '../eventBus';
import API from '../config/API.config';

export default {
    name: 'db-filterinput',
    data() {
        return {
            type_options: [],
            formInline: {
                sex: '',
                email: '',
            },
            formLabelWidth: '120px',
        };
    },

    watch: {
        'formInline.sex': 'filterResultData',
        'formInline.email': 'filterResultData',
    },

    methods: {
        selectDemo(params) {
            if (params) {
                this.$axios.get(`${API.apiBase}/api/users/sex`)
                    .then((response) => {
                        this.type_options = response.data;
                        console.log(response.data);
                    }).catch(response => console.log(response));
            }
        },
        filterResultData: _.debounce(
            function filterData() {
                this.$axios.get(`${API.apiBase}/api/users`, {
                    params: {
                        sex: this.formInline.sex,
                        email: this.formInline.email,
                    },
                }).then((response) => {
                    /* eslint-dsiable-next-line no-param-reassign */
                    response.data.sex = this.formInline.sex; /* eslint-disable-line */
                    /* eslint-dsiable-next-line no-param-reassign */
                    response.data.email = this.formInline.email; /* eslint-disable-line */
                    Bus.$emit('filterResultData', response.data);
                    console.log(response.data);
                }).catch(response => console.log(response));
            },
            500,
        ),
    },
};
</script>
