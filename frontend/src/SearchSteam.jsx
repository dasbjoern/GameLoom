import { useState, useEffect } from 'react';
import axios from 'axios';

function SearchSteam(){
    
    const [searchText, setSearchText] = useState({searchsteam: ""});
    const [searchResult, setSearchResult] = useState([]); //temp, should be array.
    const searchString = (e) => {
        setSearchText({ ...searchText, [e.target.name]: e.target.value });
    };

    const searchSteam = async (e) => {
        console.log(e);
        e.preventDefault();
        axios.post("http://localhost:8080/api/search", searchText)
        .then(response => {
            // console.log("test search" + searchResult);
            // console.log(response.data);
            
            // const temp = response.data.filter((item) => item.name.toLowerCase().includes(searchText.searchsteam.toLowerCase()));
            // console.log(temp);
          setSearchResult(response.data.filter((item) => item.name.toLowerCase().includes(searchText.searchsteam.toLowerCase())));
          // localStorage.setItem("steam", JSON.stringify(response.data));
        })
        .catch(error => {
          console.error('There was an error while searching. ', error);
        });

    }; 


    const [selectedItems, setSelectedItems] = useState([]);

    const addItem = (add) => {

        if(selectedItems.find((element) => {return add.name === element.name})){
          
          console.log("Already added");
        }
        else{
          axios.post('http://localhost:8080/data/games/add', add)
            .then(response => {
              
              setSelectedItems([...selectedItems, response.data]);
    
            })
            .catch(error => {
              console.error('There was an error adding the item!', error);
            });
        }
        console.log(selectedItems);
        
      };


    return (
        <>
        <form onSubmit={searchSteam}>
            <label for="searchsteam">Search steam</label>
            <input type="text" id="searchsteam" name="searchsteam" onChange={searchString} /><br></br>
            <button type="submit">Search</button>
        </form>
        <div class="search-container">
        <div class="container">
        <ul>
        {searchResult && searchResult.map( (item, index) => (
         <li>
            <p key={index}>{item.name}</p>
            <img src={item.logo} alt="Logo" />
            <button onClick={() => addItem(item)} >{"Add"}</button>
        </li>
        ))}
        </ul>
        </div>
        </div>

        </>
    );
    
}

export default SearchSteam;