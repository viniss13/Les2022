

import React from "react";
import AuthService from "../service/api/authService";
import NavBarItem from "./navbarItem";

const logOut = () => {
    AuthService.removeAuthenticateClient();
}

const isAuthenticateClient = () => {
    return AuthService.isAuthenticateClient();
}

function NavBar() {
    return (
        <div className="navbar navbar-expand-lg navbar-dark bg-secondary " >
            <div className="container ">
                <a href="#/home" className="navbar-brand">HarmoniCenter</a>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarResponsive"
                    aria-controls="navbarResponsive"
                    aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse justify-content-end" id="navbarResponsive">
                    <ul className="navbar-nav ">
                        <NavBarItem href="/login" label="Login" />                        
                        <NavBarItem onClick={logOut}  href="#/login" label="Sair" />
                                              
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default NavBar;