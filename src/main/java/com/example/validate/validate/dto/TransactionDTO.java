package com.example.validate.validate.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransactionDTO {
  @NotNull private String type;

  @NotNull private String iban;

  @NotNull private String description;

  @Min(value = 0L, message = "Sum must be positive!")
  @NotNull private Double sum;

  @NotNull private LocalDateTime dateTime;

  @NotNull private String name;

  @NotNull private Long cnp;
}
