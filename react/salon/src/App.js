import { BrowserRouter, Routes, Route } from "react-router-dom";
import AppHeader from "./components/AppHeader";
import HomePage from "./pages/HomePage";
import ChooseService from "./pages/HomePage";
import SlotsPage from "./pages/SlotsPage";

function App() {
  return (
    <BrowserRouter>
      <AppHeader />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route
          path="/chooseslot/:serviceId/:serviceName"
          element={<SlotsPage />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
