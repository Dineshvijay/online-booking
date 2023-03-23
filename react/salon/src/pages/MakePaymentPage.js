import React, { useEffect, useState } from "react";
import "../App.css";

import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import CheckoutForm from "../components/CheckoutForm";
import { Service } from "../config/Service";
import UserForm from "../components/UserForm";
import { Container } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import ShowConfirmedTicket from "../components/ShowConfirmedTicket";
import AppNotificationComponent from "../components/AppNotificationComponent";
import AppAlert from "../components/AppAlert";

const stripePromise = loadStripe(
  "pk_test_51MlPqdSCcfA0lX7eYPCx2eqUvOEQQmAr2cS6cgCe5RxXL7y6y5XMr8d7wImkxSgqTl5C2ztukIhfVwDF3tGxRkJG00uutWeOJO"
);

export default function MakePaymentPage(props) {
  const [clientSecret, setClientSecret] = useState("");
  const [paymentId, setPaymentId] = useState();
  const [paymentStatus, setPaymentStatus] = useState(null);
  const [ticketDetails, setTicketDetails] = useState(null);
  const navParams = useParams();
  const navigate = useNavigate();

  const appearance = {
    theme: "stripe",
  };
  const options = {
    clientSecret,
    appearance,
  };

  const handleMakePayment = (params) => {
    console.log(`MakePaymentPage called ${JSON.stringify(params)}`);
    const URL = `${Service.PAYMENT_BASE_URL}${Service.INITIATE_PAYMENT}`;
    const reqParams = { ...params };
    reqParams.slotID = parseInt(navParams.slotId);
    reqParams.salonServiceDetailID = parseInt(navParams.serviceId);
    console.log(`request params: ${reqParams}`);
    fetch(URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(reqParams),
    })
      .then((res) => res.json())
      .then((data) => {
        setClientSecret(data?.data?.secretId);
        setPaymentId(data?.data?.paymentID);
      });
  };

  const handleUpdatePayment = (status) => {
    const params = {
      paymentStatus: status,
    };
    const url = `${Service.PAYMENT_BASE_URL}${Service.CONFIRM_PAYMENT}/${paymentId}`;
    fetch(url, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(params),
    })
      .then((res) => res.json())
      .then((data) => {
        const status = data?.status;
        if (status?.code != 200) {
          setPaymentStatus("Fail");
        } else {
          const id = data?.data?.id;
          handleVerifyTickets(id);
        }
      });
  };

  const handleVerifyTickets = (ticketId) => {
    const URL = `${Service.TICKETS_BASE_URL}/${ticketId}`;
    fetch(URL)
      .then((res) => res.json())
      .then((data) => {
        console.log(`handleVerifyTickets => ${JSON.stringify(data)}`);
        const status = data?.status;
        if (status?.code != 200) {
          setPaymentStatus("Fail");
        } else {
          setTicketDetails(data?.data);
          setPaymentStatus("Success");
        }
      });
  };

  return (
    <Container fluid="md">
      {clientSecret?.length == 0 ? (
        <UserForm makePay={handleMakePayment} />
      ) : (
        <>
          {paymentStatus == null ? (
            <Elements options={options} stripe={stripePromise}>
              <CheckoutForm
                clientSecret={clientSecret}
                updatePayment={handleUpdatePayment}
              />
            </Elements>
          ) : null}
          {paymentStatus == "Success" && ticketDetails != null ? (
            <>
              <ShowConfirmedTicket data={ticketDetails} />
            </>
          ) : null}

          {paymentStatus == "Fail" && ticketDetails == null ? (
            <>
              <AppAlert
                show={true}
                variant={`danger`}
                ok={`Try again`}
                cancel={`Cancel`}
                msg={`Payment failed. Try again`}
                onClickOk={() => {
                  setPaymentStatus(null);
                  handleMakePayment();
                }}
                onClickCancel={() => {
                  window.location.replace("/");
                }}
              />
            </>
          ) : null}
        </>
      )}
    </Container>
  );
}
