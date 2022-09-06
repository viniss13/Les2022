
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
}