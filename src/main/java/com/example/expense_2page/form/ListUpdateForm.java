package com.example.expense_2page.form;

import java.util.List;
import java.util.ArrayList;

import jakarta.validation.Valid;
public class ListUpdateForm {
    
    private List<@Valid UpdateForm> listCheck = new ArrayList<>();

    public void setListCheck(List<UpdateForm> listCheck){ this.listCheck = listCheck; }

    public List<UpdateForm> getListCheck(){ return listCheck; }

}
