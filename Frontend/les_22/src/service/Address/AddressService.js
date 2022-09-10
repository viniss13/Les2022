
import ApiService from "../api/ApiService"

class AddressService extends ApiService {

  constructor() {
    super('/api/adresses');
  }

  create(address){
    return this.post('/create', address);
  }

  updateAddress(address){
    return this.put('/update', address);
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

  getAddresType(){
    return [
        { label: 'Selecione...', value:'' },
        { label:'COBRANCA', value:'COBRANCA' },
        { label:'ENTREGA', value:'ENTREGA' }
    ]
}

}

export default AddressService;

