package com.hrm.job.dao;

import com.hrm.commons.beans.Job;
import com.hrm.utils.PageModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface IJobDao {

    @SelectProvider(type = JobProvider.class, method = "selectJob")
        //传递为多个参数时
        List<Job> selectJob(@Param("name") String name,@Param("pageModel") PageModel pgeModel);
    //List<Job> selectJob(Map map);

    @SelectProvider(type = JobProvider.class, method = "selectJobCount" )
    int selectJobCount(String name);

    @Select("select * from job_inf where id = #{id}")
    Job selectJobById(int id);

    @Update("update job_inf set name=#{name},remark=#{remark} where id = #{id}")
    int updateJob(Job job);

    @DeleteProvider(type = JobProvider.class,method = "deleteJob")
    int deleteJob(@Param("ids") Integer[] ids);

    @Insert("insert into job_inf(name,remark) values(#{name},#{remark})")
    int insertJob(Job job);
}
