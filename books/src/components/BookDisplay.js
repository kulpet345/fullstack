import {Book} from "./Book";
import React from "react";
import {connect} from 'react-redux';

function BookDisplay(props) {
    return (
        <div className="books">
            {props.books.map(book => <Book key={book.id} {...book}/>)}
        </div>
    )
}

const BookDisplayContainer = connect(state => state)(BookDisplay);

export {BookDisplay, BookDisplayContainer};