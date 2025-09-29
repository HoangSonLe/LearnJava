package tech.core.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import tech.core.common.constants.RequestHeader;
import tech.core.common.models.RequestId;

import java.util.Objects;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ControllerDefault {

    public RequestId requestId(HttpServletRequest request){
        String headerRequestId = request.getHeader(RequestHeader.REQUEST_ID.getValue());
        return Objects.nonNull(headerRequestId) ? new RequestId(headerRequestId) : new RequestId(UUID.randomUUID().toString());
    }



}
