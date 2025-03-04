import React, { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Page from './Page.jsx'
import ApiFetcher from './ApiFetcher.jsx'
import SearchSteam from './SearchSteam.jsx'
import axios from 'axios'
import { BrowserRouter, Routes, Route } from "react-router-dom"; 
// import { Outlet, Link } from "react-router-dom";

// https://github.com/Revadike/InternalSteamWebAPI/wiki/Get-Search-Results


function App() {

  // const {steam, loading, error, fetchData} = ApiFetcher();
  
  
  // const url = "http://localhost:8080/api/hello";//?filter=globaltopsellers&&ignore_preferences=1&json=1";
  // const urlTwo = "http://localhost:5173/api/?filter=globaltopsellers&category3=38&ignore_preferences=1&json=1";

 
  
  return (
    <>
     <BrowserRouter>
      <Routes>
        {/* <Route path="/search" element={<SearchSteam />}/> */}
        
        <Route path="/welcome">
          <Route index element={<Page/>}/>
        </Route>
          {/* <Route path="/welcome" element={<SearchSteam />}/> */}

      </Routes>
    
      {/* <Page/>
      <SearchSteam/> */}

      {/* <button onClick={() => fetchData(url)} >{"Steam Global Top"}</button> */}
      {/* <button onClick={() => fetchData(urlTwo)} >{"Steam Online Coop TOP"}</button> */}

      
      {/* {steam && GetSteamGlobalTop(steam, loading, error)} */}


    </BrowserRouter>

     
    </>
  )
}

export default App
