import UserGames from "./UserGames";


export default function UserGamesView(){

    const {userGames, delUserGame} = UserGames();

    return (
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
        </div>);

}