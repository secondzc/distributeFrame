package com.tongyuan.distributeFrame.demo.check.strategyImpl;

import com.tongyuan.distributeFrame.demo.check.FlowStrategy;
import com.tongyuan.distributeFrame.demo.check.enums.RoleEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhangcy on 2018/3/17
 * 物理、接下来是机械
 */
public class MaLastStrategy extends AbstractStrategy{
    @Override
    public String getFirstRoleName() {
        return RoleEnum.PhsicalEngineer.getEngineerRole();
    }

    @Override
    public String getNextRoleName(String roleName) {
        if(StringUtils.equals(RoleEnum.PhsicalEngineer.getEngineerRole(),roleName)){
            return RoleEnum.MachineEngineer.getEngineerRole();
        }
        return null;
    }
}
