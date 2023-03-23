import React, { useEffect, useState } from "react";
import { useStripe, useElements, CardElement } from "@stripe/react-stripe-js";
import "../App.css";

export default function CheckoutForm(props) {
  const stripe = useStripe();
  const elements = useElements();
  const [message, setMessage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!stripe || !elements) {
      return;
    }
    setIsLoading(true);
    const payload = await stripe.confirmCardPayment(props.clientSecret, {
      payment_method: {
        card: elements.getElement(CardElement),
      },
    });
    console.log(`confirmPayment error => ${payload.error?.message}`);
    if (payload.error) {
      setMessage(`Payment failed ${payload.error.message}`);
      setIsLoading(false);
      props.updatePayment(2);
    } else {
      setMessage("Payment success");
      setIsLoading(false);
      props.updatePayment(1);
    }
  };

  const paymentElementOptions = {
    layout: "tabs",
  };

  return (
    <div style={{ padding: 10 }}>
      <p className="title">{`Enter card details`}</p>
      <form id="payment-form" onSubmit={handleSubmit}>
        <CardElement id="card-element" options={paymentElementOptions} />
        <button
          style={{ marginTop: `20px` }}
          disabled={isLoading || !stripe || !elements}
          id="submit"
        >
          <span id="button-text">
            {isLoading ? (
              <div className="spinner" id="spinner"></div>
            ) : (
              "Pay now"
            )}
          </span>
        </button>
        {/* Show any error or success messages */}
        {message && <div id="payment-message">{message}</div>}
      </form>
    </div>
  );
}
