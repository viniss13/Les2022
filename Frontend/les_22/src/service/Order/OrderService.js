
import ApiService from "../api/ApiService"

class OrderService extends ApiService {

  constructor() {
    super('/api/orders');
  }

  addAddress(request){
    return this.put('/add_address', request);
  }

  addCard(request){
    return this.put('/add_card', request);
  }

  read_draft(client_id){
    return this.get(`/read_draft?client_id=${client_id}`);
  }

}

export default OrderService;

