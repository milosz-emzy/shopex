package com.emzy.shopex.zippo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Place(
        @JsonProperty("place name")
        String placeName,
        String longitude,
        String state,
        @JsonProperty("state abbreviation")
        String stateAbbreviation,
        String latitude
) {
}
