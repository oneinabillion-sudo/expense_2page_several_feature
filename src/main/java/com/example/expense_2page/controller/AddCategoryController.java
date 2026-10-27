package com.example.expense_2page.controller;


import com.example.expense_2page.DTO.CategoryDto;
import com.example.expense_2page.service.ExpenseService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController  
public class AddCategoryController {
    
    // DI注入
    private final ExpenseService expenseService;
    public AddCategoryController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping("/category/add")
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        
        String name = categoryDto.getName() == null ? "" : categoryDto.getName().trim();
        Boolean bool = categoryDto.getBool();
        if(name.isEmpty()){
            // これはテスト用
            throw new IllegalArgumentException("費目名が空です！");
        }

        if(bool == null){
            throw new IllegalArgumentException("入金/出金が未選択です！");
        }

        // Serviceを読んで、採番されたIDを受け取る
        Integer newId = expenseService.insertNewExpense(name, bool);

        CategoryDto result = new CategoryDto();
        result.setId(newId);
        result.setName(name);
        result.setBool(bool);
    
        return result;
    }
    
}
