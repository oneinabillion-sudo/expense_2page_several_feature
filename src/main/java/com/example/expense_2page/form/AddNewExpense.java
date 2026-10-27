package com.example.expense_2page.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class AddNewExpense {
    private Integer expense_id;

    @NotBlank (message = "新しい費目を入力してください")
    private String expense_name;
}
