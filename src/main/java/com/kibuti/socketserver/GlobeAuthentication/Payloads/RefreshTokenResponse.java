package com.kibuti.socketserver.GlobeAuthentication.Payloads;

import lombok.Data;

@Data
public class RefreshTokenResponse {
    String newToken;
}
