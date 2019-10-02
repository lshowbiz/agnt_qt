package com.joymain.ng.util.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Spring调用Oracle存储过程封装,使用范例见PmRamlDaoHibernate.java
 * @author Aidy.Q
 * 
 */
public class SpringStoredProcedure extends StoredProcedure {

	// public ArrayList<HashMap> set = new ArrayList<HashMap>();
	public ArrayList set = new ArrayList();
	// 声明一个用于接收结果集的数据结构,其中的元素为row,用map存放

	private Map inParam;// 输入参数
	private RowMapper rm = new RowMapper() {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			return null;// 不用从存储过程本身获取结果
		}
	};

	private RowMapperResultSetExtractor callback = new RowMapperResultSetExtractor(rm) {
		public void processRow(ResultSet rs) // 回调处理
		        throws SQLException {
			int count = rs.getMetaData().getColumnCount();
			String[] header = new String[count];
			for (int i = 0; i < count; i++)
				header[i] = rs.getMetaData().getColumnName(i + 1);
			while (rs.next()) {
				// HashMap<String,String> row = new HashMap(count+7);
				HashMap row = new HashMap(count + 7);
				for (int i = 0; i < count; i++)
					row.put((String) header[i], (String) rs.getString(i + 1));
				set.add(row);
			}
		}
	}; // RowMapperResultReader作为输出参数的回调句柄

	public SpringStoredProcedure(DataSource ds, String SQL, boolean isFunction) {
		setDataSource(ds);
		setSql(SQL);
		setFunction(isFunction);
	}
	
	public SpringStoredProcedure(JdbcTemplate jdbcTemplate, String SQL) {
		setJdbcTemplate(jdbcTemplate);
		setSql(SQL);
	}
	
	public SpringStoredProcedure(JdbcTemplate jdbcTemplate, String SQL, boolean isFunction) {
		setJdbcTemplate(jdbcTemplate);
		setSql(SQL);
		setFunction(isFunction);
	}

	public void setOutParameter(String column, int type) {
		declareParameter(new SqlOutParameter(column, type, callback));
		// 利用回调句柄注册输出参数
	}

	public void setParameter(String column, int type) {
		declareParameter(new SqlParameter(column, type));
	}

	public void SetInParam(Map inParam) {
		this.inParam = inParam;
	}

	public Map execute() {
		compile();
		return execute(this.inParam);
	}
}
