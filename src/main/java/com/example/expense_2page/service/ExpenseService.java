package com.example.expense_2page.service;

import org.springframework.stereotype.Service;

import com.example.expense_2page.entity.ExpenseCategory;
import com.example.expense_2page.entity.ExpenseRecords;
import com.example.expense_2page.form.AddFormRecord;
import com.example.expense_2page.form.ListAddFormRecord;
import com.example.expense_2page.form.ListUpdateForm;
import com.example.expense_2page.form.UpdateForm;
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

    // 追加フォームページに出力する費目名データを返す+ expense_categoryの全件取得
    public List<ExpenseCategory> findAllCategoryName(){
        return expenseMapper.findAllCategoryName();
    }

    // 新規費目追記
    public Integer insertNewExpense(String name, Boolean bool){
        
        ExpenseCategory newExpense = new ExpenseCategory();
        newExpense.setExpense_name(name);
        newExpense.setExpense_bool(bool);
        expenseMapper.insertNewExpense(newExpense);

        return newExpense.getExpense_id();
    }

    // 追加　（INSERT）
    public void insert(AddFormRecord addFormRecord){
  
            System.out.println("Serviceでリクエストが届いたよ。");

            ExpenseRecords records = new ExpenseRecords();
            records.setRecord_date(addFormRecord.getDate());
            Integer expense_ID = Integer.parseInt(addFormRecord.getExpense_id());
            records.setExpense_id(expense_ID);/*
            １．ここでexpense_idからcategoryのbooleanを取得（expense_bool）
            ２．ほんでBoolean dORw = expenseMapper.getBool();でこのexpense_idは1なのか0なのか

            問題１expense_idをもとにexpense_boolを取得するSQLを新しく定義する必要がある。
            dORwがtrueだったら、違ったら↓の処理でやる！
            */

            Boolean expenseBool = expenseMapper.getExpenseBool(expense_ID);

            System.out.println("真偽値は：" + expenseBool + "だよ");

            records.setMemo(addFormRecord.getMemo());

            if(expenseBool){// trueだったら入金処理
                records.setDeposit(addFormRecord.getPrice());
            }else{// falseだったら出金処理
                records.setWithdraw(addFormRecord.getPrice());
            }
            expenseMapper.insert(records);
    }
    // 一括更新
    public void update(ListUpdateForm listUpdateForm){
        for(UpdateForm record : listUpdateForm.getListCheck()){
            ExpenseRecords entity = new ExpenseRecords();

            entity.setRecord_id(record.getRecord_id());

            Integer expense_ID = Integer.parseInt(record.getExpense_id());
            entity.setExpense_id(expense_ID);
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
