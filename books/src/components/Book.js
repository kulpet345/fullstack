import img from "../img/img2.jpg";
import React from "react";

export function Book(props) {
    return (
        <div className="book">
            <img src={props.img}/>
            <div><b>{props.name}</b></div>
            <div>{props.author}</div>
        </div>
    );
}