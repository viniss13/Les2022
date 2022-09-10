import React from 'react'
import CardService from '../../../service/Card/CardService';
import LocalStorageService from '../../../service/config/LocalStorageService';
import './test.css'

const cardService = new CardService();

const Cards = () => {

  const [cards, setCards] = React.useState(null);
  // const [error, setErrors] = React.useState(null);

  const getAllCards = () => {
    const userData = LocalStorageService.obterItem("_logged_user");
    const id = userData.entities[0].id;

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

  const deleteCard = (id) => {
    cardService.deleteCard(id)
      .then(() => {
        console.info('deletou card', id)
        getAllCards()
      })
      .catch((err) => {
        console.error('erro deletando id', id, err)
      })
  }

  return (
    <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
      {cards?.length === 0 && <h1>Sem cartões</h1>}
      {cards?.map((card) => (
        <div key={card.id} className="card m-3">
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
              onClick={e => deleteCard(card.id)}
            >
              Excluir
            </button>
          </div>
        </div>
      ))}
    </div>
  )
}

export default Cards