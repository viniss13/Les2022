
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

    createCard(card) {
        return this.post("/create", card);
    }


    getAddressMonth(){
      return [
          { label: "Selecione...", value:'' },
          { label: "01", value:'01' },
          { label: "02", value:'02' },
          { label: "03", value:'03' },
          { label: "04", value:'04' },
          { label: "05", value:'05' },
          { label: "06", value:'06' },
          { label: "07", value:'07' },
          { label: "08", value:'08' },
          { label: "09", value:'09' },
          { label: "10", value:'10' },
          { label: "11", value:'11' },
          { label: "12", value:'12' }
      ]
  }

  getAddressYear(){
      return [
          { label: "Selecione...", value:'' },
          { label: "2023", value:'2023' },
          { label: "2024", value:'2024' },
          { label: "2025", value:'2025' },
          { label: "2026", value:'2026' },
          { label: "2027", value:'2027' },
          { label: "2028", value:'2028' },
          
      ]
  }

  getCardFlag(){
      return [
          { label: "MASTERCARD", value:'MASTERCARD' },
          { label: "VISA", value:'VISA' },
          { label: "ELO", value:'ELO' }
      ]
  }

}

export default CardService;