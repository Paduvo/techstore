import Home from "./pages/Home";
import Login from "./pages/Login";
import "./App.css";

function App() {
  const caminho = window.location.pathname;

  if (caminho === "/login") {
    return <Login />;
  }

  return <Home />;
}

export default App;
