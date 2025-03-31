import { useState, useEffect } from 'react'
import axios from 'axios'

function UserGames({trigger}){

    const [userGames, setUserGames] = useState([]);
    const [likedGames, setLikedGames] = useState([]);


    useEffect(() => 
        {
        axios.get("http://localhost:8080/data/games/allUserGames")
          .then(response => {
            console.log("test" + response.data);
            setUserGames(response.data);
            setLikedGames(response.data);

          })
          .catch(error => {
            console.error('There was an error fetching the items!', error);
          });
      }, [trigger]);


    const addUserGame = (add, callback) => {
  
        if(userGames.find((element) => {return add.name === element.name})){
          
          console.log("Usergame Already added");
        }
        else{
        
    
          axios.post('http://localhost:8080/data/games/addUserGame', add)
            .then(response => {
              
                setUserGames([...userGames, response.data]);
                setLikedGames([...likedGames, response.data]);
                if(callback) callback();
    
            })
            .catch(error => {
              console.error('There was an error adding the usergame!', error);
            });
        }
        console.log(userGames);
        
      };
    
      const delUserGame = (del, callback) => {

        if(userGames.find((element) => {return del.name === element.name})){
    
          console.log(del);
          axios.post('http://localhost:8080/data/games/delUserGame', del)
            .then(response => {
            
              if(response.data){
                setUserGames(userGames.filter((element) => element.name != del.name));
                setLikedGames(likedGames.filter((element) => element.name != del.name));
                console.log("Removed usergame");
                if(callback) callback();
    
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

      return {userGames, likedGames, setLikedGames, addUserGame, delUserGame};


}
export default UserGames;