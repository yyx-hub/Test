package com.hrm.dept.dao;

import com.hrm.commons.beans.Dept;
import com.hrm.utils.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDeptDao {
    List<Dept> selectDept(@Param("name") String name,@Param("pageModel") PageModel pageModel);

    int selectDeptCount(String name);

    Dept selectDeptById(int id);

    int updateDept(Dept dept);

    int deleteDept(Integer[] ids);

    int insertDept(Dept dept);
}
