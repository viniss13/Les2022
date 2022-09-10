
import ApiService from "../api/ApiService"

class AddressService extends ApiService {

  constructor() {
    super('/api/adresses');
  }

  getById(id){
    return this.get(`/read_by_id?id=${id}`);
  }

  getAllAddress(id) {
    return this.get(`/read?client_id=${id}`);
  }

  deleteAddress (id){
    return this.delete(`/delete?id=${id}`);
  }

}

export default AddressService;

