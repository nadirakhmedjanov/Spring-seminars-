package ru.gb.task2.repositories;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gb.task2.model.User;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql="select * from userTable";


        RowMapper<User> userRowMapper = (r, i) -> {//-
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));//-
            rowObject.setFirstName(r.getString("firstName")); //-
            rowObject.setLastName(r.getString("lastName"));//-
            rowObject.setId(r.getInt("id"));//+
            rowObject.setFirstName(r.getString("firstName"));//+
            rowObject.setLastName(r.getString("lastName"));//+
            return rowObject;
        };

        
        return jdbc.query(sql, userRowMapper);
    }
    
    public User save(User user) {
        String sql = "INSERT INTO userTable Values (NULL, ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }
    public void deleteById(int id) {
    String sql = "DELETE FROM userTable WHERE id=?";
    jdbc.update(sql, id);
    }
}

