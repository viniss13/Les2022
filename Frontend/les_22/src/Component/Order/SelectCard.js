import React from 'react'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import CardService from '../../service/Card/CardService';
import LocalStorageService from '../../service/config/LocalStorageService';
import OrderService from '../../service/Order/OrderService';
import { Link, Navigate, useNavigate } from 'react-router-dom';

const cardService = new CardService();
const orderService = new OrderService();

const SelectCard = () => {

  const [cards, setCards] = React.useState(null);
  const [client_id, setClientId] = React.useState(null);
  const navigate = useNavigate();

  // const [error, setErrors] = React.useState(null);

  const getAllCards = () => {
    const userData = LocalStorageService.obterItem("_logged_user");
    const id = userData.entities[0].id;
    setClientId(id);

    cardService.getAllcards(id)
      .then(response => {
        setCards(response.data.entities);

      }).catch(error => {
        console.error("erro recuperando cartões do usuário", id, error)
      })
  }

  React.useEffect(() => {
    getAllCards()
  }, [])

  const selectCard = (id) => {
    orderService.addCard({ client_id: client_id, card_id: id })
      .then(() => {

        navigate("/order_summary")
        toast.success("Selecionado com sucesso!");
      })
      .catch((err) => {
        console.error('erro deletando id', id, err);
      })
  }

  return (
    <>
      <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
        <ToastContainer />

        <div className="container">
          <Link
            className="btn btn-danger rainbow-bg mx-2" to={"/create_card"} >
            Cadastrar
          </Link>
        </div>
        {cards?.length === 0 && <h1>Sem cartões</h1>}
        {cards?.map((card) => (
          <div key={card.id} className="card m-3">
            {console.log('CARDS DENTRO DO MAP', cards)}
            <div className="card-body" >
              <ul className="list-group list-group-flush">
                <li className="list-group-item">Apelido: {card.alias}</li>
                <li className="list-group-item">Bandeira: {card.flag}</li>
                <li className="list-group-item">Número: {card.number}</li>
              </ul>
            </div>

            <div className="card-footer d-flex justify-content-center">
              <button
                type="button"
                className="btn btn-danger rainbow-bg"
                onClick={e => selectCard(card.id)}
              >
                Selecionar
              </button>

            </div>
          </div>
        ))}
      </div>
      <a href="/user_home" className="btn btn-outline-secondary mb-2 " >Voltar</a>

    </>
  )
}

export default SelectCard