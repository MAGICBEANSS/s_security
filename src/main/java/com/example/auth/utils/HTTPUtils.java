package com.example.auth.utils;

import com.example.auth.exceptions.*;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


@Slf4j
@UtilityClass
public class HTTPUtils {

  public static boolean isValidURL(final String url) {
    try {
      new URL(url).toURI();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static HttpStatus getHttpStatusCodeBasedOnException(Exception ex) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    if (ex instanceof NotModifiedException) {
      log.info(ex.getMessage(), ex);
      return HttpStatus.NOT_MODIFIED;
    }
    if (ex instanceof ValidationFailedException
        ) {
      log.warn(ex.getMessage(), ex);
      return HttpStatus.UNAUTHORIZED;
    }
    if (ex instanceof UserNotFoundException
    ) {
      log.warn(ex.getMessage(), ex);
      return HttpStatus.FORBIDDEN;
    }
    if (ex instanceof XfireClientException
        || ex instanceof IllegalStateException
        || ex instanceof IllegalArgumentException
        || ex instanceof ResourceNotFoundException
        || ex instanceof CustomParameterizedException
    ) {
      log.info(ex.getMessage(), ex);
      return HttpStatus.BAD_REQUEST;
    }
    log.error(ex.getMessage(), ex);
    return status;
  }


  public static List<Converter<?, ?>> getConverters() {
    List<Converter<?, ?>> converters = new ArrayList<>();
    converters.add(JSR310DateConverters.DateToZonedDateTimeConverter.INSTANCE);
    converters.add(JSR310DateConverters.ZonedDateTimeToDateConverter.INSTANCE);
    converters.add(JSR310DateConverters.DateToLocalDateConverter.INSTANCE);
    converters.add(JSR310DateConverters.LocalDateToDateConverter.INSTANCE);
    converters.add(JSR310DateConverters.DateToLocalDateTimeConverter.INSTANCE);
    converters.add(JSR310DateConverters.LocalDateTimeToDateConverter.INSTANCE);
    return converters;
  }

  public static boolean isValidInput(String input) {
    return !input.matches("[0-9a-zA-Z]+");
  }

  public static InputStream getInputStream(int responseCode, HttpsURLConnection con) throws IOException {
    InputStream inputStream = null;
    if (responseCode == HttpURLConnection.HTTP_OK) {
      inputStream = con.getInputStream();
    } else {
      inputStream = con.getErrorStream();
    }
    return inputStream;
  }

  public static HttpHeaders getContentTypeHeaderWithAllHeaders(HttpServletResponse response, String headerValue) {
    HttpHeaders headers = new HttpHeaders();
    for (String key : response.getHeaderNames()) {
      headers.add(key, response.getHeader(key));
    }
    if (StringUtils.isEmpty(headerValue)) {
      headerValue = "application/vnd.ms-excel";
    }
    headers.add("Content-Type", headerValue);
    return headers;
  }

  /**
   * Returns current HttpServletRequest if available else return null.
   *
   * @return
   */
  public static HttpServletRequest getHttpServletRequest() {
    return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
        .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes).getRequest())
        .orElse(null);
  }

  /**
   * Returns request header from current HttpServletRequest.
   *
   * @param ignoreHeaderList Pass a list of header names that need to be ignored.
   * @return map of request headers if HttpServletRequest is available else empty map.
   */
  public static Map<String, String> getRequestHeaders(List<String> ignoreHeaderList) {
    HttpServletRequest request = getHttpServletRequest();
    Map<String, String> map = new HashMap<>();
    if (request != null) {
      Enumeration headerNames = request.getHeaderNames();
      if (headerNames != null) {
        while (headerNames.hasMoreElements()) {
          populate(ignoreHeaderList, request, map, headerNames);
        }
      }
    }
    return map;
  }

  private static void populate(List<String> ignoreHeaderList, HttpServletRequest
      request, Map<String, String> map, Enumeration headerNames) {
    String key = (String) headerNames.nextElement();
    String value = request.getHeader(key);
    if (ignoreHeaderList != null) {
      if (!ignoreHeaderList.contains(key)) {
        map.put(key, value);
      }
    } else {
      map.put(key, value);
    }
  }

  /**
   * Returns request header from current HttpServletRequest.
   *
   * @return map of request headers if HttpServletRequest is available else empty map.
   */
  public static Map<String, String> getRequestHeaders() {
    HttpServletRequest request = getHttpServletRequest();
    Map<String, String> map = new HashMap<>();
    if (request != null) {
      Enumeration headerNames = request.getHeaderNames();
      if (headerNames != null) {
        while (headerNames.hasMoreElements()) {
          String key = (String) headerNames.nextElement();
          String value = request.getHeader(key);
          map.put(key, value);
        }
      }
    }
    return map;
  }
}
