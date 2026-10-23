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
        
        String name = categoryDto.getName().trim();

        if(name.isEmpty()){
            // これはテスト用
            throw new IllegalArgumentException("費目名が空です！");
        }

        // Serviceを読んで、採番されたIDを受け取る
        Integer newId = expenseService.insertNewExpense(name);

        CategoryDto result = new CategoryDto();
        result.setId(newId);
        result.setName(name);
    
        return result;
    }
    
}
