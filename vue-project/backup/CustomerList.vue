<template>
  <div>
    <h2>Customer List</h2>
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Last Name</th>
          <th>City</th>
          <th>Country</th>
          <th>Address</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="customer in customers" :key="customer.id">

          <td>{{ customer.name }}</td>
          <td>{{ customer.last_name }}</td>
          <td>{{ customer.city }}</td>
          <td>{{ customer.country }}</td>
          <td>{{ customer.address }}</td>
          <td>

          <router-link :to="{ name: 'edit-customer', params: { id: customer.id } }">Edit</router-link> -
           <router-link :to="{ name: 'edit-customer', params: { id: customer.id }, props: { customer: customer } }">Edit</router-link>


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

export default {
  components: {
    EditCustomer
  },
  data() {
    return {
      customers: [], // Initialize an empty array to store the customers
      selectedCustomer: null
    };
  },
  methods: {
    fetchCustomers() {
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
    openEditModal(customer) {
        console.log(customer);
        this.selectedCustomer = { ...customer };
    },
    handleCustomerEdited(editedCustomer) {
      const index = this.customers.findIndex(customer => customer.id === editedCustomer.id);
      if (index !== -1) {
        this.customers.splice(index, 1, editedCustomer);
      }
      this.selectedCustomer = null;
    }
  },
  mounted() {
    this.fetchCustomers(); // Call the fetchCustomers method when the component is mounted

    // Check if the URL contains a customer ID and set the selectedCustomer accordingly
    const customerId = this.$route.params.id;
    if (customerId) {
      this.selectedCustomer = this.customers.find(customer => customer.id === Number(customerId));
    }
  }
};
</script>

<style>
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
