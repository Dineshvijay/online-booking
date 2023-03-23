import React, { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import moment from "moment";

import SlotCard from "../components/SlotCard";
import { Service } from "../config/Service";
import AppNotificationComponent from "../components/AppNotificationComponent";
import LoadingIndicator from "../components/LoadingIndicator";

export default function SlotsPage(props) {
  const params = useParams();
  const navigate = useNavigate();
  const [msgVariant, setMsgVariant] = useState(null);
  const [msg, setMsg] = useState(null);
  const [availableSlots, setAvailableSlots] = useState(null);
  const [startDate, setStartDate] = useState(new Date());
  const [showSpinner, setShowSpinner] = useState(false);
  useEffect(() => {}, []);
  const getAvailableSlots = async () => {
    setShowSpinner(true);
    const date = moment(startDate).format("yyyy-MM-DD");
    const URL = `${Service.BASE_URL}${Service.AVAILABLE_SLOTS}/${params.serviceId}/${date}`;
    fetch(URL)
      .then((res) => res.json())
      .then((data) => {
        setShowSpinner(false);
        if (data?.data?.length > 0) {
          setAvailableSlots(data?.data);
        } else {
          setMsgVariant("primary");
          setMsg("No slots found");
          setAvailableSlots(null);
        }
      })
      .catch((err) => {
        setShowSpinner(false);
        setMsgVariant("danger");
        setMsg(err.toString());
      });
  };

  const handleSelectSlot = (slot) => {
    navigate(`/makepayment/${slot?.salonServiceDetail?.id}/${slot?.id}`);
  };
  return (
    <>
      <Container
        style={{
          padding: 20,
          margin: "auto",
          width: "60%",
        }}
      >
        <Row>
          <Col>
            <p className="name">
              {`Choose a date for ${params?.serviceName}`}{" "}
            </p>
          </Col>
          <Col xs={6}>
            <DatePicker
              selected={startDate}
              onChange={(date) => setStartDate(date)}
            />
          </Col>
          <Col>
            <Button variant="primary" onClick={getAvailableSlots}>
              {`Show Slots`}{" "}
            </Button>
          </Col>
        </Row>
        <LoadingIndicator visible={showSpinner} />
        <AppNotificationComponent
          visible={msg != null ? true : false}
          variant={msgVariant}
          msg={msg}
          onClose={() => {
            setMsg(null);
          }}
        />
      </Container>

      <Container
        style={{
          margin: "auto",
          width: "60%",
        }}
      >
        {availableSlots && availableSlots?.length > 0 ? (
          <p className="slotTitle">{`Available slots on ${moment(
            startDate
          ).format("yyyy-MM-DD")}`}</p>
        ) : null}
        <Row className="justify-content-md-center">
          {availableSlots?.map((item) => {
            return (
              <Col md="auto" style={{ padding: "1rem" }}>
                <SlotCard
                  data={item}
                  serviceName={params?.serviceName}
                  onSelectSlot={handleSelectSlot}
                />
              </Col>
            );
          })}
        </Row>
      </Container>
    </>
  );
}
