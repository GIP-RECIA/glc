import AccountService from "./AccountService";

class PrincipalService {
  identify(force) {
    return new Promise((resolve, reject) => {
      var identity = undefined; // store.getters.getIdentity;
      if (force === true) {
        console.log("force");
        // store.commit("setIdentity", undefined);
        identity = undefined;
      }

      // check and see if we have retrieved the identity data from the server.
      // if we have, reuse it by immediately resolving
      if (identity !== undefined && identity !== null) {
        resolve(identity);
      } else {
        // retrieve the identity data from the server, update the identity object, and then resolve.
        AccountService.account()
          .then((response) => {
            console.log(
              "setIdentity:",
              response.data,
              "setAuthenticated: true"
            );
            // store.commit("setIdentity", response.data);
            // store.commit("setAuthenticated", true);
            resolve(identity);
          })
          .catch(() => {
            console.log("setIdentity:", null, "setAuthenticated: true");
            // store.commit("setIdentity", null);
            // store.commit("setAuthenticated", false);
            reject(identity);
          });
      }
    });
  }

  authenticate(identity) {
    console.log("authenticate", identity);
    // store.commit("setIdentity", identity);
    // store.commit(
    //   "setAuthenticated",
    //   identity !== null && identity !== undefined
    // );
  }

  isInAnyRole(roles) {
    return true;
    // const identity = store.getters.getIdentity;
    // const authenticated = store.getters.getAuthenticated;
    // if (
    //   !authenticated ||
    //   identity === undefined ||
    //   identity === null ||
    //   !identity.roles
    // ) {
    //   return false;
    // }

    // return roles.some((role) => this.isInRole(role));
  }

  isInRole(role) {
    return true;
    // const identity = store.getters.getIdentity;
    // const authenticated = store.getters.getAuthenticated;
    // if (
    //   !authenticated ||
    //   identity === undefined ||
    //   identity === null ||
    //   !identity.roles
    // ) {
    //   return false;
    // }
    // return identity.roles.indexOf(role) !== -1;
  }

  isAuthenticated() {
    return false;
    // return store.getters.getAuthenticated;
  }

  isIdentityResolved() {
    return false; // typeof store.getters.getIdentity !== "undefined";
  }
}

export default new PrincipalService();
