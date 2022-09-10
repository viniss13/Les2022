import React from 'react'
import { useParams } from 'react-router-dom';
import AddressService from '../../../service/Address/AddressService';

const addressService = new AddressService();

const AddressUpdate = () => {

  const [data, setData] = React.useState(null);
  const { id } = useParams();

  React.useEffect( () =>{
    addressService.getById(id)
      .then( response =>{
        console.log( response.data.city )
        setData(response.data.entities);
      } )
  },[])

  return (
    <div>AddressUpdate</div>
  )
}

export default AddressUpdate