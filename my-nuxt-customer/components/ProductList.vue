<template>

    <div v-if="!$store.state.isLoggedIn">
      <!-- Render the product list component here -->
      <span class="error">You must be logged in to access this page...</span>
      <LoginForm />
    </div>
    <div v-else>
      
        <h2>Product List</h2>
    
        <table>
          <tbody>
              <tr>
              <th>Name</th>
              <th>Descriptin</th>     
              <th>Price</th>
              <th>Actions</th>
          </tr>
            <tr v-for="product in products" :key="product.pid">
              <td>{{ product.name }}</td>
              <td>{{ product.description }}</td>
              <td>{{ product.price }}</td>
             
              <td>
            
                <button @click="confirmDelete(product.pid)">Delete</button>
              </td>
            </tr>
          </tbody>
        </table>
    
       
      </div>
    </template>
    
    <script>
    import axios from 'axios';
    import EditProduct from '~/components/EditProduct.vue';
    
    export default {
      components: {
        EditProduct,
      },
    
      data() {
        return {
          products: [], // Initialize an empty array to store the products
        };
      },
    
      methods: {
        fetchProducts() {
          // Fetch the list of products from the API
          axios
            .get('http://localhost:8081/api/products/get')
            .then(response => {
              this.products = response.data; // Assign the fetched products to the component's data property
            })
            .catch(error => {
              console.error(error);
              // Handle the error, show an error message, or perform appropriate error handling
            });
        },
    
        getEditProductURL(productId) {
          return `/edit-product/${productId}`;
        },
    
        setSelectedProduct() {
          const productId = this.$route.params.id;
          if (productId) {
            this.selectedProduct = this.products.find(
              product => product.pid === Number(productId)
            );
          } else {
            this.selectedProduct = null;
          }
        },
        confirmDelete(productId) {
        // Display a confirmation dialog before deleting the product
        if (window.confirm('Are you sure you want to delete this product?')) {
          this.deleteProduct(productId);
        }
      },
  
      deleteProduct(productId) {
        // Delete the customer with the specified productId
        axios
          .delete(`http://localhost:8081/api/products/delete/${productId}`)
          .then(response => {
            if (response.status === 200) {
                alert("Record deleted Successfully...");
              // Remove the deleted customer from the products array
              this.products = this.products.filter(product => product.pid !== productId);
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
          handler(newProductId, oldProductId) {
            if (newProductId !== oldProductId) {
              this.setSelectedProduct();
            }
          },
        },
      },
    
      mounted() {
        this.fetchProducts(); // Call the fetchProducts method when the component is mounted
        this.setSelectedProduct(); // Set the SelectedProduct based on the URL parameter
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
    