import React, { useState } from "react";
import { Alert } from "react-bootstrap";

export default function AppNotificationComponent(props) {
  if (!props.visible) return null;
  return (
    <Alert
      show={props.visible}
      key={props.variant}
      variant={props.variant}
      onClose={props.onClose}
      dismissible
    >
      {props.msg}
    </Alert>
  );
}
