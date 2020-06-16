package org.dxworks.utils.java.rest.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Delegate;

@Data
@AllArgsConstructor
public class HttpResponse {
    @Delegate
    private com.google.api.client.http.HttpResponse response;
}
