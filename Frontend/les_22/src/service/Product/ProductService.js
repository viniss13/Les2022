
import ApiService from "../api/ApiService"

class ProductService extends ApiService {

    constructor() {
        super('/api/products');
    }

    getAllProducts(search) {
        return this.get(`/read?search=${search}`);
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