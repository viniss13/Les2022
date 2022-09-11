import React from 'react'
import { useNavigate } from 'react-router-dom';
import CardService from '../../../service/Card/CardService';
import ClientService from '../../../service/Client/ClientService';
import LocalStorageService from '../../../service/config/LocalStorageService';
import SelectMenu from '../../SelectMenu';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const cardService = new CardService();

const CardCreate = () => {

    const navigate = useNavigate();

    const [number, setNumber] = React.useState('');
    const [holder, setHolder] = React.useState('');
    const [expirationDateMonth, setExpirationDateMonth] = React.useState('');
    const [expirationDateYear, setExpirationDateYear] = React.useState('');
    const [security, setSecurity] = React.useState('');
    const [holderCpf, setHolderCpf] = React.useState('');
    const [preferencial, setPreferencial] = React.useState(false);
    const [flag, setFlag] = React.useState('');
    const [client, setClient] = React.useState('');
    const [alias, setAlias] = React.useState('');

    let qtdMsg;

    React.useEffect(() => {

        const userData = LocalStorageService.obterItem("_logged_user");
        let dataUser = userData.entities[0];
        setClient(dataUser.id);

    }, [])

    const createCard = () => {

        cardService.createCard({
            number,
            holder,
            expirationDateMonth,
            expirationDateYear,
            security,
            holderCpf,
            preferencial,
            flag,
            client,
            alias
        })
            .then(response => {
                qtdMsg = response.data.msg.length;

                console.log("Quantidades strategy", qtdMsg);

                if (qtdMsg === 0) {
                    console.log(qtdMsg);

                    console.log("Criado");

                    navigate('/client_cards');
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
                    let msgs = messages[i].split("\n");
                    for (let message in msgs) {
                        toast.error(msgs[message]);
                    }
                }
            })
    }

    const month = cardService.getAddressMonth();
    const year = cardService.getAddressYear();
    const cardFlags = cardService.getCardFlag();



    return (
        <section className=" card px-5 py-5 mx-5 my-5" >
            <h1 className="mt-4 mb-3 text-center">Cadastrar cartão</h1>
            <ToastContainer />

            <div className="container">
                <div className="row">
                    <div className="col-md-12 personal-info">
                        <form className="form-horizontal">
                            <div className="form-group">

                                <div className="row">
                                    <div className="form-group col-6">
                                        <label className="col-6 constrol-label">Número do cartão</label>
                                        <input
                                            className="form-control border border-primary col-3"
                                            id="inputNumber"
                                            name="number"
                                            maxLength={16}
                                            value={number}
                                            onChange={(e) => setNumber(e.target.value)}>
                                        </input>
                                    </div>
                                    <div className="form-group col-6">
                                        <label className="constrol-label">Titular do cartão</label>
                                        <input
                                            className="form-control border border-primary"
                                            id="holder"
                                            name="holder"
                                            value={holder}
                                            onChange={(e) => setHolder(e.target.value)}>
                                        </input>
                                    </div>
                                </div>

                                <div className="row">
                                    <div className="form-group col-6">
                                        <label className="col-6 constrol-label">Mês de vencimento:</label>

                                        <SelectMenu
                                            className="form-control border border-primary col-3"
                                            id="expirationDateMonth"
                                            lista={month}
                                            name="expirationDateYear"
                                            value={expirationDateMonth}
                                            onChange={(e) => setExpirationDateMonth(e.target.value)}
                                        />
                                    </div>
                                    <div className="form-group col-6">

                                        <label className="col-6 constrol-label">Ano de vencimento:</label>
                                        <SelectMenu
                                            className="form-control border border-primary col-3"
                                            id="inputMonth"
                                            lista={year}
                                            name="expirationDateYear"
                                            value={expirationDateYear}
                                            onChange={(e) => setExpirationDateYear(e.target.value)}
                                        />
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="form-group col-6">
                                        <label className="constrol-label">CPF do titular: </label>
                                        <div className="col-12">
                                            <input
                                                className="form-control border border-primary col-3"
                                                id="holderCpf"
                                                name="holderCpf"
                                                value={holderCpf}
                                                onChange={(e) => setHolderCpf(e.target.value)}>
                                            </input>
                                        </div>
                                    </div>

                                    <div className="form-group col-3">
                                        <label className="constrol-label">CVC:</label>
                                        <div className="col-12">
                                            <input
                                                className="form-control border border-primary"
                                                id="security"
                                                name="security"
                                                value={security}
                                                onChange={(e) => setSecurity(e.target.value)}>
                                            </input>
                                        </div>
                                    </div>


                                </div>
                            </div>

                            <div className="row">
                                <div className="form-group col-6">
                                    <div className="form-group col-12">
                                        <label className="col-6 constrol-label">Bandeira:</label>
                                        <SelectMenu
                                            className="form-control border border-primary col-12"
                                            id="flag"
                                            lista={cardFlags}
                                            name="expiratiflagonDateYear"
                                            value={flag}
                                            onChange={(e) => setFlag(e.target.value)}
                                        />
                                    </div>
                                </div>

                                <div className="form-group col-6">
                                    <label className="constrol-label">Preferencial: </label>
                                    <div className="col-12">
                                        <input
                                            className="form-check-input border border-primary"
                                            type="checkBox"
                                            id="preferencial"
                                            name="preferencial"
                                            value={preferencial}
                                            onChange={(e) => setPreferencial(e.target.value)}>
                                        </input>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group col-12">
                                <label className="constrol-label">Apelido do cartão: </label>
                                <div className="col-12">
                                    <input
                                        className="form-control border border-primary col-3"
                                        id="alias"
                                        name="alias"
                                        value={alias}
                                        onChange={(e) => setAlias(e.target.value)}>
                                    </input>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <a href="/client_cards" className="btn btn-outline-secondary mt-4 mx-2" style={{ maxWidth: '140px' }}>Voltar</a>

                <button onClick={createCard} type="button" className="btn btn-primary mt-4">Salvar</button>
            </div>
        </section>
    )
}

export default CardCreate