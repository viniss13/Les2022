
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

  addCoupon(request){
    return this.put("/add_coupon", request);
  }

  sendOrder(request){
    return this.put("/update", request);
  }
  
  getClientOrders(client_id){
    return this.get(`/read_client?client_id=${client_id}`);
  }

  getOrderDetails(order_id){
    return this.get(`/read_details?order_id=${order_id}`);
  }

  getOrders(){
    return this.get("/read");
  }

  updateOrder(request){
    return this.put("/update", request)
  }

}

export default OrderService;

