package com.example.expense_2page.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.expense_2page.entity.ExpenseCategory;
import com.example.expense_2page.entity.ExpenseRecords;



@Mapper 
public interface ExpenseMapper {
    

    List<ExpenseRecords> findAll(@Param("order") String order, @Param("filterList") List<Integer> id);// 全件取得

    List<ExpenseCategory> findAllCategoryName();// 費目ID、費目名、費目Boolean、全取得

    Boolean getExpenseBool(Integer expense_id);

    void insertNewExpense(ExpenseCategory expenseCategory);

    void insert(ExpenseRecords expenseRecords);

    int update(ExpenseRecords expenseRecords);

    void delete(Integer record_id);

    
}
