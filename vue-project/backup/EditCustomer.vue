<template>
  <div v-if="customer">
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
export default {
  name: 'EditCustomer',
  props: {
    customer: {
      type: Object
    }
  },
  data() {
    return {
      editedCustomer: null // Initialize editedCustomer as null
    };
  },
  watch: {
      customer: {
        immediate: true, // Watch the customer prop immediately on component creation
        deep: true, // Watch for nested property changes in customer object
        handler(newValue) {
          // Update the editedCustomer when customer prop changes
          this.editedCustomer = { ...newValue };
        }
      }
    },
  methods: {
    handleSubmit(event) {
      event.preventDefault();

      // Call the API or perform any necessary actions to save the changes
      // You can use this.editedCustomer to access the updated customer data

      // Emit an event to notify the parent component (CustomerList.vue) about the edit completion
      this.$emit('customerEdited', this.editedCustomer);
    }
  }
};
</script>

<style>
/* your styles here */
</style>
