import React from "react";
import { Row, Col, Button } from "react-bootstrap";
import moment from "moment";

export default function UserInformation(props) {
  const { payment } = props.data?.ticket;
  const { salonServiceDetail, slot } = props.data?.ticket?.payment;
  return (
    <div style={{ padding: 10 }}>
      <p className="title">{`Details`}</p>
      <Row>
        <Col>
          <p className="ticketSubTitle">{" Service Details "}</p>
          <p className="description">
            {" "}
            {salonServiceDetail?.name} @{" "}
            {moment(slot?.slotFor).format("ddd MMM DD")} By {slot?.stylistName}
          </p>
          <hr></hr>
          <p style={{ fontWeight: "bolder" }}>{"User Information"}</p>
          <p className="ticketLabel">{`${payment?.firstName} ${payment.lastName}`}</p>
          <p className="ticketLabel">{payment?.email}</p>
          <p className="ticketLabel">{payment?.phoneNo}</p>
        </Col>
        <Col>
          <Button
            style={{ width: "55%" }}
            variant="primary"
            onClick={props.scanAnother}
          >{`scan another`}</Button>
        </Col>
      </Row>
    </div>
  );
}
