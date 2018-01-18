package com.elextec.bi.mapper;

import com.elextec.bi.common.entity.PageQuery;
import com.elextec.bi.entity.BiForeignFinancing;
import com.elextec.bi.entity.BiMenu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface BiForeignFinancingMapper {

	@Select("select j.\"ID\",\n" +
			"       j.ORG_ID,\n" +
			"       (case when j.\"TYPE\" = '0' then '金融机构' " +
			"             when j.\"TYPE\" = '1' then '非金融机构' " +
			"             when j.\"TYPE\" = '2' then '外部借款' " +
			"             when j.\"TYPE\" = '3' then '员工' " +
			"        end) TYPE,\n" +
			"       j.REGION_COM_NAME,\n" +
			"       j.BANK_COM_NAME,\n" +
			"       j.LOAN_AMOUNT,\n" +
			"       j.LOAN_DATE,\n" +
			"       j.APPOINTMENT_REPAYMENT_DATE,\n" +
			"       j.YEAR_COMPREHENSIVE_COST,\n" +
			"       j.COST_PAY_TYPE,\n" +
			"       j.INTEREST_PAY_TIME,\n" +
			"       j.PRESS_INFO,\n" +
			"       j.CREDIT_TERM,\n" +
			"       j.PRESS_TERM,\n" +
			"       j.\"REMARK\",\n" +
			"       j.CREATE_TIME,o.\"org_name\" AS ORG_NAME FROM BI_FOREIGN_FINANCING j LEFT JOIN (select \"org_id\",\"org_name\" FROM BI_ORG_STAGES GROUP BY \"org_id\",\"org_name\") o ON j.ORG_ID = o.\"org_id\" ORDER BY j.ORG_ID,j.\"TYPE\",j.REGION_COM_NAME")
	@Results(id="biForeignFinancingOnly",
	value={
		@Result(id = true, property = "id", column = "id"),
		@Result(property = "orgId",  column = "org_id"),
        @Result(property = "orgName", column = "org_name"),
        @Result(property = "type", column = "type"),
        @Result(property = "regionComName", column = "region_com_name"),
		@Result(property = "bankComName", column = "bank_com_name"),
        @Result(property = "loanAmount", column = "loan_amount"),
        @Result(property = "loanDate", column = "loan_date"),
        @Result(property = "appointmentRepaymentDate", column = "appointment_repayment_date"),
		@Result(property = "yearComprehensiveCost", column = "year_comprehensive_cost"),
		@Result(property = "costPayType", column = "cost_pay_type"),
		@Result(property = "interestPayTime", column = "interest_pay_time"),
		@Result(property = "pressInfo", column = "press_info"),
		@Result(property = "creditTerm", column = "credit_term"),
		@Result(property = "pressTerm", column = "press_term"),
		@Result(property = "remark", column = "remark"),
		@Result(property = "createTime", column = "create_time")
    })
	List<BiForeignFinancing> queryAll();

	@SelectProvider(type = BiMapperProvider.class, method = "findForeignFinancingByPage")
	@ResultMap("biForeignFinancingOnly")
	List<BiForeignFinancing> pageList(@Param("objName") String objName, @Param("page") PageQuery pageQuery);

	@SelectProvider(type = BiMapperProvider.class, method = "findForeignFinancingCount")
	int pageListCount(@Param("objName") String objName);

	@Insert("INSERT INTO BI_FOREIGN_FINANCING(ID,\n" +
			"        ORG_ID,\n" +
			"        \"TYPE\",\n" +
			"        REGION_COM_NAME,\n" +
			"        BANK_COM_NAME,\n" +
			"        LOAN_AMOUNT,\n" +
			"        LOAN_DATE,\n" +
			"        APPOINTMENT_REPAYMENT_DATE,\n" +
			"        YEAR_COMPREHENSIVE_COST,\n" +
			"        COST_PAY_TYPE,\n" +
			"        INTEREST_PAY_TIME,\n" +
			"        PRESS_INFO,\n" +
			"        CREDIT_TERM,\n" +
			"        PRESS_TERM,\n" +
			"        \"REMARK\",\n" +
			"        CREATE_TIME)"
			+ " VALUES(sys_guid(),\n" +
			"        #{orgId},\n" +
			"        #{type},\n" +
			"        #{regionComName},\n" +
			"        #{bankComName,jdbcType=VARCHAR},\n" +
			"        #{loanAmount,jdbcType=DECIMAL},\n" +
			"        #{loanDate,jdbcType=TIMESTAMP},\n" +
			"        #{appointmentRepaymentDate,jdbcType=TIMESTAMP},\n" +
			"        #{yearComprehensiveCost,jdbcType=DECIMAL},\n" +
			"        #{costPayType,jdbcType=VARCHAR},\n" +
			"        #{interestPayTime,jdbcType=TIMESTAMP},\n" +
			"        #{pressInfo,jdbcType=VARCHAR},\n" +
			"        #{creditTerm,jdbcType=VARCHAR},\n" +
			"        #{pressTerm,jdbcType=VARCHAR},\n" +
			"        #{remark,jdbcType=VARCHAR},\n" +
			"        sysdate\n" +
			"        )")
//	@Options(useGeneratedKeys = true, keyProperty = "ID")
	void insert(BiForeignFinancing foreignFinancing);

	@Update("UPDATE BI_FOREIGN_FINANCING SET ORG_ID= #{orgId},\n" +
			"        \"TYPE\"= #{type},\n" +
			"        REGION_COM_NAME= #{regionComName},\n" +
			"        BANK_COM_NAME= #{bankComName,jdbcType=VARCHAR},\n" +
			"        LOAN_AMOUNT= #{loanAmount,jdbcType=DECIMAL},\n" +
			"        LOAN_DATE= #{loanDate,jdbcType=TIMESTAMP},\n" +
			"        APPOINTMENT_REPAYMENT_DATE= #{appointmentRepaymentDate,jdbcType=TIMESTAMP},\n" +
			"        YEAR_COMPREHENSIVE_COST= #{yearComprehensiveCost,jdbcType=DECIMAL},\n" +
			"        COST_PAY_TYPE= #{costPayType,jdbcType=VARCHAR},\n" +
			"        INTEREST_PAY_TIME= #{interestPayTime,jdbcType=TIMESTAMP},\n" +
			"        PRESS_INFO= #{pressInfo,jdbcType=VARCHAR},\n" +
			"        CREDIT_TERM= #{creditTerm,jdbcType=VARCHAR},\n" +
			"        PRESS_TERM= #{pressTerm,jdbcType=VARCHAR},\n" +
			"        \"REMARK\"= #{remark,jdbcType=VARCHAR}\n" +
			"        WHERE ID= #{id}")
	void update(BiForeignFinancing foreignFinancing);

	@Delete("DELETE FROM BI_FOREIGN_FINANCING WHERE ID = #{id}")
	void delete(String id);
	
}
