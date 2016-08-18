package com.mingslife.web.util;

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
		
		private Class<?> targetClass;
		private List<ClassField> classFields;
		
		public boolean generateMappingFile() {
			int classFieldsSize = classFields.size();
			
			StringBuilder result = new StringBuilder();
			
			result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + CRLF);
			result.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">" + CRLF);
			result.append("<mapper namespace=\"" + daoPackage + "." + targetClassName + "Mapper\">" + CRLF);
			result.append("  <resultMap id=\"BaseResultMap\" type=\"" + modelPackage + "." + targetClassName + "\">" + CRLF);
			for (ClassField classField : classFields) {
				result.append("    <");
				if (classField.getName().equals("id")) {
					result.append("id");
				} else {
					result.append("result");
				}
				result.append(" column=\"" + classField.getColumnName() + "\" property=\"" + classField.getName() + "\" jdbcType=\"" + classField.getJdbcType() + "\" />" + CRLF);
			}
			result.append("  </resultMap>" + CRLF);
			result.append("  <sql id=\"BaseColumnList\">" + CRLF);
			result.append("    ");
			for (int i = 0; i < classFieldsSize; i++) {
				ClassField classField = classFields.get(i);
				String columnName = classField.getColumnName();
				result.append(columnName + ", ");
				if ((i + 1) % 6 == 0) {
					result.setLength(result.length() - 1);
					result.append(CRLF);
					result.append("    ");
				}
			}
			result.setLength(result.length() - 2);
			result.append(CRLF);
			result.append("  </sql>" + CRLF);
			result.append("  <select id=\"selectByPrimaryKey\" resultMap=\"ResultMapWithBLOBs\" parameterType=\"java.lang.Integer\">" + CRLF);
			result.append("    select" + CRLF);
			result.append("      <include refid=\"BaseColumnList\" />" + CRLF);
			result.append("    from " + tableName + CRLF);
			result.append("  </select>" + CRLF);
			result.append("  <delete id=\"deleteByPrimaryKey\" parameterType=\"java.lang.Integer\">" + CRLF);
			result.append("    delete from " + tableName + CRLF);
			result.append("    where id = #{id,jdbcType=INTEGER}" + CRLF);
			result.append("  </delete>" + CRLF);
			result.append("  <insert id=\"insert\" parameterType=\"" + targetClass.getName() + "\" useGeneratedKeys=\"true\" keyProperty=\"id\">" + CRLF);
			result.append("    insert into " + tableName + " (" + CRLF);
			result.append("      ");
			for (int i = 0; i < classFieldsSize; i++) {
				ClassField classField = classFields.get(i);
				String columnName = classField.getColumnName();
				result.append(columnName + ", ");
				if ((i + 1) % 3 == 0) {
					result.setLength(result.length() - 1);
					result.append(CRLF);
					result.append("      ");
				}
			}
			result.setLength(result.length() - 2);
			result.append(CRLF);
			result.append("    ) values (" + CRLF);
			result.append("      ");
			for (int i = 0; i < classFieldsSize; i++) {
				ClassField classField = classFields.get(i);
				String name = classField.getName();
				String jdbcType = classField.getJdbcType();
				result.append("#{" + name + ",jdbcType=" + jdbcType + "}, ");
				if ((i + 1) % 3 == 0) {
					result.setLength(result.length() - 1);
					result.append(CRLF);
					result.append("      ");
				}
			}
			result.setLength(result.length() - 2);
			result.append(CRLF);
			result.append("    )" + CRLF);
			result.append("  </insert>" + CRLF);
			result.append("</mapper>" + CRLF);
			
			System.out.println(result);
			
			return true;
		}
		
		private boolean init() {
			try {
				targetClass = Class.forName(modelPackage + "." + targetClassName);
				Field[] fields = targetClass.getDeclaredFields();
				classFields = new ArrayList<ClassField>(fields.length);
				for (Field field : fields) {
					if (field.getModifiers() == Modifier.PRIVATE) {
						classFields.add(new ClassField(field));
					}
				}
				System.out.println(classFields);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public Generator(String projectPath, String javaPath, String modelPackage, String mappingPackage, String daoPackage, String servicePackage, String serviceImplPackage, String controllerPackage, String targetClassName, String tableName) {
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
		
		Generator generator = new Generator(projectPath, javaPath, modelPackage, mappingPackage, daoPackage, servicePackage, serviceImplPackage, controllerPackage, targetClassName, tableName);
		generator.generateMappingFile();
	}
}
