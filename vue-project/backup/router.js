import Vue from 'vue';
import VueRouter from 'vue-router';

import FormComponent from './components/FormPage.vue';
import UserList from './components/UserList.vue';

Vue.use(VueRouter);

const routes = [
  { path: '/form', component: FormComponent },
  { path: '/users', component: UserList }
];

const router = new VueRouter({
  routes
});

export default router;
