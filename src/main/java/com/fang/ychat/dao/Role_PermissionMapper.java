package com.fang.ychat.dao;

import com.fang.ychat.pojo.Role_Permission;
import com.fang.ychat.pojo.Role_PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Role_PermissionMapper {
    int countByExample(Role_PermissionExample example);

    int deleteByExample(Role_PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role_Permission record);

    int insertSelective(Role_Permission record);

    List<Role_Permission> selectByExample(Role_PermissionExample example);

    Role_Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role_Permission record, @Param("example") Role_PermissionExample example);

    int updateByExample(@Param("record") Role_Permission record, @Param("example") Role_PermissionExample example);

    int updateByPrimaryKeySelective(Role_Permission record);

    int updateByPrimaryKey(Role_Permission record);
    int findPermissionId(int role_id);
}