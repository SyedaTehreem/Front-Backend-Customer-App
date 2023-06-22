<template>
    
    <div v-if="!$store.state.isLoggedIn">
      <!-- Render the product list component here -->
      <span class="error">You must be logged in to access this page...</span>
      <LoginForm />
    </div>
    <div v-else>
  
        <h2>Edit Customer</h2>
        <form @submit="handleSubmit">
          <div>
            <label for="name">Name:</label>
            <input type="text" id="name" v-model="editedProduct.name" required>
          </div>
    
          <div>
            <label for="description">Description:</label>
            <input type="text" id="description" v-model="editedProduct.description" required>
          </div>
    
          <div>
            <label for="price">Price:</label>
            <input type="text" id="price" v-model="editedProduct.price" required>
          </div>
    
        
    
          <div>
            <button type="submit">Save Changes</button>
          </div>
        </form>
      </div>
    </template>
    
    <script>
      import axios from 'axios';
      
      export default {
        name: 'EditProduct',
        props: {
          productId: {
            type: String,
            required: true,
          },
        },
        data() {
          return {
            editedProduct: {
              name: '',
              description: '',
              price: '',
              
            },
          };
        },
        mounted() {
          this.fetchProductData();
        },
        methods: {
            fetchProductData() {
            axios
              .get(`http://localhost:8081/edit/${this.productId}`)
              .then(response => {
                const { data } = response;
                this.editedProduct = { ...data };
              })
              .catch(error => {
                console.error(error);
                // Handle the error, show an error message, or perform appropriate error handling
              });
          },
          handleSubmit() {
            // Implement your logic for saving the changes
            // You can access the edited customer data using this.editedCustomer object
          },
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
    
    