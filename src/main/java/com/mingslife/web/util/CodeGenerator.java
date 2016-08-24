package com.mingslife.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CodeGenerator {
	protected static class ClassField {
		private String name;
		private String columnName;
		private String jdbcType;
		private String parameterType;
		private Class<?> parameterClass;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public String getJdbcType() {
			return jdbcType;
		}

		public void setJdbcType(String jdbcType) {
			this.jdbcType = jdbcType;
		}

		public String getParameterType() {
			return parameterType;
		}

		public void setParameterType(String parameterType) {
			this.parameterType = parameterType;
		}

		public Class<?> getParameterClass() {
			return parameterClass;
		}

		public void setParameterClass(Class<?> parameterClass) {
			this.parameterClass = parameterClass;
		}

		private String getColumnName(String name) {
			StringBuilder result = new StringBuilder(name.length());
			for (int i = 0, length = name.length(); i < length; i++) {
				char currentCharacter = name.charAt(i);
				if (Character.isUpperCase(currentCharacter)) {
					result.append("_" + Character.toLowerCase(currentCharacter));
				} else {
					result.append(currentCharacter);
				}
			}
			return result.toString();
		}
		
		private String getJdbcType(Class<?> type) {
			if (type.equals(String.class)) {
				return "VARCHAR";
			} else if (type.equals(Integer.class)) {
				return "INTEGER";
			} else if (type.equals(Boolean.class)) {
				return "BIT";
			} else if (type.equals(Date.class)) {
				return "TIMESTAMP";
			} else {
				return "VARCHAR";
			}
		}
		
		public ClassField(Field field) {
			String name = field.getName();
			Class<?> type = field.getType();
			this.name = name;
			this.columnName = getColumnName(name);
			this.jdbcType = getJdbcType(type);
			this.parameterType = field.getType().getName();
			this.parameterClass = field.getClass();
		}
		
		@Override
		public String toString() {
			return "[" + name + ": " + columnName + "(" + jdbcType + ")]";
		}
	}
	
	protected static class Generator {
		private static final String CRLF = "\r\n";
		
		private String projectPath;
		private String javaPath;
		private String modelPackage;
		private String mappingPackage;
		private String daoPackage;
		private String servicePackage;
		private String serviceImplPackage;
		private String controllerPackage;
		private String targetClassName;
		private String tableName;
		private String entityId;
		private String recordName;
		
		private Class<?> targetClass;
		private List<ClassField> classFields;
		private ClassField idField;
		
		public boolean generateMapperFile() {
			int classFieldsSize = classFields.size();
			
			StringBuilder result = new StringBuilder();
			
			result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + CRLF);
			result.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">" + CRLF);
			result.append("<mapper namespace=\"" + daoPackage + "." + targetClassName + "Mapper\">" + CRLF);
			result.append("  <resultMap id=\"BaseResultMap\" type=\"" + modelPackage + "." + targetClassName + "\">" + CRLF);
			result.append("    <id column=\"" + idField.getColumnName()  + "\" property=\"" + idField.getName() + "\" jdbcType=\"" + idField.getJdbcType() + "\" />" + CRLF);
			for (ClassField classField : classFields) {
				result.append("    <result column=\"" + classField.getColumnName() + "\" property=\"" + classField.getName() + "\" jdbcType=\"" + classField.getJdbcType() + "\" />" + CRLF);
			}
			result.append("  </resultMap>" + CRLF);
			result.append("  <sql id=\"BaseColumnList\">" + CRLF);
			result.append("    " + idField.getColumnName() + ", ");
			for (int i = 0; i < classFieldsSize; i++) {
				ClassField classField = classFields.get(i);
				String columnName = classField.getColumnName();
				result.append(columnName + ", ");
				if ((i + 2) % 6 == 0) {
					result.setLength(result.length() - 1);
					result.append(CRLF);
					result.append("    ");
				}
			}
			result.setLength(result.length() - 2);
			result.append(CRLF);
			result.append("  </sql>" + CRLF);
			result.append("  <select id=\"selectByPrimaryKey\" resultMap=\"ResultMapWithBLOBs\" parameterType=\"" + idField.getParameterType() + "\">" + CRLF);
			result.append("    select" + CRLF);
			result.append("      <include refid=\"BaseColumnList\" />" + CRLF);
			result.append("    from " + tableName + CRLF);
			result.append("  </select>" + CRLF);
			result.append("  <delete id=\"deleteByPrimaryKey\" parameterType=\"" + idField.getParameterType() + "\">" + CRLF);
			result.append("    delete from " + tableName + CRLF);
			result.append("    where " + idField.getColumnName() + " = #{" + idField.getName() + ",jdbcType=" + idField.getJdbcType() + "}" + CRLF);
			result.append("  </delete>" + CRLF);
			result.append("  <insert id=\"insert\" parameterType=\"" + targetClass.getName() + "\" useGeneratedKeys=\"true\" keyProperty=\"" + idField.getName() + "\">" + CRLF);
			result.append("    insert into " + tableName + " (" + CRLF);
			result.append("      " + idField.getColumnName() + ", ");
			for (int i = 0; i < classFieldsSize; i++) {
				ClassField classField = classFields.get(i);
				String columnName = classField.getColumnName();
				result.append(columnName + ", ");
				if ((i + 2) % 3 == 0) {
					result.setLength(result.length() - 1);
					result.append(CRLF);
					result.append("      ");
				}
			}
			result.setLength(result.length() - 2);
			result.append(CRLF);
			result.append("    ) values (" + CRLF);
			result.append("      #{" + idField.getName() + ",jdbcType=" + idField.getJdbcType() + "}, ");
			for (int i = 0; i < classFieldsSize; i++) {
				ClassField classField = classFields.get(i);
				String name = classField.getName();
				String jdbcType = classField.getJdbcType();
				result.append("#{" + name + ",jdbcType=" + jdbcType + "}, ");
				if ((i + 2) % 3 == 0) {
					result.setLength(result.length() - 1);
					result.append(CRLF);
					result.append("      ");
				}
			}
			result.setLength(result.length() - 2);
			result.append(CRLF);
			result.append("    )" + CRLF);
			result.append("  </insert>" + CRLF);
			result.append("  <insert id=\"insertSelective\" parameterType=\"" + targetClass.getName() + "\">" + CRLF);
			result.append("    insert into " + tableName + CRLF);
			result.append("    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">" + CRLF);
			result.append("      <if test=\"" + idField.getName() + " != null\">" + CRLF);
			result.append("        " + idField.getColumnName() + "," + CRLF);
			result.append("      </if>" + CRLF);
			for (ClassField classField : classFields) {
				result.append("      <if test=\"" + classField.getName() + " != null\">" + CRLF);
				result.append("        " + classField.getColumnName() + "," + CRLF);
				result.append("      </if>" + CRLF);
			}
			result.append("    </trim>" + CRLF);
			result.append("    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">" + CRLF);
			result.append("      <if test=\"" + idField.getName() + " != null\">" + CRLF);
			result.append("        #{" + idField.getName() + ",jdbcType=" + idField.getJdbcType() + "}," + CRLF);
			result.append("      </if>" + CRLF);
			for (ClassField classField : classFields) {
				result.append("      <if test=\"" + classField.getName() + " != null\">" + CRLF);
				result.append("        #{" + classField.getName() + ",jdbcType=" + classField.getJdbcType() + "}," + CRLF);
				result.append("      </if>" + CRLF);
			}
			result.append("    </trim>" + CRLF);
			result.append("  </insert>" + CRLF);
			result.append("  <update id=\"updateByPrimaryKeySelective\" parameterType=\"" + targetClass.getName() + "\">" + CRLF);
			result.append("    update " + tableName + CRLF);
			for (ClassField classField : classFields) {
				result.append("    <set>" + CRLF);
				result.append("      <if test=\"uuid != null\">" + CRLF);
				result.append("        " + classField.getColumnName() + " = #{" + classField.getName() + ",jdbcType=" + classField.getJdbcType() + "}," + CRLF);
				result.append("      </if>" + CRLF);
				result.append("    </set>" + CRLF);
			}
			result.append("    where " + idField.getColumnName() + " = #{" + idField.getName() + ",jdbcType=" + idField.getJdbcType() + "}" + CRLF);
			result.append("  </update>" + CRLF);
			result.append("  <update id=\"updateByPrimaryKey\" parameterType=\"" + targetClass.getName() + "\">" + CRLF);
			result.append("    update " + tableName + CRLF);
			result.append("    set");
			for (ClassField classField : classFields) {
				result.append(CRLF + "      " + classField.getColumnName() + " = #{" + classField.getName() + ",jdbcType=" + classField.getJdbcType() + "},");
			}
			result.setLength(result.length() - 1);
			result.append(CRLF);
			result.append("    where " + idField.getColumnName() + " = #{" + idField.getName() + ",jdbcType=" + idField.getJdbcType() + "}" + CRLF);
			result.append("  </update>" + CRLF);
			result.append("  <select id=\"select\" resultMap=\"BaseResultMap\">" + CRLF);
			result.append("    select" + CRLF);
			result.append("    <if test=\"parameters != null\">" + CRLF);
			result.append("      ${parameters}" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("    <if test=\"parameters == null\">" + CRLF);
			result.append("      *" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("    from " + tableName + CRLF);
			result.append("    <if test=\"condition != null\">" + CRLF);
			result.append("      where ${condition}" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("    <if test=\"order != null and sort != null\">" + CRLF);
			result.append("      order by ${order} ${sort}" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("    <if test=\"offset != -1 and limit != -1\">" + CRLF);
			result.append("      limit #{offset,jdbcType=INTEGER}, #{limit,jdbcType=INTEGER}" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("  </select>" + CRLF);
			result.append("  <select id=\"count\" resultType=\"long\">" + CRLF);
			result.append("    select count(" + CRLF);
			result.append("    <if test=\"isDistinct == true\">" + CRLF);
			result.append("      distinct" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("    <if test=\"parameters != null\">" + CRLF);
			result.append("      ${parameters}" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("    <if test=\"parameters == null\">" + CRLF);
			result.append("      *" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("    ) from " + tableName + CRLF);
			result.append("    <if test=\"condition != null\">" + CRLF);
			result.append("      where ${condition}" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("  </select>" + CRLF);
			result.append("  <select id=\"sum\" resultType=\"double\">" + CRLF);
			result.append("    select sum(" + CRLF);
			result.append("    <if test=\"isDistinct == true\">" + CRLF);
			result.append("      distinct" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("    ${parameter}) from " + tableName + CRLF);
			result.append("    <if test=\"condition != null\">" + CRLF);
			result.append("      where ${condition}" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("    <if test=\"order != null and sort != null\">" + CRLF);
			result.append("      order by ${order} ${sort}" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("    <if test=\"offset != -1 and limit != -1\">" + CRLF);
			result.append("      limit #{offset,jdbcType=INTEGER}, #{limit,jdbcType=INTEGER}" + CRLF);
			result.append("    </if>" + CRLF);
			result.append("  </select>" + CRLF);
			result.append("  <select id=\"find\" resultMap=\"ResultMapWithBLOBs\" parameterType=\"java.lang.Integer\">" + CRLF);
			result.append("    select ${parameters} from " + tableName + " where id = #{id,jdbcType=INTEGER}" + CRLF);
			result.append("  </select>" + CRLF);
			result.append("</mapper>" + CRLF);
			
			System.out.println(result);
			
//			File file = new File(projectPath + "/" + javaPath);
			File file = new File("F:/test" + "/" + javaPath + "/" + mappingPackage.replace('.', '/') + "/" + targetClassName + "Mapper.xml");
			try {
				OutputStream out = new FileOutputStream(file);
				out.write(result.toString().getBytes());
				out.flush();
				out.close();
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean generateDaoFile() {
			StringBuilder result = new StringBuilder();
			
			result.append("package " + daoPackage + ";" + CRLF);
			result.append(CRLF);
			result.append("import java.util.List;" + CRLF);
			result.append(CRLF);
			result.append("import org.apache.ibatis.annotations.Param;" + CRLF);
			result.append(CRLF);
			result.append("import " + targetClass.getName() + ";" + CRLF);
			result.append(CRLF);
			result.append("public interface " + targetClassName + "Mapper {" + CRLF);
			result.append("\tint deleteByPrimaryKey(" + idField.getParameterClass().getSimpleName() + " " + idField.getName() + ");" + CRLF);
			result.append(CRLF);
			result.append("\tint insert(" + targetClassName + " record);" + CRLF);
			result.append(CRLF);
			result.append("\tint insertSelective(" + targetClassName + " record);" + CRLF);
			result.append(CRLF);
			result.append("\t" + targetClassName + " selectByPrimaryKey(" + idField.getParameterClass().getSimpleName() + " " + idField.getName() + ");" + CRLF);
			result.append(CRLF);
			result.append("\tint updateByPrimaryKeySelective(" + targetClassName + " record);" + CRLF);
			result.append(CRLF);
			result.append("\tint updateByPrimaryKey(" + targetClassName + " record);" + CRLF);
			result.append(CRLF);
			result.append("\tList<" + targetClassName + "> select(@Param(\"parameters\") String parameters, @Param(\"condition\") String condition, @Param(\"order\") String order, @Param(\"sort\") String sort, @Param(\"offset\") int offset, @Param(\"limit\") int limit);" + CRLF);
			result.append(CRLF);
			result.append("\tlong count(@Param(\"parameters\") String parameters, @Param(\"condition\") String condition, @Param(\"isDistinct\") boolean isDistinct);" + CRLF);
			result.append(CRLF);
			result.append("\tdouble sum(@Param(\"parameter\") String parameter, @Param(\"condition\") String condition, @Param(\"order\") String order, @Param(\"sort\") String sort, @Param(\"offset\") int offset, @Param(\"limit\") int limit, @Param(\"isDistinct\") boolean isDistinct);" + CRLF);
			result.append(CRLF);
			result.append("\t" + targetClassName + " find(@Param(\"id\") Integer id, @Param(\"parameters\") String parameters);" + CRLF);
			result.append("}" + CRLF);
			
			System.out.println(result);
			
//			File file = new File(projectPath + "/" + javaPath);
			File file = new File("F:/test" + "/" + javaPath + "/" + daoPackage.replace('.', '/') + "/" + targetClassName + "Mapper.java");
			try {
				OutputStream out = new FileOutputStream(file);
				out.write(result.toString().getBytes());
				out.flush();
				out.close();
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean generateServiceFile() {
			StringBuilder result = new StringBuilder();
			
			result.append("package " + servicePackage + ";" + CRLF);
			result.append(CRLF);
			result.append("import java.util.List;" + CRLF);
			result.append(CRLF);
			result.append("import " + targetClass.getName() + ";" + CRLF);
			result.append(CRLF);
			result.append("public interface I" + targetClassName + "Service {" + CRLF);
			result.append("\tvoid save(" + targetClassName + " " + recordName + ");" + CRLF);
			result.append("\tvoid update(" + targetClassName + " " + recordName + ");" + CRLF);
			result.append("\tvoid delete(" + targetClassName + " " + recordName + ");" + CRLF);
			result.append("\tvoid delete(Integer id);" + CRLF);
			result.append("\t" + targetClassName + " find(Integer id);" + CRLF);
			result.append("\t" + targetClassName + " find(Integer id, String[] parameters);" + CRLF);
			result.append("\tList<" + targetClassName + "> load();" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String[] parameters);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String condition, Object[] values);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String[] parameters, String condition, Object[] values);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(int curPage, int limit);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String[] parameters, int curPage, int limit);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String condition, Object[] values, int curPage, int limit);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String[] parameters, String condition, Object[] values, int curPage, int limit);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String condition, Object[] values, String order, String sort);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String[] parameters, String condition, Object[] values, String order, String sort);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String order, String sort, int curPage, int limit);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String[] parameters, String order, String sort, int curPage, int limit);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String condition, Object[] values, String order, String sort, int curPage, int limit);" + CRLF);
			result.append("\tList<" + targetClassName + "> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);" + CRLF);
			result.append("\tlong count();" + CRLF);
			result.append("\tlong count(String condition, Object[] values);" + CRLF);
			result.append("\tlong count(String[] parameters, boolean isDistinct);" + CRLF);
			result.append("\tlong count(String[] parameters, String condition, Object[] values, boolean isDistinct);" + CRLF);
			result.append("\tdouble sum(String parameter);" + CRLF);
			result.append("\tdouble sum(String parameter, String condition, Object[] values);" + CRLF);
			result.append("\tdouble sum(String parameter, String order, String sort, int curPage, int limit);" + CRLF);
			result.append("\tdouble sum(String parameter, String condition, Object[] values, int curPage, int limit);" + CRLF);
			result.append("\tdouble sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit);" + CRLF);
			result.append("\tdouble sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit, boolean isDistinct);" + CRLF);
			result.append("}" + CRLF);
			
			System.out.println(result);
			
//			File file = new File(projectPath + "/" + javaPath);
			File file = new File("F:/test" + "/" + javaPath + "/" + servicePackage.replace('.', '/') + "/I" + targetClassName + "Service.java");
			try {
				OutputStream out = new FileOutputStream(file);
				out.write(result.toString().getBytes());
				out.flush();
				out.close();
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean generateServiceImplFile() {
			StringBuilder result = new StringBuilder();
			
			result.append("package " + serviceImplPackage + ";" + CRLF);
			result.append(CRLF);
			result.append("import java.util.List;" + CRLF);
			result.append(CRLF);
			result.append("import org.springframework.beans.factory.annotation.Autowired;" + CRLF);
			result.append("import org.springframework.stereotype.Service;" + CRLF);
			result.append(CRLF);
			result.append("import " + daoPackage + "." + targetClassName + "Mapper;" + CRLF);
			result.append("import " + targetClass.getName() + ";" + CRLF);
			result.append("import " + servicePackage + ".I" + targetClassName + "Service;" + CRLF);
			result.append("import com.mingslife.web.util.SQLUtil;" + CRLF);
			result.append(CRLF);
			result.append("@Service" + CRLF);
			result.append("public class " + targetClassName + "Service implements I" + targetClassName + "Service {" + CRLF);
			result.append("\t@Autowired" + CRLF);
			result.append("\tprivate " + targetClassName + "Mapper " + recordName + "Mapper;" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic void save(" + targetClassName + " " + recordName + ") {" + CRLF);
			result.append("\t\t" + recordName + "Mapper.insert(" + recordName + ");" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic void update(" + targetClassName + " " + recordName + ") {" + CRLF);
			result.append("\t\t" + recordName + "Mapper.updateByPrimaryKeySelective(" + recordName + ");" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic void delete(" + targetClassName + " " + recordName + ") {" + CRLF);
			result.append("\t\t" + recordName + "Mapper.deleteByPrimaryKey(" + recordName + ".getId());" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic void delete(Integer id) {" + CRLF);
			result.append("\t\t" + recordName + "Mapper.deleteByPrimaryKey(id);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic " + targetClassName + " find(Integer id) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.selectByPrimaryKey(id);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic " + targetClassName + " find(Integer id, String[] parameters) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.find(id, SQLUtil.formatParameters(parameters));" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load() {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(null, null, null, null, -1, -1);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String[] parameters) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(SQLUtil.formatParameters(parameters), null, null, null, -1, -1);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String condition, Object[] values) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(null, SQLUtil.fillCondition(condition, values), null, null, -1, -1);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String[] parameters, String condition, Object[] values) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, -1, -1);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(null, null, null, null, SQLUtil.getOffset(curPage, limit), limit);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String[] parameters, int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(SQLUtil.formatParameters(parameters), null, null, null, SQLUtil.getOffset(curPage, limit), limit);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String condition, Object[] values, int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(null, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String[] parameters, String condition, Object[] values, int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String condition, Object[] values, String order, String sort) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, -1, -1);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String[] parameters, String condition, Object[] values, String order, String sort) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, -1, -1);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String order, String sort, int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(null, null, order, sort, SQLUtil.getOffset(curPage, limit), limit);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String[] parameters, String order, String sort, int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(SQLUtil.formatParameters(parameters), null, order, sort, SQLUtil.getOffset(curPage, limit), limit);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String condition, Object[] values, String order, String sort, int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic List<" + targetClassName + "> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic long count() {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.count(null, null, false);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic long count(String condition, Object[] values) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.count(null, SQLUtil.fillCondition(condition, values), false);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic long count(String[] parameters, boolean isDistinct) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.count(SQLUtil.formatParameters(parameters), null, isDistinct);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic long count(String[] parameters, String condition, Object[] values, boolean isDistinct) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.count(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), isDistinct);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic double sum(String parameter) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.sum(parameter, null, null, null, -1, -1, false);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic double sum(String parameter, String condition, Object[] values) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, -1, -1, false);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic double sum(String parameter, String order, String sort, int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.sum(parameter, null, order, sort, SQLUtil.getOffset(curPage, limit), limit, false);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic double sum(String parameter, String condition, Object[] values, int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit, false);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, false);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@Override" + CRLF);
			result.append("\tpublic double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit, boolean isDistinct) {" + CRLF);
			result.append("\t\treturn " + recordName + "Mapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, isDistinct);" + CRLF);
			result.append("\t}" + CRLF);
			result.append("}" + CRLF);
			
			System.out.println(result);
			
//			File file = new File(projectPath + "/" + javaPath);
			File file = new File("F:/test" + "/" + javaPath + "/" + serviceImplPackage.replace('.', '/') + "/" + targetClassName + "Seervice.java");
			try {
				OutputStream out = new FileOutputStream(file);
				out.write(result.toString().getBytes());
				out.flush();
				out.close();
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean generateControllerFile() {
			StringBuilder result = new StringBuilder();
			
			result.append("package " + controllerPackage + ";" + CRLF);
			result.append(CRLF);
			result.append("import java.util.List;" + CRLF);
			result.append(CRLF);
			result.append("import javax.validation.Valid;" + CRLF);
			result.append(CRLF);
			result.append("import org.springframework.beans.factory.annotation.Autowired;" + CRLF);
			result.append("import org.springframework.stereotype.Controller;" + CRLF);
			result.append("import org.springframework.ui.Model;" + CRLF);
			result.append("import org.springframework.web.bind.annotation.ModelAttribute;" + CRLF);
			result.append("import org.springframework.web.bind.annotation.RequestMapping;" + CRLF);
			result.append("import org.springframework.web.bind.annotation.RequestMethod;" + CRLF);
			result.append("import org.springframework.web.bind.annotation.RequestParam;" + CRLF);
			result.append("import org.springframework.web.bind.annotation.ResponseBody;" + CRLF);
			result.append(CRLF);
			result.append("import com.mingslife.dto.CodeTableDTO;" + CRLF); // TODO
			result.append("import " + targetClass.getName() + ";" + CRLF);
			result.append("import " + servicePackage + ".I" + targetClassName + "Service;" + CRLF);
			result.append("import com.mingslife.web.controller.BaseController;" + CRLF);
			result.append(CRLF);
			result.append("@Controller" + CRLF);
			result.append("@RequestMapping(\"/" + tableName + "\")" + CRLF);
			result.append("public class " + targetClassName + "Controller extends BaseController {" + CRLF);
			result.append("\tpublic String index(@RequestParam(value = \"page\", required = false) Integer curPage, Model model) {" + CRLF);
			result.append("\t\treturn \"index\";" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@ResponseBody" + CRLF);
			result.append("\t@RequestMapping(value = \"\", method = RequestMethod.POST)" + CRLF);
			result.append("\tpublic String create(@Valid @ModelAttribute CodeTableDTO codeTableDTO) {" + CRLF);
			result.append("\t\tcodeTableService.save(codeTableDTO.toModel());" + CRLF);
			result.append("\t\treturn \"{}\";" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@ResponseBody" + CRLF);
			result.append("\t@RequestMapping(value = \"\", method = RequestMethod.GET)" + CRLF);
			result.append("\tpublic String show(@RequestParam(\"id\") int id) {" + CRLF);
			result.append("\t\tCodeTable codeTable = codeTableService.find(id, new String[] {\"id\"});" + CRLF);
			result.append("\t\treturn gson.toJson(codeTable);" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@ResponseBody" + CRLF);
			result.append("\t@RequestMapping(value = \"\", method = RequestMethod.PUT)" + CRLF);
			result.append("\tpublic String update(@Valid @ModelAttribute CodeTableDTO codeTableDTO, Model model) {" + CRLF);
			result.append("\t\tcodeTableService.update(codeTableDTO.toModel());" + CRLF);
			result.append("\t\treturn \"{}\";" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@ResponseBody" + CRLF);
			result.append("\t@RequestMapping(value = \"\", method = RequestMethod.DELETE)" + CRLF);
			result.append("\tpublic String destory(@RequestParam(\"id\") int id) {" + CRLF);
			result.append("\t\tcodeTableService.delete(id);" + CRLF);
			result.append("\t\treturn \"{}\";" + CRLF);
			result.append("\t}" + CRLF);
			result.append(CRLF);
			result.append("\t@ResponseBody" + CRLF);
			result.append("\t@RequestMapping(value = \"/deletes\", method = RequestMethod.POST)" + CRLF);
			result.append("\tpublic String deletes(@RequestParam(\"ids[]\") List<Integer> ids) {" + CRLF);
			result.append("\t\tfor (int id : ids) {" + CRLF);
			result.append("\t\t\tcodeTableService.delete(id);" + CRLF);
			result.append("\t\t}" + CRLF);
			result.append("\t\treturn \"{}\";" + CRLF);
			result.append("\t}" + CRLF);
			result.append("}" + CRLF);
			
			System.out.println(result);
			
			File file = new File("F:/test" + "/" + javaPath + "/" + serviceImplPackage.replace('.', '/') + "/" + targetClassName + "Seervice.java");
			try {
//				OutputStream out = new FileOutputStream(file);
//				out.write(result.toString().getBytes());
//				out.flush();
//				out.close();
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		private boolean init() {
			try {
				targetClass = Class.forName(modelPackage + "." + targetClassName);
				Field[] fields = targetClass.getDeclaredFields();
				classFields = new ArrayList<ClassField>(fields.length);
				for (Field field : fields) {
					if (field.getModifiers() == Modifier.PRIVATE) {
						if (field.getName().equals(entityId)) {
							idField = new ClassField(field);
						} else {
							classFields.add(new ClassField(field));
						}
					}
				}
				System.out.println(classFields);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public Generator(String projectPath, String javaPath, String modelPackage, String mappingPackage, String daoPackage, String servicePackage, String serviceImplPackage, String controllerPackage, String targetClassName, String tableName, String entityId) {
			this.projectPath = projectPath;
			this.javaPath = javaPath;
			this.modelPackage = modelPackage;
			this.mappingPackage = mappingPackage;
			this.daoPackage = daoPackage;
			this.servicePackage = servicePackage;
			this.serviceImplPackage = serviceImplPackage;
			this.controllerPackage = controllerPackage;
			this.targetClassName = targetClassName;
			this.tableName = tableName;
			this.entityId = entityId;
			this.recordName = Character.toLowerCase(targetClassName.charAt(0)) + targetClassName.substring(1);
			
			init();
		}
	}
	
	public static void main(String[] args) {
		final String projectPath = "E:/Workspaces/MyEclipse 2015 CI/LightCMS";
		final String javaPath = "src/main/java";
		final String modelPackage = "com.mingslife.model";
		final String mappingPackage = "com.mingslife.mapping";
		final String daoPackage = "com.mingslife.dao";
		final String servicePackage = "com.mingslife.service";
		final String serviceImplPackage = "com.mingslife.service.impl";
		final String controllerPackage = "com.mingslife.controller";
		
		String targetClassName = "Category";
		String tableName = "categories";
		String entityId = "id";
		
		Generator generator = new Generator(projectPath, javaPath, modelPackage, mappingPackage, daoPackage, servicePackage, serviceImplPackage, controllerPackage, targetClassName, tableName, entityId);
//		generator.generateMapperFile();
//		generator.generateDaoFile();
//		generator.generateServiceFile();
//		generator.generateServiceImplFile();
		generator.generateControllerFile();
	}
}
