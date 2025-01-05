package com.emzy.shopex.zippo;

import com.emzy.shopex.zippo.model.ZippoResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/pl")
public interface ZippoClient {

    @GetExchange(url = "{zipCode}")
    ZippoResponse getResponse(@PathVariable String zipCode);
}
