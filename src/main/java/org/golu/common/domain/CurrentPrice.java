package org.golu.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(description = "Represents price")
public class CurrentPrice {
    @ApiModelProperty(value = "Value of the price quote", required = true)
    @JsonProperty
    @NotNull
    private Double value;

    @ApiModelProperty(value = "Currency of the price quote", required = true)
    @JsonProperty("currency_code")
    @NotNull
    private String currencyCode;
}
