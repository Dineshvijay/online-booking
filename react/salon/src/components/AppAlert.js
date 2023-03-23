import React from "react";
import { Alert, Button, Col, Row } from "react-bootstrap";

export default function AppAlert(props) {
  if (!props.show) return null;
  return (
    <div
      style={{
        paddingTop: 30,
        width: "50%",
      }}
    >
      <Alert show={props.show} variant={props.variant}>
        <Alert.Heading>Stripe Payment</Alert.Heading>
        <p>{props.msg}</p>
        <hr />

        <Row>
          <Col>
            <Button onClick={props.onClickOk} variant="outline-danger">
              {props.ok}
            </Button>
          </Col>
          <Col>
            <Button onClick={props.onClickCancel} variant="outline-danger">
              {props.cancel}
            </Button>
          </Col>
        </Row>
      </Alert>
    </div>
  );
}
