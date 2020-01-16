import {Link} from "react-router-dom";
import React from "react";

export function Navigation() {
    return (
        <header>
            <Link to={"/"}>Главная</Link>
            <Link to={"/search"}>Поиск книг</Link>
            <Link to={"/all"}>Все книги</Link>
            <Link to={"/top"}>Топ книг</Link>
        </header>
    );
}