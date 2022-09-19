
import ApiService from "../api/ApiService"

class ProductService extends ApiService {

    constructor() {
        super('/api/products');
    }

    getAllProducts() {
        return this.get(`/read`);
    }
    
    getById(id) {
        console.log('id',id)
        return this.get(`/read_by_id?id=${id}`);
    }

    deleteProduct(id) {
        return this.delete(`/delete?id=${id}`);
    }

    createProduct(product) {
        return this.post("/create", product);
    }

}

export default ProductService;