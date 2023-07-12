import FetchWrapper from "./FetchWrapper";
import PrincipalService from "./PrincipalService";

class AuthenticationService {
  login() {
    return new Promise((resolve, reject) => {
      FetchWrapper.getJsonP("app/login")
        .then(() => {
          PrincipalService.identify(true)
            .then((account) => {
              if (
                account !== undefined &&
                account.user.langKey !== undefined &&
                account.user.langKey !== null
              ) {
                console.log("setLang");
                // store.commit("setLang", account.user.langKey);
              }
              resolve(account);
            })
            .catch((err) => {
              reject(err);
            });
        })
        .catch((err) => {
          this.logout();
          PrincipalService.authenticate(null);
          reject(err);
        });
    });
  }

  logout() {
    return new Promise((resolve, reject) => {
      FetchWrapper.postJson("api/logout")
        .then((response) => {
          console.log("clearAll");
          // store.commit("clearAll");
          resolve(response);
        })
        .catch((err) => {
          reject(err);
        });
    });
  }

  authorize() {
    return PrincipalService.identify().then(() => {
      const isAuthenticated = PrincipalService.isAuthenticated();
      if (
        // store.getters.getNextRoute.meta.roles &&
        // store.getters.getNextRoute.meta.roles.length > 0 &&
        // !PrincipalService.isInAnyRole(store.getters.getNextRoute.meta.roles)
        true
      ) {
        if (isAuthenticated) {
          // user is signed in but not authorized for desired state
          console.log("AccessDenied");
          // router.push({ name: "AccessDenied" });
        } else {
          // user is not authenticated. stow the state they wanted before you
          // send them to the signin state, so you can return them when you're done
          console.log("setReturnRoute");
          // store.commit("setReturnRoute", store.getters.getNextRoute);

          // now, send them to the signin state so they can log in
          console.log("Login");
          // router.push({ name: "Login" });
        }
      }
    });
  }
}

export default new AuthenticationService();
