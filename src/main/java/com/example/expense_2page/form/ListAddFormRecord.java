package com.example.expense_2page.form;

import java.util.List;

import jakarta.validation.Valid;

import java.util.ArrayList;

@Valid
public class ListAddFormRecord {// やばいリスト型のsetter/getterかけんかった
    
    private List<AddFormRecord> listRecords = new ArrayList<>();

    public void setListRecords(List<AddFormRecord> listRecords){ this.listRecords = listRecords; }

    public List<AddFormRecord> getListRecords(){ return listRecords; }


    }

