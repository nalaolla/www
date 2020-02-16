package com.nalaolla.www.restApi.model.social;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RetKakaoAuth {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;

}
