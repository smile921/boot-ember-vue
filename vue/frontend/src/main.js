/**
 * Created by gubaoer on 17/4/18.
 */

import Vue from 'vue'
import {
    Button,
    Select,
    Row,
    Col,
    Pagination,
    Table,
    TableColumn,
    Form,
    FormItem,
    DateTimePicker,
    DatePicker,
    TimePicker,
    Input,
    Dialog,
    Option
} from 'element-ui'
import router from './router';
import App from './App.vue'
import 'element-ui/lib/theme-chalk/index.css' 
// import 'element-ui/lib/theme-default/index.css'
import lang from 'element-ui/lib/locale/lang/en'
import locale from 'element-ui/lib/locale'

// more grace import third package !
import moment from 'moment'
import axios from 'axios'
import curvejs from 'curvejs'

Object.defineProperty(Vue.prototype, '$moment', { value: moment });
Object.defineProperty(Vue.prototype, '$axios', { value: axios });
Object.defineProperty(Vue.prototype, '$curvejs', { value: curvejs });

Vue.use(Button);
Vue.use(Select);
Vue.use(Row);
Vue.use(Col);
Vue.use(Pagination);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Form);
Vue.use(DatePicker);
Vue.use(TimePicker);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Dialog);
Vue.use(Option);

locale.use(lang);



// eslint-disable-next-line no-new
new Vue({
    el: '#app',
    router,
    render: h => h(App)
});

