package com.jyx.jiaoshoujia.mapper;



import com.jyx.jiaoshoujia.enity.po.LogRecords;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogOperationMapper {

    void insertOperationLog(LogRecords logRecords);


}
