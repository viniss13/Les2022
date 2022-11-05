
import ApiService from "../api/ApiService"

class RequestCardService extends ApiService {

  constructor() {
    super('/api/request_cards');
  }

  addCard(request){
    return this.post('/create', request);
  }

  

}

export default RequestCardService;

