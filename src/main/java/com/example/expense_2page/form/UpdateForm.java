package com.example.expense_2page.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class UpdateForm {

    private Integer record_id;

    private String expense_id;


    @NotNull(message = "内容を入力してください")
    @NotBlank(message = "内容を入力してください")
    private String memo;

}
