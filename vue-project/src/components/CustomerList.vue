<template>
  <div>
    <h2>Customer List</h2>

    <table v-if="isLoggedIn">
      <!-- Table body content -->
      <tbody>
        <tr v-for="customer in customers" :key="customer.id">
          <!-- Customer data columns -->
          <td>{{ customer.name }}</td>
          <td>{{ customer.last_name }}</td>
          <td>{{ customer.city }}</td>
          <td>{{ customer.country }}</td>
          <td>{{ customer.address }}</td>
          <td>
            <!-- Edit and Delete links/buttons -->
            <router-link :to="{ name: 'edit-customer', params: { id: customer.id } }">Edit</router-link> |
            <button @click="confirmDelete(customer.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    <!-- Render EditCustomer component if a customer is selected for editing -->
    <EditCustomer v-if="selectedCustomer" :customer="selectedCustomer" @customerEdited="handleCustomerEdited" />
  </div>
</template>

<script>
import axios from 'axios';
import EditCustomer from './EditCustomer.vue';
import store from '../store';

export default {
  components: {
    EditCustomer,
  },
    computed: {
      isLoggedIn() {
        return this.$store.state.isLoggedIn;
      },
    },
  data() {
    return {
      customers: [], // Initialize an empty array to store the customers
      selectedCustomer: null,
    };
  },
  methods: {
    fetchCustomers() {
      // Fetch the list of customers from the API
      axios
        .get('http://localhost:8081/api/customers/getAll')
        .then(response => {
          this.customers = response.data; // Assign the fetched customers to the component's data property
          console.log(this.customers); // Verify if the customers are being fetched correctly

          // Check if the URL contains a customer ID and set the selectedCustomer accordingly
          const customerId = this.$route.params.id;
          if (customerId) {
            this.selectedCustomer = this.customers.find(customer => customer.id === Number(customerId));
            console.log(this.selectedCustomer); // Check the selectedCustomer object after assignment
          }
        })
        .catch(error => {
          console.error(error);
          // Handle the error, show an error message, or perform appropriate error handling
        });
    },

    handleCustomerEdited(editedCustomer) {
      // Update the edited customer in the customers array
      const index = this.customers.findIndex(customer => customer.id === editedCustomer.id);
      if (index !== -1) {
        this.customers.splice(index, 1, editedCustomer);
      }
      this.selectedCustomer = null;
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
            // Remove the deleted customer from the customers array
            this.customers = this.customers.filter(customer => customer.id !== customerId);
          }
        })
        .catch(error => {
          console.error(error);
        });
    },
    logout() { //alert("fsgfd");
        // Perform the logout logic here
        // For example, you can clear the session storage, update the isLoggedIn status, etc.
        // Then redirect the user to the login page
         this.$store.commit('setLoggedIn', false);
             this.$router.push('/login');
      },
  },
  mounted() {
    this.fetchCustomers(); // Call the fetchCustomers method when the component is mounted

    // Check if the URL contains a customer ID and set the selectedCustomer accordingly
    const customerId = this.$route.params.id;
    if (customerId) {
      this.selectedCustomer = this.customers.find(customer => customer.id === Number(customerId));
    }
  },

  beforeRouteEnter(to, from, next) {
    // Navigation guard to check if the user is logged in
    const isLoggedIn = store.state.isLoggedIn; // Access the store directly

    if (!isLoggedIn) {
      // If the user is not logged in, redirect to the login page
      next('/login');
    } else {
      // If the user is logged in, proceed to the component
      next();
    }
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
