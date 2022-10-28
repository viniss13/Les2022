import React from 'react'
import ApiService from '../api/ApiService';

class CouponService extends ApiService {

  constructor() {
    
    super('/api/coupons');
  }

  getClientCoupon(id){
    return this.get(`/read_client?client_id=${id}`);
  }

}

export default CouponService