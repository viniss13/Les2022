
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ClientService from '../../service/Client/ClientService';
import LocalStorageService from '../../service/config/LocalStorageService';
import SelectMenu from '../SelectMenu';
import { errorMessage, successMessage } from '../toastr';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


const clientService = new ClientService();
// onChange={(e) => setEmail(e.target.value)}

const Signin = () => {

    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [gender, setGender] = useState('');
    const [birthDate, setBirthDate] = useState('');
    const [type, setType] = useState('');
    const [areaCode, setAreaCode] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [cpf, setCpf] = useState('');

    let qtdMsg;

    const doSignIn = () => {
        clientService.signIn({
            email,
            password,
            name,
            gender,
            birthDate,
            type,
            areaCode,
            phoneNumber,
            cpf
        })
            .then(response => {

                qtdMsg = response.data.msg.length;

                console.log("QUANTIDADES STRATEGY", qtdMsg);

                if (qtdMsg === 0) {

                    console.log(qtdMsg);

                    console.log("Salvou");

                    LocalStorageService.removerItem("_logged_user");
                    LocalStorageService.addItem('_logged_user', response.data);

                    navigate('/user_home');
                } else {
                    let messages = response.data.msg;
                    console.log("messages", messages);
                    for (let i = 0; i < messages.length; i++) {
                        let msgs = messages[i].split("\n");
                        for (let message in msgs) {
                            toast.error(msgs[message]);
                        }
                    }
                }
            }).catch(error => {
                let messages = error.response.data.msg;
                console.log("messages", messages);
                for (let i = 0; i < messages.length; i++) {
                    toast.error(messages[i]);
                }
                //  toast.error(clientService.alertMessage(error.response.data));
            })
    }

    const Msg = ({ closeToast, toastProps }) => (
        <div>
            Lorem ipsum dolor {toastProps.position}
            <button>Retry</button>
            <button onClick={closeToast}>Close</button>
        </div>
    )
    return (
        <>


            <section className="container bg-secondary px-5 py-5 mb-5 my-5 font-weight-bold text-white">

                <div className="container">
                    <ToastContainer />

                </div>

                <div className="my-5 text-center">
                    <h1 className="display-4 font-weight-bold text-white">Cadastre-se Agora</h1>
                </div>
                <section className="row justify-content-center align-items-center ">

                    <form id="formCadastro" method="POST">
                        <div className="row">

                            <div className="form-group col-12">
                                <label htmlFor="inputNome">Nome</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="inputNome"
                                    placeholder="Insira seu nome"
                                    name="name"
                                    value={name}
                                    onChange={(e) => setName(e.target.value)} />
                            </div>
                            <div className="form-group col-md-6">
                                <label htmlFor="inputEmail">E-mail</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    id="inputEmail"
                                    name="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)} />
                            </div>

                            <div className="form-group col-md-6">
                                <label htmlFor="inputSenha">Senha</label>
                                <input
                                    type="password"
                                    className="form-control"
                                    id="inputSenha"
                                    name="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)} />
                            </div>

                            {/* <div className="form-group col-md-6">
                            <label htmlFor="inputConfirmSenha">Confirmar senha</label>
                            <input
                                type="password"
                                autoComplete="off"
                                className="form-control"
                                id="inputConfirmSenha"
                                name="confirmPassword"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)} /> 
                                value={this.state.confirmPassword}
                                onChange={this.handleChange}
                            />
                        </div> */}

                        </div>

                        <div className="row">
                            <div className="form-group col-md-6">
                                <label htmlFor="gender">Gênero</label>
                                <SelectMenu id="inputGenero"
                                    lista={clientService.getGender()}
                                    className="form-control"
                                    name="gender"
                                    value={gender}
                                    onChange={(e) => setGender(e.target.value)} />
                            </div>

                            <div className="form-group col-md-6">
                                <label htmlFor="inputDataNasc">Data de Nascimento</label>
                                <input
                                    type="date"
                                    id="inputDataNasc"
                                    className="form-control"
                                    name="birthDate"
                                    value={birthDate}
                                    onChange={(e) => setBirthDate(e.target.value)} />
                            </div>
                        </div>

                        <div className="form-group col-md-3">
                            <label htmlFor="inputTipoTelefone">Tipo Telefone</label>
                            <SelectMenu id="inputType"
                                lista={clientService.getType()}
                                className="form-control"
                                name="type"
                                value={type}
                                onChange={(e) => setType(e.target.value)} />
                        </div>

                        <div className="form-group col-3">
                            <label htmlFor="areaCode">DDD</label>
                            <input
                                type="text"
                                className="form-control col-md-4"
                                id="areaCode"
                                maxLength="3"
                                placeholder="Insira o DDD"
                                name="areaCode"
                                value={areaCode}
                                onChange={(e) => setAreaCode(e.target.value)} />
                        </div>

                        <div className="form-group ">
                            <label htmlFor="inputTelefone">Telefone</label>
                            <input
                                type="text"
                                className="form-control"
                                id="inputTelefone"
                                placeholder="Insira o seu Telefone"
                                name="phoneNumber"
                                value={phoneNumber}
                                onChange={(e) => setPhoneNumber(e.target.value)} />
                        </div>

                        <div className="form-group">
                            <label htmlFor="inputCPF">CPF</label>
                            <input
                                type="text"
                                className="form-control "
                                id="inputCPF"
                                placeholder="Insira o seu CPF"
                                maxLength="11"
                                name="cpf"
                                value={cpf}
                                onChange={(e) => setCpf(e.target.value)} />
                        </div>
                        <button onClick={doSignIn} type="button" className="btn btn-primary mt-3">Salvar</button>
                        <button onClick={''} type="button" className="btn btn-danger mt-3">Cancelar</button>
                    </form>
                </section>
            </section>

        </>
    )
}

export default Signin;