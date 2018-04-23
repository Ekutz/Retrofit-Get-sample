package com.example.ekutz.retrofittest.response.GetParkInfo;

import java.util.List;

public class GetParkInfo {

    private int list_total_count;
    private Result_park RESULT;
    private List<RowData> row;

    public int getList_total_count() {
        return list_total_count;
    }

    public Result_park getRESULT() {
        return RESULT;
    }

    public List<RowData> getRow() {
        return row;
    }
}
