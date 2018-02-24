import Vue from 'vue';
import Router from 'vue-router';
import DbHome from '../route/DbHome.vue';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'DbHome',
            component: DbHome
        }
    ]
})