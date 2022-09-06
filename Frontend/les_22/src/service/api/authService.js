
import LocalStorageService from "../config/LocalStorageService";

export const CLIENT_LOGGED = '_usuario_logado'

export default class AuthService {

  static isAuthenticateClient() {
    const client = LocalStorageService.obterItem(CLIENT_LOGGED);

    return client && client.id;
  }

  static removeAuthenticateClient() {

    LocalStorageService.removerItem(CLIENT_LOGGED);

  }
}