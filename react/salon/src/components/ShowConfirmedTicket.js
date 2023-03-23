import React from "react";
import { Row, Col } from "react-bootstrap";
import moment from "moment";
import { QRCodeCanvas } from "qrcode.react";
import { Service } from "../config/Service";

export default function ShowConfirmedTicket(props) {
  const { salonDetails } = props.data;
  const { id } = props.data?.ticket;
  const { salonServiceDetail, slot } = props.data?.ticket?.payment;
  return (
    <div style={{ padding: 10 }}>
      <p className="title">{`Your Ticket Details`}</p>
      <Row>
        <Col>
          <p className="ticketSubTitle">{" Service Details "}</p>
          <p className="description">
            {" "}
            {salonServiceDetail?.name} @{" "}
            {moment(slot?.slotFor).format("ddd MMM DD")} By {slot?.stylistName}
          </p>
          <hr></hr>
          <p style={{ fontWeight: "bolder" }}>{" Salon Address Details "}</p>
          <p className="ticketLabel">{salonDetails?.name}</p>
          <p className="ticketLabel">{salonDetails?.address}</p>
          <p className="ticketLabel">{salonDetails?.city}</p>
          <p className="ticketLabel">{salonDetails?.state}</p>
          <p className="ticketLabel">{`zip ${salonDetails?.zipcode}`}</p>
          <p className="ticketLabel">{`phone ${salonDetails?.phone}`}</p>
        </Col>
        <Col>
          <p className="name" style={{ fontWeight: "bolder" }}>
            {"Take a picture of the below code and present it to admin"}
          </p>
          <QRCodeCanvas value={`${Service.TICKETS_BASE_URL}/${id}`} />
        </Col>
      </Row>
    </div>
  );
}
