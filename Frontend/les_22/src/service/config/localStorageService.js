
export default class LocalStorageService {

  static addItem(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
  }

  static obterItem(key) {

    const item = localStorage.getItem(key);

    return JSON.parse(item);
  }

  static removerItem(key) {
    console.log(key)
    localStorage.removeItem(key)

  }

  static addToCart(item){
   let cart = localStorage.getItem("cart");
   if(cart == null){
    cart = [];
   } else {
    cart = JSON.parse(cart);
   }
   const itemID = item.id;
   for(let i = 0; i < cart.length; i++){
    if(cart[i].id === itemID){
      cart[i].quantity = item.quantity;
      localStorage.setItem("cart", JSON.stringify(cart));
      return "ENTROU AQUI";
    }
   }

   cart.push(item);

   localStorage.setItem("cart", JSON.stringify(cart));
  //  console.log("teste", teste);
    //localStorage.setItem("cart", JSON.stringify(item));
  }

  static getCart(){
    let cart = localStorage.getItem("cart");
   if(cart == null){
    cart = [];
   } else {
    cart = JSON.parse(cart);
   }

   return cart;
  }
}