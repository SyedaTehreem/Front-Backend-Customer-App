<template>
    
  <div v-if="!$store.state.isLoggedIn">
    <!-- Render the customer list component here -->
    <span class="error">You must be logged in to access this page...</span>
    <LoginForm />
  </div>
  <div v-else>

      <div class="form-container">
     
    
        <form @submit="handleSubmit">
          <label for="name">Name:</label>
          <input type="text" id="name" v-model="name" required>
    
          <label for="last_name">Last Name:</label>
          <input type="text" id="last_name" v-model="last_name" required>
    
          <label for="city">City:</label>
          <input type="text" id="city" v-model="city" required>
    
          <label for="country">Country:</label>
          <input type="text" id="country" v-model="country" required>
    
          <label for="address">Address:</label>
          <input type="text" id="address" v-model="address" required>
    
         <br> <button type="submit">Submit</button>
        </form>
        <!-- Success message ===== -->
        <div v-if="showSuccessMessage">
          Form submitted successfully!
    
        </div>
    
      </div>
      </div>
    </template>
    
    <script>

    export default {
      name: 'AddCustomer', // Update the component name to be multi-word
    
      data() {
        return {
          name: '',
          last_name: '',
          city: '',
          country: '',
          address: '',
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
           last_name: this.last_name,
           city: this.city,
           country: this.country,
           address: this.address
         };
    
         fetch('http://localhost:8081/api/customers/submitForm', {
           method: 'POST',
           headers: {
             'Content-Type': 'application/json'
           },
           body: JSON.stringify(formData)
         })
           .then(response => {
             if (response.ok) {
               this.name = '';
               this.last_name = '';
               this.city = '';
               this.country = '';
               this.address = '';
    
               return fetch('http://localhost:8081/api/customers/getAll');
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
    