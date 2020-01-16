import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import {createStore} from 'redux';
import {Provider} from "react-redux";

import img from "./img/img2.jpg";
import {Navigation} from "./components/Navigation";
import {BookDisplayContainer} from "./components/BookDisplay";
import {Index} from "./views/Index";
import {BookSearchContainer} from "./components/BookSearch";
import {bookStore} from "./reducers";
import axios from "axios";
import {Login} from "./components/Login";


const books = [
    {img: img, id: 1, name: "Как делать фуллстек", author: "Пётр Кулагин"},
    {img: img, id: 2, name: "Ами", author: "Пётр Кулагин"},
    {img: img, id: 3, name: "Лёха", author: "Пётр Кулагин"},
    {img: img, id: 4, name: "Петян", author: "Пётр Кулагин"},
    {img: img, id: 5, name: "Максон", author: "Пётр Кулагин"},
];

let store = createStore(bookStore);

axios.get("http://localhost:8080/all-books").then(resp => store.dispatch({type: "SET_BOOKS", books: resp.data}));

// store.dispatch({type: "SET_BOOKS", books: books});

ReactDOM.render(
    <Provider store={store}>
        <Router>
            <Navigation/>
            <Switch>
                <Route path="/search">
                    <BookSearchContainer books={books}/>
                </Route>
                <Route path="/all">
                    <BookDisplayContainer books={books}/>
                </Route>
                <Route path="/login">
                    <Login/>
                </Route>
                <Route path="/" component={Index}/>
            </Switch>
        </Router>
    </Provider>,
    document.getElementById('root')
);
