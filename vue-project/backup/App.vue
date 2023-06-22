<template>
  <div id="app">
    <h1>Customer Application</h1>
    <h2>My Customers</h2>
    <router-link to="/">Home</router-link>
    <router-link to="/add">Submit Form</router-link>
    <router-link to="/customers">Customer List</router-link>
    <router-view></router-view>
  </div>
</template>


<script>

export default {
  name: 'App'
};

</script>
