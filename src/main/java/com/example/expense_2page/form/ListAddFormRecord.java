package com.example.expense_2page.form;

import java.util.List;
import java.util.ArrayList;

public class ListAddFormRecord {// やばいリスト型のsetter/getterかけんかった
    
    private List<AddFormRecord> listRecords = new ArrayList<>();

    public List<AddFormRecord> getListRecords(){ return listRecords; }

    public void setListRecords(List<AddFormRecord> listRecords){ this.listRecords = listRecords; }

    }

