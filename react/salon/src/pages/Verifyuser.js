import React, { useState } from "react";
import QrReader from "react-qr-reader";
import AppNotificationComponent from "../components/AppNotificationComponent";
import UserInformation from "../components/UserInformation";

export default function Verifyuser() {
  const [hideQR, setHideQR] = useState(false);
  const [apiError, setApiError] = useState(null);
  const [ticketDetails, setTicketDetails] = useState(null);

  const handleGetTicket = (url) => {
    if (ticketDetails) {
      return;
    }
    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        const status = data?.status;
        if (status?.code != 200) {
          setApiError(true);
        } else {
          setHideQR(true);
          setTicketDetails(data?.data);
        }
      });
  };

  const handleScan = (data) => {
    if (data) {
      handleGetTicket(data);
    }
  };
  const handleError = (err) => {
    console.error(err);
  };

  const handleScanAnother = () => {
    console.log(`handleScanAnother called`);
    setTicketDetails(null);
    setHideQR(false);
  };

  return (
    <div style={{ width: `35%` }}>
      <AppNotificationComponent
        visible={apiError ? true : false}
        variant={`danger`}
        msg={`Invalid QR Code`}
        onClose={() => {
          setApiError(null);
        }}
      />
      {!hideQR ? (
        <>
          <QrReader delay={300} onError={handleError} onScan={handleScan} />
        </>
      ) : null}
      {hideQR && ticketDetails != null ? (
        <UserInformation data={ticketDetails} scanAnother={handleScanAnother} />
      ) : null}
    </div>
  );
}
