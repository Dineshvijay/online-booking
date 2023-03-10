import React, { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

import { Service } from "../config/Service";
import AppNotificationComponent from "../components/AppNotificationComponent";
import ChooseService from "../components/ChooseService";
import LoadingIndicator from "../components/LoadingIndicator";

export default function HomePage(props) {
  const [availableService, setAvailableService] = useState([]);
  const [msgVariant, setMsgVariant] = useState(null);
  const [msg, setMsg] = useState(null);
  const [showAlert, setShowAlert] = useState(false);
  const [showSpinner, setShowSpinner] = useState(true);
  useEffect(() => {
    getServices();
  }, []);

  const getServices = async () => {
    fetch(`${Service.BASE_URL}${Service.AVAILABLE_SERVICES}`)
      .then((res) => res.json())
      .then((data) => {
        setAvailableService(data);
        setShowSpinner(false);
        setShowAlert(true);
        setMsg("Success!!");
        setMsgVariant("success");
      })
      .catch((err) => {
        setShowSpinner(false);
        setMsgVariant("danger");
        setShowAlert(true);
        setMsg(err.toString());
      });
  };
  return (
    <>
      <div className="header-bar">
        <p className="header-bar-text">Ar Salon & Day Spa</p>

        <Container
          style={{
            margin: "auto",
            width: "60%",
          }}
        >
          <LoadingIndicator visible={showSpinner} />
          <AppNotificationComponent
            visible={msg ? true : false}
            variant={msgVariant}
            msg={msg}
            onClose={() => {
              setMsg(null);
            }}
          />
          <Row className="justify-content-md-center">
            {availableService.map((item) => {
              return (
                <Col md="auto" style={{ padding: "1rem" }}>
                  <ChooseService data={item} />
                </Col>
              );
            })}
          </Row>
        </Container>
      </div>
    </>
  );
}
