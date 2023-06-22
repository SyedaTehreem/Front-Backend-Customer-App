<template>
    <div>
      <div v-if="$store.state.isLoggedIn">
        <!-- Render the customer list component here -->
        <CustomerList />
      </div>
      <div v-else>
        <!-- Render the login form here -->
        <div class="content_area">
          <div class="avatar-container"></div>
          <h2>Login</h2>
          <div class="LoginForm">
            <form @submit="submitForm">
              <label for="username">Username</label>
              <input type="text" id="username" v-model="username" required>
              <label for="password">Password</label>
              <input type="password" id="password" v-model="password" required>
              <button type="submit">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import CustomerList from '~/components/CustomerList.vue';
  
  export default {
    name: 'LoginForm',
    data() {
      return {
        username: '',
        password: '',
      };
    },
    methods: {
      submitForm(event) {
        event.preventDefault();
        // Perform login logic here
        const formData = {
          user_name: this.username,
          password: this.password,
        };
  
        // Make an API call to your login endpoint
        fetch('http://localhost:8081/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(formData),
        })
          .then(response => {
            if (response.ok) {
              // Login successful
              console.log('Login successful');
              // Set the isLoggedIn status in the Vuex store
              this.$store.commit('setLoggedIn', true);
            } else {
              // Login failed
              console.log('Login failed');
              // Handle login failure, display error message, etc.
            }
          })
          .catch(error => {
            console.error('An error occurred during login:', error);
            // Handle error, display error message, etc.
          });
      },
      logout() {
        // Call the setLoggedIn mutation with false to logout
        this.$store.commit('setLoggedIn', false);
        // Perform any other necessary logout actions
        // For example, clear user data, redirect to login page, etc.
        },
        
    },
    components: {
      CustomerList,
    },
  };
  </script>
  
  <style>
  /* Styles for the form and other elements */
  </style>
  