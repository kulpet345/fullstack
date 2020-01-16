import axios from "axios";
import React from "react";
import {connect} from "react-redux";
import {Loading} from "./Loading";

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}


class _Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {username: "pok", password: "123456", loading: false, ok: true};
    }

    setUsername = (ev) => this.setState({username: ev.target.value});
    setPassword = (ev) => this.setState({password: ev.target.value});

    login = async () => {
        const data = new FormData();
        data.set("username", this.state.username);
        data.set("password", this.state.password);
        this.setState({loading: true});
        try {
            await sleep(1000);
            await axios.post("http://localhost:8080/login", data, {withCredentials: true});
            const resp = await axios.get("http://localhost:8080/all-books", {withCredentials: true});
            this.props.dispatch({type: "SET_BOOKS", books: resp.data});
            this.setState({ok: true, loading: false});
        } catch (e) {
            this.setState({loading: false, ok: false});
        }
    };

    render() {
        return (
            <div>
                Логин:
                <input name="username" value={this.state.username} onChange={this.setUsername}/>
                <br/>
                Пароль:
                <input name="password" type="password" value={this.state.password} onChange={this.setPassword}/>
                <br/>
                <button onClick={this.login}>Войти</button>
                {this.state.loading && <Loading/>}
                {!this.state.ok && "Не удалось"}
            </div>
        );
    }
}


const Login = connect()(_Login);

export {Login};