<template>

  <div class="content_area">

<div class="avatar-container">
      <img class="avatar" src="../assets/avatar.jpg" alt="Avatar">
    </div>
      <h2 v-if="isLoggedIn">User Logged In</h2>
         <h2 v-else>Login</h2>

        <div class="LoginForm">
         <form v-if="!isLoggedIn" @submit="submitForm">
            <label for="username">Username</label>
            <input type="text" id="username" v-model="username" required>

            <label for="password">Password</label>
            <input type="password" id="password" v-model="password" required>

            <button type="submit">Login</button>
         </form>
      </div>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
  data() {
    return {
     isLoggedIn: false,
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
            // Commit the setLoggedIn mutation to update the isLoggedIn status
            this.$store.commit('setLoggedIn', true);
            // Redirect to the desired page or perform any other action
            this.$router.push('/customers');
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
  },
};
</script>
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

.content_area {
    width: 100%;
    text-align: center;
}

.LoginForm{
    width: 100%;
    text-align: center;
    display:flex;
}
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

img.avatar {
  width: 20%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
</style>
