package com.hrm.dept.service;

import com.hrm.commons.beans.Dept;
import com.hrm.utils.PageModel;

import java.util.List;

public interface IDeptService {
    List<Dept> findDept(String name, PageModel pageModel);

    int findDeptCount(String name);

    Dept findDeptById(int id);

    int modifyDept(Dept dept);

    int removeDept(Integer[] ids);

    int addDept(Dept dept);
}
