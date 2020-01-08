import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import UserList from './UserList';
import UserEdit from './UserEdit';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={UserList}/>
          <Route path='/users/:id' component={UserEdit}/>
        </Switch>
      </Router>
    )
  }
}

export default App;
