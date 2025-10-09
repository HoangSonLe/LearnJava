package tech.core.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
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
public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    public CustomAuthenticationEntryPoint() {
    }

    public RequestId requestUID(HttpServletRequest request) {
        String headerRequestId = request.getHeader(RequestHeader.REQUEST_ID.getValue());
        return Objects.nonNull(headerRequestId) ? new RequestId(headerRequestId) : new RequestId(UUID.randomUUID().toString());
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException {
        response.addHeader("WWW-Authenticate", "");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        String errorMsg = CoreErrorCodes.SYSTEM_AUTHORIZATION.getMessage();
        if (authEx instanceof OAuth2AuthenticationException) {
            OAuth2Error error = ((OAuth2AuthenticationException)authEx).getError();
            String authorization = request.getHeader("Authorization");
            authorization = Objects.isNull(authorization) ? "" : authorization;
            String var10000 = error.getDescription();
            errorMsg = var10000 + " " + authorization;
        }

        ApiErrorResponse apiResponse = new ApiErrorResponse(this.requestUID(request).value(), CoreErrorCodes.SYSTEM_AUTHORIZATION.getCode(), errorMsg, request.getRequestURI(), request.getMethod(), LocalDateTime.now());
        response.setHeader("X-SERVICE", Objects.isNull(SystemUtils.getHostName()) ? "DEV" : SystemUtils.getHostName());
        writer.println(JsonHelper.toJson(apiResponse));
    }

    public void afterPropertiesSet() {
        this.setRealmName("OTS");
        super.afterPropertiesSet();
    }
}