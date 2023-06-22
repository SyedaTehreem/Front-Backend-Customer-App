<template>
  <div>
    <Navbar @navigateToAbout="loadAbout" @navigateToHome="loadHome"
     @navigateToCustomer="loadCustomer" @navigateToEditCustomer="loadEditCustomer"
     @navigateToAddCustomer="loadAddCustomer"
     @navigateToProduct="loadProduct"
     @navigateToAddProduct="loadAddProduct"
     @navigateToLogin="loadLogin"
      />
    
    <Home v-if="showHome" />
    <About v-if="showAbout" />
    <CustomerList v-if="showCustomer" />
    <EditCustomer v-if="showEditCustomer" />
    <AddCustomer v-if="showAddCustomer" />  
    <ProductList v-if="showProduct" /> 
    <EditProduct v-if="showEditProduct" />
    <AddProduct v-if="showAddProduct" />  
    <LoginForm v-if="showLogin" /> 

  </div>
</template>

<script>
import Navbar from '~/components/Navbar.vue';
import Home from '~/components/Home.vue';
import About from '~/components/About.vue';

import CustomerList from '~/components/CustomerList.vue';
import AddCustomer from '~/components/AddForm.vue';
import EditCustomer from '~/components/EditCustomer.vue';

import ProductList from '~/components/ProductList.vue';
import AddProduct from '~/components/AddProduct.vue';
import EditProduct from '~/components/EditProduct.vue';
import LoginForm from '~/components/LoginForm.vue';
//==================
export default {
  components: {
    Navbar,
    About, 
    Home,
    CustomerList,
    EditCustomer,
    AddCustomer,
    ProductList,
    EditProduct,
    AddProduct,
    LoginForm,
   
  },
  data() {
    return {
      showAbout: false, 
      showHome: true,
      showCustomer: false,
      showEditCustomer: false,
      showAddCustomer: false,
      showProduct: false,
      showAddProduct: false,
      showEditProduct: false,
      showLogin: false,
    };
  },
  methods: {
    loadAbout() {
      this.showAbout = true;
      this.showHome = false; // Ensure only one component is shown at a time
      this.showCustomer = false;
      this.showEdiCustomer = false;
      this.showAddCustomer = false;
      this.showProduct = false;
    this.showAddProduct = false;
    this.showEditProduct = false;
      this.showLogin = false;
    },
    loadHome() {  //alert('Home');
      this.showHome = true;
      this.showAbout = false; // Ensure only one component is shown at a time
      this.showCustomer = false;
      this.showEdiCustomer = false;
      this.showAddCustomer = false;
      this.showProduct = false;
    this.showAddProduct = false;
    this.showEditProduct = false;
      this.showLogin = false;
    },
    loadCustomer() {  //alert('Home');
      this.showHome = false;
      this.showAbout = false; // Ensure only one component is shown at a time
      this.showCustomer = true;
      this.showEdiCustomer = false;
      this.showAddCustomer = false;
      this.showProduct = false;
    this.showAddProduct = false;
    this.showEditProduct = false;
      this.showLogin = false;
    },
    loadEditCustomer() {
    this.showHome = false;
    this.showAbout = false;
    this.showCustomer = false;
    this.showEdiCustomer = true;
    this.showAddCustomer = false;
    this.showProduct = false;
    this.showAddProduct = false;
    this.showEditProduct = false;
    this.showLogin = false;
  },
  loadAddCustomer() {
    this.showHome = false;
    this.showAbout = false;
    this.showCustomer = false;
    this.showEdiCustomer = false;
    this.showAddCustomer = true;
    this.showProduct = false;
    this.showAddProduct = false;
    this.showEditProduct = false;
    this.showLogin = false;
  },
  loadProduct() {  //alert('Home');
      this.showHome = false;
      this.showAbout = false; // Ensure only one component is shown at a time
      this.showCustomer = false;
      this.showEdiCustomer = false;
      this.showAddCustomer = false;
      this.showProduct = true;
      this.showAddProduct = false;
      this.showEditProduct = false;
      this.showLogin = false;
    },
    loadAddProduct() {  //alert('Home');
      this.showHome = false;
      this.showAbout = false; // Ensure only one component is shown at a time
      this.showCustomer = false;
      this.showEdiCustomer = false;
      this.showAddCustomer = false;
      this.showProduct = false;
      this.showAddProduct = true;
      this.showEditProduct = false;
      this.showLogin = false;
    },
    loadEditProduct() {  //alert('Home');
      this.showHome = false;
      this.showAbout = false; // Ensure only one component is shown at a time
      this.showCustomer = false;
      this.showEdiCustomer = false;
      this.showAddCustomer = false;
      this.showProduct = false;
      this.showAddProduct = false;
      this.showEditProduct = true;
      this.showLogin = false;
    },
  loadLogin() {
    this.showHome = false;
    this.showAbout = false;
    this.showCustomer = false;
    this.showEdiCustomer = false;
    this.showAddCustomer = false;
    this.showProduct = false;
    this.showAddProduct = false;
    this.showEditProduct = false;
    this.showLogin = true;
  }
  }
};
</script>
