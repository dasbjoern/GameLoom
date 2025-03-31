import { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ApiFetcher from './ApiFetcher.jsx' 
import axios from 'axios'
import GetSteamGlobalTop from './GetSteamGlobalTop.jsx'
import SearchSteam from './SearchSteam.jsx'
import UserGames from './UserGames.jsx'
import UserGamesView from './UserGamesView.jsx'



function Page() {

  const redirectSteam = (appid) => {
    window.open("https://store.steampowered.com/app/" + appid.split("https://shared.cloudflare.steamstatic.com/store_item_assets/steam/apps/")[1].split("/")[0], '_blank').focus();
  }

  const ListGames = ({gamelist, opfunc, optype, heading}) => {

    return (
    <div class="container">
  
    <h3>{heading}</h3>
    <ul>
    {gamelist.map((game) => (
      <li>
      <p key={game.id}>{game.name}</p>
      <img src={game.logo} alt="Logo" onClick={() => redirectSteam(game.logo)} />
      
      {(optype==="Del") && (<button onClick={() => updateLists(game, opfunc)} >{optype}</button>)}
      {/* {console.log("likedgames in func: ")} */}
      {/* {console.log(likedGames[0])} */}
      {(likedGames.some( likedgame => 
        (likedgame.name === game.name))) ? (

        
          
            <>
            <button onClick={() => 
              {updateLists(game, delUserGame); 
              // setLikedGames(likedGames.filter(item => item.name !== game.name));
              }} >{"Unlike"}</button>
            {/* {setLikedGames(likedGames.filter(item => item.id !== game.id))} */}

            </>
            
        )
            :
            
            <>
            
            <button onClick={() => updateLists(game, addUserGame)} >{"Like"}</button>
            </>
  }

         
       
  

    {/* {(optype==="Del") && (<><button onClick={() => updateLists(game, opfunc)} >{optype}</button>
    <button onClick={() => updateLists(game, delUserGame)} >{"Unlike"}</button></>)}
    {(optype==="Skip") && <button onClick={() => updateLists(game, delUserGame)} >{"Unlike"}</button>}
     
   
        {(optype==="Del") && (<><button onClick={() => updateLists(game, opfunc)} >{optype}</button>
                              <button onClick={() => updateLists(game, addUserGame)} >{"Like"}</button></>)}
        {(optype==="Skip") && <button onClick={() => updateLists(game, addUserGame)} >{"Like"}</button>} */}
  
      
      
  
      </li>
    ))}
    {loading && <p>Loading...</p>}
    {error && <p>Error</p>}
    </ul>
    </div>);
  }

  const [trigger, setTrigger] = useState(0);
  
  const updateLists = (game, afunc) => {

    afunc(game, () => {
      setTrigger(prev => prev + 1);
      setLikedGames(likedGames.filter(item => item.name !== game.name));
    });
    // addItem(game);
    

  }


  const url = "http://localhost:8080/api/hello";


 


  const {userGames, likedGames, setLikedGames, addUserGame, delUserGame} = UserGames({trigger});
  
  const {searchResult, searchString, searchSteam} = SearchSteam();
  
  const {steam, loading, error, fetchData} = ApiFetcher();

  const {selectedItems, addItem, delItem} = GetSteamGlobalTop({trigger});

  // const [likedGames, setLikedGames] = useState(userGames);
  // console.log("likedgames ");
  // console.log(likedGames);
  // let likedGames = [...userGames];
 

  return (
    <>
      <h1>Hello world</h1>

      <form onSubmit={searchSteam}>
              <label for="searchsteam">Search steam</label>
               <input type="text" id="searchsteam" name="searchsteam" onChange={searchString} /><br></br>
              <button type="submit">Search</button>
         </form>
      <div class="search-container"> 

      <div class="container">
      
        <h3>{"Liked games"}</h3>
        <ul>
        {userGames.map((game) => (
          <li>
          <p key={game.id}>{game.name}</p>
          <img src={game.logo} alt="Logo" onClick={() => redirectSteam(game.logo)} />
          <button onClick={() => delUserGame(game)} >{"Unlike"}</button>
          
          </li>
        ))}
        </ul>
        </div>

      {searchResult && <ListGames gamelist={searchResult} opfunc={addUserGame} optype={"Skip"}/>}
      </div>
      <button onClick={() => fetchData(url)} >{"Steam Global Top"}</button>

      <div class="lists">
      {selectedItems && <ListGames gamelist={selectedItems} opfunc={delItem} optype={"Del"} heading={"Added items:"} />}

      
      {steam && <ListGames gamelist={steam} opfunc={addUserGame} optype={"Skip"} heading={"Steam global top:"}/>}
    
    </div>

    </>
  )


  
}

export default Page
