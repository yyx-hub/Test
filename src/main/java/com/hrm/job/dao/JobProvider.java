package com.hrm.job.dao;

import com.hrm.utils.PageModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class JobProvider {
//查询
    public  String selectJob(@Param("name") final String name, @Param("pageModel")PageModel pageModel){
        String sql = new SQL(){
            {
                this.SELECT("*");
                this.FROM("job_inf");
                if (name != null && !"".equals(name)){
                    this.WHERE("name like '%' #{name} '%'");
                }
           //   PageModel pageModel = (PageModel) map.get("pageModel");
                //我是3.5.2，我可以用this.limit方法
                //this.LIMIT("#{pageModel.firstLimitParam},#{pageModel.pageSize}");
            }
        }.toString();
        //当mybatis版本较低使用字符串拼接
        sql = sql + " limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
        return sql;
    }
//查询记录数
    public String selectJobCount(final String name){
        //select count(*) from job_inf where name like '%' #{name} '%'
        String sql = new SQL(){
            {
                this.SELECT("count(*)");
                this.FROM("job_inf");
                if(name != null && !"".equals(name)){
                    this.WHERE("name like '%' #{name} '%'");
                }
            }
        }.toString();
        return sql;
    }
//删除
    public String deleteJob(@Param("ids") Integer[] ids){
        //delete from job_inf where id in (id,id,id)
        StringBuffer sql = new StringBuffer();
        sql.append("delete from job_inf where id in(");
        for (int id:ids){
            sql.append(id+",");
        }
        //减去最后一个逗号
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        return sql.toString();
    }
}
