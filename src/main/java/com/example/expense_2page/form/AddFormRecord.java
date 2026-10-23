package com.example.expense_2page.form;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AddFormRecord{
    private Integer record_id;
    private Boolean depoORdraw;
    private Integer price;
    private LocalDate date;
    private Integer expense_id;
    private String memo;
}