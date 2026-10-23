package com.example.expense_2page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.expense_2page.form.AddFormRecord;
import com.example.expense_2page.form.ListAddFormRecord;
import com.example.expense_2page.service.ExpenseService;
import org.springframework.web.bind.annotation.RequestParam;





@Controller 
public class ExpenseController {
    
    private final ExpenseService expenseservice;
    public ExpenseController(ExpenseService expenseservice){
        this.expenseservice = expenseservice;
    }

    @GetMapping("/manage")
    public String getTopPage(@RequestParam(defaultValue = "desc") String order, 
                             @ModelAttribute AddFormRecord addFormRecord, 
                              Model model) {
        System.out.println("リクエストが届きました");
                                
        model.addAttribute("Expense_RC", expenseservice.findAll(order));
        model.addAttribute("categores", expenseservice.findAllCategoryName());
        return "manage";
    }

    @GetMapping("/findAll")
    public String getOrderPage(@RequestParam(defaultValue = "desc") String order, 
                             @ModelAttribute AddFormRecord addFormRecord, 
                              Model model) {
        System.out.println("リクエストが届きました");
                                
        model.addAttribute("Expense_RC", expenseservice.findAll(order));
        model.addAttribute("categores", expenseservice.findAllCategoryName());
        return "manage";
    }
    
    

    // 編集画面へ遷移
    @GetMapping("/edit")
    public String getAddForm(@RequestParam(defaultValue = "desc") String order,
                             @ModelAttribute ListAddFormRecord listAddFormRecord, 
                             @ModelAttribute AddFormRecord addFormRecord, Model model) {
        model.addAttribute("Expense_RC", expenseservice.findAll(order));
        model.addAttribute("categores", expenseservice.findAllCategoryName());
        return "edit";
    }
    
    // 1レコード追加
    @PostMapping("/addRecord")
    public String addRecord(@ModelAttribute AddFormRecord addFormRecord) {
        
        System.out.println("リクエストが届きました！");
        expenseservice.insert(addFormRecord);
        return "redirect:/manage";
    }

    // 更新
    @PostMapping("/update")
    public String updateRecords(@ModelAttribute ListAddFormRecord listAddFormRecord, @ModelAttribute AddFormRecord addFormRecord) {
        
        System.out.println("正常にリクエストが届きました！");
        for(AddFormRecord e : listAddFormRecord.getListRecords()){
            System.out.println(e.getExpense_id());
            System.out.println(e.getMemo());   
        }
        
        expenseservice.update(listAddFormRecord);
        
        return "redirect:/edit";
    }
    
    
    // 1レコード文の削除ボタン押下後のハンドラーメソッド
    @PostMapping("/records/{record_id}/delete")
    public String recordDelete(@PathVariable Integer record_id) {
        
        System.out.println("正常にリクエストが届きました！");
        expenseservice.deleteRecord(record_id);
        return "redirect:/edit";
    }

}
