<template>
    <div>
      <ul :class="{ 'navbar': true }">
        <li :class="{ 'active': activeLink === 'home' }"><a @click="navigate('#home', 'home')">Home</a></li>
        <li :class="{ 'active': activeLink === 'customerlist' }"><a @click="loadCustomer">Customers</a></li>
        <li :class="{ 'active': activeLink === 'contact' }"><a @click="navigate('#contact', 'contact')">Contact</a></li>
        <li :class="{ 'active': activeLink === 'about' }"><a @click="loadAbout">About</a></li>
        <li :class="{ 'active': activeLink === 'addcustomer' }"><a @click="loadAddCustomer">Add Customer</a></li>
        <li v-if="!$store.state.isLoggedIn" :class="{ 'active': activeLink === 'login' }"><a @click="loadLogin">Login</a></li>
        <li :class="{ 'active': activeLink === 'productlist' }"><a @click="loadProduct">Products</a></li>
        <li :class="{ 'active': activeLink === 'addproduct' }"><a @click="loadAddProduct">Add Product</a></li>
        
        
        <li v-if="$store.state.isLoggedIn"><a @click="loadLogout">Logout</a></li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        activeLink: 'home' // Set the Home link as active by default
      };
    },
    methods: {
      navigate(hash, link) {
        const element = document.querySelector(hash);
        if (element) {
          window.scrollTo({
            top: element.offsetTop,
            behavior: 'smooth'
          });
        }   
        this.activeLink = link; // Set the active link based on the clicked link
        if (link === 'home') {
          this.$emit('navigateToHome');
        }
      },
      loadAbout() {
        this.activeLink = 'about';
        this.$emit('navigateToAbout');
      },
      loadHome() {
        this.activeLink = 'home';
        this.$emit('navigateToHome');
      },
      loadCustomer() {
        this.activeLink = 'customerlist';
        this.$emit('navigateToCustomer');
      },
      loadAddCustomer() {
        this.activeLink = 'addcustomer';
        this.$emit('navigateToAddCustomer');
      },
      loadProduct() {
        this.activeLink = 'productlist';
        this.$emit('navigateToProduct');
      },
      loadAddProduct() {
        this.activeLink = 'addproduct';
        this.$emit('navigateToAddProduct');
      },
      loadLogin() {
        this.activeLink = 'login';
        this.$emit('navigateToLogin');
      }
      ,
      loadLogout() {
        this.activeLink = 'login';
        this.$store.commit('setLoggedIn', false);
        this.$emit('navigateToLogin');
      }
    }
  };
  </script>
    
  <style>
  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
  }
  
  li {
    float: left;
  }
  
  li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    cursor: pointer;
  }
  
  li a:hover:not(.active) {
    background-color: #04AA6D;
  }
  
  .active {
    background-color: #04AA6D;
  }
  </style>
  