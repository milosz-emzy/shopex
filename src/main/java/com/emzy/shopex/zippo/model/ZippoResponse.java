package com.emzy.shopex.zippo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ZippoResponse(
        @JsonProperty("post code")
        String postCode,
        String country,
        @JsonProperty("country abbreviation")
        String countryAbbreviation,
        List<Place> places
) {
}
