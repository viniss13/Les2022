import NavBar from "./Component/navbar";
import RoutesService from "./service/api/RoutesService";

function App() {
  return (
    <div className="App">

      <NavBar />

      <RoutesService />
    </div>
  );
}

export default App;
