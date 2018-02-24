// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

/* eslint-disable */
import Vue from 'vue';
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
} from 'element-ui';

import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale';
import lang from 'element-ui/lib/locale/lang/en';

// import more grace third package
import moment from 'moment';
import axios from 'axios';
import curvejs from 'curvejs';


import App from './App';
import router from './router';

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

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
});
/* eslint-disable */