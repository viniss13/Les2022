import React from 'react'
import { useParams } from 'react-router-dom';
import OrderService from '../../service/Order/OrderService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const orderService = new OrderService();

const AdmOrderDetails = () => {

    const params = useParams();
    const [orderData, setOrderData] = React.useState(null);

    const [order, setOrder] = React.useState(null);
    const [cart, setCart] = React.useState(null);
    const [card, setCard] = React.useState(null);
    const [address, setAddress] = React.useState(null);
    const [coupon, setCoupon] = React.useState(null);
    const [code, setCode] = React.useState('');
    const [cart_value, setCartValue] = React.useState(0);
    const [order_value, setOrderValue] = React.useState(0);
    const [coupon_value, setCouponValue] = React.useState(0);
    const [client_id, setClientId] = React.useState('');
    const [couponDiscount, setDiscount] = React.useState(0);
    const [couponCode, setCouponCode] = React.useState(null);
    const [status, setStatus] = React.useState(null);
    const [exchanges, setExchanges] = React.useState(null);
    const [cards, setCards] = React.useState(null);

    console.log(params);

    const orderDetails = () => {
        orderService.getOrderDetails(params.id)
            .then(response => {
                console.log(response.data);
                setOrder(response.data);
                setCart(response.data.cart.items);
                setCard(response.data.card);
                setAddress(response.data.address);
                setCartValue(response.data.cart.total_value);
                setExchanges(response.data.exchanges);
                setCards(response.data.cards);

                if (response.data.coupon != null) {
                    setCouponValue(response.data.coupon.coupon_value);
                    setCoupon(response.data.coupon);
                    setCouponCode(response.data.coupon.code);
                }
                setStatus(response.data.status);
                // setCouponValue(response.data.entities[0].)

                setOrderValue(response.data.order_value)
            })
    }

    const confirmPayment = () => {
        alert('aaaaaaaaaaaaaa')
    }

    const changeStatus = () => {
        let nextStatus = '';

        switch (status) {
            case 'EM_ANALISE':
                nextStatus = 'PAGAMENTO_REALIZADO';
                break;
            case 'PAGAMENTO_REALIZADO':
                nextStatus = 'EM_TRANSPORTE';
                break;
            case 'EM_TRANSPORTE':
                nextStatus = 'ENTREGA_REALIZADA';
                break;
            case 'ENTREGA_REALIZADA':
                nextStatus = 'FINALIZADO';
                break;
            case 'TROCA_PENDENTE':
                nextStatus = 'TROCA_AUTORIZADA';
                break;
            case 'TROCA_AUTORIZADA':
                nextStatus = 'TROCA_CONCLUIDA';
                break;

        }

        if (nextStatus != '') {
            orderService.updateOrder({ "id": params.id, "status": nextStatus })
                .then(response => {
                    toast.dark("Status do Pedido atualizado com sucesso!")
                    orderDetails();
                }).catch(err => {

                })
        }

    }

    const rejectExchange = () => {
        orderService.updateOrder({ "id": params.id, "status": "TROCA_REJEITADA" })
            .then(response => {
                toast.dark("Status do Pedido atualizado com sucesso!")
                orderDetails();
            }).catch(err => {

            })
    }

    React.useEffect(() => {
        orderDetails();
    }, []);


    return (
        <>

            <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">
                <ToastContainer />
                <div className="container  p-5 d-flex flex-row justify-content-center flex-wrap ">
                    <h1>Itens do Pedido</h1>
                </div>
                {cart?.length === 0 && <h1>Sem items</h1>}
                {cart?.map((item) => (
                    <div key={item.product.id} className="card m-3">
                        {console.log('CARDS DENTRO DO MAP', item.product)}
                        <div className="card-body" >
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item"> <img src={item.product.imageUrl} width="250" height="250" style={{ alignItems: "center", justifyContent: "center" }} /> </li>
                                <li className="list-group-item">Nome: {item.product.name}</li>
                                <li className="list-group-item">Pre??o: R$ {item.product.price}</li>
                                <li className="list-group-item">Quantidade: {item.quantity}</li>
                                <li className="list-group-item">Pre??o Total: R$ {item.total_value}</li>
                            </ul>
                        </div>


                    </div>
                ))}
            </div>

            {/* Fim itens */}

            <div className="p-5 d-flex flex-row justify-content-center flex-wrap">

                {exchanges?.length !== 0 &&
                    <div className="container  p-5 d-flex flex-row justify-content-center flex-wrap ">
                        <h1>Itens para Troca</h1>
                        <hr />
                    </div>
                }

                {exchanges?.map((item) => (
                    <div key={item.product.id} className="card m-3">

                        <div className="card-body" >
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item">Nome: {item.product.name}</li>
                                <li className="list-group-item">Pre??o: R$ {item.product.price}</li>
                                <li className="list-group-item">Quantidade: {item.quantity}</li>
                                <li className="list-group-item">Pre??o Total: R$ {item.total_value}</li>
                            </ul>
                        </div>
                    </div>
                ))}
            </div>
            {/* Fim pedidos de troca */}

            <div className="card p-5 d-flex flex-row justify-content-center flex-wrap">

                {cards?.length === 0 && <h1>Sem cart??es</h1>}
                {cards?.map((card, index) => (
                    <div key={card.id + 'c'} className="card m-3">

                        <div className="card-body" >
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item">Apelido: {card.card.alias}</li>
                                <li className="list-group-item">Bandeira: {card.card.flag}</li>
                                <li className="list-group-item">N??mero: {card.card.number}</li>
                                <li className="list-group-item">Valor: R${card.buyingValue}</li>
                            </ul>
                        </div>
                    </div>
                ))}
            </div>

            <div className="container p-5 d-flex flex-row justify-content-center flex-wrap ">
                {address != null &&
                    <div className="card m-3">
                        <div className="card-body" >
                            <h3>Endere??o Selecionado</h3>
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item">Logradouro: {address.logradouro}</li>
                                <li className="list-group-item">Tipo Logradouro: {address.residencyType}</li>
                                <li className="list-group-item">N??mero: {address.number}</li>
                                <li className="list-group-item">CEP: {address.zipCode}</li>
                                <li className="list-group-item">Observa????o: {address.observation}</li>

                            </ul>
                        </div>
                    </div>
                }
                {card != null &&
                    <div key={card.id} className="card m-3">
                        <div className="card-body" >
                            <h3>Cart??o Selecionado</h3>
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item">Apelido: {card.alias}</li>
                                <li className="list-group-item">Bandeira: {card.flag}</li>
                                <li className="list-group-item">N??mero: {card.number}</li>

                            </ul>
                        </div>
                    </div>
                }
                {cart != null && //espera os atributos carregarem
                    <div className="row">
                        <div className="container p-5 d-flex flex-row justify-content-center flex-wrap ">
                            <div className="card-body col-6">
                                <h4 className="mt-4 mt-2">Status: {
                                    <p className={{
                                        'DRAFT': 'text-secondary',
                                        'EM_ANALISE': 'text-warning',
                                        'PAGAMENTO_REALIZADO': 'text-success',
                                        'EM_TRANSPORTE': 'text-info',
                                        'ENTREGA_REALIZADA': 'text-success',
                                        'RECUSADO': 'text-danger',
                                        'FINALIZADO': 'text-success'
                                    }[status]}>{status}
                                    </p>}
                                </h4>
                                {coupon != null &&
                                    <>
                                        <h4 className="mt-5">Valor da Compra: R$ {cart_value}</h4>
                                        <h4 className="mt-5">Cupom utilizado: R$ {couponCode}</h4>
                                        <h4 className="mt-5">Desconto do Cupom: R$ {coupon_value}</h4>
                                    </>
                                }

                                <h4 className="mt-5">Total: R$ {order_value}</h4>
                            </div>
                        </div>
                    </div>
                }
            </div>
            {/* Fim Endere??o */}
            <div className="row">
                {status !== "FINALIZADO" && status !== "TROCA_REALIZADA" && status !== "TROCA_REJEITADA" && status !== "TROCA_CONCLUIDA" &&
                    <div className="container col-6 p-5 d-flex  justify-content-center">

                        <button onClick={changeStatus} className="btn btn-success">

                            {status === 'EM_ANALISE' && 'Confirmar Pagamento'}
                            {status === 'PAGAMENTO_REALIZADO' && 'Enviar Pedido'}
                            {status === 'EM_TRANSPORTE' && 'Confirmar Entrega'}
                            {status === 'ENTREGA_REALIZADA' && 'Finalizar Pedido'}
                            {status === 'TROCA_PENDENTE' && 'Autorizar Troca'}
                            {status == 'TROCA_AUTORIZADA' && 'Concluir Troca'}

                        </button>
                        {/* <button className="btn btn-danger">Recusar Pedido</button> */}
                    </div>}

                {status === "TROCA_PENDENTE" &&
                    <div className="container col-6 p-5  ">

                        <button onClick={rejectExchange} className="btn btn-danger">
                            Rejeitar Troca
                        </button>
                        {/* <button className="btn btn-danger">Recusar Pedido</button> */}
                    </div>}
            </div>



        </>
    )
}

export default AdmOrderDetails