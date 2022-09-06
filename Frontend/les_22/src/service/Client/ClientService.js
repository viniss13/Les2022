
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

   delete(id) {      

       return this.delete(`/delete?id=${id}`);

   }

   update(client){
    return this.put('/update', client);
   }

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

  alertMessage(data){
    
    let messages = [];
          for( let i = 0; i < data.msg.length; i++){
            messages.push(data.msg[i]);
        }
        messages = messages.join("\n");
        alert(messages);
      
  }

}

export default ClientService;