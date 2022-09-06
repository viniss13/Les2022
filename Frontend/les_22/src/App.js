import Footer from "./Component/Footer";
import NavBar from "./Component/navbar";
import RoutesService from "./service/api/RoutesService";

function App() {
  return (
    <div className="App">

      <NavBar />

      <RoutesService />

      <Footer />
    </div>
  );
}

export default App;
