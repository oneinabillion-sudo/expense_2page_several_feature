package com.example.expense_2page.mapper;


import org.apache.ibatis.annotations.Mapper;
import java.util.List;



import com.example.expense_2page.entity.ExpenseCategory;



@Mapper 
public interface ExpenseCategoryMapper {
    
    List<ExpenseCategory> findAllCategoryName();// 費目ID、費目名、費目Boolean、全取得
    
    void insertNewExpense(ExpenseCategory expenseCategory);

    Boolean getExpenseBool(Integer expenseId);
}
