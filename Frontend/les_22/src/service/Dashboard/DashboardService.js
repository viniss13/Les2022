
import ApiService from "../api/ApiService"

class DashboardService extends ApiService {

  constructor() {
    super('/api/dashboard');
  }

  getDashboard() {
    return this.get('/read');
  }

  readByDate(startDate,endDate) {
    return this.get(`/read_by_date?startDate=${startDate}&endDate=${endDate}`);
  }



}

export default DashboardService;

