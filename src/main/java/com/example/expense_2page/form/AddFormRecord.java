package com.example.expense_2page.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddFormRecord{
    private Integer recordId;
    private Boolean depoORdraw;

    @NotNull(message = "金額を入力してください")
    private Integer price;

    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "日付を選択してください")
    private LocalDate date;
    
    private String expenseId;

    @NotNull(message = "内容を入力してください")
    @NotBlank(message = "内容を入力してください")
    private String memo;
}