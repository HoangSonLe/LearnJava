package tech.core.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tech.core.common.constants.CoreErrorCodes;
import tech.core.common.constants.RequestHeader;
import tech.core.common.models.ApiErrorResponse;
import tech.core.common.models.RequestId;
import tech.core.utils.JsonHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    public CustomAccessDeniedHandler() {
    }

    public RequestId requestUID(HttpServletRequest request) {
        String headerRequestId = request.getHeader(RequestHeader.REQUEST_ID.getValue());
        return Objects.nonNull(headerRequestId) ? new RequestId(headerRequestId) : new RequestId(UUID.randomUUID().toString());
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        ApiErrorResponse apiResponse = new ApiErrorResponse(this.requestUID(request).value(), CoreErrorCodes.FORBIDDEN.getCode(), CoreErrorCodes.FORBIDDEN.getMessage(), request.getRequestURI(), request.getMethod(), LocalDateTime.now());
        response.setHeader("X-SERVICE-ID", Objects.isNull(SystemUtils.getHostName()) ? "DEV" : SystemUtils.getHostName());
        writer.println(JsonHelper.toJson(apiResponse));
    }
}