import React from 'react'
import CouponService from '../../service/Coupon/CouponService';

const couponService = new CouponService();
const ClientCoupons = () => {

  const [coupons, setCoupons] = React.useState([]);

  React.useEffect( () => {
    const userData = JSON.parse(localStorage.getItem("_logged_user")).entities[0];

    getCoupons(userData.id);

  }, []);

  const getCoupons = (userId) => {
    couponService.getClientCoupon(userId)
      .then( response => {
        setCoupons(response.data.entities);
        
      }).catch( err => {

      })
  }

  return (
    <>
       <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
        <div className="container">
        
        </div>
        {coupons?.length === 0 && <h1>Sem Cupons</h1>}
        {coupons?.map((coupon) => (
          <div key={coupon.id} className="card m-3">
            <div className="card-body" >
              <ul className="list-group list-group-flush">              
                <li className="list-group-item">Código: {coupon.code}</li>                
                <li className="list-group-item">Valor: R$ {coupon.coupon_value}</li>
                <li className="list-group-item">Quantidade: {coupon.quantity}</li>
                <li className="list-group-item">Tipo do cupom: {coupon.type}</li>
              </ul>
            </div>                       
          </div>
        ))}
      </div>
      <a href="/user_home" className="btn btn-outline-secondary mb-2 " >Voltar</a>
    </>  
)
}

export default ClientCoupons