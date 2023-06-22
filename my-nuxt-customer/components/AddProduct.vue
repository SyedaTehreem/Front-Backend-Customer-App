<template>
    
    <div v-if="!$store.state.isLoggedIn">
      <!-- Render the product list component here -->
      <span class="error">You must be logged in to access this page...</span>
      <LoginForm />
    </div>
    <div v-else>
  
        <div class="form-container">
       
      
          <form @submit="handleSubmit">
            <label for="name">Name:</label>
            <input type="text" id="name" v-model="name" required>
      
            <label for="description">Description:</label>
            <input type="text" id="description" v-model="description" required>
      
            <label for="price">Price:</label>
            <input type="text" id="price" v-model="price" required>
      
      
           <br> <button type="submit">Submit</button>
          </form>
          <!-- Success message ===== -->
          <div v-if="showSuccessMessage">
            Product Added successfully!
      
          </div>
      
        </div>
        </div>
      </template>
      
      <script>
  
      export default {
        name: 'AddProduct', // Update the component name to be multi-word
      
        data() {
          return {
            name: '',
            description: '',
            price: '',
            showSuccessMessage: false,
              users: [] // Initialize the users array
          };
        },
       
        methods: {
      
         handleSubmit(event) {
           event.preventDefault();
           alert("Are you sure to submit info");
      
           const formData = {
             name: this.name,
             description: this.description,
             price: this.price
            
           };
      
           fetch('http://localhost:8081/api/products/submitForm', {
             method: 'POST',
             headers: {
               'Content-Type': 'application/json'
             },
             body: JSON.stringify(formData)
           })
             .then(response => {
               if (response.ok) {
                 this.name = '';
                 this.description = '';
                 this.price = '';
                
      
                 return fetch('http://localhost:8081/api/products/get');
               } else {
                 console.error('Form submission failed');
                 throw new Error('Form submission failed');
               }
             })
             .then(response => response.json())
             .then(data => {
               this.users = data; // Update the users data with the fetched data
               this.showSuccessMessage = true;
             })
             .catch(error => {
               console.error('Error occurred while submitting the form or fetching users:', error);
             });
         },
      
      
        },
      
        handleUserEdited(editedUser) {
        // Perform necessary actions to save the edited user data
        // Update the user data or make an API request, etc.
        // For example:
        this.users = this.users.map(user => {
          if (user.id === editedUser.id) {
            return editedUser;
          }
          return user;
        });
      },
        
      };
      </script>
      
      ///// Adding style for my Form Page
      
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
      