import React, { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import ChooseService from "../components/ChooseService";

export default function HomePage(props) {
  const [availableService, setAvailableService] = useState([]);
  const [error, setError] = useState(null);
  useEffect(() => {
    getServices();
  }, []);

  const getServices = async () => {
    fetch(
      "http://192.168.101.2:8080/api/services/retrieveAvailableSalonServices"
    )
      .then((res) => res.json())
      .then((data) => setAvailableService(data))
      .catch((err) => {});
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
