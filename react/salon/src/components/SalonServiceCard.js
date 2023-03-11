import React from "react";
import { Card } from "react-bootstrap";
import Button from "react-bootstrap/Button";

export default function SalonServiceCard(props) {
  return (
    <>
      <Card
        className="text-center"
        style={{ width: "16rem", height: "14rem", justifyContent: "center" }}
      >
        <Card.Header className="cardTitle">{props.data.name}</Card.Header>
        <Card.Body>
          <Card.Title className="price">{`$${props.data.price}`}</Card.Title>
          <Card.Text className="description">
            {props.data.description}
          </Card.Text>
          <Card.Text className="description">{`${props.data.time} mins`}</Card.Text>
          <Button
            variant="outline-primary"
            onClick={() => {
              props.selectedService(props.data);
            }}
          >
            Book Now
          </Button>
        </Card.Body>
      </Card>
    </>
  );
}
