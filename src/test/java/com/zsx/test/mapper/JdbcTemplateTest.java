package com.zsx.test.mapper;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.UUID;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void create() {
        String sqlDropDatabase = "DROP DATABASE IF EXISTS my_schema ";
        jdbcTemplate.execute(sqlDropDatabase);
        String sqlDatabase = "CREATE DATABASE my_schema";
        jdbcTemplate.execute(sqlDatabase);
        String sqlDrop = "DROP SCHEMA IF EXISTS my_schema CASCADE";
        jdbcTemplate.execute(sqlDrop);
        String sqlSchema = "CREATE schema my_schema";
        jdbcTemplate.execute(sqlSchema);
        String sqlTable = "CREATE TABLE my_schema.t_test(\"test_id\" varchar(64), \"test_json\" jsonb)";
        jdbcTemplate.execute(sqlTable);
    }

    @Test
    void insert() {
        String sql = "INSERT INTO myschema.test_jsonb VALUES (?, ?::jsonb)";
        // 不可变Map
        Map<String, String> personMap = ImmutableMap.<String, String> builder()
                .put("personId", "126")
                .put("name", "Tom")
                .put("age", "22")
                .put("createTime", "2019-10-31 17:51:23")
                .build();
        String id = "4495bf53-3cf9-4048-b97e-c1505f7748c0";
//        jdbcTemplate.update(sql, new Object[] {UUID.randomUUID().toString(), JSON.toJSONString(personMap)});
        jdbcTemplate.update(sql, new Object[] {id, JSON.toJSONString(personMap)});
    }

    @Test
    void update() {
        String sql = "UPDATE myschema.test_jsonb SET testjson = ?::jsonb WHERE testid = ?";
        // 不可变Map
        Map<String, String> personMap = ImmutableMap.<String, String> builder()
                .put("personId", "128")
                .put("name", "Tom1")
                .put("age", "20")
                .put("createTime", "2019-10-31 17:51:23")
                .build();
        String id = "4495bf53-3cf9-4048-b97e-c1505f7748c0";
        jdbcTemplate.update(sql, new Object[] {JSON.toJSONString(personMap), id});
    }

    @Test
    void delete() {
        String sql = "DELETE FROM myschema.test_jsonb WHERE testjson ::jsonb->>'age' = ? ";
        jdbcTemplate.update(sql, new Object[] {"22"});
    }
}
