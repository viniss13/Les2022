
import ApiService from "../api/ApiService"

class DashboardService extends ApiService {

  constructor() {
    super('/api/dashboard');
  }

  getDashboard() {
    return this.get('/read');
  }



}

export default DashboardService;

