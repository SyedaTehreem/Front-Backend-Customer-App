import { createApp } from 'vue'
import App from './App.vue'
import { createRouter, createWebHistory } from 'vue-router';

import FormComponent from './components/AddCustomer.vue';
import CustomerList from './components/CustomerList.vue';
import EditCustomer from './components/EditCustomer.vue';
const routes = [
  { path: '/add', component: FormComponent },
  { path: '/customers', component: CustomerList },
  { path: '/edit/:id', component: EditCustomer, name: 'edit-customer', props: true }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

const app = createApp(App);
app.use(router);

app.mount('#app');
