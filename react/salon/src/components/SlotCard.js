import React from "react";
import { Card } from "react-bootstrap";
import Button from "react-bootstrap/Button";

export default function SlotCard(props) {
  return (
    <>
      <Card
        className="text-center"
        style={{ width: "15rem", height: "14rem", justifyContent: "center" }}
      >
        <Card.Header>{props.serviceName}</Card.Header>
        <Card.Body>
          <Card.Title style={{ fontSize: "30px", fontWeight: "bold" }}>
            {props.data?.stylistName}
          </Card.Title>
          <Card.Text>{`${props.data?.salonServiceDetail?.time} Mins`}</Card.Text>
          <Button variant="outline-primary">Book this Slot</Button>
        </Card.Body>
      </Card>
    </>
  );
}
