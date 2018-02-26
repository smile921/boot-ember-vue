<template>
    <header class="head-nav"> 
        <!-- <div class="text-logo">{{msg}}</div> -->
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-menu-item index="1">处理中心</el-menu-item>
            <el-submenu index="2">
                <template slot="title">我的工作台</template>
                <el-menu-item index="2-1">选项1</el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
                <el-menu-item index="2-3">选项3</el-menu-item>
                <el-submenu index="2-4">
                <template slot="title">选项4</template>
                <el-menu-item index="2-4-1">选项1</el-menu-item>
                <el-menu-item index="2-4-2">选项2</el-menu-item>
                <el-menu-item index="2-4-3">选项3</el-menu-item>
                </el-submenu>
            </el-submenu>
            <el-menu-item index="3" disabled>消息中心</el-menu-item>
            <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
        </el-menu>
    </header>
</template>

<script>
export default {
    name: 'db-header',
    data() {
        return {
            msg: 'SpringBoot ',
            activeIndex: '1',
        };
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
    },
};
</script>

<style>
    .head-nav {
        background-color: rgb(253, 153, 153);
    }
    header {
        height: 70px;
        position: absolute;
        width: 100%;
        top: 0;
        left: 0;
        padding: 0 0;
        z-index: 1;
        box-sizing: border-box;
    }

    .text-logo {
        display: inline-block;
        vertical-align: middle;
        border-style: none;
        position: relative;
        top: 26px;
        right: -20px;
        font-size: 25px;
        color: white;

    }

    .meta {
        color: #7e95c5;
        width: 400px;
        display: block;
        margin: -5px 0 0 225px;
        font-weight: 700;
        font-size: 0.3rem;
    }
</style>
