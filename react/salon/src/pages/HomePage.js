import React, { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

import { Service } from "../config/Service";
import AppNotificationComponent from "../components/AppNotificationComponent";
import SalonServiceCard from "../components/SalonServiceCard";
import LoadingIndicator from "../components/LoadingIndicator";
import { useNavigate } from "react-router-dom";

export default function HomePage(props) {
  const navigate = useNavigate();
  const [availableService, setAvailableService] = useState([]);
  const [msgVariant, setMsgVariant] = useState(null);
  const [msg, setMsg] = useState(null);
  const [showAlert, setShowAlert] = useState(false);
  const [showSpinner, setShowSpinner] = useState(false);
  useEffect(() => {
    getServices();
  }, []);

  const getServices = async () => {
    setShowSpinner(true);
    fetch(`${Service.BASE_URL}${Service.AVAILABLE_SERVICES}`)
      .then((res) => res.json())
      .then((data) => {
        const response = data?.data;
        setAvailableService(response);
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
                <SalonServiceCard
                  data={item}
                  selectedService={(item) =>
                    navigate(`chooseslot/${item.id}/${item.name}`)
                  }
                />
              </Col>
            );
          })}
        </Row>
      </Container>
    </>
  );
}
