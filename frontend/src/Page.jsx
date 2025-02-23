import { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ApiFetcher from './ApiFetcher.jsx' 
import axios from 'axios'
import GetSteamGlobalTop from './GetSteamGlobalTop.jsx'
import SearchSteam from './SearchSteam.jsx'


const ListGames = ({gamelist, opfunc, optype, heading, loading, error}) => {

  return (
  <div class="container">

  <h3>{heading}</h3>
  <ul>
  {gamelist.map((game) => (
    <li>
    <p key={game.id}>{game.name}</p>
    <img src={game.logo} alt="Logo" />
    <button onClick={() => opfunc(game)} >{optype}</button>
    </li>
  ))}
  {loading && <p>Loading...</p>}
  {error && <p>Error</p>}
  </ul>
  </div>);
}

function Page() {

  const url = "http://localhost:8080/api/hello"
  
  const {searchResult, searchString, searchSteam} = SearchSteam();
  
  const {steam, loading, error, fetchData} = ApiFetcher();

  const {selectedItems, addItem, delItem} = GetSteamGlobalTop();

  return (
    <>
      <h1>Hello world</h1>

      <form onSubmit={searchSteam}>
              <label for="searchsteam">Search steam</label>
               <input type="text" id="searchsteam" name="searchsteam" onChange={searchString} /><br></br>
              <button type="submit">Search</button>
         </form>
      <div class="search-container"> 
      {searchResult && <ListGames gamelist={searchResult} opfunc={addItem} optype={"Add"} heading={"Search results:"}/>}
      </div>
      <button onClick={() => fetchData(url)} >{"Steam Global Top"}</button>

      <div class="lists">
      <ListGames gamelist={selectedItems} opfunc={delItem} optype={"Del"} heading={"Added items:"}/>

      
      {steam && <ListGames gamelist={steam} opfunc={addItem} optype={"Add"} heading={"Steam global top:"} loading={loading} error={error}/>}
    
    </div>

    </>
  )


  
}

export default Page
