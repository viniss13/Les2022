import ApiService from "../api/ApiService"

class ExchangeService extends ApiService {

  constructor() {
    super('/api/exchange');
  }

  createExchange(request){
    return this.post("/create", request);
  }

}

export default ExchangeService;

