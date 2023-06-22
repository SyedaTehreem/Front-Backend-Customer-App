<template>
  <div v-if="editedCustomer">
    <h2>Edit Customer</h2>
    <form @submit="handleSubmit">
      <div>
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="editedCustomer.name" required>
      </div>

      <div>
        <label for="last_name">Last Name:</label>
        <input type="text" id="last_name" v-model="editedCustomer.last_name" required>
      </div>

      <div>
        <label for="city">City:</label>
        <input type="text" id="city" v-model="editedCustomer.city" required>
      </div>

      <div>
        <label for="country">Country:</label>
        <input type="text" id="country" v-model="editedCustomer.country" required>
      </div>

      <div>
        <label for="address">Address:</label>
        <input type="text" id="address" v-model="editedCustomer.address" required>
      </div>

      <div>
        <button type="submit">Save Changes</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
import store from '../store';

export default {
name: 'EditCustomer',
  props: {
    customer: {
      type: Object,
    },
  },
  data() {
    return {
      editedCustomer: {
        id: null,
        name: '',
        last_name: '',
        city: '',
        country: '',
        address: ''
      }
    }
  },


  mounted() {
    if (this.$route.params.id) {

      const customerId = Number(this.$route.params.id);
      axios.get(`http://localhost:8081/api/customers/edit/${customerId}`)
        .then(response => {
        //console.log(response.data);

          this.editedCustomer = response.data;
         // console.log(this.editedCustomer); // Check the editedCustomer object after assignment
        })
        .catch(error => {
          console.error(error);
          // Handle the error, show an error message, or perform appropriate error handling
        });
    }
  },


  methods: {


    handleSubmit(event) {
      event.preventDefault();
      // Form submission logic
       const customerId = this.editedCustomer.id;
          const updatedCustomerData = {
            name: this.editedCustomer.name,
            last_name: this.editedCustomer.last_name,
            city: this.editedCustomer.city,
            country: this.editedCustomer.country,
            address: this.editedCustomer.address
          };

            axios.put(`http://localhost:8081/api/customers/edit/${customerId}`, updatedCustomerData)
                .then(response => {
                  // Handle the successful response
                  console.log(response.data);
                  // Optionally, you can show a success message or perform any other action
                })
                .catch(error => {
                  // Handle the error
                  console.error(error);
                  // Optionally, you can show an error message or perform any other error handling
                });

    },
    logout() { //alert("fsgfd");
                     // Perform the logout logic here
                     // For example, you can clear the session storage, update the isLoggedIn status, etc.
                     // Then redirect the user to the login page
                      this.$store.commit('setLoggedIn', false);
                          this.$router.push('/login');
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
.form {
  max-width: 400px;
  margin: auto;
}

.form-group {
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  padding: 8px;
}

button {
  padding: 8px 16px;
  margin-top: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
}

.success-message {
  margin-top: 10px;
  color: green;
}

.form-container {
  max-width: 600px; /* Adjust the width as per your preference */
  margin: 0 auto; /* Center align the container */
  padding: 20px; /* Add padding for spacing */
}

.form-heading {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
}

.form-image {
  width: 200px;
  height: auto;
  margin-bottom: 20px;
}

.container {
max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh; /* Ensure the container takes up the full height of the viewport */
  box-sizing: border-box;
}
</style>

