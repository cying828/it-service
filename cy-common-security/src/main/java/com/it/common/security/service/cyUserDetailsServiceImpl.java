package com.it.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.it.common.api.feign.RemoteUserService;
import com.it.common.api.model.system.SysUser;
import com.it.common.api.model.UserInfo;
import com.it.common.core.constant.CacheConstants;
import com.it.common.core.constant.CommonConstants;
import com.it.common.core.constant.SecurityConstants;
import com.it.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Cying
 * @Date 2022/6/29 19:01
 * @Description
 */
@Slf4j
@Service
@AllArgsConstructor
public class cyUserDetailsServiceImpl implements cyUserDetailsService {

    private final RemoteUserService remoteUserService;
    private final CacheManager cacheManager;


    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return (cyUser) cache.get(username).get();
        }

        R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
        UserDetails userDetails = getUserDetails(result);
        cache.put(username, userDetails);
        return userDetails;
    }


    /**
     * 构建数据
     */
    private UserDetails getUserDetails(R<UserInfo>  result) {
        if (result == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();
        boolean enabled = StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL);
        // 构造security用户
        return new cyUser(user.getUserId(),user.getRealname(),  user.getDeptId(),user.getOrgId(), user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(), enabled,
                true, true, !CommonConstants.STATUS_LOCK.equals(user.getLockFlag()), authorities);
    }

    @Override
    public UserDetails loadUserBySocial(String code) throws UsernameNotFoundException {
        return null;
    }
}
