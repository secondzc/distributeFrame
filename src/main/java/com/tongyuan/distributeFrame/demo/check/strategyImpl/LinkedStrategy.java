package com.tongyuan.distributeFrame.demo.check.strategyImpl;

import com.tongyuan.distributeFrame.demo.check.FlowStrategy;
import com.tongyuan.distributeFrame.demo.check.enums.RoleEnum;
import com.tongyuan.distributeFrame.util.DataUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhangcy on 2018/3/16
 * 按照热力学-电子-物理-机械工程师角色顺序排列的审签流程
 */
public class LinkedStrategy extends AbstractStrategy {

    private RoleEnum[] roleEnums = new RoleEnum[]{
            RoleEnum.DrawingEngineer,
            RoleEnum.ElectricEngineer,
            RoleEnum.PhsicalEngineer,
            RoleEnum.MachineEngineer
    };


    @Override
    public String getFirstRoleName() {
        return roleEnums[0].getEngineerRole();
    }

    @Override
    public String getNextRoleName(String roleName) {
        for(int i=0;i<roleEnums.length;i++){
            if(StringUtils.equals(roleEnums[i].getEngineerRole(),roleName) && i<roleEnums.length-1){
                return roleEnums[i+1].getEngineerRole();
            }
        }
        return null;
    }
}
