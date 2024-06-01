import React, { useContext } from "react";
import "./App.css";
import Login from "./pages/Login/Login";
import Race from "./pages/Race/Race";
import { AuthContext } from "./context/AuthContext";
import { Route, Switch, Router, Redirect, useHistory } from "react-router-dom";
import { Main } from "./lib/style/generalStyles";
import ProtectedRoute from "./components/ProtectedRoute/ProtectedRoute";

function App() {
  const { setIsLoggedIn, setIsAdmin, isAdmin, isLoggedIn } =
    useContext(AuthContext);
  const history = useHistory();

  return (
    <Main>
      <Router history={history}>
        <Switch>
          {isLoggedIn ? (
            <>
              {isAdmin && (
                <>
                  <ProtectedRoute exact path="/" component={Race} />
                </>
              )}
              {!isAdmin && (
                <>
                  <ProtectedRoute
                    exact
                    path="/avaliable-races"
                    component={Race}
                  />
                </>
              )}
            </>
          ) : (
            <>
              <Route path="/login" component={Login} />
              <Redirect from="*" to="/login" />
            </>
          )}
        </Switch>
      </Router>
    </Main>
  );
}

export default App;
