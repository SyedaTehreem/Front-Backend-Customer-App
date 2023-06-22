export const state = () => ({
    isLoggedIn: false,
  });
  
  export const mutations = {
    setLoggedIn(state, value) {
      state.isLoggedIn = value;
    },
  };
  