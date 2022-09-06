
import ApiService from "../api/ApiService"

class ClientService extends ApiService {

  constructor() {
    super('/api/clients');
  }

  authenticate(credentials) {

    const item = this.post('/login', credentials)
    console.log("ITEM LOGIN: ", item);

    return item;
  }

  signIn(client) {  
     
    console.log("CLIENT", client)

    return this.post('/create', client);
  }

  // deleteId(id) {      

  //     return this.delete(`/deletar?id=${id}`);

  // }

  // disableClientId(id){
  //     console.log("to ino no inativar")
  //     return this.put(`/inativar?id=${id}`);
  // }

  // getAllClients(){
  //     console.log("BUSCANTO TODOS")
  //     return this.get('/listaClientes')
  // }

  // getClientDetails(id){

  //     return this.get(`/detalhesUsuario?id=${id}`);
  //     //return this.get(`/consultarCliente?id=${id}`);
  //     //return this.post(`/consultarCliente`, client)
  // }

  getGender() {
    return [
      { label: 'Selecione...', value: '' },
      { label: 'MASCULINO', value: 'MASCULINO' },
      { label: 'FEMININO', value: 'FEMININO' },
      { label: 'OUTRO', value: 'OUTRO' }
    ]
  }

  getType() {
    return [
      { label: 'Selecione...', value: '' },
      { label: 'FIXO', value: 'FIXO' },
      { label: 'MOVEL', value: 'MOVEL' }
    ]
  }



}

export default ClientService;