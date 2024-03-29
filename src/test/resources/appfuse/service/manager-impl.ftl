<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
package ${basepackage}.service.impl;

import ${basepackage}.dao.${pojo.shortName}Dao;
import ${basepackage}.model.${pojo.shortName};
import ${basepackage}.service.${pojo.shortName}Manager;
import ${appfusepackage}.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("${pojoNameLower}Manager")
@WebService(serviceName = "${pojo.shortName}Service", endpointInterface = "${basepackage}.service.${pojo.shortName}Manager")
public class ${pojo.shortName}ManagerImpl extends GenericManagerImpl<${pojo.shortName}, ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)}> implements ${pojo.shortName}Manager {
    ${pojo.shortName}Dao ${pojoNameLower}Dao;

    @Autowired
    public ${pojo.shortName}ManagerImpl(${pojo.shortName}Dao ${pojoNameLower}Dao) {
        super(${pojoNameLower}Dao);
        this.${pojoNameLower}Dao = ${pojoNameLower}Dao;
    }
	
	public Pager<${pojo.shortName}> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return ${pojoNameLower}Dao.getPager(${pojo.shortName}.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}