package com.example.expense_2page.entity;

import lombok.Data;
import java.time.LocalDate;
@Data 
public class ExpenseRecords {
    private Integer record_id;
    private LocalDate record_date;
    private Integer expense_id;
    private String expense_name;// これは親テーブルの値（このフィールドを置いて
    private String memo;//         やらないと費目の名前がJava側で取得できない）
    private Integer deposit;
    private Integer withdraw;
}
