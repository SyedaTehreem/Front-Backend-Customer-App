<template>

  <div v-if="!$store.state.isLoggedIn">
    <!-- Render the customer list component here -->
    <span class="error">You must be logged in to access this page...</span>
    <LoginForm />
  </div>
  <div v-else>
    
      <h2>Customer List</h2>
  
      <table>
        <tbody>
            <tr>
            <th>Name</th>
            <th>Last Name</th>     
            <th>City</th>
            <th>Country</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
          <tr v-for="customer in customers" :key="customer.id">
            <td>{{ customer.name }}</td>
            <td>{{ customer.last_name }}</td>
            <td>{{ customer.city }}</td>
            <td>{{ customer.country }}</td>
            <td>{{ customer.address }}</td>
            <td>
              <a :href="getEditCustomerURL(customer.id)">Edit</a>
              <button @click="confirmDelete(customer.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
  
     
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import EditCustomer from '~/components/EditCustomer.vue';
  
  export default {
    components: {
      EditCustomer,
    },
  
    data() {
      return {
        customers: [], // Initialize an empty array to store the customers
      };
    },
  
    methods: {
      fetchCustomers() {
        // Fetch the list of customers from the API
        axios
          .get('http://localhost:8081/api/customers/getAll')
          .then(response => {
            this.customers = response.data; // Assign the fetched customers to the component's data property
          })
          .catch(error => {
            console.error(error);
            // Handle the error, show an error message, or perform appropriate error handling
          });
      },
  
      getEditCustomerURL(customerId) {
        return `/edit-customer/${customerId}`;
      },
  
      setSelectedCustomer() {
        const customerId = this.$route.params.id;
        if (customerId) {
          this.selectedCustomer = this.customers.find(
            customer => customer.id === Number(customerId)
          );
        } else {
          this.selectedCustomer = null;
        }
      },
      confirmDelete(customerId) {
      // Display a confirmation dialog before deleting the customer
      if (window.confirm('Are you sure you want to delete this customer?')) {
        this.deleteCustomer(customerId);
      }
    },

    deleteCustomer(customerId) {
      // Delete the customer with the specified customerId
      axios
        .delete(`http://localhost:8081/api/customers/delete/${customerId}`)
        .then(response => {
          if (response.status === 200) {
              alert("Record deleted Successfully...");
            // Remove the deleted customer from the customers array
            this.customers = this.customers.filter(customer => customer.id !== customerId);
          }
        })
        .catch(error => {
          console.error(error);
        });
        }
    },
  
    watch: {
      '$route.params.id': {
        immediate: true,
        handler(newCustomerId, oldCustomerId) {
          if (newCustomerId !== oldCustomerId) {
            this.setSelectedCustomer();
          }
        },
      },
    },
  
    mounted() {
      this.fetchCustomers(); // Call the fetchCustomers method when the component is mounted
      this.setSelectedCustomer(); // Set the selectedCustomer based on the URL parameter
    },
  };
  </script>
  
  <style>
  /* CSS styles for the table */
  table {
    width: 100%;
    border-collapse: collapse;
  }
  
  th,
  td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ccc;
  }
  
  th {
    background-color: #f2f2f2;
  }
  </style>
  