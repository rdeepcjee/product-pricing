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
@ApiModel(description = "Represents the current price quote for a product")
public class CurrentProductPriceQuote {
    @ApiModelProperty(value = "The ID of the product", required = true)
    @JsonProperty
    @NotNull
    private Integer id;

    @ApiModelProperty(value = "The name of the product", required = true)
    @JsonProperty
    @NotNull
    private String name;

    @ApiModelProperty(value = "Current price of the product", required = true)
    @JsonProperty("current_price")
    @NotNull
    private CurrentPrice currentPrice;
}
