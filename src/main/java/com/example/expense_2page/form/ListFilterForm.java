package com.example.expense_2page.form;


import java.util.List;
import java.util.ArrayList;

import lombok.Data;


@Data 
public class ListFilterForm {

    private List<Integer> expenseIdList = new ArrayList<>();
    
}
