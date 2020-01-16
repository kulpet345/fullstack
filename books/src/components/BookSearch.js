import {Book} from "./Book";
import React from "react";
import {connect} from 'react-redux';
import {BookDisplay} from "./BookDisplay";

class BookSearch extends React.Component {
    constructor(props) {
        super(props);
        this.state = {text: ""};
    }

    onChange = event => {
        this.setState({text: event.target.value});
    }

    render() {
        let foundBooks = this.props.books.filter(book => book.name.includes(this.state.text) || book.author.includes(this.state.text));

        return (<div className="search">
            <input placeholder="Введите текст для поиска" value={this.state.text} onChange={this.onChange}/>
            <BookDisplay books={foundBooks}/>
        </div>);
    }
}

const BookSearchContainer = connect(state => state)(BookSearch);

export {BookSearch, BookSearchContainer};