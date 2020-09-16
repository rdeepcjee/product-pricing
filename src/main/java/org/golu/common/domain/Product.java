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
@ApiModel(description = "Represents a product")
public class Product {
    @ApiModelProperty(value = "The ID of the product", required = true)
    @JsonProperty
    @NotNull
    private Integer id;

    @ApiModelProperty(value = "The name of the product", required = true)
    @JsonProperty
    @NotNull
    private String name;

    @ApiModelProperty(value = "The name of the company that creates the product", required = true)
    @JsonProperty
    private String company;
}
