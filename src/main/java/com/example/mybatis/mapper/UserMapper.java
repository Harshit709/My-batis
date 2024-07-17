package com.example.mybatis.mapper;

import com.example.mybatis.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    // Define the resultMap for mapping columns to properties
    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results(id = "userMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "address", column = "address"),
            @Result(property = "city", column = "city"),
            @Result(property = "state", column = "state"),
            @Result(property = "country", column = "country"),
            @Result(property = "zipCode", column = "zip_code")
    })
    User getUserById(int id);

    @Select("SELECT * FROM users")
    @ResultMap("userMap")
    List<User> getAllUsers();

    @Insert("INSERT INTO users(first_name, last_name, email, phone, address, city, state, country, zip_code) " +
            "VALUES(#{firstName}, #{lastName}, #{email}, #{phone}, #{address}, #{city}, #{state}, #{country}, #{zipCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Update("UPDATE users SET first_name=#{firstName}, last_name=#{lastName}, email=#{email}, phone=#{phone}, " +
            "address=#{address}, city=#{city}, state=#{state}, country=#{country}, zip_code=#{zipCode} WHERE id=#{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void deleteUser(int id);

    @Select("SELECT * FROM users LIMIT #{offset}, #{limit}")
    @ResultMap("userMap")
    List<User> getUsersWithPagination(@Param("offset") int offset, @Param("limit") int limit);

//dynamic queries
    @Select("<script>" +
            "SELECT * FROM users " +
            "<where>" +
            "<if test='firstName != null'> AND first_name = #{firstName} </if>" +
            "<if test='lastName != null'> AND last_name = #{lastName} </if>" +
            "<if test='email != null'> AND email = #{email} </if>" +
            "</where>" +
            "</script>")
    @ResultMap("userMap")
    List<User> searchUsers(@Param("firstName") String firstName,
                           @Param("lastName") String lastName,
                           @Param("email") String email);

// batch process
    @Insert("<script>" +
            "INSERT INTO users (first_name, last_name, email, phone, address, city, state, country, zip_code) VALUES " +
            "<foreach collection='users' item='user' index='index' separator=','>" +
            "(#{user.firstName}, #{user.lastName}, #{user.email}, #{user.phone}, #{user.address}, #{user.city}, #{user.state}, #{user.country}, #{user.zipCode})" +
            "</foreach>" +
            "</script>")
    void batchInsertUsers(@Param("users") List<User> users);

    @Update("<script>" +
            "UPDATE users SET " +
            "<foreach collection='users' item='user' separator=','>" +
            "<choose>" +
            "<when test='user.id != null'>" +
            "first_name=#{user.firstName}, last_name=#{user.lastName}, email=#{user.email}, phone=#{user.phone}, " +
            "address=#{user.address}, city=#{user.city}, state=#{user.state}, country=#{user.country}, zip_code=#{user.zipCode} " +
            "WHERE id=#{user.id}" +
            "</when>" +
            "</choose>" +
            "</foreach>" +
            "</script>")
    void batchUpdateUsers(@Param("users") List<User> users);



}
