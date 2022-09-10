
import ApiService from "../api/ApiService"

class CardService extends ApiService {

  constructor() {
    super('/api/cards');
  }

  getAllcards(id) {
    return this.get(`/read?client_id=${id}`);
  }

  deleteCard(id) {
    return this.delete(`/delete?id=${id}`);
  }

}

export default CardService;