package com.system.fsharksocialmedia.dtos;

import com.system.fsharksocialmedia.entities.Place;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Placetrip}
 */
@Data
public class PlacetripDto implements Serializable {
    Integer id;
    TripDto tripid;
    Instant datetime;
    @Size(max = 500)
    String note;
}