import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";

export default function UserForm(props) {
  const [validated, setValidated] = useState(false);
  const [firstName, setFirstName] = useState();
  const [lastName, setLastName] = useState();
  const [email, setEmail] = useState();
  const [phoneNo, setPhoneNo] = useState();

  const handleFormSubmit = (event) => {
    event.preventDefault();
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.stopPropagation();
    } else {
      // const params = {
      //   firstName: firstName,
      //   lastName: lastName,
      //   email: email,
      //   phoneNo: phoneNo,
      // };
      const params = {
        firstName: "dinesh",
        lastName: "vijay",
        email: "dinesh@mail.com",
        phoneNo: "9940191856",
      };
      props.makePay(params);
    }
    setValidated(true);
  };
  return (
    <div style={{ marginTop: "40px", marginBottom: "30px" }}>
      <div>
        <p className="title">{`Billing details`}</p>
      </div>
      <Form noValidate validated={validated} onSubmit={handleFormSubmit}>
        <Form.Group className="mb-3" controlId="validationCustom01">
          <Form.Label>First name</Form.Label>
          <Form.Control
            required
            value={"DINESH"}
            type="text"
            placeholder=""
            onChange={(e) => {
              setFirstName(e.target.value);
            }}
          />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Last name</Form.Label>
          <Form.Control
            required
            value={"VIJAY"}
            placeholder=""
            onChange={(e) => {
              setLastName(e.target.value);
            }}
          />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Email</Form.Label>
          <Form.Control
            required
            type="email"
            value={"abc@mail.com"}
            placeholder="abc@mail.com"
            onChange={(e) => {
              setEmail(e.target.value);
            }}
          />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Phone no</Form.Label>
          <Form.Control
            required
            type="tel"
            minLength={10}
            maxLength={10}
            value="99407446447"
            placeholder="99407446447"
            onChange={(e) => {
              setPhoneNo(e.target.value);
            }}
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          Make Payment
        </Button>
      </Form>
    </div>
  );
}
