import Vue from 'vue';
import Router from 'vue-router';
import HelloWorld from '@/components/HelloWorld';
import DbHome from '@/components/DbHome';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/helloworld',
            name: 'HelloWorld',
            component: HelloWorld,
        },
        {
            path: '/',
            name: 'DbHome',
            component: DbHome,
        },
    ],
});
