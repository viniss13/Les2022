
import ApiService from "../api/ApiService"

class CartService extends ApiService {

  constructor() {
    super('/api/carts');
  }

  update(item){
    return this.put("/update", item);
  }

  read(client_id){
    return this.get(`/read?client_id=${client_id}`);    
  }

  delete_item(item_id){
    return this.delete(`/delete_item?item_id=${item_id}`);
  }


}

export default CartService;