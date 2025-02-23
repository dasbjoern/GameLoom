import { useState, useEffect } from 'react'
import axios from 'axios'


function GetSteamGlobalTop(){

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

  return {selectedItems, addItem, delItem};

}
export default GetSteamGlobalTop;