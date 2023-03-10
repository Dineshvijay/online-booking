import React from "react";
import { Spinner } from "react-bootstrap";

export default function LoadingIndicator(props) {
  if (!props.visible) return null;
  return <Spinner animation="border" />;
}
