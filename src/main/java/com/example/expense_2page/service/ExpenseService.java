package com.example.expense_2page.service;

import org.springframework.stereotype.Service;

import com.example.expense_2page.entity.ExpenseCategory;
import com.example.expense_2page.entity.ExpenseRecords;
import com.example.expense_2page.form.AddFormRecord;
import com.example.expense_2page.form.ListAddFormRecord;
import com.example.expense_2page.mapper.ExpenseMapper;

import java.util.List;


@Service 
public class ExpenseService {
    
    // DI注入
    private final ExpenseMapper expenseMapper;
    public ExpenseService(ExpenseMapper expenseMapper){
        this.expenseMapper = expenseMapper;
    }

    // 全件取得　（SELECT findAll）
    public List<ExpenseRecords> findAll(String order){
        String safeOrder = "asc".equalsIgnoreCase(order) ? "asc" : "desc";
        return expenseMapper.findAll(safeOrder);
    }

    // 追加フォームページに出力する費目名データを返す
    public List<ExpenseCategory> findAllCategoryName(){
        return expenseMapper.findAllCategoryName();
    }

    // 新規費目追記
    public Integer insertNewExpense(String name){
        
        ExpenseCategory newExpense = new ExpenseCategory();
        newExpense.setExpense_name(name);
        expenseMapper.insertNewExpense(newExpense);

        return newExpense.getExpense_id();
    }

    // 追加　（INSERT）
    public void insert(AddFormRecord addFormRecord){
  
            ExpenseRecords records = new ExpenseRecords();
            records.setRecord_date(addFormRecord.getDate());
            records.setExpense_id(addFormRecord.getExpense_id());
            records.setMemo(addFormRecord.getMemo());

            if(addFormRecord.getDepoORdraw()){// trueだったら入金処理
                records.setDeposit(addFormRecord.getPrice());
            }else{// falseだったら出金処理
                records.setWithdraw(addFormRecord.getPrice());
            }
            expenseMapper.insert(records);
    }
    // 一括更新
    public void update(ListAddFormRecord listAddFormRecord){
        for(AddFormRecord record : listAddFormRecord.getListRecords()){
            ExpenseRecords entity = new ExpenseRecords();

            entity.setRecord_id(record.getRecord_id());
            entity.setExpense_id(record.getExpense_id());
            entity.setMemo(record.getMemo());

            
            int count = expenseMapper.update(entity);
            System.out.println("更新件数: " + count);
            
        }

    }

    // 1行削除
    public void deleteRecord(Integer record_id){
        expenseMapper.delete(record_id);
    }

}
