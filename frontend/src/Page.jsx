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


const ListGames = ({gamelist, opfunc, optype, heading, loading, error, addusergame, updatelists}) => {
  
  
  

  return (
  <div class="container">

  <h3>{heading}</h3>
  <ul>
  {gamelist.map((game) => (
    <li>
    <p key={game.id}>{game.name}</p>
    <img src={game.logo} alt="Logo" />
    {(optype==="Del") && (<><button onClick={() => updatelists(game, opfunc)} >{optype}</button>
                          <button onClick={() => updatelists(game, addusergame)} >{"Like"}</button></>)}
    {(optype==="Skip") && <button onClick={() => updatelists(game, addusergame)} >{"Like"}</button>}
    

    </li>
  ))}
  {loading && <p>Loading...</p>}
  {error && <p>Error</p>}
  </ul>
  </div>);
}

function Page() {
  
  const [trigger, setTrigger] = useState(0);
  
  const updateLists = (game, afunc) => {

    afunc(game, () => {
      setTrigger(prev => prev + 1);

    });
    // addItem(game);
    

  }


  const url = "http://localhost:8080/api/hello";


 


  const {userGames, addUserGame, delUserGame} = UserGames({trigger});
  
  const {searchResult, searchString, searchSteam} = SearchSteam();
  
  const {steam, loading, error, fetchData} = ApiFetcher();

  const {selectedItems, addItem, delItem} = GetSteamGlobalTop({trigger});



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
          <img src={game.logo} alt="Logo" />
          <button onClick={() => delUserGame(game)} >{"Unlike"}</button>
          
          </li>
        ))}
        </ul>
        </div>

      {searchResult && <ListGames gamelist={searchResult} opfunc={addItem} optype={"Add"} heading={"Search results:"} addusergame={addUserGame} updatelists={updateLists}/>}
      </div>
      <button onClick={() => fetchData(url)} >{"Steam Global Top"}</button>

      <div class="lists">
      {selectedItems && <ListGames gamelist={selectedItems} opfunc={delItem} optype={"Del"} heading={"Added items:"} addusergame={addUserGame} updatelists={updateLists}/>}

      
      {steam && <ListGames gamelist={steam} opfunc={addUserGame} optype={"Skip"} heading={"Steam global top:"} loading={loading} error={error} addusergame={addUserGame} updatelists={updateLists}/>}
    
    </div>

    </>
  )


  
}

export default Page
