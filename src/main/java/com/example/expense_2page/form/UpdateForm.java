package com.example.expense_2page.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class UpdateForm {

    private Integer recordId;

    private String expenseId;


    
    @NotBlank(message = "内容を入力してください")
    private String memo;

}
