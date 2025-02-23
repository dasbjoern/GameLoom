import { useState, useEffect } from 'react';
import axios from 'axios';
import GetSteamGlobalTop from './GetSteamGlobalTop';

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

    return {searchResult, searchString, searchSteam};
    
}

export default SearchSteam;