package com.nijunyang.codegen.model;
import java.io.Serializable;
import lombok.Data;

@Data
public class User implements Serializable{
    private Long id;
    private Long petId;
    private Integer quantity;
    private String shipDate;
    private Boolean complete;

}