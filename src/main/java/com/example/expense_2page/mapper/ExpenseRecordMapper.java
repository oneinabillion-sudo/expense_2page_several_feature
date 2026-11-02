package com.example.expense_2page.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.example.expense_2page.entity.ExpenseRecords;



@Mapper 
public interface ExpenseRecordMapper {
    

    List<ExpenseRecords> findRecord(@Param("order") String order, @Param("filterList") List<Integer> id,
                            @Param("period") String period, @Param("keyword") String keyword);

    void insert(ExpenseRecords expenseRecords);

    int update(ExpenseRecords expenseRecords);

    void delete(Integer recordId);

    
}
