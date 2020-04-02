package com.example.demo.config.constants;

import lombok.Getter;

public enum ExceptionCode {

  DEFAULT(-1, "No Description Available"),
  SUCCESS(0, "Success"),
  LAST4_NOT_MATCHING(2, "The last 4 digits of your card are not matching"),
  OTP_CODE_NOT_MATCHING(3, "The OTP Code is incorrect"),
  OTP_TRANSACTIONID_INCORRECT(4, "The OTP Transaction ID is incorrect"),
  OTP_ALREADY_USED(5, "This OTP has already been used"),
  OTP_EXPIRED(6, "This OTP has expired"),
  OTP_LIMIT_REACHED(7, "OTP try limit reached"),
  PHONE_NUMBER_ALREADY_EXISTS(8, "Phone number already exists in thenData system"),
  DTO_ELEMENT_NOT_PRESENT(9, "Input DTO element is empty"),
  COUNTRY_CODE_NOT_PRESENT(10, "Country code is empty"),
  PHONE_NUMBER_NOT_PRESENT(11, "Phone number is empty"),
  OTP_PURPOSE_NOT_PRESENT(12, "OTP Purpose is empty"),
  ACTIVE_USER_ALREADY_EXISTS(13, "An active user already exists in the system"),
  CUSTOMER_NOT_PRESENT(14, "Customer object is empty"),
  GENDER_NOT_PRESENT(15, "Gender is empty"),
  DOB_NOT_PRESENT(16, "Date of birth is empty"),
  PROGRAM_NOT_PRESENT(21, "Customer.Program is sempty"),
  PHONE_NUMBER_COUNTRY_CODE_NOT_PRESENT(22, "Customer.Phone.Code is empty"),
  PHONE_NUMBER_INVALID(23, "Phone number is invalid"),
  PHONE_NUMBER_COUNTRY_CODE_INVALID(24, "Customer.Phone.Code is invalid"),
  EMAIL_ID_INVALID(25, "Email ID is invalid"),
  EMAIL_ID_NOT_PRESENT(26, "Email ID is not present"),
  DB_VALUE_PRESENT(27, "The given value is present in the DB"),
  EMAIL_ID_ALREADY_REGISTERED(36, "The email ID is already registered in the system"),
  OTP_CODE_NOT_PRESENT(37, "The OTP verification code is empty"),
  LAST4_NOT_PRESENT(38, "The last 4 digits of your card are empty"),
  CUSTOMER_NOT_ACTIVE(39, "The customer is not active"),
  PIN_INCORRECT(40, "The pin (password) is incorrect"),
  PIN_NOT_PRESENT(41, "The pin (password) is not present"),
  API_MOVED(51, "API has moved to different location please update your app");

  @Getter
  int statusCode = -1;

  @Getter
  String desc;

  ExceptionCode(int statusCode, String desc) {
    this.statusCode = statusCode;
    this.desc = desc;
  }

  public static ExceptionCode getByStatusCode(int statusCode) {
    for (ExceptionCode e : ExceptionCode.values()) {
      if (e.statusCode == statusCode) {
        return e;
      }
    }
    return null;
  }

}
