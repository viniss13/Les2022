
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ClientService from '../../service/Client/ClientService';
import LocalStorageService from '../../service/config/LocalStorageService';
import SelectMenu from '../SelectMenu';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const clientService = new ClientService();

const ClientUpdate = () => {

    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [gender, setGender] = useState('');
    const [birthDate, setBirthDate] = useState('');
    const [phoneType, setPhoneType] = useState('');
    const [areaCode, setAreaCode] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [cpf, setCpf] = useState('');
    const [client, setClient] = useState('');

    let qtdMsg;
    const [data, setData] = React.useState('');

    const getData = useEffect(() => {

        const userData = LocalStorageService.obterItem("_logged_user");
        let dataUser = userData.entities[0];

        console.log("DATA USER: ", dataUser);

        setClient(dataUser.id);
        setEmail(dataUser.email);
        setPhoneNumber(dataUser.phoneNumber);
        setCpf(dataUser.cpf);
        setData(dataUser);
        setName(dataUser.name);
        setGender(dataUser.gender);
        console.log(dataUser.birthDate);


        const year = dataUser.birthDate[0];
        let month = dataUser.birthDate[1];
        let day = dataUser.birthDate[2];

        if (month < 10) month = "0" + month;
        if (day < 10) day = "0" + day;

        setBirthDate(`${year}-${month}-${day}`);
        setPhoneType(dataUser.phoneType);
        setAreaCode(dataUser.areaCode)

        console.log("USERDATA", userData.entities[0]);

    }, [])


    const doUpdate = () => {
        clientService.update({
            client,
            email,
            name,
            gender,
            birthDate,
            phoneType,
            areaCode,
            phoneNumber,
            cpf
        })
            .then(response => {

                qtdMsg = response.data.msg.length;

                console.log("QUANTIDADES STRATEGY", qtdMsg);

                if (qtdMsg === 0) {

                    console.log(qtdMsg);

                    console.log("Editado");
                    // successMessage('Cadastro realizado com sucesso!');

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
                console.log("Logando", error);
                for (let i = 0; i < messages.length; i++) {
                    let msgs = messages[i].split("\n");
                    for (let message in msgs) {
                        toast.error(msgs[message]);
                    }
                }
            })

    }

    return (
        <>
            <section className="container bg-secondary px-5 py-5 mb-5 my-5 font-weight-bold text-white">
                <ToastContainer />

                <div className="my-5 text-center">
                    <h1 className="display-4 font-weight-bold text-white">Editar</h1>
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
                                value={phoneType}
                                onChange={(e) => setPhoneType(e.target.value)} />
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
                        <button onClick={doUpdate} type="button" className="btn btn-primary mt-3">Salvar</button>
                        <a href="/client_profile" type="button" className="btn btn-danger mt-3">Cancelar</a>
                    </form>
                </section>
            </section>
        </>
    )
}

export default ClientUpdate