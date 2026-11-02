package com.example.expense_2page.service;

import org.springframework.stereotype.Service;

import com.example.expense_2page.entity.ExpenseCategory;
import com.example.expense_2page.entity.ExpenseRecord;
import com.example.expense_2page.form.AddFormRecord;
import com.example.expense_2page.form.ListFilterForm;
import com.example.expense_2page.form.ListUpdateForm;
import com.example.expense_2page.form.UpdateForm;
import com.example.expense_2page.mapper.ExpenseCategoryMapper;
import com.example.expense_2page.mapper.ExpenseRecordMapper;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Service 
@RequiredArgsConstructor 
public class ExpenseService {
    


    // DI注入
    private final ExpenseRecordMapper recordMapper;

    private final ExpenseCategoryMapper categoryMapper;

    // 全件取得　（SELECT findAll）
    public List<ExpenseRecord> findRecord(String order){
        String safeOrder = "asc".equalsIgnoreCase(order) ? "asc" : "desc";
        ListFilterForm filterValues = new ListFilterForm();

        System.out.println("Service"+filterValues.getRecordKeyword());
        return recordMapper.findRecord(safeOrder, new ArrayList<>(), null, null);
    }

    // 追加フォームページに出力する費目名データを返す+ expense_categoryの全件取得
    public List<ExpenseCategory> findAllCategoryName(){
        return categoryMapper.findAllCategoryName();
    }

    // 新規費目追記
    public Integer insertNewExpense(String name, Boolean bool){
        
        ExpenseCategory newExpense = new ExpenseCategory();
        newExpense.setExpenseName(name);
        newExpense.setExpenseBool(bool);
        categoryMapper.insertNewExpense(newExpense);

        return newExpense.getExpenseId();
    }

    // 追加　（INSERT）
    public void insert(AddFormRecord addFormRecord){
  
            System.out.println("Serviceでリクエストが届いたよ。");

            ExpenseRecord records = new ExpenseRecord();
            records.setRecordDate(addFormRecord.getDate());
            Integer expenseID = Integer.parseInt(addFormRecord.getExpenseId());
            records.setExpenseId(expenseID);/*
            １．ここでexpense_idからcategoryのbooleanを取得（expense_bool）
            ２．ほんでBoolean dORw = expenseMapper.getBool();でこのexpense_idは1なのか0なのか

            問題１expense_idをもとにexpense_boolを取得するSQLを新しく定義する必要がある。
            dORwがtrueだったら、違ったら↓の処理でやる！
            */

            Boolean expenseBool = categoryMapper.getExpenseBool(expenseID);

            System.out.println("真偽値は：" + expenseBool + "だよ");

            records.setMemo(addFormRecord.getMemo());

            if(expenseBool){// trueだったら入金処理
                records.setDeposit(addFormRecord.getPrice());
            }else{// falseだったら出金処理
                records.setWithdraw(addFormRecord.getPrice());
            }
            recordMapper.insert(records);
    }
    // 一括更新
    public void update(ListUpdateForm listUpdateForm){
        for(UpdateForm record : listUpdateForm.getListCheck()){
            ExpenseRecord entity = new ExpenseRecord();

            entity.setRecordId(record.getRecordId());

            Integer expenseID = Integer.parseInt(record.getExpenseId());
            entity.setExpenseId(expenseID);
            entity.setMemo(record.getMemo());

            
            int count = recordMapper.update(entity);
            System.out.println("更新件数: " + count);
            
        }

    }

    // 1行削除
    public void deleteRecord(Integer recordId){
        recordMapper.delete(recordId);
    }

    // 費目の絞り込み検索
    public List<ExpenseRecord> getFilterRecord(String order, ListFilterForm listFilterForm){
        

        String safeOrder = "asc".equalsIgnoreCase(order) ? "asc" : "desc";

        System.out.println("検索ワードは:" + listFilterForm.getRecordKeyword() + "です");
        
        for(Integer d : listFilterForm.getExpenseIdList()){
            System.out.println(d);
        }

        return recordMapper.findRecord(safeOrder, listFilterForm.getExpenseIdList(), listFilterForm.getPeriod(), listFilterForm.getRecordKeyword());

        
        
    }

    


}
