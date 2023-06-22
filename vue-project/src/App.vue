<template>
  <div id="app">
    <h1>Customer Application</h1>
    <h2>My Customers</h2>
    <ul>

    <li><router-link to="/">Home</router-link> </li>
   <li v-if="isLoggedIn"><router-link to="/add">Submit Form</router-link></li>
    <li v-if="isLoggedIn"><router-link to="/customers">Customer List</router-link></li>
      <li v-if="!isLoggedIn">
                  <router-link to="/login">Login</router-link>
                </li>
                <li v-else>
                  <button @click="logout">Logout</button>
                </li>

        </ul>
<router-view></router-view>
  </div>
</template>


<script>

export default {
  name: 'App',
  computed: {
        isLoggedIn() {
          return this.$store.state.isLoggedIn;
        },
      },
  data() {

      return {
        //isLoggedIn: false,
      };
    },
    methods: {
      logout() {
        // Perform logout logic here
        // Set isLoggedIn to false and perform any other necessary actions
        //this.isLoggedIn = false;

        this.$store.commit('setLoggedIn', false);
        this.$router.push('/login');

      },
    },
};

</script>
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
}

li {
  float: left;
}

li a {
  display: block;
  padding: 8px;
  background-color: #dddddd;
}
</style>