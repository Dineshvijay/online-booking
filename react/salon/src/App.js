import { BrowserRouter, Route, Routes } from "react-router-dom";

import AppHeader from "./components/AppHeader";
import HomePage from "./pages/HomePage";
import ChooseService from "./pages/HomePage";
import MakePaymentPage from "./pages/MakePaymentPage";
import SlotsPage from "./pages/SlotsPage";
import Verifyuser from "./pages/Verifyuser";

function App() {
  return (
    <BrowserRouter>
      <AppHeader />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route
          exact
          path="/chooseslot/:serviceId/:serviceName"
          element={<SlotsPage />}
        />

        <Route
          exact
          path="/makepayment/:serviceId/:slotId"
          element={<MakePaymentPage />}
        />
        <Route exact path="/admin/verifyuser" element={<Verifyuser />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
