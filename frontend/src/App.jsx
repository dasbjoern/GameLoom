import React, { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Page from './Page.jsx'
import ApiFetcher from './ApiFetcher.jsx'
import axios from 'axios'


// https://github.com/Revadike/InternalSteamWebAPI/wiki/Get-Search-Results
function GetSteamGlobalTop(steam, loading, error){

  // const url = "http://localhost:5173/api";


  const [selectedItems, setSelectedItems] = useState([]);


  useEffect(() => 
    {
    axios.get("http://localhost:8080/data/games/all")
      .then(response => {
        console.log("test" + response.data);
        setSelectedItems(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the items!', error);
      });
  }, []);
   
      
  const addItem = (add) => {
    
    

    if(selectedItems.find((element) => {return add.name === element.name})){
      
      console.log("Already added");
    }
    else{
      // item.preventDefault();
      // setSelectedItems([add, ...selectedItems]);
      // const game = { item.name, image };
      // add.flag="add";

      axios.post('http://localhost:8080/data/games/add', add)
        .then(response => {
          
          setSelectedItems([...selectedItems, response.data]);
          // setName('');
          // setLogo('');

        })
        .catch(error => {
          console.error('There was an error adding the item!', error);
        });
    }
    console.log(selectedItems);
    
  };

  const delItem = (item) => {
    if(selectedItems.find((element) => {return item.name === element.name})){

      // item.flag="del";
      console.log(item);
      axios.post('http://localhost:8080/data/games/del', item)
        .then(response => {
          // console.log("delItem: " + response.data);
          // console.log(response.data);
          if(response.data){
            setSelectedItems(selectedItems.filter((element) => element.name != item.name));
            console.log("Removed item");

          }else{
            console.log("Could not be deleted.");
          }
          // setSelectedItems([...selectedItems, response.data]);
        })
        .catch(error => {
          console.error('There was an error deleting the item!', error);
        });


    }
    else
      console.log("Item does not exist.");
      // setSelectedItems([...selectedItems, item]);
    console.log(selectedItems);

  }

  console.log("getsteamglobtop: " + steam);
  
  return (<div class="lists">

    <div class="container">
    <h3>Added items:</h3>
    <ul>
    {selectedItems.map((addedItem) => (
      <li>
      <p key={addedItem.id}>{addedItem.name}</p>
      <img src={addedItem.logo} alt="Logo" />
      <button onClick={() => delItem(addedItem)} >{"Del"}</button>
      </li>
    ))}
    </ul>
    </div>
    <div class="container">
    <h3>Steam global top:</h3>
    
    <ul>
    
   {steam && steam.map((item, index) => (
        

        <li>
        <p key={index}>{item.name}</p>
        <img src={item.logo} alt="Logo" />
        <button onClick={() => addItem(item)} >{"Add"}</button>
        </li>
      ))}

    </ul>
      {loading && <p>Loading...</p>}
      {error && <p>Error: {error.message}</p>}

      </div>
    </div>);

      

}

function App() {

  const {steam, loading, error, fetchData} = ApiFetcher();
  
  
  const url = "http://localhost:8080/api/hello";//?filter=globaltopsellers&&ignore_preferences=1&json=1";
  // const urlTwo = "http://localhost:5173/api/?filter=globaltopsellers&category3=38&ignore_preferences=1&json=1";

 
  
  return (
    <>
  

      <h1>Hello world</h1>
      <Page/>
      <button onClick={() => fetchData(url)} >{"Steam Global Top"}</button>
      {/* <button onClick={() => fetchData(urlTwo)} >{"Steam Online Coop TOP"}</button> */}

      
      {steam && GetSteamGlobalTop(steam, loading, error)}



     
    </>
  )
}

export default App
