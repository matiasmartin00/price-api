package com.zara.price.fixture;

import com.zara.price.controller.dto.ApiError;

public class ApiErrorFixture {

  public static ApiError notFound(String route) {
    return ApiError.builder()
        .status(404)
        .error("route_not_found")
        .message(String.format("Route %s not found", route))
        .build();
  }

  public static ApiError internalError() {
    return ApiError.builder()
        .message("Internal server error")
        .error("internal_error")
        .status(500)
        .build();
  }

  public static ApiError methodNotSupport(String method, String route) {
    return ApiError.builder()
        .message(String.format("Request method '%s' not supported. Route %s", method, route))
        .error("method_not_allowed")
        .status(405)
        .build();
  }

  public static ApiError missingParameter(String field) {
    return ApiError.builder()
        .status(400)
        .error("missing_parameter")
        .message(String.format("Missing required parameter '%s'", field))
        .build();
  }

  public static ApiError badRequest(String message) {
    return ApiError.builder()
        .status(400)
        .error("bad_request")
        .message(message)
        .build();
  }
}
