package com.example.expense_2page.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.example.expense_2page.entity.ExpenseRecord;



@Mapper 
public interface ExpenseRecordMapper {
    

    List<ExpenseRecord> findRecord(@Param("order") String order, @Param("filterList") List<Integer> id,
                            @Param("period") String period, @Param("keyword") String keyword);

    void insert(ExpenseRecord expenseRecord);

    int update(ExpenseRecord expenseRecord);

    void delete(Integer recordId);

    
}
