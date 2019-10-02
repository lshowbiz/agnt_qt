package ${basepackage}.service;

import ${appfusepackage}.service.GenericManager;
import ${basepackage}.model.${pojo.shortName};
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface ${pojo.shortName}Manager extends GenericManager<${pojo.shortName}, ${pojo.getJavaTypeName(pojo.identifierProperty, jdk5)}> {
    
	public Pager<${pojo.shortName}> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
}