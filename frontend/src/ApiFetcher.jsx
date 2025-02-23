import React, { useState, useEffect } from 'react'
import axios from 'axios';


function ApiFetcher(){

    // https://stackoverflow.com/questions/71402674/whats-the-best-way-to-set-localstorage-in-react
    
    // const [steam, setSteam] = useState(JSON.parse(localStorage.getItem('steam')));
    const [steam, setSteam] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchData = (url) => 
      {
      setLoading(true);
      axios.get(url)
        .then(response => {
          setSteam(response.data);
          console.log(steam);
          // localStorage.setItem("steam", JSON.stringify(response.data));
          setLoading(false);
        })
        .catch(error => {
          console.error('There was an error fetching the items!', error);
          setError(error);
          setLoading(false);
        });
    };

    
    
   

  return {steam, loading, error, fetchData};

}
export default ApiFetcher;