import { useState, useEffect } from 'react'
import axios from 'axios'

function UserGames(){

    const [userGames, setUserGames] = useState([]);

    useEffect(() => 
        {
        axios.get("http://localhost:8080/data/games/allUserGames")
          .then(response => {
            console.log("test" + response.data);
            setUserGames(response.data);
          })
          .catch(error => {
            console.error('There was an error fetching the items!', error);
          });
      }, []);


    const addUserGame = (add) => {
  
        if(userGames.find((element) => {return add.name === element.name})){
          
          console.log("Usergame Already added");
        }
        else{
        
    
          axios.post('http://localhost:8080/data/games/addUserGame', add)
            .then(response => {
              
                setUserGames([...userGames, response.data]);
            
    
            })
            .catch(error => {
              console.error('There was an error adding the usergame!', error);
            });
        }
        console.log(userGames);
        
      };
    
      const delUserGame = (del) => {

        if(userGames.find((element) => {return del.name === element.name})){
    
          console.log(del);
          axios.post('http://localhost:8080/data/games/delUserGame', del)
            .then(response => {
            
              if(response.data){
                setUserGames(userGames.filter((element) => element.name != del.name));
                console.log("Removed usergame");
    
              }else{
                console.log("Could not be deleted.");
              }
            })
            .catch(error => {
              console.error('There was an error deleting the usergame!', error);
            });
    
        }
        else
          console.log("Usergame does not exist.");
        console.log(userGames);
    
      }

      return {userGames, addUserGame, delUserGame};


}
export default UserGames;