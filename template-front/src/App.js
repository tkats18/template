import React from "react"
import MainPage from "./pages/MainPage"
import LoginPage from "./pages/Login"
import RegisterPage from "./pages/Register"
import LogoutPage from "./pages/LogoutPage"

import AppNav from "./components/Nav"
import "../src/assets/css/General.css"
import "../src/assets/css/Modal.css"


import { BrowserRouter as Router, Route,Switch } from 'react-router-dom';

function App() {
  
  return (
    <Router>
      <div className="App root" >
          <AppNav />
          <div>
            <Switch>
              <Route exact path={"/"} component={MainPage}/>
              <Route exact path={"/register"} component={RegisterPage}/>
              <Route exact path={"/login"} component={LoginPage}/>
              <Route exact path={"/logout"} component={LogoutPage}/>

            </Switch>
          </div>
      </div>
    </Router>
  );
}

export default App;
