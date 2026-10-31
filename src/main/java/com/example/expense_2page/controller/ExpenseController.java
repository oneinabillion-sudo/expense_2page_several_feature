package com.example.expense_2page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.expense_2page.form.AddFormRecord;
import com.example.expense_2page.form.ListFilterForm;
import com.example.expense_2page.form.ListAddFormRecord;
import com.example.expense_2page.form.ListUpdateForm;
import com.example.expense_2page.form.UpdateForm;
import com.example.expense_2page.service.ExpenseService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /* -----------------ページ遷移時のハンドラーメソッド----------------------- */

    // URLアクセス時
    @GetMapping("/manage")
    public String getTopPage(@RequestParam(defaultValue = "desc") String order, ListFilterForm listFilterForm,
            @ModelAttribute AddFormRecord addFormRecord, Model model) {
        System.out.println("アクセス時のリクエストが届きました");

        model.addAttribute("listFilterForm", new ListFilterForm());
        model.addAttribute("Expense_RC", expenseService.findAll(order));// categoriesに修正
        model.addAttribute("categories", expenseService.findAllCategoryName());
        return "manage";
    }

    @GetMapping("/findAll")
    public String getOrderPage(@RequestParam(defaultValue = "desc") String order,
            @ModelAttribute AddFormRecord addFormRecord, ListFilterForm listFilterForm,
            Model model) {
        System.out.println("一覧表示のリクエストが届きました");

        model.addAttribute("Expense_RC", expenseService.findAll(order));
        model.addAttribute("categories", expenseService.findAllCategoryName());
        return "manage";
    }

    // 編集画面へ遷移
    @GetMapping("/edit")
    public String getAddForm(@RequestParam(defaultValue = "desc") String order,
            @ModelAttribute ListAddFormRecord listAddFormRecord,
            @ModelAttribute AddFormRecord addFormRecord, ListUpdateForm listUpdateForm, Model model) {
        model.addAttribute("Expense_RC", expenseService.findAll(order));
        model.addAttribute("categories", expenseService.findAllCategoryName());
        return "edit";
    }

    /* -------------------------------------------------------------------- */

    /* ---------------------------CRUD操作ハンドラーメソッド------------------------------ */
    // 1レコード追加
    @PostMapping("/addRecord")
    public String addRecord(@Validated @ModelAttribute AddFormRecord addFormRecord, BindingResult result,
            @RequestParam(defaultValue = "desc") String order, ListFilterForm listFilterForm,
            @ModelAttribute ListAddFormRecord listAddFormRecord, Model model) {

        if (result.hasErrors()) {
            System.out.println("バリデーションエラーが起きたよ");
            model.addAttribute("Expense_RC", expenseService.findAll(order));
            model.addAttribute("categories", expenseService.findAllCategoryName());

            return "manage";
        }

        expenseService.insert(addFormRecord);
        System.out.println("正常に処理がされたよ");
        model.addAttribute("Expense_RC", expenseService.findAll(order));
        model.addAttribute("categories", expenseService.findAllCategoryName());

        return "manage";
    }

    // 更新：（一括更新処理）
    @PostMapping("/update")
    public String updateRecords(@Validated @ModelAttribute ListUpdateForm listUpdateForm, BindingResult result,
            @RequestParam(defaultValue = "desc") String order, ListAddFormRecord listAddFormRecord,
            @ModelAttribute AddFormRecord addFormRecord, Model model) {

        System.out.println("/updateでリクエストを受け取りました!");

        if (result.hasErrors()) {// エラー時

            model.addAttribute("Expense_RC", expenseService.findAll(order));
            model.addAttribute("categories", expenseService.findAllCategoryName());

            System.out.println("Validationエラーが起きました！");

            return "edit";
        }

        expenseService.update(listUpdateForm);

        System.out.println("----------更新処理前の値----------");
        for (UpdateForm e : listUpdateForm.getListCheck()) {
            System.out.println(e.getExpense_id());
            System.out.println(e.getMemo());
        }
        System.out.println("---------------------------------");

        System.out.println("/updateで正常に処理されました！");

        model.addAttribute("Expense_RC", expenseService.findAll(order));
        model.addAttribute("categories", expenseService.findAllCategoryName());

        return "edit";

    }

    // 1レコード文の削除ボタン押下後のハンドラーメソッド
    @PostMapping("/records/{record_id}/delete")
    public String recordDelete(@PathVariable Integer record_id) {

        System.out.println("正常にリクエストが届きました！");
        expenseService.deleteRecord(record_id);
        return "redirect:/edit";
    }

    @GetMapping("/getAll")
    public String getAll(@ModelAttribute ListAddFormRecord listAddFormRecord,
            @ModelAttribute AddFormRecord addFormRecord, Model model) {

        model.addAttribute("data", expenseService.findAllCategoryName());
        return "confirm";
    }

    // 絞り込み＜費目名＞
    @GetMapping("/filter/expense")
    public String getFilterCategory(@ModelAttribute ListFilterForm listFilterForm, ListAddFormRecord listAddFormRecord,
            AddFormRecord addFormRecord,
            @RequestParam(defaultValue = "desc") String order, Model model) {

        System.out.println("絞り込みのリクエスト値を受け取りました！");

        model.addAttribute("Expense_RC", expenseService.getFilterCategory(order, listFilterForm));
        model.addAttribute("categories", expenseService.findAllCategoryName());

        return "manage";
    }

}
