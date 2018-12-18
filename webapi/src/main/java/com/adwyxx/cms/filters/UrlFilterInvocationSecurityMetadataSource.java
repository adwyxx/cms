package com.adwyxx.cms.filters;

import com.adwyxx.cms.entities.Menu;
import com.adwyxx.cms.entities.Role;
import com.adwyxx.cms.services.MenuService;
import com.adwyxx.cms.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @description: 实现FilterInvocationSecurityMetadataSource接口，通过权限控制访问URL路径
 * 有一个默认的实现类DefaultFilterInvocationSecurityMetadataSource该类的主要功能就是通过当前的请求地址，获取该地址需要的用户角色
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException
    {
        // 获取请求Url地址
        String url = ((FilterInvocation)o).getRequestUrl();
        // 如果是登陆路由则不进行权限认证
        if(url.equals("/lgoin"))
        {
            // 返回Null表示当前这个请求不需要任何角色就能访问，甚至不需要登录
            return null;
        }
        List<Menu> allMenu = menuService.getAll();
        // 遍历所有菜单，和当前请求的url匹配比对
        for(Menu menu:allMenu)
        {
            // 使用antPathMatcher.match方法匹配比对url
            // 涉及到一个优先级问题，比如我的地址是/employee/basic/hello,这个地址既能被/employee/**匹配，
            // 也能被/employee/basic/**匹配，这就要求我们从数据库查询的时候对数据进行排序，
            // 将/employee/basic/**类型的url pattern放在集合的前面去比较
            if(antPathMatcher.match(menu.getUrl(),url))
            {
                List<Role> roles = roleService.getRolesByMenuId(menu.getId());
                if(roles.size()>0)
                {
                    String[] values = new String[roles.size()];
                    for(int i=0;i<roles.size();i++)
                    {
                        values[i]=roles.get(i).getRoleName();
                    }
                    // 返回可以访问的url的角色集合
                    return SecurityConfig.createList(values);
                }
            }

        }

        // 若没有匹配上路径，则返回登陆页面，返回一个数据控中并不存在的角色ROLE_LOGIN，在下一步角色对比中根据这个特殊角色做处理
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
