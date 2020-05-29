package com.hrm.utils;

public class PageModel {
    //当前页码，页面大小
    private  int pageIndex;
    private int pageSize = 4;
    //总页数
    private  int totalSize;
    //总记录数
    private  int rocordCount;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        if (rocordCount%pageSize == 0){

            totalSize = rocordCount%pageSize;
        }else {

            totalSize = rocordCount%pageSize+1;
        }
        return totalSize;
    }

    /*public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }*/

    public int getRocordCount() {
        return rocordCount;
    }

    public void setRocordCount(int rocordCount) {
        this.rocordCount = rocordCount;
    }
    public  int getFirstLimitParam(){
       return  (this.getPageIndex()-1)*this.pageSize;
    }
}
