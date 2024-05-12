package pers.nolan.webpos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deduction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long productId;

    private Integer quantity;

}
